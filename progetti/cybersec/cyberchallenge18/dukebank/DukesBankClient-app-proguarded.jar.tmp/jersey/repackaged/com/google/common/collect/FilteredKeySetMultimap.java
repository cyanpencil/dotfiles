// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeySetMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredKeyMultimap, FilteredSetMultimap, SetMultimap, Multimap, 
//            Sets

final class FilteredKeySetMultimap extends FilteredKeyMultimap
    implements FilteredSetMultimap
{
    class EntrySet extends FilteredKeyMultimap.Entries
        implements Set
    {

                public int hashCode()
                {
/*  73*/            return Sets.hashCodeImpl(this);
                }

                public boolean equals(Object obj)
                {
/*  78*/            return Sets.equalsImpl(this, obj);
                }

                final FilteredKeySetMultimap this$0;

                EntrySet()
                {
/*  70*/            this$0 = FilteredKeySetMultimap.this;
/*  70*/            super(FilteredKeySetMultimap.this);
                }
    }


            FilteredKeySetMultimap(SetMultimap setmultimap, Predicate predicate)
            {
/*  37*/        super(setmultimap, predicate);
            }

            public final SetMultimap unfiltered()
            {
/*  42*/        return (SetMultimap)unfiltered;
            }

            public final Set get(Object obj)
            {
/*  47*/        return (Set)super.get(obj);
            }

            public final Set removeAll(Object obj)
            {
/*  52*/        return (Set)super.removeAll(obj);
            }

            public final Set replaceValues(Object obj, Iterable iterable)
            {
/*  57*/        return (Set)super.replaceValues(obj, iterable);
            }

            public final Set entries()
            {
/*  62*/        return (Set)super.entries();
            }

            final Set createEntries()
            {
/*  67*/        return new EntrySet();
            }

            final volatile Collection createEntries()
            {
/*  32*/        return createEntries();
            }

            public final volatile Collection get(Object obj)
            {
/*  32*/        return get(obj);
            }

            public final volatile Collection removeAll(Object obj)
            {
/*  32*/        return removeAll(obj);
            }

            public final volatile Multimap unfiltered()
            {
/*  32*/        return unfiltered();
            }

            public final volatile Collection entries()
            {
/*  32*/        return entries();
            }

            public final volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  32*/        return replaceValues(obj, iterable);
            }
}
