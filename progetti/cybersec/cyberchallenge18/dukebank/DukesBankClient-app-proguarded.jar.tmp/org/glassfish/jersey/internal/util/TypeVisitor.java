// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeVisitor.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.*;

abstract class TypeVisitor
{

            TypeVisitor()
            {
            }

            public final Object visit(Type type)
            {
/*  64*/        if(!$assertionsDisabled && type == null)
/*  64*/            throw new AssertionError();
/*  66*/        if(type instanceof Class)
/*  67*/            return onClass((Class)type);
/*  69*/        if(type instanceof ParameterizedType)
/*  70*/            return onParameterizedType((ParameterizedType)type);
/*  72*/        if(type instanceof GenericArrayType)
/*  73*/            return onGenericArray((GenericArrayType)type);
/*  75*/        if(type instanceof WildcardType)
/*  76*/            return onWildcard((WildcardType)type);
/*  78*/        if(type instanceof TypeVariable)
/*  79*/            return onVariable((TypeVariable)type);
/*  83*/        if(!$assertionsDisabled)
/*  83*/            throw new AssertionError();
/*  85*/        else
/*  85*/            throw createError(type);
            }

            protected abstract Object onClass(Class class1);

            protected abstract Object onParameterizedType(ParameterizedType parameterizedtype);

            protected abstract Object onGenericArray(GenericArrayType genericarraytype);

            protected abstract Object onVariable(TypeVariable typevariable);

            protected abstract Object onWildcard(WildcardType wildcardtype);

            protected RuntimeException createError(Type type)
            {
/* 135*/        throw new IllegalArgumentException();
            }

            static final boolean $assertionsDisabled = !org/glassfish/jersey/internal/util/TypeVisitor.desiredAssertionStatus();

}
