// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.security.PrivilegedAction;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoaderImpl

class val.bundle
    implements PrivilegedAction
{

            public BundleContext run()
            {
/*  83*/        return val$bundle.getBundleContext();
            }

            public volatile Object run()
            {
/*  81*/        return run();
            }

            final Bundle val$bundle;
            final ServiceLoaderImpl this$0;

            ()
            {
/*  81*/        this$0 = final_serviceloaderimpl;
/*  81*/        val$bundle = Bundle.this;
/*  81*/        super();
            }
}
