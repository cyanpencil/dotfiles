// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Provider;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.glassfish.hk2.api.Factory;
import org.xml.sax.*;

public final class SourceProvider
{
    public static final class SourceWriter
        implements MessageBodyWriter
    {

                public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/* 212*/            return javax/xml/transform/Source.isAssignableFrom(class1);
                }

                public final long getSize(Source source, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/* 217*/            return -1L;
                }

                public final void writeTo(Source source, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                    throws IOException
                {
/* 225*/            try
                    {
/* 225*/                if(source instanceof StreamSource)
                        {
/* 226*/                    class1 = (StreamSource)source;
/* 227*/                    (type = new InputSource(class1.getInputStream())).setCharacterStream(type.getCharacterStream());
/* 229*/                    type.setPublicId(class1.getPublicId());
/* 230*/                    type.setSystemId(source.getSystemId());
/* 231*/                    source = new SAXSource(((SAXParserFactory)saxParserFactory.provide()).newSAXParser().getXMLReader(), type);
                        }
/* 234*/                class1 = new StreamResult(outputstream);
/* 235*/                ((TransformerFactory)transformerFactory.provide()).newTransformer().transform(source, class1);
/* 243*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 237*/            catch(Class class1)
                    {
/* 238*/                throw new InternalServerErrorException(class1);
                    }
                    // Misplaced declaration of an exception variable
/* 239*/            catch(Class class1)
                    {
/* 240*/                throw new InternalServerErrorException(class1);
                    }
                    // Misplaced declaration of an exception variable
/* 241*/            catch(Class class1)
                    {
/* 242*/                throw new InternalServerErrorException(class1);
                    }
                }

                public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                    throws IOException, WebApplicationException
                {
/* 196*/            writeTo((Source)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
                }

                public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/* 196*/            return getSize((Source)obj, class1, type, aannotation, mediatype);
                }

                private final Factory saxParserFactory;
                private final Factory transformerFactory;

                public SourceWriter(Factory factory, Factory factory1)
                {
/* 206*/            saxParserFactory = factory;
/* 207*/            transformerFactory = factory1;
                }
    }

    public static final class DomSourceReader
        implements MessageBodyReader
    {

                public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/* 169*/            return javax/xml/transform/dom/DOMSource == class1;
                }

                public final DOMSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException
                {
/* 181*/            class1 = ((DocumentBuilderFactory)dbf.provide()).newDocumentBuilder().parse(inputstream);
/* 182*/            return new DOMSource(class1);
/* 183*/            class1;
/* 184*/            throw new BadRequestException(class1);
/* 185*/            class1;
/* 186*/            throw new InternalServerErrorException(class1);
/* 187*/            class1;
/* 188*/            throw new InternalServerErrorException(class1);
                }

                public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException, WebApplicationException
                {
/* 156*/            return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
                }

                private final Factory dbf;

                public DomSourceReader(Factory factory)
                {
/* 164*/            dbf = factory;
                }
    }

    public static final class SaxSourceReader
        implements MessageBodyReader
    {

                public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/* 129*/            return javax/xml/transform/sax/SAXSource == class1;
                }

                public final SAXSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException
                {
/* 141*/            return new SAXSource(((SAXParserFactory)spf.get()).newSAXParser().getXMLReader(), new InputSource(inputstream));
/* 143*/            class1;
/* 144*/            throw new BadRequestException(class1);
/* 145*/            class1;
/* 146*/            throw new InternalServerErrorException(class1);
/* 147*/            class1;
/* 148*/            throw new InternalServerErrorException(class1);
                }

                public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException, WebApplicationException
                {
/* 115*/            return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
                }

                private final Provider spf;

                public SaxSourceReader(Provider provider)
                {
/* 124*/            spf = provider;
                }
    }

    public static final class StreamSourceReader
        implements MessageBodyReader
    {

                public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
                {
/*  97*/            return javax/xml/transform/stream/StreamSource == class1 || javax/xml/transform/Source == class1;
                }

                public final StreamSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException
                {
/* 108*/            return new StreamSource(inputstream);
                }

                public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                    throws IOException, WebApplicationException
                {
/*  90*/            return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
                }

                public StreamSourceReader()
                {
                }
    }


            public SourceProvider()
            {
            }
}
