// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoaderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.security.PrivilegedExceptionAction;
import org.osgi.framework.Bundle;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ServiceLoaderImpl

class val.name
    implements PrivilegedExceptionAction
{

            public Class run()
                throws ClassNotFoundException
            {
/* 165*/        return val$bundle.loadClass(val$name);
            }

            public volatile Object run()
                throws Exception
            {
/* 163*/        return run();
            }

            final Bundle val$bundle;
            final String val$name;
            final ServiceLoaderImpl this$0;

            ()
            {
/* 163*/        this$0 = final_serviceloaderimpl;
/* 163*/        val$bundle = bundle1;
/* 163*/        val$name = String.this;
/* 163*/        super();
            }
}
