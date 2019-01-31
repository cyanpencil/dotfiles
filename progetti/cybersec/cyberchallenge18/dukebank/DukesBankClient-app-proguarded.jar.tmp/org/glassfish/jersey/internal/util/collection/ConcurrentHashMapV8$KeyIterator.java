// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class  extends 
    implements Enumeration, Iterator
{

            public final Object next()
            {
                 ;
/*2822*/        if(( = next) == null)
                {
/*2823*/            throw new NoSuchElementException();
                } else
                {
/*2824*/            Object obj = .next;
/*2825*/            lastReturned = ;
/*2826*/            advance();
/*2827*/            return obj;
                }
            }

            public final Object nextElement()
            {
/*2831*/        return next();
            }

            ( a[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
            {
/*2817*/        super(a, i, j, k, concurrenthashmapv8);
            }
}
