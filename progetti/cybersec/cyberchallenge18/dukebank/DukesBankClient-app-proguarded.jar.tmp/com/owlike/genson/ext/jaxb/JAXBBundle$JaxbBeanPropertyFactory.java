// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.reflect.*;
import java.lang.reflect.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class this._cls0
    implements BeanPropertyFactory
{

            public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
            {
/* 284*/        if((genson = getType(field, field.getGenericType(), type)) != null)
/* 286*/            return new com.owlike.genson.reflect.t>(s, field, genson, TypeUtil.getRawClass(type));
/* 289*/        else
/* 289*/            return null;
            }

            public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
            {
/* 295*/        if((genson = getType(method, method.getReturnType(), type)) != null)
/* 297*/            return new com.owlike.genson.reflect.it>(s, method, genson, TypeUtil.getRawClass(type));
/* 300*/        else
/* 300*/            return null;
            }

            public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
            {
/* 305*/        if((genson = getType(field, field.getGenericType(), type)) != null)
/* 307*/            return new com.owlike.genson.reflect.(s, field, genson, TypeUtil.getRawClass(type));
/* 310*/        else
/* 310*/            return null;
            }

            public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
            {
/* 315*/        if(method.getParameterTypes().length == 1 && (genson = getType(method, method.getReturnType(), type)) != null)
/* 318*/            return new com.owlike.genson.reflect.>(s, method, genson, TypeUtil.getRawClass(type));
/* 322*/        else
/* 322*/            return null;
            }

            public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
            {
/* 328*/        return null;
            }

            public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
            {
/* 334*/        return null;
            }

            private Type getType(AccessibleObject accessibleobject, Type type, Type type1)
            {
/* 338*/        if((type1 = (XmlElement)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlElement)) != null && type1.type() != javax/xml/bind/annotation/XmlElement$DEFAULT)
                {
/* 340*/            if(!TypeUtil.getRawClass(type).isAssignableFrom(type1.type()) && (accessibleobject = (XmlJavaTypeAdapter)accessibleobject.getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter)) == null)
/* 343*/                throw new ClassCastException((new StringBuilder("Inavlid XmlElement annotation, ")).append(type).append(" is not assignable from ").append(type1.type()).toString());
/* 346*/            else
/* 346*/                return type1.type();
                } else
                {
/* 348*/            return null;
                }
            }

            final JAXBBundle this$0;

            private nverter()
            {
/* 280*/        this$0 = JAXBBundle.this;
/* 280*/        super();
            }


            // Unreferenced inner class com/owlike/genson/ext/jaxb/JAXBBundle$1

/* anonymous class */
    class JAXBBundle._cls1 extends ChainedFactory
    {

                protected Converter create(Type type, Genson genson, Converter converter)
                {
/*  81*/            if((genson = (XmlRootElement)(type = TypeUtil.getRawClass(type)).getAnnotation(javax/xml/bind/annotation/XmlRootElement)) != null)
                    {
/*  85*/                type = "##default".equals(genson.name()) ? ((Type) (JAXBBundle.access$700(JAXBBundle.this, type.getSimpleName()))) : ((Type) (genson.name()));
/*  86*/                return new com.owlike.genson.convert.DefaultConverters.WrappedRootValueConverter(type, type, converter);
                    } else
                    {
/*  88*/                return null;
                    }
                }

                final JAXBBundle this$0;

                    
                    {
/*  78*/                this$0 = JAXBBundle.this;
/*  78*/                super();
                    }
    }

}
