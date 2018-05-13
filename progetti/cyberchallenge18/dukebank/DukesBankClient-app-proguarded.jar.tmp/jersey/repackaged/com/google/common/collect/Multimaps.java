// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, FilteredEntrySetMultimap, FilteredKeyListMultimap, FilteredKeyMultimap, 
//            FilteredKeySetMultimap, FilteredMultimap, FilteredSetMultimap, ImmutableListMultimap, 
//            ImmutableMultimap, ImmutableSetMultimap, ListMultimap, Maps, 
//            Multimap, SetMultimap, SortedSetMultimap, Synchronized, 
//            AbstractMultiset, CollectPreconditions, Multiset, Multisets, 
//            TransformedIterator, Lists, AbstractMultimap, Collections2, 
//            Iterators, Sets, ForwardingMultimap, AbstractSortedSetMultimap, 
//            AbstractSetMultimap, AbstractListMultimap, AbstractMapBasedMultimap

public final class Multimaps
{
    static final class AsMap extends Maps.ImprovedAbstractMap
    {
        class EntrySet extends Maps.EntrySet
        {

                    Map map()
                    {
/*1693*/                return AsMap.this;
                    }

                    public Iterator iterator()
                    {
/*1697*/                return Maps.asMapEntryIterator(multimap.keySet(), new Function() {

                            public Collection apply(Object obj)
                            {
/*1700*/                        return multimap.get(obj);
                            }

                            public volatile Object apply(Object obj)
                            {
/*1697*/                        return apply(obj);
                            }

                            final EntrySet this$1;

                            
                            {
/*1697*/                        this$1 = EntrySet.this;
/*1697*/                        super();
                            }
                });
                    }

                    public boolean remove(Object obj)
                    {
/*1706*/                if(!contains(obj))
                        {
/*1707*/                    return false;
                        } else
                        {
/*1709*/                    obj = (java.util.Map.Entry)obj;
/*1710*/                    removeValuesForKey(((java.util.Map.Entry) (obj)).getKey());
/*1711*/                    return true;
                        }
                    }

                    final AsMap this$0;

                    EntrySet()
                    {
/*1691*/                this$0 = AsMap.this;
/*1691*/                super();
                    }
        }


                public final int size()
                {
/*1680*/            return multimap.keySet().size();
                }

                protected final Set createEntrySet()
                {
/*1684*/            return new EntrySet();
                }

                final void removeValuesForKey(Object obj)
                {
/*1688*/            multimap.keySet().remove(obj);
                }

                public final Collection get(Object obj)
                {
/*1717*/            if(containsKey(obj))
/*1717*/                return multimap.get(obj);
/*1717*/            else
/*1717*/                return null;
                }

                public final Collection remove(Object obj)
                {
/*1721*/            if(containsKey(obj))
/*1721*/                return multimap.removeAll(obj);
/*1721*/            else
/*1721*/                return null;
                }

                public final Set keySet()
                {
/*1725*/            return multimap.keySet();
                }

                public final boolean isEmpty()
                {
/*1729*/            return multimap.isEmpty();
                }

                public final boolean containsKey(Object obj)
                {
/*1733*/            return multimap.containsKey(obj);
                }

                public final void clear()
                {
/*1737*/            multimap.clear();
                }

                public final volatile Object remove(Object obj)
                {
/*1671*/            return remove(obj);
                }

                public final volatile Object get(Object obj)
                {
/*1671*/            return get(obj);
                }

                private final Multimap multimap;


                AsMap(Multimap multimap1)
                {
/*1676*/            multimap = (Multimap)Preconditions.checkNotNull(multimap1);
                }
    }

    static abstract class Entries extends AbstractCollection
    {

                abstract Multimap multimap();

                public int size()
                {
/*1644*/            return multimap().size();
                }

