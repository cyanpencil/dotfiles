// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.util.Set;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class val.allQualifiers
    implements Filter
{

            public boolean matches(Descriptor descriptor)
            {
/*1539*/        return descriptor.getQualifiers().containsAll(val$allQualifiers);
            }

            final Set val$allQualifiers;
            final ServiceLocatorImpl this$0;

            ()
            {
/*1535*/        this$0 = final_servicelocatorimpl;
/*1535*/        val$allQualifiers = Set.this;
/*1535*/        super();
            }
}
