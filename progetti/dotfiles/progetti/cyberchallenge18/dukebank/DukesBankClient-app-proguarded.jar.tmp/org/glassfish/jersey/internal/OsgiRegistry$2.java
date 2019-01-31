// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.security.PrivilegedAction;
import java.util.Enumeration;
import org.osgi.framework.Bundle;

// Referenced classes of package org.glassfish.jersey.internal:
//            OsgiRegistry

static class val.recursive
    implements PrivilegedAction
{

            public final Enumeration run()
            {
/* 622*/        return val$bundle.findEntries(val$path, val$fileNamePattern, val$recursive);
            }

            public final volatile Object run()
            {
/* 618*/        return run();
            }

            final Bundle val$bundle;
            final String val$path;
            final String val$fileNamePattern;
            final boolean val$recursive;

            (Bundle bundle1, String s, String s1, boolean flag)
            {
/* 618*/        val$bundle = bundle1;
/* 618*/        val$path = s;
/* 618*/        val$fileNamePattern = s1;
/* 618*/        val$recursive = flag;
/* 618*/        super();
            }
}
