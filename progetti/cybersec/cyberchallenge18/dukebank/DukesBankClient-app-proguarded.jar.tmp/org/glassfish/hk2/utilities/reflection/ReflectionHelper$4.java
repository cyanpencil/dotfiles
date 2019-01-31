// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

static class 
    implements PrivilegedAction
{

            public final ClassLoader run()
            {
/*1472*/        return Thread.currentThread().getContextClassLoader();
            }

            public final volatile Object run()
            {
/*1469*/        return run();
            }

            ()
            {
            }
}
