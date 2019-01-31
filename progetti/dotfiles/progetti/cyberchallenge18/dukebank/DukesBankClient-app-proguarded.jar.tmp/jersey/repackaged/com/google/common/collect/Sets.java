// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, ImmutableEnumSet, ImmutableSet, ImmutableSortedSet, 
//            Iterables, Iterators, Lists, Maps, 
//            Multiset, Platform, Synchronized, ForwardingSortedSet, 
//            ImmutableMap, AbstractIndexedListIterator, UnmodifiableIterator, ImmutableList, 
//            ForwardingCollection, CartesianList

public final class Sets
{
    static final class UnmodifiableNavigableSet extends ForwardingSortedSet
        implements Serializable, NavigableSet
    {

                protected final SortedSet _mthdelegate()
                {
/*1408*/            return Collections.unmodifiableSortedSet(_flddelegate);
                }

                public final Object lower(Object obj)
                {
/*1413*/            return _flddelegate.lower(obj);
                }

                public final Object floor(Object obj)
                {
/*1418*/            return _flddelegate.floor(obj);
                }

                public final Object ceiling(Object obj)
                {
/*1423*/            return _flddelegate.ceiling(obj);
                }

                public final Object higher(Object obj)
                {
/*1428*/            return _flddelegate.higher(obj);
                }

                public final Object pollFirst()
                {
/*1433*/            throw new UnsupportedOperationException();
                }

                public final Object pollLast()
                {
/*1438*/            throw new UnsupportedOperationException();
                }

                public final NavigableSet descendingSet()
                {
                    UnmodifiableNavigableSet unmodifiablenavigableset;
/*1445*/            if((unmodifiablenavigableset = descendingSet) == null)
/*1447*/                (unmodifiablenavigableset = descendingSet = new UnmodifiableNavigableSet(_flddelegate.descendingSet())).descendingSet = this;
/*1451*/            return unmodifiablenavigableset;
                }

                public final Iterator descendingIterator()
                {
/*1456*/            return Iterators.unmodifiableIterator(_flddelegate.descendingIterator());
                }

                public final NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*1465*/            return Sets.unmodifiableNavigableSet(_flddelegate.subSet(obj, flag, obj1, flag1));
                }

                public final NavigableSet headSet(Object obj, boolean flag)
                {
/*1474*/            return Sets.unmodifiableNavigableSet(_flddelegate.headSet(obj, flag));
                }

                public final NavigableSet tailSet(Object obj, boolean flag)
                {
/*1479*/            return Sets.unmodifiableNavigableSet(_flddelegate.tailSet(obj, flag));
                }

                protected final volatile Set _mthdelegate()
                {
/*1397*/            return _mthdelegate();
                }

                protected final volatile Collection _mthdelegate()
                {
/*1397*/            return _mthdelegate();
                }

                protected final volatile Object _mthdelegate()
                {
/*1397*/            return _mthdelegate();
                }

                private final NavigableSet _flddelegate;
                private transient UnmodifiableNavigableSet descendingSet;

