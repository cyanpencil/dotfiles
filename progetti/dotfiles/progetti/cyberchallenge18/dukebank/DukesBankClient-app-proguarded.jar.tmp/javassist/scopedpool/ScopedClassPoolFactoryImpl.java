// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedClassPoolFactoryImpl.java

package javassist.scopedpool;

import javassist.ClassPool;

// Referenced classes of package javassist.scopedpool:
//            ScopedClassPool, ScopedClassPoolFactory, ScopedClassPoolRepository

public class ScopedClassPoolFactoryImpl
    implements ScopedClassPoolFactory
{

            public ScopedClassPoolFactoryImpl()
            {
            }

            public ScopedClassPool create(ClassLoader classloader, ClassPool classpool, ScopedClassPoolRepository scopedclasspoolrepository)
            {
/*  33*/        return new ScopedClassPool(classloader, classpool, scopedclasspoolrepository, false);
            }

            public ScopedClassPool create(ClassPool classpool, ScopedClassPoolRepository scopedclasspoolrepository)
            {
/*  41*/        return new ScopedClassPool(null, classpool, scopedclasspoolrepository, true);
            }
}
