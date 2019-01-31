// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractSortedKeySortedSetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSortedSetMultimap

abstract class AbstractSortedKeySortedSetMultimap extends AbstractSortedSetMultimap
{

            AbstractSortedKeySortedSetMultimap(SortedMap sortedmap)
            {
/*  38*/        super(sortedmap);
            }

            public SortedMap asMap()
            {
/*  43*/        return (SortedMap)super.asMap();
            }

            SortedMap backingMap()
            {
/*  48*/        return (SortedMap)super.backingMap();
            }

            public SortedSet keySet()
            {
/*  53*/        return (SortedSet)super.keySet();
            }

            public volatile Map asMap()
            {
/*  34*/        return asMap();
            }

            public volatile Set keySet()
            {
/*  34*/        return keySet();
            }

            volatile Map backingMap()
            {
/*  34*/        return backingMap();
            }
}
