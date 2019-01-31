// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList, Iterators, UnmodifiableListIterator

class RegularImmutableList extends ImmutableList
{

            RegularImmutableList(Object aobj[], int i, int j)
            {
/*  37*/        offset = i;
/*  38*/        size = j;
/*  39*/        array = aobj;
            }

            RegularImmutableList(Object aobj[])
            {
/*  43*/        this(aobj, 0, aobj.length);
            }

            public int size()
            {
/*  48*/        return size;
            }

            boolean isPartialView()
            {
/*  52*/        return size != array.length;
            }

            int copyIntoArray(Object aobj[], int i)
            {
/*  57*/        System.arraycopy(((Object) (array)), offset, ((Object) (aobj)), i, size);
/*  58*/        return i + size;
            }

            public Object get(int i)
            {
/*  65*/        Preconditions.checkElementIndex(i, size);
/*  66*/        return array[i + offset];
            }

            public int indexOf(Object obj)
            {
/*  71*/        if(obj == null)
/*  72*/            return -1;
/*  74*/        for(int i = 0; i < size; i++)
/*  75*/            if(array[offset + i].equals(obj))
/*  76*/                return i;

/*  79*/        return -1;
            }

            public int lastIndexOf(Object obj)
            {
/*  84*/        if(obj == null)
/*  85*/            return -1;
/*  87*/        for(int i = size - 1; i >= 0; i--)
/*  88*/            if(array[offset + i].equals(obj))
/*  89*/                return i;

/*  92*/        return -1;
            }

            ImmutableList subListUnchecked(int i, int j)
            {
/*  97*/        return new RegularImmutableList(array, offset + i, j - i);
            }

            public UnmodifiableListIterator listIterator(int i)
            {
/* 106*/        return Iterators.forArray(array, offset, size, i);
            }

            public volatile ListIterator listIterator(int i)
            {
/*  29*/        return listIterator(i);
            }

            private final transient int offset;
            private final transient int size;
            private final transient Object array[];
}
