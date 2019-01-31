// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

static class  extends 
    implements SortedMap
{

            protected SortedMap fromMap()
            {
/*1940*/        return (SortedMap)fromMap;
            }

            public Comparator comparator()
            {
/*1949*/        return fromMap().comparator();
            }

            public Object firstKey()
            {
/*1953*/        return fromMap().firstKey();
            }

            public SortedMap headMap(Object obj)
            {
/*1957*/        return Maps.transformEntries(fromMap().headMap(obj), transformer);
            }

            public Object lastKey()
            {
/*1961*/        return fromMap().lastKey();
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/*1965*/        return Maps.transformEntries(fromMap().subMap(obj, obj1), transformer);
            }

            public SortedMap tailMap(Object obj)
            {
/*1970*/        return Maps.transformEntries(fromMap().tailMap(obj), transformer);
            }

            (SortedMap sortedmap,  )
            {
/*1945*/        super(sortedmap, );
            }
}
