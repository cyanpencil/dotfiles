// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MatchingEntityTag.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Collections;
import java.util.Set;
import javax.ws.rs.core.EntityTag;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

public class MatchingEntityTag extends EntityTag
{

            public MatchingEntityTag(String s)
            {
/*  74*/        super(s, false);
            }

            public MatchingEntityTag(String s, boolean flag)
            {
/*  85*/        super(s, flag);
            }

            public static MatchingEntityTag valueOf(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  96*/        CharSequence charsequence = httpheaderreader.getRemainder();
                Object obj;
/*  98*/        if((obj = httpheaderreader.next(false)) == HttpHeaderReader.Event.QuotedString)
/* 100*/            return new MatchingEntityTag(httpheaderreader.getEventValue().toString());
/* 101*/        if(obj == HttpHeaderReader.Event.Token && (obj = httpheaderreader.getEventValue()) != null && ((CharSequence) (obj)).length() == 1 && 'W' == ((CharSequence) (obj)).charAt(0))
                {
/* 104*/            httpheaderreader.nextSeparator('/');
/* 105*/            return new MatchingEntityTag(httpheaderreader.nextQuotedString().toString(), true);
                } else
                {
/* 109*/            throw new ParseException(LocalizationMessages.ERROR_PARSING_ENTITY_TAG(charsequence), httpheaderreader.getIndex());
                }
            }

            public static final Set ANY_MATCH = Collections.emptySet();

}
