// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedExceptionAction;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.name
    implements PrivilegedExceptionAction
{

            public final Class run()
                throws ClassNotFoundException
            {
/* 401*/        if(val$cl == null)
/* 403*/            break MISSING_BLOCK_LABEL_21;
/* 403*/        return Class.forName(val$name, false, val$cl);
/* 404*/        JVM INSTR pop ;
/* 408*/        return Class.forName(val$name);
            }

            public final volatile Object run()
                throws Exception
            {
/* 398*/        return run();
            }

            final ClassLoader val$cl;
            final String val$name;

            (ClassLoader classloader, String s)
            {
/* 398*/        val$cl = classloader;
/* 398*/        val$name = s;
/* 398*/        super();
            }
}
