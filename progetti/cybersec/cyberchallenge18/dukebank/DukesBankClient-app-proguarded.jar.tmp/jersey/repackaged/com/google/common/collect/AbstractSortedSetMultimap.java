// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractSortedSetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSetMultimap, ImmutableSortedSet, SortedSetMultimap

abstract class AbstractSortedSetMultimap extends AbstractSetMultimap
    implements SortedSetMultimap
{

            protected AbstractSortedSetMultimap(Map map)
            {
/*  47*/        super(map);
            }

            abstract SortedSet createCollection();

            SortedSet createUnmodifiableEmptyCollection()
            {
                java.util.Comparator comparator;
/*  55*/        if((comparator = valueComparator()) == null)
/*  57*/            return Collections.unmodifiableSortedSet(createCollection());
/*  59*/        else
/*  59*/            return ImmutableSortedSet.emptySet(valueComparator());
            }

            public SortedSet get(Object obj)
            {
/*  78*/        return (SortedSet)super.get(obj);
            }

            public SortedSet removeAll(Object obj)
            {
/*  90*/        return (SortedSet)super.removeAll(obj);
            }

            public SortedSet replaceValues(Object obj, Iterable iterable)
            {
/* 105*/        return (SortedSet)super.replaceValues(obj, iterable);
            }

            public Map asMap()
            {
/* 123*/        return super.asMap();
            }

            public Collection values()
            {
/* 133*/        return super.values();
            }

            public volatile Set replaceValues(Object obj, Iterable iterable)
            {
/*  37*/        return replaceValues(obj, iterable);
            }

            public volatile Set removeAll(Object obj)
            {
/*  37*/        return removeAll(obj);
            }

            public volatile Set get(Object obj)
            {
/*  37*/        return get(obj);
            }

            volatile Set createUnmodifiableEmptyCollection()
            {
/*  37*/        return createUnmodifiableEmptyCollection();
            }

            volatile Set createCollection()
            {
/*  37*/        return createCollection();
            }

            public volatile Collection get(Object obj)
            {
/*  37*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  37*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  37*/        return replaceValues(obj, iterable);
            }

            volatile Collection createCollection()
            {
/*  37*/        return createCollection();
            }

            volatile Collection createUnmodifiableEmptyCollection()
            {
/*  37*/        return createUnmodifiableEmptyCollection();
            }

            private static final long serialVersionUID = 0x5faae81de71c4a4L;
}
