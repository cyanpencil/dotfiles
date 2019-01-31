// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractEntityProviderModel.java

package org.glassfish.jersey.message;

import java.util.List;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public abstract class AbstractEntityProviderModel
{

            AbstractEntityProviderModel(Object obj, List list, boolean flag, Class class1)
            {
/*  76*/        provider = obj;
/*  77*/        declaredTypes = list;
/*  78*/        custom = flag;
/*  79*/        providedType = getProviderClassParam(obj, class1);
            }

            public Object provider()
            {
/*  88*/        return provider;
            }

            public List declaredTypes()
            {
/*  97*/        return declaredTypes;
            }

            public boolean isCustom()
            {
/* 107*/        return custom;
            }

            public Class providedType()
            {
/* 116*/        return providedType;
            }

            private static Class getProviderClassParam(Object obj, Class class1)
            {
/* 120*/        if((obj = ReflectionHelper.getParameterizedClassArguments(((org.glassfish.jersey.internal.util.ReflectionHelper.DeclaringClassInterfacePair) (obj = ReflectionHelper.getClass(obj.getClass(), class1))))) != null)
/* 124*/            return obj[0];
/* 124*/        else
/* 124*/            return java/lang/Object;
            }

            private final Object provider;
            private final List declaredTypes;
            private final boolean custom;
            private final Class providedType;
}
