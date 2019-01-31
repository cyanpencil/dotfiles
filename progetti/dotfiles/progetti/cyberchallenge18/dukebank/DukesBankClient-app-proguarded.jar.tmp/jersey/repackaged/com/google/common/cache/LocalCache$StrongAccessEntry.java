// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class previousAccess extends previousAccess
{

            public final long getAccessTime()
            {
/*1116*/        return accessTime;
            }

            public final void setAccessTime(long l)
            {
/*1121*/        accessTime = l;
            }

            public final accessTime getNextInAccessQueue()
            {
/*1129*/        return nextAccess;
            }

            public final void setNextInAccessQueue(nextAccess nextaccess)
            {
/*1134*/        nextAccess = nextaccess;
            }

            public final nextAccess getPreviousInAccessQueue()
            {
/*1142*/        return previousAccess;
            }

            public final void setPreviousInAccessQueue(previousAccess previousaccess)
            {
/*1147*/        previousAccess = previousaccess;
            }

            volatile long accessTime;
            previousAccess nextAccess;
            previousAccess previousAccess;

            (Object obj, int i,  )
            {
/*1107*/        super(obj, i, );
/*1112*/        accessTime = 0x7fffffffffffffffL;
/*1125*/        nextAccess = LocalCache.nullEntry();
/*1138*/        previousAccess = LocalCache.nullEntry();
            }
}
