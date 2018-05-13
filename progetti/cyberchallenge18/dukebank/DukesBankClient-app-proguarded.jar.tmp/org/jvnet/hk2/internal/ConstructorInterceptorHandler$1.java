// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorInterceptorHandler.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            ConstructorAction, ConstructorInterceptorHandler

static class 
    implements ConstructorAction
{

            public final Object makeMe(Constructor constructor, Object aobj[], boolean flag)
                throws Throwable
            {
/*  64*/        return ReflectionHelper.makeMe(constructor, aobj, flag);
            }

            ()
            {
            }
}
