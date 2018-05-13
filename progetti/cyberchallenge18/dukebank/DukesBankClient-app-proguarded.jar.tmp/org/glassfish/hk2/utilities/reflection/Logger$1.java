// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Logger.java

package org.glassfish.hk2.utilities.reflection;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            Logger

static class 
    implements PrivilegedAction
{

            public final Boolean run()
            {
/*  57*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.logger.debugToStdout", "false")));
            }

            public final volatile Object run()
            {
/*  54*/        return run();
            }

            ()
            {
            }
}
