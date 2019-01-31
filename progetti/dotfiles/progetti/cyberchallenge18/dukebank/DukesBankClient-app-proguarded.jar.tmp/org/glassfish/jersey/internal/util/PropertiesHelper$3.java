// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertiesHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            PropertiesHelper

static class val.def
    implements PrivilegedAction
{

            public final String run()
            {
/* 117*/        return System.getProperty(val$name, val$def);
            }

            public final volatile Object run()
            {
/* 114*/        return run();
            }

            final String val$name;
            final String val$def;

            (String s, String s1)
            {
/* 114*/        val$name = s;
/* 114*/        val$def = s1;
/* 114*/        super();
            }
}
