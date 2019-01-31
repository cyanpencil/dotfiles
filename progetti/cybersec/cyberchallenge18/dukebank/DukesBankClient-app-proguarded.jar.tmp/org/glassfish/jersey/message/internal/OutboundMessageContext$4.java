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

            public Object apply(MediaType mediatype)
            {
/* 330*/        return mediatype;
            }

            public volatile Object apply(Object obj)
            {
/* 327*/        return apply((MediaType)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 327*/        this$0 = OutboundMessageContext.this;
/* 327*/        super();
            }
}
