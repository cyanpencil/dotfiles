// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ext.MessageBodyReader;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.message.ReaderModel;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

static class 
    implements Function
{

            public final MessageBodyReader apply(ReaderModel readermodel)
            {
/* 998*/        return (MessageBodyReader)readermodel.provider();
            }

            public final volatile Object apply(Object obj)
            {
/* 995*/        return apply((ReaderModel)obj);
            }

            ()
            {
            }
}
