// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            Utilities

static class 
    implements PrivilegedAction
{

            public final Boolean run()
            {
/* 140*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.useSoftReference", "true")));
            }

            public final volatile Object run()
            {
/* 136*/        return run();
            }

            ()
            {
            }
}
