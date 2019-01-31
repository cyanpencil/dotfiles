// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperUtilities.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperUtilities, MethodWrapperImpl

static class 
    implements PrivilegedAction
{

            public final Set run()
            {
/*  72*/        HashSet hashset = new HashSet();
                java.lang.reflect.Method amethod[];
/*  74*/        int i = (amethod = java/lang/Object.getDeclaredMethods()).length;
/*  74*/        for(int j = 0; j < i; j++)
                {
/*  74*/            java.lang.reflect.Method method = amethod[j];
/*  75*/            hashset.add(new MethodWrapperImpl(method));
                }

/*  78*/        return hashset;
            }

            public final volatile Object run()
            {
/*  68*/        return run();
            }

            ()
            {
            }
}
