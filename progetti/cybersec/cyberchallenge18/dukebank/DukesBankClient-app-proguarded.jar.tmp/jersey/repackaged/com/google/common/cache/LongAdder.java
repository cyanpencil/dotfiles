// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongAdder.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            Striped64, LongAddable

final class LongAdder extends Striped64
    implements Serializable, LongAddable
{

            final long fn(long l, long l1)
            {
/*  56*/        return l + l1;
            }

            public LongAdder()
            {
            }

            public final void add(long l)
            {
                boolean flag1;
/*  71*/label0:
                {
                    Striped64.Cell acell[];
/*  71*/            if((acell = cells) == null)
                    {
/*  71*/                long l1 = base;
/*  71*/                if(casBase(base, base + l))
/*  72*/                    break label0;
                    }
/*  72*/            boolean flag = true;
                    Striped64.Cell cell;
                    int ai[];
                    int i;
/*  73*/            if((ai = (int[])threadHashCode.get()) != null && acell != null && (i = acell.length) > 0 && (cell = acell[i - 1 & ai[0]]) != null)
                    {
/*  73*/                long l2 = cell.value;
/*  73*/                flag1 = cell.cas(cell.value, cell.value + l);
/*  73*/                flag = flag1;
/*  73*/                if(flag1)
/*  77*/                    break label0;
                    }
/*  77*/            retryUpdate(l, ai, flag);
                }
            }

            public final void increment()
            {
/*  85*/        add(1L);
            }

            public final long sum()
            {
/* 105*/        long l = base;
                Striped64.Cell acell[];
/* 106*/        if((acell = cells) != null)
                {
/* 108*/            int i = acell.length;
/* 109*/            for(int j = 0; j < i; j++)
                    {
                        Striped64.Cell cell;
/* 110*/                if((cell = acell[j]) != null)
/* 112*/                    l += cell.value;
                    }

                }
/* 115*/        return l;
            }

            public final String toString()
            {
/* 161*/        return Long.toString(sum());
            }

            public final long longValue()
            {
/* 170*/        return sum();
            }

            public final int intValue()
            {
/* 178*/        return (int)sum();
            }

            public final float floatValue()
            {
/* 186*/        return (float)sum();
            }

            public final double doubleValue()
            {
/* 194*/        return (double)sum();
            }
}
