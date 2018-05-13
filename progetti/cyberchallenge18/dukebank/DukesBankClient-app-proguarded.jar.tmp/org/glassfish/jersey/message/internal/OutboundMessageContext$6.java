// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.util.Locale;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public Object apply(Locale locale)
            {
/* 379*/        return locale;
            }

            public volatile Object apply(Object obj)
            {
/* 376*/        return apply((Locale)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 376*/        this$0 = OutboundMessageContext.this;
/* 376*/        super();
            }
}
