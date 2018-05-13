// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperImpl.java

package org.glassfish.hk2.utilities.reflection.internal;

import org.glassfish.hk2.utilities.cache.*;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperImpl

class this._cls0
    implements Computable
{

            public HybridCacheEntry compute(fecycleKey fecyclekey)
            {
/*  64*/        return ClassReflectionHelperImpl.access$300(ClassReflectionHelperImpl.this).createCacheEntry(fecyclekey, ClassReflectionHelperImpl.access$200(ClassReflectionHelperImpl.this, fecycleKey.access._mth000(fecyclekey), fecycleKey.access._mth100(fecyclekey)), false);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*  60*/        return compute((fecycleKey)obj);
            }

            final ClassReflectionHelperImpl this$0;

            fecycleKey()
            {
/*  60*/        this$0 = ClassReflectionHelperImpl.this;
/*  60*/        super();
            }
}
