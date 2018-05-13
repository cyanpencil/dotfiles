// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterables, Lists, Maps

static final class predicate extends predicate
{

            public final boolean remove(Object obj)
            {
/*2625*/        return Iterables.removeFirstMatching(unfiltered.entrySet(), Predicates.and(predicate, Maps.valuePredicateOnEntries(Predicates.equalTo(obj)))) != null;
            }

            private boolean removeIf(Predicate predicate1)
            {
/*2631*/        return Iterables.removeIf(unfiltered.entrySet(), Predicates.and(predicate, Maps.valuePredicateOnEntries(predicate1)));
            }

            public final boolean removeAll(Collection collection)
            {
/*2636*/        return removeIf(Predicates.in(collection));
            }

            public final boolean retainAll(Collection collection)
            {
/*2640*/        return removeIf(Predicates.not(Predicates.in(collection)));
            }

            public final Object[] toArray()
            {
/*2645*/        return Lists.newArrayList(iterator()).toArray();
            }

            public final Object[] toArray(Object aobj[])
            {
/*2649*/        return Lists.newArrayList(iterator()).toArray(aobj);
            }

            Map unfiltered;
            Predicate predicate;

            (Map map, Map map1, Predicate predicate1)
            {
/*2619*/        super(map);
/*2620*/        unfiltered = map1;
/*2621*/        predicate = predicate1;
            }
}
