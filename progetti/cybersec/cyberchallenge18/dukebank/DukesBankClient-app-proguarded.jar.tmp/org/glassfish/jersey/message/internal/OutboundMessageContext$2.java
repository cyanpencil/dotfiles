// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Locale;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            LanguageTag, OutboundMessageContext

class this._cls0
    implements Function
{

            public Locale apply(String s)
            {
/* 271*/        return (new LanguageTag(s)).getAsLocale();
/* 272*/        s;
/* 273*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 267*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 267*/        this$0 = OutboundMessageContext.this;
/* 267*/        super();
            }
}
