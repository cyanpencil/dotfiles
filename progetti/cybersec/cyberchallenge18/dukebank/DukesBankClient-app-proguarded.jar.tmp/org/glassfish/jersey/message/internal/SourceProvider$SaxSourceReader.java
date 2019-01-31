// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Provider;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.parsers.*;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.*;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            SourceProvider

public static final class spf
    implements MessageBodyReader
{

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 129*/        return javax/xml/transform/sax/SAXSource == class1;
            }

            public final SAXSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/* 141*/        return new SAXSource(((SAXParserFactory)spf.get()).newSAXParser().getXMLReader(), new InputSource(inputstream));
/* 143*/        class1;
/* 144*/        throw new BadRequestException(class1);
/* 145*/        class1;
/* 146*/        throw new InternalServerErrorException(class1);
/* 147*/        class1;
/* 148*/        throw new InternalServerErrorException(class1);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/* 115*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            private final Provider spf;

            public (Provider provider)
            {
/* 124*/        spf = provider;
            }
}
