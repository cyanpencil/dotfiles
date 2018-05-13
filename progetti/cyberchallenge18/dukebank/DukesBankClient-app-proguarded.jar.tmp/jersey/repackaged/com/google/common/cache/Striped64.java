// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Striped64.java

package jersey.repackaged.com.google.common.cache;

import java.lang.reflect.Field;
import java.security.*;
import java.util.Random;
import sun.misc.Unsafe;

abstract class Striped64 extends Number
{
    static final class Cell
    {

                final boolean cas(long l, long l1)
                {
/* 100*/            return UNSAFE.compareAndSwapLong(this, valueOffset, l, l1);
                }

                volatile long value;
                private static final Unsafe UNSAFE;
                private static final long valueOffset;

                static 
                {
/* 108*/            try
                    {
/* 108*/                UNSAFE = Striped64.getUnsafe();
/* 109*/                /*<invalid signature>*/java.lang.Object local = jersey/repackaged/com/google/common/cache/Striped64$Cell;
/* 110*/                valueOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("value"));
                    }
/* 112*/            catch(Exception exception)
                    {
/* 113*/                throw new Error(exception);
                    }
                }

                Cell(long l)
                {
/*  97*/            value = l;
                }
    }


            Striped64()
            {
            }

            final boolean casBase(long l, long l1)
            {
/* 161*/        return UNSAFE.compareAndSwapLong(this, baseOffset, l, l1);
            }

            final boolean casBusy()
            {
/* 168*/        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
            }

            abstract long fn(long l, long l1);

            final void retryUpdate(long l, int ai[], boolean flag)
            {
                boolean flag1;
                int j;
/* 195*/        if(ai == null)
                {
/* 196*/            threadHashCode.set(ai = new int[1]);
/* 197*/            int i = rng.nextInt();
/* 198*/            j = ai[0] = i != 0 ? i : 1;
                } else
                {
/* 201*/            j = ai[0];
                }
/* 202*/        flag1 = false;
_L2:
                Cell acell[];
                int k;
                Cell cell;
                boolean flag3;
/* 205*/        if((acell = cells) == null || (k = acell.length) <= 0)
/* 206*/            break; /* Loop/switch isn't completed */
/* 206*/        if((cell = acell[k - 1 & j]) != null)
/* 207*/            break MISSING_BLOCK_LABEL_193;
/* 207*/        if(busy != 0)
/* 208*/            break MISSING_BLOCK_LABEL_187;
/* 208*/        cell = new Cell(l);
/* 209*/        if(busy != 0 || !casBusy())
/* 210*/            break MISSING_BLOCK_LABEL_187;
/* 210*/        flag3 = false;
/* 213*/        if((acell = cells) != null && (k = acell.length) > 0 && acell[k = k - 1 & j] == null)
                {
/* 216*/            acell[k] = cell;
/* 217*/            flag3 = true;
                }
/* 220*/        busy = 0;
/* 221*/        break MISSING_BLOCK_LABEL_181;
/* 220*/        l;
/* 220*/        busy = 0;
/* 220*/        throw l;
/* 222*/        if(flag3)
/* 223*/            return;
/* 227*/        continue; /* Loop/switch isn't completed */
/* 227*/        flag1 = false;
/* 227*/        break MISSING_BLOCK_LABEL_347;
/* 229*/        if(!flag)
                {
/* 230*/            flag = true;
/* 230*/            break MISSING_BLOCK_LABEL_347;
                }
                long l1;
/* 231*/        if(cell.cas(l1 = cell.value, fn(l1, l)))
/* 233*/            break; /* Loop/switch isn't completed */
/* 233*/        if(k >= NCPU || cells != acell)
                {
/* 234*/            flag1 = false;
/* 234*/            break MISSING_BLOCK_LABEL_347;
                }
/* 235*/        if(!flag1)
                {
/* 236*/            flag1 = true;
/* 236*/            break MISSING_BLOCK_LABEL_347;
                }
/* 237*/        if(busy != 0 || !casBusy())
/* 239*/            break MISSING_BLOCK_LABEL_347;
/* 239*/        if(cells == acell)
                {
/* 240*/            Cell acell1[] = new Cell[k << 1];
/* 241*/            for(int i1 = 0; i1 < k; i1++)
/* 242*/                acell1[i1] = acell[i1];

/* 243*/            cells = acell1;
                }
/* 246*/        busy = 0;
/* 247*/        break MISSING_BLOCK_LABEL_341;
/* 246*/        l;
/* 246*/        busy = 0;
/* 246*/        throw l;
/* 248*/        flag1 = false;
/* 249*/        continue; /* Loop/switch isn't completed */
/* 251*/        j = (j = (j ^= j << 13) ^ j >>> 17) ^ j << 5;
/* 254*/        ai[0] = j;
/* 254*/        if(true) goto _L2; else goto _L1
_L1:
                boolean flag2;
/* 256*/        if(busy != 0 || cells != acell || !casBusy())
/* 257*/            continue; /* Loop/switch isn't completed */
/* 257*/        flag2 = false;
/* 259*/        if(cells == acell)
                {
                    Cell acell2[];
/* 260*/            (acell2 = new Cell[2])[j & 1] = new Cell(l);
/* 262*/            cells = acell2;
/* 263*/            flag2 = true;
                }
/* 266*/        busy = 0;
/* 267*/        continue; /* Loop/switch isn't completed */
/* 266*/        l;
/* 266*/        busy = 0;
/* 266*/        throw l;
/* 268*/        if(flag2) goto _L3; else goto _L2
_L3:
/* 270*/        break; /* Loop/switch isn't completed */
                long l2;
/* 271*/        if(casBase(l2 = base, fn(l2, l))) goto _L4; else goto _L2
_L4:
            }

            private static Unsafe getUnsafe()
            {
/* 318*/        return Unsafe.getUnsafe();
/* 319*/        JVM INSTR pop ;
/* 321*/        return (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public final Unsafe run()
                        throws Exception
                    {
                        /*<invalid signature>*/java.lang.Object local;
                        Field afield[];
/* 324*/                int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/* 325*/                for(int j = 0; j < i; j++)
                        {
                            Object obj;
/* 325*/                    ((Field) (obj = afield[j])).setAccessible(true);
/* 327*/                    obj = ((Field) (obj)).get(null);
/* 328*/                    if(local.isInstance(obj))
/* 329*/                        return (Unsafe)local.cast(obj);
                        }

/* 331*/                throw new NoSuchFieldError("the Unsafe");
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/* 322*/                return run();
                    }

        });
                PrivilegedActionException privilegedactionexception;
/* 333*/        privilegedactionexception;
/* 334*/        throw new RuntimeException("Could not initialize intrinsics", privilegedactionexception.getCause());
            }

            static final ThreadLocal threadHashCode = new ThreadLocal();
            static final Random rng = new Random();
            static final int NCPU = Runtime.getRuntime().availableProcessors();
            volatile transient Cell cells[];
            volatile transient long base;
            volatile transient int busy;
            private static final Unsafe UNSAFE;
            private static final long baseOffset;
            private static final long busyOffset;

            static 
            {
/* 298*/        try
                {
/* 298*/            UNSAFE = getUnsafe();
/* 299*/            /*<invalid signature>*/java.lang.Object local = jersey/repackaged/com/google/common/cache/Striped64;
/* 300*/            baseOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("base"));
/* 302*/            busyOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("busy"));
                }
/* 304*/        catch(Exception exception)
                {
/* 305*/            throw new Error(exception);
                }
            }

}
