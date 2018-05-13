// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanPropertyFactory.java

package com.owlike.genson.reflect;

import com.owlike.genson.Genson;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanCreator, BeanPropertyFactory, PropertyAccessor, PropertyMutator, 
//            TypeUtil

public static class 
    implements BeanPropertyFactory
{

            public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
            {
/*  96*/        genson = TypeUtil.getRawClass(type);
/*  97*/        type = TypeUtil.expandType(field.getGenericType(), type);
/*  98*/        return new >(s, field, type, genson);
            }

            public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
            {
/* 103*/        genson = TypeUtil.expandType(method.getGenericReturnType(), type);
/* 104*/        return new t>(s, method, genson, TypeUtil.getRawClass(type));
            }

            public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
            {
/* 109*/        genson = TypeUtil.getRawClass(type);
/* 110*/        type = TypeUtil.expandType(field.getGenericType(), type);
/* 111*/        return new t>(s, field, type, genson);
            }

            public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
            {
/* 115*/        genson = TypeUtil.expandType(method.getGenericParameterTypes()[0], type);
/* 116*/        return new (s, method, genson, TypeUtil.getRawClass(type));
            }

            public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
            {
/* 124*/        return new (method, as, expandTypes(method.getGenericParameterTypes(), type), TypeUtil.getRawClass(type));
            }

            public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
            {
/* 130*/        return new init>(TypeUtil.getRawClass(type), constructor, as, expandTypes(constructor.getGenericParameterTypes(), type));
            }

            public Type[] expandTypes(Type atype[], Type type)
            {
/* 135*/        Type atype1[] = new Type[atype.length];
/* 136*/        for(int i = 0; i < atype.length; i++)
/* 137*/            atype1[i] = TypeUtil.expandType(atype[i], type);

/* 139*/        return atype1;
            }

            public ()
            {
            }
}
