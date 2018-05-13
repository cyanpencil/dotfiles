// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMultiset, ImmutableList, ImmutableMap, ImmutableSet, 
//            Multiset, Multisets

class RegularImmutableMultiset extends ImmutableMultiset
{

            RegularImmutableMultiset(ImmutableMap immutablemap, int i)
            {
/*  39*/        map = immutablemap;
/*  40*/        size = i;
            }

            boolean isPartialView()
            {
/*  45*/        return map.isPartialView();
            }

            public int count(Object obj)
            {
/*  50*/        if((obj = (Integer)map.get(obj)) == null)
/*  51*/            return 0;
/*  51*/        else
/*  51*/            return ((Integer) (obj)).intValue();
            }

            public int size()
            {
/*  56*/        return size;
            }

            public boolean contains(Object obj)
            {
/*  61*/        return map.containsKey(obj);
            }

            public ImmutableSet elementSet()
            {
/*  66*/        return map.keySet();
            }

            Multiset.Entry getEntry(int i)
            {
/*  71*/        return Multisets.immutableEntry((i = (java.util.Map.Entry)map.entrySet().asList().get(i)).getKey(), ((Integer)i.getValue()).intValue());
            }

            public int hashCode()
            {
/*  77*/        return map.hashCode();
            }

            public volatile Set elementSet()
            {
/*  31*/        return elementSet();
            }

            private final transient ImmutableMap map;
            private final transient int size;
}
