// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import jersey.repackaged.com.google.common.base.Predicates;
import jersey.repackaged.com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.util.concurrent.ListeningExecutorService;
import jersey.repackaged.com.google.common.util.concurrent.MoreExecutors;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.spi.AsyncConnectorCallback;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.glassfish.jersey.message.internal.Statuses;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            LocalizationMessages

public class HttpUrlConnector
    implements Connector
{

            public HttpUrlConnector(final Client client, org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory connectionfactory, int i, boolean flag, boolean flag1)
            {
/* 150*/        sslSocketFactory = Values.lazy(new Value() {

                    public SSLSocketFactory get()
                    {
/* 153*/                return client.getSslContext().getSocketFactory();
                    }

                    public volatile Object get()
                    {
/* 150*/                return get();
                    }

                    final Client val$client;
                    final HttpUrlConnector this$0;

                    
                    {
/* 150*/                this$0 = HttpUrlConnector.this;
/* 150*/                client = client1;
/* 150*/                super();
                    }
        });
/* 157*/        connectionFactory = connectionfactory;
/* 158*/        chunkSize = i;
/* 159*/        fixLengthStreaming = flag;
/* 160*/        setMethodWorkaround = flag1;
/* 169*/        LOGGER.config(isRestrictedHeaderPropertySet ? LocalizationMessages.RESTRICTED_HEADER_PROPERTY_SETTING_TRUE("sun.net.http.allowRestrictedHeaders") : LocalizationMessages.RESTRICTED_HEADER_PROPERTY_SETTING_FALSE("sun.net.http.allowRestrictedHeaders"));
            }

            private static InputStream getInputStream(HttpURLConnection httpurlconnection)
                throws IOException
            {
/* 176*/        return new InputStream(httpurlconnection) {

                    private void throwIOExceptionIfClosed()
                        throws IOException
                    {
/* 207*/                if(closed)
/* 208*/                    throw new IOException("Stream closed");
/* 210*/                else
/* 210*/                    return;
                    }

                    public final int read()
                        throws IOException
                    {
/* 214*/                int i = ((InputStream)in.get()).read();
/* 215*/                throwIOExceptionIfClosed();
/* 216*/                return i;
                    }

                    public final int read(byte abyte0[])
                        throws IOException
                    {
/* 221*/                abyte0 = ((InputStream)in.get()).read(abyte0);
/* 222*/                throwIOExceptionIfClosed();
/* 223*/                return abyte0;
                    }

                    public final int read(byte abyte0[], int i, int j)
                        throws IOException
                    {
/* 228*/                abyte0 = ((InputStream)in.get()).read(abyte0, i, j);
/* 229*/                throwIOExceptionIfClosed();
/* 230*/                return abyte0;
                    }

                    public final long skip(long l)
                        throws IOException
                    {
/* 235*/                long l1 = ((InputStream)in.get()).skip(l);
/* 236*/                throwIOExceptionIfClosed();
/* 237*/                return l1;
                    }

                    public final int available()
                        throws IOException
                    {
/* 242*/                int i = ((InputStream)in.get()).available();
/* 243*/                throwIOExceptionIfClosed();
/* 244*/                return i;
                    }

                    public final void close()
                        throws IOException
                    {
/* 250*/                ((InputStream)in.get()).close();
/* 252*/                closed = true;
/* 253*/                return;
                        Exception exception;
/* 252*/                exception;
/* 252*/                closed = true;
/* 252*/                throw exception;
                    }

                    public final void mark(int i)
                    {
/* 259*/                try
                        {
/* 259*/                    ((InputStream)in.get()).mark(i);
/* 262*/                    return;
                        }
                        // Misplaced declaration of an exception variable
/* 260*/                catch(int i)
                        {
/* 261*/                    throw new IllegalStateException("Unable to retrieve the underlying input stream.", i);
                        }
                    }

                    public final void reset()
                        throws IOException
                    {
/* 267*/                ((InputStream)in.get()).reset();
/* 268*/                throwIOExceptionIfClosed();
                    }

                    public final boolean markSupported()
                    {
/* 274*/                return ((InputStream)in.get()).markSupported();
                        IOException ioexception;
/* 275*/                ioexception;
/* 276*/                throw new IllegalStateException("Unable to retrieve the underlying input stream.", ioexception);
                    }

                    private final UnsafeValue in = Values.lazy(new UnsafeValue() {

                        public InputStream get()
                            throws IOException
                        {
/* 180*/                    if(uc.getResponseCode() < javax.ws.rs.core.Response.Status.BAD_REQUEST.getStatusCode())
/* 181*/                        return uc.getInputStream();
                            InputStream inputstream;
/* 183*/                    if((inputstream = uc.getErrorStream()) != null)
/* 184*/                        return inputstream;
/* 184*/                    else
/* 184*/                        return new ByteArrayInputStream(new byte[0]);
                        }

                        public volatile Object get()
                            throws Throwable
                        {
/* 177*/                    return get();
                        }

                        final _cls2 this$0;

                            
                            {
/* 177*/                        this$0 = _cls2.this;
/* 177*/                        super();
                            }
            });
                    private volatile boolean closed;
                    final HttpURLConnection val$uc;

                    
                    {
/* 176*/                uc = httpurlconnection;
/* 176*/                super();
/* 189*/                closed = false;
                    }
        };
            }

            public ClientResponse apply(ClientRequest clientrequest)
            {
/* 285*/        return _apply(clientrequest);
/* 286*/        clientrequest;
/* 287*/        throw new ProcessingException(clientrequest);
            }

            public Future apply(final ClientRequest request, final AsyncConnectorCallback callback)
            {
/* 293*/        return MoreExecutors.sameThreadExecutor().submit(new Runnable() {

                    public void run()
                    {
/* 297*/                try
                        {
/* 297*/                    callback.response(_apply(request));
/* 302*/                    return;
                        }
/* 298*/                catch(IOException ioexception)
                        {
/* 299*/                    callback.failure(new ProcessingException(ioexception));
/* 302*/                    return;
                        }
/* 300*/                catch(Throwable throwable)
                        {
/* 301*/                    callback.failure(throwable);
                        }
                    }

                    final AsyncConnectorCallback val$callback;
                    final ClientRequest val$request;
                    final HttpUrlConnector this$0;

                    
                    {
/* 293*/                this$0 = HttpUrlConnector.this;
/* 293*/                callback = asyncconnectorcallback;
/* 293*/                request = clientrequest;
/* 293*/                super();
                    }
        });
            }

            public void close()
            {
            }

            protected void secureConnection(JerseyClient jerseyclient, HttpURLConnection httpurlconnection)
            {
/* 322*/        if(httpurlconnection instanceof HttpsURLConnection)
                {
/* 323*/            httpurlconnection = (HttpsURLConnection)httpurlconnection;
/* 325*/            if((jerseyclient = jerseyclient.getHostnameVerifier()) != null)
/* 327*/                httpurlconnection.setHostnameVerifier(jerseyclient);
/* 330*/            if(HttpsURLConnection.getDefaultSSLSocketFactory() == httpurlconnection.getSSLSocketFactory())
/* 332*/                httpurlconnection.setSSLSocketFactory((SSLSocketFactory)sslSocketFactory.get());
                }
            }

            private ClientResponse _apply(final ClientRequest request)
                throws IOException
            {
                final HttpURLConnection uc;
/* 340*/        (uc = connectionFactory.getConnection(request.getUri().toURL())).setDoInput(true);
/* 343*/        Object obj = request.getMethod();
/* 344*/        if(((Boolean)request.resolveProperty("jersey.config.client.httpUrlConnection.setMethodWorkaround", Boolean.valueOf(setMethodWorkaround))).booleanValue())
/* 345*/            setRequestMethodViaJreBugWorkaround(uc, ((String) (obj)));
/* 347*/        else
/* 347*/            uc.setRequestMethod(((String) (obj)));
/* 350*/        uc.setInstanceFollowRedirects(((Boolean)request.resolveProperty("jersey.config.client.followRedirects", Boolean.valueOf(true))).booleanValue());
/* 352*/        uc.setConnectTimeout(((Integer)request.resolveProperty("jersey.config.client.connectTimeout", Integer.valueOf(uc.getConnectTimeout()))).intValue());
/* 354*/        uc.setReadTimeout(((Integer)request.resolveProperty("jersey.config.client.readTimeout", Integer.valueOf(uc.getReadTimeout()))).intValue());
/* 356*/        secureConnection(request.getClient(), uc);
                Object obj1;
/* 358*/        if((obj1 = request.getEntity()) != null)
                {
/* 360*/            if((obj1 = (RequestEntityProcessing)request.resolveProperty("jersey.config.client.request.entity.processing", org/glassfish/jersey/client/RequestEntityProcessing)) == null || obj1 != RequestEntityProcessing.BUFFERED)
                    {
/* 364*/                int j = request.getLength();
/* 365*/                if(fixLengthStreaming && j > 0)
/* 366*/                    uc.setFixedLengthStreamingMode(j);
/* 367*/                else
/* 367*/                if(obj1 == RequestEntityProcessing.CHUNKED)
/* 368*/                    uc.setChunkedStreamingMode(chunkSize);
                    }
/* 371*/            uc.setDoOutput(true);
                    Logger logger;
/* 373*/            if("GET".equalsIgnoreCase(((String) (obj))) && (logger = Logger.getLogger(org/glassfish/jersey/client/internal/HttpUrlConnector.getName())).isLoggable(Level.INFO))
/* 376*/                logger.log(Level.INFO, LocalizationMessages.HTTPURLCONNECTION_REPLACES_GET_WITH_ENTITY());
/* 380*/            request.setStreamProvider(new org.glassfish.jersey.message.internal.OutboundMessageContext.StreamProvider() {

                        public OutputStream getOutputStream(int k)
                            throws IOException
                        {
/* 384*/                    setOutboundHeaders(request.getStringHeaders(), uc);
/* 385*/                    return uc.getOutputStream();
                        }

                        final ClientRequest val$request;
                        final HttpURLConnection val$uc;
                        final HttpUrlConnector this$0;

                    
                    {
/* 380*/                this$0 = HttpUrlConnector.this;
/* 380*/                request = clientrequest;
/* 380*/                uc = httpurlconnection;
/* 380*/                super();
                    }
            });
/* 388*/            request.writeEntity();
                } else
                {
/* 391*/            setOutboundHeaders(request.getStringHeaders(), uc);
                }
/* 394*/        int i = uc.getResponseCode();
                String s;
/* 395*/        obj = (s = uc.getResponseMessage()) != null ? ((Object) (Statuses.from(i, s))) : ((Object) (Statuses.from(i)));
/* 400*/        try
                {
/* 400*/            i = uc.getURL().toURI();
                }
                // Misplaced declaration of an exception variable
/* 401*/        catch(final ClientRequest request)
                {
/* 402*/            throw new ProcessingException(request);
                }
/* 405*/        (request = new ClientResponse(((javax.ws.rs.core.Response.StatusType) (obj)), request, i)).headers(Maps.filterKeys(uc.getHeaderFields(), Predicates.notNull()));
/* 407*/        request.setEntityStream(getInputStream(uc));
/* 409*/        return request;
            }

            private void setOutboundHeaders(MultivaluedMap multivaluedmap, HttpURLConnection httpurlconnection)
            {
/* 413*/        boolean flag = false;
/* 414*/        multivaluedmap = multivaluedmap.entrySet().iterator();
/* 414*/        do
                {
/* 414*/            if(!multivaluedmap.hasNext())
/* 414*/                break;
                    Object obj;
/* 414*/            String s = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)multivaluedmap.next())).getKey();
/* 418*/            if(((List) (obj = (List)((java.util.Map.Entry) (obj)).getValue())).size() == 1)
                    {
/* 420*/                obj = (String)((List) (obj)).get(0);
/* 421*/                httpurlconnection.setRequestProperty(s, ((String) (obj)));
                    } else
                    {
/* 423*/                StringBuilder stringbuilder = new StringBuilder();
/* 424*/                boolean flag1 = false;
                        Object obj1;
/* 425*/                for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); stringbuilder.append(obj1))
                        {
/* 425*/                    obj1 = ((Iterator) (obj)).next();
/* 426*/                    if(flag1)
/* 427*/                        stringbuilder.append(',');
/* 429*/                    flag1 = true;
                        }

