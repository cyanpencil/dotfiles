// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformedListIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.ListIterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedIterator, Iterators

abstract class TransformedListIterator extends TransformedIterator
    implements ListIterator
{

            TransformedListIterator(ListIterator listiterator)
            {
/*  35*/        super(listiterator);
            }

            private ListIterator backingIterator()
            {
/*  39*/        return Iterators.cast(backingIterator);
            }

            public final boolean hasPrevious()
            {
/*  44*/        return backingIterator().hasPrevious();
            }

            public final Object previous()
            {
/*  49*/        return transform(backingIterator().previous());
            }

            public final int nextIndex()
            {
/*  54*/        return backingIterator().nextIndex();
            }

            public final int previousIndex()
            {
/*  59*/        return backingIterator().previousIndex();
            }

            public void set(Object obj)
            {
/*  64*/        throw new UnsupportedOperationException();
            }

            public void add(Object obj)
            {
/*  69*/        throw new UnsupportedOperationException();
            }
}
