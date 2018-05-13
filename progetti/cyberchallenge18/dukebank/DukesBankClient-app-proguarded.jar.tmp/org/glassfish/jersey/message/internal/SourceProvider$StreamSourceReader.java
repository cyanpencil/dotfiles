// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            SourceProvider

public static final class 
    implements MessageBodyReader
{

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  97*/        return javax/xml/transform/stream/StreamSource == class1 || javax/xml/transform/Source == class1;
            }

            public final StreamSource readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/* 108*/        return new StreamSource(inputstream);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  90*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public ()
            {
            }
}
