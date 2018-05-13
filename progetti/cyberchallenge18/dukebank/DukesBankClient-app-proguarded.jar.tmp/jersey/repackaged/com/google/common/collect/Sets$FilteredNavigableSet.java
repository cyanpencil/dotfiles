// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NavigableSet;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterables, Iterators, Sets

static class it> extends it>
    implements NavigableSet
{

            NavigableSet unfiltered()
            {
/* 930*/        return (NavigableSet)unfiltered;
            }

            public Object lower(Object obj)
            {
/* 936*/        return Iterators.getNext(headSet(obj, false).descendingIterator(), null);
            }

            public Object floor(Object obj)
            {
/* 942*/        return Iterators.getNext(headSet(obj, true).descendingIterator(), null);
            }

            public Object ceiling(Object obj)
            {
/* 947*/        return Iterables.getFirst(tailSet(obj, true), null);
            }

            public Object higher(Object obj)
            {
/* 952*/        return Iterables.getFirst(tailSet(obj, false), null);
            }

            public Object pollFirst()
            {
/* 957*/        return Iterables.removeFirstMatching(unfiltered(), predicate);
            }

            public Object pollLast()
            {
/* 962*/        return Iterables.removeFirstMatching(unfiltered().descendingSet(), predicate);
            }

            public NavigableSet descendingSet()
            {
/* 967*/        return Sets.filter(unfiltered().descendingSet(), predicate);
            }

            public Iterator descendingIterator()
            {
/* 972*/        return Iterators.filter(unfiltered().descendingIterator(), predicate);
            }

            public Object last()
            {
/* 977*/        return descendingIterator().next();
            }

            public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 983*/        return Sets.filter(unfiltered().subSet(obj, flag, obj1, flag1), predicate);
            }

            public NavigableSet headSet(Object obj, boolean flag)
            {
/* 989*/        return Sets.filter(unfiltered().headSet(obj, flag), predicate);
            }

            public NavigableSet tailSet(Object obj, boolean flag)
            {
/* 994*/        return Sets.filter(unfiltered().tailSet(obj, flag), predicate);
            }

            (NavigableSet navigableset, Predicate predicate)
            {
/* 926*/        super(navigableset, predicate);
            }
}
