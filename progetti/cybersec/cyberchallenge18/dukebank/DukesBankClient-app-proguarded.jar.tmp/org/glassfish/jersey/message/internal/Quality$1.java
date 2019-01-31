// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Quality.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Qualified, Quality

static class 
    implements Comparator
{

            public final int compare(Qualified qualified, Qualified qualified1)
            {
/*  65*/        return Quality.access$000(qualified1.getQuality(), qualified.getQuality());
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  60*/        return compare((Qualified)obj, (Qualified)obj1);
            }

            ()
            {
            }
}
