// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Iterators

static class ptyIterator
    implements Iterator
{

            public final boolean hasNext()
            {
/* 400*/        if(!iterator.hasNext())
/* 401*/            iterator = val$iterable.iterator();
/* 403*/        return iterator.hasNext();
            }

            public final Object next()
            {
/* 407*/        if(!hasNext())
                {
/* 408*/            throw new NoSuchElementException();
                } else
                {
/* 410*/            removeFrom = iterator;
/* 411*/            return iterator.next();
                }
            }

            public final void remove()
            {
/* 415*/        CollectPreconditions.checkRemove(removeFrom != null);
/* 416*/        removeFrom.remove();
/* 417*/        removeFrom = null;
            }

            Iterator iterator;
            Iterator removeFrom;
            final Iterable val$iterable;

            nditions(Iterable iterable1)
            {
/* 394*/        val$iterable = iterable1;
/* 394*/        super();
/* 395*/        iterator = Iterators.emptyIterator();
            }
}
