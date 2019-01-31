// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            Utilities

static class val.clazz
    implements PrivilegedAction
{

            public final Constructor[] run()
            {
/*1310*/        return val$clazz.getDeclaredConstructors();
            }

            public final volatile Object run()
            {
/*1306*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/*1306*/        val$clazz = class1;
/*1306*/        super();
            }
}
