// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.math.IntMath;
import jersey.repackaged.com.google.common.math.LongMath;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, HashMultiset, ImmutableList, Iterables, 
//            Multiset, Ordering, AbstractIterator, Lists, 
//            Iterators

public final class Collections2
{
    static class PermutationIterator extends AbstractIterator
    {

                protected List computeNext()
                {
/* 613*/            if(j <= 0)
                    {
/* 614*/                return (List)endOfData();
                    } else
                    {
/* 616*/                ImmutableList immutablelist = ImmutableList.copyOf(list);
/* 617*/                calculateNextPermutation();
/* 618*/                return immutablelist;
                    }
                }

                void calculateNextPermutation()
                {
/* 622*/label0:
                    {
/* 622*/                j = list.size() - 1;
/* 623*/                int i = 0;
/* 627*/                if(j == -1)
/* 628*/                    return;
                        int k;
/* 632*/                do
                        {
/* 632*/                    while((k = c[j] + o[j]) < 0) 
/* 634*/                        switchDirection();
/* 637*/                    if(k != j + 1)
/* 638*/                        break;
/* 638*/                    if(j == 0)
/* 641*/                        break label0;
/* 641*/                    i++;
/* 642*/                    switchDirection();
                        } while(true);
/* 646*/                Collections.swap(list, (j - c[j]) + i, (j - k) + i);
/* 647*/                c[j] = k;
                    }
                }

                void switchDirection()
                {
/* 653*/            o[j] = -o[j];
/* 654*/            j--;
                }

                protected volatile Object computeNext()
                {
/* 595*/            return computeNext();
                }

                final List list;
                final int c[];
                final int o[];
                int j;

                PermutationIterator(List list1)
                {
/* 603*/            list = new ArrayList(list1);
/* 604*/            list1 = list1.size();
/* 605*/            c = new int[list1];
/* 606*/            o = new int[list1];
/* 607*/            Arrays.fill(c, 0);
/* 608*/            Arrays.fill(o, 1);
/* 609*/            j = 0x7fffffff;
                }
    }

    static final class PermutationCollection extends AbstractCollection
    {

                public final int size()
                {
/* 571*/            return IntMath.factorial(inputList.size());
                }

                public final boolean isEmpty()
                {
/* 575*/            return false;
                }

                public final Iterator iterator()
                {
/* 579*/            return new PermutationIterator(inputList);
                }

                public final boolean contains(Object obj)
                {
/* 583*/            if(obj instanceof List)
                    {
/* 584*/                obj = (List)obj;
/* 585*/                return Collections2.isPermutation(inputList, ((List) (obj)));
                    } else
                    {
/* 587*/                return false;
                    }
                }

                public final String toString()
                {
/* 591*/            String s = String.valueOf(String.valueOf(inputList));
/* 591*/            return (new StringBuilder(14 + s.length())).append("permutations(").append(s).append(")").toString();
                }

                final ImmutableList inputList;

                PermutationCollection(ImmutableList immutablelist)
                {
/* 567*/            inputList = immutablelist;
                }
    }

    static final class OrderedPermutationIterator extends AbstractIterator
    {

                protected final List computeNext()
                {
/* 495*/            if(nextPermutation == null)
                    {
/* 496*/                return (List)endOfData();
                    } else
                    {
/* 498*/                ImmutableList immutablelist = ImmutableList.copyOf(nextPermutation);
/* 499*/                calculateNextPermutation();
/* 500*/                return immutablelist;
                    }
                }

                final void calculateNextPermutation()
                {
                    int i;
/* 504*/            if((i = findNextJ()) == -1)
                    {
/* 506*/                nextPermutation = null;
/* 507*/                return;
                    } else
                    {
/* 510*/                int j = findNextL(i);
/* 511*/                Collections.swap(nextPermutation, i, j);
/* 512*/                j = nextPermutation.size();
/* 513*/                Collections.reverse(nextPermutation.subList(i + 1, j));
/* 514*/                return;
                    }
                }

                final int findNextJ()
                {
/* 517*/            for(int i = nextPermutation.size() - 2; i >= 0; i--)
/* 518*/                if(comparator.compare(nextPermutation.get(i), nextPermutation.get(i + 1)) < 0)
/* 520*/                    return i;

/* 523*/            return -1;
                }

                final int findNextL(int i)
                {
/* 527*/            Object obj = nextPermutation.get(i);
/* 528*/            for(int j = nextPermutation.size() - 1; j > i; j--)
/* 529*/                if(comparator.compare(obj, nextPermutation.get(j)) < 0)
/* 530*/                    return j;

/* 533*/            throw new AssertionError("this statement should be unreachable");
                }

