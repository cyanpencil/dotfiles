// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInterceptorImpl.java

package org.jvnet.hk2.internal;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import javassist.util.proxy.MethodHandler;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceHandleImpl, ServiceLocatorImpl

public class MethodInterceptorImpl
    implements MethodHandler
{

            MethodInterceptorImpl(ServiceLocatorImpl servicelocatorimpl, ActiveDescriptor activedescriptor, ServiceHandleImpl servicehandleimpl, Injectee injectee)
            {
/*  73*/        locator = servicelocatorimpl;
/*  74*/        descriptor = activedescriptor;
/*  75*/        root = servicehandleimpl;
/*  76*/        if(injectee != null)
                {
/*  77*/            myInjectee = new WeakReference(injectee);
/*  77*/            return;
                } else
                {
/*  80*/            myInjectee = null;
/*  82*/            return;
                }
            }

            private Object internalInvoke(Object obj, Method method, Method method1, Object aobj[])
                throws Throwable
            {
/*  88*/        if((method1 = ((Method) (((Context) (obj = locator.resolveContext(descriptor.getScopeAnnotation()))).findOrCreate(descriptor, root)))) == null)
/*  92*/            throw new MultiException(new IllegalStateException((new StringBuilder("Proxiable context ")).append(obj).append(" findOrCreate returned a null for descriptor ").append(descriptor).append(" and handle ").append(root).toString()));
/*  97*/        if(method.getName().equals("__make"))
/*  99*/            return method1;
/* 102*/        if(isEquals(method) && aobj.length == 1 && aobj[0] != null && (aobj[0] instanceof ProxyCtl))
                {
/* 103*/            obj = (ProxyCtl)aobj[0];
/* 105*/            (aobj = new Object[1])[0] = ((ProxyCtl) (obj)).__make();
                }
/* 109*/        return ReflectionHelper.invoke(method1, method, aobj, locator.getNeutralContextClassLoader());
            }

            public Object invoke(Object obj, Method method, Method method1, Object aobj[])
                throws Throwable
            {
                boolean flag;
/* 115*/        flag = false;
                Injectee injectee;
/* 116*/        if(root != null && myInjectee != null && (injectee = (Injectee)myInjectee.get()) != null)
                {
/* 119*/            root.pushInjectee(injectee);
/* 120*/            flag = true;
                }
/* 125*/        Object obj1 = internalInvoke(obj, method, method1, aobj);
/* 128*/        if(flag)
/* 129*/            root.popInjectee();
/* 129*/        return obj1;
/* 128*/        obj;
/* 128*/        if(flag)
/* 129*/            root.popInjectee();
/* 129*/        throw obj;
            }

            private static boolean isEquals(Method method)
            {
/* 138*/        if(!method.getName().equals("equals"))
/* 138*/            return false;
/* 139*/        if((method = method.getParameterTypes()) == null || method.length != 1)
/* 140*/            return false;
/* 142*/        return java/lang/Object.equals(method[0]);
            }

            private static final String PROXY_MORE_METHOD_NAME = "__make";
            private final ServiceLocatorImpl locator;
            private final ActiveDescriptor descriptor;
            private final ServiceHandleImpl root;
            private final WeakReference myInjectee;
            private static final String EQUALS_NAME = "equals";
}
