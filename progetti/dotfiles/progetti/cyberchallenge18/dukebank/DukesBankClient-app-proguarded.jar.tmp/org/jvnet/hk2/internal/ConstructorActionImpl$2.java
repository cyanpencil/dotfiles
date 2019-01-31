// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorActionImpl.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.PrivilegedExceptionAction;
import javassist.util.proxy.ProxyFactory;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.jvnet.hk2.internal:
//            ConstructorActionImpl, MethodInterceptorHandler

class 
    implements PrivilegedExceptionAction
{

            public Object run()
                throws Exception
            {
                ClassLoader classloader;
/* 112*/        classloader = null;
/* 113*/        if(val$neutralCCL)
/* 114*/            classloader = Thread.currentThread().getContextClassLoader();
/* 118*/        Object obj = val$proxyFactory.create(val$c.getParameterTypes(), val$args, val$methodInterceptor);
/* 129*/        if(val$neutralCCL)
/* 130*/            Thread.currentThread().setContextClassLoader(classloader);
/* 130*/        return obj;
/* 120*/        JVM INSTR dup ;
/* 121*/        obj;
/* 121*/        getTargetException();
/* 121*/        obj;
/* 122*/        Logger.getLogger().debug(val$c.getDeclaringClass().getName(), val$c.getName(), ((Throwable) (obj)));
/* 123*/        if(obj instanceof Exception)
/* 124*/            throw (Exception)obj;
/* 126*/        else
/* 126*/            throw new RuntimeException(((Throwable) (obj)));
                Exception exception;
/* 129*/        exception;
/* 129*/        if(val$neutralCCL)
/* 130*/            Thread.currentThread().setContextClassLoader(classloader);
/* 130*/        throw exception;
            }

            final boolean val$neutralCCL;
            final ProxyFactory val$proxyFactory;
            final Constructor val$c;
            final Object val$args[];
            final MethodInterceptorHandler val$methodInterceptor;
            final ConstructorActionImpl this$0;

            ()
            {
/* 108*/        this$0 = final_constructoractionimpl;
/* 108*/        val$neutralCCL = flag;
/* 108*/        val$proxyFactory = proxyfactory;
/* 108*/        val$c = constructor;
/* 108*/        val$args = aobj;
/* 108*/        val$methodInterceptor = MethodInterceptorHandler.this;
/* 108*/        super();
            }
}
