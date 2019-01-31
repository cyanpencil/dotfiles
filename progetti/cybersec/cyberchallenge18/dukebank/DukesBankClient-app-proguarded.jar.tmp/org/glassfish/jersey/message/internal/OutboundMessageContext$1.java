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
/* 253*/        return HttpHeaderReader.readDate(s);
/* 254*/        s;
/* 255*/        throw new ProcessingException(s);
            }

            public volatile Object apply(Object obj)
            {
/* 249*/        return apply((String)obj);
            }

            final OutboundMessageContext this$0;

            ()
            {
/* 249*/        this$0 = OutboundMessageContext.this;
/* 249*/        super();
            }
}
