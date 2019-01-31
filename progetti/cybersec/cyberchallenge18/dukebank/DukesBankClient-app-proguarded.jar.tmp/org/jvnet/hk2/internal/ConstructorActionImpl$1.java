// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorActionImpl.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Method;
import javassist.util.proxy.MethodFilter;

// Referenced classes of package org.jvnet.hk2.internal:
//            ConstructorActionImpl

static class 
    implements MethodFilter
{

            public final boolean isHandled(Method method)
            {
/*  68*/        return !method.getName().equals("finalize");
            }

            ()
            {
            }
}
