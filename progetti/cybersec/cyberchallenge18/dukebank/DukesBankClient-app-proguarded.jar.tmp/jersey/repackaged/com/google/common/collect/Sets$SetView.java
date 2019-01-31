// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Sets, Iterators

public static abstract class <init> extends AbstractSet
{

            private <init>()
            {
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
/* 586*/                super(null);
                    }
    }

}
