// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertiesHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;
import java.util.Properties;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            PropertiesHelper

static class 
    implements PrivilegedAction
{

            public final Properties run()
            {
/*  78*/        return System.getProperties();
            }

            public final volatile Object run()
            {
/*  75*/        return run();
            }

            ()
            {
            }
}
