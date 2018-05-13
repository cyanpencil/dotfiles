// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class ntry extends Enum
    implements ntry
{

            public final ence getValueReference()
            {
/* 856*/        return null;
            }

            public final void setValueReference(ence ence)
            {
            }

            public final ntry getNext()
            {
/* 864*/        return null;
            }

            public final int getHash()
            {
/* 869*/        return 0;
            }

            public final Object getKey()
            {
/* 874*/        return null;
            }

            public final long getAccessTime()
            {
/* 879*/        return 0L;
            }

            public final void setAccessTime(long l)
            {
            }

            public final ntry getNextInAccessQueue()
            {
/* 887*/        return this;
            }

            public final void setNextInAccessQueue(ntry ntry)
            {
            }

            public final ntry getPreviousInAccessQueue()
            {
/* 895*/        return this;
            }

            public final void setPreviousInAccessQueue(ntry ntry)
            {
            }

            public final long getWriteTime()
            {
/* 903*/        return 0L;
            }

            public final void setWriteTime(long l)
            {
            }

            public final ntry getNextInWriteQueue()
            {
/* 911*/        return this;
            }

            public final void setNextInWriteQueue(ntry ntry)
            {
            }

            public final ntry getPreviousInWriteQueue()
            {
/* 919*/        return this;
            }

            public final void setPreviousInWriteQueue(ntry ntry)
            {
            }

            public static final INSTANCE INSTANCE;
            private static final INSTANCE $VALUES[];

            static 
            {
/* 852*/        INSTANCE = new <init>("INSTANCE", 0);
/* 851*/        $VALUES = (new .VALUES[] {
/* 851*/            INSTANCE
                });
            }

            private ntry(String s, int i)
            {
/* 851*/        super(s, i);
            }
}
