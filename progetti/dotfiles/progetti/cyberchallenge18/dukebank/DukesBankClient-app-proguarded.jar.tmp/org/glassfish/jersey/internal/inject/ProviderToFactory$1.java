// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProviderToFactory.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ProviderToFactory

class val.provider
    implements Factory
{

            public Object provide()
            {
/*  64*/        return val$provider.getService();
            }

            public void dispose(Object obj)
            {
            }

            final ServiceHandle val$provider;
            final ProviderToFactory this$0;

            ()
            {
/*  60*/        this$0 = final_providertofactory;
/*  60*/        val$provider = ServiceHandle.this;
/*  60*/        super();
            }
}
