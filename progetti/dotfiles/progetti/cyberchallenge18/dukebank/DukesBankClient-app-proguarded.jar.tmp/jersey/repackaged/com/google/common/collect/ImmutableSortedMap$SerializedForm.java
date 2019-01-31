// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSortedMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Comparator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, ImmutableSortedMap

static class comparator extends comparator
{

            private final Comparator comparator;

            (ImmutableSortedMap immutablesortedmap)
            {
/* 683*/        super(immutablesortedmap);
/* 684*/        comparator = immutablesortedmap.comparator();
            }
}
