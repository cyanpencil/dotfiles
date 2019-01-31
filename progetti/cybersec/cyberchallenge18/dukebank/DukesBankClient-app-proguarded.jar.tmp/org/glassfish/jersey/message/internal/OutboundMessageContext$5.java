// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.util.Locale;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableLanguageTag, OutboundMessageContext

class this._cls0
    implements Function
{

            public Locale apply(AcceptableLanguageTag acceptablelanguagetag)
            {
/* 365*/        return acceptablelanguagetag.getAsLocale();
            }

            public volatile Object apply(Object obj)
            {
/* 361*/        return apply((AcceptableLanguageTag)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 361*/        this$0 = OutboundMessageContext.this;
/* 361*/        super();
            }
}
