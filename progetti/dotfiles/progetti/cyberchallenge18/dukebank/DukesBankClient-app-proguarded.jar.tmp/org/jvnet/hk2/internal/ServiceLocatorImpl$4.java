// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            PerLocatorUtilities, ServiceLocatorImpl, SystemInjecteeImpl

class this._cls0
    implements Computable
{

            public InjectionResolver compute(SystemInjecteeImpl systeminjecteeimpl)
            {
/* 201*/        return ServiceLocatorImpl.access$200(ServiceLocatorImpl.this).getInjectionResolver(ServiceLocatorImpl.access$100(ServiceLocatorImpl.this), systeminjecteeimpl);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/* 197*/        return compute((SystemInjecteeImpl)obj);
            }

            final ServiceLocatorImpl this$0;

            nErrorException()
            {
/* 197*/        this$0 = ServiceLocatorImpl.this;
/* 197*/        super();
            }
}