                public boolean contains(Object obj)
                {
/*1648*/            if(obj instanceof java.util.Map.Entry)
                    {
/*1649*/                obj = (java.util.Map.Entry)obj;
/*1650*/                return multimap().containsEntry(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/*1652*/                return false;
                    }
                }

                public boolean remove(Object obj)
                {
/*1656*/            if(obj instanceof java.util.Map.Entry)
                    {
/*1657*/                obj = (java.util.Map.Entry)obj;
/*1658*/                return multimap().remove(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/*1660*/                return false;
                    }
                }

                public void clear()
                {
/*1664*/            multimap().clear();
                }

                Entries()
                {
                }
    }

    static class Keys extends AbstractMultiset
    {
        class KeysEntrySet extends Multisets.EntrySet
        {

                    Multiset multiset()
                    {
/*1552*/                return Keys.this;
                    }

                    public Iterator iterator()
                    {
/*1556*/                return entryIterator();
                    }

                    public int size()
                    {
/*1560*/                return distinctElements();
                    }

                    public boolean isEmpty()
                    {
/*1564*/                return multimap.isEmpty();
                    }

                    public boolean contains(Object obj)
                    {
/*1568*/                if(obj instanceof Multiset.Entry)
                        {
/*1569*/                    obj = (Multiset.Entry)obj;
                            Collection collection;
/*1570*/                    return (collection = (Collection)multimap.asMap().get(((Multiset.Entry) (obj)).getElement())) != null && collection.size() == ((Multiset.Entry) (obj)).getCount();
                        } else
                        {
/*1573*/                    return false;
                        }
                    }

                    public boolean remove(Object obj)
                    {
/*1577*/                if(obj instanceof Multiset.Entry)
                        {
/*1578*/                    obj = (Multiset.Entry)obj;
                            Collection collection;
/*1579*/                    if((collection = (Collection)multimap.asMap().get(((Multiset.Entry) (obj)).getElement())) != null && collection.size() == ((Multiset.Entry) (obj)).getCount())
                            {
/*1581*/                        collection.clear();
/*1582*/                        return true;
                            }
                        }
/*1585*/                return false;
                    }

                    final Keys this$0;

                    KeysEntrySet()
                    {
/*1550*/                this$0 = Keys.this;
/*1550*/                super();
                    }
        }


                Iterator entryIterator()
                {
/*1522*/            return new TransformedIterator(multimap.asMap().entrySet().iterator()) {

                        Multiset.Entry transform(final java.util.Map.Entry backingEntry)
                        {
/*1527*/                    return new Multisets.AbstractEntry() {

                                public Object getElement()
                                {
/*1530*/                            return backingEntry.getKey();
                                }

                                public int getCount()
                                {
/*1535*/                            return ((Collection)backingEntry.getValue()).size();
                                }

                                final java.util.Map.Entry val$backingEntry;
                                final _cls1 this$1;

                                
                                {
/*1527*/                            this$1 = _cls1.this;
/*1527*/                            backingEntry = entry;
/*1527*/                            super();
                                }
                    };
                        }

                        volatile Object transform(Object obj)
                        {
/*1523*/                    return transform((java.util.Map.Entry)obj);
                        }

                        final Keys this$0;

                        
                        {
/*1523*/                    this$0 = Keys.this;
/*1523*/                    super(iterator1);
                        }
            };
                }

                int distinctElements()
                {
/*1543*/            return multimap.asMap().size();
                }

                Set createEntrySet()
                {
/*1547*/            return new KeysEntrySet();
                }

                public boolean contains(Object obj)
                {
/*1590*/            return multimap.containsKey(obj);
                }

                public Iterator iterator()
                {
/*1594*/            return Maps.keyIterator(multimap.entries().iterator());
                }

                public int count(Object obj)
                {
/*1598*/            if((obj = (Collection)Maps.safeGet(multimap.asMap(), obj)) == null)
/*1599*/                return 0;
/*1599*/            else
/*1599*/                return ((Collection) (obj)).size();
                }

                public int remove(Object obj, int i)
                {
/*1603*/            CollectPreconditions.checkNonnegative(i, "occurrences");
/*1604*/            if(i == 0)
/*1605*/                return count(obj);
/*1608*/            if((obj = (Collection)Maps.safeGet(multimap.asMap(), obj)) == null)
/*1611*/                return 0;
/*1614*/            int j = ((Collection) (obj)).size();
/*1615*/            if(i >= j)
                    {
/*1616*/                ((Collection) (obj)).clear();
                    } else
                    {
/*1618*/                obj = ((Collection) (obj)).iterator();
/*1619*/                for(int k = 0; k < i; k++)
                        {
/*1620*/                    ((Iterator) (obj)).next();
/*1621*/                    ((Iterator) (obj)).remove();
                        }

                    }
/*1624*/            return j;
                }

                public void clear()
                {
/*1628*/            multimap.clear();
                }

                public Set elementSet()
                {
/*1632*/            return multimap.keySet();
                }

                final Multimap multimap;

                Keys(Multimap multimap1)
                {
/*1518*/            multimap = multimap1;
                }
    }

    static final class TransformedEntriesListMultimap extends TransformedEntriesMultimap
        implements ListMultimap
    {

                final List transform(Object obj, Collection collection)
                {
/*1393*/            return Lists.transform((List)collection, Maps.asValueToValueFunction(transformer, obj));
                }

                public final List get(Object obj)
                {
/*1397*/            return transform(obj, fromMultimap.get(obj));
                }

                public final List removeAll(Object obj)
                {
/*1402*/            return transform(obj, fromMultimap.removeAll(obj));
                }

                public final List replaceValues(Object obj, Iterable iterable)
                {
/*1407*/            throw new UnsupportedOperationException();
                }

                public final volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/*1383*/            return replaceValues(obj, iterable);
                }

                public final volatile Collection removeAll(Object obj)
                {
/*1383*/            return removeAll(obj);
                }

                public final volatile Collection get(Object obj)
                {
/*1383*/            return get(obj);
                }

                final volatile Collection transform(Object obj, Collection collection)
                {
/*1383*/            return transform(obj, collection);
                }

                TransformedEntriesListMultimap(ListMultimap listmultimap, Maps.EntryTransformer entrytransformer)
                {
/*1389*/            super(listmultimap, entrytransformer);
                }
    }

