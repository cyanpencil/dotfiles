// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;
import javax.xml.bind.annotation.XmlRootElement;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class  extends ChainedFactory
{

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  81*/        if((genson = (XmlRootElement)(type = TypeUtil.getRawClass(type)).getAnnotation(javax/xml/bind/annotation/XmlRootElement)) != null)
                {
/*  85*/            type = "##default".equals(genson.name()) ? ((Type) (JAXBBundle.access$700(JAXBBundle.this, type.getSimpleName()))) : ((Type) (genson.name()));
/*  86*/            return new com.owlike.genson.convert.ters.WrappedRootValueConverter(type, type, converter);
                } else
                {
/*  88*/            return null;
                }
            }

            final JAXBBundle this$0;

            ()
            {
/*  78*/        this$0 = JAXBBundle.this;
/*  78*/        super();
            }
}
