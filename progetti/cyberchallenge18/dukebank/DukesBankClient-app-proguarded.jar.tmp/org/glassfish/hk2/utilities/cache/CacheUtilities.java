// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheUtilities.java

package org.glassfish.hk2.utilities.cache;

import org.glassfish.hk2.utilities.cache.internal.WeakCARCacheImpl;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Computable, WeakCARCache

public class CacheUtilities
{

            public CacheUtilities()
            {
            }

            public static WeakCARCache createWeakCARCache(Computable computable, int i, boolean flag)
            {
/*  65*/        return new WeakCARCacheImpl(computable, i, flag);
            }
}
