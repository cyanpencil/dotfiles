// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, JerseyInvocation, ResponseCallback

class val.responseType
    implements ResponseCallback
{

            public void completed(ClientResponse clientresponse, RequestScope requestscope)
            {
/* 830*/        if(val$responseFuture.isCancelled())
                {
/* 831*/            clientresponse.close();
/* 832*/            return;
                }
/* 836*/        try
                {
/* 836*/            val$responseFuture.set(JerseyInvocation.access$800(JerseyInvocation.this, clientresponse, requestscope, val$responseType));
/* 839*/            return;
                }
                // Misplaced declaration of an exception variable
/* 837*/        catch(ClientResponse clientresponse)
                {
/* 838*/            failed(clientresponse);
                }
            }

            public void failed(ProcessingException processingexception)
            {
/* 844*/        if(val$responseFuture.isCancelled())
/* 845*/            return;
/* 847*/        if(processingexception.getCause() instanceof WebApplicationException)
                {
/* 848*/            val$responseFuture.setException(processingexception.getCause());
/* 848*/            return;
                } else
                {
/* 850*/            val$responseFuture.setException(processingexception);
/* 852*/            return;
                }
            }

            final SettableFuture val$responseFuture;
            final GenericType val$responseType;
            final JerseyInvocation this$0;

            rent.SettableFuture()
            {
/* 826*/        this$0 = final_jerseyinvocation;
/* 826*/        val$responseFuture = settablefuture;
/* 826*/        val$responseType = GenericType.this;
/* 826*/        super();
            }
}
