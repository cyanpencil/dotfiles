// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, ImmutableList, Iterables, Lists, 
//            PeekingIterator, UnmodifiableIterator, UnmodifiableListIterator, AbstractIndexedListIterator, 
//            TransformedIterator, AbstractIterator, CollectPreconditions

public final class Iterators
{
    static class MergingIterator extends UnmodifiableIterator
    {

                public boolean hasNext()
                {
/*1298*/            return !queue.isEmpty();
                }

                public Object next()
                {
                    PeekingIterator peekingiterator;
/*1303*/            Object obj = (peekingiterator = (PeekingIterator)queue.remove()).next();
/*1305*/            if(peekingiterator.hasNext())
/*1306*/                queue.add(peekingiterator);
/*1308*/            return obj;
                }

                final Queue queue;

                public MergingIterator(Iterable iterable, final Comparator itemComparator)
                {
/*1279*/            itemComparator = new Comparator() {

                        public int compare(PeekingIterator peekingiterator, PeekingIterator peekingiterator1)
                        {
/*1283*/                    return itemComparator.compare(peekingiterator.peek(), peekingiterator1.peek());
                        }

                        public volatile int compare(Object obj, Object obj1)
                        {
/*1280*/                    return compare((PeekingIterator)obj, (PeekingIterator)obj1);
                        }

                        final Comparator val$itemComparator;
                        final MergingIterator this$0;

                        
                        {
/*1280*/                    this$0 = MergingIterator.this;
/*1280*/                    itemComparator = comparator;
/*1280*/                    super();
                        }
            };
/*1287*/            queue = new PriorityQueue(2, itemComparator);
/*1289*/            iterable = iterable.iterator();
/*1289*/            do
                    {
/*1289*/                if(!iterable.hasNext())
/*1289*/                    break;
/*1289*/                if((itemComparator = (Iterator)iterable.next()).hasNext())
/*1291*/                    queue.add(Iterators.peekingIterator(itemComparator));
                    } while(true);
                }
    }

    static class PeekingImpl
        implements PeekingIterator
    {

                public boolean hasNext()
                {
/*1149*/            return hasPeeked || iterator.hasNext();
                }

                public Object next()
                {
/*1154*/            if(!hasPeeked)
                    {
/*1155*/                return iterator.next();
                    } else
                    {
/*1157*/                Object obj = peekedElement;
/*1158*/                hasPeeked = false;
/*1159*/                peekedElement = null;
/*1160*/                return obj;
                    }
                }

                public void remove()
                {
/*1165*/            Preconditions.checkState(!hasPeeked, "Can't remove after you've peeked at next");
/*1166*/            iterator.remove();
                }

                public Object peek()
                {
/*1171*/            if(!hasPeeked)
                    {
/*1172*/                peekedElement = iterator.next();
/*1173*/                hasPeeked = true;
                    }
/*1175*/            return peekedElement;
                }

                private final Iterator iterator;
                private boolean hasPeeked;
                private Object peekedElement;

                public PeekingImpl(Iterator iterator1)
                {
/*1144*/            iterator = (Iterator)Preconditions.checkNotNull(iterator1);
                }
    }


            private Iterators()
            {
            }

            /**
             * @deprecated Method emptyIterator is deprecated
             */

            public static UnmodifiableIterator emptyIterator()
            {
/* 112*/        return emptyListIterator();
            }

            static UnmodifiableListIterator emptyListIterator()
            {
/* 124*/        return EMPTY_LIST_ITERATOR;
            }

            static Iterator emptyModifiableIterator()
            {
/* 151*/        return EMPTY_MODIFIABLE_ITERATOR;
            }

            public static UnmodifiableIterator unmodifiableIterator(Iterator iterator)
            {
/* 157*/        Preconditions.checkNotNull(iterator);
/* 158*/        if(iterator instanceof UnmodifiableIterator)
/* 159*/            return (UnmodifiableIterator)iterator;
/* 161*/        else
/* 161*/            return new UnmodifiableIterator(iterator) {

                        public final boolean hasNext()
                        {
/* 164*/                    return iterator.hasNext();
                        }

                        public final Object next()
                        {
/* 168*/                    return iterator.next();
                        }

                        final Iterator val$iterator;

                    
                    {
/* 161*/                iterator = iterator1;
/* 161*/                super();
                    }
            };
            }

