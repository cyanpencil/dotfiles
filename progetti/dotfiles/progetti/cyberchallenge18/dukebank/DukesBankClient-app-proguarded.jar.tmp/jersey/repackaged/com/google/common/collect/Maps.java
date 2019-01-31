// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            BiMap, CollectPreconditions, Collections2, ImmutableEntry, 
//            ImmutableEnumMap, ImmutableMap, Iterators, MapDifference, 
//            MapMaker, Ordering, Platform, Synchronized, 
//            UnmodifiableIterator, SortedMapDifference, ForwardingMap, Sets, 
//            ForwardingSortedMap, Iterables, Lists, ForwardingSet, 
//            TransformedIterator, ForwardingMapEntry, AbstractNavigableMap, ForwardingCollection, 
//            AbstractMapEntry, ForwardingNavigableSet, ForwardingSortedSet

public final class Maps
{
    static abstract class DescendingMap extends ForwardingMap
        implements NavigableMap
    {

                abstract NavigableMap forward();

                protected final Map _mthdelegate()
                {
/*3801*/            return forward();
                }

                public Comparator comparator()
                {
                    Object obj;
/*3809*/            if((obj = comparator) == null)
                    {
/*3811*/                if((obj = forward().comparator()) == null)
/*3813*/                    obj = Ordering.natural();
/*3815*/                obj = comparator = reverse(((Comparator) (obj)));
                    }
/*3817*/            return ((Comparator) (obj));
                }

                private static Ordering reverse(Comparator comparator1)
                {
/*3822*/            return Ordering.from(comparator1).reverse();
                }

                public Object firstKey()
                {
/*3827*/            return forward().lastKey();
                }

                public Object lastKey()
                {
/*3832*/            return forward().firstKey();
                }

                public java.util.Map.Entry lowerEntry(Object obj)
                {
/*3837*/            return forward().higherEntry(obj);
                }

                public Object lowerKey(Object obj)
                {
/*3842*/            return forward().higherKey(obj);
                }

                public java.util.Map.Entry floorEntry(Object obj)
                {
/*3847*/            return forward().ceilingEntry(obj);
                }

                public Object floorKey(Object obj)
                {
/*3852*/            return forward().ceilingKey(obj);
                }

                public java.util.Map.Entry ceilingEntry(Object obj)
                {
/*3857*/            return forward().floorEntry(obj);
                }

                public Object ceilingKey(Object obj)
                {
/*3862*/            return forward().floorKey(obj);
                }

                public java.util.Map.Entry higherEntry(Object obj)
                {
/*3867*/            return forward().lowerEntry(obj);
                }

                public Object higherKey(Object obj)
                {
/*3872*/            return forward().lowerKey(obj);
                }

                public java.util.Map.Entry firstEntry()
                {
/*3877*/            return forward().lastEntry();
                }

                public java.util.Map.Entry lastEntry()
                {
/*3882*/            return forward().firstEntry();
                }

                public java.util.Map.Entry pollFirstEntry()
                {
/*3887*/            return forward().pollLastEntry();
                }

                public java.util.Map.Entry pollLastEntry()
                {
/*3892*/            return forward().pollFirstEntry();
                }

                public NavigableMap descendingMap()
                {
/*3897*/            return forward();
                }

                public Set entrySet()
                {
                    Set set;
/*3904*/            if((set = entrySet) == null)
/*3905*/                return entrySet = createEntrySet();
/*3905*/            else
/*3905*/                return set;
                }

                abstract Iterator entryIterator();

                Set createEntrySet()
                {
/*3911*/            return new EntrySet() {

                        Map map()
                        {
/*3914*/                    return DescendingMap.this;
                        }

                        public Iterator iterator()
                        {
/*3919*/                    return entryIterator();
                        }

                        final DescendingMap this$0;

                        
                        {
/*3911*/                    this$0 = DescendingMap.this;
/*3911*/                    super();
                        }
            };
                }

                public Set keySet()
                {
/*3926*/            return navigableKeySet();
                }

                public NavigableSet navigableKeySet()
                {
                    NavigableSet navigableset;
/*3933*/            if((navigableset = navigableKeySet) == null)
/*3934*/                return navigableKeySet = new NavigableKeySet(this);
/*3934*/            else
/*3934*/                return navigableset;
                }

                public NavigableSet descendingKeySet()
                {
/*3939*/            return forward().navigableKeySet();
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*3946*/            return forward().subMap(obj1, flag1, obj, flag).descendingMap();
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*3951*/            return forward().tailMap(obj, flag).descendingMap();
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*3956*/            return forward().headMap(obj, flag).descendingMap();
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*3961*/            return subMap(obj, true, obj1, false);
                }

                public SortedMap headMap(Object obj)
                {
/*3966*/            return headMap(obj, false);
                }

                public SortedMap tailMap(Object obj)
                {
/*3971*/            return tailMap(obj, true);
                }

                public Collection values()
                {
/*3976*/            return new Values(this);
                }

                public String toString()
                {
/*3981*/            return standardToString();
                }

                protected volatile Object _mthdelegate()
                {
/*3793*/            return _mthdelegate();
                }

                private transient Comparator comparator;
                private transient Set entrySet;
                private transient NavigableSet navigableKeySet;

                DescendingMap()
                {
                }
    }

    static abstract class EntrySet extends Sets.ImprovedAbstractSet
    {

                abstract Map map();

                public int size()
                {
/*3737*/            return map().size();
                }

                public void clear()
                {
/*3741*/            map().clear();
                }

                public boolean contains(Object obj)
                {
/*3745*/            if(obj instanceof java.util.Map.Entry)
                    {
/*3746*/                Object obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey();
                        Object obj2;
/*3748*/                return Objects.equal(obj2 = Maps.safeGet(map(), obj1), ((java.util.Map.Entry) (obj)).getValue()) && (obj2 != null || map().containsKey(obj1));
                    } else
                    {
/*3752*/                return false;
                    }
                }

                public boolean isEmpty()
                {
/*3756*/            return map().isEmpty();
                }

                public boolean remove(Object obj)
                {
/*3760*/            if(contains(obj))
                    {
/*3761*/                obj = (java.util.Map.Entry)obj;
/*3762*/                return map().keySet().remove(((java.util.Map.Entry) (obj)).getKey());
                    } else
                    {
/*3764*/                return false;
                    }
                }

                public boolean removeAll(Collection collection)
                {
/*3769*/            return super.removeAll((Collection)Preconditions.checkNotNull(collection));
/*3770*/            JVM INSTR pop ;
/*3772*/            return Sets.removeAllImpl(this, collection.iterator());
                }

                public boolean retainAll(Collection collection)
                {
/*3778*/            return super.retainAll((Collection)Preconditions.checkNotNull(collection));
/*3779*/            JVM INSTR pop ;
/*3781*/            HashSet hashset = Sets.newHashSetWithExpectedSize(collection.size());
/*3782*/            collection = collection.iterator();
/*3782*/            do
                    {
/*3782*/                if(!collection.hasNext())
/*3782*/                    break;
/*3782*/                Object obj = collection.next();
/*3783*/                if(contains(obj))
                        {
/*3784*/                    obj = (java.util.Map.Entry)obj;
/*3785*/                    hashset.add(((java.util.Map.Entry) (obj)).getKey());
                        }
                    } while(true);
/*3788*/            return map().keySet().retainAll(hashset);
                }

                EntrySet()
                {
                }
    }

    static class Values extends AbstractCollection
    {

                final Map map()
                {
/*3666*/            return map;
                }

                public Iterator iterator()
                {
/*3670*/            return Maps.valueIterator(map().entrySet().iterator());
                }

                public boolean remove(Object obj)
                {
/*3675*/            return super.remove(obj);
/*3676*/            JVM INSTR pop ;
/*3677*/            for(Iterator iterator1 = map().entrySet().iterator(); iterator1.hasNext();)
                    {
/*3677*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
/*3678*/                if(Objects.equal(obj, entry.getValue()))
                        {
/*3679*/                    map().remove(entry.getKey());
/*3680*/                    return true;
                        }
                    }

/*3683*/            return false;
                }

                public boolean removeAll(Collection collection)
                {
/*3689*/            return super.removeAll((Collection)Preconditions.checkNotNull(collection));
/*3690*/            JVM INSTR pop ;
/*3691*/            HashSet hashset = Sets.newHashSet();
/*3692*/            Iterator iterator1 = map().entrySet().iterator();
/*3692*/            do
                    {
/*3692*/                if(!iterator1.hasNext())
/*3692*/                    break;
/*3692*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
/*3693*/                if(collection.contains(entry.getValue()))
/*3694*/                    hashset.add(entry.getKey());
                    } while(true);
/*3697*/            return map().keySet().removeAll(hashset);
                }

                public boolean retainAll(Collection collection)
                {
/*3703*/            return super.retainAll((Collection)Preconditions.checkNotNull(collection));
/*3704*/            JVM INSTR pop ;
/*3705*/            HashSet hashset = Sets.newHashSet();
/*3706*/            Iterator iterator1 = map().entrySet().iterator();
/*3706*/            do
                    {
/*3706*/                if(!iterator1.hasNext())
/*3706*/                    break;
/*3706*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
/*3707*/                if(collection.contains(entry.getValue()))
/*3708*/                    hashset.add(entry.getKey());
                    } while(true);
/*3711*/            return map().keySet().retainAll(hashset);
                }

                public int size()
                {
/*3716*/            return map().size();
                }

                public boolean isEmpty()
                {
/*3720*/            return map().isEmpty();
                }

                public boolean contains(Object obj)
                {
/*3724*/            return map().containsValue(obj);
                }

                public void clear()
                {
/*3728*/            map().clear();
                }

                final Map map;

                Values(Map map1)
                {
/*3662*/            map = (Map)Preconditions.checkNotNull(map1);
                }
    }

    static class NavigableKeySet extends SortedKeySet
        implements NavigableSet
    {

                NavigableMap map()
                {
/*3580*/            return (NavigableMap)map;
                }

                public Object lower(Object obj)
                {
/*3585*/            return map().lowerKey(obj);
                }

                public Object floor(Object obj)
                {
/*3590*/            return map().floorKey(obj);
                }

                public Object ceiling(Object obj)
                {
/*3595*/            return map().ceilingKey(obj);
                }

                public Object higher(Object obj)
                {
/*3600*/            return map().higherKey(obj);
                }

                public Object pollFirst()
                {
/*3605*/            return Maps.keyOrNull(map().pollFirstEntry());
                }

                public Object pollLast()
                {
/*3610*/            return Maps.keyOrNull(map().pollLastEntry());
                }

                public NavigableSet descendingSet()
                {
/*3615*/            return map().descendingKeySet();
                }

                public Iterator descendingIterator()
                {
/*3620*/            return descendingSet().iterator();
                }

                public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*3629*/            return map().subMap(obj, flag, obj1, flag1).navigableKeySet();
                }

