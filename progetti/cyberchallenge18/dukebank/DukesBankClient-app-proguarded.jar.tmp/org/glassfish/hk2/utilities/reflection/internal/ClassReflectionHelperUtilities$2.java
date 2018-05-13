// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperUtilities.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            ClassReflectionHelperUtilities

static class 
    implements PrivilegedAction
{

            public final Set run()
            {
/*  90*/        HashSet hashset = new HashSet();
                java.lang.reflect.Field afield[];
/*  92*/        int i = (afield = java/lang/Object.getDeclaredFields()).length;
/*  92*/        for(int j = 0; j < i; j++)
                {
/*  92*/            java.lang.reflect.Field field = afield[j];
/*  93*/            hashset.add(field);
                }

/*  96*/        return hashset;
            }

            public final volatile Object run()
            {
/*  86*/        return run();
            }

            ()
            {
            }
}
