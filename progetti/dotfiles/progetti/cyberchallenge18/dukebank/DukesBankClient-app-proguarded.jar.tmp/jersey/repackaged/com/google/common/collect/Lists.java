// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.math.IntMath;
import jersey.repackaged.com.google.common.primitives.Ints;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CartesianList, CollectPreconditions, Collections2, ImmutableList, 
//            Iterables, Iterators, TransformedListIterator

public final class Lists
{
    static class RandomAccessListWrapper extends AbstractListWrapper
        implements RandomAccess
    {

                RandomAccessListWrapper(List list)
                {
/*1069*/            super(list);
                }
    }

    static class AbstractListWrapper extends AbstractList
    {

                public void add(int i, Object obj)
                {
/*1038*/            backingList.add(i, obj);
                }

                public boolean addAll(int i, Collection collection)
                {
/*1042*/            return backingList.addAll(i, collection);
                }

                public Object get(int i)
                {
/*1046*/            return backingList.get(i);
                }

                public Object remove(int i)
                {
/*1050*/            return backingList.remove(i);
                }

                public Object set(int i, Object obj)
                {
/*1054*/            return backingList.set(i, obj);
                }

                public boolean contains(Object obj)
                {
/*1058*/            return backingList.contains(obj);
                }

                public int size()
                {
/*1062*/            return backingList.size();
                }

                final List backingList;

                AbstractListWrapper(List list)
                {
/*1034*/            backingList = (List)Preconditions.checkNotNull(list);
                }
    }

    static class RandomAccessReverseList extends ReverseList
        implements RandomAccess
    {

                RandomAccessReverseList(List list)
                {
/* 921*/            super(list);
                }
    }

    static class ReverseList extends AbstractList
    {

                List getForwardList()
                {
/* 806*/            return forwardList;
                }

                private int reverseIndex(int i)
                {
/* 810*/            int j = size();
/* 811*/            Preconditions.checkElementIndex(i, j);
/* 812*/            return j - 1 - i;
                }

                private int reversePosition(int i)
                {
/* 816*/            int j = size();
/* 817*/            Preconditions.checkPositionIndex(i, j);
/* 818*/            return j - i;
                }

                public void add(int i, Object obj)
                {
/* 822*/            forwardList.add(reversePosition(i), obj);
                }

                public void clear()
                {
/* 826*/            forwardList.clear();
                }

                public Object remove(int i)
                {
/* 830*/            return forwardList.remove(reverseIndex(i));
                }

                protected void removeRange(int i, int j)
                {
/* 834*/            subList(i, j).clear();
                }

                public Object set(int i, Object obj)
                {
/* 838*/            return forwardList.set(reverseIndex(i), obj);
                }

                public Object get(int i)
                {
/* 842*/            return forwardList.get(reverseIndex(i));
                }

                public int size()
                {
/* 846*/            return forwardList.size();
                }

                public List subList(int i, int j)
                {
/* 850*/            Preconditions.checkPositionIndexes(i, j, size());
/* 851*/            return Lists.reverse(forwardList.subList(reversePosition(j), reversePosition(i)));
                }

                public Iterator iterator()
                {
/* 856*/            return listIterator();
                }

                public ListIterator listIterator(final int forwardIterator)
                {
/* 860*/            forwardIterator = reversePosition(forwardIterator);
/* 861*/            forwardIterator = forwardList.listIterator(forwardIterator);
/* 862*/            return new ListIterator() {

                        public void add(Object obj)
                        {
/* 867*/                    forwardIterator.add(obj);
/* 868*/                    forwardIterator.previous();
/* 869*/                    canRemoveOrSet = false;
                        }

                        public boolean hasNext()
                        {
/* 873*/                    return forwardIterator.hasPrevious();
                        }

                        public boolean hasPrevious()
                        {
/* 877*/                    return forwardIterator.hasNext();
                        }

                        public Object next()
                        {
/* 881*/                    if(!hasNext())
                            {
/* 882*/                        throw new NoSuchElementException();
                            } else
                            {
/* 884*/                        canRemoveOrSet = true;
/* 885*/                        return forwardIterator.previous();
                            }
                        }

                        public int nextIndex()
                        {
/* 889*/                    return reversePosition(forwardIterator.nextIndex());
                        }

                        public Object previous()
                        {
/* 893*/                    if(!hasPrevious())
                            {
/* 894*/                        throw new NoSuchElementException();
                            } else
                            {
/* 896*/                        canRemoveOrSet = true;
/* 897*/                        return forwardIterator.next();
                            }
                        }

                        public int previousIndex()
                        {
/* 901*/                    return nextIndex() - 1;
                        }

                        public void remove()
                        {
/* 905*/                    CollectPreconditions.checkRemove(canRemoveOrSet);
/* 906*/                    forwardIterator.remove();
/* 907*/                    canRemoveOrSet = false;
                        }

                        public void set(Object obj)
                        {
/* 911*/                    Preconditions.checkState(canRemoveOrSet);
/* 912*/                    forwardIterator.set(obj);
                        }

                        boolean canRemoveOrSet;
                        final ListIterator val$forwardIterator;
                        final ReverseList this$0;

                        
                        {
/* 862*/                    this$0 = ReverseList.this;
/* 862*/                    forwardIterator = listiterator;
/* 862*/                    super();
                        }
            };
                }

