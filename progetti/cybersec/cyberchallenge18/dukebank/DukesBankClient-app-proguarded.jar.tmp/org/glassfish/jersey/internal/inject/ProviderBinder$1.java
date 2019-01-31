// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProviderBinder.java

package org.glassfish.jersey.internal.inject;

import jersey.repackaged.com.google.common.base.Predicate;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.internal.ComponentBag;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ProviderBinder

static class 
    implements Predicate
{

            public final boolean apply(ContractProvider contractprovider)
            {
/* 265*/        return ComponentBag.EXCLUDE_EMPTY.apply(contractprovider) && ComponentBag.EXCLUDE_META_PROVIDERS.apply(contractprovider);
            }

            public final volatile boolean apply(Object obj)
            {
/* 262*/        return apply((ContractProvider)obj);
            }

            ()
            {
            }
}
