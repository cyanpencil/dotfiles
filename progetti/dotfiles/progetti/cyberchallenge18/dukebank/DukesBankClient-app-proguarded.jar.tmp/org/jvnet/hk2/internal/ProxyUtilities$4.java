// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import javassist.util.proxy.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ProxyUtilities, DelegatingClassLoader

class val.callback
    implements PrivilegedAction
{

            public Object run()
            {
/* 147*/        Object obj = ProxyUtilities.access$000();
/* 147*/        JVM INSTR monitorenter ;
                javassist.util.proxy.sLoaderProvider sloaderprovider;
/* 148*/        sloaderprovider = ProxyFactory.classLoaderProvider;
/* 149*/        ProxyFactory.classLoaderProvider = new javassist.util.proxy.ProxyFactory.ClassLoaderProvider() {

                    public ClassLoader get(ProxyFactory proxyfactory)
                    {
/* 153*/                return delegatingLoader;
                    }

                    final ProxyUtilities._cls4 this$1;

                    
                    {
/* 149*/                this$1 = ProxyUtilities._cls4.this;
/* 149*/                super();
                    }
        };
                Object obj1;
/* 158*/        ((ProxyFactory) (obj1 = new ProxyFactory())).setInterfaces(val$interfaces);
/* 160*/        ((ProxyFactory) (obj1)).setSuperclass(val$superclass);
/* 162*/        obj1 = ((ProxyFactory) (obj1)).createClass();
/* 165*/        try
                {
/* 165*/            ((ProxyObject)(obj1 = ((Class) (obj1)).newInstance())).setHandler(val$callback);
/* 169*/            obj1 = obj1;
                }
/* 170*/        catch(Exception exception1)
                {
/* 171*/            throw new RuntimeException(exception1);
                }
/* 175*/        ProxyFactory.classLoaderProvider = sloaderprovider;
/* 175*/        return obj1;
                Exception exception2;
/* 175*/        exception2;
/* 175*/        ProxyFactory.classLoaderProvider = sloaderprovider;
/* 175*/        throw exception2;
                Exception exception;
/* 177*/        exception;
/* 177*/        throw exception;
            }

            final DelegatingClassLoader val$delegatingLoader;
            final Class val$interfaces[];
            final Class val$superclass;
            final MethodHandler val$callback;
            final ProxyUtilities this$0;

            ader()
            {
/* 142*/        this$0 = final_proxyutilities;
/* 142*/        val$delegatingLoader = delegatingclassloader;
/* 142*/        val$interfaces = aclass;
/* 142*/        val$superclass = class1;
/* 142*/        val$callback = MethodHandler.this;
/* 142*/        super();
            }
}
