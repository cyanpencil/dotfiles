// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.*;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.reflect.*;
import java.lang.reflect.*;
import javax.xml.bind.annotation.*;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class BaseResolver extends com.owlike.genson.reflect.yBaseResolver
{

            public Trilean isAccessor(Field field, Class class1)
            {
/* 392*/        if(ignore(field, field.getType(), class1))
/* 392*/            return Trilean.FALSE;
/* 393*/        if(include(field, field.getType(), class1))
/* 393*/            return Trilean.TRUE;
/* 394*/        else
/* 394*/            return analyzeAccessTypeInfo(field, field, XmlAccessType.FIELD, class1);
            }

            public Trilean isMutator(Field field, Class class1)
            {
/* 399*/        if(ignore(field, field.getType(), class1))
/* 399*/            return Trilean.FALSE;
/* 400*/        if(include(field, field.getType(), class1))
/* 400*/            return Trilean.TRUE;
/* 401*/        else
/* 401*/            return analyzeAccessTypeInfo(field, field, XmlAccessType.FIELD, class1);
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/* 406*/        if(ignore(method, method.getReturnType(), class1))
/* 406*/            return Trilean.FALSE;
/* 408*/        String s = null;
/* 409*/        if(method.getName().startsWith("get") && method.getName().length() > 3)
/* 410*/            s = method.getName().substring(3);
/* 411*/        else
/* 411*/        if(method.getName().startsWith("is") && method.getName().length() > 2 && method.getReturnType() == Boolean.TYPE || method.getReturnType() == java/lang/Boolean)
/* 414*/            s = method.getName().substring(2);
/* 416*/        if(s != null)
                {
/* 417*/            if(include(method, method.getReturnType(), class1))
/* 417*/                return Trilean.TRUE;
/* 418*/            if(JAXBBundle.access$900(JAXBBundle.this, javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("set")).append(s).toString(), new Class[] {
/* 418*/    method.getReturnType()
}) != null)
/* 419*/                return Trilean.FALSE;
                }
/* 422*/        return analyzeAccessTypeInfo(method, method, XmlAccessType.PROPERTY, class1);
            }

            public Trilean isMutator(Method method, Class class1)
            {
/* 427*/        Object obj = method.getParameterTypes().length != 1 ? java/lang/Object : ((Object) (method.getParameterTypes()[0]));
/* 429*/        if(ignore(method, ((Class) (obj)), class1))
/* 429*/            return Trilean.FALSE;
/* 431*/        if(method.getName().startsWith("set") && method.getName().length() > 3)
                {
/* 432*/            if(include(method, method.getReturnType(), class1))
/* 432*/                return Trilean.TRUE;
/* 434*/            String s = method.getName().substring(3);
/* 435*/            if(JAXBBundle.access$900(JAXBBundle.this, javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("get")).append(s).toString(), new Class[0]) != null)
/* 436*/                return Trilean.FALSE;
/* 437*/            if((obj.equals(Boolean.TYPE) || obj.equals(java/lang/Boolean)) && JAXBBundle.access$900(JAXBBundle.this, javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("is")).append(s).toString(), new Class[0]) != null)
/* 439*/                return Trilean.FALSE;
                }
/* 443*/        return analyzeAccessTypeInfo(method, method, XmlAccessType.PROPERTY, class1);
            }

            public Trilean analyzeAccessTypeInfo(AccessibleObject accessibleobject, Member member, XmlAccessType xmlaccesstype, Class class1)
            {
/* 448*/        if((accessibleobject = (XmlAccessorType)JAXBBundle.access$1000(JAXBBundle.this, javax/xml/bind/annotation/XmlAccessorType, accessibleobject, class1)) != null)
                {
/* 451*/            if(accessibleobject.value() == xmlaccesstype && VisibilityFilter.PRIVATE.isVisible(member))
/* 452*/                return Trilean.TRUE;
/* 453*/            if(accessibleobject.value() != xmlaccesstype && accessibleobject.value() != XmlAccessType.PUBLIC_MEMBER)
/* 455*/                return Trilean.FALSE;
                }
/* 458*/        return Trilean.UNKNOWN;
            }

            private boolean ignore(AccessibleObject accessibleobject, Class class1, Class class2)
            {
/* 462*/        return (accessibleobject = (XmlTransient)JAXBBundle.access$1000(JAXBBundle.this, javax/xml/bind/annotation/XmlTransient, accessibleobject, class1)) != null;
            }

            private boolean include(AccessibleObject accessibleobject, Class class1, Class class2)
            {
/* 469*/        return JAXBBundle.access$1000(JAXBBundle.this, javax/xml/bind/annotation/XmlAttribute, accessibleobject, class1) != null || JAXBBundle.access$1000(JAXBBundle.this, javax/xml/bind/annotation/XmlElement, accessibleobject, class1) != null;
            }

            final JAXBBundle this$0;

            private nverter()
            {
/* 388*/        this$0 = JAXBBundle.this;
/* 388*/        super();
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