                protected final volatile Object computeNext()
                {
/* 482*/            return computeNext();
                }

                List nextPermutation;
                final Comparator comparator;

                OrderedPermutationIterator(List list, Comparator comparator1)
                {
/* 490*/            nextPermutation = Lists.newArrayList(list);
/* 491*/            comparator = comparator1;
                }
    }

    static final class OrderedPermutationCollection extends AbstractCollection
    {

                private static int calculateSize(List list, Comparator comparator1)
                {
/* 433*/            long l = 1L;
/* 434*/            int i = 1;
                    int j;
/* 435*/            for(j = 1; i < list.size(); j++)
                    {
                        int k;
/* 437*/                if((k = comparator1.compare(list.get(i - 1), list.get(i))) < 0)
                        {
/* 441*/                    l *= LongMath.binomial(i, j);
/* 442*/                    j = 0;
/* 443*/                    if(!Collections2.isPositiveInt(l))
/* 444*/                        return 0x7fffffff;
                        }
/* 447*/                i++;
                    }

/* 450*/            if(!Collections2.isPositiveInt(l *= LongMath.binomial(i, j)))
/* 452*/                return 0x7fffffff;
/* 454*/            else
/* 454*/                return (int)l;
                }

                public final int size()
                {
/* 458*/            return size;
                }

                public final boolean isEmpty()
                {
/* 462*/            return false;
                }

                public final Iterator iterator()
                {
/* 466*/            return new OrderedPermutationIterator(inputList, comparator);
                }

                public final boolean contains(Object obj)
                {
/* 470*/            if(obj instanceof List)
                    {
/* 471*/                obj = (List)obj;
/* 472*/                return Collections2.isPermutation(inputList, ((List) (obj)));
                    } else
                    {
/* 474*/                return false;
                    }
                }

                public final String toString()
                {
/* 478*/            String s = String.valueOf(String.valueOf(inputList));
/* 478*/            return (new StringBuilder(30 + s.length())).append("orderedPermutationCollection(").append(s).append(")").toString();
                }

                final ImmutableList inputList;
                final Comparator comparator;
                final int size;

                OrderedPermutationCollection(Iterable iterable, Comparator comparator1)
                {
/* 417*/            inputList = Ordering.from(comparator1).immutableSortedCopy(iterable);
/* 418*/            comparator = comparator1;
/* 419*/            size = calculateSize(inputList, comparator1);
                }
    }

    static class TransformedCollection extends AbstractCollection
    {

                public void clear()
                {
/* 261*/            fromCollection.clear();
                }

                public boolean isEmpty()
                {
/* 265*/            return fromCollection.isEmpty();
                }

                public Iterator iterator()
                {
/* 269*/            return Iterators.transform(fromCollection.iterator(), function);
                }

                public int size()
                {
/* 273*/            return fromCollection.size();
                }

                final Collection fromCollection;
                final Function function;

                TransformedCollection(Collection collection, Function function1)
                {
/* 256*/            fromCollection = (Collection)Preconditions.checkNotNull(collection);
/* 257*/            function = (Function)Preconditions.checkNotNull(function1);
                }
    }

    static class FilteredCollection extends AbstractCollection
    {

                FilteredCollection createCombined(Predicate predicate1)
                {
/* 145*/            return new FilteredCollection(unfiltered, Predicates.and(predicate, predicate1));
                }

                public boolean add(Object obj)
                {
/* 152*/            Preconditions.checkArgument(predicate.apply(obj));
/* 153*/            return unfiltered.add(obj);
                }

                public boolean addAll(Collection collection)
                {
                    Object obj;
/* 158*/            for(Iterator iterator1 = collection.iterator(); iterator1.hasNext(); Preconditions.checkArgument(predicate.apply(obj)))
/* 158*/                obj = iterator1.next();

/* 161*/            return unfiltered.addAll(collection);
                }

                public void clear()
                {
/* 166*/            Iterables.removeIf(unfiltered, predicate);
                }

                public boolean contains(Object obj)
                {
/* 171*/            if(Collections2.safeContains(unfiltered, obj))
                    {
/* 173*/                obj = obj;
/* 174*/                return predicate.apply(obj);
                    } else
                    {
/* 176*/                return false;
                    }
                }

                public boolean containsAll(Collection collection)
                {
/* 181*/            return Collections2.containsAllImpl(this, collection);
                }

                public boolean isEmpty()
                {
/* 186*/            return !Iterables.any(unfiltered, predicate);
                }