    static class TransformedEntriesMultimap extends AbstractMultimap
    {

                Collection transform(Object obj, Collection collection)
                {
/*1187*/            obj = Maps.asValueToValueFunction(transformer, obj);
/*1189*/            if(collection instanceof List)
/*1190*/                return Lists.transform((List)collection, ((Function) (obj)));
/*1192*/            else
/*1192*/                return Collections2.transform(collection, ((Function) (obj)));
                }

                Map createAsMap()
                {
/*1198*/            return Maps.transformEntries(fromMultimap.asMap(), new Maps.EntryTransformer() {

                        public Collection transformEntry(Object obj, Collection collection)
                        {
/*1202*/                    return transform(obj, collection);
                        }

                        public volatile Object transformEntry(Object obj, Object obj1)
                        {
/*1199*/                    return transformEntry(obj, (Collection)obj1);
                        }

                        final TransformedEntriesMultimap this$0;

                        
                        {
/*1199*/                    this$0 = TransformedEntriesMultimap.this;
/*1199*/                    super();
                        }
            });
                }

                public void clear()
                {
/*1208*/            fromMultimap.clear();
                }

                public boolean containsKey(Object obj)
                {
/*1212*/            return fromMultimap.containsKey(obj);
                }

                Iterator entryIterator()
                {
/*1217*/            return Iterators.transform(fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(transformer));
                }

                public Collection get(Object obj)
                {
/*1222*/            return transform(obj, fromMultimap.get(obj));
                }

                public boolean isEmpty()
                {
/*1226*/            return fromMultimap.isEmpty();
                }

                public Set keySet()
                {
/*1230*/            return fromMultimap.keySet();
                }

                public Multiset keys()
                {
/*1234*/            return fromMultimap.keys();
                }

                public boolean put(Object obj, Object obj1)
                {
/*1238*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Object obj, Iterable iterable)
                {
/*1242*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Multimap multimap)
                {
/*1247*/            throw new UnsupportedOperationException();
                }

                public boolean remove(Object obj, Object obj1)
                {
/*1252*/            return get(obj).remove(obj1);
                }

                public Collection removeAll(Object obj)
                {
/*1257*/            return transform(obj, fromMultimap.removeAll(obj));
                }

                public Collection replaceValues(Object obj, Iterable iterable)
                {
/*1262*/            throw new UnsupportedOperationException();
                }

                public int size()
                {
/*1266*/            return fromMultimap.size();
                }

                Collection createValues()
                {
/*1271*/            return Collections2.transform(fromMultimap.entries(), Maps.asEntryToValueFunction(transformer));
                }

                final Multimap fromMultimap;
                final Maps.EntryTransformer transformer;

                TransformedEntriesMultimap(Multimap multimap, Maps.EntryTransformer entrytransformer)
                {
/*1182*/            fromMultimap = (Multimap)Preconditions.checkNotNull(multimap);
/*1183*/            transformer = (Maps.EntryTransformer)Preconditions.checkNotNull(entrytransformer);
                }
    }