/* 432*/                obj = stringbuilder.toString();
/* 433*/                httpurlconnection.setRequestProperty(s, ((String) (obj)));
                    }
/* 436*/            if(!isRestrictedHeaderPropertySet && !flag && isHeaderRestricted(s, ((String) (obj))))
/* 438*/                flag = true;
                } while(true);
/* 442*/        if(flag)
/* 443*/            LOGGER.warning(LocalizationMessages.RESTRICTED_HEADER_POSSIBLY_IGNORED("sun.net.http.allowRestrictedHeaders"));
            }

            private boolean isHeaderRestricted(String s, String s1)
            {
/* 448*/        return (s = s.toLowerCase()).startsWith("sec-") || restrictedHeaderSet.contains(s) && (!"connection".equalsIgnoreCase(s) || !"close".equalsIgnoreCase(s1));
            }

            private static void setRequestMethodViaJreBugWorkaround(HttpURLConnection httpurlconnection, String s)
            {
/* 465*/        try
                {
/* 465*/            httpurlconnection.setRequestMethod(s);
/* 524*/            return;
                }
/* 466*/        catch(ProtocolException _ex) { }
/* 468*/        AccessController.doPrivileged(new PrivilegedExceptionAction(httpurlconnection, s) {

                    public final Object run()
                        throws NoSuchFieldException, IllegalAccessException
                    {
/* 474*/label0:
                        {
                            Class class1;
/* 474*/                    try
                            {
/* 474*/                        httpURLConnection.setRequestMethod(method);
/* 512*/                        break label0;
                            }
/* 477*/                    catch(ProtocolException _ex)
                            {
/* 478*/                        class1 = httpURLConnection.getClass();
/* 481*/                        try
                                {
                                    Object obj;
/* 481*/                            ((Field) (obj = class1.getDeclaredField("delegate"))).setAccessible(true);
/* 484*/                            HttpUrlConnector.setRequestMethodViaJreBugWorkaround(((HttpURLConnection) (obj = (HttpURLConnection)((Field) (obj)).get(httpURLConnection))), method);
                                }
/* 487*/                        catch(NoSuchFieldException _ex2) { }
/* 489*/                        catch(IllegalArgumentException illegalargumentexception)
                                {
/* 490*/                            throw new RuntimeException(illegalargumentexception);
                                }
/* 491*/                        catch(IllegalAccessException illegalaccessexception)
                                {
/* 492*/                            throw new RuntimeException(illegalaccessexception);
                                }
                            }
/* 496*/                    try
                            {
                                Field field;
/* 496*/                        do
                                {
/* 496*/                            if(class1 == null)
/* 498*/                                break label0;
/* 498*/                            try
                                    {
/* 498*/                                field = class1.getDeclaredField("method");
/* 504*/                                break;
                                    }
/* 500*/                            catch(NoSuchFieldException _ex)
                                    {
/* 501*/                                class1 = class1.getSuperclass();
                                    }
                                } while(true);
/* 505*/                        field.setAccessible(true);
/* 506*/                        field.set(httpURLConnection, method);
                            }
/* 509*/                    catch(Exception exception)
                            {
/* 510*/                        throw new RuntimeException(exception);
                            }
                        }
/* 513*/                return null;
                    }

                    final HttpURLConnection val$httpURLConnection;
                    final String val$method;

                    
                    {
/* 469*/                httpURLConnection = httpurlconnection;
/* 469*/                method = s;
/* 469*/                super();
                    }
        });
/* 523*/        return;
/* 516*/        JVM INSTR dup ;
/* 517*/        httpurlconnection;
/* 517*/        getCause();
/* 517*/        JVM INSTR dup ;
/* 518*/        httpurlconnection;
/* 518*/        JVM INSTR instanceof #32  <Class RuntimeException>;
/* 518*/        JVM INSTR ifeq 39;
                   goto _L1 _L2
_L1:
/* 519*/        break MISSING_BLOCK_LABEL_34;
_L2:
/* 519*/        break MISSING_BLOCK_LABEL_39;
/* 519*/        throw (RuntimeException)httpurlconnection;
/* 521*/        throw new RuntimeException(httpurlconnection);
            }

            public String getName()
            {
/* 529*/        return (new StringBuilder("HttpUrlConnection ")).append((String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("java.version"))).toString();
            }

            public volatile Object apply(Object obj)
            {
/*  96*/        return apply((ClientRequest)obj);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/client/internal/HttpUrlConnector.getName());
            private static final String ALLOW_RESTRICTED_HEADERS_SYSTEM_PROPERTY = "sun.net.http.allowRestrictedHeaders";
            private static final String restrictedHeaders[] = {
/* 101*/        "Access-Control-Request-Headers", "Access-Control-Request-Method", "Connection", "Content-Length", "Content-Transfer-Encoding", "Host", "Keep-Alive", "Origin", "Trailer", "Transfer-Encoding", 
/* 101*/        "Upgrade", "Via"
            };
            private static final Set restrictedHeaderSet;
            private final org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory connectionFactory;
            private final int chunkSize;
            private final boolean fixLengthStreaming;
            private final boolean setMethodWorkaround;
            private final boolean isRestrictedHeaderPropertySet = Boolean.valueOf((String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("sun.net.http.allowRestrictedHeaders", "false"))).booleanValue();
            private final LazyValue sslSocketFactory;

            static 
            {
/* 116*/        restrictedHeaderSet = new HashSet(restrictedHeaders.length);
                String as[];
/* 119*/        int i = (as = restrictedHeaders).length;
/* 119*/        for(int j = 0; j < i; j++)
                {
/* 119*/            String s = as[j];
/* 120*/            restrictedHeaderSet.add(s.toLowerCase());
                }

            }



}
