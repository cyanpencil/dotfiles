// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ext.MessageBodyWriter;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.message.WriterModel;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

static class 
    implements Function
{

            public final MessageBodyWriter apply(WriterModel writermodel)
            {
/* 918*/        return (MessageBodyWriter)writermodel.provider();
            }

            public final volatile Object apply(Object obj)
            {
/* 915*/        return apply((WriterModel)obj);
            }

            ()
            {
            }
}