            /**
             * @deprecated Method unmodifiableIterator is deprecated
             */

            public static UnmodifiableIterator unmodifiableIterator(UnmodifiableIterator unmodifiableiterator)
            {
/* 181*/        return (UnmodifiableIterator)Preconditions.checkNotNull(unmodifiableiterator);
            }

            public static int size(Iterator iterator)
            {
                int i;
/* 190*/        for(i = 0; iterator.hasNext(); i++)
/* 192*/            iterator.next();

/* 195*/        return i;
            }

            public static boolean contains(Iterator iterator, Object obj)
            {
/* 202*/        return any(iterator, Predicates.equalTo(obj));
            }

            public static boolean removeAll(Iterator iterator, Collection collection)
            {
/* 216*/        return removeIf(iterator, Predicates.in(collection));
            }

            public static boolean removeIf(Iterator iterator, Predicate predicate)
            {
/* 232*/        Preconditions.checkNotNull(predicate);
/* 233*/        boolean flag = false;
/* 234*/        do
                {
/* 234*/            if(!iterator.hasNext())
/* 235*/                break;
/* 235*/            if(predicate.apply(iterator.next()))
                    {
/* 236*/                iterator.remove();
/* 237*/                flag = true;
                    }
                } while(true);
/* 240*/        return flag;
            }

            public static boolean retainAll(Iterator iterator, Collection collection)
            {
/* 254*/        return removeIf(iterator, Predicates.not(Predicates.in(collection)));
            }

            public static boolean elementsEqual(Iterator iterator, Iterator iterator1)
            {
/* 269*/        while(iterator.hasNext()) 
                {
/* 270*/            if(!iterator1.hasNext())
/* 271*/                return false;
/* 273*/            Object obj = iterator.next();
/* 274*/            Object obj1 = iterator1.next();
/* 275*/            if(!Objects.equal(obj, obj1))
/* 276*/                return false;
                }
/* 279*/        return !iterator1.hasNext();
            }

            public static String toString(Iterator iterator)
            {
/* 288*/        return Collections2.STANDARD_JOINER.appendTo(new StringBuilder("["), iterator).append(']').toString();
            }

            public static Object getOnlyElement(Iterator iterator)
            {
/* 302*/        Object obj = iterator.next();
/* 303*/        if(!iterator.hasNext())
/* 304*/            return obj;
                StringBuilder stringbuilder;
/* 307*/        (stringbuilder = new StringBuilder()).append((new StringBuilder(31 + ((String) (obj = String.valueOf(String.valueOf(obj)))).length())).append("expected one element but was: <").append(((String) (obj))).toString());
/* 309*/        for(int i = 0; i < 4 && iterator.hasNext(); i++)
                {
                    String s;
/* 310*/            stringbuilder.append((new StringBuilder(2 + (s = String.valueOf(String.valueOf(iterator.next()))).length())).append(", ").append(s).toString());
                }

/* 312*/        if(iterator.hasNext())
/* 313*/            stringbuilder.append(", ...");
/* 315*/        stringbuilder.append('>');
/* 317*/        throw new IllegalArgumentException(stringbuilder.toString());
            }

            public static Object getOnlyElement(Iterator iterator, Object obj)
            {
/* 329*/        if(iterator.hasNext())
/* 329*/            return getOnlyElement(iterator);
/* 329*/        else
/* 329*/            return obj;
            }

            public static Object[] toArray(Iterator iterator, Class class1)
            {
/* 344*/        return Iterables.toArray(iterator = Lists.newArrayList(iterator), class1);
            }

            public static boolean addAll(Collection collection, Iterator iterator)
            {
/* 358*/        Preconditions.checkNotNull(collection);
/* 359*/        Preconditions.checkNotNull(iterator);
                boolean flag;
/* 360*/        for(flag = false; iterator.hasNext(); flag |= collection.add(iterator.next()));
/* 364*/        return flag;
            }

            public static int frequency(Iterator iterator, Object obj)
            {
/* 375*/        return size(filter(iterator, Predicates.equalTo(obj)));
            }

