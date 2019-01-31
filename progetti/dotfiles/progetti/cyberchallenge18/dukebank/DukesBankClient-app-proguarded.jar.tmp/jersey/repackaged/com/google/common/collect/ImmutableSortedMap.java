// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSortedMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedMapFauxverideShim, EmptyImmutableSortedMap, ImmutableCollection, ImmutableList, 
//            ImmutableMapEntry, ImmutableSet, ImmutableSortedSet, Maps, 
//            Ordering, ImmutableMap

public abstract class ImmutableSortedMap extends ImmutableSortedMapFauxverideShim
    implements NavigableMap
{
    static class SerializedForm extends ImmutableMap.SerializedForm
    {

                private final Comparator comparator;

                SerializedForm(ImmutableSortedMap immutablesortedmap)
                {
/* 683*/            super(immutablesortedmap);
/* 684*/            comparator = immutablesortedmap.comparator();
                }
    }


            ImmutableSortedMap()
            {
            }

            ImmutableSortedMap(ImmutableSortedMap immutablesortedmap)
            {
/* 416*/        descendingMap = immutablesortedmap;
            }

            public int size()
            {
/* 421*/        return values().size();
            }

            public boolean containsValue(Object obj)
            {
/* 425*/        return values().contains(obj);
            }

            boolean isPartialView()
            {
/* 429*/        return keySet().isPartialView() || values().isPartialView();
            }

            public ImmutableSet entrySet()
            {
/* 437*/        return super.entrySet();
            }

            public abstract ImmutableSortedSet keySet();

            public abstract ImmutableCollection values();

            public Comparator comparator()
            {
/* 459*/        return keySet().comparator();
            }

            public Object firstKey()
            {
/* 464*/        return keySet().first();
            }

            public Object lastKey()
            {
/* 469*/        return keySet().last();
            }

            public ImmutableSortedMap headMap(Object obj)
            {
/* 484*/        return headMap(obj, false);
            }

            public abstract ImmutableSortedMap headMap(Object obj, boolean flag);

            public ImmutableSortedMap subMap(Object obj, Object obj1)
            {
/* 517*/        return subMap(obj, true, obj1, false);
            }

            public ImmutableSortedMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 538*/        Preconditions.checkNotNull(obj);
/* 539*/        Preconditions.checkNotNull(obj1);
/* 540*/        Preconditions.checkArgument(comparator().compare(obj, obj1) <= 0, "expected fromKey <= toKey but %s > %s", new Object[] {
/* 540*/            obj, obj1
                });
/* 542*/        return headMap(obj1, flag1).tailMap(obj, flag);
            }

            public ImmutableSortedMap tailMap(Object obj)
            {
/* 557*/        return tailMap(obj, true);
            }

            public abstract ImmutableSortedMap tailMap(Object obj, boolean flag);

            public java.util.Map.Entry lowerEntry(Object obj)
            {
/* 578*/        return headMap(obj, false).lastEntry();
            }

            public Object lowerKey(Object obj)
            {
/* 583*/        return Maps.keyOrNull(lowerEntry(obj));
            }

            public java.util.Map.Entry floorEntry(Object obj)
            {
/* 588*/        return headMap(obj, true).lastEntry();
            }

            public Object floorKey(Object obj)
            {
/* 593*/        return Maps.keyOrNull(floorEntry(obj));
            }

            public java.util.Map.Entry ceilingEntry(Object obj)
            {
/* 598*/        return tailMap(obj, true).firstEntry();
            }

            public Object ceilingKey(Object obj)
            {
/* 603*/        return Maps.keyOrNull(ceilingEntry(obj));
            }

            public java.util.Map.Entry higherEntry(Object obj)
            {
/* 608*/        return tailMap(obj, false).firstEntry();
            }

            public Object higherKey(Object obj)
            {
/* 613*/        return Maps.keyOrNull(higherEntry(obj));
            }

            public java.util.Map.Entry firstEntry()
            {
/* 618*/        if(isEmpty())
/* 618*/            return null;
/* 618*/        else
/* 618*/            return (java.util.Map.Entry)entrySet().asList().get(0);
            }

            public java.util.Map.Entry lastEntry()
            {
/* 623*/        if(isEmpty())
/* 623*/            return null;
/* 623*/        else
/* 623*/            return (java.util.Map.Entry)entrySet().asList().get(size() - 1);
            }

            /**
             * @deprecated Method pollFirstEntry is deprecated
             */

            public final java.util.Map.Entry pollFirstEntry()
            {
/* 635*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method pollLastEntry is deprecated
             */

            public final java.util.Map.Entry pollLastEntry()
            {
/* 647*/        throw new UnsupportedOperationException();
            }

            public ImmutableSortedMap descendingMap()
            {
                ImmutableSortedMap immutablesortedmap;
/* 654*/        if((immutablesortedmap = descendingMap) == null)
/* 656*/            immutablesortedmap = descendingMap = createDescendingMap();
/* 658*/        return immutablesortedmap;
            }

            abstract ImmutableSortedMap createDescendingMap();

            public ImmutableSortedSet navigableKeySet()
            {
/* 665*/        return keySet();
            }

            public ImmutableSortedSet descendingKeySet()
            {
/* 670*/        return keySet().descendingSet();
            }

            Object writeReplace()
            {
/* 694*/        return new SerializedForm(this);
            }

            public volatile ImmutableSet keySet()
            {
/*  58*/        return keySet();
            }

            public volatile Set entrySet()
            {
/*  58*/        return entrySet();
            }

            public volatile Collection values()
            {
/*  58*/        return values();
            }

            public volatile Set keySet()
            {
/*  58*/        return keySet();
            }

            public volatile SortedMap tailMap(Object obj)
            {
/*  58*/        return tailMap(obj);
            }

            public volatile SortedMap headMap(Object obj)
            {
/*  58*/        return headMap(obj);
            }

            public volatile SortedMap subMap(Object obj, Object obj1)
            {
/*  58*/        return subMap(obj, obj1);
            }

            public volatile NavigableMap tailMap(Object obj, boolean flag)
            {
/*  58*/        return tailMap(obj, flag);
            }

            public volatile NavigableMap headMap(Object obj, boolean flag)
            {
/*  58*/        return headMap(obj, flag);
            }

            public volatile NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*  58*/        return subMap(obj, flag, obj1, flag1);
            }

            public volatile NavigableSet descendingKeySet()
            {
/*  58*/        return descendingKeySet();
            }

            public volatile NavigableSet navigableKeySet()
            {
/*  58*/        return navigableKeySet();
            }

            public volatile NavigableMap descendingMap()
            {
/*  58*/        return descendingMap();
            }

            private static final Comparator NATURAL_ORDER;
            private static final ImmutableSortedMap NATURAL_EMPTY_MAP;
            private transient ImmutableSortedMap descendingMap;

            static 
            {
/*  65*/        NATURAL_ORDER = Ordering.natural();
/*  67*/        NATURAL_EMPTY_MAP = new EmptyImmutableSortedMap(NATURAL_ORDER);
            }
}