                public NavigableSet headSet(Object obj, boolean flag)
                {
/*3634*/            return map().headMap(obj, flag).navigableKeySet();
                }

                public NavigableSet tailSet(Object obj, boolean flag)
                {
/*3639*/            return map().tailMap(obj, flag).navigableKeySet();
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/*3644*/            return subSet(obj, true, obj1, false);
                }

                public SortedSet headSet(Object obj)
                {
/*3649*/            return headSet(obj, false);
                }

                public SortedSet tailSet(Object obj)
                {
/*3654*/            return tailSet(obj, true);
                }

                volatile SortedMap map()
                {
/*3572*/            return map();
                }

                volatile Map map()
                {
/*3572*/            return map();
                }

                NavigableKeySet(NavigableMap navigablemap)
                {
/*3575*/            super(navigablemap);
                }
    }

    static class SortedKeySet extends KeySet
        implements SortedSet
    {

                SortedMap map()
                {
/*3538*/            return (SortedMap)super.map();
                }

                public Comparator comparator()
                {
/*3543*/            return map().comparator();
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/*3548*/            return new SortedKeySet(map().subMap(obj, obj1));
                }

                public SortedSet headSet(Object obj)
                {
/*3553*/            return new SortedKeySet(map().headMap(obj));
                }

                public SortedSet tailSet(Object obj)
                {
/*3558*/            return new SortedKeySet(map().tailMap(obj));
                }

                public Object first()
                {
/*3563*/            return map().firstKey();
                }

                public Object last()
                {
/*3568*/            return map().lastKey();
                }

                volatile Map map()
                {
/*3531*/            return map();
                }

                SortedKeySet(SortedMap sortedmap)
                {
/*3533*/            super(sortedmap);
                }
    }

    static class KeySet extends Sets.ImprovedAbstractSet
    {

                Map map()
                {
/*3489*/            return map;
                }

                public Iterator iterator()
                {
/*3493*/            return Maps.keyIterator(map().entrySet().iterator());
                }

                public int size()
                {
/*3497*/            return map().size();
                }

                public boolean isEmpty()
                {
/*3501*/            return map().isEmpty();
                }

                public boolean contains(Object obj)
                {
/*3505*/            return map().containsKey(obj);
                }

                public boolean remove(Object obj)
                {
/*3509*/            if(contains(obj))
                    {
/*3510*/                map().remove(obj);
/*3511*/                return true;
                    } else
                    {
/*3513*/                return false;
                    }
                }

                public void clear()
                {
/*3517*/            map().clear();
                }

                final Map map;

                KeySet(Map map1)
                {
/*3485*/            map = (Map)Preconditions.checkNotNull(map1);
                }
    }

    static abstract class ImprovedAbstractMap extends AbstractMap
    {

                abstract Set createEntrySet();

                public Set entrySet()
                {
                    Set set;
/*3319*/            if((set = entrySet) == null)
/*3320*/                return entrySet = createEntrySet();
/*3320*/            else
/*3320*/                return set;
                }

                public Set keySet()
                {
                    Set set;
/*3326*/            if((set = keySet) == null)
/*3327*/                return keySet = createKeySet();
/*3327*/            else
/*3327*/                return set;
                }

                Set createKeySet()
                {
/*3331*/            return new KeySet(this);
                }

                public Collection values()
                {
                    Collection collection;
/*3337*/            if((collection = values) == null)
/*3338*/                return values = createValues();
/*3338*/            else
/*3338*/                return collection;
                }

                Collection createValues()
                {
/*3342*/            return new Values(this);
                }

                private transient Set entrySet;
                private transient Set keySet;
                private transient Collection values;

                ImprovedAbstractMap()
                {
                }
    }

    static class UnmodifiableNavigableMap extends ForwardingSortedMap
        implements Serializable, NavigableMap
    {

                protected SortedMap _mthdelegate()
                {
/*3121*/            return Collections.unmodifiableSortedMap(_flddelegate);
                }

                public java.util.Map.Entry lowerEntry(Object obj)
                {
/*3126*/            return Maps.unmodifiableOrNull(_flddelegate.lowerEntry(obj));
                }

                public Object lowerKey(Object obj)
                {
/*3131*/            return _flddelegate.lowerKey(obj);
                }

                public java.util.Map.Entry floorEntry(Object obj)
                {
/*3136*/            return Maps.unmodifiableOrNull(_flddelegate.floorEntry(obj));
                }

                public Object floorKey(Object obj)
                {
/*3141*/            return _flddelegate.floorKey(obj);
                }

                public java.util.Map.Entry ceilingEntry(Object obj)
                {
/*3146*/            return Maps.unmodifiableOrNull(_flddelegate.ceilingEntry(obj));
                }

                public Object ceilingKey(Object obj)
                {
/*3151*/            return _flddelegate.ceilingKey(obj);
                }

                public java.util.Map.Entry higherEntry(Object obj)
                {
/*3156*/            return Maps.unmodifiableOrNull(_flddelegate.higherEntry(obj));
                }

                public Object higherKey(Object obj)
                {
/*3161*/            return _flddelegate.higherKey(obj);
                }

                public java.util.Map.Entry firstEntry()
                {
/*3166*/            return Maps.unmodifiableOrNull(_flddelegate.firstEntry());
                }

                public java.util.Map.Entry lastEntry()
                {
/*3171*/            return Maps.unmodifiableOrNull(_flddelegate.lastEntry());
                }

                public final java.util.Map.Entry pollFirstEntry()
                {
/*3176*/            throw new UnsupportedOperationException();
                }

                public final java.util.Map.Entry pollLastEntry()
                {
/*3181*/            throw new UnsupportedOperationException();
                }

                public NavigableMap descendingMap()
                {
                    UnmodifiableNavigableMap unmodifiablenavigablemap;
/*3188*/            if((unmodifiablenavigablemap = descendingMap) == null)
/*3189*/                return descendingMap = new UnmodifiableNavigableMap(_flddelegate.descendingMap(), this);
/*3189*/            else
/*3189*/                return unmodifiablenavigablemap;
                }

                public Set keySet()
                {
/*3196*/            return navigableKeySet();
                }

                public NavigableSet navigableKeySet()
                {
/*3201*/            return Sets.unmodifiableNavigableSet(_flddelegate.navigableKeySet());
                }

                public NavigableSet descendingKeySet()
                {
/*3206*/            return Sets.unmodifiableNavigableSet(_flddelegate.descendingKeySet());
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*3211*/            return subMap(obj, true, obj1, false);
                }

                public SortedMap headMap(Object obj)
                {
/*3216*/            return headMap(obj, false);
                }

                public SortedMap tailMap(Object obj)
                {
/*3221*/            return tailMap(obj, true);
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*3228*/            return Maps.unmodifiableNavigableMap(_flddelegate.subMap(obj, flag, obj1, flag1));
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*3237*/            return Maps.unmodifiableNavigableMap(_flddelegate.headMap(obj, flag));
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*3242*/            return Maps.unmodifiableNavigableMap(_flddelegate.tailMap(obj, flag));
                }

                protected volatile Map _mthdelegate()
                {
/*3104*/            return _mthdelegate();
                }

                protected volatile Object _mthdelegate()
                {
/*3104*/            return _mthdelegate();
                }

                private final NavigableMap _flddelegate;
                private transient UnmodifiableNavigableMap descendingMap;

                UnmodifiableNavigableMap(NavigableMap navigablemap)
                {
/*3110*/            _flddelegate = navigablemap;
                }

                UnmodifiableNavigableMap(NavigableMap navigablemap, UnmodifiableNavigableMap unmodifiablenavigablemap)
                {
/*3115*/            _flddelegate = navigablemap;
/*3116*/            descendingMap = unmodifiablenavigablemap;
                }
    }

    static final class FilteredEntryBiMap extends FilteredEntryMap
        implements BiMap
    {

                private static Predicate inversePredicate(Predicate predicate)
                {
/*3034*/            return new Predicate(predicate) {

                        public final boolean apply(java.util.Map.Entry entry)
                        {
/*3037*/                    return forwardPredicate.apply(Maps.immutableEntry(entry.getValue(), entry.getKey()));
                        }

                        public final volatile boolean apply(Object obj)
                        {
/*3034*/                    return apply((java.util.Map.Entry)obj);
                        }

                        final Predicate val$forwardPredicate;

                        
                        {
/*3034*/                    forwardPredicate = predicate;
/*3034*/                    super();
                        }
            };
                }

                final BiMap unfiltered()
                {
/*3058*/            return (BiMap)unfiltered;
                }

                public final BiMap inverse()
                {
/*3069*/            return inverse;
                }

                public final Set values()
                {
/*3074*/            return inverse.keySet();
                }

                public final volatile Collection values()
                {
/*3028*/            return values();
                }

                private final BiMap inverse;

                FilteredEntryBiMap(BiMap bimap, Predicate predicate)
                {
/*3045*/            super(bimap, predicate);
/*3046*/            inverse = new FilteredEntryBiMap(bimap.inverse(), inversePredicate(predicate), this);
                }

                private FilteredEntryBiMap(BiMap bimap, Predicate predicate, BiMap bimap1)
                {
/*3053*/            super(bimap, predicate);
/*3054*/            inverse = bimap1;
                }
    }

    static class FilteredEntryNavigableMap extends AbstractNavigableMap
    {

                public Comparator comparator()
                {
/*2904*/            return unfiltered.comparator();
                }

                public NavigableSet navigableKeySet()
                {
/*2909*/            return new NavigableKeySet(this) {

                        public boolean removeAll(Collection collection)
                        {
/*2912*/                    return Iterators.removeIf(unfiltered.entrySet().iterator(), Predicates.and(entryPredicate, Maps.keyPredicateOnEntries(Predicates.in(collection))));
                        }

                        public boolean retainAll(Collection collection)
                        {
/*2918*/                    return Iterators.removeIf(unfiltered.entrySet().iterator(), Predicates.and(entryPredicate, Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection)))));
                        }

                        final FilteredEntryNavigableMap this$0;

                        
                        {
/*2909*/                    this$0 = FilteredEntryNavigableMap.this;
/*2909*/                    super(navigablemap);
                        }
            };
                }

                public Collection values()
                {
/*2926*/            return new FilteredMapValues(this, unfiltered, entryPredicate);
                }

                Iterator entryIterator()
                {
/*2931*/            return Iterators.filter(unfiltered.entrySet().iterator(), entryPredicate);
                }

                Iterator descendingEntryIterator()
                {
/*2936*/            return Iterators.filter(unfiltered.descendingMap().entrySet().iterator(), entryPredicate);
                }

                public int size()
                {
/*2941*/            return filteredDelegate.size();
                }

                public boolean isEmpty()
                {
/*2946*/            return !Iterables.any(unfiltered.entrySet(), entryPredicate);
                }

                public Object get(Object obj)
                {
/*2952*/            return filteredDelegate.get(obj);
                }

                public boolean containsKey(Object obj)
                {
/*2957*/            return filteredDelegate.containsKey(obj);
                }

                public Object put(Object obj, Object obj1)
                {
/*2962*/            return filteredDelegate.put(obj, obj1);
                }

                public Object remove(Object obj)
                {
/*2967*/            return filteredDelegate.remove(obj);
                }

                public void putAll(Map map)
                {
/*2972*/            filteredDelegate.putAll(map);
                }

                public void clear()
                {
/*2977*/            filteredDelegate.clear();
                }

                public Set entrySet()
                {
/*2982*/            return filteredDelegate.entrySet();
                }

                public java.util.Map.Entry pollFirstEntry()
                {
/*2987*/            return (java.util.Map.Entry)Iterables.removeFirstMatching(unfiltered.entrySet(), entryPredicate);
                }

                public java.util.Map.Entry pollLastEntry()
                {
/*2992*/            return (java.util.Map.Entry)Iterables.removeFirstMatching(unfiltered.descendingMap().entrySet(), entryPredicate);
                }

                public NavigableMap descendingMap()
                {
/*2997*/            return Maps.filterEntries(unfiltered.descendingMap(), entryPredicate);
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*3003*/            return Maps.filterEntries(unfiltered.subMap(obj, flag, obj1, flag1), entryPredicate);
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*3009*/            return Maps.filterEntries(unfiltered.headMap(obj, flag), entryPredicate);
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*3014*/            return Maps.filterEntries(unfiltered.tailMap(obj, flag), entryPredicate);
                }

                private final NavigableMap unfiltered;
                private final Predicate entryPredicate;
                private final Map filteredDelegate;



                FilteredEntryNavigableMap(NavigableMap navigablemap, Predicate predicate)
                {
/*2897*/            unfiltered = (NavigableMap)Preconditions.checkNotNull(navigablemap);
/*2898*/            entryPredicate = predicate;
/*2899*/            filteredDelegate = new FilteredEntryMap(navigablemap, predicate);
                }
    }

    static class FilteredEntrySortedMap extends FilteredEntryMap
        implements SortedMap
    {
        class SortedKeySet extends FilteredEntryMap.KeySet
            implements SortedSet
        {

                    public Comparator comparator()
                    {
/*2805*/                return sortedMap().comparator();
                    }

                    public SortedSet subSet(Object obj, Object obj1)
                    {
/*2810*/                return (SortedSet)subMap(obj, obj1).keySet();
                    }

                    public SortedSet headSet(Object obj)
                    {
/*2815*/                return (SortedSet)headMap(obj).keySet();
                    }

                    public SortedSet tailSet(Object obj)
                    {
/*2820*/                return (SortedSet)tailMap(obj).keySet();
                    }

                    public Object first()
                    {
/*2825*/                return firstKey();
                    }

                    public Object last()
                    {
/*2830*/                return lastKey();
                    }

                    final FilteredEntrySortedMap this$0;

                    SortedKeySet()
                    {
/*2802*/                this$0 = FilteredEntrySortedMap.this;
/*2802*/                super();
                    }
        }


                SortedMap sortedMap()
                {
/*2790*/            return (SortedMap)unfiltered;
                }

                public SortedSet keySet()
                {
/*2794*/            return (SortedSet)super.keySet();
                }

                SortedSet createKeySet()
                {
/*2799*/            return new SortedKeySet();
                }

                public Comparator comparator()
                {
/*2835*/            return sortedMap().comparator();
                }

                public Object firstKey()
                {
/*2840*/            return keySet().iterator().next();
                }

                public Object lastKey()
                {
/*2844*/            Object obj = sortedMap();
/*2847*/            do
                    {
/*2847*/                obj = ((SortedMap) (obj)).lastKey();
/*2848*/                if(apply(obj, unfiltered.get(obj)))
/*2849*/                    return obj;
/*2851*/                obj = sortedMap().headMap(obj);
                    } while(true);
                }

                public SortedMap headMap(Object obj)
                {
/*2856*/            return new FilteredEntrySortedMap(sortedMap().headMap(obj), predicate);
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*2860*/            return new FilteredEntrySortedMap(sortedMap().subMap(obj, obj1), predicate);
                }

                public SortedMap tailMap(Object obj)
                {
/*2865*/            return new FilteredEntrySortedMap(sortedMap().tailMap(obj), predicate);
                }

                volatile Set createKeySet()
                {
/*2781*/            return createKeySet();
                }

                public volatile Set keySet()
                {
/*2781*/            return keySet();
                }

                FilteredEntrySortedMap(SortedMap sortedmap, Predicate predicate)
                {
/*2786*/            super(sortedmap, predicate);
                }
    }

    static class FilteredEntryMap extends AbstractFilteredMap
    {
        class KeySet extends KeySet
        {

                    public boolean remove(Object obj)
                    {
/*2736*/                if(containsKey(obj))
                        {
/*2737*/                    unfiltered.remove(obj);
/*2738*/                    return true;
                        } else
                        {
/*2740*/                    return false;
                        }
                    }

                    private boolean removeIf(Predicate predicate)
                    {
/*2744*/                return Iterables.removeIf(unfiltered.entrySet(), Predicates.and(FilteredEntryMap.this.predicate, Maps.keyPredicateOnEntries(predicate)));
                    }

                    public boolean removeAll(Collection collection)
                    {
/*2750*/                return removeIf(Predicates.in(collection));
                    }

                    public boolean retainAll(Collection collection)
                    {
/*2755*/                return removeIf(Predicates.not(Predicates.in(collection)));
                    }

                    public Object[] toArray()
                    {
/*2760*/                return Lists.newArrayList(iterator()).toArray();
                    }

                    public Object[] toArray(Object aobj[])
                    {
/*2764*/                return Lists.newArrayList(iterator()).toArray(aobj);
                    }

                    final FilteredEntryMap this$0;

                    KeySet()
                    {
/*2731*/                this$0 = FilteredEntryMap.this;
/*2732*/                super(FilteredEntryMap.this);
                    }
        }

        class EntrySet extends ForwardingSet
        {

                    protected Set _mthdelegate()
                    {
/*2701*/                return filteredEntrySet;
                    }

                    public Iterator iterator()
                    {
/*2705*/                return new TransformedIterator(filteredEntrySet.iterator()) {

                            java.util.Map.Entry transform(final java.util.Map.Entry entry)
                            {
/*2708*/                        return new ForwardingMapEntry() {

                                    protected java.util.Map.Entry _mthdelegate()
                                    {
/*2711*/                                return entry;
                                    }

                                    public Object setValue(Object obj)
                                    {
/*2716*/                                Preconditions.checkArgument(apply(getKey(), obj));
/*2717*/                                return super.setValue(obj);
                                    }

                                    protected volatile Object _mthdelegate()
                                    {
/*2708*/                                return _mthdelegate();
                                    }

                                    final java.util.Map.Entry val$entry;
                                    final _cls1 this$2;

                                    
                                    {
/*2708*/                                this$2 = _cls1.this;
/*2708*/                                entry = entry1;
/*2708*/                                super();
                                    }
                        };
                            }

                            volatile Object transform(Object obj)
                            {
/*2705*/                        return transform((java.util.Map.Entry)obj);
                            }

                            final EntrySet this$1;

                            
                            {
/*2705*/                        this$1 = EntrySet.this;
/*2705*/                        super(iterator1);
                            }
                };
                    }

                    protected volatile Collection _mthdelegate()
                    {
/*2699*/                return _mthdelegate();
                    }

                    protected volatile Object _mthdelegate()
                    {
/*2699*/                return _mthdelegate();
                    }

                    final FilteredEntryMap this$0;

                    private EntrySet()
                    {
/*2699*/                this$0 = FilteredEntryMap.this;
/*2699*/                super();
                    }

        }


                protected Set createEntrySet()
                {
/*2696*/            return new EntrySet();
                }

                Set createKeySet()
                {
/*2727*/            return new KeySet();
                }

                final Set filteredEntrySet;

                FilteredEntryMap(Map map, Predicate predicate)
                {
/*2690*/            super(map, predicate);
/*2691*/            filteredEntrySet = Sets.filter(map.entrySet(), this.predicate);
                }
    }

    static class FilteredKeyMap extends AbstractFilteredMap
    {

                protected Set createEntrySet()
                {
/*2664*/            return Sets.filter(unfiltered.entrySet(), predicate);
                }

                Set createKeySet()
                {
/*2669*/            return Sets.filter(unfiltered.keySet(), keyPredicate);
                }

                public boolean containsKey(Object obj)
                {
/*2677*/            return unfiltered.containsKey(obj) && keyPredicate.apply(obj);
                }

                Predicate keyPredicate;

                FilteredKeyMap(Map map, Predicate predicate, Predicate predicate1)
                {
/*2658*/            super(map, predicate1);
/*2659*/            keyPredicate = predicate;
                }
    }

    static final class FilteredMapValues extends Values
    {

                public final boolean remove(Object obj)
                {
/*2625*/            return Iterables.removeFirstMatching(unfiltered.entrySet(), Predicates.and(predicate, Maps.valuePredicateOnEntries(Predicates.equalTo(obj)))) != null;
                }

                private boolean removeIf(Predicate predicate1)
                {
/*2631*/            return Iterables.removeIf(unfiltered.entrySet(), Predicates.and(predicate, Maps.valuePredicateOnEntries(predicate1)));
                }

                public final boolean removeAll(Collection collection)
                {
/*2636*/            return removeIf(Predicates.in(collection));
                }

                public final boolean retainAll(Collection collection)
                {
/*2640*/            return removeIf(Predicates.not(Predicates.in(collection)));
                }

                public final Object[] toArray()
                {
/*2645*/            return Lists.newArrayList(iterator()).toArray();
                }

                public final Object[] toArray(Object aobj[])
                {
/*2649*/            return Lists.newArrayList(iterator()).toArray(aobj);
                }

                Map unfiltered;
                Predicate predicate;

                FilteredMapValues(Map map, Map map1, Predicate predicate1)
                {
/*2619*/            super(map);
/*2620*/            unfiltered = map1;
/*2621*/            predicate = predicate1;
                }
    }

    static abstract class AbstractFilteredMap extends ImprovedAbstractMap
    {

                boolean apply(Object obj, Object obj1)
                {
/*2574*/            obj = obj;
/*2575*/            return predicate.apply(Maps.immutableEntry(obj, obj1));
                }

                public Object put(Object obj, Object obj1)
                {
/*2579*/            Preconditions.checkArgument(apply(obj, obj1));
/*2580*/            return unfiltered.put(obj, obj1);
                }

                public void putAll(Map map)
                {
                    java.util.Map.Entry entry;
/*2584*/            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); Preconditions.checkArgument(apply(entry.getKey(), entry.getValue())))
/*2584*/                entry = (java.util.Map.Entry)iterator.next();

