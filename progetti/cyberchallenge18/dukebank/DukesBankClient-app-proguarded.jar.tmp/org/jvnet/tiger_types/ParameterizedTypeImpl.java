// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParameterizedTypeImpl.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;
import java.util.Arrays;

final class ParameterizedTypeImpl
    implements ParameterizedType
{

            ParameterizedTypeImpl(Class class1, Type atype[], Type type)
            {
/*  59*/        actualTypeArguments = atype;
/*  60*/        rawType = class1;
/*  61*/        if(type != null)
/*  62*/            ownerType = type;
/*  64*/        else
/*  64*/            ownerType = class1.getDeclaringClass();
/*  66*/        validateConstructorArguments();
            }

            private void validateConstructorArguments()
            {
                java.lang.reflect.TypeVariable atypevariable[];
/*  70*/        if((atypevariable = rawType.getTypeParameters()).length != actualTypeArguments.length)
/*  73*/            throw new MalformedParameterizedTypeException();
/*  79*/        else
/*  79*/            return;
            }

            public final Type[] getActualTypeArguments()
            {
/*  82*/        return (Type[])actualTypeArguments.clone();
            }

            public final Class getRawType()
            {
/*  86*/        return rawType;
            }

            public final Type getOwnerType()
            {
/*  90*/        return ownerType;
            }

            public final boolean equals(Object obj)
            {
/* 102*/        if(obj instanceof ParameterizedType)
                {
/* 104*/            obj = (ParameterizedType)obj;
/* 106*/            if(this == obj)
/* 107*/                return true;
/* 109*/            Type type = ((ParameterizedType) (obj)).getOwnerType();
/* 110*/            Type type1 = ((ParameterizedType) (obj)).getRawType();
/* 132*/            return (ownerType != null ? ownerType.equals(type) : type == null) && (rawType != null ? rawType.equals(type1) : type1 == null) && Arrays.equals(actualTypeArguments, ((ParameterizedType) (obj)).getActualTypeArguments());
                } else
                {
/* 142*/            return false;
                }
            }

            public final int hashCode()
            {
/* 147*/        return Arrays.hashCode(actualTypeArguments) ^ (ownerType != null ? ownerType.hashCode() : 0) ^ (rawType != null ? rawType.hashCode() : 0);
            }

            public final String toString()
            {
/* 153*/        StringBuilder stringbuilder = new StringBuilder();
/* 155*/        if(ownerType != null)
                {
/* 156*/            if(ownerType instanceof Class)
/* 157*/                stringbuilder.append(((Class)ownerType).getName());
/* 159*/            else
/* 159*/                stringbuilder.append(ownerType.toString());
/* 161*/            stringbuilder.append(".");
/* 163*/            if(ownerType instanceof ParameterizedTypeImpl)
/* 166*/                stringbuilder.append(rawType.getName().replace((new StringBuilder()).append(((ParameterizedTypeImpl)ownerType).rawType.getName()).append("$").toString(), ""));
/* 169*/            else
/* 169*/                stringbuilder.append(rawType.getName());
                } else
                {
/* 171*/            stringbuilder.append(rawType.getName());
                }
/* 173*/        if(actualTypeArguments != null && actualTypeArguments.length > 0)
                {
/* 175*/            stringbuilder.append("<");
/* 176*/            boolean flag = true;
                    Type atype[];
/* 177*/            int i = (atype = actualTypeArguments).length;
/* 177*/            for(int j = 0; j < i; j++)
                    {
/* 177*/                Type type = atype[j];
/* 178*/                if(!flag)
/* 179*/                    stringbuilder.append(", ");
/* 180*/                if(type instanceof Class)
/* 181*/                    stringbuilder.append(((Class)type).getName());
/* 183*/                else
/* 183*/                    stringbuilder.append(type.toString());
/* 184*/                flag = false;
                    }

/* 186*/            stringbuilder.append(">");
                }
/* 189*/        return stringbuilder.toString();
            }

            public final volatile Type getRawType()
            {
/*  51*/        return getRawType();
            }

            private Type actualTypeArguments[];
            private Class rawType;
            private Type ownerType;
}
