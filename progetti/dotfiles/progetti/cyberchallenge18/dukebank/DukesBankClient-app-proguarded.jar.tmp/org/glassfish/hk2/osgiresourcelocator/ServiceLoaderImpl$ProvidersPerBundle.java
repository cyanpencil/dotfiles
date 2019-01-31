// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.security.PrivilegedAction;
import java.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoaderImpl

static class <init>
{

            public long getBundleId()
            {
/* 315*/        return bundleId;
            }

            public void put(String s, List list)
            {
/* 319*/        serviceToProvidersMap.put(s, list);
            }

            public Map getServiceToProvidersMap()
            {
/* 323*/        return serviceToProvidersMap;
            }

            private long bundleId;
            Map serviceToProvidersMap;

            private (long l)
            {
/* 308*/        serviceToProvidersMap = new HashMap();
/* 311*/        bundleId = l;
            }

            bundleId(long l, bundleId bundleid)
            {
/* 306*/        this(l);
            }

            // Unreferenced inner class org/glassfish/hk2/osgiresourcelocator/ServiceLoaderImpl$1

/* anonymous class */
    class ServiceLoaderImpl._cls1
        implements PrivilegedAction
    {

                public BundleContext run()
                {
/*  83*/            return bundle.getBundleContext();
                }

                public volatile Object run()
                {
/*  81*/            return run();
                }

                final Bundle val$bundle;
                final ServiceLoaderImpl this$0;

                    
                    {
/*  81*/                this$0 = final_serviceloaderimpl;
/*  81*/                bundle = Bundle.this;
/*  81*/                super();
                    }
    }

}
