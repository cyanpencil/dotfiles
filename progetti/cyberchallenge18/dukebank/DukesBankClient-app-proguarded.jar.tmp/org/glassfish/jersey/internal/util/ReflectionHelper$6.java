// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.clazz
    implements PrivilegedAction
{

            public final Collection run()
            {
/* 356*/        return Arrays.asList(val$clazz.getDeclaredMethods());
            }

            public final volatile Object run()
            {
/* 353*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 353*/        val$clazz = class1;
/* 353*/        super();
            }
}
