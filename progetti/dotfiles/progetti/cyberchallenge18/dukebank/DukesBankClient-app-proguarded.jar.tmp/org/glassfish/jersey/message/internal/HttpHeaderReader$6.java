// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableToken, HttpHeaderReader

static class stElementCreator
    implements stElementCreator
{

            public final AcceptableToken create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 519*/        return new AcceptableToken(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 515*/        return create(httpheaderreader);
            }

            stElementCreator()
            {
            }
}
