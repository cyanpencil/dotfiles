// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperImpl.java

package org.glassfish.hk2.utilities.reflection.internal;

import org.glassfish.hk2.utilities.cache.*;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperImpl

static final class hash
{

            public final int hashCode()
            {
/* 236*/        return hash;
            }

            public final boolean equals(Object obj)
            {
/* 240*/        if(obj == null)
/* 240*/            return false;
/* 241*/        if(!(obj instanceof hash))
/* 241*/            return false;
/* 242*/        else
/* 242*/            return clazz.equals(((clazz)obj).clazz);
            }

            private final Class clazz;
            private final Class matchingClass;
            private final int hash;



            private (Class class1, Class class2)
            {
/* 230*/        clazz = class1;
/* 231*/        matchingClass = class2;
/* 232*/        hash = class1.hashCode();
            }


            // Unreferenced inner class org/glassfish/hk2/utilities/reflection/internal/ClassReflectionHelperImpl$1

/* anonymous class */
    class ClassReflectionHelperImpl._cls1
        implements Computable
    {

                public HybridCacheEntry compute(ClassReflectionHelperImpl.LifecycleKey lifecyclekey)
                {
/*  64*/            return ClassReflectionHelperImpl.access$300(ClassReflectionHelperImpl.this).createCacheEntry(lifecyclekey, ClassReflectionHelperImpl.access$200(ClassReflectionHelperImpl.this, lifecyclekey.clazz, lifecyclekey.matchingClass), false);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  60*/            return compute((ClassReflectionHelperImpl.LifecycleKey)obj);
                }

                final ClassReflectionHelperImpl this$0;

                    
                    {
/*  60*/                this$0 = ClassReflectionHelperImpl.this;
/*  60*/                super();
                    }
    }

}
