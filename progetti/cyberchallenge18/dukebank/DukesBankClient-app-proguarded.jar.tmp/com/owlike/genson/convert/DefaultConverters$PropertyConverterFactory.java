// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.reflect.BeanProperty;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.convert:
//            ContextualFactory, DefaultConverters

public static final class 
    implements ContextualFactory
{

            public final Converter create(BeanProperty beanproperty, Genson genson)
            {
/*1246*/        if((genson = (JsonConverter)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonConverter)) == null)
/*1248*/            break MISSING_BLOCK_LABEL_225;
/*1248*/        Type type = TypeUtil.expandType(TypeUtil.lookupGenericType(com/owlike/genson/Converter, genson.value()), genson.value());
/*1250*/        type = TypeUtil.typeOf(0, type);
                Class class1;
/*1252*/        if((class1 = beanproperty.getRawClass()).isPrimitive())
/*1253*/            class1 = TypeUtil.wrap(class1);
/*1256*/        if(!TypeUtil.match(class1, type, false))
/*1257*/            throw new ClassCastException((new StringBuilder("The type defined in ")).append(genson.value().getName()).append(" is not assignale from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
/*1262*/        if(!(beanproperty = genson.value().getConstructor(new Class[0])).isAccessible())
/*1263*/            beanproperty.setAccessible(true);
/*1264*/        return (Converter)beanproperty.newInstance(new Object[0]);
/*1267*/        beanproperty;
/*1268*/        throw new RuntimeException(beanproperty);
/*1269*/        beanproperty;
/*1270*/        throw new RuntimeException(beanproperty);
/*1271*/        beanproperty;
/*1272*/        throw new RuntimeException(beanproperty);
/*1273*/        beanproperty;
/*1274*/        throw new RuntimeException(beanproperty);
/*1275*/        beanproperty;
/*1276*/        throw new RuntimeException(beanproperty);
/*1277*/        beanproperty;
/*1278*/        throw new RuntimeException(beanproperty);
/*1281*/        return null;
            }

            public ()
            {
            }
}
