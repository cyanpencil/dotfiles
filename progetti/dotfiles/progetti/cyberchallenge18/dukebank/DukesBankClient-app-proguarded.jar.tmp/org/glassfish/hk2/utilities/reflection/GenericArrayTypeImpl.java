// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericArrayTypeImpl.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import org.glassfish.hk2.utilities.general.GeneralUtilities;

public class GenericArrayTypeImpl
    implements GenericArrayType
{

            public GenericArrayTypeImpl(Type type)
            {
/*  64*/        genericComponentType = type;
            }

            public Type getGenericComponentType()
            {
/*  72*/        return genericComponentType;
            }

            public int hashCode()
            {
/*  77*/        return genericComponentType.hashCode();
            }

            public boolean equals(Object obj)
            {
/*  82*/        if(obj == null)
/*  82*/            return false;
/*  83*/        if(!(obj instanceof GenericArrayType))
                {
/*  83*/            return false;
                } else
                {
/*  85*/            obj = (GenericArrayType)obj;
/*  87*/            return GeneralUtilities.safeEquals(genericComponentType, ((GenericArrayType) (obj)).getGenericComponentType());
                }
            }

            public String toString()
            {
/*  91*/        return (new StringBuilder("GenericArrayTypeImpl(")).append(genericComponentType).append(")").toString();
            }

            private final Type genericComponentType;
}
