// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientBuilder.java

package javax.ws.rs.client;

import java.security.KeyStore;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;

// Referenced classes of package javax.ws.rs.client:
//            FactoryFinder, Client

public abstract class ClientBuilder
    implements Configurable
{

            protected ClientBuilder()
            {
            }

            public static ClientBuilder newBuilder()
            {
                Object obj;
/*  86*/        if(!((obj = FactoryFinder.find("javax.ws.rs.client.ClientBuilder", "org.glassfish.jersey.client.JerseyClientBuilder")) instanceof ClientBuilder))
                {
/*  90*/            /*<invalid signature>*/java.lang.Object local = javax/ws/rs/client/ClientBuilder;
/*  91*/            String s = (new StringBuilder()).append(local.getName().replace('.', '/')).append(".class").toString();
                    Object obj1;
/*  92*/            if((obj1 = local.getClassLoader()) == null)
/*  94*/                obj1 = ClassLoader.getSystemClassLoader();
/*  96*/            obj1 = ((ClassLoader) (obj1)).getResource(s);
/*  97*/            throw new LinkageError((new StringBuilder("ClassCastException: attempting to cast")).append(obj.getClass().getClassLoader().getResource(s)).append(" to ").append(obj1).toString());
                }
/* 101*/        return (ClientBuilder)obj;
                Exception exception;
/* 102*/        exception;
/* 103*/        throw new RuntimeException(exception);
            }

            public static Client newClient()
            {
/* 114*/        return newBuilder().build();
            }

            public static Client newClient(Configuration configuration)
            {
/* 126*/        return newBuilder().withConfig(configuration).build();
            }

            public abstract ClientBuilder withConfig(Configuration configuration);

            public abstract ClientBuilder sslContext(SSLContext sslcontext);

            public abstract ClientBuilder keyStore(KeyStore keystore, char ac[]);

            public ClientBuilder keyStore(KeyStore keystore, String s)
            {
/* 205*/        return keyStore(keystore, s.toCharArray());
            }

            public abstract ClientBuilder trustStore(KeyStore keystore);

            public abstract ClientBuilder hostnameVerifier(HostnameVerifier hostnameverifier);

            public abstract Client build();

            public static final String JAXRS_DEFAULT_CLIENT_BUILDER_PROPERTY = "javax.ws.rs.client.ClientBuilder";
            private static final String JAXRS_DEFAULT_CLIENT_BUILDER = "org.glassfish.jersey.client.JerseyClientBuilder";
}
