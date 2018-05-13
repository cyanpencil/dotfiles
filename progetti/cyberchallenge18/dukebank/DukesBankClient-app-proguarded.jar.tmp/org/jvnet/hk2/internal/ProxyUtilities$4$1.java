// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import javassist.util.proxy.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ProxyUtilities, DelegatingClassLoader

class this._cls1
    implements javassist.util.proxy.oaderProvider
{

            public ClassLoader get(ProxyFactory proxyfactory)
            {
/* 153*/        return delegatingLoader;
            }

            final l.delegatingLoader this$1;

            l.callback()
            {
/* 149*/        this$1 = this._cls1.this;
/* 149*/        super();
            }

            // Unreferenced inner class org/jvnet/hk2/internal/ProxyUtilities$4

/* anonymous class */
    class ProxyUtilities._cls4
        implements PrivilegedAction
    {

                public Object run()
                {
/* 147*/            Object obj = ProxyUtilities.access$000();
/* 147*/            JVM INSTR monitorenter ;
                    javassist.util.proxy.ProxyFactory.ClassLoaderProvider classloaderprovider;
/* 148*/            classloaderprovider = ProxyFactory.classLoaderProvider;
/* 149*/            ProxyFactory.classLoaderProvider = new ProxyUtilities._cls4._cls1();
                    Object obj1;
/* 158*/            ((ProxyFactory) (obj1 = new ProxyFactory())).setInterfaces(interfaces);
/* 160*/            ((ProxyFactory) (obj1)).setSuperclass(superclass);
/* 162*/            obj1 = ((ProxyFactory) (obj1)).createClass();
/* 165*/            try
                    {
/* 165*/                ((ProxyObject)(obj1 = ((Class) (obj1)).newInstance())).setHandler(callback);
/* 169*/                obj1 = obj1;
                    }
/* 170*/            catch(Exception exception1)
                    {
/* 171*/                throw new RuntimeException(exception1);
                    }
/* 175*/            ProxyFactory.classLoaderProvider = classloaderprovider;
/* 175*/            return obj1;
                    Exception exception2;
/* 175*/            exception2;
/* 175*/            ProxyFactory.classLoaderProvider = classloaderprovider;
/* 175*/            throw exception2;
                    Exception exception;
/* 177*/            exception;
/* 177*/            throw exception;
                }

                final DelegatingClassLoader val$delegatingLoader;
                final Class val$interfaces[];
                final Class val$superclass;
                final MethodHandler val$callback;
                final ProxyUtilities this$0;

                    
                    {
/* 142*/                this$0 = final_proxyutilities;
/* 142*/                delegatingLoader = delegatingclassloader;
/* 142*/                interfaces = aclass;
/* 142*/                superclass = class1;
/* 142*/                callback = MethodHandler.this;
/* 142*/                super();
                    }
    }

}
