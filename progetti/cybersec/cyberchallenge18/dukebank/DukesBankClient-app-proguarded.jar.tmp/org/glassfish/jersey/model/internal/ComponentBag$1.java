// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import java.util.Set;
import javax.ws.rs.core.Feature;
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
/* 101*/        if((contractprovider = contractprovider.getContracts()).isEmpty())
/* 103*/            return true;
/* 106*/        byte byte0 = 0;
/* 107*/        if(contractprovider.contains(javax/ws/rs/core/Feature))
/* 108*/            byte0 = 1;
/* 110*/        if(contractprovider.contains(org/glassfish/hk2/utilities/Binder))
/* 111*/            byte0++;
/* 113*/        return contractprovider.size() > byte0;
            }

            public final volatile boolean apply(Object obj)
            {
/*  98*/        return apply((ContractProvider)obj);
            }

            ()
            {
            }
}
