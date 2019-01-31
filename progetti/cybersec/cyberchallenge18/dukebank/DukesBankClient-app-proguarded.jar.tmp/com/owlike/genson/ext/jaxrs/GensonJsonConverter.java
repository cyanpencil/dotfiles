// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonConverter.java

package com.owlike.genson.ext.jaxrs;

import com.owlike.genson.*;
import com.owlike.genson.annotation.WithBeanView;
import com.owlike.genson.stream.JsonStreamException;
import com.owlike.genson.stream.ObjectWriter;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.*;

// Referenced classes of package com.owlike.genson.ext.jaxrs:
//            GensonJaxRSFeature

public class GensonJsonConverter
    implements MessageBodyReader, MessageBodyWriter
{

            public GensonJsonConverter()
            {
/*  34*/        this(((ContextResolver) (new GensonJaxRSFeature())));
            }

            public GensonJsonConverter(Providers providers)
            {
                Object obj;
/*  38*/        if((obj = providers.getContextResolver(com/owlike/genson/ext/jaxrs/GensonJaxRSFeature, null)) == null && (providers = providers.getContextResolver(com/owlike/genson/Genson, null)) != null)
/*  43*/            obj = (new GensonJaxRSFeature()).use((Genson)providers.getContext(java/lang/Object));
/*  47*/        if(obj == null)
                {
/*  48*/            _gensonResolver = new GensonJaxRSFeature();
/*  48*/            return;
                } else
                {
/*  50*/            _gensonResolver = ((ContextResolver) (obj));
/*  51*/            return;
                }
            }

            public GensonJsonConverter(ContextResolver contextresolver)
            {
/*  54*/        _gensonResolver = contextresolver;
            }

            private Genson getInstance(Class class1)
            {
                Genson genson;
/*  58*/        if((genson = ((GensonJaxRSFeature)_gensonResolver.getContext(class1)).genson()) == null)
/*  60*/            throw new NullPointerException((new StringBuilder("Could not resolve a Genson instance for type ")).append(class1).append(" using ContextResolver ").append(_gensonResolver.getClass()).toString());
/*  62*/        else
/*  62*/            return genson;
            }

            public boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  68*/        return (type = (GensonJaxRSFeature)_gensonResolver.getContext(class1)).isEnabled() && type.isSerializable(class1);
            }

            public void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  75*/        multivaluedmap = getInstance(class1);
/*  76*/        if((mediatype = (String)mediatype.getParameters().get("charset")) == null)
/*  77*/            mediatype = "UTF-8";
/*  78*/        if(!mediatype.equalsIgnoreCase("UTF-8") && !mediatype.equalsIgnoreCase("UTF-16BE") && !mediatype.equalsIgnoreCase("UTF-16LE") && !mediatype.equalsIgnoreCase("UTF-32BE") && !mediatype.equalsIgnoreCase("UTF-32LE"))
/*  81*/            throw new UnsupportedEncodingException("JSON spec allows only UTF-8/16/32 encodings.");
/*  83*/        mediatype = multivaluedmap.createWriter(new OutputStreamWriter(outputstream, mediatype));
/*  85*/        try
                {
/*  85*/            multivaluedmap.serialize(obj, rawIfNullGenericType(class1, type), mediatype, createContext(aannotation, multivaluedmap));
/*  86*/            mediatype.flush();
/*  91*/            return;
                }
                // Misplaced declaration of an exception variable
/*  87*/        catch(Object obj)
                {
/*  88*/            throw new WebApplicationException(((Throwable) (obj)));
                }
                // Misplaced declaration of an exception variable
/*  89*/        catch(Object obj)
                {
/*  90*/            throw new WebApplicationException(((Throwable) (obj)));
                }
            }

            private Context createContext(Annotation aannotation[], Genson genson)
            {
/*  95*/        if((aannotation = (WithBeanView)find(com/owlike/genson/annotation/WithBeanView, aannotation)) != null)
/*  98*/            aannotation = new Context(genson, Arrays.asList(aannotation.views()));
/* 100*/        else
/* 100*/            aannotation = new Context(genson);
/* 101*/        return aannotation;
            }

            private Annotation find(Class class1, Annotation aannotation[])
            {
/* 105*/        if(aannotation != null)
                {
/* 106*/            int i = (aannotation = aannotation).length;
/* 106*/            for(int j = 0; j < i; j++)
                    {
/* 106*/                Annotation annotation = aannotation[j];
/* 107*/                if(class1.isInstance(annotation))
/* 108*/                    return (Annotation)class1.cast(annotation);
                    }

                }
/* 110*/        return null;
            }

            public boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 115*/        return (type = (GensonJaxRSFeature)_gensonResolver.getContext(class1)).isEnabled() && type.isDeserializable(class1);
            }

            public Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/* 123*/        multivaluedmap = (mediatype = getInstance(class1)).createReader(inputstream);
/* 125*/        return mediatype.deserialize(GenericType.of(rawIfNullGenericType(class1, type)), multivaluedmap, createContext(aannotation, mediatype));
/* 126*/        mediatype;
/* 127*/        throw new WebApplicationException(mediatype);
/* 128*/        mediatype;
/* 129*/        throw new WebApplicationException(mediatype);
            }

            public long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 135*/        return -1L;
            }

            private Type rawIfNullGenericType(Class class1, Type type)
            {
/* 139*/        if(type != null)
/* 139*/            return type;
/* 139*/        else
/* 139*/            return class1;
            }

            private final ContextResolver _gensonResolver;
}