            public static Iterator cycle(Iterable iterable)
            {
/* 393*/        Preconditions.checkNotNull(iterable);
/* 394*/        return new Iterator(iterable) {

                    public final boolean hasNext()
                    {
/* 400*/                if(!iterator.hasNext())
/* 401*/                    iterator = iterable.iterator();
/* 403*/                return iterator.hasNext();
                    }

                    public final Object next()
                    {
/* 407*/                if(!hasNext())
                        {
/* 408*/                    throw new NoSuchElementException();
                        } else
                        {
/* 410*/                    removeFrom = iterator;
/* 411*/                    return iterator.next();
                        }
                    }

                    public final void remove()
                    {
/* 415*/                CollectPreconditions.checkRemove(removeFrom != null);
/* 416*/                removeFrom.remove();
/* 417*/                removeFrom = null;
                    }

                    Iterator iterator;
                    Iterator removeFrom;
                    final Iterable val$iterable;

                    
                    {
/* 394*/                iterable = iterable1;
/* 394*/                super();
/* 395*/                iterator = Iterators.emptyIterator();
                    }
        };
            }

            public static transient Iterator cycle(Object aobj[])
            {
/* 436*/        return cycle(((Iterable) (Lists.newArrayList(aobj))));
            }

            public static Iterator concat(Iterator iterator, Iterator iterator1)
            {
/* 454*/        return concat(((Iterator) (ImmutableList.of(iterator, iterator1).iterator())));
            }

            public static Iterator concat(Iterator iterator, Iterator iterator1, Iterator iterator2)
            {
/* 473*/        return concat(((Iterator) (ImmutableList.of(iterator, iterator1, iterator2).iterator())));
            }

            public static Iterator concat(Iterator iterator, Iterator iterator1, Iterator iterator2, Iterator iterator3)
            {
/* 493*/        return concat(((Iterator) (ImmutableList.of(iterator, iterator1, iterator2, iterator3).iterator())));
            }

            public static transient Iterator concat(Iterator aiterator[])
            {
/* 512*/        return concat(((Iterator) (ImmutableList.copyOf(aiterator).iterator())));
            }

            public static Iterator concat(Iterator iterator)
            {
/* 531*/        Preconditions.checkNotNull(iterator);
/* 532*/        return new Iterator(iterator) {

                    public final boolean hasNext()
                    {
                        boolean flag;
/* 547*/                while(!(flag = ((Iterator)Preconditions.checkNotNull(current)).hasNext()) && inputs.hasNext()) 
/* 548*/                    current = (Iterator)inputs.next();
/* 550*/                return flag;
                    }

                    public final Object next()
                    {
/* 554*/                if(!hasNext())
                        {
/* 555*/                    throw new NoSuchElementException();
                        } else
                        {
/* 557*/                    removeFrom = current;
/* 558*/                    return current.next();
                        }
                    }

                    public final void remove()
                    {
/* 562*/                CollectPreconditions.checkRemove(removeFrom != null);
/* 563*/                removeFrom.remove();
/* 564*/                removeFrom = null;
                    }

                    Iterator current;
                    Iterator removeFrom;
                    final Iterator val$inputs;

                    
                    {
/* 532*/                inputs = iterator;
/* 532*/                super();
/* 533*/                current = Iterators.emptyIterator();
                    }
        };
            }

            public static UnmodifiableIterator partition(Iterator iterator, int i)
            {
/* 586*/        return partitionImpl(iterator, i, false);
            }

            public static UnmodifiableIterator paddedPartition(Iterator iterator, int i)
            {
/* 607*/        return partitionImpl(iterator, i, true);
            }

