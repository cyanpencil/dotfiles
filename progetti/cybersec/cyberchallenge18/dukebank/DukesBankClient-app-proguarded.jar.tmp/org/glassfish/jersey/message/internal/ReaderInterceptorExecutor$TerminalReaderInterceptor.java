// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.ext.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CompletableReader, EntityInputStream, MessageBodyProviderNotFoundException, MsgTraceEvent, 
//            ReaderInterceptorExecutor, TracingLogger

class this._cls0
    implements ReaderInterceptor
{

            public Object aroundReadFrom(ReaderInterceptorContext readerinterceptorcontext)
                throws IOException, WebApplicationException
            {
/* 203*/        ReaderInterceptorExecutor.access$110(ReaderInterceptorExecutor.this);
/* 205*/        traceBefore(null, MsgTraceEvent.RI_BEFORE);
                Object obj;
                EntityInputStream entityinputstream;
/* 207*/        if(((TracingLogger) (obj = getTracingLogger())).isLogEnabled(MsgTraceEvent.MBR_FIND))
/* 209*/            ((TracingLogger) (obj)).log(MsgTraceEvent.MBR_FIND, new Object[] {
/* 209*/                readerinterceptorcontext.getType().getName(), (readerinterceptorcontext.getGenericType() instanceof Class) ? ((Class)readerinterceptorcontext.getGenericType()).getName() : readerinterceptorcontext.getGenericType(), String.valueOf(readerinterceptorcontext.getMediaType()), Arrays.toString(readerinterceptorcontext.getAnnotations())
                    });
/* 216*/        obj = ReaderInterceptorExecutor.access$200(ReaderInterceptorExecutor.this).getMessageBodyReader(readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType(), readerinterceptorcontext.getAnnotations(), readerinterceptorcontext.getMediaType(), ReaderInterceptorExecutor.this);
/* 223*/        entityinputstream = new EntityInputStream(readerinterceptorcontext.getInputStream());
/* 225*/        if(obj != null)
/* 226*/            break MISSING_BLOCK_LABEL_281;
/* 226*/        if(entityinputstream.isEmpty() && !readerinterceptorcontext.getHeaders().containsKey("Content-Type"))
                {
/* 242*/            clearLastTracedInterceptor();
/* 243*/            traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/            return null;
                }
/* 229*/        ReaderInterceptorExecutor.access$300().log(Level.FINE, LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType()));
/* 231*/        throw new MessageBodyProviderNotFoundException(LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType()));
/* 235*/        readerinterceptorcontext = ((ReaderInterceptorContext) (invokeReadFrom(readerinterceptorcontext, ((MessageBodyReader) (obj)), entityinputstream)));
/* 237*/        if(obj instanceof CompletableReader)
/* 238*/            readerinterceptorcontext = ((ReaderInterceptorContext) (((CompletableReader)obj).complete(readerinterceptorcontext)));
/* 240*/        readerinterceptorcontext = readerinterceptorcontext;
/* 242*/        clearLastTracedInterceptor();
/* 243*/        traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/        return readerinterceptorcontext;
/* 242*/        readerinterceptorcontext;
/* 242*/        clearLastTracedInterceptor();
/* 243*/        traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/        throw readerinterceptorcontext;
            }

            private Object invokeReadFrom(ReaderInterceptorContext readerinterceptorcontext, MessageBodyReader messagebodyreader, EntityInputStream entityinputstream)
                throws WebApplicationException, IOException
            {
                TracingLogger tracinglogger;
/* 251*/        long l = (tracinglogger = getTracingLogger()).timestamp(MsgTraceEvent.MBR_READ_FROM);
/* 253*/        entityinputstream = new it>(entityinputstream, messagebodyreader);
/* 256*/        try
                {
/* 256*/            readerinterceptorcontext = ((ReaderInterceptorContext) (messagebodyreader.readFrom(readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType(), readerinterceptorcontext.getAnnotations(), readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getHeaders(), entityinputstream)));
                }
                // Misplaced declaration of an exception variable
/* 258*/        catch(ReaderInterceptorContext readerinterceptorcontext)
                {
/* 259*/            if(ReaderInterceptorExecutor.access$500(ReaderInterceptorExecutor.this))
/* 260*/                throw new BadRequestException(readerinterceptorcontext);
/* 262*/            else
/* 262*/                throw readerinterceptorcontext;
                }
/* 265*/        finally
                {
/* 265*/            tracinglogger.logDuration(MsgTraceEvent.MBR_READ_FROM, l, new Object[] {
/* 265*/                messagebodyreader
                    });
                }
/* 265*/        tracinglogger.logDuration(MsgTraceEvent.MBR_READ_FROM, l, new Object[] {
/* 265*/            messagebodyreader
                });
/* 265*/        return readerinterceptorcontext;
/* 265*/        throw readerinterceptorcontext;
            }

            final ReaderInterceptorExecutor this$0;

            private ()
            {
/* 198*/        this$0 = ReaderInterceptorExecutor.this;
/* 198*/        super();
            }

}
