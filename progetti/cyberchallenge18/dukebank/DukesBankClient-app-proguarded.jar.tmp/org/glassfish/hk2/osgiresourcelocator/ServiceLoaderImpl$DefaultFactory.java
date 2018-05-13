// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.security.PrivilegedAction;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoader, ServiceLoaderImpl

static class 
    implements 
{

            public Object make(Class class1, Class class2)
                throws Exception
            {
/* 370*/        if(class2.isAssignableFrom(class1))
/* 371*/            return class1.newInstance();
/* 373*/        else
/* 373*/            return null;
            }

            private ()
            {
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
