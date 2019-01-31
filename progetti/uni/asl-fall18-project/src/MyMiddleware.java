import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.logging.Logger;

public class MyMiddleware implements Runnable {

	public String myIp = null;
	public int myPort = 0;
	public List<String> mcAddresses = null;
	public int numThreadsPTP;

	public Logger log;

	public boolean readSharded = false;

	public Socket memcache_socket;
	
	public static ExecutorService executorService;

	public InitializingThreadFactory threadFactory;

	public static AtomicInteger rr_counter = new AtomicInteger(0);
	public static long startTime, lastLogTime;

	public MyMiddleware(String myIp, int myPort, List<String> mcAddresses, int numThreadsPTP, boolean readSharded) {
		this.myIp = myIp;
		this.myPort = myPort;
		this.mcAddresses = mcAddresses;
		this.numThreadsPTP = numThreadsPTP;
		this.readSharded = readSharded;
		this.startTime = System.nanoTime();
		this.threadFactory = new InitializingThreadFactory(mcAddresses.toArray(new String [mcAddresses.size()]));
		executorService = Executors.newFixedThreadPool(numThreadsPTP, threadFactory);
		try {
			String [] memcache_addr = mcAddresses.get(0).split(":");
			this.memcache_socket = new Socket(memcache_addr[0], Integer.parseInt(memcache_addr[1]));
		} catch (Exception e){}

		MyLog.initializeLogger();
		this.log = MyLog.getLogger();

		Runtime.getRuntime().addShutdownHook(shutdown); // catch SIGINT

		log.info("Created middleware at " + myIp + " port " + myPort + " with " + numThreadsPTP + " threads!");
	}

	public Thread shutdown = new Thread () {
		@Override 
		public void run() {
			log.info("Saving samplings...");
			threadFactory.stopSampling();
			log.info("Shutting down server...");
			executorService.shutdown();
			try {
				executorService.awaitTermination(10000, TimeUnit.MILLISECONDS); // TODO 2018/10/09 14:28 -  time limit?
				log.info("Finished jobs.");
				executorService.shutdownNow();
				threadFactory.cleanup(); //clean
				MyLog.shutdown();
			} catch (InterruptedException e) {}
		}
	};

	@Override
	public void run () {
		try {
		Selector selector = Selector.open(); 
		ServerSocketChannel socket = ServerSocketChannel.open();
		socket.bind(new InetSocketAddress(myIp, myPort), 1024);
		socket.configureBlocking(false);
		SelectionKey selectKy = socket.register(selector, socket.validOps(), null);
		Map<SelectionKey, RequestData> pendingMap = new HashMap<>();



		//Thread.currentThread().setPriority(Thread.MAX_PRIORITY); //TODO wtf



		while (!executorService.isShutdown()) {
			selector.select(1); // TODO 2018/10/09 14:45 -  select timeout

			// token representing the registration of a SelectableChannel with a Selector
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();

			while (keyIterator.hasNext()) {
				SelectionKey myKey = keyIterator.next();
				if (myKey.isAcceptable()) {
					SocketChannel client = socket.accept();
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_READ);
					//log.info("Connection Accepted: " + client.getRemoteAddress());
				} else if (myKey.isReadable()) {
					long from_client_time = System.nanoTime();
					SocketChannel client = (SocketChannel) myKey.channel();

					RequestData request;
					String result;
					ByteBuffer buffer = ByteBuffer.allocate(8192); 
					RequestData.kind request_type;
					int read = -1;
					int sum = 0;

					read = client.read(buffer);
					sum += read;
					if (read == -1) {
						//log.warning("Read -1, closing connection");
						client.close();
						continue;
					}

					byte bytes[] = new byte[sum];
					System.arraycopy(buffer.array(), 0, bytes, 0, sum);
					result = new String(bytes);

					if (pendingMap.containsKey(myKey)) {
						request = pendingMap.get(myKey);
						request.request += result;
						if (request.request.length() < request.size + 2) {
							continue;
						} else {
							request.request.trim();
							pendingMap.remove(myKey);
							request.saveTime(RequestData.time.enqueue);
						}
					} else {
						int end_header = result.indexOf('\r');
						String parole[] = result.substring(0, end_header).split("[ \r\n]+"); 

						if (parole[0].equals("get") || parole.length < 4) {
							if (parole.length > 2 && readSharded) request_type = RequestData.kind.multiget;
							else request_type = RequestData.kind.get;
						} else {
							request_type = RequestData.kind.set;
						}

						request = new RequestData();
						request.setRequest(result);
						request.setKind(request_type);
						request.saveTime(RequestData.time.from_client, from_client_time);

						if (request_type == RequestData.kind.set) {
							int to_read = Integer.parseInt(parole[4]);
							request.size = to_read;
							if (sum < to_read + 2) { 
								pendingMap.put(myKey, request);
								continue;
							}
						}
						request.saveTime(RequestData.time.enqueue);
					}


					if (executorService.isShutdown()) {
						log.severe("executorService shutdown, cancelling query");
						break;
					}
					try {
						executorService.execute(new RequestManager(client, request));
					} catch (RejectedExecutionException e) {
						e.printStackTrace();
						log.severe("Something went wrong while shutting down.");
					}

					if (result.equals("close")) {
						client.close();
					}
				}
				keyIterator.remove();
			}
		}
		} catch (Exception e) {e.printStackTrace();}
	}
}

