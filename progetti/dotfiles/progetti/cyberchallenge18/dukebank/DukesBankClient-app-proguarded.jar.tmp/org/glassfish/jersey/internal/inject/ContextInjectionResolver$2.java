// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextInjectionResolver.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ContextInjectionResolver

class val.handle
    implements Factory
{

            public Object provide()
            {
/* 136*/        return val$handle.getService();
            }

            public void dispose(Object obj)
            {
            }

            final ServiceHandle val$handle;
            final ContextInjectionResolver this$0;

            ()
            {
/* 133*/        this$0 = final_contextinjectionresolver;
/* 133*/        val$handle = ServiceHandle.this;
/* 133*/        super();
            }
}
