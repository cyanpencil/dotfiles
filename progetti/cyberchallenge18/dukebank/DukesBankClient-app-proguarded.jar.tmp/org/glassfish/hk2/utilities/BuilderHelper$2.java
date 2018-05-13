// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BuilderHelper.java

package org.glassfish.hk2.utilities;

import java.lang.reflect.Method;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities:
//            BuilderHelper

static class val.annotationClass
    implements PrivilegedAction
{

            public final Method[] run()
            {
/* 587*/        return val$annotationClass.getDeclaredMethods();
            }

            public final volatile Object run()
            {
/* 583*/        return run();
            }

            final Class val$annotationClass;

            (Class class1)
            {
/* 583*/        val$annotationClass = class1;
/* 583*/        super();
            }
}