/*2587*/            unfiltered.putAll(map);
                }

                public boolean containsKey(Object obj)
                {
/*2591*/            return unfiltered.containsKey(obj) && apply(obj, unfiltered.get(obj));
                }

                public Object get(Object obj)
                {
                    Object obj1;
/*2595*/            if((obj1 = unfiltered.get(obj)) != null && apply(obj, obj1))
/*2596*/                return obj1;
/*2596*/            else
/*2596*/                return null;
                }

                public boolean isEmpty()
                {
/*2600*/            return entrySet().isEmpty();
                }

                public Object remove(Object obj)
                {
/*2604*/            if(containsKey(obj))
/*2604*/                return unfiltered.remove(obj);
/*2604*/            else
/*2604*/                return null;
                }

                Collection createValues()
                {
/*2609*/            return new FilteredMapValues(this, unfiltered, predicate);
                }

                final Map unfiltered;
                final Predicate predicate;

                AbstractFilteredMap(Map map, Predicate predicate1)
                {
/*2566*/            unfiltered = map;
/*2567*/            predicate = predicate1;
                }
    }

    static class TransformedEntriesNavigableMap extends TransformedEntriesSortedMap
        implements NavigableMap
    {

                public java.util.Map.Entry ceilingEntry(Object obj)
                {
/*1985*/            return transformEntry(fromMap().ceilingEntry(obj));
                }

                public Object ceilingKey(Object obj)
                {
/*1989*/            return fromMap().ceilingKey(obj);
                }

                public NavigableSet descendingKeySet()
                {
/*1993*/            return fromMap().descendingKeySet();
                }

                public NavigableMap descendingMap()
                {
/*1997*/            return Maps.transformEntries(fromMap().descendingMap(), transformer);
                }

                public java.util.Map.Entry firstEntry()
                {
/*2001*/            return transformEntry(fromMap().firstEntry());
                }

                public java.util.Map.Entry floorEntry(Object obj)
                {
/*2004*/            return transformEntry(fromMap().floorEntry(obj));
                }

                public Object floorKey(Object obj)
                {
/*2008*/            return fromMap().floorKey(obj);
                }

                public NavigableMap headMap(Object obj)
                {
/*2012*/            return headMap(obj, false);
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*2016*/            return Maps.transformEntries(fromMap().headMap(obj, flag), transformer);
                }

                public java.util.Map.Entry higherEntry(Object obj)
                {
/*2021*/            return transformEntry(fromMap().higherEntry(obj));
                }

                public Object higherKey(Object obj)
                {
/*2025*/            return fromMap().higherKey(obj);
                }

                public java.util.Map.Entry lastEntry()
                {
/*2029*/            return transformEntry(fromMap().lastEntry());
                }

                public java.util.Map.Entry lowerEntry(Object obj)
                {
/*2033*/            return transformEntry(fromMap().lowerEntry(obj));
                }

                public Object lowerKey(Object obj)
                {
/*2037*/            return fromMap().lowerKey(obj);
                }

                public NavigableSet navigableKeySet()
                {
/*2041*/            return fromMap().navigableKeySet();
                }

                public java.util.Map.Entry pollFirstEntry()
                {
/*2045*/            return transformEntry(fromMap().pollFirstEntry());
                }

                public java.util.Map.Entry pollLastEntry()
                {
/*2049*/            return transformEntry(fromMap().pollLastEntry());
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*2054*/            return Maps.transformEntries(fromMap().subMap(obj, flag, obj1, flag1), transformer);
                }

                public NavigableMap subMap(Object obj, Object obj1)
                {
/*2060*/            return subMap(obj, true, obj1, false);
                }

                public NavigableMap tailMap(Object obj)
                {
/*2064*/            return tailMap(obj, true);
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*2068*/            return Maps.transformEntries(fromMap().tailMap(obj, flag), transformer);
                }

                private java.util.Map.Entry transformEntry(java.util.Map.Entry entry)
                {
/*2074*/            if(entry == null)
/*2074*/                return null;
/*2074*/            else
/*2074*/                return Maps.transformEntry(transformer, entry);
                }

                protected NavigableMap fromMap()
                {
/*2078*/            return (NavigableMap)super.fromMap();
                }

                public volatile SortedMap tailMap(Object obj)
                {
/*1974*/            return tailMap(obj);
                }

                public volatile SortedMap subMap(Object obj, Object obj1)
                {
/*1974*/            return subMap(obj, obj1);
                }

                public volatile SortedMap headMap(Object obj)
                {
/*1974*/            return headMap(obj);
                }

                protected volatile SortedMap fromMap()
                {
/*1974*/            return fromMap();
                }

                TransformedEntriesNavigableMap(NavigableMap navigablemap, EntryTransformer entrytransformer)
                {
/*1981*/            super(navigablemap, entrytransformer);
                }
    }

    static class TransformedEntriesSortedMap extends TransformedEntriesMap
        implements SortedMap
    {

                protected SortedMap fromMap()
                {
/*1940*/            return (SortedMap)fromMap;
                }

                public Comparator comparator()
                {
/*1949*/            return fromMap().comparator();
                }

                public Object firstKey()
                {
/*1953*/            return fromMap().firstKey();
                }

                public SortedMap headMap(Object obj)
                {
/*1957*/            return Maps.transformEntries(fromMap().headMap(obj), transformer);
                }

                public Object lastKey()
                {
/*1961*/            return fromMap().lastKey();
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*1965*/            return Maps.transformEntries(fromMap().subMap(obj, obj1), transformer);
                }

                public SortedMap tailMap(Object obj)
                {
/*1970*/            return Maps.transformEntries(fromMap().tailMap(obj), transformer);
                }

                TransformedEntriesSortedMap(SortedMap sortedmap, EntryTransformer entrytransformer)
                {
/*1945*/            super(sortedmap, entrytransformer);
                }
    }

    static class TransformedEntriesMap extends ImprovedAbstractMap
    {

                public int size()
                {
/*1889*/            return fromMap.size();
                }

                public boolean containsKey(Object obj)
                {
/*1893*/            return fromMap.containsKey(obj);
                }

                public Object get(Object obj)
                {
                    Object obj1;
/*1899*/            if((obj1 = fromMap.get(obj)) != null || fromMap.containsKey(obj))
/*1900*/                return transformer.transformEntry(obj, obj1);
/*1900*/            else
/*1900*/                return null;
                }

                public Object remove(Object obj)
                {
/*1908*/            if(fromMap.containsKey(obj))
/*1908*/                return transformer.transformEntry(obj, fromMap.remove(obj));
/*1908*/            else
/*1908*/                return null;
                }

                public void clear()
                {
/*1914*/            fromMap.clear();
                }

                public Set keySet()
                {
/*1918*/            return fromMap.keySet();
                }

                protected Set createEntrySet()
                {
/*1923*/            return new EntrySet() {

                        Map map()
                        {
/*1925*/                    return TransformedEntriesMap.this;
                        }

                        public Iterator iterator()
                        {
/*1929*/                    return Iterators.transform(fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(transformer));
                        }

                        final TransformedEntriesMap this$0;

                        
                        {
/*1923*/                    this$0 = TransformedEntriesMap.this;
/*1923*/                    super();
                        }
            };
                }

                final Map fromMap;
                final EntryTransformer transformer;

                TransformedEntriesMap(Map map, EntryTransformer entrytransformer)
                {
/*1884*/            fromMap = (Map)Preconditions.checkNotNull(map);
/*1885*/            transformer = (EntryTransformer)Preconditions.checkNotNull(entrytransformer);
                }
    }

    public static interface EntryTransformer
    {

        public abstract Object transformEntry(Object obj, Object obj1);
    }

    static class UnmodifiableBiMap extends ForwardingMap
        implements Serializable, BiMap
    {

                protected Map _mthdelegate()
                {
/*1430*/            return unmodifiableMap;
                }

                public BiMap inverse()
                {
                    BiMap bimap;
/*1440*/            if((bimap = inverse) == null)
/*1441*/                return inverse = new UnmodifiableBiMap(_flddelegate.inverse(), this);
/*1441*/            else
/*1441*/                return bimap;
                }

                public Set values()
                {
                    Set set;
/*1447*/            if((set = values) == null)
/*1448*/                return values = Collections.unmodifiableSet(_flddelegate.values());
/*1448*/            else
/*1448*/                return set;
                }

                public volatile Collection values()
                {
/*1415*/            return values();
                }

                protected volatile Object _mthdelegate()
                {
/*1415*/            return _mthdelegate();
                }

                final Map unmodifiableMap;
                final BiMap _flddelegate;
                BiMap inverse;
                transient Set values;

                UnmodifiableBiMap(BiMap bimap, BiMap bimap1)
                {
/*1424*/            unmodifiableMap = Collections.unmodifiableMap(bimap);
/*1425*/            _flddelegate = bimap;
/*1426*/            inverse = bimap1;
                }
    }

    static final class BiMapConverter extends Converter
        implements Serializable
    {

                protected final Object doForward(Object obj)
                {
/*1327*/            return convert(bimap, obj);
                }

                private static Object convert(BiMap bimap1, Object obj)
                {
/*1336*/            Preconditions.checkArgument((bimap1 = ((BiMap) (bimap1.get(obj)))) != null, "No non-null mapping present for input: %s", new Object[] {
/*1337*/                obj
                    });
/*1338*/            return bimap1;
                }

                public final boolean equals(Object obj)
                {
/*1343*/            if(obj instanceof BiMapConverter)
                    {
/*1344*/                obj = (BiMapConverter)obj;
/*1345*/                return bimap.equals(((BiMapConverter) (obj)).bimap);
                    } else
                    {
/*1347*/                return false;
                    }
                }

                public final int hashCode()
                {
/*1352*/            return bimap.hashCode();
                }

                public final String toString()
                {
/*1358*/            String s = String.valueOf(String.valueOf(bimap));
/*1358*/            return (new StringBuilder(18 + s.length())).append("Maps.asConverter(").append(s).append(")").toString();
                }

                private final BiMap bimap;

                BiMapConverter(BiMap bimap1)
                {
/*1322*/            bimap = (BiMap)Preconditions.checkNotNull(bimap1);
                }
    }

    static class UnmodifiableEntrySet extends UnmodifiableEntries
        implements Set
    {

                public boolean equals(Object obj)
                {
/*1294*/            return Sets.equalsImpl(this, obj);
                }

                public int hashCode()
                {
/*1298*/            return Sets.hashCodeImpl(this);
                }

                UnmodifiableEntrySet(Set set)
                {
/*1288*/            super(set);
                }
    }

    static class UnmodifiableEntries extends ForwardingCollection
    {

                protected Collection _mthdelegate()
                {
/*1256*/            return entries;
                }

                public Iterator iterator()
                {
/*1260*/            final Iterator delegate = super.iterator();
/*1261*/            return new UnmodifiableIterator() {

                        public boolean hasNext()
                        {
/*1264*/                    return delegate.hasNext();
                        }

                        public java.util.Map.Entry next()
                        {
/*1268*/                    return Maps.unmodifiableEntry((java.util.Map.Entry)delegate.next());
                        }

                        public volatile Object next()
                        {
/*1261*/                    return next();
                        }

                        final Iterator val$delegate;
                        final UnmodifiableEntries this$0;

                        
                        {
/*1261*/                    this$0 = UnmodifiableEntries.this;
/*1261*/                    delegate = iterator1;
/*1261*/                    super();
                        }
            };
                }

                public Object[] toArray()
                {
/*1276*/            return standardToArray();
                }

                public Object[] toArray(Object aobj[])
                {
/*1280*/            return standardToArray(aobj);
                }

                protected volatile Object _mthdelegate()
                {
/*1247*/            return _mthdelegate();
                }

                private final Collection entries;

                UnmodifiableEntries(Collection collection)
                {
/*1252*/            entries = collection;
                }
    }

    static final class NavigableAsMapView extends AbstractNavigableMap
    {

                public final NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/* 908*/            return Maps.asMap(set.subSet(obj, flag, obj1, flag1), function);
                }

                public final NavigableMap headMap(Object obj, boolean flag)
                {
/* 913*/            return Maps.asMap(set.headSet(obj, flag), function);
                }

                public final NavigableMap tailMap(Object obj, boolean flag)
                {
/* 918*/            return Maps.asMap(set.tailSet(obj, flag), function);
                }

                public final Comparator comparator()
                {
/* 923*/            return set.comparator();
                }

                public final Object get(Object obj)
                {
/* 929*/            if(Collections2.safeContains(set, obj))
                    {
/* 931*/                obj = obj;
/* 932*/                return function.apply(obj);
                    } else
                    {
/* 934*/                return null;
                    }
                }

                public final void clear()
                {
/* 940*/            set.clear();
                }

                final Iterator entryIterator()
                {
/* 945*/            return Maps.asMapEntryIterator(set, function);
                }

                final Iterator descendingEntryIterator()
                {
/* 950*/            return descendingMap().entrySet().iterator();
                }

                public final NavigableSet navigableKeySet()
                {
/* 955*/            return Maps.removeOnlyNavigableSet(set);
                }

                public final int size()
                {
/* 960*/            return set.size();
                }

                public final NavigableMap descendingMap()
                {
/* 965*/            return Maps.asMap(set.descendingSet(), function);
                }

                private final NavigableSet set;
                private final Function function;

                NavigableAsMapView(NavigableSet navigableset, Function function1)
                {
/* 901*/            set = (NavigableSet)Preconditions.checkNotNull(navigableset);
/* 902*/            function = (Function)Preconditions.checkNotNull(function1);
                }
    }

    static class SortedAsMapView extends AsMapView
        implements SortedMap
    {

                SortedSet backingSet()
                {
/* 850*/            return (SortedSet)super.backingSet();
                }

                public Comparator comparator()
                {
/* 855*/            return backingSet().comparator();
                }

                public Set keySet()
                {
/* 860*/            return Maps.removeOnlySortedSet(backingSet());
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/* 865*/            return Maps.asMap(backingSet().subSet(obj, obj1), function);
                }

                public SortedMap headMap(Object obj)
                {
/* 870*/            return Maps.asMap(backingSet().headSet(obj), function);
                }

                public SortedMap tailMap(Object obj)
                {
/* 875*/            return Maps.asMap(backingSet().tailSet(obj), function);
                }

                public Object firstKey()
                {
/* 880*/            return backingSet().first();
                }

                public Object lastKey()
                {
/* 885*/            return backingSet().last();
                }

                volatile Set backingSet()
                {
/* 841*/            return backingSet();
                }

                SortedAsMapView(SortedSet sortedset, Function function)
                {
/* 845*/            super(sortedset, function);
                }
    }

    static class AsMapView extends ImprovedAbstractMap
    {

                Set backingSet()
                {
/* 760*/            return set;
                }

                public Set createKeySet()
                {
/* 770*/            return Maps.removeOnlySet(backingSet());
                }

                Collection createValues()
                {
/* 775*/            return Collections2.transform(set, function);
                }

                public int size()
                {
/* 780*/            return backingSet().size();
                }

                public boolean containsKey(Object obj)
                {
/* 785*/            return backingSet().contains(obj);
                }

                public Object get(Object obj)
                {
/* 790*/            if(Collections2.safeContains(backingSet(), obj))
                    {
/* 792*/                obj = obj;
/* 793*/                return function.apply(obj);
                    } else
                    {
/* 795*/                return null;
                    }
                }

                public Object remove(Object obj)
                {
/* 801*/            if(backingSet().remove(obj))
                    {
/* 803*/                obj = obj;
/* 804*/                return function.apply(obj);
                    } else
                    {
/* 806*/                return null;
                    }
                }

                public void clear()
                {
/* 812*/            backingSet().clear();
                }

                protected Set createEntrySet()
                {
/* 817*/            return new EntrySet() {

                        Map map()
                        {
/* 820*/                    return AsMapView.this;
                        }

                        public Iterator iterator()
                        {
/* 825*/                    return Maps.asMapEntryIterator(backingSet(), function);
                        }

                        final AsMapView this$0;

                        
                        {
/* 817*/                    this$0 = AsMapView.this;
/* 817*/                    super();
                        }
            };
                }

                private final Set set;
                final Function function;

                AsMapView(Set set1, Function function1)
                {
/* 764*/            set = (Set)Preconditions.checkNotNull(set1);
/* 765*/            function = (Function)Preconditions.checkNotNull(function1);
                }
    }

    static class SortedMapDifferenceImpl extends MapDifferenceImpl
        implements SortedMapDifference
    {

                public SortedMap entriesDiffering()
                {
/* 617*/            return (SortedMap)super.entriesDiffering();
                }

                public SortedMap entriesInCommon()
                {
/* 621*/            return (SortedMap)super.entriesInCommon();
                }

                public SortedMap entriesOnlyOnLeft()
                {
/* 625*/            return (SortedMap)super.entriesOnlyOnLeft();
                }

                public SortedMap entriesOnlyOnRight()
                {
/* 629*/            return (SortedMap)super.entriesOnlyOnRight();
                }

                public volatile Map entriesDiffering()
                {
/* 608*/            return entriesDiffering();
                }

                public volatile Map entriesInCommon()
                {
/* 608*/            return entriesInCommon();
                }

                public volatile Map entriesOnlyOnRight()
                {
/* 608*/            return entriesOnlyOnRight();
                }

                public volatile Map entriesOnlyOnLeft()
                {
/* 608*/            return entriesOnlyOnLeft();
                }

                SortedMapDifferenceImpl(SortedMap sortedmap, SortedMap sortedmap1, SortedMap sortedmap2, SortedMap sortedmap3)
                {
/* 613*/            super(sortedmap, sortedmap1, sortedmap2, sortedmap3);
                }
    }

    static class ValueDifferenceImpl
        implements MapDifference.ValueDifference
    {

                static MapDifference.ValueDifference create(Object obj, Object obj1)
                {
/* 537*/            return new ValueDifferenceImpl(obj, obj1);
                }

                public Object leftValue()
                {
/* 547*/            return left;
                }

                public Object rightValue()
                {
/* 552*/            return right;
                }

                public boolean equals(Object obj)
                {
/* 556*/            if(obj instanceof MapDifference.ValueDifference)
                    {
/* 557*/                obj = (MapDifference.ValueDifference)obj;
/* 559*/                return Objects.equal(left, ((MapDifference.ValueDifference) (obj)).leftValue()) && Objects.equal(right, ((MapDifference.ValueDifference) (obj)).rightValue());
                    } else
                    {
/* 562*/                return false;
                    }
                }

                public int hashCode()
                {
/* 566*/            return Objects.hashCode(new Object[] {
/* 566*/                left, right
                    });
                }

                public String toString()
                {
/* 570*/            String s = String.valueOf(String.valueOf(left));
/* 570*/            String s1 = String.valueOf(String.valueOf(right));
/* 570*/            return (new StringBuilder(4 + s.length() + s1.length())).append("(").append(s).append(", ").append(s1).append(")").toString();
                }

                private final Object left;
                private final Object right;

                private ValueDifferenceImpl(Object obj, Object obj1)
                {
/* 541*/            left = obj;
/* 542*/            right = obj1;
                }
    }

    static class MapDifferenceImpl
        implements MapDifference
    {

                public boolean areEqual()
                {
/* 470*/            return onlyOnLeft.isEmpty() && onlyOnRight.isEmpty() && differences.isEmpty();
                }

                public Map entriesOnlyOnLeft()
                {
/* 475*/            return onlyOnLeft;
                }

                public Map entriesOnlyOnRight()
                {
/* 480*/            return onlyOnRight;
                }

                public Map entriesInCommon()
                {
/* 485*/            return onBoth;
                }

                public Map entriesDiffering()
                {
/* 490*/            return differences;
                }

                public boolean equals(Object obj)
                {
/* 494*/            if(obj == this)
/* 495*/                return true;
/* 497*/            if(obj instanceof MapDifference)
                    {
/* 498*/                obj = (MapDifference)obj;
/* 499*/                return entriesOnlyOnLeft().equals(((MapDifference) (obj)).entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(((MapDifference) (obj)).entriesOnlyOnRight()) && entriesInCommon().equals(((MapDifference) (obj)).entriesInCommon()) && entriesDiffering().equals(((MapDifference) (obj)).entriesDiffering());
                    } else
                    {
/* 504*/                return false;
                    }
                }

                public int hashCode()
                {
/* 508*/            return Objects.hashCode(new Object[] {
/* 508*/                entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering()
                    });
                }

                public String toString()
                {
/* 513*/            if(areEqual())
/* 514*/                return "equal";
/* 517*/            StringBuilder stringbuilder = new StringBuilder("not equal");
/* 518*/            if(!onlyOnLeft.isEmpty())
/* 519*/                stringbuilder.append(": only on left=").append(onlyOnLeft);
/* 521*/            if(!onlyOnRight.isEmpty())
/* 522*/                stringbuilder.append(": only on right=").append(onlyOnRight);
/* 524*/            if(!differences.isEmpty())
/* 525*/                stringbuilder.append(": value differences=").append(differences);
/* 527*/            return stringbuilder.toString();
                }

                final Map onlyOnLeft;
                final Map onlyOnRight;
                final Map onBoth;
                final Map differences;

                MapDifferenceImpl(Map map, Map map1, Map map2, Map map3)
                {
/* 462*/            onlyOnLeft = Maps.unmodifiableMap(map);
/* 463*/            onlyOnRight = Maps.unmodifiableMap(map1);
/* 464*/            onBoth = Maps.unmodifiableMap(map2);
/* 465*/            differences = Maps.unmodifiableMap(map3);
                }
    }

    static abstract class EntryFunction extends Enum
        implements Function
    {

                public static final EntryFunction KEY;
                public static final EntryFunction VALUE;
                private static final EntryFunction $VALUES[];

                static 
                {
/*  86*/            KEY = new EntryFunction("KEY", 0) {

                        public final Object apply(java.util.Map.Entry entry)
                        {
/*  90*/                    return entry.getKey();
                        }

                        public final volatile Object apply(Object obj)
                        {
/*  86*/                    return apply((java.util.Map.Entry)obj);
                        }

            };
/*  93*/            VALUE = new EntryFunction("VALUE", 1) {

                        public final Object apply(java.util.Map.Entry entry)
                        {
/*  97*/                    return entry.getValue();
                        }

                        public final volatile Object apply(Object obj)
                        {
/*  93*/                    return apply((java.util.Map.Entry)obj);
                        }

            };
/*  85*/            $VALUES = (new EntryFunction[] {
/*  85*/                KEY, VALUE
                    });
                }

                private EntryFunction(String s, int i)
                {
/*  85*/            super(s, i);
                }

    }


            private Maps()
            {
            }

            static Function keyFunction()
            {
/* 104*/        return EntryFunction.KEY;
            }

            static Function valueFunction()
            {
/* 109*/        return EntryFunction.VALUE;
            }

            static Iterator keyIterator(Iterator iterator)
            {
/* 113*/        return Iterators.transform(iterator, keyFunction());
            }

            static Iterator valueIterator(Iterator iterator)
            {
/* 117*/        return Iterators.transform(iterator, valueFunction());
            }

            static UnmodifiableIterator valueIterator(UnmodifiableIterator unmodifiableiterator)
            {
/* 122*/        return new UnmodifiableIterator(unmodifiableiterator) {

                    public final boolean hasNext()
                    {
/* 125*/                return entryIterator.hasNext();
                    }

                    public final Object next()
                    {
/* 130*/                return ((java.util.Map.Entry)entryIterator.next()).getValue();
                    }

                    final UnmodifiableIterator val$entryIterator;

                    
                    {
/* 122*/                entryIterator = unmodifiableiterator;
/* 122*/                super();
                    }
        };
            }

            public static ImmutableMap immutableEnumMap(Map map)
            {
                ImmutableEnumMap immutableenummap;
/* 150*/        if(map instanceof ImmutableEnumMap)
/* 152*/            return immutableenummap = (ImmutableEnumMap)map;
/* 154*/        if(map.isEmpty())
/* 155*/            return ImmutableMap.of();
                java.util.Map.Entry entry;
/* 157*/        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); Preconditions.checkNotNull(entry.getValue()))
/* 157*/            Preconditions.checkNotNull((entry = (java.util.Map.Entry)iterator.next()).getKey());

