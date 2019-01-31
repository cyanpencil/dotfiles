// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnectorProvider.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Configuration;
import org.glassfish.jersey.client.internal.HttpUrlConnector;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.client.spi.ConnectorProvider;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientProperties

public class HttpUrlConnectorProvider
    implements ConnectorProvider
{
    static class DefaultConnectionFactory
        implements ConnectionFactory
    {

                public HttpURLConnection getConnection(URL url)
                    throws IOException
                {
/* 299*/            return (HttpURLConnection)url.openConnection();
                }

                private DefaultConnectionFactory()
                {
                }

    }

    public static interface ConnectionFactory
    {

        public abstract HttpURLConnection getConnection(URL url)
            throws IOException;
    }


            public HttpUrlConnectorProvider()
            {
/* 147*/        connectionFactory = DEFAULT_CONNECTION_FACTORY;
/* 148*/        chunkSize = 4096;
/* 149*/        useFixedLengthStreaming = false;
/* 150*/        useSetMethodWorkaround = false;
            }

            public HttpUrlConnectorProvider connectionFactory(ConnectionFactory connectionfactory)
            {
/* 161*/        if(connectionfactory == null)
                {
/* 162*/            throw new NullPointerException(LocalizationMessages.NULL_INPUT_PARAMETER("connectionFactory"));
                } else
                {
/* 165*/            connectionFactory = connectionfactory;
/* 166*/            return this;
                }
            }

            public HttpUrlConnectorProvider chunkSize(int i)
            {
/* 185*/        if(i < 0)
                {
/* 186*/            throw new IllegalArgumentException(LocalizationMessages.NEGATIVE_INPUT_PARAMETER("chunkSize"));
                } else
                {
/* 188*/            chunkSize = i;
/* 189*/            return this;
                }
            }

            public HttpUrlConnectorProvider useFixedLengthStreaming()
            {
/* 204*/        useFixedLengthStreaming = true;
/* 205*/        return this;
            }

            public HttpUrlConnectorProvider useSetMethodWorkaround()
            {
/* 220*/        useSetMethodWorkaround = true;
/* 221*/        return this;
            }

            public Connector getConnector(Client client, Configuration configuration)
            {
                int i;
/* 226*/        if((i = ((Integer)ClientProperties.getValue(configuration = configuration.getProperties(), "jersey.config.client.chunkedEncodingSize", Integer.valueOf(chunkSize), java/lang/Integer)).intValue()) < 0)
                {
/* 231*/            LOGGER.warning(LocalizationMessages.NEGATIVE_CHUNK_SIZE(Integer.valueOf(i), Integer.valueOf(chunkSize)));
/* 232*/            i = chunkSize;
                }
/* 235*/        boolean flag = ((Boolean)ClientProperties.getValue(configuration, "jersey.config.client.httpUrlConnector.useFixedLengthStreaming", Boolean.valueOf(useFixedLengthStreaming), java/lang/Boolean)).booleanValue();
/* 237*/        configuration = ((Boolean)ClientProperties.getValue(configuration, "jersey.config.client.httpUrlConnection.setMethodWorkaround", Boolean.valueOf(useSetMethodWorkaround), java/lang/Boolean)).booleanValue();
/* 240*/        return createHttpUrlConnector(client, connectionFactory, i, flag, configuration);
            }

            protected Connector createHttpUrlConnector(Client client, ConnectionFactory connectionfactory, int i, boolean flag, boolean flag1)
            {
/* 261*/        return new HttpUrlConnector(client, connectionfactory, i, flag, flag1);
            }

            public boolean equals(Object obj)
            {
/* 305*/        if(this == obj)
/* 306*/            return true;
/* 308*/        if(obj == null || getClass() != obj.getClass())
/* 309*/            return false;
/* 312*/        obj = (HttpUrlConnectorProvider)obj;
/* 314*/        if(chunkSize != ((HttpUrlConnectorProvider) (obj)).chunkSize)
/* 315*/            return false;
/* 317*/        if(useFixedLengthStreaming != ((HttpUrlConnectorProvider) (obj)).useFixedLengthStreaming)
/* 318*/            return false;
/* 321*/        else
/* 321*/            return connectionFactory.equals(((HttpUrlConnectorProvider) (obj)).connectionFactory);
            }

            public int hashCode()
            {
/* 326*/        int i = connectionFactory.hashCode();
/* 327*/        i = i * 31 + chunkSize;
/* 328*/        return i = i * 31 + (useFixedLengthStreaming ? 1 : 0);
            }

            public static final String USE_FIXED_LENGTH_STREAMING = "jersey.config.client.httpUrlConnector.useFixedLengthStreaming";
            public static final String SET_METHOD_WORKAROUND = "jersey.config.client.httpUrlConnection.setMethodWorkaround";
            private static final ConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultConnectionFactory();
            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/client/HttpUrlConnectorProvider.getName());
            private ConnectionFactory connectionFactory;
            private int chunkSize;
            private boolean useFixedLengthStreaming;
            private boolean useSetMethodWorkaround;

}
