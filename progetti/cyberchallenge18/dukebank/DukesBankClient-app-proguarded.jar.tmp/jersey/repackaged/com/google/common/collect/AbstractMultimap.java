// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps, Multimap, Multimaps, 
//            Multiset, SetMultimap, Sets

abstract class AbstractMultimap
    implements Multimap
{
    class Values extends AbstractCollection
    {

                public Iterator iterator()
                {
/* 183*/            return valueIterator();
                }

                public int size()
                {
/* 187*/            return AbstractMultimap.this.size();
                }

                public boolean contains(Object obj)
                {
/* 191*/            return containsValue(obj);
                }

                public void clear()
                {
/* 195*/            AbstractMultimap.this.clear();
                }

                final AbstractMultimap this$0;

                Values()
                {
/* 181*/            this$0 = AbstractMultimap.this;
/* 181*/            super();
                }
    }

    class EntrySet extends Entries
        implements Set
    {

                public int hashCode()
                {
/* 134*/            return Sets.hashCodeImpl(this);
                }

                public boolean equals(Object obj)
                {
/* 139*/            return Sets.equalsImpl(this, obj);
                }

                final AbstractMultimap this$0;

                private EntrySet()
                {
/* 131*/            this$0 = AbstractMultimap.this;
/* 131*/            super();
                }

    }

    class Entries extends Multimaps.Entries
    {

                Multimap multimap()
                {
/* 122*/            return AbstractMultimap.this;
                }

                public Iterator iterator()
                {
/* 127*/            return entryIterator();
                }

                final AbstractMultimap this$0;

                private Entries()
                {
/* 119*/            this$0 = AbstractMultimap.this;
/* 119*/            super();
                }

    }


            AbstractMultimap()
            {
            }

            public boolean isEmpty()
            {
/*  41*/        return size() == 0;
            }

            public boolean containsValue(Object obj)
            {
                Collection collection;
/*  46*/        for(Iterator iterator = asMap().values().iterator(); iterator.hasNext();)
/*  46*/            if((collection = (Collection)iterator.next()).contains(obj))
/*  48*/                return true;

/*  52*/        return false;
            }

            public boolean containsEntry(Object obj, Object obj1)
            {
/*  57*/        return (obj = (Collection)asMap().get(obj)) != null && ((Collection) (obj)).contains(obj1);
            }

            public boolean remove(Object obj, Object obj1)
            {
/*  63*/        return (obj = (Collection)asMap().get(obj)) != null && ((Collection) (obj)).remove(obj1);
            }

            public boolean put(Object obj, Object obj1)
            {
/*  69*/        return get(obj).add(obj1);
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/*  74*/        Preconditions.checkNotNull(iterable);
/*  77*/        if(iterable instanceof Collection)
/*  78*/            return !(iterable = (Collection)iterable).isEmpty() && get(obj).addAll(iterable);
/*  81*/        return (iterable = iterable.iterator()).hasNext() && Iterators.addAll(get(obj), iterable);
            }

            public boolean putAll(Multimap multimap)
            {
/*  88*/        boolean flag = false;
/*  89*/        for(multimap = multimap.entries().iterator(); multimap.hasNext();)
                {
/*  89*/            java.util.Map.Entry entry = (java.util.Map.Entry)multimap.next();
/*  90*/            flag |= put(entry.getKey(), entry.getValue());
                }

/*  92*/        return flag;
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/*  97*/        Preconditions.checkNotNull(iterable);
/*  98*/        Collection collection = removeAll(obj);
/*  99*/        putAll(obj, iterable);
/* 100*/        return collection;
            }

            public Collection entries()
            {
                Collection collection;
/* 107*/        if((collection = entries) == null)
/* 108*/            return entries = createEntries();
/* 108*/        else
/* 108*/            return collection;
            }

            Collection createEntries()
            {
/* 112*/        if(this instanceof SetMultimap)
/* 113*/            return new EntrySet();
/* 115*/        else
/* 115*/            return new Entries();
            }

            abstract Iterator entryIterator();

            public Set keySet()
            {
                Set set;
/* 149*/        if((set = keySet) == null)
/* 150*/            return keySet = createKeySet();
/* 150*/        else
/* 150*/            return set;
            }

            Set createKeySet()
            {
/* 154*/        return new Maps.KeySet(asMap());
            }

            public Multiset keys()
            {
                Multiset multiset;
/* 161*/        if((multiset = keys) == null)
/* 162*/            return keys = createKeys();
/* 162*/        else
/* 162*/            return multiset;
            }

            Multiset createKeys()
            {
/* 166*/        return new Multimaps.Keys(this);
            }

            public Collection values()
            {
                Collection collection;
/* 173*/        if((collection = values) == null)
/* 174*/            return values = createValues();
/* 174*/        else
/* 174*/            return collection;
            }

            Collection createValues()
            {
/* 178*/        return new Values();
            }

            Iterator valueIterator()
            {
/* 200*/        return Maps.valueIterator(entries().iterator());
            }

            public Map asMap()
            {
                Map map;
/* 207*/        if((map = asMap) == null)
/* 208*/            return asMap = createAsMap();
/* 208*/        else
/* 208*/            return map;
            }

            abstract Map createAsMap();

            public boolean equals(Object obj)
            {
/* 216*/        return Multimaps.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 228*/        return asMap().hashCode();
            }

            public String toString()
            {
/* 239*/        return asMap().toString();
            }

            private transient Collection entries;
            private transient Set keySet;
            private transient Multiset keys;
            private transient Collection values;
            private transient Map asMap;
}
