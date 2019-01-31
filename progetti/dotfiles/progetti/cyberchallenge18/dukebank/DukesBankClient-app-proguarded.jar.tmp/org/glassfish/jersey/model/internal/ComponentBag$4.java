// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag

static class 
    implements Inflector
{

            public final ContractProvider apply(org.glassfish.jersey.model.r.Builder builder)
            {
/* 161*/        return builder.build();
            }

            public final volatile Object apply(Object obj)
            {
/* 158*/        return apply((org.glassfish.jersey.model.r.Builder)obj);
            }

            ()
            {
            }
}
