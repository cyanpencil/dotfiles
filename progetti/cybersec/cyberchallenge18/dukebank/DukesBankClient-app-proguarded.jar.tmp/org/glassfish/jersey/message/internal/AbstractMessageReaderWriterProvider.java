// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMessageReaderWriterProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            ReaderWriter

public abstract class AbstractMessageReaderWriterProvider
    implements MessageBodyReader, MessageBodyWriter
{

            public AbstractMessageReaderWriterProvider()
            {
            }

            public static void writeTo(InputStream inputstream, OutputStream outputstream)
                throws IOException
            {
/*  79*/        ReaderWriter.writeTo(inputstream, outputstream);
            }

            public static void writeTo(Reader reader, Writer writer)
                throws IOException
            {
/*  90*/        ReaderWriter.writeTo(reader, writer);
            }

            public static Charset getCharset(MediaType mediatype)
            {
/* 103*/        return ReaderWriter.getCharset(mediatype);
            }

            public static String readFromAsString(InputStream inputstream, MediaType mediatype)
                throws IOException
            {
/* 117*/        return ReaderWriter.readFromAsString(inputstream, mediatype);
            }

            public static void writeToAsString(String s, OutputStream outputstream, MediaType mediatype)
                throws IOException
            {
/* 130*/        ReaderWriter.writeToAsString(s, outputstream, mediatype);
            }

            public long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 136*/        return -1L;
            }

            public static final Charset UTF8;

            static 
            {
/*  69*/        UTF8 = ReaderWriter.UTF8;
            }
}
