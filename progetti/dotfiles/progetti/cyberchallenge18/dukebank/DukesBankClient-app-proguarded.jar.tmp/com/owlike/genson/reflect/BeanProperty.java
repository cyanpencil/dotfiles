// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanProperty.java

package com.owlike.genson.reflect;

import com.owlike.genson.annotation.JsonProperty;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil

public abstract class BeanProperty
{

            protected BeanProperty(String s, Type type1, Class class1, Class class2, Annotation aannotation[], int i)
            {
/*  28*/        name = s;
/*  29*/        type = type1;
/*  30*/        declaringClass = class1;
/*  31*/        concreteClass = class2;
/*  32*/        annotations = aannotation;
/*  33*/        modifiers = i;
            }

            public Class getDeclaringClass()
            {
/*  40*/        return declaringClass;
            }

            public Class getConcreteClass()
            {
/*  48*/        return concreteClass;
            }

            public String getName()
            {
/*  54*/        return name;
            }

            public Type getType()
            {
/*  61*/        return type;
            }

            public Class getRawClass()
            {
/*  65*/        return TypeUtil.getRawClass(type);
            }

            public int getModifiers()
            {
/*  69*/        return modifiers;
            }

            public String[] aliases()
            {
                JsonProperty jsonproperty;
/*  73*/        if((jsonproperty = (JsonProperty)getAnnotation(com/owlike/genson/annotation/JsonProperty)) != null)
/*  74*/            return jsonproperty.aliases();
/*  74*/        else
/*  74*/            return new String[0];
            }

            public Annotation getAnnotation(Class class1)
            {
                Annotation aannotation[];
/*  78*/        int i = (aannotation = annotations).length;
/*  78*/        for(int j = 0; j < i; j++)
                {
/*  78*/            Annotation annotation = aannotation[j];
/*  79*/            if(class1.isInstance(annotation))
/*  79*/                return (Annotation)class1.cast(annotation);
                }

/*  80*/        return null;
            }

            void updateBoth(BeanProperty beanproperty)
            {
/*  87*/        if(annotations.length > 0 || beanproperty.annotations.length > 0)
                {
/*  88*/            Annotation aannotation[] = new Annotation[annotations.length + beanproperty.annotations.length];
/*  90*/            System.arraycopy(annotations, 0, aannotation, 0, annotations.length);
/*  91*/            System.arraycopy(beanproperty.annotations, 0, aannotation, annotations.length, beanproperty.annotations.length);
/*  93*/            if(beanproperty.annotations.length > 0)
/*  93*/                annotations = aannotation;
/*  96*/            if(annotations.length > 0)
/*  96*/                beanproperty.annotations = aannotation;
                }
            }

            abstract int priority();

            abstract String signature();

            protected final String name;
            protected final Type type;
            protected final Class declaringClass;
            protected final Class concreteClass;
            protected Annotation annotations[];
            protected final int modifiers;
}
