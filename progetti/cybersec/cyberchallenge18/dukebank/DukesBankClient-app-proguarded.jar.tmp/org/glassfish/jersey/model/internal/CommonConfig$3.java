// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonConfig.java

package org.glassfish.jersey.model.internal;

import java.util.Set;
import jersey.repackaged.com.google.common.base.Predicate;
import org.glassfish.hk2.utilities.Binder;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            CommonConfig

class val.configured
    implements Predicate
{

            public boolean apply(Binder binder)
            {
/* 691*/        return !val$configured.contains(binder);
            }

            public volatile boolean apply(Object obj)
            {
/* 688*/        return apply((Binder)obj);
            }

            final Set val$configured;
            final CommonConfig this$0;

            ()
            {
/* 688*/        this$0 = final_commonconfig;
/* 688*/        val$configured = Set.this;
/* 688*/        super();
            }
}
