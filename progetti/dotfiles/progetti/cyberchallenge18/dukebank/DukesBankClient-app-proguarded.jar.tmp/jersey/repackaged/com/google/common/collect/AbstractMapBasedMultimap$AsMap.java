// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Iterators, Maps, Collections2

class submap extends submap
{
    class AsMapIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/*1344*/            return delegateIterator.hasNext();
                }

                public java.util.Map.Entry next()
                {
/*1349*/            java.util.Map.Entry entry = (java.util.Map.Entry)delegateIterator.next();
/*1350*/            collection = (Collection)entry.getValue();
/*1351*/            return wrapEntry(entry);
                }

                public void remove()
                {
/*1356*/            delegateIterator.remove();
/*1357*/            AbstractMapBasedMultimap.access$220(this$0, collection.size());
/*1358*/            collection.clear();
                }

                public volatile Object next()
                {
/*1337*/            return next();
                }

                final Iterator delegateIterator;
                Collection collection;
                final AbstractMapBasedMultimap.AsMap this$1;

                AsMapIterator()
                {
/*1337*/            this$1 = AbstractMapBasedMultimap.AsMap.this;
/*1337*/            super();
/*1338*/            delegateIterator = submap.entrySet().iterator();
                }
    }

    class AsMapEntries extends Maps.EntrySet
    {

                Map map()
                {
/*1313*/            return AbstractMapBasedMultimap.AsMap.this;
                }

                public Iterator iterator()
                {
/*1317*/            return new AsMapIterator();
                }

                public boolean contains(Object obj)
                {
/*1323*/            return Collections2.safeContains(submap.entrySet(), obj);
                }

                public boolean remove(Object obj)
                {
/*1327*/            if(!contains(obj))
                    {
/*1328*/                return false;
                    } else
                    {
/*1330*/                obj = (java.util.Map.Entry)obj;
/*1331*/                AbstractMapBasedMultimap.access$400(this$0, ((java.util.Map.Entry) (obj)).getKey());
/*1332*/                return true;
                    }
                }

                final AbstractMapBasedMultimap.AsMap this$1;

                AsMapEntries()
                {
/*1310*/            this$1 = AbstractMapBasedMultimap.AsMap.this;
/*1310*/            super();
                }
    }


            protected Set createEntrySet()
            {
/*1243*/        return new AsMapEntries();
            }

            public boolean containsKey(Object obj)
            {
/*1249*/        return Maps.safeContainsKey(submap, obj);
            }

            public Collection get(Object obj)
            {
                Collection collection;
/*1253*/        if((collection = (Collection)Maps.safeGet(submap, obj)) == null)
                {
/*1255*/            return null;
                } else
                {
/*1258*/            obj = obj;
/*1259*/            return wrapCollection(obj, collection);
                }
            }

            public Set keySet()
            {
/*1263*/        return AbstractMapBasedMultimap.this.keySet();
            }

            public int size()
            {
/*1268*/        return submap.size();
            }

            public Collection remove(Object obj)
            {
/*1272*/        if((obj = (Collection)submap.remove(obj)) == null)
                {
/*1274*/            return null;
                } else
                {
                    Collection collection;
/*1277*/            (collection = createCollection()).addAll(((Collection) (obj)));
/*1279*