    static class MapMultimap extends AbstractMultimap
        implements Serializable, SetMultimap
    {

                public int size()
                {
/* 937*/            return map.size();
                }

                public boolean containsKey(Object obj)
                {
/* 942*/            return map.containsKey(obj);
                }

                public boolean containsValue(Object obj)
                {
/* 947*/            return map.containsValue(obj);
                }

                public boolean containsEntry(Object obj, Object obj1)
                {
/* 952*/            return map.entrySet().contains(Maps.immutableEntry(obj, obj1));
                }

                public Set get(final Object key)
                {
/* 957*/            return new Sets.ImprovedAbstractSet() {

                        public Iterator iterator()
                        {
/* 959*/                    return new Iterator() {

                                public boolean hasNext()
                                {
/* 964*/                            return i == 0 && map.containsKey(key);
                                }

                                public Object next()
                                {
/* 969*/                            if(!hasNext())
                                    {
/* 970*/                                throw new NoSuchElementException();
                                    } else
                                    {
/* 972*/                                i++;
/* 973*/                                return map.get(key);
                                    }
                                }

                                public void remove()
                                {
/* 978*/                            CollectPreconditions.checkRemove(i == 1);
/* 979*/                            i = -1;
/* 980*/                            map.remove(key);
                                }

                                int i;
                                final _cls1 this$1;

                                
                                {
/* 959*/                            this$1 = _cls1.this;
/* 959*/                            super();
                                }
                    };
                        }

                        public int size()
                        {
/* 986*/                    return !map.containsKey(key) ? 0 : 1;
                        }

                        final Object val$key;
                        final MapMultimap this$0;

                        
                        {
/* 957*/                    this$0 = MapMultimap.this;
/* 957*/                    key = obj;
/* 957*/                    super();
                        }
            };
                }

                public boolean put(Object obj, Object obj1)
                {
/* 993*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Object obj, Iterable iterable)
                {
/* 998*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Multimap multimap)
                {
/*1003*/            throw new UnsupportedOperationException();
                }

                public Set replaceValues(Object obj, Iterable iterable)
                {
/*1008*/            throw new UnsupportedOperationException();
                }

                public boolean remove(Object obj, Object obj1)
                {
/*1013*/            return map.entrySet().remove(Maps.immutableEntry(obj, obj1));
                }

                public Set removeAll(Object obj)
                {
/*1018*/            HashSet hashset = new HashSet(2);
/*1019*/            if(!map.containsKey(obj))
                    {
/*1020*/                return hashset;
                    } else
                    {
/*1022*/                hashset.add(map.remove(obj));
/*1023*/                return hashset;
                    }
                }

                public void clear()
                {
/*1028*/            map.clear();
                }

                public Set keySet()
                {
/*1033*/            return map.keySet();
                }

                public Collection values()
                {
/*1038*/            return map.values();
                }

                public Set entries()
                {
/*1043*/            return map.entrySet();
                }

                Iterator entryIterator()
                {
/*1048*/            return map.entrySet().iterator();
                }

                Map createAsMap()
                {
/*1053*/            return new AsMap(this);
                }

                public int hashCode()
                {
/*1057*/            return map.hashCode();
                }

                public volatile Collection entries()
                {
/* 927*/            return entries();
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 927*/            return replaceValues(obj, iterable);
                }

                public volatile Collection get(Object obj)
                {
/* 927*/            return get(obj);
                }

                public volatile Collection removeAll(Object obj)
                {
/* 927*/            return removeAll(obj);
                }

                final Map map;

                MapMultimap(Map map1)
                {
/* 932*/            map = (Map)Preconditions.checkNotNull(map1);
                }
    }

    static class UnmodifiableSortedSetMultimap extends UnmodifiableSetMultimap
        implements SortedSetMultimap
    {

                public SortedSetMultimap _mthdelegate()
                {
/* 646*/            return (SortedSetMultimap)super._mthdelegate();
                }

                public SortedSet get(Object obj)
                {
/* 649*/            return Collections.unmodifiableSortedSet(_mthdelegate().get(obj));
                }

                public SortedSet removeAll(Object obj)
                {
/* 652*/            throw new UnsupportedOperationException();
                }

                public SortedSet replaceValues(Object obj, Iterable iterable)
                {
/* 656*/            throw new UnsupportedOperationException();
                }

                public Comparator valueComparator()
                {
/* 660*/            return _mthdelegate().valueComparator();
                }

                public volatile Set replaceValues(Object obj, Iterable iterable)
                {
/* 640*/            return replaceValues(obj, iterable);
                }

                public volatile Set removeAll(Object obj)
                {
/* 640*/            return removeAll(obj);
                }

                public volatile Set get(Object obj)
                {
/* 640*/            return get(obj);
                }

                public volatile SetMultimap _mthdelegate()
                {
/* 640*/            return _mthdelegate();
                }

                public volatile Collection get(Object obj)
                {
/* 640*/            return get(obj);
                }

                public volatile Collection removeAll(Object obj)
                {
/* 640*/            return removeAll(obj);
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 640*/            return replaceValues(obj, iterable);
                }

                public volatile Multimap _mthdelegate()
                {
/* 640*/            return _mthdelegate();
                }

                public volatile Object _mthdelegate()
                {
/* 640*/            return _mthdelegate();
                }

                UnmodifiableSortedSetMultimap(SortedSetMultimap sortedsetmultimap)
                {
/* 643*/            super(sortedsetmultimap);
                }
    }

