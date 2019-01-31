// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClient.java

package org.glassfish.jersey.client;

import java.lang.ref.*;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.DefaultSslContextProvider;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig, Initializable, JerseyInvocation, JerseyWebTarget

public class JerseyClient
    implements Client, Initializable
{
    static interface ShutdownHook
    {

        public abstract void onShutdown();
    }


            protected JerseyClient()
            {
/* 109*/        this(null, ((UnsafeValue) (null)), null, null);
            }

            protected JerseyClient(Configuration configuration, SSLContext sslcontext, HostnameVerifier hostnameverifier)
            {
/* 123*/        this(configuration, sslcontext, hostnameverifier, null);
            }

            protected JerseyClient(Configuration configuration, SSLContext sslcontext, HostnameVerifier hostnameverifier, DefaultSslContextProvider defaultsslcontextprovider)
            {
/* 138*/        this(configuration, sslcontext != null ? Values.unsafe(sslcontext) : null, hostnameverifier, defaultsslcontextprovider);
            }

            protected JerseyClient(Configuration configuration, UnsafeValue unsafevalue, HostnameVerifier hostnameverifier)
            {
/* 152*/        this(configuration, unsafevalue, hostnameverifier, null);
            }

            protected JerseyClient(Configuration configuration, UnsafeValue unsafevalue, HostnameVerifier hostnameverifier, DefaultSslContextProvider defaultsslcontextprovider)
            {
/*  86*/        closedFlag = new AtomicBoolean(false);
/*  91*/        shutdownHooks = new LinkedBlockingDeque();
/*  93*/        shReferenceQueue = new ReferenceQueue();
/* 168*/        config = configuration != null ? new ClientConfig(this, configuration) : new ClientConfig(this);
/* 170*/        if(unsafevalue == null)
                {
/* 171*/            isDefaultSslContext = true;
/* 173*/            if(defaultsslcontextprovider != null)
                    {
/* 174*/                sslContext = createLazySslContext(defaultsslcontextprovider);
                    } else
                    {
/* 178*/                if((configuration = ServiceFinder.find(org/glassfish/jersey/client/spi/DefaultSslContextProvider).iterator()).hasNext())
/* 182*/                    configuration = (DefaultSslContextProvider)configuration.next();
/* 184*/                else
/* 184*/                    configuration = DEFAULT_SSL_CONTEXT_PROVIDER;
/* 187*/                sslContext = createLazySslContext(configuration);
                    }
                } else
                {
/* 190*/            isDefaultSslContext = false;
/* 191*/            sslContext = Values.lazy(unsafevalue);
                }
/* 194*/        hostnameVerifier = hostnameverifier;
            }

            public void close()
            {
/* 199*/        if(closedFlag.compareAndSet(false, true))
/* 200*/            release();
            }

            private void release()
            {
/* 206*/        do
                {
                    Reference reference;
/* 206*/            if((reference = (Reference)shutdownHooks.pollFirst()) == null)
/* 207*/                break;
                    ShutdownHook shutdownhook;
/* 207*/            if((shutdownhook = (ShutdownHook)reference.get()) != null)
/* 210*/                try
                        {
/* 210*/                    shutdownhook.onShutdown();
                        }
/* 211*/                catch(Throwable throwable)
                        {
/* 212*/                    LOG.log(Level.WARNING, LocalizationMessages.ERROR_SHUTDOWNHOOK_CLOSE(reference.getClass().getName()), throwable);
                        }
                } while(true);
            }

            private UnsafeValue createLazySslContext(final DefaultSslContextProvider provider)
            {
/* 219*/        return Values.lazy(new UnsafeValue() {

                    public SSLContext get()
                    {
/* 222*/                return provider.getDefaultSslContext();
                    }

                    public volatile Object get()
                        throws Throwable
                    {
/* 219*/                return get();
                    }

                    final DefaultSslContextProvider val$provider;
                    final JerseyClient this$0;

                    
                    {
/* 219*/                this$0 = JerseyClient.this;
/* 219*/                provider = defaultsslcontextprovider;
/* 219*/                super();
                    }
        });
            }

            void registerShutdownHook(ShutdownHook shutdownhook)
            {
/* 233*/        checkNotClosed();
/* 234*/        shutdownHooks.push(new WeakReference(shutdownhook, shReferenceQueue));
/* 235*/        cleanUpShutdownHooks();
            }

            private void cleanUpShutdownHooks()
            {
/* 245*/        do
                {
                    Object obj;
/* 245*/            if((obj = shReferenceQueue.poll()) == null)
/* 247*/                break;
/* 247*/            shutdownHooks.remove(obj);
/* 249*/            if((obj = (ShutdownHook)((Reference) (obj)).get()) != null)
/* 251*/                ((ShutdownHook) (obj)).onShutdown();
                } while(true);
            }

            public boolean isClosed()
            {
/* 264*/        return closedFlag.get();
            }

            void checkNotClosed()
            {
/* 273*/        Preconditions.checkState(!closedFlag.get(), LocalizationMessages.CLIENT_INSTANCE_CLOSED());
            }

            public boolean isDefaultSslContext()
            {
/* 283*/        return isDefaultSslContext;
            }

            public JerseyWebTarget target(String s)
            {
/* 288*/        checkNotClosed();
/* 289*/        Preconditions.checkNotNull(s, LocalizationMessages.CLIENT_URI_TEMPLATE_NULL());
/* 290*/        return new JerseyWebTarget(s, this);
            }

            public JerseyWebTarget target(URI uri)
            {
/* 295*/        checkNotClosed();
/* 296*/        Preconditions.checkNotNull(uri, LocalizationMessages.CLIENT_URI_NULL());
/* 297*/        return new JerseyWebTarget(uri, this);
            }

            public JerseyWebTarget target(UriBuilder uribuilder)
            {
/* 302*/        checkNotClosed();
/* 303*/        Preconditions.checkNotNull(uribuilder, LocalizationMessages.CLIENT_URI_BUILDER_NULL());
/* 304*/        return new JerseyWebTarget(uribuilder, this);
            }

            public JerseyWebTarget target(Link link)
            {
/* 309*/        checkNotClosed();
/* 310*/        Preconditions.checkNotNull(link, LocalizationMessages.CLIENT_TARGET_LINK_NULL());
/* 311*/        return new JerseyWebTarget(link, this);
            }

            public JerseyInvocation.Builder invocation(Link link)
            {
/* 316*/        checkNotClosed();
/* 317*/        Preconditions.checkNotNull(link, LocalizationMessages.CLIENT_INVOCATION_LINK_NULL());
/* 318*/        JerseyWebTarget jerseywebtarget = new JerseyWebTarget(link, this);
/* 319*/        if((link = link.getType()) != null)
/* 320*/            return jerseywebtarget.request(new String[] {
/* 320*/                link
                    });
/* 320*/        else
/* 320*/            return jerseywebtarget.request();
            }

            public JerseyClient register(Class class1)
            {
/* 325*/        checkNotClosed();
/* 326*/        config.register(class1);
/* 327*/        return this;
            }

            public JerseyClient register(Object obj)
            {
/* 332*/        checkNotClosed();
/* 333*/        config.register(obj);
/* 334*/        return this;
            }

            public JerseyClient register(Class class1, int i)
            {
/* 339*/        checkNotClosed();
/* 340*/        config.register(class1, i);
/* 341*/        return this;
            }

            public transient JerseyClient register(Class class1, Class aclass[])
            {
/* 346*/        checkNotClosed();
/* 347*/        config.register(class1, aclass);
/* 348*/        return this;
            }

            public JerseyClient register(Class class1, Map map)
            {
/* 353*/        checkNotClosed();
/* 354*/        config.register(class1, map);
/* 355*/        return this;
            }

            public JerseyClient register(Object obj, int i)
            {
/* 360*/        checkNotClosed();
/* 361*/        config.register(obj, i);
/* 362*/        return this;
            }

            public transient JerseyClient register(Object obj, Class aclass[])
            {
/* 367*/        checkNotClosed();
/* 368*/        config.register(obj, aclass);
/* 369*/        return this;
            }

            public JerseyClient register(Object obj, Map map)
            {
/* 374*/        checkNotClosed();
/* 375*/        config.register(obj, map);
/* 376*/        return this;
            }

            public JerseyClient property(String s, Object obj)
            {
/* 381*/        checkNotClosed();
/* 382*/        config.property(s, obj);
/* 383*/        return this;
            }

            public ClientConfig getConfiguration()
            {
/* 388*/        checkNotClosed();
/* 389*/        return config.getConfiguration();
            }

            public SSLContext getSslContext()
            {
/* 394*/        return (SSLContext)sslContext.get();
            }

            public HostnameVerifier getHostnameVerifier()
            {
/* 399*/        return hostnameVerifier;
            }

            public JerseyClient preInitialize()
            {
/* 404*/        config.preInitialize();
/* 405*/        return this;
            }

            public volatile javax.ws.rs.client.Invocation.Builder invocation(Link link)
            {
/*  76*/        return invocation(link);
            }

            public volatile WebTarget target(Link link)
            {
/*  76*/        return target(link);
            }

            public volatile WebTarget target(UriBuilder uribuilder)
            {
/*  76*/        return target(uribuilder);
            }

            public volatile WebTarget target(URI uri)
            {
/*  76*/        return target(uri);
            }

            public volatile WebTarget target(String s)
            {
/*  76*/        return target(s);
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  76*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  76*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  76*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  76*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  76*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  76*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  76*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  76*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  76*/        return property(s, obj);
            }

            public volatile Configuration getConfiguration()
            {
/*  76*/        return getConfiguration();
            }

            public volatile Initializable preInitialize()
            {
/*  76*/        return preInitialize();
            }

            private static final Logger LOG = Logger.getLogger(org/glassfish/jersey/client/JerseyClient.getName());
            private static final DefaultSslContextProvider DEFAULT_SSL_CONTEXT_PROVIDER = new DefaultSslContextProvider() {

                public final SSLContext getDefaultSslContext()
                {
/*  82*/            return SslConfigurator.getDefaultContext();
                }

    };
            private final AtomicBoolean closedFlag;
            private final boolean isDefaultSslContext;
            private final ClientConfig config;
            private final HostnameVerifier hostnameVerifier;
            private final UnsafeValue sslContext;
            private final LinkedBlockingDeque shutdownHooks;
            private final ReferenceQueue shReferenceQueue;

}
