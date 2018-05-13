// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimaps, SortedSetMultimap, SetMultimap, Multimap

static class  extends 
    implements SortedSetMultimap
{

            public SortedSetMultimap _mthdelegate()
            {
/* 646*/        return (SortedSetMultimap)super.te();
            }

            public SortedSet get(Object obj)
            {
/* 649*/        return Collections.unmodifiableSortedSet(_mthdelegate().get(obj));
            }

            public SortedSet removeAll(Object obj)
            {
/* 652*/        throw new UnsupportedOperationException();
            }

            public SortedSet replaceValues(Object obj, Iterable iterable)
            {
/* 656*/        throw new UnsupportedOperationException();
            }

            public Comparator valueComparator()
            {
/* 660*/        return _mthdelegate().valueComparator();
            }

            public volatile Set replaceValues(Object obj, Iterable iterable)
            {
/* 640*/        return replaceValues(obj, iterable);
            }

            public volatile Set removeAll(Object obj)
            {
/* 640*/        return removeAll(obj);
            }

            public volatile Set get(Object obj)
            {
/* 640*/        return get(obj);
            }

            public volatile SetMultimap _mthdelegate()
            {
/* 640*/        return _mthdelegate();
            }

            public volatile Collection get(Object obj)
            {
/* 640*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/* 640*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 640*/        return replaceValues(obj, iterable);
            }

            public volatile Multimap _mthdelegate()
            {
/* 640*/        return _mthdelegate();
            }

            public volatile Object _mthdelegate()
            {
/* 640*/        return _mthdelegate();
            }

            Y(SortedSetMultimap sortedsetmultimap)
            {
/* 643*/        super(sortedsetmultimap);
            }
}