    static class UnmodifiableSetMultimap extends UnmodifiableMultimap
        implements SetMultimap
    {

                public SetMultimap _mthdelegate()
                {
/* 618*/            return (SetMultimap)super._mthdelegate();
                }

                public Set get(Object obj)
                {
/* 625*/            return Collections.unmodifiableSet(_mthdelegate().get(obj));
                }

                public Set entries()
                {
/* 628*/            return Maps.unmodifiableEntrySet(_mthdelegate().entries());
                }

                public Set removeAll(Object obj)
                {
/* 631*/            throw new UnsupportedOperationException();
                }

                public Set replaceValues(Object obj, Iterable iterable)
                {
/* 635*/            throw new UnsupportedOperationException();
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 612*/            return replaceValues(obj, iterable);
                }

                public volatile Collection removeAll(Object obj)
                {
/* 612*/            return removeAll(obj);
                }

                public volatile Collection get(Object obj)
                {
/* 612*/            return get(obj);
                }

                public volatile Collection entries()
                {
/* 612*/            return entries();
                }

                public volatile Multimap _mthdelegate()
                {
/* 612*/            return _mthdelegate();
                }

                public volatile Object _mthdelegate()
                {
/* 612*/            return _mthdelegate();
                }

                UnmodifiableSetMultimap(SetMultimap setmultimap)
                {
/* 615*/            super(setmultimap);
                }
    }

    static class UnmodifiableListMultimap extends UnmodifiableMultimap
        implements ListMultimap
    {

                public ListMultimap _mthdelegate()
                {
/* 597*/            return (ListMultimap)super._mthdelegate();
                }

                public List get(Object obj)
                {
/* 600*/            return Collections.unmodifiableList(_mthdelegate().get(obj));
                }

                public List removeAll(Object obj)
                {
/* 603*/            throw new UnsupportedOperationException();
                }

                public List replaceValues(Object obj, Iterable iterable)
                {
/* 607*/            throw new UnsupportedOperationException();
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 591*/            return replaceValues(obj, iterable);
                }

                public volatile Collection removeAll(Object obj)
                {
/* 591*/            return removeAll(obj);
                }

                public volatile Collection get(Object obj)
                {
/* 591*/            return get(obj);
                }

                public volatile Multimap _mthdelegate()
                {
/* 591*/            return _mthdelegate();
                }

                public volatile Object _mthdelegate()
                {
/* 591*/            return _mthdelegate();
                }

                UnmodifiableListMultimap(ListMultimap listmultimap)
                {
/* 594*/            super(listmultimap);
                }
    }

    static class UnmodifiableMultimap extends ForwardingMultimap
        implements Serializable
    {

                protected Multimap _mthdelegate()
                {
/* 505*/            return _flddelegate;
                }

                public void clear()
                {
/* 509*/            throw new UnsupportedOperationException();
                }

                public Map asMap()
                {
                    Map map1;
/* 513*/            if((map1 = map) == null)
/* 515*/                map1 = map = Collections.unmodifiableMap(Maps.transformValues(_flddelegate.asMap(), new Function() {

                            public Collection apply(Collection collection)
                            {
/* 519*/                        return Multimaps.unmodifiableValueCollection(collection);
                            }

                            public volatile Object apply(Object obj)
                            {
/* 516*/                        return apply((Collection)obj);
                            }

                            final UnmodifiableMultimap this$0;

                        
                        {
/* 516*/                    this$0 = UnmodifiableMultimap.this;
/* 516*/                    super();
                        }
                }));
/* 523*/            return map1;
                }

                public Collection entries()
                {
                    Collection collection;
/* 527*/            if((collection = entries) == null)
/* 529*/                entries = collection = Multimaps.unmodifiableEntries(_flddelegate.entries());
/* 531*/            return collection;
                }

                public Collection get(Object obj)
                {
/* 535*/            return Multimaps.unmodifiableValueCollection(_flddelegate.get(obj));
                }

                public Multiset keys()
                {
                    Multiset multiset;
/* 539*/            if((multiset = keys) == null)
/* 541*/                keys = multiset = Multisets.unmodifiableMultiset(_flddelegate.keys());
/* 543*/            return multiset;
                }

                public Set keySet()
                {
                    Set set;
/* 547*/            if((set = keySet) == null)
/* 549*/                keySet = set = Collections.unmodifiableSet(_flddelegate.keySet());
/* 551*/            return set;
                }

                public boolean put(Object obj, Object obj1)
                {
/* 555*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Object obj, Iterable iterable)
                {
/* 559*/            throw new UnsupportedOperationException();
                }

                public boolean putAll(Multimap multimap)
                {
/* 564*/            throw new UnsupportedOperationException();
                }

                public boolean remove(Object obj, Object obj1)
                {
/* 568*/            throw new UnsupportedOperationException();
                }

                public Collection removeAll(Object obj)
                {
/* 572*/            throw new UnsupportedOperationException();
                }

                public Collection replaceValues(Object obj, Iterable iterable)
                {
/* 577*/            throw new UnsupportedOperationException();
                }

                public Collection values()
                {
                    Collection collection;
/* 581*/            if((collection = values) == null)
/* 583*/                values = collection = Collections.unmodifiableCollection(_flddelegate.values());
/* 585*/            return collection;
                }

                protected volatile Object _mthdelegate()
                {
/* 491*/            return _mthdelegate();
                }

                final Multimap _flddelegate;
                transient Collection entries;
                transient Multiset keys;
                transient Set keySet;
                transient Collection values;
                transient Map map;

                UnmodifiableMultimap(Multimap multimap)
                {
/* 501*/            _flddelegate = (Multimap)Preconditions.checkNotNull(multimap);
                }
    }

