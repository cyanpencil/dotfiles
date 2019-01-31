// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InputStreamProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, ReaderInterceptorExecutor

public final class InputStreamProvider extends AbstractMessageReaderWriterProvider
{

            public InputStreamProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  67*/        return java/io/InputStream == class1;
            }

            public final InputStream readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  78*/        return ReaderInterceptorExecutor.closeableInputStream(inputstream);
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  83*/        return java/io/InputStream.isAssignableFrom(class1);
            }

            public final long getSize(InputStream inputstream, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  88*/        if(inputstream instanceof ByteArrayInputStream)
/*  89*/            return (long)((ByteArrayInputStream)inputstream).available();
/*  91*/        else
/*  91*/            return -1L;
            }

            public final void writeTo(InputStream inputstream, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 105*/        writeTo(inputstream, outputstream);
/* 107*/        inputstream.close();
/* 108*/        return;
/* 107*/        class1;
/* 107*/        inputstream.close();
/* 107*/        throw class1;
            }

            public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  60*/        return getSize((InputStream)obj, class1, type, aannotation, mediatype);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  60*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  60*/        writeTo((InputStream)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
