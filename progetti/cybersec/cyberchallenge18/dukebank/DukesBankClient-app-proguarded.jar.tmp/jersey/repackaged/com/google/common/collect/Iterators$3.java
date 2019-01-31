// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators

static class Iterator extends UnmodifiableIterator
{

            public final boolean hasNext()
            {
/* 164*/        return val$iterator.hasNext();
            }

            public final Object next()
            {
/* 168*/        return val$iterator.next();
            }

            final Iterator val$iterator;

            Iterator(Iterator iterator1)
            {
/* 161*/        val$iterator = iterator1;
/* 161*/        super();
            }
}
