// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultiset, Iterables, Maps, Multisets

public final class HashMultiset extends AbstractMapBasedMultiset
{

            public static HashMultiset create(int i)
            {
/*  53*/        return new HashMultiset(i);
            }

            public static HashMultiset create(Iterable iterable)
            {
                HashMultiset hashmultiset;
/*  65*/        Iterables.addAll(hashmultiset = create(Multisets.inferDistinctElements(iterable)), iterable);
/*  68*/        return hashmultiset;
            }

            private HashMultiset()
            {
/*  72*/        super(new HashMap());
            }

            private HashMultiset(int i)
            {
/*  76*/        super(Maps.newHashMapWithExpectedSize(i));
            }

            public final volatile int setCount(Object obj, int i)
            {
/*  34*/        return super.setCount(obj, i);
            }

            public final volatile int remove(Object obj, int i)
            {
/*  34*/        return super.remove(obj, i);
            }

            public final volatile int add(Object obj, int i)
            {
/*  34*/        return super.add(obj, i);
            }

            public final volatile int count(Object obj)
            {
/*  34*/        return super.count(obj);
            }

            public final volatile Iterator iterator()
            {
/*  34*/        return super.iterator();
            }

            public final volatile int size()
            {
/*  34*/        return super.size();
            }

            public final volatile void clear()
            {
/*  34*/        super.clear();
            }

            public final volatile Set entrySet()
            {
/*  34*/        return super.entrySet();
            }

            public final volatile String toString()
            {
/*  34*/        return super.toString();
            }

            public final volatile int hashCode()
            {
/*  34*/        return super.hashCode();
            }

            public final volatile boolean equals(Object obj)
            {
/*  34*/        return super.equals(obj);
            }

            public final volatile Set elementSet()
            {
/*  34*/        return super.elementSet();
            }

            public final volatile boolean retainAll(Collection collection)
            {
/*  34*/        return super.retainAll(collection);
            }

            public final volatile boolean removeAll(Collection collection)
            {
/*  34*/        return super.removeAll(collection);
            }

            public final volatile boolean addAll(Collection collection)
            {
/*  34*/        return super.addAll(collection);
            }

            public final volatile boolean setCount(Object obj, int i, int j)
            {
/*  34*/        return super.setCount(obj, i, j);
            }

            public final volatile boolean remove(Object obj)
            {
/*  34*/        return super.remove(obj);
            }

            public final volatile boolean add(Object obj)
            {
/*  34*/        return super.add(obj);
            }

            public final volatile boolean contains(Object obj)
            {
/*  34*/        return super.contains(obj);
            }

            public final volatile boolean isEmpty()
            {
/*  34*/        return super.isEmpty();
            }
}
