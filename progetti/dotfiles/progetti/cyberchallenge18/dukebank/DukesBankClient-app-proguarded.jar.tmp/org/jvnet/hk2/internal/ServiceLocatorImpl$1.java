// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

static class 
    implements PrivilegedAction
{

            public final String run()
            {
/* 127*/        return System.getProperty("org.jvnet.hk2.properties.bind.tracing.pattern");
            }

            public final volatile Object run()
            {
/* 124*/        return run();
            }

            ()
            {
            }
}
