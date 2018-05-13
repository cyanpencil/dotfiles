// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableListIterator, Iterators

static class ListIterator extends UnmodifiableListIterator
{

            public final boolean hasNext()
            {
/*  76*/        return false;
            }

            public final Object next()
            {
/*  80*/        throw new NoSuchElementException();
            }

            public final boolean hasPrevious()
            {
/*  84*/        return false;
            }

            public final Object previous()
            {
/*  88*/        throw new NoSuchElementException();
            }

            public final int nextIndex()
            {
/*  92*/        return 0;
            }

            public final int previousIndex()
            {
/*  96*/        return -1;
            }

            ListIterator()
            {
            }
}
