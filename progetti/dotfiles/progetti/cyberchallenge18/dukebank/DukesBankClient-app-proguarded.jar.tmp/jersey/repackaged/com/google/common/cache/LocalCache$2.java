// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.AbstractQueue;
import java.util.Iterator;
import jersey.repackaged.com.google.common.collect.ImmutableSet;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class t extends AbstractQueue
{

            public final boolean offer(Object obj)
            {
/*1021*/        return true;
            }

            public final Object peek()
            {
/*1026*/        return null;
            }

            public final Object poll()
            {
/*1031*/        return null;
            }

            public final int size()
            {
/*1036*/        return 0;
            }

            public final Iterator iterator()
            {
/*1041*/        return ImmutableSet.of().iterator();
            }

            t()
            {
            }
}
