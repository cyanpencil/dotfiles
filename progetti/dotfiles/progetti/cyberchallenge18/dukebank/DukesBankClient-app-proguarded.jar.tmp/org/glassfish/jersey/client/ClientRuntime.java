// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRuntime.java

package org.glassfish.jersey.client;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MultivaluedMap;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.AsyncConnectorCallback;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.internal.Version;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.process.internal.*;

// Referenced classes of package org.glassfish.jersey.client:
//            AbortException, ClientConfig, ClientFilteringStages, ClientLifecycleListener, 
//            ClientRequest, ClientResponse, JerseyClient, RequestProcessingInitializationStage, 
//            ResponseCallback, ClientAsyncExecutorLiteral

class ClientRuntime
    implements JerseyClient.ShutdownHook
{

            public ClientRuntime(ClientConfig clientconfig, Connector connector1, final ServiceLocator locator)
            {
/* 105*/        Object obj = Stages.chain((Function)locator.createAndInitialize(org/glassfish/jersey/client/RequestProcessingInitializationStage));
/* 107*/        ChainableStage chainablestage = ClientFilteringStages.createRequestFilteringStage(locator);
/* 108*/        requestProcessingRoot = chainablestage == null ? ((org.glassfish.jersey.process.internal.Stage.Builder) (obj)).build() : ((org.glassfish.jersey.process.internal.Stage.Builder) (obj)).build(chainablestage);
/* 111*/        obj = ClientFilteringStages.createResponseFilteringStage(locator);
/* 112*/        responseProcessingRoot = obj == null ? ((Stage) (Stages.identity())) : ((Stage) (obj));
/* 115*/        config = clientconfig;
/* 116*/        connector = connector1;
/* 118*/        requestScope = (RequestScope)locator.getService(org/glassfish/jersey/process/internal/RequestScope, new Annotation[0]);
/* 120*/        asyncRequestExecutor = Values.lazy(new Value() {

                    public ExecutorService get()
                    {
/* 123*/                return (ExecutorService)locator.getService(java/util/concurrent/ExecutorService, new Annotation[] {
/* 123*/                    ClientAsyncExecutorLiteral.INSTANCE
                        });
                    }

                    public volatile Object get()
                    {
/* 120*/                return get();
                    }

                    final ServiceLocator val$locator;
                    final ClientRuntime this$0;

                    
                    {
/* 120*/                this$0 = ClientRuntime.this;
/* 120*/                locator = servicelocator;
/* 120*/                super();
                    }
        });
/* 127*/        this.locator = locator;
/* 129*/        lifecycleListeners = Providers.getAllProviders(locator, org/glassfish/jersey/client/ClientLifecycleListener);
/* 131*/        for(clientconfig = lifecycleListeners.iterator(); clientconfig.hasNext();)
                {
/* 131*/            connector1 = (ClientLifecycleListener)clientconfig.next();
/* 133*/            try
                    {
/* 133*/                connector1.onInit();
                    }
                    // Misplaced declaration of an exception variable
/* 134*/            catch(final ServiceLocator locator)
                    {
/* 135*/                LOG.log(Level.WARNING, LocalizationMessages.ERROR_LISTENER_INIT(connector1.getClass().getName()), locator);
                    }
                }

            }

            public void submit(final ClientRequest request, final ResponseCallback callback)
            {
/* 151*/        submit((ExecutorService)asyncRequestExecutor.get(), new Runnable() {

                    public void run()
                    {
                        ClientRequest clientrequest;
/* 158*/                try
                        {
/* 158*/                    clientrequest = (ClientRequest)Stages.process(request, requestProcessingRoot);
/* 159*/                    clientrequest = addUserAgent(clientrequest, connector.getName());
                        }
/* 160*/                catch(AbortException abortexception)
                        {
/* 161*/                    processResponse(abortexception.getAbortResponse(), callback);
/* 162*/                    return;
                        }
/* 165*/                try
                        {
/* 165*/                    final SettableFuture responseFuture = SettableFuture.create();
/* 166*/                    AsyncConnectorCallback asyncconnectorcallback = new AsyncConnectorCallback() {

                                public void response(ClientResponse clientresponse)
                                {
/* 170*/                            responseFuture.set(clientresponse);
                                }

                                public void failure(Throwable throwable1)
                                {
/* 175*/                            responseFuture.setException(throwable1);
                                }

                                final SettableFuture val$responseFuture;
                                final _cls2 this$1;

                            
                            {
/* 166*/                        this$1 = _cls2.this;
/* 166*/                        responseFuture = settablefuture;
/* 166*/                        super();
                            }
                    };
/* 178*/                    connector.apply(clientrequest, asyncconnectorcallback);
/* 180*/                    processResponse((ClientResponse)responseFuture.get(), callback);
/* 185*/                    return;
                        }
/* 181*/                catch(ExecutionException executionexception)
                        {
/* 182*/                    processFailure(executionexception.getCause(), callback);
/* 185*/                    return;
                        }
/* 183*/                catch(Throwable throwable)
                        {
/* 184*/                    processFailure(throwable, callback);
                        }
/* 186*/                return;
                    }

                    final ClientRequest val$request;
                    final ResponseCallback val$callback;
                    final ClientRuntime this$0;

                    
                    {
/* 151*/                this$0 = ClientRuntime.this;
/* 151*/                request = clientrequest;
/* 151*/                callback = responsecallback;
/* 151*/                super();
                    }
        });
            }

            private void processResponse(ClientResponse clientresponse, ResponseCallback responsecallback)
            {
/* 193*/        try
                {
/* 193*/            clientresponse = (ClientResponse)Stages.process(clientresponse, responseProcessingRoot);
                }
                // Misplaced declaration of an exception variable
/* 194*/        catch(ClientResponse clientresponse)
                {
/* 195*/            processFailure(clientresponse, responsecallback);
/* 196*/            return;
                }
/* 198*/        responsecallback.completed(clientresponse, requestScope);
            }

            private void processFailure(Throwable throwable, ResponseCallback responsecallback)
            {
/* 202*/        responsecallback.failed((throwable instanceof ProcessingException) ? (ProcessingException)throwable : new ProcessingException(throwable));
            }

            private Future submit(ExecutorService executorservice, final Runnable task)
            {
/* 207*/        return executorservice.submit(new Runnable() {

                    public void run()
                    {
/* 210*/                requestScope.runInScope(task);
                    }

                    final Runnable val$task;
                    final ClientRuntime this$0;

                    
                    {
/* 207*/                this$0 = ClientRuntime.this;
/* 207*/                task = runnable;
/* 207*/                super();
                    }
        });
            }

            private ClientRequest addUserAgent(ClientRequest clientrequest, String s)
            {
                MultivaluedMap multivaluedmap;
/* 216*/        if((multivaluedmap = clientrequest.getHeaders()).containsKey("User-Agent"))
                {
/* 220*/            if(clientrequest.getHeaderString("User-Agent") == null)
/* 221*/                multivaluedmap.remove("User-Agent");
                } else
/* 223*/        if(!clientrequest.ignoreUserAgent())
/* 224*/            if(s != null && !s.isEmpty())
/* 225*/                multivaluedmap.put("User-Agent", Arrays.asList(new Object[] {
/* 225*/                    String.format("Jersey/%s (%s)", new Object[] {
/* 226*/                        Version.getVersion(), s
                            })
                        }));
/* 228*/            else
/* 228*/                multivaluedmap.put("User-Agent", Arrays.asList(new Object[] {
/* 228*/                    String.format("Jersey/%s", new Object[] {
/* 229*/                        Version.getVersion()
                            })
                        }));
/* 233*/        return clientrequest;
            }

            public ClientResponse invoke(ClientRequest clientrequest)
            {
/* 255*/        clientrequest = connector.apply(addUserAgent((ClientRequest)Stages.process(clientrequest, requestProcessingRoot), connector.getName()));
                  goto _L1
/* 256*/        JVM INSTR dup ;
/* 257*/        clientrequest;
/* 257*/        getAbortResponse();
/* 257*/        clientrequest;
_L1:
/* 260*/        return (ClientResponse)Stages.process(clientrequest, responseProcessingRoot);
/* 261*/        JVM INSTR dup ;
/* 262*/        clientrequest;
/* 262*/        throw ;
/* 263*/        clientrequest;
/* 264*/        throw new ProcessingException(clientrequest.getMessage(), clientrequest);
            }

            public RequestScope getRequestScope()
            {
/* 274*/        return requestScope;
            }

            public ClientConfig getConfig()
            {
/* 283*/        return config;
            }

            protected void finalize()
                throws Throwable
            {
/* 297*/        close();
/* 299*/        super.finalize();
/* 300*/        return;
                Exception exception;
/* 299*/        exception;
/* 299*/        super.finalize();
/* 299*/        throw exception;
            }

            public void onShutdown()
            {
/* 305*/        close();
            }

            private void close()
            {
/* 309*/        if(!closed.compareAndSet(false, true))
/* 311*/            break MISSING_BLOCK_LABEL_135;
/* 311*/        for(Iterator iterator = lifecycleListeners.iterator(); iterator.hasNext();)
                {
/* 311*/            ClientLifecycleListener clientlifecyclelistener = (ClientLifecycleListener)iterator.next();
/* 313*/            try
                    {
/* 313*/                clientlifecyclelistener.onClose();
                    }
/* 314*/            catch(Throwable throwable)
                    {
/* 315*/                LOG.log(Level.WARNING, LocalizationMessages.ERROR_LISTENER_CLOSE(clientlifecyclelistener.getClass().getName()), throwable);
                    }
                }

/* 320*/        connector.close();
/* 322*/        Injections.shutdownLocator(locator);
/* 323*/        break MISSING_BLOCK_LABEL_135;
                Exception exception;
/* 322*/        exception;
/* 322*/        Injections.shutdownLocator(locator);
/* 322*/        throw exception;
/* 319*/        exception;
/* 320*/        connector.close();
/* 322*/        Injections.shutdownLocator(locator);
/* 323*/        break MISSING_BLOCK_LABEL_133;
/* 322*/        exception;
/* 322*/        Injections.shutdownLocator(locator);
/* 322*/        throw exception;
/* 322*/        throw exception;
            }

            public void preInitialize()
            {
/* 333*/        locator.getService(org/glassfish/jersey/message/MessageBodyWorkers, new Annotation[0]);
            }

            public Connector getConnector()
            {
/* 342*/        return connector;
            }

            ServiceLocator getServiceLocator()
            {
/* 351*/        return locator;
            }

            private static final Logger LOG = Logger.getLogger(org/glassfish/jersey/client/ClientRuntime.getName());
            private final Stage requestProcessingRoot;
            private final Stage responseProcessingRoot;
            private final Connector connector;
            private final ClientConfig config;
            private final RequestScope requestScope;
            private final LazyValue asyncRequestExecutor;
            private final ServiceLocator locator;
            private final Iterable lifecycleListeners;
            private final AtomicBoolean closed = new AtomicBoolean(false);







}
