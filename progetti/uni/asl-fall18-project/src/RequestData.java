public class RequestData {

	public static enum time {
		from_client,
		enqueue,
		dequeue,
		to_server,
		to_client
	};

	public static enum kind {
		get,
		set,
		multiget,
		undefined
	};

	public long [] times;
	public kind selected;
	public String request;
	public int size;

	public RequestData () {
		times = new long [5];
	}

	public RequestData (String request) {
		this();
		this.request = request;
	}

	public void setKind (kind selected) {
		this.selected = selected;
	}

	public kind getKind () {
		return selected;
	}

	public void saveTime (time kind) {
		times[kind.ordinal()] = System.nanoTime();
	}

	public void saveTime (time kind, long time) {
		times[kind.ordinal()] = time;
	}

	public long getTime (time kind_start, time kind_end)  {
		return times[kind_start.ordinal()] - times[kind_end.ordinal()];
	}
	//a

	public void getElapsedTime () {

	}

	public void setRequest(String request) {
		this.request = request;
	}

}
