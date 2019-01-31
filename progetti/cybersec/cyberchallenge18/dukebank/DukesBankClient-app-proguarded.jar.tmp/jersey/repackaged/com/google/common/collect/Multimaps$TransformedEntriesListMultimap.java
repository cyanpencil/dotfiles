// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.List;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ListMultimap, Lists, Maps, Multimap, 
//            Multimaps

static final class t> extends t>
    implements ListMultimap
{

            final List transform(Object obj, Collection collection)
            {
/*1393*/        return Lists.transform((List)collection, Maps.asValueToValueFunction(transformer, obj));
            }

            public final List get(Object obj)
            {
/*1397*/        return transform(obj, fromMultimap.get(obj));
            }

            public final List removeAll(Object obj)
            {
/*1402*/        return transform(obj, fromMultimap.removeAll(obj));
            }

            public final List replaceValues(Object obj, Iterable iterable)
            {
/*1407*/        throw new UnsupportedOperationException();
            }

            public final volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*1383*/        return replaceValues(obj, iterable);
            }

            public final volatile Collection removeAll(Object obj)
            {
/*1383*/        return removeAll(obj);
            }

            public final volatile Collection get(Object obj)
            {
/*1383*/        return get(obj);
            }

            final volatile Collection transform(Object obj, Collection collection)
            {
/*1383*/        return transform(obj, collection);
            }

            (ListMultimap listmultimap,  )
            {
/*1389*/        super(listmultimap, );
            }
}
