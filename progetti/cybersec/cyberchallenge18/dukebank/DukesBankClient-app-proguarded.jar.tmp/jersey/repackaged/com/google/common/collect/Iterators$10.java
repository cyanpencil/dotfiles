// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators

static class terator extends UnmodifiableIterator
{

            public final boolean hasNext()
            {
/* 972*/        return val$iterator.hasNext();
            }

            public final Object next()
            {
/* 977*/        Object obj = val$iterator.next();
/* 978*/        val$iterator.remove();
/* 979*/        return obj;
            }

            public final String toString()
            {
/* 984*/        return "Iterators.consumingIterator(...)";
            }

            final Iterator val$iterator;

            terator(Iterator iterator1)
            {
/* 969*/        val$iterator = iterator1;
/* 969*/        super();
            }
}
