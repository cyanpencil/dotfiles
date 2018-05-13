// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeVisitor.java

package org.jvnet.tiger_types;

import java.lang.reflect.*;

abstract class TypeVisitor
{

            TypeVisitor()
            {
            }

            public final Object visit(Type type, Object obj)
            {
/*  53*/        if(!$assertionsDisabled && type == null)
/*  53*/            throw new AssertionError();
/*  55*/        if(type instanceof Class)
/*  56*/            return onClass((Class)type, obj);
/*  57*/        if(type instanceof ParameterizedType)
/*  58*/            return onParameterizdType((ParameterizedType)type, obj);
/*  59*/        if(type instanceof GenericArrayType)
/*  60*/            return onGenericArray((GenericArrayType)type, obj);
/*  61*/        if(type instanceof WildcardType)
/*  62*/            return onWildcard((WildcardType)type, obj);
/*  63*/        if(type instanceof TypeVariable)
/*  64*/            return onVariable((TypeVariable)type, obj);
/*  67*/        if(!$assertionsDisabled)
/*  67*/            throw new AssertionError();
/*  68*/        else
/*  68*/            throw new IllegalArgumentException();
            }

            protected abstract Object onClass(Class class1, Object obj);

            protected abstract Object onParameterizdType(ParameterizedType parameterizedtype, Object obj);

            protected abstract Object onGenericArray(GenericArrayType genericarraytype, Object obj);

            protected abstract Object onVariable(TypeVariable typevariable, Object obj);

            protected abstract Object onWildcard(WildcardType wildcardtype, Object obj);

            static final boolean $assertionsDisabled = !org/jvnet/tiger_types/TypeVisitor.desiredAssertionStatus();

}
