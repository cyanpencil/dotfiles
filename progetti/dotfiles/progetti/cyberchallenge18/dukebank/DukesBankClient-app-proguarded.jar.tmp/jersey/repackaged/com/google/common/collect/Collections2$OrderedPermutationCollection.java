// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.math.LongMath;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Ordering, ImmutableList

static final class inputList extends AbstractCollection
{

            private static int calculateSize(List list, Comparator comparator1)
            {
/* 433*/        long l = 1L;
/* 434*/        int i = 1;
                int j;
/* 435*/        for(j = 1; i < list.size(); j++)
                {
                    int k;
/* 437*/            if((k = comparator1.compare(list.get(i - 1), list.get(i))) < 0)
                    {
/* 441*/                l *= LongMath.binomial(i, j);
/* 442*/                j = 0;
/* 443*/                if(!Collections2.access$000(l))
/* 444*/                    return 0x7fffffff;
                    }
/* 447*/            i++;
                }

/* 450*/        if(!Collections2.access$000(l *= LongMath.binomial(i, j)))
/* 452*/            return 0x7fffffff;
/* 454*/        else
/* 454*/            return (int)l;
            }

            public final int size()
            {
/* 458*/        return size;
            }

            public final boolean isEmpty()
            {
/* 462*/        return false;
            }

            public final Iterator iterator()
            {
/* 466*/        return new nit>(inputList, comparator);
            }

            public final boolean contains(Object obj)
            {
/* 470*/        if(obj instanceof List)
                {
/* 471*/            obj = (List)obj;
/* 472*/            return Collections2.access$100(inputList, ((List) (obj)));
                } else
                {
/* 474*/            return false;
                }
            }

            public final String toString()
            {
/* 478*/        String s = String.valueOf(String.valueOf(inputList));
/* 478*/        return (new StringBuilder(30 + s.length())).append("orderedPermutationCollection(").append(s).append(")").toString();
            }

            final ImmutableList inputList;
            final Comparator comparator;
            final int size;

            (Iterable iterable, Comparator comparator1)
            {
/* 417*/        inputList = Ordering.from(comparator1).immutableSortedCopy(iterable);
/* 418*/        comparator = comparator1;
/* 419*/        size = calculateSize(inputList, comparator1);
            }
}
