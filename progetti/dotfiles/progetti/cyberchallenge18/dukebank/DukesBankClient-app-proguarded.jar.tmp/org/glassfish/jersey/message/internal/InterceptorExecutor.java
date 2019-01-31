// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.InterceptorContext;
import org.glassfish.jersey.internal.PropertiesDelegate;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

abstract class InterceptorExecutor
    implements InterceptorContext, PropertiesDelegate
{
    static class InterceptorTimestampPair
    {

                private Object getInterceptor()
                {
/*  85*/            return interceptor;
                }

                private long getTimestamp()
                {
/*  89*/            return timestamp;
                }

                private final Object interceptor;
                private final long timestamp;



                private InterceptorTimestampPair(Object obj, long l)
                {
/*  80*/            interceptor = obj;
/*  81*/            timestamp = l;
                }

                InterceptorTimestampPair(Object obj, long l, _cls1 _pcls1)
                {
/*  74*/            this(obj, l);
                }
    }


            public InterceptorExecutor(Class class1, Type type1, Annotation aannotation[], MediaType mediatype, PropertiesDelegate propertiesdelegate)
            {
/* 107*/        type = class1;
/* 108*/        genericType = type1;
/* 109*/        annotations = aannotation;
/* 110*/        mediaType = mediatype;
/* 111*/        propertiesDelegate = propertiesdelegate;
/* 112*/        tracingLogger = TracingLogger.getInstance(propertiesdelegate);
            }

            public Object getProperty(String s)
            {
/* 117*/        return propertiesDelegate.getProperty(s);
            }

            public Collection getPropertyNames()
            {
/* 122*/        return propertiesDelegate.getPropertyNames();
            }

            public void setProperty(String s, Object obj)
            {
/* 127*/        propertiesDelegate.setProperty(s, obj);
            }

            public void removeProperty(String s)
            {
/* 132*/        propertiesDelegate.removeProperty(s);
            }

            protected final TracingLogger getTracingLogger()
            {
/* 141*/        return tracingLogger;
            }

            protected final void traceBefore(Object obj, TracingLogger.Event event)
            {
/* 151*/        if(tracingLogger.isLogEnabled(event))
                {
/* 152*/            if(lastTracedInterceptor != null && obj != null)
/* 153*/                tracingLogger.logDuration(event, lastTracedInterceptor.getTimestamp(), new Object[] {
/* 153*/                    lastTracedInterceptor.getInterceptor()
                        });
/* 155*/            lastTracedInterceptor = new InterceptorTimestampPair(obj, System.nanoTime());
                }
            }

            protected final void traceAfter(Object obj, TracingLogger.Event event)
            {
/* 166*/        if(tracingLogger.isLogEnabled(event))
                {
/* 167*/            if(lastTracedInterceptor != null && lastTracedInterceptor.getInterceptor() != null)
/* 168*/                tracingLogger.logDuration(event, lastTracedInterceptor.getTimestamp(), new Object[] {
/* 168*/                    obj
                        });
/* 170*/            lastTracedInterceptor = new InterceptorTimestampPair(obj, System.nanoTime());
                }
            }

            protected final void clearLastTracedInterceptor()
            {
/* 178*/        lastTracedInterceptor = null;
            }

            public Annotation[] getAnnotations()
            {
/* 183*/        return annotations;
            }

            public void setAnnotations(Annotation aannotation[])
            {
/* 188*/        if(aannotation == null)
                {
/* 189*/            throw new NullPointerException("Annotations must not be null.");
                } else
                {
/* 191*/            annotations = aannotation;
/* 192*/            return;
                }
            }

            public Class getType()
            {
/* 196*/        return type;
            }

            public void setType(Class class1)
            {
/* 201*/        type = class1;
            }

            public Type getGenericType()
            {
/* 206*/        return genericType;
            }

            public void setGenericType(Type type1)
            {
/* 211*/        genericType = type1;
            }

            public MediaType getMediaType()
            {
/* 216*/        return mediaType;
            }

            public void setMediaType(MediaType mediatype)
            {
/* 221*/        mediaType = mediatype;
            }

            private final PropertiesDelegate propertiesDelegate;
            private Annotation annotations[];
            private Class type;
            private Type genericType;
            private MediaType mediaType;
            private final TracingLogger tracingLogger;
            private InterceptorTimestampPair lastTracedInterceptor;
}
