// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class  extends 
    implements SortedMap
{

            SortedMap sortedMap()
            {
/*1370*/        return (SortedMap)submap;
            }

            public Comparator comparator()
            {
/*1375*/        return sortedMap().comparator();
            }

            public Object firstKey()
            {
/*1380*/        return sortedMap().firstKey();
            }

            public Object lastKey()
            {
/*1385*/        return sortedMap().lastKey();
            }

            public SortedMap headMap(Object obj)
            {
/*1390*/        return new <init>(sortedMap().headMap(obj));
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/*1395*/        return new <init>(sortedMap().subMap(obj, obj1));
            }

            public SortedMap tailMap(Object obj)
            {
/*1400*/        return new <init>(sortedMap().tailMap(obj));
            }

            public SortedSet keySet()
            {
                SortedSet sortedset;
/*1408*/        if((sortedset = sortedKeySet) == null)
/*1409*/            return sortedKeySet = createKeySet();
/*1409*/        else
/*1409*/            return sortedset;
            }

            SortedSet createKeySet()
            {
/*1414*/        return new (AbstractMapBasedMultimap.this, sortedMap());
            }

            public volatile Set keySet()
            {
/*1363*/        return keySet();
            }

            volatile Set createKeySet()
            {
/*1363*/        return createKeySet();
            }

            SortedSet sortedKeySet;
            final AbstractMapBasedMultimap this$0;

            (SortedMap sortedmap)
            {
/*1365*/        this$0 = AbstractMapBasedMultimap.this;
/*1366*/        super(AbstractMapBasedMultimap.this, sortedmap);
            }
}
