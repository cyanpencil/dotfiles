// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationLiteral.java

package org.glassfish.hk2.api;

import java.lang.reflect.Method;
import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.api:
//            AnnotationLiteral

class this._cls0
    implements PrivilegedAction
{

            public Method[] run()
            {
/* 112*/        return annotationType().getDeclaredMethods();
            }

            public volatile Object run()
            {
/* 108*/        return run();
            }

            final AnnotationLiteral this$0;

            ()
            {
/* 108*/        this$0 = AnnotationLiteral.this;
/* 108*/        super();
            }
}
