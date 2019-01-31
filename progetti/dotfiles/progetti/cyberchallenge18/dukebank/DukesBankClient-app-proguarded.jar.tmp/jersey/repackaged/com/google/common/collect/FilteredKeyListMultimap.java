// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeyListMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.List;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredKeyMultimap, ListMultimap, Multimap

final class FilteredKeyListMultimap extends FilteredKeyMultimap
    implements ListMultimap
{

            FilteredKeyListMultimap(ListMultimap listmultimap, Predicate predicate)
            {
/*  35*/        super(listmultimap, predicate);
            }

            public final ListMultimap unfiltered()
            {
/*  40*/        return (ListMultimap)super.unfiltered();
            }

            public final List get(Object obj)
            {
/*  45*/        return (List)super.get(obj);
            }

            public final List removeAll(Object obj)
            {
/*  50*/        return (List)super.removeAll(obj);
            }

            public final List replaceValues(Object obj, Iterable iterable)
            {
/*  55*/        return (List)super.replaceValues(obj, iterable);
            }

            public final volatile Collection get(Object obj)
            {
/*  31*/        return get(obj);
            }

            public final volatile Collection removeAll(Object obj)
            {
/*  31*/        return removeAll(obj);
            }

            public final volatile Multimap unfiltered()
            {
/*  31*/        return unfiltered();
            }

            public final volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  31*/        return replaceValues(obj, iterable);
            }
}
