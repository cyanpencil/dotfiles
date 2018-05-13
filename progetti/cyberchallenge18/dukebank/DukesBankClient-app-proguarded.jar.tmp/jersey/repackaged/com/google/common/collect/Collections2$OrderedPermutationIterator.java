// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIterator, Collections2, ImmutableList, Lists

static final class comparator extends AbstractIterator
{

            protected final List computeNext()
            {
/* 495*/        if(nextPermutation == null)
                {
/* 496*/            return (List)endOfData();
                } else
                {
/* 498*/            ImmutableList immutablelist = ImmutableList.copyOf(nextPermutation);
/* 499*/            calculateNextPermutation();
/* 500*/            return immutablelist;
                }
            }

            final void calculateNextPermutation()
            {
                int i;
/* 504*/        if((i = findNextJ()) == -1)
                {
/* 506*/            nextPermutation = null;
/* 507*/            return;
                } else
                {
/* 510*/            int j = findNextL(i);
/* 511*/            Collections.swap(nextPermutation, i, j);
/* 512*/            j = nextPermutation.size();
/* 513*/            Collections.reverse(nextPermutation.subList(i + 1, j));
/* 514*/            return;
                }
            }

            final int findNextJ()
            {
/* 517*/        for(int i = nextPermutation.size() - 2; i >= 0; i--)
/* 518*/            if(comparator.compare(nextPermutation.get(i), nextPermutation.get(i + 1)) < 0)
/* 520*/                return i;

/* 523*/        return -1;
            }

            final int findNextL(int i)
            {
/* 527*/        Object obj = nextPermutation.get(i);
/* 528*/        for(int j = nextPermutation.size() - 1; j > i; j--)
/* 529*/            if(comparator.compare(obj, nextPermutation.get(j)) < 0)
/* 530*/                return j;

/* 533*/        throw new AssertionError("this statement should be unreachable");
            }

            protected final volatile Object computeNext()
            {
/* 482*/        return computeNext();
            }

            List nextPermutation;
            final Comparator comparator;

            Y(List list, Comparator comparator1)
            {
/* 490*/        nextPermutation = Lists.newArrayList(list);
/* 491*/        comparator = comparator1;
            }
}