    static class CustomSortedSetMultimap extends AbstractSortedSetMultimap
    {

                protected SortedSet createCollection()
                {
/* 366*/            return (SortedSet)factory.get();
                }

                public Comparator valueComparator()
                {
/* 370*/            return valueComparator;
                }

                protected volatile Set createCollection()
                {
/* 353*/            return createCollection();
                }

                protected volatile Collection createCollection()
                {
/* 353*/            return createCollection();
                }

                transient Supplier factory;
                transient Comparator valueComparator;

                CustomSortedSetMultimap(Map map, Supplier supplier)
                {
/* 360*/            super(map);
/* 361*/            factory = (Supplier)Preconditions.checkNotNull(supplier);
/* 362*/            valueComparator = ((SortedSet)supplier.get()).comparator();
                }
    }

    static class CustomSetMultimap extends AbstractSetMultimap
    {

                protected Set createCollection()
                {
/* 286*/            return (Set)factory.get();
                }

                protected volatile Collection createCollection()
                {
/* 275*/            return createCollection();
                }

                transient Supplier factory;

                CustomSetMultimap(Map map, Supplier supplier)
                {
/* 281*/            super(map);
/* 282*/            factory = (Supplier)Preconditions.checkNotNull(supplier);
                }
    }

    static class CustomListMultimap extends AbstractListMultimap
    {

                protected List createCollection()
                {
/* 208*/            return (List)factory.get();
                }

                protected volatile Collection createCollection()
                {
/* 197*/            return createCollection();
                }

                transient Supplier factory;

                CustomListMultimap(Map map, Supplier supplier)
                {
/* 203*/            super(map);
/* 204*/            factory = (Supplier)Preconditions.checkNotNull(supplier);
                }
    }

    static class CustomMultimap extends AbstractMapBasedMultimap
    {

                protected Collection createCollection()
                {
/* 126*/            return (Collection)factory.get();
                }

                transient Supplier factory;

                CustomMultimap(Map map, Supplier supplier)
                {
/* 121*/            super(map);
/* 122*/            factory = (Supplier)Preconditions.checkNotNull(supplier);
                }
    }


            private Multimaps()
            {
            }

            public static Multimap newMultimap(Map map, Supplier supplier)
            {
/* 113*/        return new CustomMultimap(map, supplier);
            }

            public static ListMultimap newListMultimap(Map map, Supplier supplier)
            {
/* 194*/        return new CustomListMultimap(map, supplier);
            }

            public static SetMultimap newSetMultimap(Map map, Supplier supplier)
            {
/* 272*/        return new CustomSetMultimap(map, supplier);
            }

            public static SortedSetMultimap newSortedSetMultimap(Map map, Supplier supplier)
            {
/* 350*/        return new CustomSortedSetMultimap(map, supplier);
            }

            public static Multimap invertFrom(Multimap multimap, Multimap multimap1)
            {
/* 409*/        Preconditions.checkNotNull(multimap1);
                java.util.Map.Entry entry;
/* 410*/        for(multimap = multimap.entries().iterator(); multimap.hasNext(); multimap1.put(entry.getValue(), entry.getKey()))
/* 410*/            entry = (java.util.Map.Entry)multimap.next();

/* 413*/        return multimap1;
            }

            public static Multimap synchronizedMultimap(Multimap multimap)
            {
/* 451*/        return Synchronized.multimap(multimap, null);
            }

            public static Multimap unmodifiableMultimap(Multimap multimap)
            {
/* 473*/        if((multimap instanceof UnmodifiableMultimap) || (multimap instanceof ImmutableMultimap))
/* 475*/            return multimap;
/* 477*/        else
/* 477*/            return new UnmodifiableMultimap(multimap);
            }

            /**
             * @deprecated Method unmodifiableMultimap is deprecated
             */

            public static Multimap unmodifiableMultimap(ImmutableMultimap immutablemultimap)
            {
/* 488*/        return (Multimap)Preconditions.checkNotNull(immutablemultimap);
            }

            public static SetMultimap synchronizedSetMultimap(SetMultimap setmultimap)
            {
/* 679*/        return Synchronized.setMultimap(setmultimap, null);
            }

            public static SetMultimap unmodifiableSetMultimap(SetMultimap setmultimap)
            {
/* 702*/        if((setmultimap instanceof UnmodifiableSetMultimap) || (setmultimap instanceof ImmutableSetMultimap))
/* 704*/            return setmultimap;
/* 706*/        else
/* 706*/            return new UnmodifiableSetMultimap(setmultimap);
            }

            /**
             * @deprecated Method unmodifiableSetMultimap is deprecated
             */

