// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.EntityTag;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            OutboundMessageContext

class this._cls0
    implements Function
{

            public EntityTag apply(String s)
            {
/* 475*/        if(s == null)
/* 475*/            return null;
/* 475*/        return EntityTag.valueOf(s);
/* 476*/        s;
/* 477*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 471*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 471*/        this$0 = OutboundMessageContext.this;
/* 471*/        super();
            }
}
