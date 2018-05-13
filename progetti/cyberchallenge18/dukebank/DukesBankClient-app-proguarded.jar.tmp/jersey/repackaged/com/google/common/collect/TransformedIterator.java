// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformedIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import jersey.repackaged.com.google.common.base.Preconditions;

abstract class TransformedIterator
    implements Iterator
{

            TransformedIterator(Iterator iterator)
            {
/*  36*/        backingIterator = (Iterator)Preconditions.checkNotNull(iterator);
            }

            abstract Object transform(Object obj);

            public final boolean hasNext()
            {
/*  43*/        return backingIterator.hasNext();
            }

            public final Object next()
            {
/*  48*/        return transform(backingIterator.next());
            }

            public final void remove()
            {
/*  53*/        backingIterator.remove();
            }

            final Iterator backingIterator;
}
