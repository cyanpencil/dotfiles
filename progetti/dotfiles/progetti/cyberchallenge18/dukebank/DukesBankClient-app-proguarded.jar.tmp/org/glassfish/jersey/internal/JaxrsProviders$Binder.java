// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JaxrsProviders.java

package org.glassfish.jersey.internal;

import javax.ws.rs.ext.Providers;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;

// Referenced classes of package org.glassfish.jersey.internal:
//            JaxrsProviders

public static class ilder extends AbstractBinder
{

            protected void configure()
            {
/*  75*/        bind(org/glassfish/jersey/internal/JaxrsProviders).to(javax/ws/rs/ext/Providers).in(org/glassfish/hk2/api/PerLookup);
            }

            public ilder()
            {
            }
}
