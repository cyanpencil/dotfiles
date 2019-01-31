// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Sets

static class tView extends tView
{

            public final Iterator iterator()
            {
/* 646*/        return Iterators.filter(val$set1.iterator(), val$inSet2);
            }

            public final int size()
            {
/* 649*/        return Iterators.size(iterator());
            }

            public final boolean isEmpty()
            {
/* 652*/        return !iterator().hasNext();
            }

            public final boolean contains(Object obj)
            {
/* 655*/        return val$set1.contains(obj) && val$set2.contains(obj);
            }

            public final boolean containsAll(Collection collection)
            {
/* 658*/        return val$set1.containsAll(collection) && val$set2.containsAll(collection);
            }

            final Set val$set1;
            final Predicate val$inSet2;
            final Set val$set2;

            tView(Set set, Predicate predicate, Set set3)
            {
/* 644*/        val$set1 = set;
/* 644*/        val$inSet2 = predicate;
/* 644*/        val$set2 = set3;
/* 644*/        super();
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/collect/Sets$1

/* anonymous class */
    static class Sets._cls1 extends Sets.SetView
    {

                public final int size()
                {
/* 588*/            return set1.size() + set2minus1.size();
                }

                public final boolean isEmpty()
                {
/* 591*/            return set1.isEmpty() && set2.isEmpty();
                }

                public final Iterator iterator()
                {
/* 594*/            return Iterators.unmodifiableIterator(Iterators.concat(set1.iterator(), set2minus1.iterator()));
                }

                public final boolean contains(Object obj)
                {
/* 598*/            return set1.contains(obj) || set2.contains(obj);
                }

                final Set val$set1;
                final Set val$set2minus1;
                final Set val$set2;

                    
                    {
/* 586*/                set1 = set;
/* 586*/                set2minus1 = set3;
/* 586*/                set2 = set4;
/* 586*/                super();
                    }
    }

}
