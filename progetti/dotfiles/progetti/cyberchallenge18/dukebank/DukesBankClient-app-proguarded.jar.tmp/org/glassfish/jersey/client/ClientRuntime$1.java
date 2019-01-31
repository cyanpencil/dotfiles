// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRuntime.java

package org.glassfish.jersey.client;

import java.lang.annotation.Annotation;
import java.util.concurrent.ExecutorService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.util.collection.Value;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientAsyncExecutorLiteral, ClientRuntime

class val.locator
    implements Value
{

            public ExecutorService get()
            {
/* 123*/        return (ExecutorService)val$locator.getService(java/util/concurrent/ExecutorService, new Annotation[] {
/* 123*/            ClientAsyncExecutorLiteral.INSTANCE
                });
            }

            public volatile Object get()
            {
/* 120*/        return get();
            }

            final ServiceLocator val$locator;
            final ClientRuntime this$0;

            n.Value()
            {
/* 120*/        this$0 = final_clientruntime;
/* 120*/        val$locator = ServiceLocator.this;
/* 120*/        super();
            }
}
