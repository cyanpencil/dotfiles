// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Method;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.m
    implements PrivilegedAction
{

            public final Method run()
            {
/*1342*/        return val$c.getMethod(val$m.getName(), val$m.getParameterTypes());
/*1343*/        JVM INSTR pop ;
                Method amethod[];
/*1344*/        int i = (amethod = val$c.getMethods()).length;
/*1344*/        for(int j = 0; j < i; j++)
                {
                    Method method;
/*1344*/            if((method = amethod[j]).getName().equals(val$m.getName()) && method.getParameterTypes().length == val$m.getParameterTypes().length && ReflectionHelper.access$200(val$m.getGenericParameterTypes(), method.getGenericParameterTypes()))
/*1349*/                return method;
                }

/*1353*/        return null;
            }

            public final volatile Object run()
            {
/*1338*/        return run();
            }

            final Class val$c;
            final Method val$m;

            (Class class1, Method method)
            {
/*1338*/        val$c = class1;
/*1338*/        val$m = method;
/*1338*/        super();
            }
}
