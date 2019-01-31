// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Count.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;

final class Count
    implements Serializable
{

            Count(int i)
            {
/*  33*/        value = i;
            }

            public final int get()
            {
/*  37*/        return value;
            }

            public final int getAndAdd(int i)
            {
/*  41*/        int j = value;
/*  42*/        value = j + i;
/*  43*/        return j;
            }

            public final int addAndGet(int i)
            {
/*  47*/        return value += i;
            }

            public final void set(int i)
            {
/*  51*/        value = i;
            }

            public final int getAndSet(int i)
            {
/*  55*/        int j = value;
/*  56*/        value = i;
/*  57*/        return j;
            }

            public final int hashCode()
            {
/*  62*/        return value;
            }

            public final boolean equals(Object obj)
            {
/*  67*/        return (obj instanceof Count) && ((Count)obj).value == value;
            }

            public final String toString()
            {
/*  72*/        return Integer.toString(value);
            }

            private int value;
}
