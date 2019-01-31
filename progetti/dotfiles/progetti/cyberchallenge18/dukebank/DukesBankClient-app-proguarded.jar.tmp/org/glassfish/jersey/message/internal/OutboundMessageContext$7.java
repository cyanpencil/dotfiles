// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public Integer apply(String s)
            {
/* 436*/        return Integer.valueOf(s == null || s.isEmpty() ? -1 : Integer.parseInt(s));
/* 437*/        s;
/* 438*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 432*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 432*/        this$0 = OutboundMessageContext.this;
/* 432*/        super();
            }
}
