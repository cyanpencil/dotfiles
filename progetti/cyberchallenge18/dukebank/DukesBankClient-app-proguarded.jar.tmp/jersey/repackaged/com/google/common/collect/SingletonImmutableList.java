// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.List;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, Iterators, UnmodifiableIterator

final class SingletonImmutableList extends ImmutableList
{

            SingletonImmutableList(Object obj)
            {
/*  40*/        element = Preconditions.checkNotNull(obj);
            }

            public final Object get(int i)
            {
/*  45*/        Preconditions.checkElementIndex(i, 1);
/*  46*/        return element;
            }

            public final int indexOf(Object obj)
            {
/*  50*/        return !element.equals(obj) ? -1 : 0;
            }

            public final UnmodifiableIterator iterator()
            {
/*  54*/        return Iterators.singletonIterator(element);
            }

            public final int lastIndexOf(Object obj)
            {
/*  58*/        return indexOf(obj);
            }

            public final int size()
            {
/*  63*/        return 1;
            }

            public final ImmutableList subList(int i, int j)
            {
/*  67*/        Preconditions.checkPositionIndexes(i, j, 1);
/*  68*/        if(i == j)
/*  68*/            return ImmutableList.of();
/*  68*/        else
/*  68*/            return this;
            }

            public final ImmutableList reverse()
            {
/*  72*/        return this;
            }

            public final boolean contains(Object obj)
            {
/*  76*/        return element.equals(obj);
            }

            public final boolean equals(Object obj)
            {
/*  80*/        if(obj == this)
/*  81*/            return true;
/*  83*/        if(obj instanceof List)
/*  84*/            return ((List) (obj = (List)obj)).size() == 1 && element.equals(((List) (obj)).get(0));
/*  87*/        else
/*  87*/            return false;
            }

            public final int hashCode()
            {
/*  93*/        return 31 + element.hashCode();
            }

            public final String toString()
            {
/*  97*/        String s = element.toString();
/*  98*/        return (new StringBuilder(s.length() + 2)).append('[').append(s).append(']').toString();
            }

            public final boolean isEmpty()
            {
/* 106*/        return false;
            }

            final boolean isPartialView()
            {
/* 110*/        return false;
            }

            final int copyIntoArray(Object aobj[], int i)
            {
/* 115*/        aobj[i] = element;
/* 116*/        return i + 1;
            }

            public final volatile List subList(int i, int j)
            {
/*  33*/        return subList(i, j);
            }

            public final volatile Iterator iterator()
            {
/*  33*/        return iterator();
            }

            final transient Object element;
}
