// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableBiMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, BiMap, EmptyImmutableBiMap, ImmutableMapEntry, 
//            SingletonImmutableBiMap, ImmutableSet, ImmutableCollection

public abstract class ImmutableBiMap extends ImmutableMap
    implements BiMap
{
    static class SerializedForm extends ImmutableMap.SerializedForm
    {

                SerializedForm(ImmutableBiMap immutablebimap)
                {
/* 258*/            super(immutablebimap);
                }
    }


            public static ImmutableBiMap of()
            {
/*  50*/        return EmptyImmutableBiMap.INSTANCE;
            }

            public static ImmutableBiMap of(Object obj, Object obj1)
            {
/*  57*/        return new SingletonImmutableBiMap(obj, obj1);
            }

            ImmutableBiMap()
            {
            }

            public abstract ImmutableBiMap inverse();

            public ImmutableSet values()
            {
/* 232*/        return inverse().keySet();
            }

            Object writeReplace()
            {
/* 268*/        return new SerializedForm(this);
            }

            public volatile ImmutableCollection values()
            {
/*  40*/        return values();
            }

            public volatile Collection values()
            {
/*  40*/        return values();
            }

            public volatile BiMap inverse()
            {
/*  40*/        return inverse();
            }

            public volatile Set values()
            {
/*  40*/        return values();
            }

            private static final java.util.Map.Entry EMPTY_ENTRY_ARRAY[] = new java.util.Map.Entry[0];

}
