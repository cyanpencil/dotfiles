// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataSourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.activation.DataSource;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, ReaderWriter

public class DataSourceProvider extends AbstractMessageReaderWriterProvider
{
    public static class ByteArrayDataSource
        implements DataSource
    {
        static class DSByteArrayOutputStream extends ByteArrayOutputStream
        {

                    public byte[] getBuf()
                    {
/*  91*/                return buf;
                    }

                    public int getCount()
                    {
/*  95*/                return count;
                    }

                    DSByteArrayOutputStream()
                    {
                    }
        }


                public InputStream getInputStream()
                    throws IOException
                {
/* 121*/            if(data == null)
/* 122*/                throw new IOException("no data");
/* 124*/            if(len < 0)
/* 125*/                len = data.length;
/* 127*/            return new ByteArrayInputStream(data, 0, len);
                }

                public OutputStream getOutputStream()
                    throws IOException
                {
/* 132*/            throw new IOException("cannot do this");
                }

                public String getContentType()
                {
/* 137*/            return type;
                }

                public String getName()
                {
/* 142*/            return name;
                }

                public void setName(String s)
                {
/* 146*/            name = s;
                }

                private final String type;
                private byte data[];
                private int len;
                private String name;

                public ByteArrayDataSource(InputStream inputstream, String s)
                    throws IOException
                {
/*  85*/            len = -1;
/*  86*/            name = "";
/* 100*/            DSByteArrayOutputStream dsbytearrayoutputstream = new DSByteArrayOutputStream();
/* 101*/            ReaderWriter.writeTo(inputstream, dsbytearrayoutputstream);
/* 102*/            data = dsbytearrayoutputstream.getBuf();
/* 103*/            len = dsbytearrayoutputstream.getCount();
/* 112*/            if(data.length - len > 0x40000)
                    {
/* 113*/                data = dsbytearrayoutputstream.toByteArray();
/* 114*/                len = data.length;
                    }
/* 116*/            type = s;
                }
    }


            public DataSourceProvider()
            {
            }

            public boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 155*/        return javax/activation/DataSource == class1;
            }

            public DataSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/* 166*/        return new ByteArrayDataSource(inputstream, mediatype != null ? mediatype.toString() : null);
            }

            public boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 174*/        return javax/activation/DataSource.isAssignableFrom(class1);
            }

            public void writeTo(DataSource datasource, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 186*/        datasource = datasource.getInputStream();
/* 188*/        writeTo(((InputStream) (datasource)), outputstream);
/* 190*/        datasource.close();
/* 191*/        return;
/* 190*/        class1;
/* 190*/        datasource.close();
/* 190*/        throw class1;
            }

            public volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  64*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  64*/        writeTo((DataSource)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
