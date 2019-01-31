// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.primitives.Ints;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Ordering, Multiset, Multisets

static class ry extends Ordering
{

            public final int compare(ry ry, ry ry1)
            {
/*1083*/        return Ints.compare(ry1.getCount(), ry.getCount());
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*1080*/        return compare((ry)obj, (ry)obj1);
            }

            ry()
            {
            }
}
