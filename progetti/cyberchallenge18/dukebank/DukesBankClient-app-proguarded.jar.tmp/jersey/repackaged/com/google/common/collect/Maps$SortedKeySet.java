// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

static class  extends 
    implements SortedSet
{

            SortedMap map()
            {
/*3538*/        return (SortedMap)super.();
            }

            public Comparator comparator()
            {
/*3543*/        return map().comparator();
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/*3548*/        return new <init>(map().subMap(obj, obj1));
            }

            public SortedSet headSet(Object obj)
            {
/*3553*/        return new <init>(map().headMap(obj));
            }

            public SortedSet tailSet(Object obj)
            {
/*3558*/        return new <init>(map().tailMap(obj));
            }

            public Object first()
            {
/*3563*/        return map().firstKey();
            }

            public Object last()
            {
/*3568*/        return map().lastKey();
            }

            volatile Map map()
            {
/*3531*/        return map();
            }

            (SortedMap sortedmap)
            {
/*3533*/        super(sortedmap);
            }
}
