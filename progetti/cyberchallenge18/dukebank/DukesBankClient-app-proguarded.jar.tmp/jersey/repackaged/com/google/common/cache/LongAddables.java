// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongAddables.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.atomic.AtomicLong;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LongAddable, LongAdder

final class LongAddables
{
    static final class PureJavaLongAddable extends AtomicLong
        implements LongAddable
    {

                public final void increment()
                {
/*  62*/            getAndIncrement();
                }

                public final void add(long l)
                {
/*  67*/            getAndAdd(l);
                }

                public final long sum()
                {
/*  72*/            return get();
                }

                private PureJavaLongAddable()
                {
                }

    }


            public static LongAddable create()
            {
/*  56*/        return (LongAddable)SUPPLIER.get();
            }

            private static final Supplier SUPPLIER;

            static 
            {
                Supplier supplier;
/*  37*/        try
                {
/*  37*/            new LongAdder();
/*  38*/            supplier = new Supplier() {

                        public final LongAddable get()
                        {
/*  41*/                    return new LongAdder();
                        }

                        public final volatile Object get()
                        {
/*  38*/                    return get();
                        }

            };
                }
/*  44*/        catch(Throwable _ex)
                {
/*  45*/            supplier = new Supplier() {

                        public final LongAddable get()
                        {
/*  48*/                    return new PureJavaLongAddable();
                        }

                        public final volatile Object get()
                        {
/*  45*/                    return get();
                        }

            };
                }
/*  52*/        SUPPLIER = supplier;
            }
}
