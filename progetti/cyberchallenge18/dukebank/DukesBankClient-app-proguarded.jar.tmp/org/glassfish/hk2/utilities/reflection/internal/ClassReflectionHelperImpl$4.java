// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperImpl.java

package org.glassfish.hk2.utilities.reflection.internal;

import org.glassfish.hk2.utilities.cache.*;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperImpl, ClassReflectionHelperUtilities

class this._cls0
    implements Computable
{

            public HybridCacheEntry compute(Class class1)
            {
/*  95*/        return ClassReflectionHelperImpl.access$700(ClassReflectionHelperImpl.this).createCacheEntry(class1, ClassReflectionHelperUtilities.getAllFieldWrappers(class1), false);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*  91*/        return compute((Class)obj);
            }

            final ClassReflectionHelperImpl this$0;

            es()
            {
/*  91*/        this$0 = ClassReflectionHelperImpl.this;
/*  91*/        super();
            }
}
