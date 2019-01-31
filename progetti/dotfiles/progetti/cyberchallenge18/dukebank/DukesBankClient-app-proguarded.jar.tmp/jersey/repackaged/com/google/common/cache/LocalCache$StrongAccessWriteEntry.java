// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class previousWrite extends previousWrite
{

            public final long getAccessTime()
            {
/*1208*/        return accessTime;
            }

            public final void setAccessTime(long l)
            {
/*1213*/        accessTime = l;
            }

            public final accessTime getNextInAccessQueue()
            {
/*1221*/        return nextAccess;
            }

            public final void setNextInAccessQueue(nextAccess nextaccess)
            {
/*1226*/        nextAccess = nextaccess;
            }

            public final nextAccess getPreviousInAccessQueue()
            {
/*1234*/        return previousAccess;
            }

            public final void setPreviousInAccessQueue(previousAccess previousaccess)
            {
/*1239*/        previousAccess = previousaccess;
            }

            public final long getWriteTime()
            {
/*1248*/        return writeTime;
            }

            public final void setWriteTime(long l)
            {
/*1253*/        writeTime = l;
            }

            public final writeTime getNextInWriteQueue()
            {
/*1261*/        return nextWrite;
            }

            public final void setNextInWriteQueue(nextWrite nextwrite)
            {
/*1266*/        nextWrite = nextwrite;
            }

            public final nextWrite getPreviousInWriteQueue()
            {
/*1274*/        return previousWrite;
            }

            public final void setPreviousInWriteQueue(previousWrite previouswrite)
            {
/*1279*/        previousWrite = previouswrite;
            }

            volatile long accessTime;
            previousWrite nextAccess;
            previousWrite previousAccess;
            volatile long writeTime;
            previousWrite nextWrite;
            previousWrite previousWrite;

            Q(Object obj, int i, Q q)
            {
/*1199*/        super(obj, i, q);
/*1204*/        accessTime = 0x7fffffffffffffffL;
/*1217*/        nextAccess = LocalCache.nullEntry();
/*1230*/        previousAccess = LocalCache.nullEntry();
/*1244*/        writeTime = 0x7fffffffffffffffL;
/*1257*/        nextWrite = LocalCache.nullEntry();
/*1270*/        previousWrite = LocalCache.nullEntry();
            }
}
