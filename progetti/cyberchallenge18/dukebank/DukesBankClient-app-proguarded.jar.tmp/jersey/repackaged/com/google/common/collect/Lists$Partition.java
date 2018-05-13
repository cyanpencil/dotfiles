// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.List;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.math.IntMath;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Lists

static class size extends AbstractList
{

            public List get(int i)
            {
/* 669*/        Preconditions.checkElementIndex(i, size());
/* 670*/        int j = Math.min((i *= size) + size, list.size());
/* 672*/        return list.subList(i, j);
            }

            public int size()
            {
/* 676*/        return IntMath.divide(list.size(), size, RoundingMode.CEILING);
            }

            public boolean isEmpty()
            {
/* 680*/        return list.isEmpty();
            }

            public volatile Object get(int i)
            {
/* 659*/        return get(i);
            }

            final List list;
            final int size;

            A(List list1, int i)
            {
/* 664*/        list = list1;
/* 665*/        size = i;
            }
}
