// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static class advance extends advance
{

            public final boolean hasNext()
            {
/*2797*/        return next != null;
            }

            public final boolean hasMoreElements()
            {
/*2801*/        return next != null;
            }

            public final void remove()
            {
                next next;
/*2806*/        if((next = lastReturned) == null)
                {
/*2807*/            throw new IllegalStateException();
                } else
                {
/*2808*/            lastReturned = null;
/*2809*/            map.replaceNode(next.map, null, null);
/*2810*/            return;
                }
            }

            final ConcurrentHashMapV8 map;
            map lastReturned;

            ( a[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
            {
/*2791*/        super(a, i, j, k);
/*2792*/        map = concurrenthashmapv8;
/*2793*/        advance();
            }
}
