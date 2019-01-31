// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StreamingOutputProvider.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.MessageBodyWriter;

public final class StreamingOutputProvider
    implements MessageBodyWriter
{

            public StreamingOutputProvider()
            {
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  66*/        return javax/ws/rs/core/StreamingOutput.isAssignableFrom(class1);
            }

            public final long getSize(StreamingOutput streamingoutput, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  71*/        return -1L;
            }

            public final void writeTo(StreamingOutput streamingoutput, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/*  78*/        streamingoutput.write(outputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  60*/        writeTo((StreamingOutput)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }

            public final volatile long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  60*/        return getSize((StreamingOutput)obj, class1, type, aannotation, mediatype);
            }
}
