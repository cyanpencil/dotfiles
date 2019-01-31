// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

static class  extends 
    implements SortedMap
{
    class SortedKeySet extends Maps.FilteredEntryMap.KeySet
        implements SortedSet
    {

                public Comparator comparator()
                {
/*2805*/            return sortedMap().comparator();
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/*2810*/            return (SortedSet)subMap(obj, obj1).keySet();
                }

                public SortedSet headSet(Object obj)
                {
/*2815*/            return (SortedSet)headMap(obj).keySet();
                }

                public SortedSet tailSet(Object obj)
                {
/*2820*/            return (SortedSet)tailMap(obj).keySet();
                }

                public Object first()
                {
/*2825*/            return firstKey();
                }

                public Object last()
                {
/*2830*/            return lastKey();
                }

                final Maps.FilteredEntrySortedMap this$0;

                SortedKeySet()
                {
/*2802*/            this$0 = Maps.FilteredEntrySortedMap.this;
/*2802*/            super(Maps.FilteredEntrySortedMap.this);
                }
    }


            SortedMap sortedMap()
            {
/*2790*/        return (SortedMap)unfiltered;
            }

            public SortedSet keySet()
            {
/*2794*/        return (SortedSet)super.();
            }

            SortedSet createKeySet()
            {
/*2799*/        return new SortedKeySet();
            }

            public Comparator comparator()
            {
/*2835*/        return sortedMap().comparator();
            }

            public Object firstKey()
            {
/*2840*/        return keySet().iterator().next();
            }

            public Object lastKey()
            {
/*2844*/        Object obj = sortedMap();
/*2847*/        do
                {
/*2847*/            obj = ((SortedMap) (obj)).lastKey();
/*2848*/            if(apply(obj, unfiltered.get(obj)))
/*2849*/                return obj;
/*2851*/            obj = sortedMap().headMap(obj);
                } while(true);
            }

            public SortedMap headMap(Object obj)
            {
/*2856*/        return new <init>(sortedMap().headMap(obj), predicate);
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/*2860*/        return new <init>(sortedMap().subMap(obj, obj1), predicate);
            }

            public SortedMap tailMap(Object obj)
            {
/*2865*/        return new <init>(sortedMap().tailMap(obj), predicate);
            }

            volatile Set createKeySet()
            {
/*2781*/        return createKeySet();
            }

            public volatile Set keySet()
            {
/*2781*/        return keySet();
            }

            (SortedMap sortedmap, Predicate predicate)
            {
/*2786*/        super(sortedmap, predicate);
            }
}
