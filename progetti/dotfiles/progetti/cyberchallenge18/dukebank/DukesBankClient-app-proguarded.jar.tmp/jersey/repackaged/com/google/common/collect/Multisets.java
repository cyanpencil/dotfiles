// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.primitives.Ints;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, ImmutableMultiset, Iterators, Multiset, 
//            Ordering, Sets, TransformedIterator, AbstractMultiset, 
//            UnmodifiableIterator, ForwardingMultiset

public final class Multisets
{
    static final class MultisetIteratorImpl
        implements Iterator
    {

                public final boolean hasNext()
                {
/*1032*/            return laterCount > 0 || entryIterator.hasNext();
                }

                public final Object next()
                {
/*1037*/            if(!hasNext())
/*1038*/                throw new NoSuchElementException();
/*1040*/            if(laterCount == 0)
                    {
/*1041*/                currentEntry = (Multiset.Entry)entryIterator.next();
/*1042*/                totalCount = laterCount = currentEntry.getCount();
                    }
/*1044*/            laterCount--;
/*1045*/            canRemove = true;
/*1046*/            return currentEntry.getElement();
                }

                public final void remove()
                {
/*1051*/            CollectPreconditions.checkRemove(canRemove);
/*1052*/            if(totalCount == 1)
/*1053*/                entryIterator.remove();
/*1055*/            else
/*1055*/                multiset.remove(currentEntry.getElement());
/*1057*/            totalCount--;
/*1058*/            canRemove = false;
                }

                private final Multiset multiset;
                private final Iterator entryIterator;
                private Multiset.Entry currentEntry;
                private int laterCount;
                private int totalCount;
                private boolean canRemove;

                MultisetIteratorImpl(Multiset multiset1, Iterator iterator)
                {
/*1026*/            multiset = multiset1;
/*1027*/            entryIterator = iterator;
                }
    }

    static abstract class EntrySet extends Sets.ImprovedAbstractSet
    {

                abstract Multiset multiset();

                public boolean contains(Object obj)
                {
/* 968*/            if(obj instanceof Multiset.Entry)
                    {
/* 973*/                if(((Multiset.Entry) (obj = (Multiset.Entry)obj)).getCount() <= 0)
/* 975*/                    return false;
                        int i;
/* 977*/                return (i = multiset().count(((Multiset.Entry) (obj)).getElement())) == ((Multiset.Entry) (obj)).getCount();
                    } else
                    {
/* 981*/                return false;
                    }
                }

                public boolean remove(Object obj)
                {
/* 987*/            if(obj instanceof Multiset.Entry)
                    {
/* 988*/                Object obj1 = ((Multiset.Entry) (obj = (Multiset.Entry)obj)).getElement();
                        Multiset multiset1;
/* 990*/                if((obj = ((Multiset.Entry) (obj)).getCount()) != 0)
/* 994*/                    return (multiset1 = multiset()).setCount(obj1, ((int) (obj)), 0);
                    }
/* 998*/            return false;
                }

                public void clear()
                {
/*1002*/            multiset().clear();
                }

                EntrySet()
                {
                }
    }

    static abstract class ElementSet extends Sets.ImprovedAbstractSet
    {

                abstract Multiset multiset();

                public void clear()
                {
/* 925*/            multiset().clear();
                }

                public boolean contains(Object obj)
                {
/* 929*/            return multiset().contains(obj);
                }

                public boolean containsAll(Collection collection)
                {
/* 933*/            return multiset().containsAll(collection);
                }

                public boolean isEmpty()
                {
/* 937*/            return multiset().isEmpty();
                }

                public Iterator iterator()
                {
/* 941*/            return new TransformedIterator(multiset().entrySet().iterator()) {

                        Object transform(Multiset.Entry entry)
                        {
/* 944*/                    return entry.getElement();
                        }

                        volatile Object transform(Object obj)
                        {
/* 941*/                    return transform((Multiset.Entry)obj);
                        }

                        final ElementSet this$0;

                        
                        {
/* 941*/                    this$0 = ElementSet.this;
/* 941*/                    super(iterator1);
                        }
            };
                }

