// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInterceptorInvocationHandler.java

package org.jvnet.hk2.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import javassist.util.proxy.MethodHandler;

public class MethodInterceptorInvocationHandler
    implements InvocationHandler
{

            public MethodInterceptorInvocationHandler(MethodHandler methodhandler)
            {
/*  56*/        interceptor = methodhandler;
            }

            public Object invoke(Object obj, Method method, Object aobj[])
                throws Throwable
            {
/*  62*/        return interceptor.invoke(obj, method, null, aobj);
            }

            private final MethodHandler interceptor;
}
