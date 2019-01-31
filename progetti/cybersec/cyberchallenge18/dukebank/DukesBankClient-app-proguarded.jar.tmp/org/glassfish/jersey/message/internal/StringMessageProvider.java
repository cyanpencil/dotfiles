// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringMessageProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider

final class StringMessageProvider extends AbstractMessageReaderWriterProvider
{

            StringMessageProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  66*/        return class1 == java/lang/String;
            }

            public final String readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  77*/        return readFromAsString(inputstream, mediatype);
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  82*/        return class1 == java/lang/String;
            }

            public final long getSize(String s, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  87*/        return (long)s.length();
            }

            public final void writeTo(String s, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/*  99*/        writeToAsString(s, outputstream, mediatype);
            }

            public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  59*/        return getSize((String)obj, class1, type, aannotation, mediatype);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  59*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  59*/        writeTo((String)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
