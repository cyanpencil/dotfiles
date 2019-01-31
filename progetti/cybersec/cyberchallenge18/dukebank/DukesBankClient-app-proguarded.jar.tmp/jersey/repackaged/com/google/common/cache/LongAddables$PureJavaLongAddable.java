// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongAddables.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.atomic.AtomicLong;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LongAddable, LongAddables, LongAdder

static final class  extends AtomicLong
    implements LongAddable
{

            public final void increment()
            {
/*  62*/        getAndIncrement();
            }

            public final void add(long l)
            {
/*  67*/        getAndAdd(l);
            }

            public final long sum()
            {
/*  72*/        return get();
            }

            private ()
            {
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/cache/LongAddables$1

/* anonymous class */
    static class LongAddables._cls1
        implements Supplier
    {

                public final LongAddable get()
                {
/*  41*/            return new LongAdder();
                }

                public final volatile Object get()
                {
/*  38*/            return get();
                }

    }

}