            private static UnmodifiableIterator partitionImpl(Iterator iterator, int i, boolean flag)
            {
/* 612*/        Preconditions.checkNotNull(iterator);
/* 613*/        Preconditions.checkArgument(i > 0);
/* 614*/        return new UnmodifiableIterator(iterator, i, flag) {

                    public final boolean hasNext()
                    {
/* 617*/                return iterator.hasNext();
                    }

                    public final List next()
                    {
/* 621*/                if(!hasNext())
/* 622*/                    throw new NoSuchElementException();
/* 624*/                Object aobj[] = new Object[size];
                        int j;
/* 625*/                for(j = 0; j < size && iterator.hasNext(); j++)
/* 627*/                    aobj[j] = iterator.next();

/* 629*/                for(int k = j; k < size; k++)
/* 630*/                    aobj[k] = null;

/* 634*/                List list = Collections.unmodifiableList(Arrays.asList(aobj));
/* 636*/                if(pad || j == size)
/* 636*/                    return list;
/* 636*/                else
/* 636*/                    return list.subList(0, j);
                    }

                    public final volatile Object next()
                    {
/* 614*/                return next();
                    }

                    final Iterator val$iterator;
                    final int val$size;
                    final boolean val$pad;

                    
                    {
/* 614*/                iterator = iterator1;
/* 614*/                size = i;
/* 614*/                pad = flag;
/* 614*/                super();
                    }
        };
            }

            public static UnmodifiableIterator filter(Iterator iterator, Predicate predicate)
            {
/* 646*/        Preconditions.checkNotNull(iterator);
/* 647*/        Preconditions.checkNotNull(predicate);
/* 648*/        return new AbstractIterator(iterator, predicate) {

                    protected final Object computeNext()
                    {
/* 650*/                while(unfiltered.hasNext()) 
                        {
/* 651*/                    Object obj = unfiltered.next();
/* 652*/                    if(predicate.apply(obj))
/* 653*/                        return obj;
                        }
/* 656*/                return endOfData();
                    }

                    final Iterator val$unfiltered;
                    final Predicate val$predicate;

                    
                    {
/* 648*/                unfiltered = iterator;
/* 648*/                predicate = predicate1;
/* 648*/                super();
                    }
        };
            }

            public static UnmodifiableIterator filter(Iterator iterator, Class class1)
            {
/* 675*/        return filter(iterator, Predicates.instanceOf(class1));
            }

            public static boolean any(Iterator iterator, Predicate predicate)
            {
/* 684*/        return indexOf(iterator, predicate) != -1;
            }

            public static boolean all(Iterator iterator, Predicate predicate)
            {
/* 694*/        Preconditions.checkNotNull(predicate);
/* 695*/        while(iterator.hasNext()) 
                {
/* 696*/            Object obj = iterator.next();
/* 697*/            if(!predicate.apply(obj))
/* 698*/                return false;
                }
/* 701*/        return true;
            }

            public static Object find(Iterator iterator, Predicate predicate)
            {
/* 717*/        return filter(iterator, predicate).next();
            }

            public static Object find(Iterator iterator, Predicate predicate, Object obj)
            {
/* 733*/        return getNext(filter(iterator, predicate), obj);
            }

            public static Optional tryFind(Iterator iterator, Predicate predicate)
            {
/* 751*/        if((iterator = filter(iterator, predicate)).hasNext())
/* 752*/            return Optional.of(iterator.next());
/* 752*/        else
/* 752*/            return Optional.absent();
            }

            public static int indexOf(Iterator iterator, Predicate predicate)
            {
/* 775*/        Preconditions.checkNotNull(predicate, "predicate");
/* 776*/        for(int i = 0; iterator.hasNext(); i++)
                {
/* 777*/            Object obj = iterator.next();
/* 778*/            if(predicate.apply(obj))
/* 779*/                return i;
                }

/* 782*/        return -1;
            }

            public static Iterator transform(Iterator iterator, Function function)
            {
/* 795*/        Preconditions.checkNotNull(function);
/* 796*/        return new TransformedIterator(iterator, function) {

                    final Object transform(Object obj)
                    {
/* 799*/                return function.apply(obj);
                    }

                    final Function val$function;

                    
                    {
/* 796*/                function = function1;
/* 796*/                super(iterator);
                    }
        };
            }

            public static Object get(Iterator iterator, int i)
            {
/* 815*/        checkNonnegative(i);
/* 816*/        int j = advance(iterator, i);
/* 817*/        if(!iterator.hasNext())
/* 818*/            throw new IndexOutOfBoundsException((new StringBuilder(91)).append("position (").append(i).append(") must be less than the number of elements that remained (").append(j).append(")").toString());
/* 822*/        else
/* 822*/            return iterator.next();
            }

