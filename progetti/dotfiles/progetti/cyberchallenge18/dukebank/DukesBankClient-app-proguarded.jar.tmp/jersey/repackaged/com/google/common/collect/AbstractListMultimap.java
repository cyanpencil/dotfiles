// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractListMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, ImmutableList, ListMultimap

abstract class AbstractListMultimap extends AbstractMapBasedMultimap
    implements ListMultimap
{

            protected AbstractListMultimap(Map map)
            {
/*  46*/        super(map);
            }

            abstract List createCollection();

            List createUnmodifiableEmptyCollection()
            {
/*  53*/        return ImmutableList.of();
            }

            public List get(Object obj)
            {
/*  66*/        return (List)super.get(obj);
            }

            public List removeAll(Object obj)
            {
/*  77*/        return (List)super.removeAll(obj);
            }

            public List replaceValues(Object obj, Iterable iterable)
            {
/*  89*/        return (List)super.replaceValues(obj, iterable);
            }

            public boolean put(Object obj, Object obj1)
            {
/* 100*/        return super.put(obj, obj1);
            }

            public Map asMap()
            {
/* 110*/        return super.asMap();
            }

            public boolean equals(Object obj)
            {
/* 121*/        return super.equals(obj);
            }

            public volatile Collection get(Object obj)
            {
/*  36*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  36*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  36*/        return replaceValues(obj, iterable);
            }

            volatile Collection createCollection()
            {
/*  36*/        return createCollection();
            }

            volatile Collection createUnmodifiableEmptyCollection()
            {
/*  36*/        return createUnmodifiableEmptyCollection();
            }
}
