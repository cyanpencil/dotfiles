// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FileProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, ReaderWriter, Utils

public final class FileProvider extends AbstractMessageReaderWriterProvider
{

            public FileProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  77*/        return java/io/File == class1;
            }

            public final File readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  87*/        class1 = Utils.createTempFile();
/*  88*/        type = new BufferedOutputStream(new FileOutputStream(class1));
/*  91*/        writeTo(inputstream, type);
/*  93*/        type.close();
/*  94*/        break MISSING_BLOCK_LABEL_40;
/*  93*/        class1;
/*  93*/        type.close();
/*  93*/        throw class1;
/*  96*/        return class1;
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 104*/        return java/io/File.isAssignableFrom(class1);
            }

            public final void writeTo(File file, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 115*/        file = new BufferedInputStream(new FileInputStream(file), ReaderWriter.BUFFER_SIZE);
/* 118*/        writeTo(((InputStream) (file)), outputstream);
/* 120*/        file.close();
/* 121*/        return;
/* 120*/        class1;
/* 120*/        file.close();
/* 120*/        throw class1;
            }

            public final long getSize(File file, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 130*/        return file.length();
            }

            public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  67*/        return getSize((File)obj, class1, type, aannotation, mediatype);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  67*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  67*/        writeTo((File)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
