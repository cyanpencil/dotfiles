// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import javassist.util.proxy.*;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            DelegatingClassLoader, MethodInterceptorImpl, ServiceHandleImpl, ServiceLocatorImpl, 
//            Utilities, MethodInterceptorInvocationHandler

public class ProxyUtilities
{

            public ProxyUtilities()
            {
            }

            private Object secureCreate(final Class superclass, final Class interfaces[], final MethodHandler callback, boolean flag, final ServiceLocator loader)
            {
/*  91*/        loader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                    public ClassLoader run()
                    {
                        ClassLoader classloader;
/*  95*/                if((classloader = superclass.getClassLoader()) == null)
/*  96*/                    classloader = ClassLoader.getSystemClassLoader();
/*  97*/                if(classloader == null)
/*  98*/                    throw new IllegalStateException((new StringBuilder("Could not find system classloader or classloader of ")).append(superclass.getName()).toString());
/* 101*/                else
/* 101*/                    return classloader;
                    }

                    public volatile Object run()
                    {
/*  91*/                return run();
                    }

                    final Class val$superclass;
                    final ProxyUtilities this$0;

                    
                    {
/*  91*/                this$0 = ProxyUtilities.this;
/*  91*/                superclass = class1;
/*  91*/                super();
                    }
        });
                DelegatingClassLoader delegatingclassloader;
/* 107*/        synchronized(superClassToDelegator)
                {
/* 108*/            if((delegatingclassloader = (DelegatingClassLoader)superClassToDelegator.get(loader)) == null)
                    {
/* 110*/                delegatingclassloader = (DelegatingClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                            public DelegatingClassLoader run()
                            {
/* 114*/                        return new DelegatingClassLoader(loader, new ClassLoader[] {
/* 114*/                            javassist/util/proxy/ProxyFactory.getClassLoader(), org/glassfish/hk2/api/ProxyCtl.getClassLoader()
                                });
                            }

                            public volatile Object run()
                            {
/* 110*/                        return run();
                            }

                            final ClassLoader val$loader;
                            final ProxyUtilities this$0;

                    
                    {
/* 110*/                this$0 = ProxyUtilities.this;
/* 110*/                loader = classloader;
/* 110*/                super();
                    }
                });
/* 122*/                superClassToDelegator.put(loader, delegatingclassloader);
                    }
                }
/* 126*/        final DelegatingClassLoader delegatingLoader = delegatingclassloader;
/* 128*/        if(flag)
/* 129*/            return AccessController.doPrivileged(new PrivilegedAction() {

                        public Object run()
                        {
/* 134*/                    return Proxy.newProxyInstance(delegatingLoader, interfaces, new MethodInterceptorInvocationHandler(callback));
                        }

                        final DelegatingClassLoader val$delegatingLoader;
                        final Class val$interfaces[];
                        final MethodHandler val$callback;
                        final ProxyUtilities this$0;

                    
                    {
/* 129*/                this$0 = ProxyUtilities.this;
/* 129*/                delegatingLoader = delegatingclassloader;
/* 129*/                interfaces = aclass;
/* 129*/                callback = methodhandler;
/* 129*/                super();
                    }
            });
