// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators

static class val.iterator
    implements Iterator
{

            public final boolean hasNext()
            {
/* 935*/        return count < val$limitSize && val$iterator.hasNext();
            }

            public final Object next()
            {
/* 940*/        if(!hasNext())
                {
/* 941*/            throw new NoSuchElementException();
                } else
                {
/* 943*/            count++;
/* 944*/            return val$iterator.next();
                }
            }

            public final void remove()
            {
/* 949*/        val$iterator.remove();
            }

            private int count;
            final int val$limitSize;
            final Iterator val$iterator;

            (int i, Iterator iterator1)
            {
/* 930*/        val$limitSize = i;
/* 930*/        val$iterator = iterator1;
/* 930*/        super();
            }
}
