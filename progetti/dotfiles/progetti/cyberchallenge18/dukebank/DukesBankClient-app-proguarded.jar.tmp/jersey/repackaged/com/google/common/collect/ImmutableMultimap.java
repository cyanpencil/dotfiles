// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, ImmutableCollection, ImmutableListMultimap, ImmutableMap, 
//            ImmutableMultiset, Multimap, ImmutableSet, UnmodifiableIterator, 
//            Multiset, ImmutableList, Multisets, Iterators, 
//            Maps, CollectPreconditions, Lists, Ordering, 
//            AbstractMapBasedMultimap

public abstract class ImmutableMultimap extends AbstractMultimap
    implements Serializable
{
    static final class Values extends ImmutableCollection
    {

                public final boolean contains(Object obj)
                {
/* 650*/            return multimap.containsValue(obj);
                }

                public final UnmodifiableIterator iterator()
                {
/* 654*/            return multimap.valueIterator();
                }

                final int copyIntoArray(Object aobj[], int i)
                {
                    ImmutableCollection immutablecollection;
/* 660*/            for(Iterator iterator1 = multimap.map.values().iterator(); iterator1.hasNext();)
/* 660*/                i = (immutablecollection = (ImmutableCollection)iterator1.next()).copyIntoArray(aobj, i);

/* 663*/            return i;
                }

                public final int size()
                {
/* 668*/            return multimap.size();
                }

                final boolean isPartialView()
                {
/* 672*/            return true;
                }

                public final volatile Iterator iterator()
                {
/* 641*/            return iterator();
                }

                private final transient ImmutableMultimap multimap;

                Values(ImmutableMultimap immutablemultimap)
                {
/* 645*/            multimap = immutablemultimap;
                }
    }

    class Keys extends ImmutableMultiset
    {

                public boolean contains(Object obj)
                {
/* 585*/            return containsKey(obj);
                }

                public int count(Object obj)
                {
/* 590*/            if((obj = (Collection)map.get(obj)) == null)
/* 591*/                return 0;
/* 591*/            else
/* 591*/                return ((Collection) (obj)).size();
                }

                public Set elementSet()
                {
/* 596*/            return keySet();
                }

                public int size()
                {
/* 601*/            return ImmutableMultimap.this.size();
                }

                Multiset.Entry getEntry(int i)
                {
/* 606*/            return Multisets.immutableEntry((i = (java.util.Map.Entry)map.entrySet().asList().get(i)).getKey(), ((Collection)i.getValue()).size());
                }

                boolean isPartialView()
                {
/* 612*/            return true;
                }

                final ImmutableMultimap this$0;

                Keys()
                {
/* 582*/            this$0 = ImmutableMultimap.this;
/* 582*/            super();
                }
    }

    abstract class Itr extends UnmodifiableIterator
    {

                abstract Object output(Object obj, Object obj1);

                public boolean hasNext()
                {
/* 541*/            return mapIterator.hasNext() || valueIterator.hasNext();
                }

                public Object next()
                {
/* 546*/            if(!valueIterator.hasNext())
                    {
/* 547*/                java.util.Map.Entry entry = (java.util.Map.Entry)mapIterator.next();
/* 548*/                key = entry.getKey();
/* 549*/                valueIterator = ((Collection)entry.getValue()).iterator();
                    }
/* 551*/            return output(key, valueIterator.next());
                }

                final Iterator mapIterator;
                Object key;
                Iterator valueIterator;
                final ImmutableMultimap this$0;

                private Itr()
                {
/* 532*/            this$0 = ImmutableMultimap.this;
/* 532*/            super();
/* 533*/            mapIterator = asMap().entrySet().iterator();
/* 534*/            key = null;
/* 535*/            valueIterator = Iterators.emptyIterator();
                }

    }

    static class EntryCollection extends ImmutableCollection
    {

                public UnmodifiableIterator iterator()
                {
/* 509*/            return multimap.entryIterator();
                }

                boolean isPartialView()
                {
/* 513*/            return multimap.isPartialView();
                }

                public int size()
                {
/* 518*/            return multimap.size();
                }

                public boolean contains(Object obj)
                {
/* 522*/            if(obj instanceof java.util.Map.Entry)
                    {
/* 523*/                obj = (java.util.Map.Entry)obj;
/* 524*/                return multimap.containsEntry(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/* 526*/                return false;
                    }
                }

                public volatile Iterator iterator()
                {
/* 500*/            return iterator();
                }

                final ImmutableMultimap multimap;

                EntryCollection(ImmutableMultimap immutablemultimap)
                {
/* 505*/            multimap = immutablemultimap;
                }
    }

    public static class Builder
    {

                public Builder put(Object obj, Object obj1)
                {
/* 169*/            CollectPreconditions.checkEntryNotNull(obj, obj1);
/* 170*/            builderMultimap.put(obj, obj1);
/* 171*/            return this;
                }

                public ImmutableMultimap build()
                {
/* 254*/            if(valueComparator != null)
                    {
                        Object obj;
/* 255*/                for(Iterator iterator = builderMultimap.asMap().values().iterator(); iterator.hasNext(); Collections.sort(((List) (obj = (List)(obj = (Collection)iterator.next()))), valueComparator));
                    }
/* 260*/            if(keyComparator != null)
                    {
/* 261*/                BuilderMultimap buildermultimap = new BuilderMultimap();
                        Object obj1;
/* 262*/                Collections.sort(((List) (obj1 = Lists.newArrayList(builderMultimap.asMap().entrySet()))), Ordering.from(keyComparator).onKeys());
                        java.util.Map.Entry entry;
/* 267*/                for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); buildermultimap.putAll(entry.getKey(), (Iterable)entry.getValue()))
/* 267*/                    entry = (java.util.Map.Entry)((Iterator) (obj1)).next();

/* 270*/                builderMultimap = buildermultimap;
                    }
/* 272*/            return ImmutableMultimap.copyOf(builderMultimap);
                }

                Multimap builderMultimap;
                Comparator keyComparator;
                Comparator valueComparator;

                public Builder()
                {
/* 155*/            builderMultimap = new BuilderMultimap();
                }
    }

    static class BuilderMultimap extends AbstractMapBasedMultimap
    {

                Collection createCollection()
                {
/* 131*/            return Lists.newArrayList();
                }

                BuilderMultimap()
                {
/* 128*/            super(new LinkedHashMap());
                }
    }


            public static ImmutableMultimap copyOf(Multimap multimap)
            {
                ImmutableMultimap immutablemultimap;
/* 290*/        if((multimap instanceof ImmutableMultimap) && !(immutablemultimap = (ImmutableMultimap)multimap).isPartialView())
/* 295*/            return immutablemultimap;
/* 298*/        else
/* 298*/            return ImmutableListMultimap.copyOf(multimap);
            }

            ImmutableMultimap(ImmutableMap immutablemap, int i)
            {
/* 322*/        map = immutablemap;
/* 323*/        size = i;
            }

            /**
             * @deprecated Method removeAll is deprecated
             */

            public ImmutableCollection removeAll(Object obj)
            {
/* 337*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method replaceValues is deprecated
             */

            public ImmutableCollection replaceValues(Object obj, Iterable iterable)
            {
/* 350*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method clear is deprecated
             */

            public void clear()
            {
/* 362*/        throw new UnsupportedOperationException();
            }

            public abstract ImmutableCollection get(Object obj);

            /**
             * @deprecated Method put is deprecated
             */

            public boolean put(Object obj, Object obj1)
            {
/* 392*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method putAll is deprecated
             */

            public boolean putAll(Object obj, Iterable iterable)
            {
/* 404*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method putAll is deprecated
             */

            public boolean putAll(Multimap multimap)
            {
/* 416*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public boolean remove(Object obj, Object obj1)
            {
/* 428*/        throw new UnsupportedOperationException();
            }

            boolean isPartialView()
            {
/* 438*/        return map.isPartialView();
            }

            public boolean containsKey(Object obj)
            {
/* 445*/        return map.containsKey(obj);
            }

            public boolean containsValue(Object obj)
            {
/* 450*/        return obj != null && super.containsValue(obj);
            }

            public int size()
            {
/* 455*/        return size;
            }

            public ImmutableSet keySet()
            {
/* 467*/        return map.keySet();
            }

            public ImmutableMap asMap()
            {
/* 477*/        return map;
            }

            Map createAsMap()
            {
/* 482*/        throw new AssertionError("should never be called");
            }

            public ImmutableCollection entries()
            {
/* 492*/        return (ImmutableCollection)super.entries();
            }

            ImmutableCollection createEntries()
            {
/* 497*/        return new EntryCollection(this);
            }

            UnmodifiableIterator entryIterator()
            {
/* 557*/        return new Itr() {

                    java.util.Map.Entry output(Object obj, Object obj1)
                    {
/* 560*/                return Maps.immutableEntry(obj, obj1);
                    }

                    volatile Object output(Object obj, Object obj1)
                    {
/* 557*/                return output(obj, obj1);
                    }

                    final ImmutableMultimap this$0;

                    
                    {
/* 557*/                this$0 = ImmutableMultimap.this;
/* 557*/                super();
                    }
        };
            }

            public ImmutableMultiset keys()
            {
/* 573*/        return (ImmutableMultiset)super.keys();
            }

            ImmutableMultiset createKeys()
            {
/* 578*/        return new Keys();
            }

            public ImmutableCollection values()
            {
/* 623*/        return (ImmutableCollection)super.values();
            }

            ImmutableCollection createValues()
            {
/* 628*/        return new Values(this);
            }

            UnmodifiableIterator valueIterator()
            {
/* 633*/        return new Itr() {

                    Object output(Object obj, Object obj1)
                    {
/* 636*/                return obj1;
                    }

                    final ImmutableMultimap this$0;

                    
                    {
/* 633*/                this$0 = ImmutableMultimap.this;
/* 633*/                super();
                    }
        };
            }

            public volatile String toString()
            {
/*  64*/        return super.toString();
            }

            public volatile int hashCode()
            {
/*  64*/        return super.hashCode();
            }

            public volatile boolean equals(Object obj)
            {
/*  64*/        return super.equals(obj);
            }

            public volatile Map asMap()
            {
/*  64*/        return asMap();
            }

            volatile Iterator valueIterator()
            {
/*  64*/        return valueIterator();
            }

            volatile Collection createValues()
            {
/*  64*/        return createValues();
            }

            public volatile Collection values()
            {
/*  64*/        return values();
            }

            volatile Multiset createKeys()
            {
/*  64*/        return createKeys();
            }

            public volatile Multiset keys()
            {
/*  64*/        return keys();
            }

            public volatile Set keySet()
            {
/*  64*/        return keySet();
            }

            volatile Iterator entryIterator()
            {
/*  64*/        return entryIterator();
            }

            volatile Collection createEntries()
            {
/*  64*/        return createEntries();
            }

            public volatile Collection entries()
            {
/*  64*/        return entries();
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  64*/        return replaceValues(obj, iterable);
            }

            public volatile boolean containsEntry(Object obj, Object obj1)
            {
/*  64*/        return super.containsEntry(obj, obj1);
            }

            public volatile boolean isEmpty()
            {
/*  64*/        return super.isEmpty();
            }

            public volatile Collection get(Object obj)
            {
/*  64*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  64*/        return removeAll(obj);
            }

            final transient ImmutableMap map;
            final transient int size;
}
