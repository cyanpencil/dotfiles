// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class this._cls0
    implements Computable
{

            public Context compute(Class class1)
            {
/* 182*/        return ServiceLocatorImpl.access$000(ServiceLocatorImpl.this, class1);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/* 178*/        return compute((Class)obj);
            }

            final ServiceLocatorImpl this$0;

            nErrorException()
            {
/* 178*/        this$0 = ServiceLocatorImpl.this;
/* 178*/        super();
            }
}