                public boolean remove(Object obj)
                {
                    int i;
/* 951*/            if((i = multiset().count(obj)) > 0)
                    {
/* 953*/                multiset().remove(obj, i);
/* 954*/                return true;
                    } else
                    {
/* 956*/                return false;
                    }
                }

                public int size()
                {
/* 960*/            return multiset().entrySet().size();
                }

                ElementSet()
                {
                }
    }

    static abstract class AbstractEntry
        implements Multiset.Entry
    {

                public boolean equals(Object obj)
                {
/* 785*/            if(obj instanceof Multiset.Entry)
                    {
/* 786*/                obj = (Multiset.Entry)obj;
/* 787*/                return getCount() == ((Multiset.Entry) (obj)).getCount() && Objects.equal(getElement(), ((Multiset.Entry) (obj)).getElement());
                    } else
                    {
/* 790*/                return false;
                    }
                }

                public int hashCode()
                {
                    Object obj;
/* 798*/            return ((obj = getElement()) != null ? obj.hashCode() : 0) ^ getCount();
                }

                public String toString()
                {
/* 810*/            String s = String.valueOf(getElement());
                    int i;
/* 811*/            if((i = getCount()) == 1)
                    {
/* 812*/                return s;
                    } else
                    {
/* 812*/                s = String.valueOf(String.valueOf(s));
/* 812*/                return (new StringBuilder(14 + s.length())).append(s).append(" x ").append(i).toString();
                    }
                }

                AbstractEntry()
                {
                }
    }

    static final class FilteredMultiset extends AbstractMultiset
    {

                public final UnmodifiableIterator iterator()
                {
/* 289*/            return Iterators.filter(unfiltered.iterator(), predicate);
                }

                final Set createElementSet()
                {
/* 294*/            return Sets.filter(unfiltered.elementSet(), predicate);
                }

                final Set createEntrySet()
                {
/* 299*/            return Sets.filter(unfiltered.entrySet(), new Predicate() {

                        public boolean apply(Multiset.Entry entry)
                        {
/* 302*/                    return predicate.apply(entry.getElement());
                        }

                        public volatile boolean apply(Object obj)
                        {
/* 299*/                    return apply((Multiset.Entry)obj);
                        }

                        final FilteredMultiset this$0;

                        
                        {
/* 299*/                    this$0 = FilteredMultiset.this;
/* 299*/                    super();
                        }
            });
                }

                final Iterator entryIterator()
                {
/* 309*/            throw new AssertionError("should never be called");
                }

                final int distinctElements()
                {
/* 314*/            return elementSet().size();
                }

                public final int count(Object obj)
                {
                    int i;
/* 319*/            if((i = unfiltered.count(obj)) > 0)
                    {
/* 322*/                obj = obj;
/* 323*/                if(predicate.apply(obj))
/* 323*/                    return i;
/* 323*/                else
/* 323*/                    return 0;
                    } else
                    {
/* 325*/                return 0;
                    }
                }

                public final int add(Object obj, int i)
                {
/* 330*/            Preconditions.checkArgument(predicate.apply(obj), "Element %s does not match predicate %s", new Object[] {
/* 330*/                obj, predicate
                    });
/* 332*/            return unfiltered.add(obj, i);
                }

                public final int remove(Object obj, int i)
                {
/* 337*/            CollectPreconditions.checkNonnegative(i, "occurrences");
/* 338*/            if(i == 0)
/* 339*/                return count(obj);
/* 341*/            if(contains(obj))
/* 341*/                return unfiltered.remove(obj, i);
/* 341*/            else
/* 341*/                return 0;
                }

                public final void clear()
                {
/* 347*/            elementSet().clear();
                }

                public final volatile Iterator iterator()
                {
/* 278*/            return iterator();
                }

                final Multiset unfiltered;
                final Predicate predicate;

                FilteredMultiset(Multiset multiset, Predicate predicate1)
                {
/* 283*/            unfiltered = (Multiset)Preconditions.checkNotNull(multiset);
/* 284*/            predicate = (Predicate)Preconditions.checkNotNull(predicate1);
                }
    }

