// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Quality.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Quality

static class 
    implements Comparator
{

            public final int compare(Integer integer, Integer integer1)
            {
/*  79*/        return Quality.access$000(integer1.intValue(), integer.intValue());
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  74*/        return compare((Integer)obj, (Integer)obj1);
            }

            ()
            {
            }
}
