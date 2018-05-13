// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Iterators

class it> extends it>
    implements NavigableSet
{

            NavigableMap sortedMap()
            {
/*1030*/        return (NavigableMap)super.tedMap();
            }

            public Object lower(Object obj)
            {
/*1035*/        return sortedMap().lowerKey(obj);
            }

            public Object floor(Object obj)
            {
/*1040*/        return sortedMap().floorKey(obj);
            }

            public Object ceiling(Object obj)
            {
/*1045*/        return sortedMap().ceilingKey(obj);
            }

            public Object higher(Object obj)
            {
/*1050*/        return sortedMap().higherKey(obj);
            }

            public Object pollFirst()
            {
/*1055*/        return Iterators.pollNext(iterator());
            }

            public Object pollLast()
            {
/*1060*/        return Iterators.pollNext(descendingIterator());
            }

            public NavigableSet descendingSet()
            {
/*1065*/        return new <init>(sortedMap().descendingMap());
            }

            public Iterator descendingIterator()
            {
/*1070*/        return descendingSet().iterator();
            }

            public NavigableSet headSet(Object obj)
            {
/*1075*/        return headSet(obj, false);
            }

            public NavigableSet headSet(Object obj, boolean flag)
            {
/*1080*/        return new <init>(sortedMap().headMap(obj, flag));
            }

            public NavigableSet subSet(Object obj, Object obj1)
            {
/*1085*/        return subSet(obj, true, obj1, false);
            }

            public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*1091*/        return new <init>(sortedMap().subMap(obj, flag, obj1, flag1));
            }

            public NavigableSet tailSet(Object obj)
            {
/*1097*/        return tailSet(obj, true);
            }

            public NavigableSet tailSet(Object obj, boolean flag)
            {
/*1102*/        return new <init>(sortedMap().tailMap(obj, flag));
            }

            public volatile SortedSet tailSet(Object obj)
            {
/*1022*/        return tailSet(obj);
            }

            public volatile SortedSet subSet(Object obj, Object obj1)
            {
/*1022*/        return subSet(obj, obj1);
            }

            public volatile SortedSet headSet(Object obj)
            {
/*1022*/        return headSet(obj);
            }

            volatile SortedMap sortedMap()
            {
/*1022*/        return sortedMap();
            }

            final AbstractMapBasedMultimap this$0;

            (NavigableMap navigablemap)
            {
/*1024*/        this$0 = AbstractMapBasedMultimap.this;
/*1025*/        super(AbstractMapBasedMultimap.this, navigablemap);
            }
}
