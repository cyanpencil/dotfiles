// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractSetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, ImmutableSet, SetMultimap

abstract class AbstractSetMultimap extends AbstractMapBasedMultimap
    implements SetMultimap
{

            protected AbstractSetMultimap(Map map)
            {
/*  44*/        super(map);
            }

            abstract Set createCollection();

            Set createUnmodifiableEmptyCollection()
            {
/*  50*/        return ImmutableSet.of();
            }

            public Set get(Object obj)
            {
/*  63*/        return (Set)super.get(obj);
            }

            public Set entries()
            {
/*  74*/        return (Set)super.entries();
            }

            public Set removeAll(Object obj)
            {
/*  85*/        return (Set)super.removeAll(obj);
            }

            public Set replaceValues(Object obj, Iterable iterable)
            {
/*  99*/        return (Set)super.replaceValues(obj, iterable);
            }

            public Map asMap()
            {
/* 109*/        return super.asMap();
            }

            public boolean put(Object obj, Object obj1)
            {
/* 121*/        return super.put(obj, obj1);
            }

            public boolean equals(Object obj)
            {
/* 132*/        return super.equals(obj);
            }

            public volatile Collection entries()
            {
/*  34*/        return entries();
            }

            public volatile Collection get(Object obj)
            {
/*  34*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  34*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  34*/        return replaceValues(obj, iterable);
            }

            volatile Collection createCollection()
            {
/*  34*/        return createCollection();
            }

            volatile Collection createUnmodifiableEmptyCollection()
            {
/*  34*/        return createUnmodifiableEmptyCollection();
            }

            private static final long serialVersionUID = 0x67226fd4cd0928d8L;
}
