// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, MatchingEntityTag

static class stElementCreator
    implements stElementCreator
{

            public final MatchingEntityTag create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 370*/        return MatchingEntityTag.valueOf(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 366*/        return create(httpheaderreader);
            }

            stElementCreator()
            {
            }
}
