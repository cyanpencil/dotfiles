// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.reflect.PropertyNameResolver;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.*;
import javax.xml.bind.annotation.*;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class this._cls0
    implements PropertyNameResolver
{

            public String resolve(int i, Constructor constructor)
            {
/* 357*/        return null;
            }

            public String resolve(int i, Method method)
            {
/* 362*/        return null;
            }

            public String resolve(Field field)
            {
/* 367*/        return extractName(field);
            }

            public String resolve(Method method)
            {
/* 372*/        return extractName(method);
            }

            private String extractName(AccessibleObject accessibleobject)
            {
/* 376*/        String s = null;
                XmlAttribute xmlattribute;
/* 377*/        if((xmlattribute = (XmlAttribute)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlAttribute)) != null)
/* 379*/            s = xmlattribute.name();
/* 381*/        else
/* 381*/        if((accessibleobject = (XmlElement)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlElement)) != null)
/* 382*/            s = accessibleobject.name();
/* 384*/        if("##default".equals(s))
/* 384*/            return null;
/* 384*/        else
/* 384*/            return s;
            }

            private static final String DEFAULT_NAME = "##default";
            final JAXBBundle this$0;

            private ValueConverter()
            {
/* 352*/        this$0 = JAXBBundle.this;
/* 352*/        super();
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