                UnmodifiableNavigableSet(NavigableSet navigableset)
                {
/*1403*/            _flddelegate = (NavigableSet)Preconditions.checkNotNull(navigableset);
                }
    }

    static final class PowerSet extends AbstractSet
    {

                public final int size()
                {
/*1291*/            return 1 << inputSet.size();
                }

                public final boolean isEmpty()
                {
/*1295*/            return false;
                }

                public final Iterator iterator()
                {
/*1299*/            return new AbstractIndexedListIterator(size()) {

                        protected Set get(int i)
                        {
/*1301*/                    return new SubSet(inputSet, i);
                        }

                        protected volatile Object get(int i)
                        {
/*1299*/                    return get(i);
                        }

                        final PowerSet this$0;

                        
                        {
/*1299*/                    this$0 = PowerSet.this;
/*1299*/                    super(i);
                        }
            };
                }

                public final boolean contains(Object obj)
                {
/*1307*/            if(obj instanceof Set)
                    {
/*1308*/                obj = (Set)obj;
/*1309*/                return inputSet.keySet().containsAll(((Collection) (obj)));
                    } else
                    {
/*1311*/                return false;
                    }
                }

                public final boolean equals(Object obj)
                {
/*1315*/            if(obj instanceof PowerSet)
                    {
/*1316*/                obj = (PowerSet)obj;
/*1317*/                return inputSet.equals(((PowerSet) (obj)).inputSet);
                    } else
                    {
/*1319*/                return super.equals(obj);
                    }
                }

                public final int hashCode()
                {
/*1328*/            return inputSet.keySet().hashCode() << inputSet.size() - 1;
                }

                public final String toString()
                {
/*1332*/            String s = String.valueOf(String.valueOf(inputSet));
/*1332*/            return (new StringBuilder(10 + s.length())).append("powerSet(").append(s).append(")").toString();
                }

                final ImmutableMap inputSet;

                PowerSet(Set set)
                {
/*1280*/            ImmutableMap.Builder builder = ImmutableMap.builder();
/*1281*/            int i = 0;
                    Object obj;
/*1282*/            for(set = ((Set)Preconditions.checkNotNull(set)).iterator(); set.hasNext(); builder.put(obj, Integer.valueOf(i++)))
/*1282*/                obj = set.next();

/*1285*/            inputSet = builder.build();
/*1286*/            Preconditions.checkArgument(inputSet.size() <= 30, "Too many elements to create power set: %s > 30", new Object[] {
/*1286*/                Integer.valueOf(inputSet.size())
                    });
                }
    }

    static final class SubSet extends AbstractSet
    {

                public final Iterator iterator()
                {
/*1243*/            return new UnmodifiableIterator() {

                        public boolean hasNext()
                        {
/*1249*/                    return remainingSetBits != 0;
                        }

                        public Object next()
                        {
                            int i;
/*1254*/                    if((i = Integer.numberOfTrailingZeros(remainingSetBits)) == 32)
                            {
/*1256*/                        throw new NoSuchElementException();
                            } else
                            {
/*1258*/                        remainingSetBits &= ~(1 << i);
/*1259*/                        return elements.get(i);
                            }
                        }

                        final ImmutableList elements;
                        int remainingSetBits;
                        final SubSet this$0;

                        
                        {
/*1243*/                    this$0 = SubSet.this;
/*1243*/                    super();
/*1244*/                    elements = inputSet.keySet().asList();
/*1245*/                    remainingSetBits = mask;
                        }
            };
                }

                public final int size()
                {
/*1266*/            return Integer.bitCount(mask);
                }

                public final boolean contains(Object obj)
                {
/*1271*/            return (obj = (Integer)inputSet.get(obj)) != null && (mask & 1 << ((Integer) (obj)).intValue()) != 0;
                }

                private final ImmutableMap inputSet;
                private final int mask;



                SubSet(ImmutableMap immutablemap, int i)
                {
/*1237*/            inputSet = immutablemap;
/*1238*/            mask = i;
                }
    }

    static final class CartesianSet extends ForwardingCollection
        implements Set
    {

                static Set create(List list)
                {
/*1124*/            ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
                    Object obj;
/*1126*/            for(list = list.iterator(); list.hasNext(); builder.add(obj))
/*1126*/                if(((ImmutableSet) (obj = ImmutableSet.copyOf(((Collection) (obj = (Set)list.next()))))).isEmpty())
/*1129*/                    return ImmutableSet.of();

/*1133*/            list = builder.build();
/*1134*/            ImmutableList immutablelist = new ImmutableList(list) {

                        public final int size()
                        {
/*1138*/                    return axes.size();
                        }

                        public final List get(int i)
                        {
/*1143*/                    return ((ImmutableSet)axes.get(i)).asList();
                        }

                        final boolean isPartialView()
                        {
/*1148*/                    return true;
                        }

                        public final volatile Object get(int i)
                        {
/*1134*/                    return get(i);
                        }

                        final ImmutableList val$axes;

                        
                        {
/*1134*/                    axes = immutablelist;
/*1134*/                    super();
                        }
            };
/*1151*/            return new CartesianSet(list, new CartesianList(immutablelist));
                }

                protected final Collection _mthdelegate()
                {
/*1162*/            return _flddelegate;
                }

                public final boolean equals(Object obj)
                {
/*1168*/            if(obj instanceof CartesianSet)
                    {
/*1169*/                obj = (CartesianSet)obj;
/*1170*/                return axes.equals(((CartesianSet) (obj)).axes);
                    } else
                    {
/*1172*/                return super.equals(obj);
                    }
                }

                public final int hashCode()
                {
/*1181*/            int i = size() - 1;
/*1182*/            for(int j = 0; j < axes.size(); j++)
/*1183*/                i = ~~(i *= 31);

/*1187*/            int k = 1;
/*1188*/            for(Iterator iterator = axes.iterator(); iterator.hasNext();)
                    {
/*1188*/                Set set = (Set)iterator.next();
/*1189*/                k = ~~(k = k * 31 + (size() / set.size()) * set.hashCode());
                    }

/*1193*/            return ~~(k += i);
                }

                protected final volatile Object _mthdelegate()
                {
/*1118*/            return _mthdelegate();
                }

                private final transient ImmutableList axes;
                private final transient CartesianList _flddelegate;

                private CartesianSet(ImmutableList immutablelist, CartesianList cartesianlist)
                {
/*1156*/            axes = immutablelist;
/*1157*/            _flddelegate = cartesianlist;
                }
    }

    static class FilteredNavigableSet extends FilteredSortedSet
        implements NavigableSet
    {

                NavigableSet unfiltered()
                {
/* 930*/            return (NavigableSet)unfiltered;
                }

                public Object lower(Object obj)
                {
/* 936*/            return Iterators.getNext(headSet(obj, false).descendingIterator(), null);
                }

                public Object floor(Object obj)
                {
/* 942*/            return Iterators.getNext(headSet(obj, true).descendingIterator(), null);
                }

                public Object ceiling(Object obj)
                {
/* 947*/            return Iterables.getFirst(tailSet(obj, true), null);
                }

                public Object higher(Object obj)
                {
/* 952*/            return Iterables.getFirst(tailSet(obj, false), null);
                }

                public Object pollFirst()
                {
/* 957*/            return Iterables.removeFirstMatching(unfiltered(), predicate);
                }

                public Object pollLast()
                {
/* 962*/            return Iterables.removeFirstMatching(unfiltered().descendingSet(), predicate);
                }

                public NavigableSet descendingSet()
                {
/* 967*/            return Sets.filter(unfiltered().descendingSet(), predicate);
                }

                public Iterator descendingIterator()
                {
/* 972*/            return Iterators.filter(unfiltered().descendingIterator(), predicate);
                }

                public Object last()
                {
/* 977*/            return descendingIterator().next();
                }

                public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/* 983*/            return Sets.filter(unfiltered().subSet(obj, flag, obj1, flag1), predicate);
                }

                public NavigableSet headSet(Object obj, boolean flag)
                {
/* 989*/            return Sets.filter(unfiltered().headSet(obj, flag), predicate);
                }

                public NavigableSet tailSet(Object obj, boolean flag)
                {
/* 994*/            return Sets.filter(unfiltered().tailSet(obj, flag), predicate);
                }

                FilteredNavigableSet(NavigableSet navigableset, Predicate predicate)
                {
/* 926*/            super(navigableset, predicate);
                }
    }

    static class FilteredSortedSet extends FilteredSet
        implements SortedSet
    {

                public Comparator comparator()
                {
/* 838*/            return ((SortedSet)unfiltered).comparator();
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/* 843*/            return new FilteredSortedSet(((SortedSet)unfiltered).subSet(obj, obj1), predicate);
                }

                public SortedSet headSet(Object obj)
                {
/* 849*/            return new FilteredSortedSet(((SortedSet)unfiltered).headSet(obj), predicate);
                }

                public SortedSet tailSet(Object obj)
                {
/* 854*/            return new FilteredSortedSet(((SortedSet)unfiltered).tailSet(obj), predicate);
                }

                public Object first()
                {
/* 859*/            return iterator().next();
                }

                public Object last()
                {
/* 864*/            SortedSet sortedset = (SortedSet)unfiltered;
/* 866*/            do
                    {
/* 866*/                Object obj = sortedset.last();
/* 867*/                if(predicate.apply(obj))
/* 868*/                    return obj;
/* 870*/                sortedset = sortedset.headSet(obj);
                    } while(true);
                }

                FilteredSortedSet(SortedSet sortedset, Predicate predicate)
                {
/* 833*/            super(sortedset, predicate);
                }
    }

    static class FilteredSet extends Collections2.FilteredCollection
        implements Set
    {

                public boolean equals(Object obj)
                {
/* 771*/            return Sets.equalsImpl(this, obj);
                }

                public int hashCode()
                {
/* 775*/            return Sets.hashCodeImpl(this);
                }

                FilteredSet(Set set, Predicate predicate)
                {
/* 767*/            super(set, predicate);
                }
    }

    public static abstract class SetView extends AbstractSet
    {

                private SetView()
                {
                }

    }

    static abstract class ImprovedAbstractSet extends AbstractSet
    {

                public boolean removeAll(Collection collection)
                {
/*  74*/            return Sets.removeAllImpl(this, collection);
                }

                public boolean retainAll(Collection collection)
                {
/*  79*/            return super.retainAll((Collection)Preconditions.checkNotNull(collection));
                }

                ImprovedAbstractSet()
                {
                }
    }


            private Sets()
            {
            }

            public static transient ImmutableSet immutableEnumSet(Enum enum, Enum aenum[])
            {
/*  98*/        return ImmutableEnumSet.asImmutable(EnumSet.of(enum, aenum));
            }

            public static ImmutableSet immutableEnumSet(Iterable iterable)
            {
/* 116*/        if(iterable instanceof ImmutableEnumSet)
/* 117*/            return (ImmutableEnumSet)iterable;
/* 118*/        if(iterable instanceof Collection)
/* 119*/            if((iterable = (Collection)iterable).isEmpty())
/* 121*/                return ImmutableSet.of();
/* 123*/            else
/* 123*/                return ImmutableEnumSet.asImmutable(EnumSet.copyOf(iterable));
/* 126*/        if((iterable = iterable.iterator()).hasNext())
                {
                    EnumSet enumset;
/* 128*/            Iterators.addAll(enumset = EnumSet.of((Enum)iterable.next()), iterable);
/* 130*/            return ImmutableEnumSet.asImmutable(enumset);
                } else
                {
/* 132*/            return ImmutableSet.of();
                }
            }

            public static EnumSet newEnumSet(Iterable iterable, Class class1)
            {
/* 145*/        Iterables.addAll(class1 = EnumSet.noneOf(class1), iterable);
/* 147*/        return class1;
            }

            public static HashSet newHashSet()
            {
/* 164*/        return new HashSet();
            }

            public static transient HashSet newHashSet(Object aobj[])
            {
                HashSet hashset;
/* 182*/        Collections.addAll(hashset = newHashSetWithExpectedSize(aobj.length), aobj);
/* 184*/        return hashset;
            }

            public static HashSet newHashSetWithExpectedSize(int i)
            {
/* 201*/        return new HashSet(Maps.capacity(i));
            }

            public static HashSet newHashSet(Iterable iterable)
            {
/* 218*/        if(iterable instanceof Collection)
/* 218*/            return new HashSet(Collections2.cast(iterable));
/* 218*/        else
/* 218*/            return newHashSet(iterable.iterator());
            }

            public static HashSet newHashSet(Iterator iterator)
            {
                HashSet hashset;
/* 237*/        Iterators.addAll(hashset = newHashSet(), iterator);
/* 239*/        return hashset;
            }

            public static Set newConcurrentHashSet()
            {
/* 254*/        return newSetFromMap(new ConcurrentHashMap());
            }

            public static Set newConcurrentHashSet(Iterable iterable)
            {
                Set set;
/* 273*/        Iterables.addAll(set = newConcurrentHashSet(), iterable);
/* 275*/        return set;
            }

            public static LinkedHashSet newLinkedHashSet()
            {
/* 289*/        return new LinkedHashSet();
            }

            public static LinkedHashSet newLinkedHashSetWithExpectedSize(int i)
            {
/* 308*/        return new LinkedHashSet(Maps.capacity(i));
            }

            public static LinkedHashSet newLinkedHashSet(Iterable iterable)
            {
/* 324*/        if(iterable instanceof Collection)
                {
/* 325*/            return new LinkedHashSet(Collections2.cast(iterable));
                } else
                {
                    LinkedHashSet linkedhashset;
/* 327*/            Iterables.addAll(linkedhashset = newLinkedHashSet(), iterable);
/* 329*/            return linkedhashset;
                }
            }

            public static TreeSet newTreeSet()
            {
/* 344*/        return new TreeSet();
            }

            public static TreeSet newTreeSet(Iterable iterable)
            {
                TreeSet treeset;
/* 364*/        Iterables.addAll(treeset = newTreeSet(), iterable);
/* 366*/        return treeset;
            }

            public static TreeSet newTreeSet(Comparator comparator)
            {
/* 381*/        return new TreeSet((Comparator)Preconditions.checkNotNull(comparator));
            }

            public static Set newIdentityHashSet()
            {
/* 395*/        return newSetFromMap(Maps.newIdentityHashMap());
            }

            public static CopyOnWriteArraySet newCopyOnWriteArraySet()
            {
/* 409*/        return new CopyOnWriteArraySet();
            }

            public static CopyOnWriteArraySet newCopyOnWriteArraySet(Iterable iterable)
            {
/* 424*/        iterable = ((Iterable) ((iterable instanceof Collection) ? ((Iterable) (Collections2.cast(iterable))) : ((Iterable) (Lists.newArrayList(iterable)))));
/* 427*/        return new CopyOnWriteArraySet(iterable);
            }

            public static EnumSet complementOf(Collection collection)
            {
/* 447*/        if(collection instanceof EnumSet)
                {
/* 448*/            return EnumSet.complementOf((EnumSet)collection);
                } else
                {
/* 450*/            Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
/* 452*/            Class class1 = ((Enum)collection.iterator().next()).getDeclaringClass();
/* 453*/            return makeComplementByHand(collection, class1);
                }
            }

            public static EnumSet complementOf(Collection collection, Class class1)
            {
/* 470*/        Preconditions.checkNotNull(collection);
/* 471*/        if(collection instanceof EnumSet)
/* 471*/            return EnumSet.complementOf((EnumSet)collection);
/* 471*/        else
/* 471*/            return makeComplementByHand(collection, class1);
            }

            private static EnumSet makeComplementByHand(Collection collection, Class class1)
            {
/* 478*/        (class1 = EnumSet.allOf(class1)).removeAll(collection);
/* 480*/        return class1;
            }

            public static Set newSetFromMap(Map map)
            {
/* 515*/        return Platform.newSetFromMap(map);
            }

            public static SetView union(Set set, Set set1)
            {
/* 581*/        Preconditions.checkNotNull(set, "set1");
/* 582*/        Preconditions.checkNotNull(set1, "set2");
/* 584*/        SetView setview = difference(set1, set);
/* 586*/        return new SetView(set, setview, set1) {

                    public final int size()
                    {
/* 588*/                return set1.size() + set2minus1.size();
                    }

                    public final boolean isEmpty()
                    {
/* 591*/                return set1.isEmpty() && set2.isEmpty();
                    }

                    public final Iterator iterator()
                    {
/* 594*/                return Iterators.unmodifiableIterator(Iterators.concat(set1.iterator(), set2minus1.iterator()));
                    }

                    public final boolean contains(Object obj)
                    {
/* 598*/                return set1.contains(obj) || set2.contains(obj);
                    }

                    final Set val$set1;
                    final Set val$set2minus1;
                    final Set val$set2;

                    
                    {
/* 586*/                set1 = set;
/* 586*/                set2minus1 = set3;
/* 586*/                set2 = set4;
/* 586*/                super();
                    }
        };
            }

            public static SetView intersection(Set set, Set set1)
            {
/* 640*/        Preconditions.checkNotNull(set, "set1");
/* 641*/        Preconditions.checkNotNull(set1, "set2");
/* 643*/        Predicate predicate = Predicates.in(set1);
/* 644*/        return new SetView(set, predicate, set1) {

                    public final Iterator iterator()
                    {
/* 646*/                return Iterators.filter(set1.iterator(), inSet2);
                    }

                    public final int size()
                    {
/* 649*/                return Iterators.size(iterator());
                    }

                    public final boolean isEmpty()
                    {
/* 652*/                return !iterator().hasNext();
                    }

                    public final boolean contains(Object obj)
                    {
/* 655*/                return set1.contains(obj) && set2.contains(obj);
                    }

                    public final boolean containsAll(Collection collection)
                    {
/* 658*/                return set1.containsAll(collection) && set2.containsAll(collection);
                    }

                    final Set val$set1;
                    final Predicate val$inSet2;
                    final Set val$set2;

                    
                    {
/* 644*/                set1 = set;
/* 644*/                inSet2 = predicate;
/* 644*/                set2 = set3;
/* 644*/                super();
                    }
        };
            }

            public static SetView difference(Set set, Set set1)
            {
/* 677*/        Preconditions.checkNotNull(set, "set1");
/* 678*/        Preconditions.checkNotNull(set1, "set2");
/* 680*/        Predicate predicate = Predicates.not(Predicates.in(set1));
/* 681*/        return new SetView(set, predicate, set1) {

                    public final Iterator iterator()
                    {
/* 683*/                return Iterators.filter(set1.iterator(), notInSet2);
                    }

                    public final int size()
                    {
/* 686*/                return Iterators.size(iterator());
                    }

                    public final boolean isEmpty()
                    {
/* 689*/                return set2.containsAll(set1);
                    }

                    public final boolean contains(Object obj)
                    {
/* 692*/                return set1.contains(obj) && !set2.contains(obj);
                    }

                    final Set val$set1;
                    final Predicate val$notInSet2;
                    final Set val$set2;

                    
                    {
/* 681*/                set1 = set;
/* 681*/                notInSet2 = predicate;
/* 681*/                set2 = set3;
/* 681*/                super();
                    }
        };
            }

            public static SetView symmetricDifference(Set set, Set set1)
            {
/* 711*/        Preconditions.checkNotNull(set, "set1");
/* 712*/        Preconditions.checkNotNull(set1, "set2");
/* 715*/        return difference(union(set, set1), intersection(set, set1));
            }

            public static Set filter(Set set, Predicate predicate)
            {
/* 747*/        if(set instanceof SortedSet)
/* 748*/            return filter((SortedSet)set, predicate);
/* 750*/        if(set instanceof FilteredSet)
                {
/* 753*/            predicate = Predicates.and(((FilteredSet) (set = (FilteredSet)set)).predicate, predicate);
/* 756*/            return new FilteredSet((Set)((FilteredSet) (set)).unfiltered, predicate);
                } else
                {
/* 760*/            return new FilteredSet((Set)Preconditions.checkNotNull(set), (Predicate)Preconditions.checkNotNull(predicate));
                }
            }

            public static SortedSet filter(SortedSet sortedset, Predicate predicate)
            {
/* 810*/        return Platform.setsFilterSortedSet(sortedset, predicate);
            }

            static SortedSet filterSortedIgnoreNavigable(SortedSet sortedset, Predicate predicate)
            {
/* 815*/        if(sortedset instanceof FilteredSet)
                {
/* 818*/            predicate = Predicates.and(((FilteredSet) (sortedset = (FilteredSet)sortedset)).predicate, predicate);
/* 821*/            return new FilteredSortedSet((SortedSet)((FilteredSet) (sortedset)).unfiltered, predicate);
                } else
                {
/* 825*/            return new FilteredSortedSet((SortedSet)Preconditions.checkNotNull(sortedset), (Predicate)Preconditions.checkNotNull(predicate));
                }
            }

            public static NavigableSet filter(NavigableSet navigableset, Predicate predicate)
            {
/* 908*/        if(navigableset instanceof FilteredSet)
                {
/* 911*/            predicate = Predicates.and(((FilteredSet) (navigableset = (FilteredSet)navigableset)).predicate, predicate);
/* 914*/            return new FilteredNavigableSet((NavigableSet)((FilteredSet) (navigableset)).unfiltered, predicate);
                } else
                {
/* 918*/            return new FilteredNavigableSet((NavigableSet)Preconditions.checkNotNull(navigableset), (Predicate)Preconditions.checkNotNull(predicate));
                }
            }

            public static Set cartesianProduct(List list)
            {
/*1055*/        return CartesianSet.create(list);
            }

            public static transient Set cartesianProduct(Set aset[])
            {
/*1115*/        return cartesianProduct(Arrays.asList(aset));
            }

            public static Set powerSet(Set set)
            {
/*1229*/        return new PowerSet(set);
            }

            static int hashCodeImpl(Set set)
            {
/*1340*/        int i = 0;
/*1341*/        for(set = set.iterator(); set.hasNext();)
                {
/*1341*/            Object obj = set.next();
/*1342*/            i = ~~(i += obj == null ? 0 : obj.hashCode());
                }

/*1347*/        return i;
            }

            static boolean equalsImpl(Set set, Object obj)
            {
/*1354*/        if(set == obj)
/*1355*/            return true;
/*1357*/        if(!(obj instanceof Set))
/*1358*/            break MISSING_BLOCK_LABEL_54;
/*1358*/        obj = (Set)obj;
/*1361*/        if(set.size() == ((Set) (obj)).size() && set.containsAll(((Collection) (obj))))
/*1361*/            return true;
/*1361*/        return false;
/*1362*/        JVM INSTR pop ;
/*1363*/        return false;
/*1364*/        JVM INSTR pop ;
/*1365*/        return false;
/*1368*/        return false;
            }

            public static NavigableSet unmodifiableNavigableSet(NavigableSet navigableset)
            {
/*1390*/        if((navigableset instanceof ImmutableSortedSet) || (navigableset instanceof UnmodifiableNavigableSet))
/*1392*/            return navigableset;
/*1394*/        else
/*1394*/            return new UnmodifiableNavigableSet(navigableset);
            }

            public static NavigableSet synchronizedNavigableSet(NavigableSet navigableset)
            {
/*1532*/        return Synchronized.navigableSet(navigableset);
            }

            static boolean removeAllImpl(Set set, Iterator iterator)
            {
                boolean flag;
/*1539*/        for(flag = false; iterator.hasNext(); flag |= set.remove(iterator.next()));
/*1543*/        return flag;
            }

            static boolean removeAllImpl(Set set, Collection collection)
            {
/*1547*/        Preconditions.checkNotNull(collection);
/*1548*/        if(collection instanceof Multiset)
/*1549*/            collection = ((Multiset)collection).elementSet();
/*1558*/        if((collection instanceof Set) && collection.size() > set.size())
/*1559*/            return Iterators.removeAll(set.iterator(), collection);
/*1561*/        else
/*1561*/            return removeAllImpl(set, collection.iterator());
            }
}
