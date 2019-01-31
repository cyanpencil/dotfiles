// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInterceptorHandler.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            MethodInterceptorHandler

static class 
    implements PrivilegedAction
{

            public final Boolean run()
            {
/*  73*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.tracing.interceptors", "false")));
            }

            public final volatile Object run()
            {
/*  70*/        return run();
            }

            ()
            {
            }
}
