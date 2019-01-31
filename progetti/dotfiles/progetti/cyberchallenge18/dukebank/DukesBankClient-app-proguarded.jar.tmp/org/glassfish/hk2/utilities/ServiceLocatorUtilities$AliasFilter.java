// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorUtilities.java

package org.glassfish.hk2.utilities;

import java.util.*;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            ServiceLocatorUtilities

static class values
    implements Filter
{

            public boolean matches(Descriptor descriptor)
            {
/* 869*/        if((descriptor = (List)descriptor.getMetadata().get("__AliasOf")) == null || descriptor.isEmpty())
                {
/* 870*/            return false;
                } else
                {
/* 872*/            descriptor = (String)descriptor.get(0);
/* 874*/            return values.contains(descriptor);
                }
            }

            private final Set values;

            private (List list)
            {
/* 858*/        values = new HashSet();
                Object obj;
/* 861*/        for(list = list.iterator(); list.hasNext(); values.add(obj))
                {
/* 861*/            obj = (ActiveDescriptor)list.next();
/* 862*/            obj = (new StringBuilder()).append(((ActiveDescriptor) (obj)).getLocatorId()).append(".").append(((ActiveDescriptor) (obj)).getServiceId()).toString();
                }

            }


            // Unreferenced inner class org/glassfish/hk2/utilities/ServiceLocatorUtilities$1

/* anonymous class */
    static class ServiceLocatorUtilities._cls1 extends TypeLiteral
    {

    }

}
