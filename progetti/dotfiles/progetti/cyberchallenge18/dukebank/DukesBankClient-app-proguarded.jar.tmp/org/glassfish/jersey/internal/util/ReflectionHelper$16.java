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

            public final Constructor[] run()
            {
/* 856*/        return val$clazz.getDeclaredConstructors();
            }

            public final volatile Object run()
            {
/* 853*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 853*/        val$clazz = class1;
/* 853*/        super();
            }
}
