// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Type;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

public static class genericInterface
{

            public final Class concreteClass;
            public final Class declaringClass;
            public final Type genericInterface;

            private (Class class1, Class class2, Type type)
            {
/*1000*/        concreteClass = class1;
/*1001*/        declaringClass = class2;
/*1002*/        genericInterface = type;
            }


            // Unreferenced inner class org/glassfish/jersey/internal/util/ReflectionHelper$1

/* anonymous class */
    static class ReflectionHelper._cls1
        implements PrivilegedAction
    {

                public final Object run()
                {
/*  99*/            return null;
                }

    }

}
