// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicConvertersFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.BeanDescriptorProvider;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.*;

public class BasicConvertersFactory
    implements Factory
{
    class DelegatedConverter extends Wrapper
        implements Converter
    {

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 118*/            serializer.serialize(obj, objectwriter, context);
                }

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 122*/            return deserializer.deserialize(objectreader, context);
                }

                public Annotation getAnnotation(Class class1)
                {
/* 127*/            Annotation annotation = null;
/* 128*/            if(serializer != null)
/* 128*/                annotation = toAnnotatedElement(serializer).getAnnotation(class1);
/* 129*/            if(deserializer != null && annotation == null)
/* 130*/                annotation = toAnnotatedElement(deserializer).getAnnotation(class1);
/* 131*/            return annotation;
                }

                public Annotation[] getAnnotations()
                {
/* 136*/            if(serializer != null && deserializer != null)
/* 137*/                return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/* 137*/                    toAnnotatedElement(serializer).getAnnotations(), toAnnotatedElement(deserializer).getAnnotations()
                        });
/* 139*/            if(serializer != null)
/* 139*/                return toAnnotatedElement(serializer).getAnnotations();
/* 140*/            if(deserializer != null)
/* 140*/                return toAnnotatedElement(deserializer).getAnnotations();
/* 142*/            else
/* 142*/                return new Annotation[0];
                }

                public Annotation[] getDeclaredAnnotations()
                {
/* 147*/            if(serializer != null && deserializer != null)
/* 148*/                return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/* 148*/                    toAnnotatedElement(serializer).getDeclaredAnnotations(), toAnnotatedElement(deserializer).getDeclaredAnnotations()
                        });
/* 151*/            if(serializer != null)
/* 151*/                return toAnnotatedElement(serializer).getDeclaredAnnotations();
/* 152*/            if(deserializer != null)
/* 153*/                return toAnnotatedElement(deserializer).getDeclaredAnnotations();
/* 155*/            else
/* 155*/                return new Annotation[0];
                }

                public boolean isAnnotationPresent(Class class1)
                {
/* 160*/            if(serializer != null)
/* 161*/                return toAnnotatedElement(serializer).isAnnotationPresent(class1);
/* 162*/            if(deserializer != null)
/* 163*/                return toAnnotatedElement(deserializer).isAnnotationPresent(class1);
/* 164*/            else
/* 164*/                return false;
                }

                private final Serializer serializer;
                private final Deserializer deserializer;
                final BasicConvertersFactory this$0;

                public DelegatedConverter(Serializer serializer1, Deserializer deserializer1)
                {
/* 112*/            this$0 = BasicConvertersFactory.this;
/* 112*/            super();
/* 113*/            serializer = serializer1;
/* 114*/            deserializer = deserializer1;
                }
    }


            public BasicConvertersFactory(Map map, Map map1, List list, BeanDescriptorProvider beandescriptorprovider)
            {
/*  60*/        serializersMap = map;
/*  61*/        deserializersMap = map1;
/*  62*/        factories = list;
/*  63*/        beanDescriptorProvider = beandescriptorprovider;
            }

            public Converter create(Type type, Genson genson)
            {
/*  69*/        Serializer serializer = (Serializer)provide(com/owlike/genson/Serializer, type, serializersMap, genson);
/*  70*/        type = (Deserializer)provide(com/owlike/genson/Deserializer, type, deserializersMap, genson);
/*  71*/        if((serializer instanceof Converter) && (type instanceof Converter))
/*  72*/            type = (Converter)type;
/*  74*/        else
/*  74*/            type = new DelegatedConverter(serializer, type);
/*  76*/        return type;
            }

            protected Object provide(Class class1, Type type, Map map, Genson genson)
            {
/*  82*/        if(map.containsKey(type))
/*  82*/            return map.get(type);
/*  84*/        map = type;
/*  85*/        if((type instanceof Class) && ((Class)type).isPrimitive())
/*  86*/            map = TypeUtil.wrap((Class)type);
/*  88*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
                {
/*  89*/            Object obj = (Factory)iterator.next();
                    Type type1;
/*  91*/            type1 = TypeUtil.expandType(type1 = TypeUtil.lookupGenericType(com/owlike/genson/Factory, obj.getClass()), obj.getClass());
/*  95*/            type1 = TypeUtil.typeOf(0, type1);
/*  96*/            Type type2 = TypeUtil.typeOf(0, type1);
/*  97*/            if(class1.isAssignableFrom(TypeUtil.getRawClass(type1)) && TypeUtil.match(map, type2, false) && (obj = ((Factory) (obj)).create(type, genson)) != null)
/* 100*/                return class1.cast(obj);
                }

/* 104*/        return beanDescriptorProvider.provide(TypeUtil.getRawClass(type), type, genson);
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  51*/        return create(type, genson);
            }

            private final Map serializersMap;
            private final Map deserializersMap;
            private final List factories;
            private final BeanDescriptorProvider beanDescriptorProvider;
}
