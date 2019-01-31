// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityActions.java

package javassist.util.proxy;

import java.security.PrivilegedAction;

// Referenced classes of package javassist.util.proxy:
//            SecurityActions

static class val.clazz
    implements PrivilegedAction
{

            public final Object run()
            {
/*  35*/        return val$clazz.getDeclaredMethods();
            }

            final Class val$clazz;

            (Class class1)
            {
/*  33*/        val$clazz = class1;
/*  33*/        super();
            }
}
