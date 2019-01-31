// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Collections2, Iterators, Lists, 
//            Maps, Multimap, Multimaps, Multiset

static class transformer extends AbstractMultimap
{

            Collection transform(Object obj, Collection collection)
            {
/*1187*/        obj = Maps.asValueToValueFunction(transformer, obj);
/*1189*/        if(collection instanceof List)
/*1190*/            return Lists.transform((List)collection, ((Function) (obj)));
/*1192*/        else
/*1192*/            return Collections2.transform(collection, ((Function) (obj)));
            }

            Map createAsMap()
            {
/*1198*/        return Maps.transformEntries(fromMultimap.asMap(), new Maps.EntryTransformer() {

                    public Collection transformEntry(Object obj, Collection collection)
                    {
/*1202*/                return transform(obj, collection);
                    }

                    public volatile Object transformEntry(Object obj, Object obj1)
                    {
/*1199*/                return transformEntry(obj, (Collection)obj1);
                    }

                    final Multimaps.TransformedEntriesMultimap this$0;

                    
                    {
/*1199*/                this$0 = Multimaps.TransformedEntriesMultimap.this;
/*1199*/                super();
                    }
        });
            }

            public void clear()
            {
/*1208*/        fromMultimap.clear();
            }

            public boolean containsKey(Object obj)
            {
/*1212*/        return fromMultimap.containsKey(obj);
            }

            Iterator entryIterator()
            {
/*1217*/        return Iterators.transform(fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(transformer));
            }

            public Collection get(Object obj)
            {
/*1222*/        return transform(obj, fromMultimap.get(obj));
            }

            public boolean isEmpty()
            {
/*1226*/        return fromMultimap.isEmpty();
            }

            public Set keySet()
            {
/*1230*/        return fromMultimap.keySet();
            }

            public Multiset keys()
            {
/*1234*/        return fromMultimap.keys();
            }

            public boolean put(Object obj, Object obj1)
            {
/*1238*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/*1242*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Multimap multimap)
            {
/*1247*/        throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj, Object obj1)
            {
/*1252*/        return get(obj).remove(obj1);
            }

            public Collection removeAll(Object obj)
            {
/*1257*/        return transform(obj, fromMultimap.removeAll(obj));
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/*1262*/        throw new UnsupportedOperationException();
            }

            public int size()
            {
/*1266*/        return fromMultimap.size();
            }

            Collection createValues()
            {
/*1271*/        return Collections2.transform(fromMultimap.entries(), Maps.asEntryToValueFunction(transformer));
            }

            final Multimap fromMultimap;
            final transformer transformer;

            _cls1.this._cls0(Multimap multimap, _cls1.this._cls0 _pcls0)
            {
/*1182*/        fromMultimap = (Multimap)Preconditions.checkNotNull(multimap);
/*1183*/        transformer = (transformer)Preconditions.checkNotNull(_pcls0);
            }
}
