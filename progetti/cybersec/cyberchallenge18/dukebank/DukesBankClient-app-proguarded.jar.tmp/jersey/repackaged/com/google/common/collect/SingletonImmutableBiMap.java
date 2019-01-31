// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonImmutableBiMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableBiMap, CollectPreconditions, ImmutableSet, Maps, 
//            BiMap

final class SingletonImmutableBiMap extends ImmutableBiMap
{

            SingletonImmutableBiMap(Object obj, Object obj1)
            {
/*  39*/        CollectPreconditions.checkEntryNotNull(obj, obj1);
/*  40*/        singleKey = obj;
/*  41*/        singleValue = obj1;
            }

            private SingletonImmutableBiMap(Object obj, Object obj1, ImmutableBiMap immutablebimap)
            {
/*  46*/        singleKey = obj;
/*  47*/        singleValue = obj1;
/*  48*/        inverse = immutablebimap;
            }

            public final Object get(Object obj)
            {
/*  56*/        if(singleKey.equals(obj))
/*  56*/            return singleValue;
/*  56*/        else
/*  56*/            return null;
            }

            public final int size()
            {
/*  61*/        return 1;
            }

            public final boolean containsKey(Object obj)
            {
/*  65*/        return singleKey.equals(obj);
            }

            public final boolean containsValue(Object obj)
            {
/*  69*/        return singleValue.equals(obj);
            }

            final boolean isPartialView()
            {
/*  73*/        return false;
            }

            final ImmutableSet createEntrySet()
            {
/*  78*/        return ImmutableSet.of(Maps.immutableEntry(singleKey, singleValue));
            }

            final ImmutableSet createKeySet()
            {
/*  83*/        return ImmutableSet.of(singleKey);
            }

            public final ImmutableBiMap inverse()
            {
                ImmutableBiMap immutablebimap;
/*  91*/        if((immutablebimap = inverse) == null)
/*  93*/            return inverse = new SingletonImmutableBiMap(singleValue, singleKey, this);
/*  96*/        else
/*  96*/            return immutablebimap;
            }

            public final volatile BiMap inverse()
            {
/*  31*/        return inverse();
            }

            final transient Object singleKey;
            final transient Object singleValue;
            transient ImmutableBiMap inverse;
}
