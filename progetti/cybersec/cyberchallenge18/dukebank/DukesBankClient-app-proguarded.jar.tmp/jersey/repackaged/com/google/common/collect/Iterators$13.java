// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Enumeration;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators

static class terator extends UnmodifiableIterator
{

            public final boolean hasNext()
            {
/*1104*/        return val$enumeration.hasMoreElements();
            }

            public final Object next()
            {
/*1108*/        return val$enumeration.nextElement();
            }

            final Enumeration val$enumeration;

            terator(Enumeration enumeration1)
            {
/*1101*/        val$enumeration = enumeration1;
/*1101*/        super();
            }
}
