// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.MediaType;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public MediaType apply(String s)
            {
/* 289*/        return MediaType.valueOf(s);
            }

            public volatile Object apply(Object obj)
            {
/* 286*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 286*/        this$0 = OutboundMessageContext.this;
/* 286*/        super();
            }
}
