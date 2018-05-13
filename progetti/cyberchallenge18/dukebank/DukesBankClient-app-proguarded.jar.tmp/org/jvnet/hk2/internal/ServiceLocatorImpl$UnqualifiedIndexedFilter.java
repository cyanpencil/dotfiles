// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import java.util.*;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

static class unqualified
    implements IndexedFilter
{

            public boolean matches(Descriptor descriptor)
            {
/*2440*/        if(unqualified == null)
/*2440*/            return true;
                Class aclass[];
/*2442*/        if((aclass = unqualified.value()).length <= 0)
/*2445*/            return descriptor.getQualifiers().isEmpty();
/*2448*/        HashSet hashset = new HashSet();
/*2449*/        int i = (aclass = aclass).length;
/*2449*/        for(int j = 0; j < i; j++)
                {
/*2449*/            Class class1 = aclass[j];
/*2450*/            hashset.add(class1.getName());
                }

/*2453*/        for(Iterator iterator = descriptor.getQualifiers().iterator(); iterator.hasNext();)
                {
/*2453*/            String s = (String)iterator.next();
/*2454*/            if(hashset.contains(s))
/*2454*/                return false;
                }

/*2457*/        return true;
            }

            public String getAdvertisedContract()
            {
/*2462*/        return contract;
            }

            public String getName()
            {
/*2467*/        return name;
            }

            private final String contract;
            private final String name;
            private final Unqualified unqualified;

            private (String s, String s1, Unqualified unqualified1)
            {
/*2433*/        contract = s;
/*2434*/        name = s1;
/*2435*/        unqualified = unqualified1;
            }


            // Unreferenced inner class org/jvnet/hk2/internal/ServiceLocatorImpl$1

/* anonymous class */
    static class ServiceLocatorImpl._cls1
        implements PrivilegedAction
    {

                public final String run()
                {
/* 127*/            return System.getProperty("org.jvnet.hk2.properties.bind.tracing.pattern");
                }

                public final volatile Object run()
                {
/* 124*/            return run();
                }

    }

}
