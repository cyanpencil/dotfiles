// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.internal.util.collection.Value;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            HttpUrlConnector

class val.client
    implements Value
{

            public SSLSocketFactory get()
            {
/* 153*/        return val$client.getSslContext().getSocketFactory();
            }

            public volatile Object get()
            {
/* 150*/        return get();
            }

            final Client val$client;
            final HttpUrlConnector this$0;

            nectionFactory()
            {
/* 150*/        this$0 = final_httpurlconnector;
/* 150*/        val$client = Client.this;
/* 150*/        super();
            }
}
