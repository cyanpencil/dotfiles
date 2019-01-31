// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import java.util.Set;
import jersey.repackaged.com.google.common.base.Predicate;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.model.ContractProvider;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag

static class 
    implements Predicate
{

            public final boolean apply(ContractProvider contractprovider)
            {
/* 127*/        return contractprovider.getContracts().contains(org/glassfish/hk2/utilities/Binder);
            }

            public final volatile boolean apply(Object obj)
            {
/* 124*/        return apply((ContractProvider)obj);
            }

            ()
            {
            }
}
