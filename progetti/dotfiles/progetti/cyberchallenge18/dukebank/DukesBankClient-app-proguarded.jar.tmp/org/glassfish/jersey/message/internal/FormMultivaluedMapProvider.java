// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FormMultivaluedMapProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.internal.util.collection.NullableMultivaluedHashMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractFormProvider

public final class FormMultivaluedMapProvider extends AbstractFormProvider
{

            public FormMultivaluedMapProvider()
            {
/*  73*/        ParameterizedType parameterizedtype = (ParameterizedType)getClass().getGenericSuperclass();
/*  74*/        mapType = parameterizedtype.getActualTypeArguments()[0];
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  80*/        return class1 == javax/ws/rs/core/MultivaluedMap && (class1 == type || mapType.equals(type));
            }

            public final MultivaluedMap readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  92*/        return readFrom(((MultivaluedMap) (new NullableMultivaluedHashMap())), mediatype, true, inputstream);
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  97*/        return javax/ws/rs/core/MultivaluedMap.isAssignableFrom(class1);
            }

            public final void writeTo(MultivaluedMap multivaluedmap, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap1, OutputStream outputstream)
                throws IOException
            {
/* 109*/        writeTo(multivaluedmap, mediatype, outputstream);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  65*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  65*/        writeTo((MultivaluedMap)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }

            private final Type mapType;
}
