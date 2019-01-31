// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.methodName
    implements PrivilegedAction
{

            public final Method run()
            {
                Method method;
/* 806*/        if(Modifier.isStatic((method = val$clazz.getDeclaredMethod(val$methodName, new Class[] {
/* 806*/    java/lang/String
})).getModifiers()) && method.getReturnType() == val$clazz)
/* 808*/            return method;
/* 810*/        else
/* 810*/            return null;
/* 811*/        JVM INSTR pop ;
/* 812*/        return null;
            }

            public final volatile Object run()
            {
/* 802*/        return run();
            }

            final Class val$clazz;
            final String val$methodName;

            (Class class1, String s)
            {
/* 802*/        val$clazz = class1;
/* 802*/        val$methodName = s;
/* 802*/        super();
            }
}
