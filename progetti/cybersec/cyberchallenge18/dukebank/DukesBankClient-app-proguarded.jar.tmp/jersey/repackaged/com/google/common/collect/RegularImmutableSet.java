// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, Hashing, Iterators, RegularImmutableAsList, 
//            UnmodifiableIterator, ImmutableList

final class RegularImmutableSet extends ImmutableSet
{

            RegularImmutableSet(Object aobj[], int i, Object aobj1[], int j)
            {
/*  39*/        elements = aobj;
/*  40*/        table = aobj1;
/*  41*/        mask = j;
/*  42*/        hashCode = i;
            }

            public final boolean contains(Object obj)
            {
/*  46*/        if(obj == null)
/*  47*/            return false;
/*  49*/        int i = Hashing.smear(obj.hashCode());
/*  50*/        do
                {
                    Object obj1;
/*  50*/            if((obj1 = table[i & mask]) == null)
/*  52*/                return false;
/*  54*/            if(obj1.equals(obj))
/*  55*/                return true;
/*  49*/            i++;
                } while(true);
            }

            public final int size()
            {
/*  62*/        return elements.length;
            }

            public final UnmodifiableIterator iterator()
            {
/*  68*/        return Iterators.forArray(elements);
            }

            final int copyIntoArray(Object aobj[], int i)
            {
/*  73*/        System.arraycopy(((Object) (elements)), 0, ((Object) (aobj)), i, elements.length);
/*  74*/        return i + elements.length;
            }

            final ImmutableList createAsList()
            {
/*  79*/        return new RegularImmutableAsList(this, elements);
            }

            final boolean isPartialView()
            {
/*  84*/        return false;
            }

            public final int hashCode()
            {
/*  88*/        return hashCode;
            }

            final boolean isHashCodeFast()
            {
/*  92*/        return true;
            }

            public final volatile Iterator iterator()
            {
/*  27*/        return iterator();
            }

            private final Object elements[];
            final transient Object table[];
            private final transient int mask;
            private final transient int hashCode;
}
