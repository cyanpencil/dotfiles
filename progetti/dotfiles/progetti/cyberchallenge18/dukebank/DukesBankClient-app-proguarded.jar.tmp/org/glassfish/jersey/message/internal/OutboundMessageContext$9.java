// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OutboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Date;
import javax.ws.rs.ProcessingException;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, OutboundMessageContext

class this._cls0
    implements Function
{

            public Date apply(String s)
            {
/* 493*/        return HttpHeaderReader.readDate(s);
/* 494*/        s;
/* 495*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 489*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 489*/        this$0 = OutboundMessageContext.this;
/* 489*/        super();
            }
}
