// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmptyImmutableBiMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableBiMap, ImmutableSet, ImmutableSetMultimap, BiMap

final class EmptyImmutableBiMap extends ImmutableBiMap
{

            private EmptyImmutableBiMap()
            {
            }

            public final ImmutableBiMap inverse()
            {
/*  36*/        return this;
            }

            public final int size()
            {
/*  41*/        return 0;
            }

            public final boolean isEmpty()
            {
/*  46*/        return true;
            }

            public final Object get(Object obj)
            {
/*  51*/        return null;
            }

            public final ImmutableSet entrySet()
            {
/*  56*/        return ImmutableSet.of();
            }

            final ImmutableSet createEntrySet()
            {
/*  61*/        throw new AssertionError("should never be called");
            }

            public final ImmutableSetMultimap asMultimap()
            {
/*  66*/        return ImmutableSetMultimap.of();
            }

            public final ImmutableSet keySet()
            {
/*  71*/        return ImmutableSet.of();
            }

            final boolean isPartialView()
            {
/*  76*/        return false;
            }

            public final volatile BiMap inverse()
            {
/*  28*/        return inverse();
            }

            public final volatile Set entrySet()
            {
/*  28*/        return entrySet();
            }

            public final volatile Set keySet()
            {
/*  28*/        return keySet();
            }

            static final EmptyImmutableBiMap INSTANCE = new EmptyImmutableBiMap();

}
