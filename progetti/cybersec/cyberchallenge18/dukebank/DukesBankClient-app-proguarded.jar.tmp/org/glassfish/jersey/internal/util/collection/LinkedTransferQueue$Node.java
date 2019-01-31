// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LinkedTransferQueue.java

package org.glassfish.jersey.internal.util.collection;

import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            LinkedTransferQueue

static final class isData
{

            final boolean casNext(isData isdata, isData isdata1)
            {
/* 468*/        return UNSAFE.compareAndSwapObject(this, nextOffset, isdata, isdata1);
            }

            final boolean casItem(Object obj, Object obj1)
            {
/* 473*/        return UNSAFE.compareAndSwapObject(this, itemOffset, obj, obj1);
            }

            final void forgetNext()
            {
/* 490*/        UNSAFE.putObject(this, nextOffset, this);
            }

            final void forgetContents()
            {
/* 503*/        UNSAFE.putObject(this, itemOffset, this);
/* 504*/        UNSAFE.putObject(this, waiterOffset, null);
            }

            final boolean isMatched()
            {
                Object obj;
/* 512*/        return (obj = item) == this || (obj == null) == isData;
            }

            final boolean isUnmatchedRequest()
            {
/* 520*/        return !isData && item == null;
            }

            final boolean cannotPrecede(boolean flag)
            {
                boolean flag1;
/* 529*/        return (flag1 = isData) != flag && (flag = ((boolean) (item))) != this && (flag != null) == flag1;
            }

            final boolean tryMatchData()
            {
                Object obj;
/* 539*/        if((obj = item) != null && obj != this && casItem(obj, null))
                {
/* 541*/            LockSupport.unpark(waiter);
/* 542*/            return true;
                } else
                {
/* 544*/            return false;
                }
            }

            final boolean isData;
            volatile Object item;
            volatile UNSAFE next;
            volatile Thread waiter;
            private static final long serialVersionUID = 0xd1261c33b18e3356L;
            private static final Unsafe UNSAFE;
            private static final long itemOffset;
            private static final long nextOffset;
            private static final long waiterOffset;

            static 
            {
/* 557*/        try
                {
/* 557*/            UNSAFE = LinkedTransferQueue.getUnsafe();
/* 558*/            /*<invalid signature>*/java.lang.Object local = org/glassfish/jersey/internal/util/collection/LinkedTransferQueue$Node;
/* 559*/            itemOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("item"));
/* 561*/            nextOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("next"));
/* 563*/            waiterOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("waiter"));
                }
/* 565*/        catch(Exception exception)
                {
/* 566*/            throw new Error(exception);
                }
            }

            (Object obj, boolean flag)
            {
/* 481*/        UNSAFE.putObject(this, itemOffset, obj);
/* 482*/        isData = flag;
            }
}
