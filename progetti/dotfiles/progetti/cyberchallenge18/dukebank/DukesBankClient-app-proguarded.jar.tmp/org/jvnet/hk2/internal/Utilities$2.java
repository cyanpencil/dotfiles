// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import java.security.PrivilegedAction;

// Referenced classes of package org.jvnet.hk2.internal:
//            Utilities

static class val.result
    implements PrivilegedAction
{

            public final Object run()
            {
/* 199*/        val$result.setAccessible(true);
/* 200*/        return null;
            }

            final Constructor val$result;

            (Constructor constructor)
            {
/* 195*/        val$result = constructor;
/* 195*/        super();
            }
}