                private final List forwardList;


                ReverseList(List list)
                {
/* 802*/            forwardList = (List)Preconditions.checkNotNull(list);
                }
    }

    static final class CharSequenceAsList extends AbstractList
    {

                public final Character get(int i)
                {
/* 765*/            Preconditions.checkElementIndex(i, size());
/* 766*/            return Character.valueOf(sequence.charAt(i));
                }

                public final int size()
                {
/* 770*/            return sequence.length();
                }

                public final volatile Object get(int i)
                {
/* 756*/            return get(i);
                }

                private final CharSequence sequence;

                CharSequenceAsList(CharSequence charsequence)
                {
/* 761*/            sequence = charsequence;
                }
    }

    static final class StringAsImmutableList extends ImmutableList
    {

                public final int indexOf(Object obj)
                {
/* 712*/            if(obj instanceof Character)
/* 712*/                return string.indexOf(((Character)obj).charValue());
/* 712*/            else
/* 712*/                return -1;
                }

                public final int lastIndexOf(Object obj)
                {
/* 717*/            if(obj instanceof Character)
/* 717*/                return string.lastIndexOf(((Character)obj).charValue());
/* 717*/            else
/* 717*/                return -1;
                }

                public final ImmutableList subList(int i, int j)
                {
/* 723*/            Preconditions.checkPositionIndexes(i, j, size());
/* 724*/            return Lists.charactersOf(string.substring(i, j));
                }

                final boolean isPartialView()
                {
/* 728*/            return false;
                }

                public final Character get(int i)
                {
/* 732*/            Preconditions.checkElementIndex(i, size());
/* 733*/            return Character.valueOf(string.charAt(i));
                }

                public final int size()
                {
/* 737*/            return string.length();
                }

                public final volatile List subList(int i, int j)
                {
/* 701*/            return subList(i, j);
                }

                public final volatile Object get(int i)
                {
/* 701*/            return get(i);
                }

                private final String string;

                StringAsImmutableList(String s)
                {
/* 708*/            string = s;
                }
    }

    static class RandomAccessPartition extends Partition
        implements RandomAccess
    {

                RandomAccessPartition(List list, int i)
                {
/* 687*/            super(list, i);
                }
    }

    static class Partition extends AbstractList
    {

                public List get(int i)
                {
/* 669*/            Preconditions.checkElementIndex(i, size());
/* 670*/            int j = Math.min((i *= size) + size, list.size());
/* 672*/            return list.subList(i, j);
                }

                public int size()
                {
/* 676*/            return IntMath.divide(list.size(), size, RoundingMode.CEILING);
                }

                public boolean isEmpty()
                {
/* 680*/            return list.isEmpty();
                }

                public volatile Object get(int i)
                {
/* 659*/            return get(i);
                }

                final List list;
                final int size;

                Partition(List list1, int i)
                {
/* 664*/            list = list1;
/* 665*/            size = i;
                }
    }

    static class TransformingRandomAccessList extends AbstractList
        implements Serializable, RandomAccess
    {

                public void clear()
                {
/* 605*/            fromList.clear();
                }

                public Object get(int i)
                {
/* 608*/            return function.apply(fromList.get(i));
                }

                public Iterator iterator()
                {
/* 611*/            return listIterator();
                }

                public ListIterator listIterator(int i)
                {
/* 614*/            return new TransformedListIterator(fromList.listIterator(i)) {

                        Object transform(Object obj)
                        {
/* 617*/                    return function.apply(obj);
                        }

                        final TransformingRandomAccessList this$0;

                        
                        {
/* 614*/                    this$0 = TransformingRandomAccessList.this;
/* 614*/                    super(listiterator);
                        }
            };
                }

                public boolean isEmpty()
                {
/* 622*/            return fromList.isEmpty();
                }

                public Object remove(int i)
                {
/* 625*/            return function.apply(fromList.remove(i));
                }

                public int size()
                {
/* 628*/            return fromList.size();
                }

                final List fromList;
                final Function function;

                TransformingRandomAccessList(List list, Function function1)
                {
/* 601*/            fromList = (List)Preconditions.checkNotNull(list);
/* 602*/            function = (Function)Preconditions.checkNotNull(function1);
                }
    }

