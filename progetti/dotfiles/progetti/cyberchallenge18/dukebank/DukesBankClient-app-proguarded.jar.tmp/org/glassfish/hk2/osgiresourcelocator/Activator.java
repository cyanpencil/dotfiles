// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Activator.java

package org.glassfish.hk2.osgiresourcelocator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ResourceFinder, ResourceFinderImpl, ServiceLoader, ServiceLoaderImpl

public class Activator
    implements BundleActivator
{

            public Activator()
            {
            }

            public void start(BundleContext bundlecontext)
                throws Exception
            {
/*  51*/        (bundlecontext = new ServiceLoaderImpl()).trackBundles();
/*  53*/        ServiceLoader.initialize(bundlecontext);
/*  54*/        ResourceFinder.initialize(bundlecontext = new ResourceFinderImpl());
            }

            public void stop(BundleContext bundlecontext)
                throws Exception
            {
/*  59*/        ServiceLoader.reset();
            }
}
