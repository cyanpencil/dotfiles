// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class  extends 
    implements SortedSet
{

            SortedMap sortedMap()
            {
/* 988*/        return (SortedMap)super.();
            }

            public Comparator comparator()
            {
/* 993*/        return sortedMap().comparator();
            }

            public Object first()
            {
/* 998*/        return sortedMap().firstKey();
            }

            public SortedSet headSet(Object obj)
            {
/*1003*/        return new <init>(sortedMap().headMap(obj));
            }

            public Object last()
            {
/*1008*/        return sortedMap().lastKey();
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/*1013*/        return new <init>(sortedMap().subMap(obj, obj1));
            }

            public SortedSet tailSet(Object obj)
            {
/*1018*/        return new <init>(sortedMap().tailMap(obj));
            }

            final AbstractMapBasedMultimap this$0;

            (SortedMap sortedmap)
            {
/* 983*/        this$0 = AbstractMapBasedMultimap.this;
/* 984*/        super(AbstractMapBasedMultimap.this, sortedmap);
            }
}
