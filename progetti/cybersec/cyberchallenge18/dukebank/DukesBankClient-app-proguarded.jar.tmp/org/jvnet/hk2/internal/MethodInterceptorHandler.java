// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInterceptorHandler.java

package org.jvnet.hk2.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javassist.util.proxy.MethodHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.HK2Invocation;
import org.glassfish.hk2.utilities.reflection.Logger;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

public class MethodInterceptorHandler
    implements MethodHandler
{
    class MethodInvocationImpl
        implements MethodInvocation, HK2Invocation
    {

                public Object[] getArguments()
                {
/* 160*/            return arguments;
                }

                public AccessibleObject getStaticPart()
                {
/* 165*/            return method;
                }

                public Object getThis()
                {
/* 170*/            return myself;
                }

                public Method getMethod()
                {
/* 175*/            return method;
                }

                public Object proceed()
                    throws Throwable
                {
                    int i;
                    long l;
/* 180*/            if((i = index + 1) < interceptors.size())
/* 182*/                break MISSING_BLOCK_LABEL_169;
/* 182*/            l = 0L;
/* 183*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
/* 184*/                l = System.currentTimeMillis();
/* 188*/            Object obj = ReflectionHelper.invoke(myself, proceed, arguments, locator.getNeutralContextClassLoader());
/* 191*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
                    {
/* 192*/                l = System.currentTimeMillis() - l;
/* 194*/                Logger.getLogger().debug((new StringBuilder("Time to call actual intercepted method ")).append(method).append(" is ").append(l).append(" milliseconds").toString());
                    }
/* 194*/            return obj;
                    Exception exception;
/* 191*/            exception;
/* 191*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
                    {
/* 192*/                l = System.currentTimeMillis() - l;
/* 194*/                Logger.getLogger().debug((new StringBuilder("Time to call actual intercepted method ")).append(method).append(" is ").append(l).append(" milliseconds").toString());
                    }
/* 194*/            throw exception;
                    MethodInterceptor methodinterceptor;
                    long l1;
/* 200*/            methodinterceptor = (MethodInterceptor)interceptors.get(i);
/* 202*/            l1 = 0L;
/* 203*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
                    {
/* 204*/                l1 = System.currentTimeMillis();
/* 205*/                Logger.getLogger().debug((new StringBuilder("Invoking interceptor ")).append(methodinterceptor.getClass().getName()).append(" index ").append(i).append(" in stack of ").append(interceptors.size()).append(" of method ").append(method).toString());
                    }
/* 211*/            Object obj1 = methodinterceptor.invoke(new MethodInvocationImpl(arguments, method, myself, interceptors, i, proceed, userData));
/* 215*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
                    {
/* 216*/                l1 = System.currentTimeMillis() - l1;
/* 217*/                Logger.getLogger().debug((new StringBuilder("Interceptor ")).append(methodinterceptor.getClass().getName()).append(" index ").append(i).append(" took an aggregate of ").append(l1).append(" milliseconds").toString());
                    }
/* 217*/            return obj1;
/* 215*/            obj1;
/* 215*/            if(MethodInterceptorHandler.DEBUG_INTERCEPTION)
                    {
/* 216*/                l1 = System.currentTimeMillis() - l1;
/* 217*/                Logger.getLogger().debug((new StringBuilder("Interceptor ")).append(methodinterceptor.getClass().getName()).append(" index ").append(i).append(" took an aggregate of ").append(l1).append(" milliseconds").toString());
                    }
