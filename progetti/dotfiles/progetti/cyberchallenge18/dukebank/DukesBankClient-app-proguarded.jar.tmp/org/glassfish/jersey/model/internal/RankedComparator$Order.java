// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RankedComparator.java

package org.glassfish.jersey.model.internal;


// Referenced classes of package org.glassfish.jersey.model.internal:
//            RankedComparator

public static final class ordering extends Enum
{

            public static ordering[] values()
            {
/*  59*/        return (ordering[])$VALUES.clone();
            }

            public static r_3B_.clone valueOf(String s)
            {
/*  59*/        return (r_3B_.clone)Enum.valueOf(org/glassfish/jersey/model/internal/RankedComparator$Order, s);
            }

            public static final DESCENDING ASCENDING;
            public static final DESCENDING DESCENDING;
            private final int ordering;
            private static final DESCENDING $VALUES[];

            static 
            {
/*  63*/        ASCENDING = new <init>("ASCENDING", 0, 1);
/*  67*/        DESCENDING = new <init>("DESCENDING", 1, -1);
/*  59*/        $VALUES = (new .VALUES[] {
/*  59*/            ASCENDING, DESCENDING
                });
            }


            private (String s, int i, int j)
            {
/*  71*/        super(s, i);
/*  72*/        ordering = j;
            }
}
