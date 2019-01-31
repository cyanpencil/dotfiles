// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonImmutableSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Set;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, Iterators, UnmodifiableIterator

final class SingletonImmutableSet extends ImmutableSet
{

            SingletonImmutableSet(Object obj)
            {
/*  47*/        element = Preconditions.checkNotNull(obj);
            }

            SingletonImmutableSet(Object obj, int i)
            {
/*  52*/        element = obj;
/*  53*/        cachedHashCode = i;
            }

            public final int size()
            {
/*  58*/        return 1;
            }

            public final boolean isEmpty()
            {
/*  62*/        return false;
            }

            public final boolean contains(Object obj)
            {
/*  66*/        return element.equals(obj);
            }

            public final UnmodifiableIterator iterator()
            {
/*  70*/        return Iterators.singletonIterator(element);
            }

            final boolean isPartialView()
            {
/*  74*/        return false;
            }

            final int copyIntoArray(Object aobj[], int i)
            {
/*  79*/        aobj[i] = element;
/*  80*/        return i + 1;
            }

            public final boolean equals(Object obj)
            {
/*  84*/        if(obj == this)
/*  85*/            return true;
/*  87*/        if(obj instanceof Set)
/*  88*/            return ((Set) (obj = (Set)obj)).size() == 1 && element.equals(((Set) (obj)).iterator().next());
/*  91*/        else
/*  91*/            return false;
            }

            public final int hashCode()
            {
                int i;
/*  96*/        if((i = cachedHashCode) == 0)
/*  98*/            cachedHashCode = i = element.hashCode();
/* 100*/        return i;
            }

            final boolean isHashCodeFast()
            {
/* 104*/        return cachedHashCode != 0;
            }

            public final String toString()
            {
/* 108*/        String s = element.toString();
/* 109*/        return (new StringBuilder(s.length() + 2)).append('[').append(s).append(']').toString();
            }

            public final volatile Iterator iterator()
            {
/*  32*/        return iterator();
            }

            final transient Object element;
            private transient int cachedHashCode;
}
