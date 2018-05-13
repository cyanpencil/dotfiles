// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataStructures.java

package org.glassfish.jersey.internal.util.collection;

import java.security.PrivilegedExceptionAction;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            DataStructures

static class val.cn
    implements PrivilegedExceptionAction
{

            public final Class run()
                throws Exception
            {
/* 107*/        return org/glassfish/jersey/internal/util/collection/DataStructures.getClassLoader().loadClass(val$cn).newInstance().getClass();
            }

            public final volatile Object run()
                throws Exception
            {
/* 104*/        return run();
            }

            final String val$cn;

            (String s)
            {
/* 104*/        val$cn = s;
/* 104*/        super();
            }
}
