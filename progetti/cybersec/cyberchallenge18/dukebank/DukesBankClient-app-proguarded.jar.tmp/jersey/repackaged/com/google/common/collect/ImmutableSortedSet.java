// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSortedSet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedSetFauxverideShim, DescendingImmutableSortedSet, EmptyImmutableSortedSet, Iterables, 
//            Iterators, Ordering, SortedIterable, UnmodifiableIterator

public abstract class ImmutableSortedSet extends ImmutableSortedSetFauxverideShim
    implements NavigableSet, SortedIterable
{
    static class SerializedForm
        implements Serializable
    {

                final Comparator comparator;
                final Object elements[];

                public SerializedForm(Comparator comparator1, Object aobj[])
                {
/* 815*/            comparator = comparator1;
/* 816*/            elements = aobj;
                }
    }


            private static ImmutableSortedSet emptySet()
            {
/* 105*/        return NATURAL_EMPTY_SET;
            }

            static ImmutableSortedSet emptySet(Comparator comparator1)
            {
/* 110*/        if(NATURAL_ORDER.equals(comparator1))
/* 111*/            return emptySet();
/* 113*/        else
/* 113*/            return new EmptyImmutableSortedSet(comparator1);
            }

            ImmutableSortedSet(Comparator comparator1)
            {
/* 585*/        comparator = comparator1;
            }

            public Comparator comparator()
            {
/* 597*/        return comparator;
            }

            public abstract UnmodifiableIterator iterator();

            public ImmutableSortedSet headSet(Object obj)
            {
/* 616*/        return headSet(obj, false);
            }

            public ImmutableSortedSet headSet(Object obj, boolean flag)
            {
/* 625*/        return headSetImpl(Preconditions.checkNotNull(obj), flag);
            }

            public ImmutableSortedSet subSet(Object obj, Object obj1)
            {
/* 643*/        return subSet(obj, true, obj1, false);
            }

            public ImmutableSortedSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 653*/        Preconditions.checkNotNull(obj);
/* 654*/        Preconditions.checkNotNull(obj1);
/* 655*/        Preconditions.checkArgument(comparator.compare(obj, obj1) <= 0);
/* 656*/        return subSetImpl(obj, flag, obj1, flag1);
            }

            public ImmutableSortedSet tailSet(Object obj)
            {
/* 672*/        return tailSet(obj, true);
            }

            public ImmutableSortedSet tailSet(Object obj, boolean flag)
            {
/* 681*/        return tailSetImpl(Preconditions.checkNotNull(obj), flag);
            }

            abstract ImmutableSortedSet headSetImpl(Object obj, boolean flag);

            abstract ImmutableSortedSet subSetImpl(Object obj, boolean flag, Object obj1, boolean flag1);

            abstract ImmutableSortedSet tailSetImpl(Object obj, boolean flag);

            public Object lower(Object obj)
            {
/* 701*/        return Iterators.getNext(headSet(obj, false).descendingIterator(), null);
            }

            public Object floor(Object obj)
            {
/* 710*/        return Iterators.getNext(headSet(obj, true).descendingIterator(), null);
            }

            public Object ceiling(Object obj)
            {
/* 719*/        return Iterables.getFirst(tailSet(obj, true), null);
            }

            public Object higher(Object obj)
            {
/* 728*/        return Iterables.getFirst(tailSet(obj, false), null);
            }

            public Object first()
            {
/* 733*/        return iterator().next();
            }

            public Object last()
            {
/* 738*/        return descendingIterator().next();
            }

            /**
             * @deprecated Method pollFirst is deprecated
             */

            public final Object pollFirst()
            {
/* 752*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method pollLast is deprecated
             */

            public final Object pollLast()
            {
/* 766*/        throw new UnsupportedOperationException();
            }

            public ImmutableSortedSet descendingSet()
            {
                ImmutableSortedSet immutablesortedset;
/* 779*/        if((immutablesortedset = descendingSet) == null)
/* 781*/            (immutablesortedset = descendingSet = createDescendingSet()).descendingSet = this;
/* 784*/        return immutablesortedset;
            }

            ImmutableSortedSet createDescendingSet()
            {
/* 789*/        return new DescendingImmutableSortedSet(this);
            }

            public abstract UnmodifiableIterator descendingIterator();

            Object writeReplace()
            {
/* 833*/        return new SerializedForm(comparator, toArray());
            }

            public volatile Iterator iterator()
            {
/*  92*/        return iterator();
            }

            public volatile SortedSet tailSet(Object obj)
            {
/*  92*/        return tailSet(obj);
            }

            public volatile SortedSet headSet(Object obj)
            {
/*  92*/        return headSet(obj);
            }

            public volatile SortedSet subSet(Object obj, Object obj1)
            {
/*  92*/        return subSet(obj, obj1);
            }

            public volatile NavigableSet tailSet(Object obj, boolean flag)
            {
/*  92*/        return tailSet(obj, flag);
            }

            public volatile NavigableSet headSet(Object obj, boolean flag)
            {
/*  92*/        return headSet(obj, flag);
            }

            public volatile NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*  92*/        return subSet(obj, flag, obj1, flag1);
            }

            public volatile Iterator descendingIterator()
            {
/*  92*/        return descendingIterator();
            }

            public volatile NavigableSet descendingSet()
            {
/*  92*/        return descendingSet();
            }

            private static final Comparator NATURAL_ORDER;
            private static final ImmutableSortedSet NATURAL_EMPTY_SET;
            final transient Comparator comparator;
            transient ImmutableSortedSet descendingSet;

            static 
            {
/*  97*/        NATURAL_ORDER = Ordering.natural();
/* 100*/        NATURAL_EMPTY_SET = new EmptyImmutableSortedSet(NATURAL_ORDER);
            }
}
