// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList

static class forwardList extends ImmutableList
{

            private int reverseIndex(int i)
            {
/* 521*/        return size() - 1 - i;
            }

            private int reversePosition(int i)
            {
/* 525*/        return size() - i;
            }

            public ImmutableList reverse()
            {
/* 529*/        return forwardList;
            }

            public boolean contains(Object obj)
            {
/* 533*/        return forwardList.contains(obj);
            }

            public int indexOf(Object obj)
            {
/* 537*/        if((obj = forwardList.lastIndexOf(obj)) >= 0)
/* 538*/            return reverseIndex(((int) (obj)));
/* 538*/        else
/* 538*/            return -1;
            }

            public int lastIndexOf(Object obj)
            {
/* 542*/        if((obj = forwardList.indexOf(obj)) >= 0)
/* 543*/            return reverseIndex(((int) (obj)));
/* 543*/        else
/* 543*/            return -1;
            }

            public ImmutableList subList(int i, int j)
            {
/* 547*/        Preconditions.checkPositionIndexes(i, j, size());
/* 548*/        return forwardList.subList(reversePosition(j), reversePosition(i)).reverse();
            }

            public Object get(int i)
            {
/* 553*/        Preconditions.checkElementIndex(i, size());
/* 554*/        return forwardList.get(reverseIndex(i));
            }

            public int size()
            {
/* 558*/        return forwardList.size();
            }

            boolean isPartialView()
            {
/* 562*/        return forwardList.isPartialView();
            }

            public volatile List subList(int i, int j)
            {
/* 513*/        return subList(i, j);
            }

            public volatile ListIterator listIterator(int i)
            {
/* 513*/        return super.listIterator(i);
            }

            public volatile ListIterator listIterator()
            {
/* 513*/        return super.listIterator();
            }

            public volatile Iterator iterator()
            {
/* 513*/        return super.iterator();
            }

            private final transient ImmutableList forwardList;

            (ImmutableList immutablelist)
            {
/* 517*/        forwardList = immutablelist;
            }
}
