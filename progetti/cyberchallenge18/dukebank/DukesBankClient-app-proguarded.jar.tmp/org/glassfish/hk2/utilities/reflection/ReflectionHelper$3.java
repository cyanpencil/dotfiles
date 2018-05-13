// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

static class val.ao
    implements PrivilegedAction
{

            public final Object run()
            {
/*1323*/        val$ao.setAccessible(true);
/*1324*/        return null;
            }

            final AccessibleObject val$ao;

            (AccessibleObject accessibleobject)
            {
/*1319*/        val$ao = accessibleobject;
/*1319*/        super();
            }
}