            static void checkNonnegative(int i)
            {
/* 826*/        if(i < 0)
/* 827*/            throw new IndexOutOfBoundsException((new StringBuilder(43)).append("position (").append(i).append(") must not be negative").toString());
/* 830*/        else
/* 830*/            return;
            }

            public static Object get(Iterator iterator, int i, Object obj)
            {
/* 849*/        checkNonnegative(i);
/* 850*/        advance(iterator, i);
/* 851*/        return getNext(iterator, obj);
            }

            public static Object getNext(Iterator iterator, Object obj)
            {
/* 865*/        if(iterator.hasNext())
/* 865*/            return iterator.next();
/* 865*/        else
/* 865*/            return obj;
            }

            public static Object getLast(Iterator iterator)
            {
                Object obj;
/* 876*/        do
/* 876*/            obj = iterator.next();
/* 877*/        while(iterator.hasNext());
/* 878*/        return obj;
            }

            public static Object getLast(Iterator iterator, Object obj)
            {
/* 893*/        if(iterator.hasNext())
/* 893*/            return getLast(iterator);
/* 893*/        else
/* 893*/            return obj;
            }

            public static int advance(Iterator iterator, int i)
            {
/* 904*/        Preconditions.checkNotNull(iterator);
/* 905*/        Preconditions.checkArgument(i >= 0, "numberToAdvance must be nonnegative");
                int j;
/* 908*/        for(j = 0; j < i && iterator.hasNext(); j++)
/* 909*/            iterator.next();

/* 911*/        return j;
            }

            public static Iterator limit(Iterator iterator, int i)
            {
/* 928*/        Preconditions.checkNotNull(iterator);
/* 929*/        Preconditions.checkArgument(i >= 0, "limit is negative");
/* 930*/        return new Iterator(i, iterator) {

                    public final boolean hasNext()
                    {
/* 935*/                return count < limitSize && iterator.hasNext();
                    }

                    public final Object next()
                    {
/* 940*/                if(!hasNext())
                        {
/* 941*/                    throw new NoSuchElementException();
                        } else
                        {
/* 943*/                    count++;
/* 944*/                    return iterator.next();
                        }
                    }

                    public final void remove()
                    {
/* 949*/                iterator.remove();
                    }

                    private int count;
                    final int val$limitSize;
                    final Iterator val$iterator;

                    
                    {
/* 930*/                limitSize = i;
/* 930*/                iterator = iterator1;
/* 930*/                super();
                    }
        };
            }

            public static Iterator consumingIterator(Iterator iterator)
            {
/* 968*/        Preconditions.checkNotNull(iterator);
/* 969*/        return new UnmodifiableIterator(iterator) {

                    public final boolean hasNext()
                    {
/* 972*/                return iterator.hasNext();
                    }

                    public final Object next()
                    {
/* 977*/                Object obj = iterator.next();
/* 978*/                iterator.remove();
/* 979*/                return obj;
                    }

                    public final String toString()
                    {
/* 984*/                return "Iterators.consumingIterator(...)";
                    }

                    final Iterator val$iterator;

                    
                    {
/* 969*/                iterator = iterator1;
/* 969*/                super();
                    }
        };
            }

            static Object pollNext(Iterator iterator)
            {
/* 995*/        if(iterator.hasNext())
                {
/* 996*/            Object obj = iterator.next();
/* 997*/            iterator.remove();
/* 998*/            return obj;
                } else
                {
/*1000*/            return null;
                }
            }

            static void clear(Iterator iterator)
            {
/*1010*/        Preconditions.checkNotNull(iterator);
/*1011*/        for(; iterator.hasNext(); iterator.remove())
/*1012*/            iterator.next();

            }

            public static transient UnmodifiableIterator forArray(Object aobj[])
            {
/*1031*/        return forArray(aobj, 0, aobj.length, 0);
            }

