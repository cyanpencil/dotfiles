// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, QualitySourceMediaType

static class ype
    implements stElementCreator
{

            public final QualitySourceMediaType create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 439*/        return QualitySourceMediaType.valueOf(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 435*/        return create(httpheaderreader);
            }

            ype()
            {
            }
}
