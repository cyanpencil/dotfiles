// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.message.internal.OutboundMessageContext;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            HttpUrlConnector

class val.uc
    implements org.glassfish.jersey.message.internal.ext.StreamProvider
{

            public OutputStream getOutputStream(int i)
                throws IOException
            {
/* 384*/        HttpUrlConnector.access$100(HttpUrlConnector.this, val$request.getStringHeaders(), val$uc);
/* 385*/        return val$uc.getOutputStream();
            }

            final ClientRequest val$request;
            final HttpURLConnection val$uc;
            final HttpUrlConnector this$0;

            text.StreamProvider()
            {
/* 380*/        this$0 = final_httpurlconnector;
/* 380*/        val$request = clientrequest;
/* 380*/        val$uc = HttpURLConnection.this;
/* 380*/        super();
            }
}
