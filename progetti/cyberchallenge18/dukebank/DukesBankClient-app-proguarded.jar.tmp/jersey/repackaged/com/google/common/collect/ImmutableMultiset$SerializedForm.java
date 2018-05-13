// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMultiset, Multiset

static class counts
    implements Serializable
{

            final Object elements[];
            final int counts[];

            (Multiset multiset)
            {
/* 432*/        int i = multiset.entrySet().size();
/* 433*/        elements = new Object[i];
/* 434*/        counts = new int[i];
/* 435*/        i = 0;
/* 436*/        for(multiset = multiset.entrySet().iterator(); multiset.hasNext();)
                {
/* 436*/              = (counts)multiset.next();
/* 437*/            elements[i] = .elements();
/* 438*/            counts[i] = .counts();
/* 439*/            i++;
                }

            }
}
