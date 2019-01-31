// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Proxy;
import java.security.PrivilegedAction;
import javassist.util.proxy.MethodHandler;

// Referenced classes of package org.jvnet.hk2.internal:
//            MethodInterceptorInvocationHandler, ProxyUtilities, DelegatingClassLoader

class val.callback
    implements PrivilegedAction
{

            public Object run()
            {
/* 134*/        return Proxy.newProxyInstance(val$delegatingLoader, val$interfaces, new MethodInterceptorInvocationHandler(val$callback));
            }

            final DelegatingClassLoader val$delegatingLoader;
            final Class val$interfaces[];
            final MethodHandler val$callback;
            final ProxyUtilities this$0;

            ader()
            {
/* 129*/        this$0 = final_proxyutilities;
/* 129*/        val$delegatingLoader = delegatingclassloader;
/* 129*/        val$interfaces = aclass;
/* 129*/        val$callback = MethodHandler.this;
/* 129*/        super();
            }
}