            static UnmodifiableListIterator forArray(Object aobj[], int i, int j, int k)
            {
/*1043*/        Preconditions.checkArgument(j >= 0);
/*1044*/        int l = i + j;
/*1047*/        Preconditions.checkPositionIndexes(i, l, aobj.length);
/*1048*/        Preconditions.checkPositionIndex(k, j);
/*1049*/        if(j == 0)
/*1050*/            return emptyListIterator();
/*1058*/        else
/*1058*/            return new AbstractIndexedListIterator(j, k, aobj, i) {

                        protected final Object get(int i1)
                        {
/*1060*/                    return array[offset + i1];
                        }

                        final Object val$array[];
                        final int val$offset;

                    
                    {
/*1058*/                array = aobj;
/*1058*/                offset = k;
/*1058*/                super(i, j);
                    }
            };
            }

            public static UnmodifiableIterator singletonIterator(Object obj)
            {
/*1073*/        return new UnmodifiableIterator(obj) {

                    public final boolean hasNext()
                    {
/*1077*/                return !done;
                    }

                    public final Object next()
                    {
/*1081*/                if(done)
                        {
/*1082*/                    throw new NoSuchElementException();
                        } else
                        {
/*1084*/                    done = true;
/*1085*/                    return value;
                        }
                    }

                    boolean done;
                    final Object val$value;

                    
                    {
/*1073*/                value = obj;
/*1073*/                super();
                    }
        };
            }

            public static UnmodifiableIterator forEnumeration(Enumeration enumeration)
            {
/*1100*/        Preconditions.checkNotNull(enumeration);
/*1101*/        return new UnmodifiableIterator(enumeration) {

                    public final boolean hasNext()
                    {
/*1104*/                return enumeration.hasMoreElements();
                    }

                    public final Object next()
                    {
/*1108*/                return enumeration.nextElement();
                    }

                    final Enumeration val$enumeration;

                    
                    {
/*1101*/                enumeration = enumeration1;
/*1101*/                super();
                    }
        };
            }

            public static Enumeration asEnumeration(Iterator iterator)
            {
/*1121*/        Preconditions.checkNotNull(iterator);
/*1122*/        return new Enumeration(iterator) {

                    public final boolean hasMoreElements()
                    {
/*1125*/                return iterator.hasNext();
                    }

                    public final Object nextElement()
                    {
/*1129*/                return iterator.next();
                    }

                    final Iterator val$iterator;

                    
                    {
/*1122*/                iterator = iterator1;
/*1122*/                super();
                    }
        };
            }

            public static PeekingIterator peekingIterator(Iterator iterator)
            {
/*1219*/        if(iterator instanceof PeekingImpl)
/*1223*/            return iterator = (PeekingImpl)iterator;
/*1226*/        else
/*1226*/            return new PeekingImpl(iterator);
            }

            /**
             * @deprecated Method peekingIterator is deprecated
             */

            public static PeekingIterator peekingIterator(PeekingIterator peekingiterator)
            {
/*1237*/        return (PeekingIterator)Preconditions.checkNotNull(peekingiterator);
            }

            public static UnmodifiableIterator mergeSorted(Iterable iterable, Comparator comparator)
            {
/*1257*/        Preconditions.checkNotNull(iterable, "iterators");
/*1258*/        Preconditions.checkNotNull(comparator, "comparator");
/*1260*/        return new MergingIterator(iterable, comparator);
            }

            static ListIterator cast(Iterator iterator)
            {
/*1316*/        return (ListIterator)iterator;
            }

            static final UnmodifiableListIterator EMPTY_LIST_ITERATOR = new UnmodifiableListIterator() {

                public final boolean hasNext()
                {
/*  76*/            return false;
                }

                public final Object next()
                {
/*  80*/            throw new NoSuchElementException();
                }

                public final boolean hasPrevious()
                {
/*  84*/            return false;
                }

                public final Object previous()
                {
/*  88*/            throw new NoSuchElementException();
                }

                public final int nextIndex()
                {
/*  92*/            return 0;
                }

                public final int previousIndex()
                {
/*  96*/            return -1;
                }

    };
            private static final Iterator EMPTY_MODIFIABLE_ITERATOR = new Iterator() {

                public final boolean hasNext()
                {
/* 130*/            return false;
                }

                public final Object next()
                {
/* 134*/            throw new NoSuchElementException();
                }

                public final void remove()
                {
/* 138*/            CollectPreconditions.checkRemove(false);
                }

    };

}
