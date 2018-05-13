// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class ntry extends Enum
    implements ntry
{

            public final ence getValueReference()
            {
/* 724*/        return null;
            }

            public final void setValueReference(ence ence)
            {
            }

            public final ntry getNext()
            {
/* 732*/        return null;
            }

            public final int getHash()
            {
/* 737*/        return 0;
            }

            public final Object getKey()
            {
/* 742*/        return null;
            }

            public final long getExpirationTime()
            {
/* 747*/        return 0L;
            }

            public final void setExpirationTime(long l)
            {
            }

            public final ntry getNextExpirable()
            {
/* 755*/        return this;
            }

            public final void setNextExpirable(ntry ntry)
            {
            }

            public final ntry getPreviousExpirable()
            {
/* 763*/        return this;
            }

            public final void setPreviousExpirable(ntry ntry)
            {
            }

            public final ntry getNextEvictable()
            {
/* 771*/        return this;
            }

            public final void setNextEvictable(ntry ntry)
            {
            }

            public final ntry getPreviousEvictable()
            {
/* 779*/        return this;
            }

            public final void setPreviousEvictable(ntry ntry)
            {
            }

            public static final INSTANCE INSTANCE;
            private static final INSTANCE $VALUES[];

            static 
            {
/* 720*/        INSTANCE = new <init>("INSTANCE", 0);
/* 719*/        $VALUES = (new .VALUES[] {
/* 719*/            INSTANCE
                });
            }

            private ntry(String s, int i)
            {
/* 719*/        super(s, i);
            }
}
