// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, InboundJaxrsResponse, JerseyInvocation, ResponseCallback

class rent.SettableFuture
    implements ResponseCallback
{

            public void completed(ClientResponse clientresponse, RequestScope requestscope)
            {
/* 740*/        if(!val$responseFuture.isCancelled())
                {
/* 741*/            val$responseFuture.set(new InboundJaxrsResponse(clientresponse, requestscope));
/* 741*/            return;
                } else
                {
/* 743*/            clientresponse.close();
/* 745*/            return;
                }
            }

            public void failed(ProcessingException processingexception)
            {
/* 749*/        if(!val$responseFuture.isCancelled())
/* 750*/            val$responseFuture.setException(processingexception);
            }

            final SettableFuture val$responseFuture;
            final JerseyInvocation this$0;

            rent.SettableFuture()
            {
/* 736*/        this$0 = final_jerseyinvocation;
/* 736*/        val$responseFuture = SettableFuture.this;
/* 736*/        super();
            }
}
