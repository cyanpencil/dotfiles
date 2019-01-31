// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, HttpHeaderReader, MediaTypes, QualitySourceMediaType

static class val.priorityMediaTypes
    implements Comparator
{

            public final int compare(AcceptableMediaType acceptablemediatype, AcceptableMediaType acceptablemediatype1)
            {
/* 485*/        boolean flag = false;
/* 486*/        int i = 0;
/* 487*/        boolean flag1 = false;
/* 488*/        int j = 0;
/* 489*/        Iterator iterator = val$priorityMediaTypes.iterator();
/* 489*/        do
                {
/* 489*/            if(!iterator.hasNext())
/* 489*/                break;
/* 489*/            QualitySourceMediaType qualitysourcemediatype = (QualitySourceMediaType)iterator.next();
/* 490*/            if(!flag && MediaTypes.typeEqual(acceptablemediatype, qualitysourcemediatype))
                    {
/* 491*/                i = acceptablemediatype.getQuality() * qualitysourcemediatype.getQuality();
/* 492*/                flag = true;
                    } else
/* 493*/            if(!flag1 && MediaTypes.typeEqual(acceptablemediatype1, qualitysourcemediatype))
                    {
/* 494*/                j = acceptablemediatype1.getQuality() * qualitysourcemediatype.getQuality();
/* 495*/                flag1 = true;
                    }
                } while(true);
                int k;
/* 498*/        if((k = j - i) != 0)
/* 500*/            return k;
/* 503*/        if((k = acceptablemediatype1.getQuality() - acceptablemediatype.getQuality()) != 0)
/* 505*/            return k;
/* 508*/        else
/* 508*/            return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 480*/        return compare((AcceptableMediaType)obj, (AcceptableMediaType)obj1);
            }

            final List val$priorityMediaTypes;

            (List list)
            {
/* 480*/        val$priorityMediaTypes = list;
/* 480*/        super();
            }
}
