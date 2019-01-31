// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRuntime, JerseyInvocation

class val.responseType
    implements Producer
{

            public Object call()
                throws ProcessingException
            {
/* 722*/        return JerseyInvocation.access$800(JerseyInvocation.this, val$runtime.invoke(JerseyInvocation.access$600(JerseyInvocation.this, JerseyInvocation.access$500(JerseyInvocation.this))), val$requestScope, val$responseType);
/* 723*/        JVM INSTR dup ;
                ProcessingException processingexception;
/* 724*/        processingexception;
/* 724*/        getCause();
/* 724*/        JVM INSTR instanceof #3   <Class WebApplicationException>;
/* 724*/        JVM INSTR ifeq 56;
                   goto _L1 _L2
_L1:
/* 725*/        break MISSING_BLOCK_LABEL_48;
_L2:
/* 725*/        break MISSING_BLOCK_LABEL_56;
/* 725*/        throw (WebApplicationException)processingexception.getCause();
/* 727*/        throw processingexception;
            }

            final ClientRuntime val$runtime;
            final RequestScope val$requestScope;
            final GenericType val$responseType;
            final JerseyInvocation this$0;

            ope()
            {
/* 718*/        this$0 = final_jerseyinvocation;
/* 718*/        val$runtime = clientruntime;
/* 718*/        val$requestScope = requestscope;
/* 718*/        val$responseType = GenericType.this;
/* 718*/        super();
            }
}
