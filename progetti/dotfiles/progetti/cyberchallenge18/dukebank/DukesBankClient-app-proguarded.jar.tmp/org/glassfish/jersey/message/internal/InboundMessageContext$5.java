// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext

class this._cls0
    implements Function
{

            public MediaType apply(String s)
            {
/* 450*/        return MediaType.valueOf(s);
/* 451*/        s;
/* 452*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 446*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 446*/        this$0 = InboundMessageContext.this;
/* 446*/        super();
            }
}
