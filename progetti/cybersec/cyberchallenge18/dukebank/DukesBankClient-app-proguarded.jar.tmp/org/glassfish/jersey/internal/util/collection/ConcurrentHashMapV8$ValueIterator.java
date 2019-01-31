// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class init> extends init>
    implements Enumeration, Iterator
{

            public final Object next()
            {
                init> init>;
/*2844*/        if((init> = next) == null)
                {
/*2845*/            throw new NoSuchElementException();
                } else
                {
/*2846*/            Object obj = init>.next;
/*2847*/            lastReturned = init>;
/*2848*/            advance();
/*2849*/            return obj;
                }
            }

            public final Object nextElement()
            {
/*2853*/        return next();
            }

            ( a[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
            {
/*2839*/        super(a, i, j, k, concurrenthashmapv8);
            }
}
