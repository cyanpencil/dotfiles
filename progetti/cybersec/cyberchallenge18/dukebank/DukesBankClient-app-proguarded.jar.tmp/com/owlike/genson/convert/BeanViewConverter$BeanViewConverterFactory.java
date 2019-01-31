// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.HandleBeanView;
import com.owlike.genson.reflect.BeanViewDescriptorProvider;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory, BeanViewConverter

public static class provider extends ChainedFactory
{

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  38*/        if(!Wrapper.toAnnotatedElement(converter).isAnnotationPresent(com/owlike/genson/annotation/HandleBeanView))
/*  42*/            return new BeanViewConverter(type, provider, converter);
/*  43*/        else
/*  43*/            return converter;
            }

            private final BeanViewDescriptorProvider provider;

            public (BeanViewDescriptorProvider beanviewdescriptorprovider)
            {
/*  32*/        provider = beanviewdescriptorprovider;
            }
}
