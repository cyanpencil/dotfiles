// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.name
    implements PrivilegedAction
{

            public final Class run()
            {
/* 256*/        if(val$cl == null)
/* 258*/            break MISSING_BLOCK_LABEL_85;
/* 258*/        return Class.forName(val$name, false, val$cl);
                ClassNotFoundException classnotfoundexception;
/* 259*/        classnotfoundexception;
/* 260*/        if(ReflectionHelper.access$000().isLoggable(Level.FINER))
/* 261*/            ReflectionHelper.access$000().log(Level.FINER, (new StringBuilder("Unable to load class ")).append(val$name).append(" using the supplied class loader ").append(val$cl.getClass().getName()).append(".").toString(), classnotfoundexception);
/* 268*/        return Class.forName(val$name);
/* 269*/        classnotfoundexception;
/* 270*/        if(ReflectionHelper.access$000().isLoggable(Level.FINER))
/* 271*/            ReflectionHelper.access$000().log(Level.FINER, (new StringBuilder("Unable to load class ")).append(val$name).append(" using the current class loader.").toString(), classnotfoundexception);
/* 275*/        return null;
            }

            public final volatile Object run()
            {
/* 253*/        return run();
            }

            final ClassLoader val$cl;
            final String val$name;

            (ClassLoader classloader, String s)
            {
/* 253*/        val$cl = classloader;
/* 253*/        val$name = s;
/* 253*/        super();
            }
}
