// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIterator, Collections2, ImmutableList

static class j extends AbstractIterator
{

            protected List computeNext()
            {
/* 613*/        if(j <= 0)
                {
/* 614*/            return (List)endOfData();
                } else
                {
/* 616*/            ImmutableList immutablelist = ImmutableList.copyOf(list);
/* 617*/            calculateNextPermutation();
/* 618*/            return immutablelist;
                }
            }

            void calculateNextPermutation()
            {
/* 622*/label0:
                {
/* 622*/            j = list.size() - 1;
/* 623*/            int i = 0;
/* 627*/            if(j == -1)
/* 628*/                return;
                    int k;
/* 632*/            do
                    {
/* 632*/                while((k = c[j] + o[j]) < 0) 
/* 634*/                    switchDirection();
/* 637*/                if(k != j + 1)
/* 638*/                    break;
/* 638*/                if(j == 0)
/* 641*/                    break label0;
/* 641*/                i++;
/* 642*/                switchDirection();
                    } while(true);
/* 646*/            Collections.swap(list, (j - c[j]) + i, (j - k) + i);
/* 647*/            c[j] = k;
                }
            }

            void switchDirection()
            {
/* 653*/        o[j] = -o[j];
/* 654*/        j--;
            }

            protected volatile Object computeNext()
            {
/* 595*/        return computeNext();
            }

            final List list;
            final int c[];
            final int o[];
            int j;

            (List list1)
            {
/* 603*/        list = new ArrayList(list1);
/* 604*/        list1 = list1.size();
/* 605*/        c = new int[list1];
/* 606*/        o = new int[list1];
/* 607*/        Arrays.fill(c, 0);
/* 608*/        Arrays.fill(o, 1);
/* 609*/        j = 0x7fffffff;
            }
}
