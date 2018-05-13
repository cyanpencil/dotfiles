// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            BiMap, Maps

static final class inverse extends inverse
    implements BiMap
{

            private static Predicate inversePredicate(Predicate predicate)
            {
/*3034*/        return new Predicate(predicate) {

                    public final boolean apply(java.util.Map.Entry entry)
                    {
/*3037*/                return forwardPredicate.apply(Maps.immutableEntry(entry.getValue(), entry.getKey()));
                    }

                    public final volatile boolean apply(Object obj)
                    {
/*3034*/                return apply((java.util.Map.Entry)obj);
                    }

                    final Predicate val$forwardPredicate;

                    
                    {
/*3034*/                forwardPredicate = predicate;
/*3034*/                super();
                    }
        };
            }

            final BiMap unfiltered()
            {
/*3058*/        return (BiMap)unfiltered;
            }

            public final BiMap inverse()
            {
/*3069*/        return inverse;
            }

            public final Set values()
            {
/*3074*/        return inverse.keySet();
            }

            public final volatile Collection values()
            {
/*3028*/        return values();
            }

            private final BiMap inverse;

            _cls1.val.forwardPredicate(BiMap bimap, Predicate predicate)
            {
/*3045*/        super(bimap, predicate);
/*3046*/        inverse = new <init>(bimap.inverse(), inversePredicate(predicate), this);
            }

            private inversePredicate(BiMap bimap, Predicate predicate, BiMap bimap1)
            {
/*3053*/        super(bimap, predicate);
/*3054*/        inverse = bimap1;
            }
}
