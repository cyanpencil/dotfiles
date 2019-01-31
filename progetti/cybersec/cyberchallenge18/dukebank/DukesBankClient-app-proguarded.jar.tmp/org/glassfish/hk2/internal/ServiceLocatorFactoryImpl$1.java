// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorFactoryImpl.java

package org.glassfish.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.internal:
//            ServiceLocatorFactoryImpl

static class 
    implements PrivilegedAction
{

            public final Boolean run()
            {
/*  70*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.service.locator.lifecycle", "false")));
            }

            public final volatile Object run()
            {
/*  67*/        return run();
            }

            ()
            {
            }
}
