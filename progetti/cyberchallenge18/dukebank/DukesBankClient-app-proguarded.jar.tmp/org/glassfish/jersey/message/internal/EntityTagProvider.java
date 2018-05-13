// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EntityTagProvider.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import javax.ws.rs.core.EntityTag;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, StringBuilderUtils, Utils

public class EntityTagProvider
    implements HeaderDelegateProvider
{

            public EntityTagProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  65*/        return class1 == javax/ws/rs/core/EntityTag;
            }

            public String toString(EntityTag entitytag)
            {
/*  71*/        Utils.throwIllegalArgumentExceptionIfNull(entitytag, LocalizationMessages.ENTITY_TAG_IS_NULL());
/*  73*/        StringBuilder stringbuilder = new StringBuilder();
/*  74*/        if(entitytag.isWeak())
/*  75*/            stringbuilder.append("W/");
/*  77*/        StringBuilderUtils.appendQuoted(stringbuilder, entitytag.getValue());
/*  78*/        return stringbuilder.toString();
            }

            public EntityTag fromString(String s)
            {
/*  84*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.ENTITY_TAG_IS_NULL());
                HttpHeaderReader httpheaderreader;
                Object obj;
/*  87*/        if((obj = (httpheaderreader = HttpHeaderReader.newInstance(s)).next(false)) == HttpHeaderReader.Event.QuotedString)
/*  90*/            return new EntityTag(httpheaderreader.getEventValue().toString());
/*  91*/        if(obj != HttpHeaderReader.Event.Token || (obj = httpheaderreader.getEventValue()) == null || ((CharSequence) (obj)).length() <= 0 || ((CharSequence) (obj)).charAt(0) != 'W')
/*  94*/            break MISSING_BLOCK_LABEL_137;
/*  94*/        httpheaderreader.nextSeparator('/');
/*  95*/        return new EntityTag(httpheaderreader.nextQuotedString().toString(), true);
                ParseException parseexception;
/*  98*/        parseexception;
/*  99*/        throw new IllegalArgumentException((new StringBuilder("Error parsing entity tag '")).append(s).append("'").toString(), parseexception);
/* 103*/        throw new IllegalArgumentException((new StringBuilder("Error parsing entity tag '")).append(s).append("'").toString());
            }

            public volatile String toString(Object obj)
            {
/*  60*/        return toString((EntityTag)obj);
            }

            public volatile Object fromString(String s)
            {
/*  60*/        return fromString(s);
            }
}
