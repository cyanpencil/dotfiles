// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import java.util.List;
import org.glassfish.hk2.api.Filter;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class val.filter
    implements PrivilegedAction
{

            public List run()
            {
/* 390*/        return getDescriptors(val$filter);
            }

            public volatile Object run()
            {
/* 386*/        return run();
            }

            final Filter val$filter;
            final ServiceLocatorImpl this$0;

            ()
            {
/* 386*/        this$0 = final_servicelocatorimpl;
/* 386*/        val$filter = Filter.this;
/* 386*/        super();
            }
}