/* 161*/        return ImmutableEnumMap.asImmutable(new EnumMap(map));
            }

            public static HashMap newHashMap()
            {
/* 177*/        return new HashMap();
            }

            public static HashMap newHashMapWithExpectedSize(int i)
            {
/* 195*/        return new HashMap(capacity(i));
            }

            static int capacity(int i)
            {
/* 204*/        if(i < 3)
                {
/* 205*/            CollectPreconditions.checkNonnegative(i, "expectedSize");
/* 206*/            return i + 1;
                }
/* 208*/        if(i < 0x40000000)
/* 209*/            return i + i / 3;
/* 211*/        else
/* 211*/            return 0x7fffffff;
            }

            public static HashMap newHashMap(Map map)
            {
/* 230*/        return new HashMap(map);
            }

            public static LinkedHashMap newLinkedHashMap()
            {
/* 243*/        return new LinkedHashMap();
            }

            public static LinkedHashMap newLinkedHashMap(Map map)
            {
/* 259*/        return new LinkedHashMap(map);
            }

            public static ConcurrentMap newConcurrentMap()
            {
/* 278*/        return (new MapMaker()).makeMap();
            }

            public static TreeMap newTreeMap()
            {
/* 291*/        return new TreeMap();
            }

            public static TreeMap newTreeMap(SortedMap sortedmap)
            {
/* 307*/        return new TreeMap(sortedmap);
            }

            public static TreeMap newTreeMap(Comparator comparator)
            {
/* 327*/        return new TreeMap(comparator);
            }

            public static EnumMap newEnumMap(Class class1)
            {
/* 337*/        return new EnumMap((Class)Preconditions.checkNotNull(class1));
            }

            public static EnumMap newEnumMap(Map map)
            {
/* 351*/        return new EnumMap(map);
            }

            public static IdentityHashMap newIdentityHashMap()
            {
/* 360*/        return new IdentityHashMap();
            }

            public static MapDifference difference(Map map, Map map1)
            {
/* 382*/        if(map instanceof SortedMap)
/* 383*/            return map = difference(((SortedMap) (map = (SortedMap)map)), map1);
/* 387*/        else
/* 387*/            return difference(map, map1, Equivalence.equals());
            }

            public static MapDifference difference(Map map, Map map1, Equivalence equivalence)
            {
/* 413*/        Preconditions.checkNotNull(equivalence);
/* 415*/        HashMap hashmap = newHashMap();
/* 416*/        HashMap hashmap1 = new HashMap(map1);
/* 417*/        HashMap hashmap2 = newHashMap();
/* 418*/        HashMap hashmap3 = newHashMap();
/* 419*/        doDifference(map, map1, equivalence, hashmap, hashmap1, hashmap2, hashmap3);
/* 420*/        return new MapDifferenceImpl(hashmap, hashmap1, hashmap2, hashmap3);
            }

            private static void doDifference(Map map, Map map1, Equivalence equivalence, Map map2, Map map3, Map map4, Map map5)
            {
/* 428*/        for(map = map.entrySet().iterator(); map.hasNext();)
                {
                    Object obj;
/* 428*/            Object obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)map.next())).getKey();
/* 430*/            obj = ((java.util.Map.Entry) (obj)).getValue();
/* 431*/            if(map1.containsKey(obj1))
                    {
/* 432*/                Object obj2 = map3.remove(obj1);
/* 433*/                if(equivalence.equivalent(obj, obj2))
/* 434*/                    map4.put(obj1, obj);
/* 436*/                else
/* 436*/                    map5.put(obj1, ValueDifferenceImpl.create(obj, obj2));
                    } else
                    {
/* 440*/                map2.put(obj1, obj);
                    }
                }

            }

            private static Map unmodifiableMap(Map map)
            {
/* 446*/        if(map instanceof SortedMap)
/* 447*/            return Collections.unmodifiableSortedMap((SortedMap)map);
/* 449*/        else
/* 449*/            return Collections.unmodifiableMap(map);
            }

            public static SortedMapDifference difference(SortedMap sortedmap, Map map)
            {
/* 595*/        Preconditions.checkNotNull(sortedmap);
/* 596*/        Preconditions.checkNotNull(map);
                Object obj;
/* 597*/        TreeMap treemap = newTreeMap(((Comparator) (obj = orNaturalOrder(sortedmap.comparator()))));
                TreeMap treemap1;
/* 599*/        (treemap1 = newTreeMap(((Comparator) (obj)))).putAll(map);
/* 601*/        TreeMap treemap2 = newTreeMap(((Comparator) (obj)));
/* 602*/        obj = newTreeMap(((Comparator) (obj)));
/* 604*/        doDifference(sortedmap, map, Equivalence.equals(), treemap, treemap1, treemap2, ((Map) (obj)));
/* 605*/        return new SortedMapDifferenceImpl(treemap, treemap1, treemap2, ((SortedMap) (obj)));
            }

            static Comparator orNaturalOrder(Comparator comparator)
            {
/* 641*/        if(comparator != null)
/* 642*/            return comparator;
/* 644*/        else
/* 644*/            return Ordering.natural();
            }

            public static Map asMap(Set set, Function function)
            {
/* 677*/        if(set instanceof SortedSet)
/* 678*/            return asMap((SortedSet)set, function);
/* 680*/        else
/* 680*/            return new AsMapView(set, function);
            }

            public static SortedMap asMap(SortedSet sortedset, Function function)
            {
/* 713*/        return Platform.mapsAsMapSortedSet(sortedset, function);
            }

            static SortedMap asMapSortedIgnoreNavigable(SortedSet sortedset, Function function)
            {
/* 718*/        return new SortedAsMapView(sortedset, function);
            }

            public static NavigableMap asMap(NavigableSet navigableset, Function function)
            {
/* 751*/        return new NavigableAsMapView(navigableset, function);
            }

            static Iterator asMapEntryIterator(Set set, Function function)
            {
/* 833*/        return new TransformedIterator(set.iterator(), function) {

                    final java.util.Map.Entry transform(Object obj)
                    {
/* 836*/                return Maps.immutableEntry(obj, function.apply(obj));
                    }

                    final volatile Object transform(Object obj)
                    {
/* 833*/                return transform(obj);
                    }

                    final Function val$function;

                    
                    {
/* 833*/                function = function1;
/* 833*/                super(iterator);
                    }
        };
            }

            private static Set removeOnlySet(Set set)
            {
/* 970*/        return new ForwardingSet(set) {

                    protected final Set _mthdelegate()
                    {
/* 973*/                return set;
                    }

                    public final boolean add(Object obj)
                    {
/* 978*/                throw new UnsupportedOperationException();
                    }

                    public final boolean addAll(Collection collection)
                    {
/* 983*/                throw new UnsupportedOperationException();
                    }

                    protected final volatile Collection _mthdelegate()
                    {
/* 970*/                return _mthdelegate();
                    }

                    protected final volatile Object _mthdelegate()
                    {
/* 970*/                return _mthdelegate();
                    }

                    final Set val$set;

                    
                    {
/* 970*/                set = set1;
/* 970*/                super();
                    }
        };
            }

            private static SortedSet removeOnlySortedSet(SortedSet sortedset)
            {
/* 989*/        return new ForwardingSortedSet(sortedset) {

                    protected final SortedSet _mthdelegate()
                    {
/* 992*/                return set;
                    }

                    public final boolean add(Object obj)
                    {
/* 997*/                throw new UnsupportedOperationException();
                    }

                    public final boolean addAll(Collection collection)
                    {
/*1002*/                throw new UnsupportedOperationException();
                    }

                    public final SortedSet headSet(Object obj)
                    {
/*1007*/                return Maps.removeOnlySortedSet(headSet(obj));
                    }

                    public final SortedSet subSet(Object obj, Object obj1)
                    {
/*1012*/                return Maps.removeOnlySortedSet(subSet(obj, obj1));
                    }

                    public final SortedSet tailSet(Object obj)
                    {
/*1017*/                return Maps.removeOnlySortedSet(tailSet(obj));
                    }

                    protected final volatile Set _mthdelegate()
                    {
/* 989*/                return _mthdelegate();
                    }

                    protected final volatile Collection _mthdelegate()
                    {
/* 989*/                return _mthdelegate();
                    }

                    protected final volatile Object _mthdelegate()
                    {
/* 989*/                return _mthdelegate();
                    }

                    final SortedSet val$set;

                    
                    {
/* 989*/                set = sortedset;
/* 989*/                super();
                    }
        };
            }

            private static NavigableSet removeOnlyNavigableSet(NavigableSet navigableset)
            {
/*1024*/        return new ForwardingNavigableSet(navigableset) {

                    protected final NavigableSet _mthdelegate()
                    {
/*1027*/                return set;
                    }

                    public final boolean add(Object obj)
                    {
/*1032*/                throw new UnsupportedOperationException();
                    }

                    public final boolean addAll(Collection collection)
                    {
/*1037*/                throw new UnsupportedOperationException();
                    }

                    public final SortedSet headSet(Object obj)
                    {
/*1042*/                return Maps.removeOnlySortedSet(headSet(obj));
                    }

                    public final SortedSet subSet(Object obj, Object obj1)
                    {
/*1047*/                return Maps.removeOnlySortedSet(subSet(obj, obj1));
                    }

                    public final SortedSet tailSet(Object obj)
                    {
/*1053*/                return Maps.removeOnlySortedSet(tailSet(obj));
                    }

                    public final NavigableSet headSet(Object obj, boolean flag)
                    {
/*1058*/                return Maps.removeOnlyNavigableSet(headSet(obj, flag));
                    }

                    public final NavigableSet tailSet(Object obj, boolean flag)
                    {
/*1063*/                return Maps.removeOnlyNavigableSet(tailSet(obj, flag));
                    }

                    public final NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                    {
/*1069*/                return Maps.removeOnlyNavigableSet(subSet(obj, flag, obj1, flag1));
                    }

                    public final NavigableSet descendingSet()
                    {
/*1075*/                return Maps.removeOnlyNavigableSet(descendingSet());
                    }

                    protected final volatile SortedSet _mthdelegate()
                    {
/*1024*/                return _mthdelegate();
                    }

                    protected final volatile Set _mthdelegate()
                    {
/*1024*/                return _mthdelegate();
                    }

                    protected final volatile Collection _mthdelegate()
                    {
/*1024*/                return _mthdelegate();
                    }

                    protected final volatile Object _mthdelegate()
                    {
/*1024*/                return _mthdelegate();
                    }

                    final NavigableSet val$set;

                    
                    {
/*1024*/                set = navigableset;
/*1024*/                super();
                    }
        };
            }

            public static ImmutableMap toMap(Iterable iterable, Function function)
            {
/*1097*/        return toMap(iterable.iterator(), function);
            }

            public static ImmutableMap toMap(Iterator iterator, Function function)
            {
/*1114*/        Preconditions.checkNotNull(function);
/*1116*/        LinkedHashMap linkedhashmap = newLinkedHashMap();
                Object obj;
/*1117*/        for(; iterator.hasNext(); linkedhashmap.put(obj, function.apply(obj)))
/*1118*/            obj = iterator.next();

/*1121*/        return ImmutableMap.copyOf(linkedhashmap);
            }

            public static ImmutableMap uniqueIndex(Iterable iterable, Function function)
            {
/*1140*/        return uniqueIndex(iterable.iterator(), function);
            }

            public static ImmutableMap uniqueIndex(Iterator iterator, Function function)
            {
/*1160*/        Preconditions.checkNotNull(function);
/*1161*/        ImmutableMap.Builder builder = ImmutableMap.builder();
                Object obj;
/*1162*/        for(; iterator.hasNext(); builder.put(function.apply(obj), obj))
/*1163*/            obj = iterator.next();

/*1166*/        return builder.build();
            }

            public static ImmutableMap fromProperties(Properties properties)
            {
/*1185*/        ImmutableMap.Builder builder = ImmutableMap.builder();
                String s;
/*1187*/        for(Enumeration enumeration = properties.propertyNames(); enumeration.hasMoreElements(); builder.put(s, properties.getProperty(s)))
/*1188*/            s = (String)enumeration.nextElement();

/*1192*/        return builder.build();
            }

            public static java.util.Map.Entry immutableEntry(Object obj, Object obj1)
            {
/*1207*/        return new ImmutableEntry(obj, obj1);
            }

            static Set unmodifiableEntrySet(Set set)
            {
/*1220*/        return new UnmodifiableEntrySet(Collections.unmodifiableSet(set));
            }

            static java.util.Map.Entry unmodifiableEntry(java.util.Map.Entry entry)
            {
/*1234*/        Preconditions.checkNotNull(entry);
/*1235*/        return new AbstractMapEntry(entry) {

                    public final Object getKey()
                    {
/*1237*/                return entry.getKey();
                    }

                    public final Object getValue()
                    {
/*1241*/                return entry.getValue();
                    }

                    final java.util.Map.Entry val$entry;

                    
                    {
/*1235*/                entry = entry1;
/*1235*/                super();
                    }
        };
            }

            public static Converter asConverter(BiMap bimap)
            {
/*1315*/        return new BiMapConverter(bimap);
            }

            public static BiMap synchronizedBiMap(BiMap bimap)
            {
/*1393*/        return Synchronized.biMap(bimap, null);
            }

            public static BiMap unmodifiableBiMap(BiMap bimap)
            {
/*1411*/        return new UnmodifiableBiMap(bimap, null);
            }

            public static Map transformValues(Map map, Function function)
            {
/*1494*/        return transformEntries(map, asEntryTransformer(function));
            }

            public static SortedMap transformValues(SortedMap sortedmap, Function function)
            {
/*1538*/        return transformEntries(sortedmap, asEntryTransformer(function));
            }

            public static NavigableMap transformValues(NavigableMap navigablemap, Function function)
            {
/*1585*/        return transformEntries(navigablemap, asEntryTransformer(function));
            }

            public static Map transformEntries(Map map, EntryTransformer entrytransformer)
            {
/*1642*/        if(map instanceof SortedMap)
/*1643*/            return transformEntries((SortedMap)map, entrytransformer);
/*1645*/        else
/*1645*/            return new TransformedEntriesMap(map, entrytransformer);
            }

            public static SortedMap transformEntries(SortedMap sortedmap, EntryTransformer entrytransformer)
            {
/*1703*/        return Platform.mapsTransformEntriesSortedMap(sortedmap, entrytransformer);
            }

            public static NavigableMap transformEntries(NavigableMap navigablemap, EntryTransformer entrytransformer)
            {
/*1763*/        return new TransformedEntriesNavigableMap(navigablemap, entrytransformer);
            }

            static SortedMap transformEntriesIgnoreNavigable(SortedMap sortedmap, EntryTransformer entrytransformer)
            {
/*1769*/        return new TransformedEntriesSortedMap(sortedmap, entrytransformer);
            }

            static EntryTransformer asEntryTransformer(Function function)
            {
/*1808*/        Preconditions.checkNotNull(function);
/*1809*/        return new EntryTransformer(function) {

                    public final Object transformEntry(Object obj, Object obj1)
                    {
/*1812*/                return function.apply(obj1);
                    }

                    final Function val$function;

                    
                    {
/*1809*/                function = function1;
/*1809*/                super();
                    }
        };
            }

            static Function asValueToValueFunction(EntryTransformer entrytransformer, Object obj)
            {
/*1819*/        Preconditions.checkNotNull(entrytransformer);
/*1820*/        return new Function(entrytransformer, obj) {

                    public final Object apply(Object obj1)
                    {
/*1823*/                return transformer.transformEntry(key, obj1);
                    }

                    final EntryTransformer val$transformer;
                    final Object val$key;

                    
                    {
/*1820*/                transformer = entrytransformer;
/*1820*/                key = obj;
/*1820*/                super();
                    }
        };
            }

            static Function asEntryToValueFunction(EntryTransformer entrytransformer)
            {
/*1833*/        Preconditions.checkNotNull(entrytransformer);
/*1834*/        return new Function(entrytransformer) {

                    public final Object apply(java.util.Map.Entry entry)
                    {
/*1837*/                return transformer.transformEntry(entry.getKey(), entry.getValue());
                    }

                    public final volatile Object apply(Object obj)
                    {
/*1834*/                return apply((java.util.Map.Entry)obj);
                    }

                    final EntryTransformer val$transformer;

                    
                    {
/*1834*/                transformer = entrytransformer;
/*1834*/                super();
                    }
        };
            }

            static java.util.Map.Entry transformEntry(EntryTransformer entrytransformer, java.util.Map.Entry entry)
            {
/*1847*/        Preconditions.checkNotNull(entrytransformer);
/*1848*/        Preconditions.checkNotNull(entry);
/*1849*/        return new AbstractMapEntry(entry, entrytransformer) {

                    public final Object getKey()
                    {
/*1852*/                return entry.getKey();
                    }

                    public final Object getValue()
                    {
/*1857*/                return transformer.transformEntry(entry.getKey(), entry.getValue());
                    }

                    final java.util.Map.Entry val$entry;
                    final EntryTransformer val$transformer;

                    
                    {
/*1849*/                entry = entry1;
/*1849*/                transformer = entrytransformer;
/*1849*/                super();
                    }
        };
            }

            static Function asEntryToEntryFunction(EntryTransformer entrytransformer)
            {
/*1867*/        Preconditions.checkNotNull(entrytransformer);
/*1868*/        return new Function(entrytransformer) {

                    public final java.util.Map.Entry apply(java.util.Map.Entry entry)
                    {
/*1871*/                return Maps.transformEntry(transformer, entry);
                    }

                    public final volatile Object apply(Object obj)
                    {
/*1868*/                return apply((java.util.Map.Entry)obj);
                    }

                    final EntryTransformer val$transformer;

                    
                    {
/*1868*/                transformer = entrytransformer;
/*1868*/                super();
                    }
        };
            }

            static Predicate keyPredicateOnEntries(Predicate predicate)
            {
/*2083*/        return Predicates.compose(predicate, keyFunction());
            }

            static Predicate valuePredicateOnEntries(Predicate predicate)
            {
/*2087*/        return Predicates.compose(predicate, valueFunction());
            }

            public static Map filterKeys(Map map, Predicate predicate)
            {
/*2120*/        if(map instanceof SortedMap)
/*2121*/            return filterKeys((SortedMap)map, predicate);
/*2122*/        if(map instanceof BiMap)
/*2123*/            return filterKeys((BiMap)map, predicate);
/*2125*/        Preconditions.checkNotNull(predicate);
/*2126*/        Predicate predicate1 = keyPredicateOnEntries(predicate);
/*2127*/        if(map instanceof AbstractFilteredMap)
/*2127*/            return filterFiltered((AbstractFilteredMap)map, predicate1);
/*2127*/        else
/*2127*/            return new FilteredKeyMap((Map)Preconditions.checkNotNull(map), predicate, predicate1);
            }

            public static SortedMap filterKeys(SortedMap sortedmap, Predicate predicate)
            {
/*2167*/        return filterEntries(sortedmap, keyPredicateOnEntries(predicate));
            }

            public static NavigableMap filterKeys(NavigableMap navigablemap, Predicate predicate)
            {
/*2205*/        return filterEntries(navigablemap, keyPredicateOnEntries(predicate));
            }

            public static BiMap filterKeys(BiMap bimap, Predicate predicate)
            {
/*2235*/        Preconditions.checkNotNull(predicate);
/*2236*/        return filterEntries(bimap, keyPredicateOnEntries(predicate));
            }

            public static Map filterValues(Map map, Predicate predicate)
            {
/*2270*/        if(map instanceof SortedMap)
/*2271*/            return filterValues((SortedMap)map, predicate);
/*2272*/        if(map instanceof BiMap)
/*2273*/            return filterValues((BiMap)map, predicate);
/*2275*/        else
/*2275*/            return filterEntries(map, valuePredicateOnEntries(predicate));
            }

            public static SortedMap filterValues(SortedMap sortedmap, Predicate predicate)
            {
/*2311*/        return filterEntries(sortedmap, valuePredicateOnEntries(predicate));
            }

            public static NavigableMap filterValues(NavigableMap navigablemap, Predicate predicate)
            {
/*2348*/        return filterEntries(navigablemap, valuePredicateOnEntries(predicate));
            }

            public static BiMap filterValues(BiMap bimap, Predicate predicate)
            {
/*2381*/        return filterEntries(bimap, valuePredicateOnEntries(predicate));
            }

            public static Map filterEntries(Map map, Predicate predicate)
            {
/*2415*/        if(map instanceof SortedMap)
/*2416*/            return filterEntries((SortedMap)map, predicate);
/*2417*/        if(map instanceof BiMap)
/*2418*/            return filterEntries((BiMap)map, predicate);
/*2420*/        Preconditions.checkNotNull(predicate);
/*2421*/        if(map instanceof AbstractFilteredMap)
/*2421*/            return filterFiltered((AbstractFilteredMap)map, predicate);
/*2421*/        else
/*2421*/            return new FilteredEntryMap((Map)Preconditions.checkNotNull(map), predicate);
            }

            public static SortedMap filterEntries(SortedMap sortedmap, Predicate predicate)
            {
/*2460*/        return Platform.mapsFilterSortedMap(sortedmap, predicate);
            }

            static SortedMap filterSortedIgnoreNavigable(SortedMap sortedmap, Predicate predicate)
            {
/*2466*/        Preconditions.checkNotNull(predicate);
/*2467*/        if(sortedmap instanceof FilteredEntrySortedMap)
/*2467*/            return filterFiltered((FilteredEntrySortedMap)sortedmap, predicate);
/*2467*/        else
/*2467*/            return new FilteredEntrySortedMap((SortedMap)Preconditions.checkNotNull(sortedmap), predicate);
            }

            public static NavigableMap filterEntries(NavigableMap navigablemap, Predicate predicate)
            {
/*2507*/        Preconditions.checkNotNull(predicate);
/*2508*/        if(navigablemap instanceof FilteredEntryNavigableMap)
/*2508*/            return filterFiltered((FilteredEntryNavigableMap)navigablemap, predicate);
/*2508*/        else
/*2508*/            return new FilteredEntryNavigableMap((NavigableMap)Preconditions.checkNotNull(navigablemap), predicate);
            }

            public static BiMap filterEntries(BiMap bimap, Predicate predicate)
            {
/*2542*/        Preconditions.checkNotNull(bimap);
/*2543*/        Preconditions.checkNotNull(predicate);
/*2544*/        if(bimap instanceof FilteredEntryBiMap)
/*2544*/            return filterFiltered((FilteredEntryBiMap)bimap, predicate);
/*2544*/        else
/*2544*/            return new FilteredEntryBiMap(bimap, predicate);
            }

            private static Map filterFiltered(AbstractFilteredMap abstractfilteredmap, Predicate predicate)
            {
/*2555*/        return new FilteredEntryMap(abstractfilteredmap.unfiltered, Predicates.and(abstractfilteredmap.predicate, predicate));
            }

            private static SortedMap filterFiltered(FilteredEntrySortedMap filteredentrysortedmap, Predicate predicate)
            {
/*2776*/        predicate = Predicates.and(filteredentrysortedmap.predicate, predicate);
/*2778*/        return new FilteredEntrySortedMap(filteredentrysortedmap.sortedMap(), predicate);
            }

            private static NavigableMap filterFiltered(FilteredEntryNavigableMap filteredentrynavigablemap, Predicate predicate)
            {
/*2878*/        predicate = Predicates.and(filteredentrynavigablemap.entryPredicate, predicate);
/*2880*/        return new FilteredEntryNavigableMap(filteredentrynavigablemap.unfiltered, predicate);
            }

            private static BiMap filterFiltered(FilteredEntryBiMap filteredentrybimap, Predicate predicate)
            {
/*3024*/        predicate = Predicates.and(filteredentrybimap.predicate, predicate);
/*3025*/        return new FilteredEntryBiMap(filteredentrybimap.unfiltered(), predicate);
            }

            public static NavigableMap unmodifiableNavigableMap(NavigableMap navigablemap)
            {
/*3092*/        Preconditions.checkNotNull(navigablemap);
/*3093*/        if(navigablemap instanceof UnmodifiableNavigableMap)
/*3094*/            return navigablemap;
/*3096*/        else
/*3096*/            return new UnmodifiableNavigableMap(navigablemap);
            }

            private static java.util.Map.Entry unmodifiableOrNull(java.util.Map.Entry entry)
            {
/*3101*/        if(entry == null)
/*3101*/            return null;
/*3101*/        else
/*3101*/            return unmodifiableEntry(entry);
            }

            public static NavigableMap synchronizedNavigableMap(NavigableMap navigablemap)
            {
/*3297*/        return Synchronized.navigableMap(navigablemap);
            }

            static Object safeGet(Map map, Object obj)
            {
/*3351*/        Preconditions.checkNotNull(map);
/*3353*/        return map.get(obj);
/*3354*/        JVM INSTR pop ;
/*3355*/        return null;
/*3356*/        JVM INSTR pop ;
/*3357*/        return null;
            }

            static boolean safeContainsKey(Map map, Object obj)
            {
/*3366*/        Preconditions.checkNotNull(map);
/*3368*/        return map.containsKey(obj);
/*3369*/        JVM INSTR pop ;
/*3370*/        return false;
/*3371*/        JVM INSTR pop ;
/*3372*/        return false;
            }

            static Object safeRemove(Map map, Object obj)
            {
/*3381*/        Preconditions.checkNotNull(map);
/*3383*/        return map.remove(obj);
/*3384*/        JVM INSTR pop ;
/*3385*/        return null;
/*3386*/        JVM INSTR pop ;
/*3387*/        return null;
            }

            static boolean containsKeyImpl(Map map, Object obj)
            {
/*3395*/        return Iterators.contains(keyIterator(map.entrySet().iterator()), obj);
            }

            static boolean containsValueImpl(Map map, Object obj)
            {
/*3402*/        return Iterators.contains(valueIterator(map.entrySet().iterator()), obj);
            }

            static boolean containsEntryImpl(Collection collection, Object obj)
            {
/*3419*/        if(!(obj instanceof java.util.Map.Entry))
/*3420*/            return false;
/*3422*/        else
/*3422*/            return collection.contains(unmodifiableEntry((java.util.Map.Entry)obj));
            }

            static boolean removeEntryImpl(Collection collection, Object obj)
            {
/*3439*/        if(!(obj instanceof java.util.Map.Entry))
/*3440*/            return false;
/*3442*/        else
/*3442*/            return collection.remove(unmodifiableEntry((java.util.Map.Entry)obj));
            }

            static boolean equalsImpl(Map map, Object obj)
            {
/*3449*/        if(map == obj)
/*3450*/            return true;
/*3451*/        if(obj instanceof Map)
                {
/*3452*/            obj = (Map)obj;
/*3453*/            return map.entrySet().equals(((Map) (obj)).entrySet());
                } else
                {
/*3455*/            return false;
                }
            }

            static String toStringImpl(Map map)
            {
/*3465*/        StringBuilder stringbuilder = Collections2.newStringBuilderForCollection(map.size()).append('{');
/*3467*/        STANDARD_JOINER.appendTo(stringbuilder, map);
/*3468*/        return stringbuilder.append('}').toString();
            }

            static void putAllImpl(Map map, Map map1)
            {
                java.util.Map.Entry entry;
/*3476*/        for(map1 = map1.entrySet().iterator(); map1.hasNext(); map.put(entry.getKey(), entry.getValue()))
/*3476*/            entry = (java.util.Map.Entry)map1.next();

            }

            static Object keyOrNull(java.util.Map.Entry entry)
            {
/*3523*/        if(entry == null)
/*3523*/            return null;
/*3523*/        else
/*3523*/            return entry.getKey();
            }

            static Object valueOrNull(java.util.Map.Entry entry)
            {
/*3528*/        if(entry == null)
/*3528*/            return null;
/*3528*/        else
/*3528*/            return entry.getValue();
            }

            static final jersey.repackaged.com.google.common.base.Joiner.MapJoiner STANDARD_JOINER;

            static 
            {
/*3458*/        STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");
            }





}
