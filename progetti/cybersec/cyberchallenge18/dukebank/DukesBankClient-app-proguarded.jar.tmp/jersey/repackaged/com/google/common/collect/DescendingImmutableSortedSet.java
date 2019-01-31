// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescendingImmutableSortedSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NavigableSet;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedSet, Ordering, UnmodifiableIterator

class DescendingImmutableSortedSet extends ImmutableSortedSet
{

            DescendingImmutableSortedSet(ImmutableSortedSet immutablesortedset)
            {
/*  32*/        super(Ordering.from(immutablesortedset.comparator()).reverse());
/*  33*/        forward = immutablesortedset;
            }

            public int size()
            {
/*  38*/        return forward.size();
            }

            public UnmodifiableIterator iterator()
            {
/*  43*/        return forward.descendingIterator();
            }

            ImmutableSortedSet headSetImpl(Object obj, boolean flag)
            {
/*  48*/        return forward.tailSet(obj, flag).descendingSet();
            }

            ImmutableSortedSet subSetImpl(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*  54*/        return forward.subSet(obj1, flag1, obj, flag).descendingSet();
            }

            ImmutableSortedSet tailSetImpl(Object obj, boolean flag)
            {
/*  59*/        return forward.headSet(obj, flag).descendingSet();
            }

            public ImmutableSortedSet descendingSet()
            {
/*  65*/        return forward;
            }

            public UnmodifiableIterator descendingIterator()
            {
/*  71*/        return forward.iterator();
            }

            ImmutableSortedSet createDescendingSet()
            {
/*  77*/        throw new AssertionError("should never be called");
            }

            public Object lower(Object obj)
            {
/*  82*/        return forward.higher(obj);
            }

            public Object floor(Object obj)
            {
/*  87*/        return forward.ceiling(obj);
            }

            public Object ceiling(Object obj)
            {
/*  92*/        return forward.floor(obj);
            }

            public Object higher(Object obj)
            {
/*  97*/        return forward.lower(obj);
            }

            boolean isPartialView()
            {
/* 112*/        return forward.isPartialView();
            }

            public volatile Iterator descendingIterator()
            {
/*  28*/        return descendingIterator();
            }

            public volatile NavigableSet descendingSet()
            {
/*  28*/        return descendingSet();
            }

            public volatile Iterator iterator()
            {
/*  28*/        return iterator();
            }

            private final ImmutableSortedSet forward;
}
