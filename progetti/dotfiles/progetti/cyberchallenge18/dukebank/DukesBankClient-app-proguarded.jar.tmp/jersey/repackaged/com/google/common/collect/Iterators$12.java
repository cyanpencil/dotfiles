// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators

static class terator extends UnmodifiableIterator
{

            public final boolean hasNext()
            {
/*1077*/        return !done;
            }

            public final Object next()
            {
/*1081*/        if(done)
                {
/*1082*/            throw new NoSuchElementException();
                } else
                {
/*1084*/            done = true;
/*1085*/            return val$value;
                }
            }

            boolean done;
            final Object val$value;

            terator(Object obj)
            {
/*1073*/        val$value = obj;
/*1073*/        super();
            }
}
