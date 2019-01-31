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

            public final long getWriteTime()
            {
/*1453*/        return writeTime;
            }

            public final void setWriteTime(long l)
            {
/*1458*/        writeTime = l;
            }

            public final writeTime getNextInWriteQueue()
            {
/*1466*/        return nextWrite;
            }

            public final void setNextInWriteQueue(nextWrite nextwrite)
            {
/*1471*/        nextWrite = nextwrite;
            }

            public final nextWrite getPreviousInWriteQueue()
            {
/*1479*/        return previousWrite;
            }

            public final void setPreviousInWriteQueue(previousWrite previouswrite)
            {
/*1484*/        previousWrite = previouswrite;
            }

            volatile long writeTime;
            previousWrite nextWrite;
            previousWrite previousWrite;

            I(ReferenceQueue referencequeue, Object obj, int i, I j)
            {
/*1444*/        super(referencequeue, obj, i, j);
/*1449*/        writeTime = 0x7fffffffffffffffL;
/*1462*/        nextWrite = LocalCache.nullEntry();
/*1475*/        previousWrite = LocalCache.nullEntry();
            }
}
