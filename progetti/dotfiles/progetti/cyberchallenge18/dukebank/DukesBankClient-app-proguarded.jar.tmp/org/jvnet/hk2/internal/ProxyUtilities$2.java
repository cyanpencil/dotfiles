// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import javassist.util.proxy.ProxyFactory;
import org.glassfish.hk2.api.ProxyCtl;

// Referenced classes of package org.jvnet.hk2.internal:
//            DelegatingClassLoader, ProxyUtilities

class val.loader
    implements PrivilegedAction
{

            public DelegatingClassLoader run()
            {
/* 114*/        return new DelegatingClassLoader(val$loader, new ClassLoader[] {
/* 114*/            javassist/util/proxy/ProxyFactory.getClassLoader(), org/glassfish/hk2/api/ProxyCtl.getClassLoader()
                });
            }

            public volatile Object run()
            {
/* 110*/        return run();
            }

            final ClassLoader val$loader;
            final ProxyUtilities this$0;

            ader()
            {
/* 110*/        this$0 = final_proxyutilities;
/* 110*/        val$loader = ClassLoader.this;
/* 110*/        super();
            }
}
