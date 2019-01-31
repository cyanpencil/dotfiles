// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperUtilities.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.lang.reflect.Method;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperUtilities

static class val.clazz
    implements PrivilegedAction
{

            public final Method[] run()
            {
/* 108*/        return val$clazz.getDeclaredMethods();
            }

            public final volatile Object run()
            {
/* 104*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 104*/        val$clazz = class1;
/* 104*/        super();
            }
}
