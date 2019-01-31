// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.EntityTag;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext

class this._cls0
    implements Function
{

            public EntityTag apply(String s)
            {
/* 602*/        return EntityTag.valueOf(s);
            }

            public volatile Object apply(Object obj)
            {
/* 599*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 599*/        this$0 = InboundMessageContext.this;
/* 599*/        super();
            }
}
