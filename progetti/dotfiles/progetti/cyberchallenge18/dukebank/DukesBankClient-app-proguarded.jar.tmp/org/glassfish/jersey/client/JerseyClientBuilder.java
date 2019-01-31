// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClientBuilder.java

package org.glassfish.jersey.client;

import java.security.KeyStore;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig, JerseyClient

public class JerseyClientBuilder extends ClientBuilder
{

            public static JerseyClient createClient()
            {
/*  75*/        return (new JerseyClientBuilder()).build();
            }

            public static JerseyClient createClient(Configuration configuration)
            {
/*  87*/        return (new JerseyClientBuilder()).withConfig(configuration).build();
            }

            public JerseyClientBuilder()
            {
            }

            public JerseyClientBuilder sslContext(SSLContext sslcontext)
            {
/*  99*/        if(sslcontext == null)
                {
/* 100*/            throw new NullPointerException(LocalizationMessages.NULL_SSL_CONTEXT());
                } else
                {
/* 102*/            sslContext = sslcontext;
/* 103*/            sslConfigurator = null;
/* 104*/            return this;
                }
            }

            public JerseyClientBuilder keyStore(KeyStore keystore, char ac[])
            {
/* 109*/        if(keystore == null)
/* 110*/            throw new NullPointerException(LocalizationMessages.NULL_KEYSTORE());
/* 112*/        if(ac == null)
/* 113*/            throw new NullPointerException(LocalizationMessages.NULL_KEYSTORE_PASWORD());
/* 115*/        if(sslConfigurator == null)
/* 116*/            sslConfigurator = SslConfigurator.newInstance();
/* 118*/        sslConfigurator.keyStore(keystore);
/* 119*/        sslConfigurator.keyPassword(ac);
/* 120*/        sslContext = null;
/* 121*/        return this;
            }

            public JerseyClientBuilder trustStore(KeyStore keystore)
            {
/* 126*/        if(keystore == null)
/* 127*/            throw new NullPointerException(LocalizationMessages.NULL_TRUSTSTORE());
/* 129*/        if(sslConfigurator == null)
/* 130*/            sslConfigurator = SslConfigurator.newInstance();
/* 132*/        sslConfigurator.trustStore(keystore);
/* 133*/        sslContext = null;
/* 134*/        return this;
            }

            public JerseyClientBuilder hostnameVerifier(HostnameVerifier hostnameverifier)
            {
/* 139*/        hostnameVerifier = hostnameverifier;
/* 140*/        return this;
            }

            public JerseyClient build()
            {
/* 145*/        if(sslContext != null)
/* 146*/            return new JerseyClient(config, sslContext, hostnameVerifier);
/* 147*/        if(sslConfigurator != null)
                {
/* 148*/            final SslConfigurator sslConfiguratorCopy = sslConfigurator.copy();
/* 149*/            return new JerseyClient(config, Values.lazy(new UnsafeValue() {

                        public SSLContext get()
                        {
/* 154*/                    return sslConfiguratorCopy.createSSLContext();
                        }

                        public volatile Object get()
                            throws Throwable
                        {
/* 151*/                    return get();
                        }

                        final SslConfigurator val$sslConfiguratorCopy;
                        final JerseyClientBuilder this$0;

                    
                    {
/* 151*/                this$0 = JerseyClientBuilder.this;
/* 151*/                sslConfiguratorCopy = sslconfigurator;
/* 151*/                super();
                    }
            }), hostnameVerifier);
                } else
                {
/* 159*/            return new JerseyClient(config, null, hostnameVerifier);
                }
            }

            public ClientConfig getConfiguration()
            {
/* 165*/        return config;
            }

            public JerseyClientBuilder property(String s, Object obj)
            {
/* 170*/        config.property(s, obj);
/* 171*/        return this;
            }

            public JerseyClientBuilder register(Class class1)
            {
/* 176*/        config.register(class1);
/* 177*/        return this;
            }

            public JerseyClientBuilder register(Class class1, int i)
            {
/* 182*/        config.register(class1, i);
/* 183*/        return this;
            }

            public transient JerseyClientBuilder register(Class class1, Class aclass[])
            {
/* 188*/        config.register(class1, aclass);
/* 189*/        return this;
            }

            public JerseyClientBuilder register(Class class1, Map map)
            {
/* 194*/        config.register(class1, map);
/* 195*/        return this;
            }

            public JerseyClientBuilder register(Object obj)
            {
/* 200*/        config.register(obj);
/* 201*/        return this;
            }

            public JerseyClientBuilder register(Object obj, int i)
            {
/* 206*/        config.register(obj, i);
/* 207*/        return this;
            }

            public transient JerseyClientBuilder register(Object obj, Class aclass[])
            {
/* 212*/        config.register(obj, aclass);
/* 213*/        return this;
            }

            public JerseyClientBuilder register(Object obj, Map map)
            {
/* 218*/        config.register(obj, map);
/* 219*/        return this;
            }

            public JerseyClientBuilder withConfig(Configuration configuration)
            {
/* 224*/        config.loadFrom(configuration);
/* 225*/        return this;
            }

            public volatile Client build()
            {
/*  61*/        return build();
            }

            public volatile ClientBuilder hostnameVerifier(HostnameVerifier hostnameverifier)
            {
/*  61*/        return hostnameVerifier(hostnameverifier);
            }

            public volatile ClientBuilder trustStore(KeyStore keystore)
            {
/*  61*/        return trustStore(keystore);
            }

            public volatile ClientBuilder keyStore(KeyStore keystore, char ac[])
            {
/*  61*/        return keyStore(keystore, ac);
            }

            public volatile ClientBuilder sslContext(SSLContext sslcontext)
            {
/*  61*/        return sslContext(sslcontext);
            }

            public volatile ClientBuilder withConfig(Configuration configuration)
            {
/*  61*/        return withConfig(configuration);
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  61*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  61*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  61*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  61*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  61*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  61*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  61*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  61*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  61*/        return property(s, obj);
            }

            public volatile Configuration getConfiguration()
            {
/*  61*/        return getConfiguration();
            }

            private final ClientConfig config = new ClientConfig();
            private HostnameVerifier hostnameVerifier;
            private SslConfigurator sslConfigurator;
            private SSLContext sslContext;
}
