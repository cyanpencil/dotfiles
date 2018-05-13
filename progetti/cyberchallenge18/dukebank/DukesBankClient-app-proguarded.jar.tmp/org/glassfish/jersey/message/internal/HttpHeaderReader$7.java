// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableLanguageTag, HttpHeaderReader

static class ag
    implements stElementCreator
{

            public final AcceptableLanguageTag create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 535*/        return new AcceptableLanguageTag(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 531*/        return create(httpheaderreader);
            }

            ag()
            {
            }
}
