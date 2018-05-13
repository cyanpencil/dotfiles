// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorActionImpl.java

package org.jvnet.hk2.internal;

import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.ProxyFactory;
import org.glassfish.hk2.api.AOPProxyCtl;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.jvnet.hk2.internal:
//            ClazzCreator, ConstructorAction, MethodInterceptorHandler

final class ConstructorActionImpl
    implements ConstructorAction
{

            ConstructorActionImpl(ClazzCreator clazzcreator, Map map)
            {
/*  91*/        clazzCreator = clazzcreator;
/*  92*/        methodInterceptors = map;
            }

            public final Object makeMe(final Constructor c, final Object args[], final boolean neutralCCL)
                throws Throwable
            {
/*  98*/        final MethodInterceptorHandler methodInterceptor = new MethodInterceptorHandler(clazzCreator.getServiceLocator(), clazzCreator.getUnderlyingDescriptor(), methodInterceptors);
                final ProxyFactory proxyFactory;
/* 103*/        (proxyFactory = new ProxyFactory()).setSuperclass(clazzCreator.getImplClass());
/* 105*/        proxyFactory.setFilter(METHOD_FILTER);
/* 106*/        proxyFactory.setInterfaces(ADDED_INTERFACES);
/* 108*/        return AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public Object run()
                        throws Exception
                    {
                        ClassLoader classloader;
/* 112*/                classloader = null;
/* 113*/                if(neutralCCL)
/* 114*/                    classloader = Thread.currentThread().getContextClassLoader();
/* 118*/                Object obj = proxyFactory.create(c.getParameterTypes(), args, methodInterceptor);
/* 129*/                if(neutralCCL)
/* 130*/                    Thread.currentThread().setContextClassLoader(classloader);
/* 130*/                return obj;
/* 120*/                JVM INSTR dup ;
/* 121*/                obj;
/* 121*/                getTargetException();
/* 121*/                obj;
/* 122*/                Logger.getLogger().debug(c.getDeclaringClass().getName(), c.getName(), ((Throwable) (obj)));
/* 123*/                if(obj instanceof Exception)
/* 124*/                    throw (Exception)obj;
/* 126*/                else
/* 126*/                    throw new RuntimeException(((Throwable) (obj)));
                        Exception exception;
/* 129*/                exception;
/* 129*/                if(neutralCCL)
/* 130*/                    Thread.currentThread().setContextClassLoader(classloader);
/* 130*/                throw exception;
                    }

                    final boolean val$neutralCCL;
                    final ProxyFactory val$proxyFactory;
                    final Constructor val$c;
                    final Object val$args[];
                    final MethodInterceptorHandler val$methodInterceptor;
                    final ConstructorActionImpl this$0;

                    
                    {
/* 108*/                this$0 = ConstructorActionImpl.this;
/* 108*/                neutralCCL = flag;
/* 108*/                proxyFactory = proxyfactory;
/* 108*/                c = constructor;
/* 108*/                args = aobj;
/* 108*/                methodInterceptor = methodinterceptorhandler;
/* 108*/                super();
                    }
        });
            }

            private static final Class ADDED_INTERFACES[] = {
/*  62*/        org/glassfish/hk2/api/AOPProxyCtl
            };
            private static final MethodFilter METHOD_FILTER = new MethodFilter() {

                public final boolean isHandled(Method method)
                {
/*  68*/            return !method.getName().equals("finalize");
                }

    };
            private final ClazzCreator clazzCreator;
            private final Map methodInterceptors;

}
