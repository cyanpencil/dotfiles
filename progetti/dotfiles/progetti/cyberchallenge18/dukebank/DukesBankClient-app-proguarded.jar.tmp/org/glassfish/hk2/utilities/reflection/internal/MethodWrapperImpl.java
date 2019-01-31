// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodWrapperImpl.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.lang.reflect.Method;
import org.glassfish.hk2.utilities.reflection.*;

public class MethodWrapperImpl
    implements MethodWrapper
{

            public MethodWrapperImpl(Method method1)
            {
/*  60*/        if(method1 == null)
/*  60*/            throw new IllegalArgumentException();
/*  62*/        method = method1;
                int i;
/*  66*/        i = (i = 0 ^ method1.getName().hashCode()) ^ method1.getReturnType().hashCode();
/*  68*/        int j = (method1 = method1.getParameterTypes()).length;
/*  68*/        for(int k = 0; k < j; k++)
                {
/*  68*/            Object obj = method1[k];
/*  69*/            i ^= obj.hashCode();
                }

/*  72*/        hashCode = i;
            }

            public Method getMethod()
            {
/*  80*/        return method;
            }

            public int hashCode()
            {
/*  85*/        return hashCode;
            }

            public boolean equals(Object obj)
            {
/*  90*/        if(obj == null)
/*  90*/            return false;
/*  91*/        if(!(obj instanceof MethodWrapperImpl))
/*  91*/            return false;
/*  93*/        obj = (MethodWrapperImpl)obj;
/*  95*/        if(!method.getName().equals(((MethodWrapperImpl) (obj)).method.getName()))
/*  95*/            return false;
/*  96*/        if(!method.getReturnType().equals(((MethodWrapperImpl) (obj)).method.getReturnType()))
/*  96*/            return false;
/*  98*/        Class aclass[] = method.getParameterTypes();
/*  99*/        Class aclass1[] = ((MethodWrapperImpl) (obj)).method.getParameterTypes();
/* 101*/        if(aclass.length != aclass1.length)
/* 101*/            return false;
/* 103*/        if(ReflectionHelper.isPrivate(method) || ReflectionHelper.isPrivate(((MethodWrapperImpl) (obj)).method))
/* 103*/            return false;
/* 105*/        for(obj = 0; obj < aclass.length; obj++)
/* 106*/            if(!aclass[obj].equals(aclass1[obj]))
/* 106*/                return false;

/* 109*/        return true;
            }

            public String toString()
            {
/* 114*/        return (new StringBuilder("MethodWrapperImpl(")).append(Pretty.method(method)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final Method method;
            private final int hashCode;
}
