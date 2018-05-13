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

            public final Object run()
            {
/* 477*/        if(!val$m.isAccessible())
/* 478*/            val$m.setAccessible(true);
/* 480*/        return val$m;
            }

            final Method val$m;

            (Method method)
            {
/* 473*/        val$m = method;
/* 473*/        super();
            }
}
