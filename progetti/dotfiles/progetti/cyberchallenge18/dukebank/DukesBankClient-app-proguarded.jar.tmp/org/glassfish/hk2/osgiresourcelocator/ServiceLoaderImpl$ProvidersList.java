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

static class allProviders
{

            void addProviders(ndle ndle)
            {
/* 336*/        long l = ndle.getBundleId();
                ndle ndle1;
/* 338*/        for(Iterator iterator = getAllProviders().iterator(); iterator.hasNext();)
/* 340*/            if((ndle1 = (ndle)iterator.next()).getBundleId() > l)
                    {
/* 342*/                getAllProviders().add(0, ndle);
/* 343*/                return;
                    }

/* 346*/        getAllProviders().add(ndle);
            }

            void removeProviders(long l)
            {
                ndle ndle;
/* 350*/        for(Iterator iterator = getAllProviders().iterator(); iterator.hasNext();)
/* 352*/            if((ndle = (ndle)iterator.next()).getBundleId() == l)
                    {
/* 354*/                iterator.remove();
/* 355*/                return;
                    }

            }

            public List getAllProviders()
            {
/* 364*/        return allProviders;
            }

            private List allProviders;

            private ndle()
            {
/* 333*/        allProviders = new LinkedList();
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
