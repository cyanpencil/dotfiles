// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.security.PrivilegedAction;
import org.osgi.framework.*;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoaderImpl

class this._cls0
    implements BundleListener
{

            public void bundleChanged(BundleEvent bundleevent)
            {
/* 249*/        Bundle bundle = bundleevent.getBundle();
/* 250*/        switch(bundleevent.getType())
                {
/* 252*/        case 1: // '\001'
/* 252*/            ServiceLoaderImpl.access$300(ServiceLoaderImpl.this, bundle);
/* 253*/            return;

/* 255*/        case 16: // '\020'
/* 255*/            ServiceLoaderImpl.access$400(ServiceLoaderImpl.this, bundle);
/* 256*/            return;

/* 258*/        case 8: // '\b'
/* 258*/            ServiceLoaderImpl.access$400(ServiceLoaderImpl.this, bundle);
/* 259*/            ServiceLoaderImpl.access$300(ServiceLoaderImpl.this, bundle);
                    break;
                }
            }

            final ServiceLoaderImpl this$0;

            private ()
            {
/* 247*/        this$0 = ServiceLoaderImpl.this;
/* 247*/        super();
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
