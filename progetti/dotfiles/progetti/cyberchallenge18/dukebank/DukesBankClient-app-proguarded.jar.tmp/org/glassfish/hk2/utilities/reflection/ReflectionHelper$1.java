// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.AnnotatedElement;
import java.security.PrivilegedAction;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

static class val.annotatedGuy
    implements PrivilegedAction
{

            public final Set run()
            {
/* 850*/        return ReflectionHelper.access$000(val$annotatedGuy);
            }

            public final volatile Object run()
            {
/* 846*/        return run();
            }

            final AnnotatedElement val$annotatedGuy;

            (AnnotatedElement annotatedelement)
            {
/* 846*/        val$annotatedGuy = annotatedelement;
/* 846*/        super();
            }
}
