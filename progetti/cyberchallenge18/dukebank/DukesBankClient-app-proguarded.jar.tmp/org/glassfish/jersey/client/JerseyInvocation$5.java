// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, JerseyInvocation, ResponseCallback

class val.responseType
    implements ResponseCallback
{

            public void completed(ClientResponse clientresponse, RequestScope requestscope)
            {
/* 768*/        if(val$responseFuture.isCancelled())
                {
/* 769*/            clientresponse.close();
/* 770*/            return;
                }
/* 773*/        try
                {
/* 773*/            val$responseFuture.set(JerseyInvocation.access$700(JerseyInvocation.this, clientresponse, requestscope, val$responseType));
/* 776*/            return;
                }
                // Misplaced declaration of an exception variable
/* 774*/        catch(ClientResponse clientresponse)
                {
/* 775*/            failed(clientresponse);
                }
            }

            public void failed(ProcessingException processingexception)
            {
/* 781*/        if(val$responseFuture.isCancelled())
/* 782*/            return;
/* 784*/        if(processingexception.getCause() instanceof WebApplicationException)
                {
/* 785*/            val$responseFuture.setException(processingexception.getCause());
/* 785*/            return;
                } else
                {
/* 787*/            val$responseFuture.setException(processingexception);
/* 789*/            return;
                }
            }

            final SettableFuture val$responseFuture;
            final Class val$responseType;
            final JerseyInvocation this$0;

            rent.SettableFuture()
            {
/* 764*/        this$0 = final_jerseyinvocation;
/* 764*/        val$responseFuture = settablefuture;
/* 764*/        val$responseType = Class.this;
/* 764*/        super();
            }
}
