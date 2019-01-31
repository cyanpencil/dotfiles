// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
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
/* 700*/        return JerseyInvocation.access$700(JerseyInvocation.this, val$runtime.invoke(JerseyInvocation.access$600(JerseyInvocation.this, JerseyInvocation.access$500(JerseyInvocation.this))), val$requestScope, val$responseType);
/* 701*/        JVM INSTR dup ;
                ProcessingException processingexception;
/* 702*/        processingexception;
/* 702*/        getCause();
/* 702*/        JVM INSTR instanceof #3   <Class WebApplicationException>;
/* 702*/        JVM INSTR ifeq 56;
                   goto _L1 _L2
_L1:
/* 703*/        break MISSING_BLOCK_LABEL_48;
_L2:
/* 703*/        break MISSING_BLOCK_LABEL_56;
/* 703*/        throw (WebApplicationException)processingexception.getCause();
/* 705*/        throw processingexception;
            }

            final ClientRuntime val$runtime;
            final RequestScope val$requestScope;
            final Class val$responseType;
            final JerseyInvocation this$0;

            ope()
            {
/* 696*/        this$0 = final_jerseyinvocation;
/* 696*/        val$runtime = clientruntime;
/* 696*/        val$requestScope = requestscope;
/* 696*/        val$responseType = Class.this;
/* 696*/        super();
            }
}
