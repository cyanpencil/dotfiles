// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RankedComparator.java

package org.glassfish.jersey.model.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            RankedProvider

public class RankedComparator
    implements Comparator
{
    public static final class Order extends Enum
    {

                public static Order[] values()
                {
/*  59*/            return (Order[])$VALUES.clone();
                }

                public static Order valueOf(String s)
                {
/*  59*/            return (Order)Enum.valueOf(org/glassfish/jersey/model/internal/RankedComparator$Order, s);
                }

                public static final Order ASCENDING;
                public static final Order DESCENDING;
                private final int ordering;
                private static final Order $VALUES[];

                static 
                {
/*  63*/            ASCENDING = new Order("ASCENDING", 0, 1);
/*  67*/            DESCENDING = new Order("DESCENDING", 1, -1);
/*  59*/            $VALUES = (new Order[] {
/*  59*/                ASCENDING, DESCENDING
                    });
                }


                private Order(String s, int i, int j)
                {
/*  71*/            super(s, i);
/*  72*/            ordering = j;
                }
    }


            public RankedComparator()
            {
/*  79*/        this(Order.ASCENDING);
            }

            public RankedComparator(Order order1)
            {
/*  83*/        order = order1;
            }

            public int compare(RankedProvider rankedprovider, RankedProvider rankedprovider1)
            {
/*  88*/        if(getPriority(rankedprovider) > getPriority(rankedprovider1))
/*  88*/            return order.ordering;
/*  88*/        else
/*  88*/            return -order.ordering;
            }

            protected int getPriority(RankedProvider rankedprovider)
            {
/*  92*/        return rankedprovider.getRank();
            }

            public volatile int compare(Object obj, Object obj1)
            {
/*  54*/        return compare((RankedProvider)obj, (RankedProvider)obj1);
            }

            private final Order order;
}
