// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QualitySourceMediaType.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes, Quality, QualitySourceMediaType

static class 
    implements Comparator
{

            public final int compare(QualitySourceMediaType qualitysourcemediatype, QualitySourceMediaType qualitysourcemediatype1)
            {
                int i;
/*  63*/        if((i = Quality.QUALIFIED_COMPARATOR.compare(qualitysourcemediatype, qualitysourcemediatype1)) != 0)
/*  65*/            return i;
/*  67*/        else
/*  67*/            return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(qualitysourcemediatype, qualitysourcemediatype1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  59*/        return compare((QualitySourceMediaType)obj, (QualitySourceMediaType)obj1);
            }

            ()
            {
            }
}
