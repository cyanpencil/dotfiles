// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InterceptorExecutor, MsgTraceEvent, TracingLogger, CompletableReader, 
//            EntityInputStream, MessageBodyProviderNotFoundException

public final class ReaderInterceptorExecutor extends InterceptorExecutor
    implements ReaderInterceptorContext, ServiceLocatorSupplier
{
    static class UnCloseableInputStream extends InputStream
    {

                public int read()
                    throws IOException
                {
/* 286*/            return original.read();
                }

                public int read(byte abyte0[])
                    throws IOException
                {
/* 291*/            return original.read(abyte0);
                }

                public int read(byte abyte0[], int i, int j)
                    throws IOException
                {
/* 296*/            return original.read(abyte0, i, j);
                }

                public long skip(long l)
                    throws IOException
                {
/* 301*/            return original.skip(l);
                }

                public int available()
                    throws IOException
                {
/* 306*/            return original.available();
                }

                public synchronized void mark(int i)
                {
/* 311*/            original.mark(i);
                }

                public synchronized void reset()
                    throws IOException
                {
/* 316*/            original.reset();
                }

                public boolean markSupported()
                {
/* 321*/            return original.markSupported();
                }

                public void close()
                    throws IOException
                {
/* 326*/            if(ReaderInterceptorExecutor.LOGGER.isLoggable(Level.FINE))
/* 327*/                ReaderInterceptorExecutor.LOGGER.log(Level.FINE, LocalizationMessages.MBR_TRYING_TO_CLOSE_STREAM(reader.getClass()));
                }

                private InputStream unwrap()
                {
/* 332*/            return original;
                }

                private final InputStream original;
                private final MessageBodyReader reader;


                private UnCloseableInputStream(InputStream inputstream, MessageBodyReader messagebodyreader)
                {
/* 280*/            original = inputstream;
/* 281*/            reader = messagebodyreader;
                }

    }

    class TerminalReaderInterceptor
        implements ReaderInterceptor
    {

                public Object aroundReadFrom(ReaderInterceptorContext readerinterceptorcontext)
                    throws IOException, WebApplicationException
                {
/* 203*/            processedCount--;
/* 205*/            traceBefore(null, MsgTraceEvent.RI_BEFORE);
                    Object obj;
                    EntityInputStream entityinputstream;
/* 207*/            if(((TracingLogger) (obj = getTracingLogger())).isLogEnabled(MsgTraceEvent.MBR_FIND))
/* 209*/                ((TracingLogger) (obj)).log(MsgTraceEvent.MBR_FIND, new Object[] {
/* 209*/                    readerinterceptorcontext.getType().getName(), (readerinterceptorcontext.getGenericType() instanceof Class) ? ((Class)readerinterceptorcontext.getGenericType()).getName() : readerinterceptorcontext.getGenericType(), String.valueOf(readerinterceptorcontext.getMediaType()), Arrays.toString(readerinterceptorcontext.getAnnotations())
                        });
/* 216*/            obj = workers.getMessageBodyReader(readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType(), readerinterceptorcontext.getAnnotations(), readerinterceptorcontext.getMediaType(), ReaderInterceptorExecutor.this);
/* 223*/            entityinputstream = new EntityInputStream(readerinterceptorcontext.getInputStream());
/* 225*/            if(obj != null)
/* 226*/                break MISSING_BLOCK_LABEL_281;
/* 226*/            if(entityinputstream.isEmpty() && !readerinterceptorcontext.getHeaders().containsKey("Content-Type"))
                    {
/* 242*/                clearLastTracedInterceptor();
/* 243*/                traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/                return null;
                    }
/* 229*/            ReaderInterceptorExecutor.LOGGER.log(Level.FINE, LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType()));
/* 231*/            throw new MessageBodyProviderNotFoundException(LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType()));
/* 235*/            readerinterceptorcontext = ((ReaderInterceptorContext) (invokeReadFrom(readerinterceptorcontext, ((MessageBodyReader) (obj)), entityinputstream)));
/* 237*/            if(obj instanceof CompletableReader)
/* 238*/                readerinterceptorcontext = ((ReaderInterceptorContext) (((CompletableReader)obj).complete(readerinterceptorcontext)));
/* 240*/            readerinterceptorcontext = readerinterceptorcontext;
/* 242*/            clearLastTracedInterceptor();
/* 243*/            traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/            return readerinterceptorcontext;
/* 242*/            readerinterceptorcontext;
/* 242*/            clearLastTracedInterceptor();
/* 243*/            traceAfter(null, MsgTraceEvent.RI_AFTER);
/* 243*/            throw readerinterceptorcontext;
                }

                private Object invokeReadFrom(ReaderInterceptorContext readerinterceptorcontext, MessageBodyReader messagebodyreader, EntityInputStream entityinputstream)
                    throws WebApplicationException, IOException
                {
                    TracingLogger tracinglogger;
/* 251*/            long l = (tracinglogger = getTracingLogger()).timestamp(MsgTraceEvent.MBR_READ_FROM);
/* 253*/            entityinputstream = new UnCloseableInputStream(entityinputstream, messagebodyreader);
/* 256*/            try
                    {
/* 256*/                readerinterceptorcontext = ((ReaderInterceptorContext) (messagebodyreader.readFrom(readerinterceptorcontext.getType(), readerinterceptorcontext.getGenericType(), readerinterceptorcontext.getAnnotations(), readerinterceptorcontext.getMediaType(), readerinterceptorcontext.getHeaders(), entityinputstream)));
                    }
                    // Misplaced declaration of an exception variable
/* 258*/            catch(ReaderInterceptorContext readerinterceptorcontext)
                    {
/* 259*/                if(translateNce)
/* 260*/                    throw new BadRequestException(readerinterceptorcontext);
/* 262*/                else
/* 262*/                    throw readerinterceptorcontext;
                    }
/* 265*/            finally
                    {
/* 265*/                tracinglogger.logDuration(MsgTraceEvent.MBR_READ_FROM, l, new Object[] {
/* 265*/                    messagebodyreader
                        });
                    }
/* 265*/            tracinglogger.logDuration(MsgTraceEvent.MBR_READ_FROM, l, new Object[] {
/* 265*/                messagebodyreader
                    });
/* 265*/            return readerinterceptorcontext;
/* 265*/            throw readerinterceptorcontext;
                }

                final ReaderInterceptorExecutor this$0;

                private TerminalReaderInterceptor()
                {
/* 198*/            this$0 = ReaderInterceptorExecutor.this;
/* 198*/            super();
                }

    }


            public ReaderInterceptorExecutor(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, InputStream inputstream, 
                    MessageBodyWorkers messagebodyworkers, Iterable iterable, boolean flag, ServiceLocator servicelocator)
            {
/* 127*/        super(class1, type, aannotation, mediatype, propertiesdelegate);
/* 128*/        headers = multivaluedmap;
/* 129*/        inputStream = inputstream;
/* 130*/        workers = messagebodyworkers;
/* 131*/        translateNce = flag;
/* 132*/        serviceLocator = servicelocator;
/* 134*/        (class1 = Lists.newArrayList(iterable)).add(new TerminalReaderInterceptor());
/* 137*/        interceptors = class1.iterator();
/* 138*/        processedCount = 0;
            }

            public final Object proceed()
                throws IOException
            {
                ReaderInterceptor readerinterceptor;
/* 149*/        if(!interceptors.hasNext())
/* 150*/            throw new ProcessingException(LocalizationMessages.ERROR_INTERCEPTOR_READER_PROCEED());
/* 152*/        readerinterceptor = (ReaderInterceptor)interceptors.next();
/* 153*/        traceBefore(readerinterceptor, MsgTraceEvent.RI_BEFORE);
/* 155*/        Object obj = readerinterceptor.aroundReadFrom(this);
/* 157*/        processedCount++;
/* 158*/        traceAfter(readerinterceptor, MsgTraceEvent.RI_AFTER);
/* 158*/        return obj;
                Exception exception;
/* 157*/        exception;
/* 157*/        processedCount++;
/* 158*/        traceAfter(readerinterceptor, MsgTraceEvent.RI_AFTER);
/* 158*/        throw exception;
            }

            public final InputStream getInputStream()
            {
/* 164*/        return inputStream;
            }

            public final void setInputStream(InputStream inputstream)
            {
/* 169*/        inputStream = inputstream;
            }

            public final MultivaluedMap getHeaders()
            {
/* 175*/        return headers;
            }

            final int getProcessedCount()
            {
/* 184*/        return processedCount;
            }

            public final ServiceLocator getServiceLocator()
            {
/* 189*/        return serviceLocator;
            }

            public static InputStream closeableInputStream(InputStream inputstream)
            {
/* 345*/        if(inputstream instanceof UnCloseableInputStream)
/* 346*/            return ((UnCloseableInputStream)inputstream).unwrap();
/* 348*/        else
/* 348*/            return inputstream;
            }

            public final volatile void setMediaType(MediaType mediatype)
            {
/*  80*/        super.setMediaType(mediatype);
            }

            public final volatile MediaType getMediaType()
            {
/*  80*/        return super.getMediaType();
            }

            public final volatile void setGenericType(Type type)
            {
/*  80*/        super.setGenericType(type);
            }

            public final volatile Type getGenericType()
            {
/*  80*/        return super.getGenericType();
            }

            public final volatile void setType(Class class1)
            {
/*  80*/        super.setType(class1);
            }

            public final volatile Class getType()
            {
/*  80*/        return super.getType();
            }

            public final volatile void setAnnotations(Annotation aannotation[])
            {
/*  80*/        super.setAnnotations(aannotation);
            }

            public final volatile Annotation[] getAnnotations()
            {
/*  80*/        return super.getAnnotations();
            }

            public final volatile void removeProperty(String s)
            {
/*  80*/        super.removeProperty(s);
            }

            public final volatile void setProperty(String s, Object obj)
            {
/*  80*/        super.setProperty(s, obj);
            }

            public final volatile Collection getPropertyNames()
            {
/*  80*/        return super.getPropertyNames();
            }

            public final volatile Object getProperty(String s)
            {
/*  80*/        return super.getProperty(s);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/ReaderInterceptorExecutor.getName());
            private final MultivaluedMap headers;
            private final Iterator interceptors;
            private final MessageBodyWorkers workers;
            private final boolean translateNce;
            private final ServiceLocator serviceLocator;
            private InputStream inputStream;
            private int processedCount;





}
