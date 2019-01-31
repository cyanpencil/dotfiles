// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericType.java

package javax.ws.rs.core;

import java.lang.reflect.*;
import java.util.*;

public class GenericType
{

            protected GenericType()
            {
/* 117*/        type = getTypeArgument(getClass(), javax/ws/rs/core/GenericType);
/* 118*/        rawType = getClass(type);
            }

            public GenericType(Type type1)
            {
/* 131*/        if(type1 == null)
                {
/* 132*/            throw new IllegalArgumentException("Type must not be null");
                } else
                {
/* 135*/            type = type1;
/* 136*/            rawType = getClass(type);
/* 137*/            return;
                }
            }

            public final Type getType()
            {
/* 145*/        return type;
            }

            public final Class getRawType()
            {
/* 156*/        return rawType;
            }

            private static Class getClass(Type type1)
            {
/* 167*/        if(type1 instanceof Class)
/* 168*/            return (Class)type1;
                Object obj;
/* 169*/        if(type1 instanceof ParameterizedType)
                {
/* 170*/            if(((ParameterizedType) (obj = (ParameterizedType)type1)).getRawType() instanceof Class)
/* 172*/                return (Class)((ParameterizedType) (obj)).getRawType();
                } else
/* 174*/        if(type1 instanceof GenericArrayType)
/* 175*/            return getArrayClass(type1 = getClass(((GenericArrayType) (obj = (GenericArrayType)type1)).getGenericComponentType()));
/* 179*/        throw new IllegalArgumentException((new StringBuilder("Type parameter ")).append(type1.toString()).append(" not a class or parameterized type whose raw type is a class").toString());
            }

            private static Class getArrayClass(Class class1)
            {
/* 191*/        return (class1 = ((Class) (Array.newInstance(class1, 0)))).getClass();
/* 193*/        class1;
/* 194*/        throw new IllegalArgumentException(class1);
            }

            static Type getTypeArgument(Class class1, Class class2)
            {
/* 207*/        Stack stack = new Stack();
/* 209*/        Class class3 = class1;
/* 211*/        do
                {
/* 211*/            class1 = class3.getGenericSuperclass();
/* 212*/            stack.push(class1);
/* 213*/            if(class1 instanceof Class)
/* 214*/                class3 = (Class)class1;
/* 215*/            else
/* 215*/            if(class1 instanceof ParameterizedType)
/* 216*/                class3 = (Class)((ParameterizedType)class1).getRawType();
                } while(!class3.equals(class2));
                ParameterizedType parameterizedtype;
                Class class4;
/* 221*/        for(class2 = class2.getTypeParameters()[0]; !stack.isEmpty() && ((class1 = (Type)stack.pop()) instanceof ParameterizedType) && (class2 = Arrays.asList((class4 = (Class)(parameterizedtype = (ParameterizedType)class1).getRawType()).getTypeParameters()).indexOf(class2)) >= 0;)
/* 230*/            if((class2 = parameterizedtype.getActualTypeArguments()[class2]) instanceof TypeVariable)
/* 234*/                class2 = (TypeVariable)class2;
/* 238*/            else
/* 238*/                return class2;

/* 246*/        throw new IllegalArgumentException((new StringBuilder()).append(class1).append(" does not specify the type parameter T of GenericType<T>").toString());
            }

            public boolean equals(Object obj)
            {
                boolean flag;
/* 251*/        if(!(flag = this == obj) && (obj instanceof GenericType))
                {
/* 254*/            obj = (GenericType)obj;
/* 255*/            return type.equals(((GenericType) (obj)).type);
                } else
                {
/* 257*/            return flag;
                }
            }

            public int hashCode()
            {
/* 262*/        return type.hashCode();
            }

            public String toString()
            {
/* 267*/        return (new StringBuilder("GenericType{")).append(type.toString()).append("}").toString();
            }

            private final Type type;
            private final Class rawType;
}
