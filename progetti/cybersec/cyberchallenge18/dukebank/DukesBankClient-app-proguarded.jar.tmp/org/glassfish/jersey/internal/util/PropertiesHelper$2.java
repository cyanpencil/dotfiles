// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertiesHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            PropertiesHelper

static class val.name
    implements PrivilegedAction
{

            public final String run()
            {
/*  97*/        return System.getProperty(val$name);
            }

            public final volatile Object run()
            {
/*  94*/        return run();
            }

            final String val$name;

            (String s)
            {
/*  94*/        val$name = s;
/*  94*/        super();
            }
}
