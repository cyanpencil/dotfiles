// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.MoreObjects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMultimap, EmptyImmutableSetMultimap, ImmutableMap, ImmutableSet, 
//            ImmutableSortedSet, SetMultimap, ImmutableCollection, UnmodifiableIterator

public class ImmutableSetMultimap extends ImmutableMultimap
    implements SetMultimap
{
    static final class EntrySet extends ImmutableSet
    {

                public final boolean contains(Object obj)
                {
/* 434*/            if(obj instanceof java.util.Map.Entry)
                    {
/* 435*/                obj = (java.util.Map.Entry)obj;
/* 436*/                return multimap.containsEntry(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/* 438*/                return false;
                    }
                }

                public final int size()
                {
/* 443*/            return multimap.size();
                }

                public final UnmodifiableIterator iterator()
                {
/* 448*/            return multimap.entryIterator();
                }

                final boolean isPartialView()
                {
/* 453*/            return false;
                }

                public final volatile Iterator iterator()
                {
/* 425*/            return iterator();
                }

                private final transient ImmutableSetMultimap multimap;

                EntrySet(ImmutableSetMultimap immutablesetmultimap)
                {
/* 429*/            multimap = immutablesetmultimap;
                }
    }


            public static ImmutableSetMultimap of()
            {
/*  73*/        return EmptyImmutableSetMultimap.INSTANCE;
            }

            ImmutableSetMultimap(ImmutableMap immutablemap, int i, Comparator comparator)
            {
/* 345*/        super(immutablemap, i);
/* 346*/        emptySet = emptySet(comparator);
            }

            public ImmutableSet get(Object obj)
            {
/* 359*/        return (ImmutableSet)MoreObjects.firstNonNull(obj = (ImmutableSet)map.get(obj), emptySet);
            }

            /**
             * @deprecated Method removeAll is deprecated
             */

            public ImmutableSet removeAll(Object obj)
            {
/* 397*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method replaceValues is deprecated
             */

            public ImmutableSet replaceValues(Object obj, Iterable iterable)
            {
/* 408*/        throw new UnsupportedOperationException();
            }

            public ImmutableSet entries()
            {
                ImmutableSet immutableset;
/* 419*/        if((immutableset = entries) == null)
/* 420*/            return entries = new EntrySet(this);
/* 420*/        else
/* 420*/            return immutableset;
            }

            private static ImmutableSet emptySet(Comparator comparator)
            {
/* 467*/        if(comparator == null)
/* 467*/            return ImmutableSet.of();
/* 467*/        else
/* 467*/            return ImmutableSortedSet.emptySet(comparator);
            }

            public volatile ImmutableCollection entries()
            {
/*  64*/        return entries();
            }

            public volatile ImmutableCollection get(Object obj)
            {
/*  64*/        return get(obj);
            }

            public volatile ImmutableCollection replaceValues(Object obj, Iterable iterable)
            {
/*  64*/        return replaceValues(obj, iterable);
            }

            public volatile ImmutableCollection removeAll(Object obj)
            {
/*  64*/        return removeAll(obj);
            }

            public volatile Collection entries()
            {
/*  64*/        return entries();
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  64*/        return replaceValues(obj, iterable);
            }

            public volatile Collection get(Object obj)
            {
/*  64*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  64*/        return removeAll(obj);
            }

            public volatile Set entries()
            {
/*  64*/        return entries();
            }

            public volatile Set replaceValues(Object obj, Iterable iterable)
            {
/*  64*/        return replaceValues(obj, iterable);
            }

            public volatile Set removeAll(Object obj)
            {
/*  64*/        return removeAll(obj);
            }

            public volatile Set get(Object obj)
            {
/*  64*/        return get(obj);
            }

            private final transient ImmutableSet emptySet;
            private transient ImmutableSet entries;
}
