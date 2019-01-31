// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryFinder.java

package javax.ws.rs.ext;

import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package javax.ws.rs.ext:
//            FactoryFinder

static class 
    implements PrivilegedAction
{

            public final ClassLoader run()
            {
/*  76*/        ClassLoader classloader = null;
/*  78*/        try
                {
/*  78*/            classloader = Thread.currentThread().getContextClassLoader();
                }
/*  79*/        catch(SecurityException securityexception)
                {
/*  80*/            FactoryFinder.access$000().log(Level.WARNING, "Unable to get context classloader instance.", securityexception);
                }
/*  85*/        return classloader;
            }

            public final volatile Object run()
            {
/*  72*/        return run();
            }

            ()
            {
            }
}
