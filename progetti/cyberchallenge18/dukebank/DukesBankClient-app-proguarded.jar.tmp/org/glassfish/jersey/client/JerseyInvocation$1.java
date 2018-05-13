// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRuntime, InboundJaxrsResponse, JerseyInvocation

class ope
    implements Producer
{

            public Response call()
                throws ProcessingException
            {
/* 684*/        return new InboundJaxrsResponse(val$runtime.invoke(JerseyInvocation.access$600(JerseyInvocation.this, JerseyInvocation.access$500(JerseyInvocation.this))), val$requestScope);
            }

            public volatile Object call()
            {
/* 681*/        return call();
            }

            final ClientRuntime val$runtime;
            final RequestScope val$requestScope;
            final JerseyInvocation this$0;

            ope()
            {
/* 681*/        this$0 = final_jerseyinvocation;
/* 681*/        val$runtime = clientruntime;
/* 681*/        val$requestScope = RequestScope.this;
/* 681*/        super();
            }
}
