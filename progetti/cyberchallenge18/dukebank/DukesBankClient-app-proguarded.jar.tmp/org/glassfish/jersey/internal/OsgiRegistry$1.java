// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.security.PrivilegedExceptionAction;
import org.osgi.framework.Bundle;

// Referenced classes of package org.glassfish.jersey.internal:
//            OsgiRegistry

static class val.className
    implements PrivilegedExceptionAction
{

            public final Class run()
                throws ClassNotFoundException
            {
/* 599*/        return val$bundle.loadClass(val$className);
            }

            public final volatile Object run()
                throws Exception
            {
/* 596*/        return run();
            }

            final Bundle val$bundle;
            final String val$className;

            (Bundle bundle1, String s)
            {
/* 596*/        val$bundle = bundle1;
/* 596*/        val$className = s;
/* 596*/        super();
            }
}
