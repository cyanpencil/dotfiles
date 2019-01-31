// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import org.glassfish.hk2.api.Factory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            SourceProvider

public static final class dbf
    implements MessageBodyReader
{

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 169*/        return javax/xml/transform/dom/DOMSource == class1;
            }

            public final DOMSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/* 181*/        class1 = ((DocumentBuilderFactory)dbf.provide()).newDocumentBuilder().parse(inputstream);
/* 182*/        return new DOMSource(class1);
/* 183*/        class1;
/* 184*/        throw new BadRequestException(class1);
/* 185*/        class1;
/* 186*/        throw new InternalServerErrorException(class1);
/* 187*/        class1;
/* 188*/        throw new InternalServerErrorException(class1);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/* 156*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            private final Factory dbf;

            public (Factory factory)
            {
/* 164*/        dbf = factory;
            }
}
