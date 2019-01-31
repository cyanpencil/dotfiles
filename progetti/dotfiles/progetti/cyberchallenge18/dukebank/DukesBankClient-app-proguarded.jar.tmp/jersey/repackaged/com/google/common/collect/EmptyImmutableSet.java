// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, ImmutableList, Iterators, UnmodifiableIterator

final class EmptyImmutableSet extends ImmutableSet
{

            private EmptyImmutableSet()
            {
            }

            public final int size()
            {
/*  39*/        return 0;
            }

            public final boolean isEmpty()
            {
/*  43*/        return true;
            }

            public final boolean contains(Object obj)
            {
/*  47*/        return false;
            }

            public final boolean containsAll(Collection collection)
            {
/*  51*/        return collection.isEmpty();
            }

            public final UnmodifiableIterator iterator()
            {
/*  55*/        return Iterators.emptyIterator();
            }

            final boolean isPartialView()
            {
/*  59*/        return false;
            }

            final int copyIntoArray(Object aobj[], int i)
            {
/*  64*/        return i;
            }

            public final ImmutableList asList()
            {
/*  69*/        return ImmutableList.of();
            }

            public final boolean equals(Object obj)
            {
/*  73*/        if(obj instanceof Set)
/*  74*/            return ((Set) (obj = (Set)obj)).isEmpty();
/*  77*/        else
/*  77*/            return false;
            }

            public final int hashCode()
            {
/*  81*/        return 0;
            }

            final boolean isHashCodeFast()
            {
/*  85*/        return true;
            }

            public final String toString()
            {
/*  89*/        return "[]";
            }

            public final volatile Iterator iterator()
            {
/*  31*/        return iterator();
            }

            static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();

}
