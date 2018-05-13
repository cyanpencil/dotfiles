// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Constructor;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.clazz
    implements PrivilegedAction
{

            public final Constructor run()
            {
/* 833*/        return val$clazz.getConstructor(new Class[] {
/* 833*/            java/lang/String
                });
/* 834*/        JVM INSTR dup ;
                SecurityException securityexception;
/* 835*/        securityexception;
/* 835*/        throw ;
/* 836*/        JVM INSTR pop ;
/* 837*/        return null;
            }

            public final volatile Object run()
            {
/* 829*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 829*/        val$clazz = class1;
/* 829*/        super();
            }
}
