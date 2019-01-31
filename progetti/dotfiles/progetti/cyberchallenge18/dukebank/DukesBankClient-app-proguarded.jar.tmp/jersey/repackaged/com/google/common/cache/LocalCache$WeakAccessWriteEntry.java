// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class previousWrite extends previousWrite
{

            public final long getAccessTime()
            {
/*1500*/        return accessTime;
            }

            public final void setAccessTime(long l)
            {
/*1505*/        accessTime = l;
            }

            public final accessTime getNextInAccessQueue()
            {
/*1513*/        return nextAccess;
            }

            public final void setNextInAccessQueue(nextAccess nextaccess)
            {
/*1518*/        nextAccess = nextaccess;
            }

            public final nextAccess getPreviousInAccessQueue()
            {
/*1526*/        return previousAccess;
            }

            public final void setPreviousInAccessQueue(previousAccess previousaccess)
            {
/*1531*/        previousAccess = previousaccess;
            }

            public final long getWriteTime()
            {
/*1540*/        return writeTime;
            }

            public final void setWriteTime(long l)
            {
/*1545*/        writeTime = l;
            }

            public final writeTime getNextInWriteQueue()
            {
/*1553*/        return nextWrite;
            }

            public final void setNextInWriteQueue(nextWrite nextwrite)
            {
/*1558*/        nextWrite = nextwrite;
            }

            public final nextWrite getPreviousInWriteQueue()
            {
/*1566*/        return previousWrite;
            }

            public final void setPreviousInWriteQueue(previousWrite previouswrite)
            {
/*1571*/        previousWrite = previouswrite;
            }

            volatile long accessTime;
            previousWrite nextAccess;
            previousWrite previousAccess;
            volatile long writeTime;
            previousWrite nextWrite;
            previousWrite previousWrite;

            (ReferenceQueue referencequeue, Object obj, int i,  )
            {
/*1491*/        super(referencequeue, obj, i, );
/*1496*/        accessTime = 0x7fffffffffffffffL;
/*1509*/        nextAccess = LocalCache.nullEntry();
/*1522*/        previousAccess = LocalCache.nullEntry();
/*1536*/        writeTime = 0x7fffffffffffffffL;
/*1549*/        nextWrite = LocalCache.nullEntry();
/*1562*/        previousWrite = LocalCache.nullEntry();
            }
}
