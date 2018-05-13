// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

static class  extends 
    implements SortedMap
{

            SortedSet backingSet()
            {
/* 850*/        return (SortedSet)super.gSet();
            }

            public Comparator comparator()
            {
/* 855*/        return backingSet().comparator();
            }

            public Set keySet()
            {
/* 860*/        return Maps.access$300(backingSet());
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/* 865*/        return Maps.asMap(backingSet().subSet(obj, obj1), function);
            }

            public SortedMap headMap(Object obj)
            {
/* 870*/        return Maps.asMap(backingSet().headSet(obj), function);
            }

            public SortedMap tailMap(Object obj)
            {
/* 875*/        return Maps.asMap(backingSet().tailSet(obj), function);
            }

            public Object firstKey()
            {
/* 880*/        return backingSet().first();
            }

            public Object lastKey()
            {
/* 885*/        return backingSet().last();
            }

            volatile Set backingSet()
            {
/* 841*/        return backingSet();
            }

            (SortedSet sortedset, Function function)
            {
/* 845*/        super(sortedset, function);
            }
}
