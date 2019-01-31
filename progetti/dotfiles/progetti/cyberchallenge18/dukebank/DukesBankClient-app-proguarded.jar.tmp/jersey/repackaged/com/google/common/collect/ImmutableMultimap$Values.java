// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, ImmutableMap, ImmutableMultimap, UnmodifiableIterator

static final class multimap extends ImmutableCollection
{

            public final boolean contains(Object obj)
            {
/* 650*/        return multimap.containsValue(obj);
            }

            public final UnmodifiableIterator iterator()
            {
/* 654*/        return multimap.valueIterator();
            }

            final int copyIntoArray(Object aobj[], int i)
            {
                ImmutableCollection immutablecollection;
/* 660*/        for(Iterator iterator1 = multimap.map.values().iterator(); iterator1.hasNext();)
/* 660*/            i = (immutablecollection = (ImmutableCollection)iterator1.next()).copyIntoArray(aobj, i);

/* 663*/        return i;
            }

            public final int size()
            {
/* 668*/        return multimap.size();
            }

            final boolean isPartialView()
            {
/* 672*/        return true;
            }

            public final volatile Iterator iterator()
            {
/* 641*/        return iterator();
            }

            private final transient ImmutableMultimap multimap;

            (ImmutableMultimap immutablemultimap)
            {
/* 645*/        multimap = immutablemultimap;
            }
}