/* 142*/        else
/* 142*/            return AccessController.doPrivileged(new PrivilegedAction() {

                        public Object run()
                        {
/* 147*/                    Object obj = ProxyUtilities.proxyCreationLock;
/* 147*/                    JVM INSTR monitorenter ;
                            javassist.util.proxy.ProxyFactory.ClassLoaderProvider classloaderprovider;
/* 148*/                    classloaderprovider = ProxyFactory.classLoaderProvider;
/* 149*/                    ProxyFactory.classLoaderProvider = new javassist.util.proxy.ProxyFactory.ClassLoaderProvider() {

                                public ClassLoader get(ProxyFactory proxyfactory)
                                {
/* 153*/                            return delegatingLoader;
                                }

                                final _cls4 this$1;

                            
                            {
/* 149*/                        this$1 = _cls4.this;
/* 149*/                        super();
                            }
                    };
                            Object obj1;
/* 158*/                    ((ProxyFactory) (obj1 = new ProxyFactory())).setInterfaces(interfaces);
/* 160*/                    ((ProxyFactory) (obj1)).setSuperclass(superclass);
/* 162*/                    obj1 = ((ProxyFactory) (obj1)).createClass();
/* 165*/                    try
                            {
/* 165*/                        ((ProxyObject)(obj1 = ((Class) (obj1)).newInstance())).setHandler(callback);
/* 169*/                        obj1 = obj1;
                            }
/* 170*/                    catch(Exception exception1)
                            {
/* 171*/                        throw new RuntimeException(exception1);
                            }
/* 175*/                    ProxyFactory.classLoaderProvider = classloaderprovider;
/* 175*/                    return obj1;
                            Exception exception2;
/* 175*/                    exception2;
/* 175*/                    ProxyFactory.classLoaderProvider = classloaderprovider;
/* 175*/                    throw exception2;
                            Exception exception;
/* 177*/                    exception;
/* 177*/                    throw exception;
                        }

                        final DelegatingClassLoader val$delegatingLoader;
                        final Class val$interfaces[];
                        final Class val$superclass;
                        final MethodHandler val$callback;
                        final ProxyUtilities this$0;

                    
                    {
/* 142*/                this$0 = ProxyUtilities.this;
/* 142*/                delegatingLoader = delegatingclassloader;
/* 142*/                interfaces = aclass;
/* 142*/                superclass = class1;
/* 142*/                callback = methodhandler;
/* 142*/                super();
                    }
            });
            }

            public Object generateProxy(Class class1, ServiceLocatorImpl servicelocatorimpl, ActiveDescriptor activedescriptor, ServiceHandleImpl servicehandleimpl, Injectee injectee)
            {
                boolean flag;
                Class aclass[];
/* 190*/        if(flag = class1 != null ? class1.isInterface() : false)
                {
/* 195*/            class1 = class1;
/* 196*/            (aclass = new Class[2])[0] = class1;
/* 198*/            aclass[1] = org/glassfish/hk2/api/ProxyCtl;
                } else
                {
/* 201*/            class1 = Utilities.getFactoryAwareImplementationClass(activedescriptor);
/* 203*/            aclass = Utilities.getInterfacesForProxy(activedescriptor.getContractTypes());
                }
/* 208*/        try
                {
/* 208*/            servicelocatorimpl = ((ServiceLocatorImpl) (secureCreate(class1, aclass, new MethodInterceptorImpl(servicelocatorimpl, activedescriptor, servicehandleimpl, injectee), flag, servicelocatorimpl)));
                }
                // Misplaced declaration of an exception variable
/* 213*/        catch(ServiceLocatorImpl servicelocatorimpl)
                {
/* 214*/            class1 = new IllegalArgumentException((new StringBuilder("While attempting to create a Proxy for ")).append(class1.getName()).append(" in scope ").append(activedescriptor.getScope()).append(" an error occured while creating the proxy").toString());
/* 217*/            if(servicelocatorimpl instanceof MultiException)
                    {
/* 218*/                (servicelocatorimpl = (MultiException)servicelocatorimpl).addError(class1);
/* 222*/                throw servicelocatorimpl;
                    } else
                    {
/* 225*/                (servicelocatorimpl = new MultiException(servicelocatorimpl)).addError(class1);
/* 227*/                throw servicelocatorimpl;
                    }
                }
/* 230*/        return servicelocatorimpl;
            }

            public void releaseCache()
            {
/* 234*/        synchronized(superClassToDelegator)
                {
/* 235*/            superClassToDelegator.clear();
                }
            }

            private static final Object proxyCreationLock = new Object();
            private final HashMap superClassToDelegator = new HashMap();


}
