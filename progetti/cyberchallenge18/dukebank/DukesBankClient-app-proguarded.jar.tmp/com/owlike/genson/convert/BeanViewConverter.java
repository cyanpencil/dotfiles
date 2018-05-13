// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.HandleBeanView;
import com.owlike.genson.reflect.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory

public class BeanViewConverter extends Wrapper
    implements Converter
{
    public static class BeanViewConverterFactory extends ChainedFactory
    {

                protected Converter create(Type type1, Genson genson, Converter converter)
                {
/*  38*/            if(!Wrapper.toAnnotatedElement(converter).isAnnotationPresent(com/owlike/genson/annotation/HandleBeanView))
/*  42*/                return new BeanViewConverter(type1, provider, converter);
/*  43*/            else
/*  43*/                return converter;
                }

                private final BeanViewDescriptorProvider provider;

                public BeanViewConverterFactory(BeanViewDescriptorProvider beanviewdescriptorprovider)
                {
/*  32*/            provider = beanviewdescriptorprovider;
                }
    }


            public BeanViewConverter(Type type1, BeanViewDescriptorProvider beanviewdescriptorprovider, Converter converter)
            {
/*  51*/        super(converter);
/*  52*/        provider = beanviewdescriptorprovider;
/*  53*/        type = type1;
            }

            protected Class findViewFor(Type type1, List list)
            {
/*  59*/        for(list = list.iterator(); list.hasNext();)
                {
/*  59*/            Class class1 = (Class)list.next();
                    Type type2;
/*  60*/            type2 = TypeUtil.expandType(type2 = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, class1), class1);
/*  62*/            type2 = TypeUtil.typeOf(0, type2);
/*  63*/            if(TypeUtil.match(type1, type2, false))
/*  64*/                return class1;
                }

/*  67*/        return null;
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  71*/        Object obj1 = 0;
                Object obj2;
/*  72*/        if((obj2 = context.views()) != null && ((List) (obj2)).size() > 0 && (obj2 = findViewFor(type, ((List) (obj2)))) != null)
                {
/*  76*/            obj1 = TypeUtil.getRawClass(((Type) (obj1 = TypeUtil.expandType(com/owlike/genson/BeanView.getTypeParameters()[0], ((Type) (obj2))))));
/*  80*/            ((BeanDescriptor) (obj1 = provider.provide(((Class) (obj1)), ((Type) (obj2)), context.genson))).serialize(obj, objectwriter, context);
/*  83*/            obj1 = 1;
                }
/*  86*/        if(obj1 == 0)
/*  86*/            ((Converter)wrapped).serialize(obj, objectwriter, context);
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
                Object obj;
/*  90*/        if(context.hasViews() && (obj = findViewFor(type, context.views())) != null)
                {
                    Object obj1;
/*  93*/            obj1 = TypeUtil.getRawClass(((Type) (obj1 = TypeUtil.expandType(com/owlike/genson/BeanView.getTypeParameters()[0], ((Type) (obj))))));
/*  97*/            return ((BeanDescriptor) (obj = provider.provide(((Class) (obj1)), ((Type) (obj)), context.genson))).deserialize(objectreader, context);
                } else
                {
/* 102*/            return ((Converter)wrapped).deserialize(objectreader, context);
                }
            }

            private final BeanViewDescriptorProvider provider;
            private final Type type;
}
