// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.net.URI;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public URI apply(String s)
            {
/* 511*/        if(s == null)
/* 511*/            return null;
/* 511*/        return URI.create(s);
/* 512*/        s;
/* 513*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 507*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 507*/        this$0 = OutboundMessageContext.this;
/* 507*/        super();
            }
}