                public Iterator iterator()
                {
/* 191*/            return Iterators.filter(unfiltered.iterator(), predicate);
                }

                public boolean remove(Object obj)
                {
/* 196*/            return contains(obj) && unfiltered.remove(obj);
                }

                public boolean removeAll(Collection collection)
                {
/* 201*/            return Iterables.removeIf(unfiltered, Predicates.and(predicate, Predicates.in(collection)));
                }

                public boolean retainAll(Collection collection)
                {
/* 206*/            return Iterables.removeIf(unfiltered, Predicates.and(predicate, Predicates.not(Predicates.in(collection))));
                }

                public int size()
                {
/* 211*/            return Iterators.size(iterator());
                }

                public Object[] toArray()
                {
/* 217*/            return Lists.newArrayList(iterator()).toArray();
                }

                public Object[] toArray(Object aobj[])
                {
/* 222*/            return Lists.newArrayList(iterator()).toArray(aobj);
                }

                final Collection unfiltered;
                final Predicate predicate;

                FilteredCollection(Collection collection, Predicate predicate1)
                {
/* 140*/            unfiltered = collection;
/* 141*/            predicate = predicate1;
                }
    }


            private Collections2()
            {
            }

            public static Collection filter(Collection collection, Predicate predicate)
            {
/*  91*/        if(collection instanceof FilteredCollection)
/*  94*/            return ((FilteredCollection)collection).createCombined(predicate);
/*  97*/        else
/*  97*/            return new FilteredCollection((Collection)Preconditions.checkNotNull(collection), (Predicate)Preconditions.checkNotNull(predicate));
            }

            static boolean safeContains(Collection collection, Object obj)
            {
/* 108*/        Preconditions.checkNotNull(collection);
/* 110*/        return collection.contains(obj);
/* 111*/        JVM INSTR pop ;
/* 112*/        return false;
/* 113*/        JVM INSTR pop ;
/* 114*/        return false;
            }

            static boolean safeRemove(Collection collection, Object obj)
            {
/* 124*/        Preconditions.checkNotNull(collection);
/* 126*/        return collection.remove(obj);
/* 127*/        JVM INSTR pop ;
/* 128*/        return false;
/* 129*/        JVM INSTR pop ;
/* 130*/        return false;
            }

            public static Collection transform(Collection collection, Function function)
            {
/* 247*/        return new TransformedCollection(collection, function);
            }

            static boolean containsAllImpl(Collection collection, Collection collection1)
            {
/* 290*/        return Iterables.all(collection1, Predicates.in(collection));
            }

            static String toStringImpl(Collection collection)
            {
/* 297*/        StringBuilder stringbuilder = newStringBuilderForCollection(collection.size()).append('[');
/* 299*/        STANDARD_JOINER.appendTo(stringbuilder, Iterables.transform(collection, new Function(collection) {

                    public final Object apply(Object obj)
                    {
/* 302*/                if(obj == collection)
/* 302*/                    return "(this Collection)";
/* 302*/                else
/* 302*/                    return obj;
                    }

                    final Collection val$collection;

                    
                    {
/* 300*/                collection = collection1;
/* 300*/                super();
                    }
        }));
/* 305*/        return stringbuilder.append(']').toString();
            }

            static StringBuilder newStringBuilderForCollection(int i)
            {
/* 312*/        CollectPreconditions.checkNonnegative(i, "size");
/* 313*/        return new StringBuilder((int)Math.min((long)i << 3, 0x40000000L));
            }

            static Collection cast(Iterable iterable)
            {
/* 320*/        return (Collection)iterable;
            }

            public static Collection orderedPermutations(Iterable iterable)
            {
/* 354*/        return orderedPermutations(iterable, ((Comparator) (Ordering.natural())));
            }

            public static Collection orderedPermutations(Iterable iterable, Comparator comparator)
            {
/* 406*/        return new OrderedPermutationCollection(iterable, comparator);
            }

            public static Collection permutations(Collection collection)
            {
/* 559*/        return new PermutationCollection(ImmutableList.copyOf(collection));
            }

            private static boolean isPermutation(List list, List list1)
            {
/* 663*/        if(list.size() != list1.size())
                {
/* 664*/            return false;
                } else
                {
/* 666*/            list = HashMultiset.create(list);
/* 667*/            list1 = HashMultiset.create(list1);
/* 668*/            return list.equals(list1);
                }
            }

            private static boolean isPositiveInt(long l)
            {
/* 672*/        return l >= 0L && l <= 0x7fffffffL;
            }

            static final Joiner STANDARD_JOINER = Joiner.on(", ").useForNull("null");



}
