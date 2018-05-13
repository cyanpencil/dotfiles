// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerThreadContext.java

package org.glassfish.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.internal:
//            PerThreadContext

static class 
    implements PrivilegedAction
{

            public final Boolean run()
            {
/*  67*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.hk2.debug.perthreadcontext.log", "false")));
            }

            public final volatile Object run()
            {
/*  63*/        return run();
            }

            ()
            {
            }
}