            public static SetMultimap unmodifiableSetMultimap(ImmutableSetMultimap immutablesetmultimap)
            {
/* 717*/        return (SetMultimap)Preconditions.checkNotNull(immutablesetmultimap);
            }

            public static SortedSetMultimap synchronizedSortedSetMultimap(SortedSetMultimap sortedsetmultimap)
            {
/* 734*/        return Synchronized.sortedSetMultimap(sortedsetmultimap, null);
            }

            public static SortedSetMultimap unmodifiableSortedSetMultimap(SortedSetMultimap sortedsetmultimap)
            {
/* 757*/        if(sortedsetmultimap instanceof UnmodifiableSortedSetMultimap)
/* 758*/            return sortedsetmultimap;
/* 760*/        else
/* 760*/            return new UnmodifiableSortedSetMultimap(sortedsetmultimap);
            }

            public static ListMultimap synchronizedListMultimap(ListMultimap listmultimap)
            {
/* 774*/        return Synchronized.listMultimap(listmultimap, null);
            }

            public static ListMultimap unmodifiableListMultimap(ListMultimap listmultimap)
            {
/* 797*/        if((listmultimap instanceof UnmodifiableListMultimap) || (listmultimap instanceof ImmutableListMultimap))
/* 799*/            return listmultimap;
/* 801*/        else
/* 801*/            return new UnmodifiableListMultimap(listmultimap);
            }

            /**
             * @deprecated Method unmodifiableListMultimap is deprecated
             */

            public static ListMultimap unmodifiableListMultimap(ImmutableListMultimap immutablelistmultimap)
            {
/* 812*/        return (ListMultimap)Preconditions.checkNotNull(immutablelistmultimap);
            }

            private static Collection unmodifiableValueCollection(Collection collection)
            {
/* 825*/        if(collection instanceof SortedSet)
/* 826*/            return Collections.unmodifiableSortedSet((SortedSet)collection);
/* 827*/        if(collection instanceof Set)
/* 828*/            return Collections.unmodifiableSet((Set)collection);
/* 829*/        if(collection instanceof List)
/* 830*/            return Collections.unmodifiableList((List)collection);
/* 832*/        else
/* 832*/            return Collections.unmodifiableCollection(collection);
            }

            private static Collection unmodifiableEntries(Collection collection)
            {
/* 846*/        if(collection instanceof Set)
/* 847*/            return Maps.unmodifiableEntrySet((Set)collection);
/* 849*/        else
/* 849*/            return new Maps.UnmodifiableEntries(Collections.unmodifiableCollection(collection));
            }

            public static Map asMap(ListMultimap listmultimap)
            {
/* 863*/        return listmultimap.asMap();
            }

            public static Map asMap(SetMultimap setmultimap)
            {
/* 876*/        return setmultimap.asMap();
            }

            public static Map asMap(SortedSetMultimap sortedsetmultimap)
            {
/* 891*/        return sortedsetmultimap.asMap();
            }

            public static Map asMap(Multimap multimap)
            {
/* 902*/        return multimap.asMap();
            }

            public static SetMultimap forMap(Map map)
            {
/* 923*/        return new MapMultimap(map);
            }

            public static Multimap transformValues(Multimap multimap, Function function)
            {
/*1109*/        Preconditions.checkNotNull(function);
/*1110*/        function = Maps.asEntryTransformer(function);
/*1111*/        return transformEntries(multimap, function);
            }

            public static Multimap transformEntries(Multimap multimap, Maps.EntryTransformer entrytransformer)
            {
/*1172*/        return new TransformedEntriesMultimap(multimap, entrytransformer);
            }

            public static ListMultimap transformValues(ListMultimap listmultimap, Function function)
            {
/*1320*/        Preconditions.checkNotNull(function);
/*1321*/        function = Maps.asEntryTransformer(function);
/*1322*/        return transformEntries(listmultimap, function);
            }

            public static ListMultimap transformEntries(ListMultimap listmultimap, Maps.EntryTransformer entrytransformer)
            {
/*1380*/        return new TransformedEntriesListMultimap(listmultimap, entrytransformer);
            }

            public static ImmutableListMultimap index(Iterable iterable, Function function)
            {
/*1455*/        return index(iterable.iterator(), function);
            }

            public static ImmutableListMultimap index(Iterator iterator, Function function)
            {
/*1503*/        Preconditions.checkNotNull(function);
/*1504*/        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
                Object obj;
/*1506*/        for(; iterator.hasNext(); builder.put(function.apply(obj), obj))
/*1507*/            Preconditions.checkNotNull(obj = iterator.next(), iterator);

/*1511*/        return builder.build();
            }

