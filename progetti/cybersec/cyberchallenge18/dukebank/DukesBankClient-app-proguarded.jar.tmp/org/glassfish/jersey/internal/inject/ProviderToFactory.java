// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProviderToFactory.java

package org.glassfish.jersey.internal.inject;

import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceHandle;

public final class ProviderToFactory
    implements Function
{

            public ProviderToFactory()
            {
            }

            public final Factory apply(final ServiceHandle provider)
            {
/*  60*/        return new Factory() {

                    public Object provide()
                    {
/*  64*/                return provider.getService();
                    }

                    public void dispose(Object obj)
                    {
                    }

                    final ServiceHandle val$provider;
                    final ProviderToFactory this$0;

                    
                    {
/*  60*/                this$0 = ProviderToFactory.this;
/*  60*/                provider = servicehandle;
/*  60*/                super();
                    }
        };
            }

            public final volatile Object apply(Object obj)
            {
/*  56*/        return apply((ServiceHandle)obj);
            }
}
