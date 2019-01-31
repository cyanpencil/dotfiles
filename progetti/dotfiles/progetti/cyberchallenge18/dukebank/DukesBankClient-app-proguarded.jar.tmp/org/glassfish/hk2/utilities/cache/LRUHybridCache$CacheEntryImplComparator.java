// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUHybridCache.java

package org.glassfish.hk2.utilities.cache;

import java.util.Comparator;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            LRUHybridCache

static class 
    implements Comparator
{

            public int compare( ,  1)
            {
                long l;
/* 375*/        if((l = ccess._mth200() - ccess._mth200(1)) > 0L)
/* 376*/            return 1;
/* 376*/        return l != 0L ? -1 : 0;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 371*/        return compare((compare)obj, (compare)obj1);
            }

            private ()
            {
            }


            // Unreferenced inner class org/glassfish/hk2/utilities/cache/LRUHybridCache$1

/* anonymous class */
    static class LRUHybridCache._cls1
        implements LRUHybridCache.CycleHandler
    {

                public final void handleCycle(Object obj)
                {
                }

    }

}
