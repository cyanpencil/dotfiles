// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class previousAccess extends previousAccess
{

            public final long getAccessTime()
            {
/*1406*/        return accessTime;
            }

            public final void setAccessTime(long l)
            {
/*1411*/        accessTime = l;
            }

            public final accessTime getNextInAccessQueue()
            {
/*1419*/        return nextAccess;
            }

            public final void setNextInAccessQueue(nextAccess nextaccess)
            {
/*1424*/        nextAccess = nextaccess;
            }

            public final nextAccess getPreviousInAccessQueue()
            {
/*1432*/        return previousAccess;
            }

            public final void setPreviousInAccessQueue(previousAccess previousaccess)
            {
/*1437*/        previousAccess = previousaccess;
            }

            volatile long accessTime;
            previousAccess nextAccess;
            previousAccess previousAccess;

            (ReferenceQueue referencequeue, Object obj, int i,  )
            {
/*1397*/        super(referencequeue, obj, i, );
/*1402*/        accessTime = 0x7fffffffffffffffL;
/*1415*/        nextAccess = LocalCache.nullEntry();
/*1428*/        previousAccess = LocalCache.nullEntry();
            }
}
