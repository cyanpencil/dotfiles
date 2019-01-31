// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext

class this._cls0
    implements Function
{

            public Integer apply(String s)
            {
/* 431*/        return Integer.valueOf(s == null || s.isEmpty() ? -1 : Integer.parseInt(s));
/* 432*/        s;
/* 433*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 427*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 427*/        this$0 = InboundMessageContext.this;
/* 427*/        super();
            }
}
