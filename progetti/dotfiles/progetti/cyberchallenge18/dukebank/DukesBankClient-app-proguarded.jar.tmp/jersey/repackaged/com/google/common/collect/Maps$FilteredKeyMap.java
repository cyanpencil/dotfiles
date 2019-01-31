// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Sets

static class keyPredicate extends dMap
{

            protected Set createEntrySet()
            {
/*2664*/        return Sets.filter(unfiltered.entrySet(), predicate);
            }

            Set createKeySet()
            {
/*2669*/        return Sets.filter(unfiltered.keySet(), keyPredicate);
            }

            public boolean containsKey(Object obj)
            {
/*2677*/        return unfiltered.containsKey(obj) && keyPredicate.apply(obj);
            }

            Predicate keyPredicate;

            dMap(Map map, Predicate predicate, Predicate predicate1)
            {
/*2658*/        super(map, predicate1);
/*2659*/        keyPredicate = predicate;
            }
}
