// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.BeanView;
import com.owlike.genson.Genson;
import java.lang.reflect.*;
import java.util.Map;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanPropertyFactory, BeanViewDescriptorProvider, TypeUtil, PropertyAccessor, 
//            PropertyMutator, BeanCreator

public static class views
    implements BeanPropertyFactory
{

            public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
            {
/* 119*/        if((genson = (BeanView)views.get(TypeUtil.getRawClass(type))) != null)
                {
/* 121*/            Object obj = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, genson.getClass());
/* 123*/            obj = TypeUtil.getRawClass(TypeUtil.typeOf(0, TypeUtil.expandType(((Type) (obj)), genson.getClass())));
/* 126*/            type = TypeUtil.expandType(method.getGenericReturnType(), type);
/* 127*/            return new (s, method, type, genson, ((Class) (obj)));
                } else
                {
/* 128*/            return null;
                }
            }

            public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
            {
/* 133*/        if((genson = (BeanView)views.get(TypeUtil.getRawClass(type))) != null)
                {
/* 135*/            Object obj = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, genson.getClass());
/* 137*/            obj = TypeUtil.getRawClass(TypeUtil.typeOf(0, TypeUtil.expandType(((Type) (obj)), genson.getClass())));
/* 140*/            type = TypeUtil.expandType(method.getGenericParameterTypes()[0], type);
/* 141*/            return new <init>(s, method, type, genson, ((Class) (obj)));
                } else
                {
/* 142*/            return null;
                }
            }

            public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
            {
/* 148*/        return null;
            }

            public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
            {
/* 154*/        return null;
            }

            public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
            {
/* 160*/        return null;
            }

            public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
            {
/* 166*/        return null;
            }

            private final Map views;

            public (Map map)
            {
/* 113*/        views = map;
            }
}
