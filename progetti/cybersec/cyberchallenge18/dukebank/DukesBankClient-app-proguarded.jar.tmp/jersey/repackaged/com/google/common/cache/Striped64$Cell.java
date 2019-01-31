// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Striped64.java

package jersey.repackaged.com.google.common.cache;

import sun.misc.Unsafe;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            Striped64

static final class value
{

            final boolean cas(long l, long l1)
            {
/* 100*/        return UNSAFE.compareAndSwapLong(this, valueOffset, l, l1);
            }

            volatile long value;
            private static final Unsafe UNSAFE;
            private static final long valueOffset;

            static 
            {
/* 108*/        try
                {
/* 108*/            UNSAFE = Striped64.access$000();
/* 109*/            /*<invalid signature>*/java.lang.Object local = jersey/repackaged/com/google/common/cache/Striped64$Cell;
/* 110*/            valueOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("value"));
                }
/* 112*/        catch(Exception exception)
                {
/* 113*/            throw new Error(exception);
                }
            }

            (long l)
            {
/*  97*/        value = l;
            }
}