    static class TransformingSequentialList extends AbstractSequentialList
        implements Serializable
    {

                public void clear()
                {
/* 569*/            fromList.clear();
                }

                public int size()
                {
/* 572*/            return fromList.size();
                }

                public ListIterator listIterator(int i)
                {
/* 575*/            return new TransformedListIterator(fromList.listIterator(i)) {

                        Object transform(Object obj)
                        {
/* 578*/                    return function.apply(obj);
                        }

                        final TransformingSequentialList this$0;

                        
                        {
/* 575*/                    this$0 = TransformingSequentialList.this;
/* 575*/                    super(listiterator);
                        }
            };
                }

                final List fromList;
                final Function function;

                TransformingSequentialList(List list, Function function1)
                {
/* 560*/            fromList = (List)Preconditions.checkNotNull(list);
/* 561*/            function = (Function)Preconditions.checkNotNull(function1);
                }
    }

    static class TwoPlusArrayList extends AbstractList
        implements Serializable, RandomAccess
    {

                public int size()
                {
/* 371*/            return rest.length + 2;
                }

                public Object get(int i)
                {
/* 374*/            switch(i)
                    {
/* 376*/            case 0: // '\0'
/* 376*/                return first;

/* 378*/            case 1: // '\001'
/* 378*/                return second;
                    }
/* 381*/            Preconditions.checkElementIndex(i, size());
/* 382*/            return rest[i - 2];
                }

                final Object first;
                final Object second;
                final Object rest[];

                TwoPlusArrayList(Object obj, Object obj1, Object aobj[])
                {
/* 366*/            first = obj;
/* 367*/            second = obj1;
/* 368*/            rest = (Object[])Preconditions.checkNotNull(((Object) (aobj)));
                }
    }

    static class OnePlusArrayList extends AbstractList
        implements Serializable, RandomAccess
    {

                public int size()
                {
/* 326*/            return rest.length + 1;
                }

                public Object get(int i)
                {
/* 330*/            Preconditions.checkElementIndex(i, size());
/* 331*/            if(i == 0)
/* 331*/                return first;
/* 331*/            else
/* 331*/                return rest[i - 1];
                }

                final Object first;
                final Object rest[];

                OnePlusArrayList(Object obj, Object aobj[])
                {
/* 322*/            first = obj;
/* 323*/            rest = (Object[])Preconditions.checkNotNull(((Object) (aobj)));
                }
    }


            private Lists()
            {
            }

            public static ArrayList newArrayList()
            {
/*  88*/        return new ArrayList();
            }

            public static transient ArrayList newArrayList(Object aobj[])
            {
/* 110*/        Preconditions.checkNotNull(((Object) (aobj)));
/* 112*/        int i = computeArrayListCapacity(aobj.length);
                ArrayList arraylist;
/* 113*/        Collections.addAll(arraylist = new ArrayList(i), aobj);
/* 115*/        return arraylist;
            }

            static int computeArrayListCapacity(int i)
            {
/* 119*/        CollectPreconditions.checkNonnegative(i, "arraySize");
/* 122*/        return Ints.saturatedCast(5L + (long)i + (long)(i / 10));
            }

            public static ArrayList newArrayList(Iterable iterable)
            {
/* 142*/        Preconditions.checkNotNull(iterable);
/* 144*/        if(iterable instanceof Collection)
/* 144*/            return new ArrayList(Collections2.cast(iterable));
/* 144*/        else
/* 144*/            return newArrayList(iterable.iterator());
            }

            public static ArrayList newArrayList(Iterator iterator)
            {
                ArrayList arraylist;
/* 159*/        Iterators.addAll(arraylist = newArrayList(), iterator);
/* 161*/        return arraylist;
            }

            public static ArrayList newArrayListWithCapacity(int i)
            {
/* 185*/        CollectPreconditions.checkNonnegative(i, "initialArraySize");
/* 186*/        return new ArrayList(i);
            }

            public static ArrayList newArrayListWithExpectedSize(int i)
            {
/* 208*/        return new ArrayList(computeArrayListCapacity(i));
            }

            public static LinkedList newLinkedList()
            {
/* 232*/        return new LinkedList();
            }

            public static LinkedList newLinkedList(Iterable iterable)
            {
                LinkedList linkedlist;
/* 258*/        Iterables.addAll(linkedlist = newLinkedList(), iterable);
/* 260*/        return linkedlist;
            }

            public static CopyOnWriteArrayList newCopyOnWriteArrayList()
            {
/* 274*/        return new CopyOnWriteArrayList();
            }

