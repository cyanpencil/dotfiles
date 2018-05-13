// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.*;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InterceptorExecutor, MsgTraceEvent, TracingLogger, MessageBodyProviderNotFoundException

public final class WriterInterceptorExecutor extends InterceptorExecutor
    implements WriterInterceptorContext, ServiceLocatorSupplier
{
    static class UnCloseableOutputStream extends OutputStream
    {

                public void write(int i)
                    throws IOException
                {
/* 289*/            original.write(i);
                }

                public void write(byte abyte0[])
                    throws IOException
                {
/* 294*/            original.write(abyte0);
                }

                public void write(byte abyte0[], int i, int j)
                    throws IOException
                {
/* 299*/            original.write(abyte0, i, j);
                }

                public void flush()
                    throws IOException
                {
/* 304*/            original.flush();
                }

                public void close()
                    throws IOException
                {
/* 309*/            if(WriterInterceptorExecutor.LOGGER.isLoggable(Level.FINE))
/* 310*/                WriterInterceptorExecutor.LOGGER.log(Level.FINE, LocalizationMessages.MBW_TRYING_TO_CLOSE_STREAM(writer.getClass()));
                }

                private final OutputStream original;
                private final MessageBodyWriter writer;

                private UnCloseableOutputStream(OutputStream outputstream, MessageBodyWriter messagebodywriter)
                {
/* 283*/            original = outputstream;
/* 284*/            writer = messagebodywriter;
                }

    }

    class TerminalWriterInterceptor
        implements WriterInterceptor
    {

                public void aroundWriteTo(WriterInterceptorContext writerinterceptorcontext)
                    throws WebApplicationException, IOException
                {
/* 228*/            processedCount--;
/* 230*/            traceBefore(null, MsgTraceEvent.WI_BEFORE);
                    Object obj;
/* 232*/            if(((TracingLogger) (obj = getTracingLogger())).isLogEnabled(MsgTraceEvent.MBW_FIND))
/* 234*/                ((TracingLogger) (obj)).log(MsgTraceEvent.MBW_FIND, new Object[] {
/* 234*/                    writerinterceptorcontext.getType().getName(), (writerinterceptorcontext.getGenericType() instanceof Class) ? ((Class)writerinterceptorcontext.getGenericType()).getName() : writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getMediaType(), Arrays.toString(writerinterceptorcontext.getAnnotations())
                        });
/* 241*/            if((obj = workers.getMessageBodyWriter(writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getAnnotations(), writerinterceptorcontext.getMediaType(), WriterInterceptorExecutor.this)) == null)
                    {
/* 245*/                WriterInterceptorExecutor.LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYWRITER(writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType()));
/* 247*/                throw new MessageBodyProviderNotFoundException(LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYWRITER(writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType()));
                    }
/* 250*/            invokeWriteTo(writerinterceptorcontext, ((MessageBodyWriter) (obj)));
/* 252*/            clearLastTracedInterceptor();
/* 253*/            traceAfter(null, MsgTraceEvent.WI_AFTER);
/* 254*/            return;
/* 252*/            writerinterceptorcontext;
/* 252*/            clearLastTracedInterceptor();
/* 253*/            traceAfter(null, MsgTraceEvent.WI_AFTER);
/* 253*/            throw writerinterceptorcontext;
                }

                private void invokeWriteTo(WriterInterceptorContext writerinterceptorcontext, MessageBodyWriter messagebodywriter)
                    throws WebApplicationException, IOException
                {
                    TracingLogger tracinglogger;
                    long l;
                    UnCloseableOutputStream uncloseableoutputstream;
/* 260*/            l = (tracinglogger = getTracingLogger()).timestamp(MsgTraceEvent.MBW_WRITE_TO);
/* 262*/            uncloseableoutputstream = new UnCloseableOutputStream(writerinterceptorcontext.getOutputStream(), messagebodywriter);
/* 265*/            messagebodywriter.writeTo(writerinterceptorcontext.getEntity(), writerinterceptorcontext.getType(), writerinterceptorcontext.getGenericType(), writerinterceptorcontext.getAnnotations(), writerinterceptorcontext.getMediaType(), writerinterceptorcontext.getHeaders(), uncloseableoutputstream);
/* 268*/            tracinglogger.logDuration(MsgTraceEvent.MBW_WRITE_TO, l, new Object[] {
/* 268*/                messagebodywriter
                    });
/* 269*/            return;
/* 268*/            writerinterceptorcontext;
/* 268*/            tracinglogger.logDuration(MsgTraceEvent.MBW_WRITE_TO, l, new Object[] {
/* 268*/                messagebodywriter
                    });
/* 268*/            throw writerinterceptorcontext;
                }

                private final MessageBodyWorkers workers;
                final WriterInterceptorExecutor this$0;

                public TerminalWriterInterceptor(MessageBodyWorkers messagebodyworkers)
                {
/* 220*/            this$0 = WriterInterceptorExecutor.this;
/* 221*/            super();
/* 222*/            workers = messagebodyworkers;
                }
    }


            public WriterInterceptorExecutor(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, 
                    OutputStream outputstream, MessageBodyWorkers messagebodyworkers, Iterable iterable, ServiceLocator servicelocator)
            {
/* 125*/        super(class1, type, aannotation, mediatype, propertiesdelegate);
/* 126*/        entity = obj;
/* 127*/        headers = multivaluedmap;
/* 128*/        outputStream = outputstream;
/* 129*/        serviceLocator = servicelocator;
/* 131*/        ((List) (obj = Lists.newArrayList(iterable))).add(new TerminalWriterInterceptor(messagebodyworkers));
/* 134*/        iterator = ((List) (obj)).iterator();
/* 135*/        processedCount = 0;
            }

            public final WriterInterceptor getNextInterceptor()
            {
/* 144*/        if(!iterator.hasNext())
/* 145*/            return null;
/* 147*/        else
/* 147*/            return (WriterInterceptor)iterator.next();
            }

            public final void proceed()
                throws IOException
            {
                WriterInterceptor writerinterceptor;
/* 156*/        if((writerinterceptor = getNextInterceptor()) == null)
/* 158*/            throw new ProcessingException(LocalizationMessages.ERROR_INTERCEPTOR_WRITER_PROCEED());
/* 160*/        traceBefore(writerinterceptor, MsgTraceEvent.WI_BEFORE);
/* 162*/        writerinterceptor.aroundWriteTo(this);
/* 164*/        processedCount++;
/* 165*/        traceAfter(writerinterceptor, MsgTraceEvent.WI_AFTER);
/* 166*/        return;
                Exception exception;
/* 164*/        exception;
/* 164*/        processedCount++;
/* 165*/        traceAfter(writerinterceptor, MsgTraceEvent.WI_AFTER);
/* 165*/        throw exception;
            }

            public final Object getEntity()
            {
/* 171*/        return entity;
            }

            public final void setEntity(Object obj)
            {
/* 176*/        entity = obj;
            }

            public final OutputStream getOutputStream()
            {
/* 181*/        return outputStream;
            }

            public final void setOutputStream(OutputStream outputstream)
            {
/* 186*/        outputStream = outputstream;
            }

            public final MultivaluedMap getHeaders()
            {
/* 192*/        return headers;
            }

            final int getProcessedCount()
            {
/* 201*/        return processedCount;
            }

            public final ServiceLocator getServiceLocator()
            {
/* 206*/        return serviceLocator;
            }

            public final volatile void setMediaType(MediaType mediatype)
            {
/*  78*/        super.setMediaType(mediatype);
            }

            public final volatile MediaType getMediaType()
            {
/*  78*/        return super.getMediaType();
            }

            public final volatile void setGenericType(Type type)
            {
/*  78*/        super.setGenericType(type);
            }

            public final volatile Type getGenericType()
            {
/*  78*/        return super.getGenericType();
            }

            public final volatile void setType(Class class1)
            {
/*  78*/        super.setType(class1);
            }

            public final volatile Class getType()
            {
/*  78*/        return super.getType();
            }

            public final volatile void setAnnotations(Annotation aannotation[])
            {
/*  78*/        super.setAnnotations(aannotation);
            }

            public final volatile Annotation[] getAnnotations()
            {
/*  78*/        return super.getAnnotations();
            }

            public final volatile void removeProperty(String s)
            {
/*  78*/        super.removeProperty(s);
            }

            public final volatile void setProperty(String s, Object obj)
            {
/*  78*/        super.setProperty(s, obj);
            }

            public final volatile Collection getPropertyNames()
            {
/*  78*/        return super.getPropertyNames();
            }

            public final volatile Object getProperty(String s)
            {
/*  78*/        return super.getProperty(s);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/WriterInterceptorExecutor.getName());
            private OutputStream outputStream;
            private final MultivaluedMap headers;
            private Object entity;
            private final Iterator iterator;
            private int processedCount;
            private final ServiceLocator serviceLocator;



}
