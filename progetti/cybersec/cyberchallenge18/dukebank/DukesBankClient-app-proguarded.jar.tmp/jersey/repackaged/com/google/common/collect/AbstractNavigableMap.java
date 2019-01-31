// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractNavigableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps

abstract class AbstractNavigableMap extends AbstractMap
    implements NavigableMap
{
    final class DescendingMap extends Maps.DescendingMap
    {

                final NavigableMap forward()
                {
/* 189*/            return AbstractNavigableMap.this;
                }

                final Iterator entryIterator()
                {
/* 194*/            return descendingEntryIterator();
                }

                final AbstractNavigableMap this$0;

                private DescendingMap()
                {
/* 186*/            this$0 = AbstractNavigableMap.this;
/* 186*/            super();
                }

    }


            AbstractNavigableMap()
            {
            }

            public java.util.Map.Entry firstEntry()
            {
/*  44*/        return (java.util.Map.Entry)Iterators.getNext(entryIterator(), null);
            }

            public java.util.Map.Entry lastEntry()
            {
/*  50*/        return (java.util.Map.Entry)Iterators.getNext(descendingEntryIterator(), null);
            }

            public java.util.Map.Entry pollFirstEntry()
            {
/*  56*/        return (java.util.Map.Entry)Iterators.pollNext(entryIterator());
            }

            public java.util.Map.Entry pollLastEntry()
            {
/*  62*/        return (java.util.Map.Entry)Iterators.pollNext(descendingEntryIterator());
            }

            public Object firstKey()
            {
                java.util.Map.Entry entry;
/*  67*/        if((entry = firstEntry()) == null)
/*  69*/            throw new NoSuchElementException();
/*  71*/        else
/*  71*/            return entry.getKey();
            }

            public Object lastKey()
            {
                java.util.Map.Entry entry;
/*  77*/        if((entry = lastEntry()) == null)
/*  79*/            throw new NoSuchElementException();
/*  81*/        else
/*  81*/            return entry.getKey();
            }

            public java.util.Map.Entry lowerEntry(Object obj)
            {
/*  88*/        return headMap(obj, false).lastEntry();
            }

            public java.util.Map.Entry floorEntry(Object obj)
            {
/*  94*/        return headMap(obj, true).lastEntry();
            }

            public java.util.Map.Entry ceilingEntry(Object obj)
            {
/* 100*/        return tailMap(obj, true).firstEntry();
            }

            public java.util.Map.Entry higherEntry(Object obj)
            {
/* 106*/        return tailMap(obj, false).firstEntry();
            }

            public Object lowerKey(Object obj)
            {
/* 111*/        return Maps.keyOrNull(lowerEntry(obj));
            }

            public Object floorKey(Object obj)
            {
/* 116*/        return Maps.keyOrNull(floorEntry(obj));
            }

            public Object ceilingKey(Object obj)
            {
/* 121*/        return Maps.keyOrNull(ceilingEntry(obj));
            }

            public Object higherKey(Object obj)
            {
/* 126*/        return Maps.keyOrNull(higherEntry(obj));
            }

            abstract Iterator entryIterator();

            abstract Iterator descendingEntryIterator();

            public SortedMap subMap(Object obj, Object obj1)
            {
/* 135*/        return subMap(obj, true, obj1, false);
            }

            public SortedMap headMap(Object obj)
            {
/* 140*/        return headMap(obj, false);
            }

            public SortedMap tailMap(Object obj)
            {
/* 145*/        return tailMap(obj, true);
            }

            public NavigableSet navigableKeySet()
            {
/* 150*/        return new Maps.NavigableKeySet(this);
            }

            public Set keySet()
            {
/* 155*/        return navigableKeySet();
            }

            public Set entrySet()
            {
/* 163*/        return new Maps.EntrySet() {

                    Map map()
                    {
/* 166*/                return AbstractNavigableMap.this;
                    }

                    public Iterator iterator()
                    {
/* 171*/                return entryIterator();
                    }

                    final AbstractNavigableMap this$0;

                    
                    {
/* 163*/                this$0 = AbstractNavigableMap.this;
/* 163*/                super();
                    }
        };
            }

            public NavigableSet descendingKeySet()
            {
/* 178*/        return descendingMap().navigableKeySet();
            }

            public NavigableMap descendingMap()
            {
/* 183*/        return new DescendingMap();
            }
}
