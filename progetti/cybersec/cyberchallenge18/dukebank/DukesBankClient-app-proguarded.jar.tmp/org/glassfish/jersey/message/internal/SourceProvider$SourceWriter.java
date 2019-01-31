// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.glassfish.hk2.api.Factory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            SourceProvider

public static final class transformerFactory
    implements MessageBodyWriter
{

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 212*/        return javax/xml/transform/Source.isAssignableFrom(class1);
            }

            public final long getSize(Source source, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 217*/        return -1L;
            }

            public final void writeTo(Source source, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 225*/        try
                {
/* 225*/            if(source instanceof StreamSource)
                    {
/* 226*/                class1 = (StreamSource)source;
/* 227*/                (type = new InputSource(class1.getInputStream())).setCharacterStream(type.getCharacterStream());
/* 229*/                type.setPublicId(class1.getPublicId());
/* 230*/                type.setSystemId(source.getSystemId());
/* 231*/                source = new SAXSource(((SAXParserFactory)saxParserFactory.provide()).newSAXParser().getXMLReader(), type);
                    }
/* 234*/            class1 = new StreamResult(outputstream);
/* 235*/            ((TransformerFactory)transformerFactory.provide()).newTransformer().transform(source, class1);
/* 243*/            return;
                }
                // Misplaced declaration of an exception variable
/* 237*/        catch(Class class1)
                {
/* 238*/            throw new InternalServerErrorException(class1);
                }
                // Misplaced declaration of an exception variable
/* 239*/        catch(Class class1)
                {
/* 240*/            throw new InternalServerErrorException(class1);
                }
                // Misplaced declaration of an exception variable
/* 241*/        catch(Class class1)
                {
/* 242*/            throw new InternalServerErrorException(class1);
                }
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/* 196*/        writeTo((Source)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }

            public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 196*/        return getSize((Source)obj, class1, type, aannotation, mediatype);
            }

            private final Factory saxParserFactory;
            private final Factory transformerFactory;

            public (Factory factory, Factory factory1)
            {
/* 206*/        saxParserFactory = factory;
/* 207*/        transformerFactory = factory1;
            }
}
