// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyProviderNotFoundException, MsgTraceEvent, TracingLogger, WriterInterceptorExecutor

class workers
    implements WriterInterceptor
{

            public void aroundWriteTo(WriterInterceptorContext writerinterceptorcontext)
                throws WebApplicationException, IOException
            {
/* 228*/        WriterInterceptorExecutor.access$010(WriterInterceptorExecutor.this);
/* 230*/        traceBefore(null, MsgTraceEvent.WI_BEFORE);
                Object obj;
/* 232*/        if(((TracingLogger) (obj = getTracingLogger())).isLogEnabled(MsgTraceEvent.MBW_FIND))
/* 234*/            ((TracingLogger) (obj)).log(MsgTraceEvent.MBW_FIND, new Object[] {
/* 234*/                writerinterceptorcontext.getType().getName(), (writerinterceptorcontext.getGenericType() instanceof Class) ? ((Class)writerinterceptorcontext.getGenericType()).getName() : writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getMediaType(), Arrays.toString(writerinterceptorcontext.getAnnotations())
                    });
/* 241*/        if((obj = workers.getMessageBodyWriter(writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getAnnotations(), writerinterceptorcontext.getMediaType(), WriterInterceptorExecutor.this)) == null)
                {
/* 245*/            WriterInterceptorExecutor.access$100().log(Level.SEVERE, LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYWRITER(writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType()));
/* 247*/            throw new MessageBodyProviderNotFoundException(LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYWRITER(writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType()));
                }
/* 250*/        invokeWriteTo(writerinterceptorcontext, ((MessageBodyWriter) (obj)));
/* 252*/        clearLastTracedInterceptor();
/* 253*/        traceAfter(null, MsgTraceEvent.WI_AFTER);
/* 254*/        return;
/* 252*/        writerinterceptorcontext;
/* 252*/        clearLastTracedInterceptor();
/* 253*/        traceAfter(null, MsgTraceEvent.WI_AFTER);
/* 253*/        throw writerinterceptorcontext;
            }

            private void invokeWriteTo(WriterInterceptorContext writerinterceptorcontext, MessageBodyWriter messagebodywriter)
                throws WebApplicationException, IOException
            {
                TracingLogger tracinglogger;
                long l;
                 ;
/* 260*/        l = (tracinglogger = getTracingLogger()).timestamp(MsgTraceEvent.MBW_WRITE_TO);
/* 262*/         = new nit>(writerinterceptorcontext.getOutputStream(), messagebodywriter);
/* 265*/        messagebodywriter.writeTo(writerinterceptorcontext.getEntity(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getAnnotations(), writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getHeaders(), );
/* 268*/        tracinglogger.logDuration(MsgTraceEvent.MBW_WRITE_TO, l, new Object[] {
/* 268*/            messagebodywriter
                });
/* 269*/        return;
/* 268*/        writerinterceptorcontext;
/* 268*/        tracinglogger.logDuration(MsgTraceEvent.MBW_WRITE_TO, l, new Object[] {
/* 268*/            messagebodywriter
                });
/* 268*/        throw writerinterceptorcontext;
            }

            private final MessageBodyWorkers workers;
            final WriterInterceptorExecutor this$0;

            public (MessageBodyWorkers messagebodyworkers)
            {
/* 220*/        this$0 = WriterInterceptorExecutor.this;
/* 221*/        super();
/* 222*/        workers = messagebodyworkers;
            }
}
