// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.message.MessageUtils;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, EntityInputStream

public final class ReaderProvider extends AbstractMessageReaderWriterProvider
{

            public ReaderProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  74*/        return java/io/Reader == class1;
            }

            public final Reader readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  86*/        if((class1 = EntityInputStream.create(inputstream)).isEmpty())
/*  88*/            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(new byte[0]), MessageUtils.getCharset(mediatype)));
/*  92*/        else
/*  92*/            return new BufferedReader(new InputStreamReader(class1, getCharset(mediatype)));
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  98*/        return java/io/Reader.isAssignableFrom(class1);
            }

            public final void writeTo(Reader reader, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 111*/        class1 = new OutputStreamWriter(outputstream, getCharset(mediatype));
/* 113*/        writeTo(reader, ((java.io.Writer) (class1)));
/* 114*/        class1.flush();
/* 116*/        reader.close();
/* 117*/        return;
/* 116*/        class1;
/* 116*/        reader.close();
/* 116*/        throw class1;
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  66*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  66*/        writeTo((Reader)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