            public static CopyOnWriteArrayList newCopyOnWriteArrayList(Iterable iterable)
            {
/* 289*/        iterable = ((Iterable) ((iterable instanceof Collection) ? ((Iterable) (Collections2.cast(iterable))) : ((Iterable) (newArrayList(iterable)))));
/* 292*/        return new CopyOnWriteArrayList(iterable);
            }

            public static List asList(Object obj, Object aobj[])
            {
/* 312*/        return new OnePlusArrayList(obj, aobj);
            }

            public static List asList(Object obj, Object obj1, Object aobj[])
            {
/* 355*/        return new TwoPlusArrayList(obj, obj1, aobj);
            }

            static List cartesianProduct(List list)
            {
/* 445*/        return CartesianList.create(list);
            }

            static transient List cartesianProduct(List alist[])
            {
/* 505*/        return cartesianProduct(Arrays.asList(alist));
            }

            public static List transform(List list, Function function)
            {
/* 543*/        if(list instanceof RandomAccess)
/* 543*/            return new TransformingRandomAccessList(list, function);
/* 543*/        else
/* 543*/            return new TransformingSequentialList(list, function);
            }

            public static List partition(List list, int i)
            {
/* 652*/        Preconditions.checkNotNull(list);
/* 653*/        Preconditions.checkArgument(i > 0);
/* 654*/        if(list instanceof RandomAccess)
/* 654*/            return new RandomAccessPartition(list, i);
/* 654*/        else
/* 654*/            return new Partition(list, i);
            }

            public static ImmutableList charactersOf(String s)
            {
/* 698*/        return new StringAsImmutableList((String)Preconditions.checkNotNull(s));
            }

            public static List charactersOf(CharSequence charsequence)
            {
/* 753*/        return new CharSequenceAsList((CharSequence)Preconditions.checkNotNull(charsequence));
            }

            public static List reverse(List list)
            {
/* 787*/        if(list instanceof ImmutableList)
/* 788*/            return ((ImmutableList)list).reverse();
/* 789*/        if(list instanceof ReverseList)
/* 790*/            return ((ReverseList)list).getForwardList();
/* 791*/        if(list instanceof RandomAccess)
/* 792*/            return new RandomAccessReverseList(list);
/* 794*/        else
/* 794*/            return new ReverseList(list);
            }

            static int hashCodeImpl(List list)
            {
/* 930*/        int i = 1;
/* 931*/        for(list = list.iterator(); list.hasNext();)
                {
/* 931*/            Object obj = list.next();
/* 932*/            i = ~~(i = i * 31 + (obj != null ? obj.hashCode() : 0));
                }

/* 937*/        return i;
            }

            static boolean equalsImpl(List list, Object obj)
            {
/* 944*/        if(obj == Preconditions.checkNotNull(list))
/* 945*/            return true;
/* 947*/        if(!(obj instanceof List))
/* 948*/            return false;
/* 951*/        obj = (List)obj;
/* 953*/        return list.size() == ((List) (obj)).size() && Iterators.elementsEqual(list.iterator(), ((List) (obj)).iterator());
            }

            static boolean addAllImpl(List list, int i, Iterable iterable)
            {
/* 962*/        boolean flag = false;
/* 963*/        list = list.listIterator(i);
/* 964*/        for(i = iterable.iterator(); i.hasNext();)
                {
/* 964*/            iterable = ((Iterable) (i.next()));
/* 965*/            list.add(iterable);
/* 966*/            flag = true;
                }

/* 968*/        return flag;
            }

            static int indexOfImpl(List list, Object obj)
            {
/* 975*/        for(list = list.listIterator(); list.hasNext();)
/* 977*/            if(Objects.equal(obj, list.next()))
/* 978*/                return list.previousIndex();

/* 981*/        return -1;
            }

            static int lastIndexOfImpl(List list, Object obj)
            {
/* 988*/        for(list = list.listIterator(list.size()); list.hasPrevious();)
/* 990*/            if(Objects.equal(obj, list.previous()))
/* 991*/                return list.nextIndex();

/* 994*/        return -1;
            }

            static ListIterator listIteratorImpl(List list, int i)
            {
/*1001*/        return (new AbstractListWrapper(list)).listIterator(i);
            }

            static List subListImpl(List list, int i, int j)
            {
/*1010*/        if(list instanceof RandomAccess)
/*1011*/            list = new RandomAccessListWrapper(list) {

                        public final ListIterator listIterator(int k)
                        {
/*1013*/                    return backingList.listIterator(k);
                        }

            };
/*1019*/        else
/*1019*/            list = new AbstractListWrapper(list) {

                        public final ListIterator listIterator(int k)
                        {
/*1021*/                    return backingList.listIterator(k);
                        }

            };
/*1027*/        return list.subList(i, j);
            }

            static List cast(Iterable iterable)
            {
/*1077*/        return (List)iterable;
            }
}
