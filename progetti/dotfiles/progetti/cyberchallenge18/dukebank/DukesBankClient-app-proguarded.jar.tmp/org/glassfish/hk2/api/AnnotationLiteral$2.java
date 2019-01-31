// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationLiteral.java

package org.glassfish.hk2.api;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.api:
//            AnnotationLiteral

static class val.ao
    implements PrivilegedAction
{

            public final Object run()
            {
/* 294*/        val$ao.setAccessible(true);
/* 295*/        return null;
            }

            final AccessibleObject val$ao;

            (AccessibleObject accessibleobject)
            {
/* 290*/        val$ao = accessibleobject;
/* 290*/        super();
            }
}
