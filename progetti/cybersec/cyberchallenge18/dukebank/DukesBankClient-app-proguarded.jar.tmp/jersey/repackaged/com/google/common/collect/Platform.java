// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Platform.java

package jersey.repackaged.com.google.common.collect;

import java.lang.reflect.Array;
import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Sets

final class Platform
{

            static Object[] newArray(Object aobj[], int i)
            {
/*  48*/        return aobj = (Object[])Array.newInstance(((Class) (aobj = ((Object) (aobj)).getClass().getComponentType())), i);
            }

            static Set newSetFromMap(Map map)
            {
/*  58*/        return Collections.newSetFromMap(map);
            }

            static SortedMap mapsTransformEntriesSortedMap(SortedMap sortedmap, Maps.EntryTransformer entrytransformer)
            {
/*  74*/        if(sortedmap instanceof NavigableMap)
/*  74*/            return Maps.transformEntries((NavigableMap)sortedmap, entrytransformer);
/*  74*/        else
/*  74*/            return Maps.transformEntriesIgnoreNavigable(sortedmap, entrytransformer);
            }

            static SortedMap mapsAsMapSortedSet(SortedSet sortedset, Function function)
            {
/*  81*/        if(sortedset instanceof NavigableSet)
/*  81*/            return Maps.asMap((NavigableSet)sortedset, function);
/*  81*/        else
/*  81*/            return Maps.asMapSortedIgnoreNavigable(sortedset, function);
            }

            static SortedSet setsFilterSortedSet(SortedSet sortedset, Predicate predicate)
            {
/*  88*/        if(sortedset instanceof NavigableSet)
/*  88*/            return Sets.filter((NavigableSet)sortedset, predicate);
/*  88*/        else
/*  88*/            return Sets.filterSortedIgnoreNavigable(sortedset, predicate);
            }

            static SortedMap mapsFilterSortedMap(SortedMap sortedmap, Predicate predicate)
            {
/*  95*/        if(sortedmap instanceof NavigableMap)
/*  95*/            return Maps.filterEntries((NavigableMap)sortedmap, predicate);
/*  95*/        else
/*  95*/            return Maps.filterSortedIgnoreNavigable(sortedmap, predicate);
            }
}
