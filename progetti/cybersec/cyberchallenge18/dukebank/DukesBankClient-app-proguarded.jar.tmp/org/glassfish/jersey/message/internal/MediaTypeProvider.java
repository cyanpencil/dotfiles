// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypeProvider.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.*;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, StringBuilderUtils, Utils

public class MediaTypeProvider
    implements HeaderDelegateProvider
{

            public MediaTypeProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  67*/        return javax/ws/rs/core/MediaType.isAssignableFrom(class1);
            }

            public String toString(MediaType mediatype)
            {
/*  73*/        Utils.throwIllegalArgumentExceptionIfNull(mediatype, MEDIA_TYPE_IS_NULL);
                StringBuilder stringbuilder;
/*  75*/        (stringbuilder = new StringBuilder()).append(mediatype.getType()).append('/').append(mediatype.getSubtype());
                java.util.Map.Entry entry;
/*  77*/        for(mediatype = mediatype.getParameters().entrySet().iterator(); mediatype.hasNext(); StringBuilderUtils.appendQuotedIfNonToken(stringbuilder, (String)entry.getValue()))
                {
/*  77*/            entry = (java.util.Map.Entry)mediatype.next();
/*  78*/            stringbuilder.append(";").append((String)entry.getKey()).append('=');
                }

/*  81*/        return stringbuilder.toString();
            }

            public MediaType fromString(String s)
            {
/*  87*/        Utils.throwIllegalArgumentExceptionIfNull(s, MEDIA_TYPE_IS_NULL);
/*  90*/        return valueOf(HttpHeaderReader.newInstance(s));
                ParseException parseexception;
/*  91*/        parseexception;
/*  92*/        throw new IllegalArgumentException((new StringBuilder("Error parsing media type '")).append(s).append("'").toString(), parseexception);
            }

            public static MediaType valueOf(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 107*/        httpheaderreader.hasNext();
/* 110*/        String s = httpheaderreader.nextToken().toString();
/* 111*/        httpheaderreader.nextSeparator('/');
/* 113*/        String s1 = httpheaderreader.nextToken().toString();
/* 115*/        Map map = null;
/* 117*/        if(httpheaderreader.hasNext())
/* 118*/            map = HttpHeaderReader.readParameters(httpheaderreader);
/* 121*/        return new MediaType(s, s1, map);
            }

            public volatile String toString(Object obj)
            {
/*  60*/        return toString((MediaType)obj);
            }

            public volatile Object fromString(String s)
            {
/*  60*/        return fromString(s);
            }

            private static final String MEDIA_TYPE_IS_NULL = LocalizationMessages.MEDIA_TYPE_IS_NULL();

}
