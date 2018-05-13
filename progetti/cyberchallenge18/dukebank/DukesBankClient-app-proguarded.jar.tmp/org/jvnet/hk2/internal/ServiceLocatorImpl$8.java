// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class this._cls0
    implements Computable
{

            public dValue compute(dCacheKey dcachekey)
            {
/*1144*/        return ServiceLocatorImpl.access$400(ServiceLocatorImpl.this, dcachekey);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*1141*/        return compute((dCacheKey)obj);
            }

            final ServiceLocatorImpl this$0;

            nErrorException()
            {
/*1141*/        this$0 = ServiceLocatorImpl.this;
/*1141*/        super();
            }
}
