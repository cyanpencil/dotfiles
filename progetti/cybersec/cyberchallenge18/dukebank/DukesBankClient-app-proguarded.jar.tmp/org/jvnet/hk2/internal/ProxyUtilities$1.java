// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyUtilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            ProxyUtilities

class val.superclass
    implements PrivilegedAction
{

            public ClassLoader run()
            {
                ClassLoader classloader;
/*  95*/        if((classloader = val$superclass.getClassLoader()) == null)
/*  96*/            classloader = ClassLoader.getSystemClassLoader();
/*  97*/        if(classloader == null)
/*  98*/            throw new IllegalStateException((new StringBuilder("Could not find system classloader or classloader of ")).append(val$superclass.getName()).toString());
/* 101*/        else
/* 101*/            return classloader;
            }

            public volatile Object run()
            {
/*  91*/        return run();
            }

            final Class val$superclass;
            final ProxyUtilities this$0;

            ()
            {
/*  91*/        this$0 = final_proxyutilities;
/*  91*/        val$superclass = Class.this;
/*  91*/        super();
            }
}
