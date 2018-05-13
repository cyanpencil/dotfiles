// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableSortedSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedSet, ImmutableList, Iterators, Ordering, 
//            UnmodifiableIterator

class EmptyImmutableSortedSet extends ImmutableSortedSet
{

            EmptyImmutableSortedSet(Comparator comparator)
            {
/*  38*/        super(comparator);
            }

            public int size()
            {
/*  43*/        return 0;
            }

            public boolean isEmpty()
            {
/*  47*/        return true;
            }

            public boolean contains(Object obj)
            {
/*  51*/        return false;
            }

            public boolean containsAll(Collection collection)
            {
/*  55*/        return collection.isEmpty();
            }

            public UnmodifiableIterator iterator()
            {
/*  59*/        return Iterators.emptyIterator();
            }

            public UnmodifiableIterator descendingIterator()
            {
/*  64*/        return Iterators.emptyIterator();
            }

            boolean isPartialView()
            {
/*  68*/        return false;
            }

            public ImmutableList asList()
            {
/*  72*/        return ImmutableList.of();
            }

            int copyIntoArray(Object aobj[], int i)
            {
/*  77*/        return i;
            }

            public boolean equals(Object obj)
            {
/*  81*/        if(obj instanceof Set)
/*  82*/            return ((Set) (obj = (Set)obj)).isEmpty();
/*  85*/        else
/*  85*/            return false;
            }

            public int hashCode()
            {
/*  89*/        return 0;
            }

            public String toString()
            {
/*  93*/        return "[]";
            }

            public Object first()
            {
/*  98*/        throw new NoSuchElementException();
            }

            public Object last()
            {
/* 103*/        throw new NoSuchElementException();
            }

            ImmutableSortedSet headSetImpl(Object obj, boolean flag)
            {
/* 108*/        return this;
            }

            ImmutableSortedSet subSetImpl(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 114*/        return this;
            }

            ImmutableSortedSet tailSetImpl(Object obj, boolean flag)
            {
/* 119*/        return this;
            }

            ImmutableSortedSet createDescendingSet()
            {
/* 128*/        return new EmptyImmutableSortedSet(Ordering.from(comparator).reverse());
            }

            public volatile Iterator descendingIterator()
            {
/*  34*/        return descendingIterator();
            }

            public volatile Iterator iterator()
            {
/*  34*/        return iterator();
            }
}
