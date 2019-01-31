// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.classLoader
    implements PrivilegedAction
{

            public final Object run()
            {
/* 453*/        Thread.currentThread().setContextClassLoader(val$classLoader);
/* 454*/        return null;
            }

            final ClassLoader val$classLoader;

            (ClassLoader classloader)
            {
/* 450*/        val$classLoader = classloader;
/* 450*/        super();
            }
}
