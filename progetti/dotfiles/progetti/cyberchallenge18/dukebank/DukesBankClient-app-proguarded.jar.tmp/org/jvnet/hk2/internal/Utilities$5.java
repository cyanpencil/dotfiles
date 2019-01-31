// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.util.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            Utilities

static class val.cRetVal
    implements terceptors
{

            public final Map getMethodInterceptors()
            {
/*2122*/        return val$retVal;
            }

            public final List getConstructorInterceptors()
            {
/*2127*/        return val$cRetVal;
            }

            final LinkedHashMap val$retVal;
            final ArrayList val$cRetVal;

            terceptors(LinkedHashMap linkedhashmap, ArrayList arraylist)
            {
/*2118*/        val$retVal = linkedhashmap;
/*2118*/        val$cRetVal = arraylist;
/*2118*/        super();
            }
}
