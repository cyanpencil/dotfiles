// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import java.io.IOException;
import javax.ws.rs.ProcessingException;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.spi.AsyncConnectorCallback;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            HttpUrlConnector

class val.request
    implements Runnable
{

            public void run()
            {
/* 297*/        try
                {
/* 297*/            val$callback.response(HttpUrlConnector.access$000(HttpUrlConnector.this, val$request));
/* 302*/            return;
                }
/* 298*/        catch(IOException ioexception)
                {
/* 299*/            val$callback.failure(new ProcessingException(ioexception));
/* 302*/            return;
                }
/* 300*/        catch(Throwable throwable)
                {
/* 301*/            val$callback.failure(throwable);
                }
            }

            final AsyncConnectorCallback val$callback;
            final ClientRequest val$request;
            final HttpUrlConnector this$0;

            ()
            {
/* 293*/        this$0 = final_httpurlconnector;
/* 293*/        val$callback = asyncconnectorcallback;
/* 293*/        val$request = ClientRequest.this;
/* 293*/        super();
            }
}