class RequestManager implements Runnable {
	private String words [];
	private SocketChannel client;
	private RequestData request_data;
	private InitializingThread thread;

	public RequestManager(SocketChannel client, RequestData request) {
		this.client = client;
		this.request_data = request;
	}

	@Override
	public void run () {
		try {
			request_data.saveTime(RequestData.time.dequeue);

			this.thread = (InitializingThread) Thread.currentThread();
			String response = "ERROR\r\n";

			RequestData.kind type = request_data.getKind();

			if (type == RequestData.kind.get) {
				response = query_memcached_get(request_data.request);
			} else {
				String [] words = request_data.request.trim().split("\\s");
				String query = "";
				if (type == RequestData.kind.set && words.length >= 7) {
					query = "set " + words[1] + " " + words[2] + " " + words[3] + " " + words[4];
					query += " " + words[5] + "\r\n" + words[6];
					query += "\r\n";
					response = query_memcached_set(query);
				} else if (type == RequestData.kind.multiget) {
					response = query_memcached_multiget(Arrays.copyOfRange(words, 1, words.length));
				}
			}

			//String [] words = request_data.request.trim().split("\\s");
			//String query = "";
			//if (words[0].equals("get")) {
				//if (words.length == 2) {
					//query = "get " + words[1] + "\r\n";
					//request_data.setKind(RequestData.kind.get);
					//response = query_memcached_get(query);
				//} else if (words.length > 2) {
					//request_data.setKind(RequestData.kind.multiget);
					//response = query_memcached_multiget(Arrays.copyOfRange(words, 1, words.length));
				//}
			//} else if (words[0].equals("set")) {
				//if (words.length >= 7) {
					//query = "set " + words[1] + " " + words[2] + " " + words[3] + " " + words[4];
					//query += " " + words[5] + "\r\n" + words[6];
					//query += "\r\n";
					//request_data.setKind(RequestData.kind.set);
					//response = query_memcached_set(query);
				//}
			//}

			ByteBuffer b = ByteBuffer.wrap(response.getBytes("UTF-8"));
			client.write(b);
			request_data.saveTime(RequestData.time.to_client);
			request_data.request = "";		// do not need this anymore
			thread.requests.add(request_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String query_memcached_get (String query) {
		String res = "";
		try {
			int rr_counter = MyMiddleware.rr_counter.getAndIncrement();
			PrintWriter [] pws = thread.pws;
			InputStream [] iss = thread.iss;
			PrintWriter mypw = pws[rr_counter % pws.length];
			InputStream myis = iss[rr_counter % iss.length];

			query = query.trim() + "\r\n";
			mypw.write(query);
			mypw.flush();

			request_data.saveTime(RequestData.time.to_server);
			
			byte[] buffer = new byte[4096*10]; // TODO 2018/09/30 11:31 - check buffer size 
			int read;
			while((read = myis.read(buffer)) != -1) {
				String response = new String(buffer, 0, read);
				//System.out.println("Got answer from memcached: " + response);
				res += response;
				if (response.endsWith("END\r\n")) {
					break;
				} else if (response.endsWith("ERROR\r\n")) {
					return "ERROR\r\n";
				}
			};
		} catch (IOException e) {e.printStackTrace();}
		return res;
	}

	private String query_memcached_multiget (String [] queries) {
		String res = "";
		try {
			int rr_counter = MyMiddleware.rr_counter.getAndIncrement();
			PrintWriter [] pws = thread.pws;
			InputStream [] iss = thread.iss;

			int queries_per_server = (int) Math.ceil((float)queries.length / pws.length);

			for (int k = 0; k < pws.length; k++) {
				// TODO 2018/10/04 01:24 -  implement round robin here
				String query = "get";
				for (int i = 0; i < queries_per_server && k*queries_per_server + i < queries.length; i++) {
					query += " " + queries[k*queries_per_server + i];
				}
				query += "\r\n";
				pws[k].write(query);
				pws[k].flush();
			}

			request_data.saveTime(RequestData.time.to_server);

			byte[] buffer = new byte[4096*10]; 
			int read;
			boolean failed = false, exit = false;
			for (InputStream is : iss) {
				String answer = "";
				while (( read = is.read(buffer)) != -1) {
					String response = new String(buffer, 0, read);
					answer += response;
					if (answer.endsWith("ERROR\r\n")) {
						failed = true;
						break;
					} else if (answer.endsWith("END\r\n")) {
						res += answer.substring(0, answer.length() - 5);
						break;
					}
				}
			}
			res += "END\r\n";
			return failed ? "ERROR\r\n" : res;
		} catch (IOException e) {e.printStackTrace();}
		return "ERROR\r\n";
	}

	private String query_memcached_set (String query) {
		try {
			PrintWriter [] pws = thread.pws;
			InputStream [] iss = thread.iss;

			for (PrintWriter pw : pws) {
				pw.write(query);
			}
			for (PrintWriter pw : pws) {
				pw.flush();
			}
			
			request_data.saveTime(RequestData.time.to_server);

			byte[] buffer = new byte[100]; 
			int read;
			boolean failed = false;
			for (InputStream is : iss) {
				while (( read = is.read(buffer)) != -1) {
					String response = new String(buffer, 0, read);
					//System.out.println("got response" + response);
					if (response.endsWith("NOT_STORED\r\n") || response.endsWith("ERROR\r\n")) {
						failed = true;
						break;
					} else if (response.endsWith("STORED\r\n")) {
						break;
					}
				}
			}
			return failed ? "ERROR\r\n" : "STORED\r\n";

		} catch (IOException e) {e.printStackTrace();}
		return "ERROR\r\n";
	}
}

class InitializingThread extends Thread {
	private String [] memcache_addrs;
	public Socket [] memcache_sockets;
	public PrintWriter [] pws;
	public InputStream [] iss;
	public int lastRequest = 0;
	public ArrayList<RequestData> requests = new ArrayList<RequestData>();

    public InitializingThread(Runnable r, String [] memcache_addrs) {
		super(r);
		this.memcache_addrs = memcache_addrs;
    }

    public void run() {
		try {
			memcache_sockets = new Socket [memcache_addrs.length];
			pws = new PrintWriter [memcache_addrs.length];
			iss = new InputStream [memcache_addrs.length];
			for (int i = 0; i < memcache_sockets.length; i++) {
				String [] addr = memcache_addrs[i].split(":");
				memcache_sockets[i] = new Socket(addr[0], Integer.parseInt(addr[1]));
				memcache_sockets[i].setReuseAddress(true);
				pws[i] = new PrintWriter(memcache_sockets[i].getOutputStream());
				iss[i] = memcache_sockets[i].getInputStream();
			}
		} catch (Exception e) {e.printStackTrace();}
        super.run();
    }
}

class InitializingThreadFactory implements ThreadFactory {
	private String [] memcache_addrs;
	private boolean running_sampling = true;
	private Logger log;
	private List<InitializingThread> dyson_palle = new ArrayList<InitializingThread> ();

	public InitializingThreadFactory (String [] memcache_addrs) {
		this.memcache_addrs = memcache_addrs;
		log = MyLog.getLogger();
		sampler.start();
	}

    public Thread newThread(Runnable r) {
		InitializingThread thread = new InitializingThread (r, memcache_addrs);
		dyson_palle.add(thread);
        return thread;
    }

	private void log() {
		long experiment_time = System.nanoTime() - MyMiddleware.startTime;
		long elapsed_time = System.nanoTime() - MyMiddleware.lastLogTime;
		MyMiddleware.lastLogTime = System.nanoTime();
		int request_types = RequestData.kind.values().length;

		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../middleware_log.log",true)))) {
			for (int i = 0; i < dyson_palle.size(); i++) {
					InitializingThread t = dyson_palle.get(i);
					int size = t.requests.size() - t.lastRequest;

					if (size == 0) continue;

					int count [] = new int [request_types];
					long total_response_time = 0, total_queue_time = 0;
					long total_memcached_time = 0, total_calculating_time = 0;
					long total_service_time = 0, total_mainthread_time = 0;

					for (;t.lastRequest < t.requests.size(); t.lastRequest++) {
						RequestData r = t.requests.get(t.lastRequest);
						total_response_time += r.getTime(RequestData.time.to_client, RequestData.time.from_client);
						total_queue_time += r.getTime(RequestData.time.dequeue, RequestData.time.enqueue);
						total_memcached_time += r.getTime(RequestData.time.to_client, RequestData.time.to_server);
						total_calculating_time += r.getTime(RequestData.time.to_server, RequestData.time.dequeue);
						total_service_time += r.getTime(RequestData.time.to_client, RequestData.time.dequeue);
						total_mainthread_time += r.getTime(RequestData.time.enqueue, RequestData.time.from_client);
						count[r.getKind().ordinal()]++;
					}


					experiment_time = System.nanoTime() - MyMiddleware.startTime;
					double current_time = (double) experiment_time / 1_000_000_000.0;
					double average_response_time = (double) total_response_time / size / 1_000_000.0;
					double average_queue_time = (double) total_queue_time / size / 1_000_000.0;
					double average_memcached_time = (double) total_memcached_time / size / 1_000_000.0;
					double average_calculating_time = (double) total_calculating_time / size / 1_000_000.0;
					double average_mainthread_time = (double) total_mainthread_time / size / 1_000_000.0;
					double throughput = size / ((double) elapsed_time / 1_000_000_000.0);
					int queue_length = ((ThreadPoolExecutor) MyMiddleware.executorService).getQueue().size(); 
					int no_sets = count[RequestData.kind.set.ordinal()];
					int no_gets = count[RequestData.kind.get.ordinal()];
					int no_mgets =count[RequestData.kind.multiget.ordinal()];

					//log.fine("Thread " + i + " -- gets: " + no_gets + " [" + no_mgets + "] -- sets: " + no_sets + " -- avg: " + average_response_time);

					out.println("time="+current_time+",thread="+i+
							",resp_time="+average_response_time+",throughput="+throughput+
							",queue_time="+average_queue_time+",queue_size="+queue_length+
							",calculating="+average_calculating_time+",waiting="+average_memcached_time+
							",mainthread="+average_mainthread_time+
							",gets="+no_gets+",sets="+no_sets);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void percentiles() {
		log.fine("Printing percentiles");
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../middleware_percentiles.log",true)))) {
			int gets_ms[] = new int [10 * 100];
			int sets_ms[] = new int [10 * 100];
			int request_types = RequestData.kind.values().length;
			int count [] = new int [request_types];

			for (int i = 0; i < gets_ms.length; i++) gets_ms[i] = 0;
			for (int i = 0; i < sets_ms.length; i++) gets_ms[i] = 0;

			for (int i = 0; i < dyson_palle.size(); i++) {
				InitializingThread t = dyson_palle.get(i);

				for (int j = 0; j < t.requests.size(); j++) {
					RequestData r = t.requests.get(j);
					count[r.getKind().ordinal()]++;
					if (r.getKind() == RequestData.kind.get || r.getKind() == RequestData.kind.multiget) {
						gets_ms[(int) (r.getTime(RequestData.time.to_client, RequestData.time.from_client) / 100_000.0)]++;
					} else {
						//sets_ms[(int) (r.getTime(RequestData.time.to_client, RequestData.time.from_client) / 100_000.0)]++;
					}
				}

			}

			out.println("Request Latency Distribution\nType     <= msec         Percent\n ------------------------------------------------------------------------");

			int no_sets = count[RequestData.kind.set.ordinal()];
			int no_gets = count[RequestData.kind.get.ordinal()];
			int no_mgets =count[RequestData.kind.multiget.ordinal()];

			for (int j = 0; j < gets_ms.length; j++) {
				out.println("GET      "+(double)j/10.0+"      "+gets_ms[j]+"      "+(double)(gets_ms[j])/(no_mgets + no_gets)*100.0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void stopSampling() {
		percentiles();
		running_sampling = false;
		sampler.interrupt();
	}

	public void cleanup() {
		for (int i = 0; i < dyson_palle.size(); i++) {
			InitializingThread t = dyson_palle.get(i);
			for (Socket s : t.memcache_sockets) {
				try { 
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Thread sampler = new Thread () {
		@Override
		public void run() {
			try {
				while (running_sampling) {
					log();
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				log.warning("Sampler interrupted!");
			}
		}
	};
}
