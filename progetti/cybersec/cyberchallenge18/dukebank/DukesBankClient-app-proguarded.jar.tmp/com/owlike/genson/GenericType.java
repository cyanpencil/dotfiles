// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericType.java

package com.owlike.genson;

import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericType
{

            protected GenericType()
            {
                Type type1;
/*  34*/        if((type1 = getClass().getGenericSuperclass()) instanceof Class)
                {
/*  36*/            throw new IllegalArgumentException("You must specify the parametrized type!");
                } else
                {
/*  38*/            type = ((ParameterizedType)type1).getActualTypeArguments()[0];
/*  39*/            rawClass = TypeUtil.getRawClass(type);
/*  40*/            return;
                }
            }

            private GenericType(Class class1)
            {
/*  43*/        type = class1;
/*  44*/        rawClass = class1;
            }

            private GenericType(Type type1)
            {
/*  49*/        type = type1;
/*  50*/        rawClass = TypeUtil.getRawClass(type1);
            }

            public static GenericType of(Class class1)
            {
/*  54*/        return new GenericType(class1) {

        };
            }

            public static GenericType of(Type type1)
            {
/*  59*/        return new GenericType(type1) {

        };
            }

            public Type getType()
            {
/*  64*/        return type;
            }

            public Class getRawClass()
            {
/*  68*/        return rawClass;
            }



            private final Type type;
            private final Class rawClass;
}
