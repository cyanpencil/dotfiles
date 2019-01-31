// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.clazz
    implements PrivilegedAction
{

            public final Field[] run()
            {
/* 311*/        return val$clazz.getDeclaredFields();
            }

            public final volatile Object run()
            {
/* 308*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 308*/        val$clazz = class1;
/* 308*/        super();
            }
}