/* 217*/            throw obj1;
                }

                public void setUserData(String s, Object obj)
                {
/* 229*/            if(s == null)
/* 229*/                throw new IllegalArgumentException();
/* 231*/            if(userData == null)
/* 231*/                userData = new HashMap();
/* 233*/            if(obj == null)
                    {
/* 234*/                userData.remove(s);
/* 234*/                return;
                    } else
                    {
/* 237*/                userData.put(s, obj);
/* 239*/                return;
                    }
                }

                public Object getUserData(String s)
                {
/* 246*/            if(s == null)
/* 246*/                throw new IllegalArgumentException();
/* 248*/            if(userData == null)
/* 248*/                return null;
/* 249*/            else
/* 249*/                return userData.get(s);
                }

                private final Object arguments[];
                private final Method method;
                private final Object myself;
                private final List interceptors;
                private final int index;
                private final Method proceed;
                private HashMap userData;
                final MethodInterceptorHandler this$0;

                private MethodInvocationImpl(Object aobj[], Method method1, Object obj, List list, int i, Method method2, 
                        HashMap hashmap)
                {
/* 148*/            this$0 = MethodInterceptorHandler.this;
/* 148*/            super();
/* 149*/            arguments = aobj;
/* 150*/            method = method1;
/* 151*/            myself = obj;
/* 152*/            interceptors = list;
/* 153*/            index = i;
/* 154*/            proceed = method2;
/* 155*/            userData = hashmap;
                }

    }


            MethodInterceptorHandler(ServiceLocatorImpl servicelocatorimpl, ActiveDescriptor activedescriptor, Map map)
            {
/*  86*/        locator = servicelocatorimpl;
/*  87*/        interceptorLists = map;
/*  88*/        underlyingDescriptor = activedescriptor;
            }

            public Object invoke(Object obj, Method method, Method method1, Object aobj[])
                throws Throwable
            {
                Object obj1;
                MethodInterceptor methodinterceptor;
                long l;
/*  97*/        if(method.getName().equals("__getUnderlyingDescriptor"))
/*  98*/            return underlyingDescriptor;
/* 101*/        if((obj1 = (List)interceptorLists.get(method)) == null || ((List) (obj1)).isEmpty())
/* 103*/            return ReflectionHelper.invoke(obj, method1, aobj, locator.getNeutralContextClassLoader());
/* 106*/        if(!(obj1 instanceof RandomAccess))
/* 108*/            obj1 = new ArrayList(((java.util.Collection) (obj1)));
/* 111*/        methodinterceptor = (MethodInterceptor)((List) (obj1)).get(0);
/* 113*/        l = 0L;
/* 114*/        if(DEBUG_INTERCEPTION)
                {
/* 115*/            l = System.currentTimeMillis();
/* 116*/            Logger.getLogger().debug((new StringBuilder("Invoking interceptor ")).append(methodinterceptor.getClass().getName()).append(" index 0 in stack of ").append(((List) (obj1)).size()).append(" of method ").append(method).toString());
                }
/* 121*/        obj = methodinterceptor.invoke(new MethodInvocationImpl(aobj, method, obj, ((List) (obj1)), 0, method1, null));
/* 125*/        if(DEBUG_INTERCEPTION)
                {
/* 126*/            l = System.currentTimeMillis() - l;
/* 127*/            Logger.getLogger().debug((new StringBuilder("Interceptor ")).append(methodinterceptor.getClass().getName()).append(" index 0 took an aggregate of ").append(l).append(" milliseconds").toString());
                }
/* 127*/        return obj;
/* 125*/        obj;
/* 125*/        if(DEBUG_INTERCEPTION)
                {
/* 126*/            l = System.currentTimeMillis() - l;
/* 127*/            Logger.getLogger().debug((new StringBuilder("Interceptor ")).append(methodinterceptor.getClass().getName()).append(" index 0 took an aggregate of ").append(l).append(" milliseconds").toString());
                }
/* 127*/        throw obj;
            }

            private static final boolean DEBUG_INTERCEPTION = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  73*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.tracing.interceptors", "false")));
                }

                public final volatile Object run()
                {
/*  70*/            return run();
                }

    })).booleanValue();
            private final ServiceLocatorImpl locator;
            private final Map interceptorLists;
            private final ActiveDescriptor underlyingDescriptor;



}
