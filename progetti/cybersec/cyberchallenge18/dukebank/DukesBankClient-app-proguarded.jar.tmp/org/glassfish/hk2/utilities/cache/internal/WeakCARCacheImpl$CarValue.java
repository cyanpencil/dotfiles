// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakCARCacheImpl.java

package org.glassfish.hk2.utilities.cache.internal;


// Referenced classes of package org.glassfish.hk2.utilities.cache.internal:
//            WeakCARCacheImpl

static class <init>
{

            private final Object value;
            private volatile boolean referenceBit;




            private (Object obj)
            {
/* 312*/        referenceBit = false;
/* 315*/        value = obj;
            }

            value(Object obj, value value1)
            {
/* 310*/        this(obj);
            }
}
