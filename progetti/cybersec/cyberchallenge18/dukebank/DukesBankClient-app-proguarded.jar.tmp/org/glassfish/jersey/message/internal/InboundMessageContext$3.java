// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Locale;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext, LanguageTag

class this._cls0
    implements Function
{

            public Locale apply(String s)
            {
/* 413*/        return (new LanguageTag(s)).getAsLocale();
/* 414*/        s;
/* 415*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 409*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 409*/        this$0 = InboundMessageContext.this;
/* 409*/        super();
            }
}
