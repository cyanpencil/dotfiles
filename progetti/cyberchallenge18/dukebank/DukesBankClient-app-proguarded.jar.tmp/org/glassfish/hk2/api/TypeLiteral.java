// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeLiteral.java

package org.glassfish.hk2.api;

import java.lang.reflect.*;

public abstract class TypeLiteral
{

            protected TypeLiteral()
            {
            }

            public final Type getType()
            {
/*  79*/        if(type == null)
                {
                    Class class1;
/*  81*/            if((class1 = getTypeLiteralSubclass(getClass())) == null)
/*  83*/                throw new RuntimeException((new StringBuilder()).append(getClass()).append(" is not a subclass of TypeLiteral<T>").toString());
/*  87*/            type = getTypeParameter(class1);
/*  88*/            if(type == null)
/*  89*/                throw new RuntimeException((new StringBuilder()).append(getClass()).append(" does not specify the type parameter T of TypeLiteral<T>").toString());
                }
/*  92*/        return type;
            }

            public final Type[] getParameterTypes()
            {
/* 100*/        type = getType();
/* 101*/        if(type instanceof ParameterizedType)
/* 102*/            return ((ParameterizedType)type).getActualTypeArguments();
/* 105*/        else
/* 105*/            return new Type[0];
            }

            public final Class getRawType()
            {
                Type type1;
/* 114*/        if(rawType == null)
/* 117*/            return getRawType(type1 = getType());
/* 121*/        else
/* 121*/            return rawType;
            }

            public static Class getRawType(Type type1)
            {
/* 131*/        if(type1 instanceof Class)
/* 133*/            return (Class)type1;
/* 135*/        if(type1 instanceof ParameterizedType)
/* 137*/            return (Class)(type1 = (ParameterizedType)type1).getRawType();
/* 140*/        if(type1 instanceof GenericArrayType)
/* 142*/            return [Ljava/lang/Object;;
/* 144*/        if(type1 instanceof WildcardType)
/* 145*/            return null;
/* 147*/        else
/* 147*/            throw new RuntimeException("Illegal type");
            }

            private static Class getTypeLiteralSubclass(Class class1)
            {
/* 158*/        do
                {
                    Class class2;
/* 158*/            if((class2 = class1.getSuperclass()).equals(org/glassfish/hk2/api/TypeLiteral))
/* 162*/                return class1;
/* 163*/            if(class2.equals(java/lang/Object))
/* 165*/                return null;
/* 168*/            class1 = class2;
                } while(true);
            }

            private static Type getTypeParameter(Class class1)
            {
/* 180*/        if(((class1 = class1.getGenericSuperclass()) instanceof ParameterizedType) && (class1 = (ParameterizedType)class1).getActualTypeArguments().length == 1)
/* 186*/            return class1.getActualTypeArguments()[0];
/* 189*/        else
/* 189*/            return null;
            }

            public boolean equals(Object obj)
            {
/* 194*/        if(obj instanceof TypeLiteral)
                {
/* 197*/            obj = (TypeLiteral)obj;
/* 198*/            return getType().equals(((TypeLiteral) (obj)).getType());
                } else
                {
/* 200*/            return false;
                }
            }

            public int hashCode()
            {
/* 205*/        return getType().hashCode();
            }

            public String toString()
            {
/* 210*/        return getType().toString();
            }

            private transient Type type;
            private transient Class rawType;
}
