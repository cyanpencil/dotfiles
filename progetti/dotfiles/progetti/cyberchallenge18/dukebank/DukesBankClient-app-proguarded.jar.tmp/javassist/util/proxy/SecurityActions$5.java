// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityActions.java

package javassist.util.proxy;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;

// Referenced classes of package javassist.util.proxy:
//            SecurityActions

static class val.accessible
    implements PrivilegedAction
{

            public final Object run()
            {
/* 107*/        val$ao.setAccessible(val$accessible);
/* 108*/        return null;
            }

            final AccessibleObject val$ao;
            final boolean val$accessible;

            (AccessibleObject accessibleobject, boolean flag)
            {
/* 105*/        val$ao = accessibleobject;
/* 105*/        val$accessible = flag;
/* 105*/        super();
            }
}
