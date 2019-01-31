// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class previousWrite extends previousWrite
{

            public final long getWriteTime()
            {
/*1162*/        return writeTime;
            }

            public final void setWriteTime(long l)
            {
/*1167*/        writeTime = l;
            }

            public final writeTime getNextInWriteQueue()
            {
/*1175*/        return nextWrite;
            }

            public final void setNextInWriteQueue(nextWrite nextwrite)
            {
/*1180*/        nextWrite = nextwrite;
            }

            public final nextWrite getPreviousInWriteQueue()
            {
/*1188*/        return previousWrite;
            }

            public final void setPreviousInWriteQueue(previousWrite previouswrite)
            {
/*1193*/        previousWrite = previouswrite;
            }

            volatile long writeTime;
            previousWrite nextWrite;
            previousWrite previousWrite;

            (Object obj, int i,  )
            {
/*1153*/        super(obj, i, );
/*1158*/        writeTime = 0x7fffffffffffffffL;
/*1171*/        nextWrite = LocalCache.nullEntry();
/*1184*/        previousWrite = LocalCache.nullEntry();
            }
}
