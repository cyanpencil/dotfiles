// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredMultimapValues.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredMultimap, Iterables, Maps, Multimap

final class FilteredMultimapValues extends AbstractCollection
{

            FilteredMultimapValues(FilteredMultimap filteredmultimap)
            {
/*  42*/        multimap = (FilteredMultimap)Preconditions.checkNotNull(filteredmultimap);
            }

            public final Iterator iterator()
            {
/*  47*/        return Maps.valueIterator(multimap.entries().iterator());
            }

            public final boolean contains(Object obj)
            {
/*  52*/        return multimap.containsValue(obj);
            }

            public final int size()
            {
/*  57*/        return multimap.size();
            }

            public final boolean remove(Object obj)
            {
/*  62*/        Predicate predicate = multimap.entryPredicate();
/*  63*/        for(Iterator iterator1 = multimap.unfiltered().entries().iterator(); iterator1.hasNext();)
                {
/*  65*/            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
/*  66*/            if(predicate.apply(entry) && Objects.equal(entry.getValue(), obj))
                    {
/*  67*/                iterator1.remove();
/*  68*/                return true;
                    }
                }

/*  71*/        return false;
            }

            public final boolean removeAll(Collection collection)
            {
/*  76*/        return Iterables.removeIf(multimap.unfiltered().entries(), Predicates.and(multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.in(collection))));
            }

            public final boolean retainAll(Collection collection)
            {
/*  84*/        return Iterables.removeIf(multimap.unfiltered().entries(), Predicates.and(multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection)))));
            }

            public final void clear()
            {
/*  92*/        multimap.clear();
            }

            private final FilteredMultimap multimap;
}
