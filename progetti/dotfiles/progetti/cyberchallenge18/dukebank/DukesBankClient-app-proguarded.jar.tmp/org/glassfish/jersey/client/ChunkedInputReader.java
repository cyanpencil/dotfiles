// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInputReader.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkedInput

class ChunkedInputReader
    implements MessageBodyReader
{

            ChunkedInputReader()
            {
            }

            public boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  77*/        return class1.equals(org/glassfish/jersey/client/ChunkedInput);
            }

            public ChunkedInput readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  88*/        class1 = ReflectionHelper.getTypeArgument(type, 0);
/*  90*/        return new ChunkedInput(class1, inputstream, aannotation, mediatype, multivaluedmap, (MessageBodyWorkers)messageBodyWorkers.get(), (PropertiesDelegate)propertiesDelegateProvider.get());
            }

            public volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  67*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            private Provider messageBodyWorkers;
            private Provider propertiesDelegateProvider;
}
