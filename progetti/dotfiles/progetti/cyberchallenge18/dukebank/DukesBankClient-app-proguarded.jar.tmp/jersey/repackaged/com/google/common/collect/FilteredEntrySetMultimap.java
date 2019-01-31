// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntrySetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, FilteredSetMultimap, SetMultimap, Sets, 
//            Multimap

final class FilteredEntrySetMultimap extends FilteredEntryMultimap
    implements FilteredSetMultimap
{

            FilteredEntrySetMultimap(SetMultimap setmultimap, Predicate predicate)
            {
/*  35*/        super(setmultimap, predicate);
            }

            public final SetMultimap unfiltered()
            {
/*  40*/        return (SetMultimap)unfiltered;
            }

            public final Set get(Object obj)
            {
/*  45*/        return (Set)super.get(obj);
            }

            public final Set removeAll(Object obj)
            {
/*  50*/        return (Set)super.removeAll(obj);
            }

            public final Set replaceValues(Object obj, Iterable iterable)
            {
/*  55*/        return (Set)super.replaceValues(obj, iterable);
            }

            final Set createEntries()
            {
/*  60*/        return Sets.filter(unfiltered().entries(), entryPredicate());
            }

            public final Set entries()
            {
/*  65*/        return (Set)super.entries();
            }

            final volatile Collection createEntries()
            {
/*  30*/        return createEntries();
            }

            public final volatile Collection get(Object obj)
            {
/*  30*/        return get(obj);
            }

            public final volatile Collection removeAll(Object obj)
            {
/*  30*/        return removeAll(obj);
            }

            public final volatile Multimap unfiltered()
            {
/*  30*/        return unfiltered();
            }

            public final volatile Collection entries()
            {
/*  30*/        return entries();
            }

            public final volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  30*/        return replaceValues(obj, iterable);
            }
}
