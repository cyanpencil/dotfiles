// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AcceptableMediaType.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, MediaTypes, Quality

static class A
    implements Comparator
{

            public final int compare(AcceptableMediaType acceptablemediatype, AcceptableMediaType acceptablemediatype1)
            {
                int i;
/*  62*/        if((i = Quality.QUALIFIED_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1)) != 0)
/*  64*/            return i;
/*  67*/        else
/*  67*/            return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  58*/        return compare((AcceptableMediaType)obj, (AcceptableMediaType)obj1);
            }

            A()
            {
            }
}
