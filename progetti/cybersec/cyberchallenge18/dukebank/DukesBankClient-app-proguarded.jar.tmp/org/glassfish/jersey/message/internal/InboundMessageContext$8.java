// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.net.URI;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext

class this._cls0
    implements Function
{

            public URI apply(String s)
            {
/* 635*/        return URI.create(s);
/* 636*/        s;
/* 637*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 631*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 631*/        this$0 = InboundMessageContext.this;
/* 631*/        super();
            }
}
