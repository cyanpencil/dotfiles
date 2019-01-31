// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FormProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Encoded;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import org.glassfish.jersey.internal.util.collection.NullableMultivaluedHashMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractFormProvider

public final class FormProvider extends AbstractFormProvider
{

            public FormProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  73*/        return class1 == javax/ws/rs/core/Form;
            }

            public final Form readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  85*/        return new Form(readFrom(((MultivaluedMap) (new NullableMultivaluedHashMap())), mediatype, decode(aannotation), inputstream));
            }

            private boolean decode(Annotation aannotation[])
            {
/*  90*/        int i = (aannotation = aannotation).length;
/*  90*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation;
/*  90*/            if((annotation = aannotation[j]).annotationType().equals(javax/ws/rs/Encoded))
/*  92*/                return false;
                }

/*  95*/        return true;
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 100*/        return class1 == javax/ws/rs/core/Form;
            }

            public final void writeTo(Form form, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 112*/        writeTo(form.asMap(), mediatype, outputstream);
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  66*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  66*/        writeTo((Form)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }
}
