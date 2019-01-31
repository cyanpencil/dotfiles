// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

static class val.l
    implements PrivilegedAction
{

            public final Object run()
            {
/*1302*/        val$t.setContextClassLoader(val$l);
/*1303*/        return null;
            }

            final Thread val$t;
            final ClassLoader val$l;

            (Thread thread, ClassLoader classloader)
            {
/*1298*/        val$t = thread;
/*1298*/        val$l = classloader;
/*1298*/        super();
            }
}