    static final class ImmutableEntry extends AbstractEntry
        implements Serializable
    {

                public final Object getElement()
                {
/* 228*/            return element;
                }

                public final int getCount()
                {
/* 233*/            return count;
                }

                final Object element;
                final int count;

                ImmutableEntry(Object obj, int i)
                {
/* 221*/            element = obj;
/* 222*/            count = i;
/* 223*/            CollectPreconditions.checkNonnegative(i, "count");
                }
    }

    static class UnmodifiableMultiset extends ForwardingMultiset
        implements Serializable
    {

                protected Multiset _mthdelegate()
                {
/* 106*/            return _flddelegate;
                }

                Set createElementSet()
                {
/* 112*/            return Collections.unmodifiableSet(_flddelegate.elementSet());
                }

                public Set elementSet()
                {
                    Set set;
/* 117*/            if((set = elementSet) == null)
/* 118*/                return elementSet = createElementSet();
/* 118*/            else
/* 118*/                return set;
                }

                public Set entrySet()
                {
                    Set set;
/* 125*/            if((set = entrySet) == null)
/* 126*/                return entrySet = Collections.unmodifiableSet(_flddelegate.entrySet());
/* 126*/            else
/* 126*/                return set;
                }

                public Iterator iterator()
                {
/* 136*/            return Iterators.unmodifiableIterator(_flddelegate.iterator());
                }

                public boolean add(Object obj)
                {
/* 140*/            throw new UnsupportedOperationException();
                }

                public int add(Object obj, int i)
                {
/* 144*/            throw new UnsupportedOperationException();
                }

                public boolean addAll(Collection collection)
                {
/* 148*/            throw new UnsupportedOperationException();
                }

                public boolean remove(Object obj)
                {
/* 152*/            throw new UnsupportedOperationException();
                }

                public int remove(Object obj, int i)
                {
/* 156*/            throw new UnsupportedOperationException();
                }

                public boolean removeAll(Collection collection)
                {
/* 160*/            throw new UnsupportedOperationException();
                }

                public boolean retainAll(Collection collection)
                {
/* 164*/            throw new UnsupportedOperationException();
                }

                public void clear()
                {
/* 168*/            throw new UnsupportedOperationException();
                }

                public int setCount(Object obj, int i)
                {
/* 172*/            throw new UnsupportedOperationException();
                }

                public boolean setCount(Object obj, int i, int j)
                {
/* 176*/            throw new UnsupportedOperationException();
                }

                protected volatile Collection _mthdelegate()
                {
/*  95*/            return _mthdelegate();
                }

                protected volatile Object _mthdelegate()
                {
/*  95*/            return _mthdelegate();
                }

                final Multiset _flddelegate;
                transient Set elementSet;
                transient Set entrySet;

                UnmodifiableMultiset(Multiset multiset)
                {
/* 100*/            _flddelegate = multiset;
                }
    }


            public static Multiset unmodifiableMultiset(Multiset multiset)
            {
/*  74*/        if((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset))
/*  78*/            return multiset = multiset;
/*  81*/        else
/*  81*/            return new UnmodifiableMultiset((Multiset)Preconditions.checkNotNull(multiset));
            }

            public static Multiset.Entry immutableEntry(Object obj, int i)
            {
/* 212*/        return new ImmutableEntry(obj, i);
            }

            public static Multiset filter(Multiset multiset, Predicate predicate)
            {
/* 267*/        if(multiset instanceof FilteredMultiset)
                {
/* 270*/            predicate = Predicates.and(((FilteredMultiset) (multiset = (FilteredMultiset)multiset)).predicate, predicate);
/* 273*/            return new FilteredMultiset(((FilteredMultiset) (multiset)).unfiltered, predicate);
                } else
                {
/* 275*/            return new FilteredMultiset(multiset, predicate);
                }
            }

            static int inferDistinctElements(Iterable iterable)
            {
/* 358*/        if(iterable instanceof Multiset)
/* 359*/            return ((Multiset)iterable).elementSet().size();
/* 361*/        else
/* 361*/            return 11;
            }

