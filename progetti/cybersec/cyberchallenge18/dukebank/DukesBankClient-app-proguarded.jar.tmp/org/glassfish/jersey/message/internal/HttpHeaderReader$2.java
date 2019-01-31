// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, MediaTypeProvider

static class stElementCreator
    implements stElementCreator
{

            public final MediaType create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 401*/        return MediaTypeProvider.valueOf(httpheaderreader);
            }

            public final volatile Object create(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 397*/        return create(httpheaderreader);
            }

            stElementCreator()
            {
            }
}
