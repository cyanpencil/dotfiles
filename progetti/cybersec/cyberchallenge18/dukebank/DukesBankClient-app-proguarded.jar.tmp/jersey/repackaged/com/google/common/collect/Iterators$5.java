// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Iterators

static class ptyIterator
    implements Iterator
{

            public final boolean hasNext()
            {
                boolean flag;
/* 547*/        while(!(flag = ((Iterator)Preconditions.checkNotNull(current)).hasNext()) && val$inputs.hasNext()) 
/* 548*/            current = (Iterator)val$inputs.next();
/* 550*/        return flag;
            }

            public final Object next()
            {
/* 554*/        if(!hasNext())
                {
/* 555*/            throw new NoSuchElementException();
                } else
                {
/* 557*/            removeFrom = current;
/* 558*/            return current.next();
                }
            }

            public final void remove()
            {
/* 562*/        CollectPreconditions.checkRemove(removeFrom != null);
/* 563*/        removeFrom.remove();
/* 564*/        removeFrom = null;
            }

            Iterator current;
            Iterator removeFrom;
            final Iterator val$inputs;

            nditions(Iterator iterator)
            {
/* 532*/        val$inputs = iterator;
/* 532*/        super();
/* 533*/        current = Iterators.emptyIterator();
            }
}