            static boolean equalsImpl(Multiset multiset, Object obj)
            {
/* 820*/        if(obj == multiset)
/* 821*/            return true;
/* 823*/        if(obj instanceof Multiset)
                {
/* 824*/            obj = (Multiset)obj;
/* 831*/            if(multiset.size() != ((Multiset) (obj)).size() || multiset.entrySet().size() != ((Multiset) (obj)).entrySet().size())
/* 833*/                return false;
/* 835*/            for(obj = ((Multiset) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext();)
                    {
/* 835*/                Multiset.Entry entry = (Multiset.Entry)((Iterator) (obj)).next();
/* 836*/                if(multiset.count(entry.getElement()) != entry.getCount())
/* 837*/                    return false;
                    }

/* 840*/            return true;
                } else
                {
/* 842*/            return false;
                }
            }

            static boolean addAllImpl(Multiset multiset, Collection collection)
            {
/* 850*/        if(collection.isEmpty())
/* 851*/            return false;
/* 853*/        if(collection instanceof Multiset)
                {
                    Multiset.Entry entry;
/* 854*/            for(collection = (collection = cast(collection)).entrySet().iterator(); collection.hasNext(); multiset.add(entry.getElement(), entry.getCount()))
/* 855*/                entry = (Multiset.Entry)collection.next();

                } else
                {
/* 859*/            Iterators.addAll(multiset, collection.iterator());
                }
/* 861*/        return true;
            }

            static boolean removeAllImpl(Multiset multiset, Collection collection)
            {
/* 869*/        collection = ((Collection) ((collection instanceof Multiset) ? ((Collection) (((Multiset)collection).elementSet())) : collection));
/* 872*/        return multiset.elementSet().removeAll(collection);
            }

            static boolean retainAllImpl(Multiset multiset, Collection collection)
            {
/* 880*/        Preconditions.checkNotNull(collection);
/* 881*/        collection = ((Collection) ((collection instanceof Multiset) ? ((Collection) (((Multiset)collection).elementSet())) : collection));
/* 884*/        return multiset.elementSet().retainAll(collection);
            }

            static int setCountImpl(Multiset multiset, Object obj, int i)
            {
/* 891*/        CollectPreconditions.checkNonnegative(i, "count");
/* 893*/        int j = multiset.count(obj);
/* 895*/        if((i -= j) > 0)
/* 897*/            multiset.add(obj, i);
/* 898*/        else
/* 898*/        if(i < 0)
/* 899*/            multiset.remove(obj, -i);
/* 902*/        return j;
            }

            static boolean setCountImpl(Multiset multiset, Object obj, int i, int j)
            {
/* 910*/        CollectPreconditions.checkNonnegative(i, "oldCount");
/* 911*/        CollectPreconditions.checkNonnegative(j, "newCount");
/* 913*/        if(multiset.count(obj) == i)
                {
/* 914*/            multiset.setCount(obj, j);
/* 915*/            return true;
                } else
                {
/* 917*/            return false;
                }
            }

            static Iterator iteratorImpl(Multiset multiset)
            {
/*1010*/        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
            }

            static int sizeImpl(Multiset multiset)
            {
/*1066*/        long l = 0L;
/*1067*/        for(multiset = multiset.entrySet().iterator(); multiset.hasNext();)
                {
/*1067*/            Multiset.Entry entry = (Multiset.Entry)multiset.next();
/*1068*/            l += entry.getCount();
                }

/*1070*/        return Ints.saturatedCast(l);
            }

            static Multiset cast(Iterable iterable)
            {
/*1077*/        return (Multiset)iterable;
            }

            private static final Ordering DECREASING_COUNT_ORDERING = new Ordering() {

                public final int compare(Multiset.Entry entry, Multiset.Entry entry1)
                {
/*1083*/            return Ints.compare(entry1.getCount(), entry.getCount());
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*1080*/            return compare((Multiset.Entry)obj, (Multiset.Entry)obj1);
                }

    };

}
