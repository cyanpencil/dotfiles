// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, HttpHeaderReader

static class 
    implements stElementCreator
{

            public final AcceptableMediaType create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 420*/        return AcceptableMediaType.valueOf(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 416*/        return create(httpheaderreader);
            }

            ()
            {
            }
}
