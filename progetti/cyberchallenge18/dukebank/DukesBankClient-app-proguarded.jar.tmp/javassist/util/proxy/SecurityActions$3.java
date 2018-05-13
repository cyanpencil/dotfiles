// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityActions.java

package javassist.util.proxy;

import java.security.PrivilegedExceptionAction;

// Referenced classes of package javassist.util.proxy:
//            SecurityActions

static class val.types
    implements PrivilegedExceptionAction
{

            public final Object run()
                throws Exception
            {
/*  63*/        return val$clazz.getDeclaredMethod(val$name, val$types);
            }

            final Class val$clazz;
            final String val$name;
            final Class val$types[];

            (Class class1, String s, Class aclass[])
            {
/*  61*/        val$clazz = class1;
/*  61*/        val$name = s;
/*  61*/        val$types = aclass;
/*  61*/        super();
            }
}