            public static Multimap filterKeys(Multimap multimap, Predicate predicate)
            {
/*1773*/        if(multimap instanceof SetMultimap)
/*1774*/            return filterKeys((SetMultimap)multimap, predicate);
/*1775*/        if(multimap instanceof ListMultimap)
/*1776*/            return filterKeys((ListMultimap)multimap, predicate);
/*1777*/        if(multimap instanceof FilteredKeyMultimap)
                {
/*1778*/            multimap = (FilteredKeyMultimap)multimap;
/*1779*/            return new FilteredKeyMultimap(((FilteredKeyMultimap) (multimap)).unfiltered, Predicates.and(((FilteredKeyMultimap) (multimap)).keyPredicate, predicate));
                }
/*1781*/        if(multimap instanceof FilteredMultimap)
/*1782*/            return filterFiltered(multimap = (FilteredMultimap)multimap, Maps.keyPredicateOnEntries(predicate));
/*1785*/        else
/*1785*/            return new FilteredKeyMultimap(multimap, predicate);
            }

            public static SetMultimap filterKeys(SetMultimap setmultimap, Predicate predicate)
            {
/*1821*/        if(setmultimap instanceof FilteredKeySetMultimap)
                {
/*1822*/            setmultimap = (FilteredKeySetMultimap)setmultimap;
/*1823*/            return new FilteredKeySetMultimap(setmultimap.unfiltered(), Predicates.and(((FilteredKeySetMultimap) (setmultimap)).keyPredicate, predicate));
                }
/*1825*/        if(setmultimap instanceof FilteredSetMultimap)
/*1826*/            return filterFiltered(setmultimap = (FilteredSetMultimap)setmultimap, Maps.keyPredicateOnEntries(predicate));
/*1829*/        else
/*1829*/            return new FilteredKeySetMultimap(setmultimap, predicate);
            }

            public static ListMultimap filterKeys(ListMultimap listmultimap, Predicate predicate)
            {
/*1865*/        if(listmultimap instanceof FilteredKeyListMultimap)
                {
/*1866*/            listmultimap = (FilteredKeyListMultimap)listmultimap;
/*1867*/            return new FilteredKeyListMultimap(listmultimap.unfiltered(), Predicates.and(((FilteredKeyListMultimap) (listmultimap)).keyPredicate, predicate));
                } else
                {
/*1870*/            return new FilteredKeyListMultimap(listmultimap, predicate);
                }
            }

            public static Multimap filterValues(Multimap multimap, Predicate predicate)
            {
/*1906*/        return filterEntries(multimap, Maps.valuePredicateOnEntries(predicate));
            }

            public static SetMultimap filterValues(SetMultimap setmultimap, Predicate predicate)
            {
/*1941*/        return filterEntries(setmultimap, Maps.valuePredicateOnEntries(predicate));
            }

            public static Multimap filterEntries(Multimap multimap, Predicate predicate)
            {
/*1974*/        Preconditions.checkNotNull(predicate);
/*1975*/        if(multimap instanceof SetMultimap)
/*1976*/            return filterEntries((SetMultimap)multimap, predicate);
/*1978*/        if(multimap instanceof FilteredMultimap)
/*1978*/            return filterFiltered((FilteredMultimap)multimap, predicate);
/*1978*/        else
/*1978*/            return new FilteredEntryMultimap((Multimap)Preconditions.checkNotNull(multimap), predicate);
            }

            public static SetMultimap filterEntries(SetMultimap setmultimap, Predicate predicate)
            {
/*2013*/        Preconditions.checkNotNull(predicate);
/*2014*/        if(setmultimap instanceof FilteredSetMultimap)
/*2014*/            return filterFiltered((FilteredSetMultimap)setmultimap, predicate);
/*2014*/        else
/*2014*/            return new FilteredEntrySetMultimap((SetMultimap)Preconditions.checkNotNull(setmultimap), predicate);
            }

            private static Multimap filterFiltered(FilteredMultimap filteredmultimap, Predicate predicate)
            {
/*2028*/        predicate = Predicates.and(filteredmultimap.entryPredicate(), predicate);
/*2030*/        return new FilteredEntryMultimap(filteredmultimap.unfiltered(), predicate);
            }

            private static SetMultimap filterFiltered(FilteredSetMultimap filteredsetmultimap, Predicate predicate)
            {
/*2042*/        predicate = Predicates.and(filteredsetmultimap.entryPredicate(), predicate);
/*2044*/        return new FilteredEntrySetMultimap(filteredsetmultimap.unfiltered(), predicate);
            }

            static boolean equalsImpl(Multimap multimap, Object obj)
            {
/*2048*/        if(obj == multimap)
/*2049*/            return true;
/*2051*/        if(obj instanceof Multimap)
                {
/*2052*/            obj = (Multimap)obj;
/*2053*/            return multimap.asMap().equals(((Multimap) (obj)).asMap());
                } else
                {
/*2055*/            return false;
                }
            }


}
