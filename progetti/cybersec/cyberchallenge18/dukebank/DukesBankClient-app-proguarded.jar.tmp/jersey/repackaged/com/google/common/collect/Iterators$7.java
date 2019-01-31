// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIterator, Iterators

static class ator extends AbstractIterator
{

            protected final Object computeNext()
            {
/* 650*/        while(val$unfiltered.hasNext()) 
                {
/* 651*/            Object obj = val$unfiltered.next();
/* 652*/            if(val$predicate.apply(obj))
/* 653*/                return obj;
                }
/* 656*/        return endOfData();
            }

            final Iterator val$unfiltered;
            final Predicate val$predicate;

            ator(Iterator iterator, Predicate predicate1)
            {
/* 648*/        val$unfiltered = iterator;
/* 648*/        val$predicate = predicate1;
/* 648*/        super();
            }
}
