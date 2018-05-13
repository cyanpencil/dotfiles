// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericArrayTypeImpl.java

package org.jvnet.tiger_types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

final class GenericArrayTypeImpl
    implements GenericArrayType
{

            GenericArrayTypeImpl(Type type)
            {
/*  52*/        if(!$assertionsDisabled && type == null)
                {
/*  52*/            throw new AssertionError();
                } else
                {
/*  53*/            genericComponentType = type;
/*  54*/            return;
                }
            }

            public final Type getGenericComponentType()
            {
/*  65*/        return genericComponentType;
            }

            public final String toString()
            {
/*  69*/        Type type = getGenericComponentType();
/*  70*/        StringBuilder stringbuilder = new StringBuilder();
/*  72*/        if(type instanceof Class)
/*  73*/            stringbuilder.append(((Class)type).getName());
/*  75*/        else
/*  75*/            stringbuilder.append(type.toString());
/*  76*/        stringbuilder.append("[]");
/*  77*/        return stringbuilder.toString();
            }

            public final boolean equals(Object obj)
            {
/*  82*/        if(obj instanceof GenericArrayType)
                {
/*  83*/            obj = ((GenericArrayType) (obj = (GenericArrayType)obj)).getGenericComponentType();
/*  86*/            return genericComponentType.equals(obj);
                } else
                {
/*  88*/            return false;
                }
            }

            public final int hashCode()
            {
/*  93*/        return genericComponentType.hashCode();
            }

            private Type genericComponentType;
            static final boolean $assertionsDisabled = !org/jvnet/tiger_types/GenericArrayTypeImpl.desiredAssertionStatus();

}
