// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Link;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public Object apply(Link link)
            {
/* 552*/        return link;
            }

            public volatile Object apply(Object obj)
            {
/* 549*/        return apply((Link)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 549*/        this$0 = OutboundMessageContext.this;
/* 549*/        super();
            }
}
