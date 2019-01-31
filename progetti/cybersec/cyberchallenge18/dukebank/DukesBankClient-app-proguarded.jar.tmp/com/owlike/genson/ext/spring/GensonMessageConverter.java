// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonMessageConverter.java

package com.owlike.genson.ext.spring;

import com.owlike.genson.*;
import com.owlike.genson.annotation.WithBeanView;
import com.owlike.genson.stream.ObjectWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.*;

public class GensonMessageConverter extends AbstractHttpMessageConverter
{

            public GensonMessageConverter()
            {
/*  27*/        this((new GensonBuilder()).setHtmlSafe(true).setSkipNull(true).useBeanViews(true).create());
            }

            public GensonMessageConverter(Genson genson1)
            {
/*  31*/        super(new MediaType("application", "json", DEFAULT_CHARSET));
/*  32*/        genson = genson1;
            }

            protected Object readInternal(Class class1, HttpInputMessage httpinputmessage)
                throws IOException, HttpMessageNotReadableException
            {
/*  38*/        MethodParameter methodparameter = (MethodParameter)ThreadLocalHolder.get("__GENSON$method_param", org/springframework/core/MethodParameter);
/*  40*/        WithBeanView withbeanview = null;
/*  41*/        class1 = class1;
/*  42*/        if(methodparameter != null)
                {
/*  43*/            withbeanview = (WithBeanView)methodparameter.getMethodAnnotation(com/owlike/genson/annotation/WithBeanView);
/*  44*/            class1 = methodparameter.getGenericParameterType();
                }
/*  47*/        class1 = GenericType.of(class1);
/*  49*/        if(withbeanview != null)
/*  50*/            return genson.deserialize(class1, genson.createReader(httpinputmessage.getBody()), new Context(genson, Arrays.asList(withbeanview.views())));
/*  54*/        else
/*  54*/            return genson.deserialize(class1, genson.createReader(httpinputmessage.getBody()), new Context(genson));
            }

            protected boolean supports(Class class1)
            {
/*  59*/        return true;
            }

            protected void writeInternal(Object obj, HttpOutputMessage httpoutputmessage)
                throws IOException, HttpMessageNotWritableException
            {
/*  66*/        httpoutputmessage = genson.createWriter(httpoutputmessage.getBody());
                Object obj1;
/*  67*/        if((obj1 = (obj1 = (MethodParameter)ThreadLocalHolder.get("__GENSON$return_param", org/springframework/core/MethodParameter)) == null ? null : ((Object) ((WithBeanView)((MethodParameter) (obj1)).getMethodAnnotation(com/owlike/genson/annotation/WithBeanView)))) != null)
/*  70*/            genson.serialize(obj, httpoutputmessage, new Context(genson, Arrays.asList(((WithBeanView) (obj1)).views())));
/*  72*/        else
/*  72*/            genson.serialize(obj, httpoutputmessage, new Context(genson));
/*  73*/        httpoutputmessage.flush();
            }

            private final Genson genson;
            public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

}
