// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongAddables.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LongAddables, LongAdder, LongAddable

static class 
    implements Supplier
{

            public final LongAddable get()
            {
/*  41*/        return new LongAdder();
            }

            public final volatile Object get()
            {
/*  38*/        return get();
            }

            ()
            {
            }
}
