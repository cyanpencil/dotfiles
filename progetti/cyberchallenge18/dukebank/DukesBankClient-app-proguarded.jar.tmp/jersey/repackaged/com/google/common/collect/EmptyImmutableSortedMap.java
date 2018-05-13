// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableSortedMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedMap, ImmutableList, ImmutableSet, ImmutableSetMultimap, 
//            ImmutableSortedSet, Ordering, ImmutableCollection

final class EmptyImmutableSortedMap extends ImmutableSortedMap
{

            EmptyImmutableSortedMap(Comparator comparator)
            {
/*  37*/        keySet = ImmutableSortedSet.emptySet(comparator);
            }

            EmptyImmutableSortedMap(Comparator comparator, ImmutableSortedMap immutablesortedmap)
            {
/*  42*/        super(immutablesortedmap);
/*  43*/        keySet = ImmutableSortedSet.emptySet(comparator);
            }

            public final Object get(Object obj)
            {
/*  48*/        return null;
            }

            public final ImmutableSortedSet keySet()
            {
/*  53*/        return keySet;
            }

            public final int size()
            {
/*  58*/        return 0;
            }

            public final boolean isEmpty()
            {
/*  63*/        return true;
            }

            public final ImmutableCollection values()
            {
/*  68*/        return ImmutableList.of();
            }

            public final String toString()
            {
/*  73*/        return "{}";
            }

            final boolean isPartialView()
            {
/*  78*/        return false;
            }

            public final ImmutableSet entrySet()
            {
/*  83*/        return ImmutableSet.of();
            }

            final ImmutableSet createEntrySet()
            {
/*  88*/        throw new AssertionError("should never be called");
            }

            public final ImmutableSetMultimap asMultimap()
            {
/*  93*/        return ImmutableSetMultimap.of();
            }

            public final ImmutableSortedMap headMap(Object obj, boolean flag)
            {
/*  98*/        Preconditions.checkNotNull(obj);
/*  99*/        return this;
            }

            public final ImmutableSortedMap tailMap(Object obj, boolean flag)
            {
/* 104*/        Preconditions.checkNotNull(obj);
/* 105*/        return this;
            }

            final ImmutableSortedMap createDescendingMap()
            {
/* 110*/        return new EmptyImmutableSortedMap(Ordering.from(comparator()).reverse(), this);
            }

            public final volatile NavigableMap tailMap(Object obj, boolean flag)
            {
/*  31*/        return tailMap(obj, flag);
            }

            public final volatile NavigableMap headMap(Object obj, boolean flag)
            {
/*  31*/        return headMap(obj, flag);
            }

            public final volatile Set entrySet()
            {
/*  31*/        return entrySet();
            }

            public final volatile Collection values()
            {
/*  31*/        return values();
            }

            public final volatile Set keySet()
            {
/*  31*/        return keySet();
            }

            public final volatile ImmutableSet keySet()
            {
/*  31*/        return keySet();
            }

            private final transient ImmutableSortedSet keySet;
}
