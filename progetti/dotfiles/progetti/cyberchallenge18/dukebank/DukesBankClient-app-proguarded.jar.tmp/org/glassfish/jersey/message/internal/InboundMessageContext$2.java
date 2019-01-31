// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Date;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, InboundMessageContext

class this._cls0
    implements Function
{

            public Date apply(String s)
            {
/* 361*/        return HttpHeaderReader.readDate(s);
/* 362*/        s;
/* 363*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 357*/        return apply((String)obj);
            }

            final InboundMessageContext this$0;

            ()
            {
/* 357*/        this$0 = InboundMessageContext.this;
/* 357*/        super();
            }
}
