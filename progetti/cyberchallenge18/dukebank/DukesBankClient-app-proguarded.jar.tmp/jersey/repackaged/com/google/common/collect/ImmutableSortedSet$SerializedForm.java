// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableSortedSet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSortedSet

static class elements
    implements Serializable
{

            final Comparator comparator;
            final Object elements[];

            public (Comparator comparator1, Object aobj[])
            {
/* 815*/        comparator = comparator1;
/* 816*/        elements = aobj;
            }
}
