// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientConfig.java

package org.glassfish.jersey.client;

import javax.ws.rs.core.Configuration;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig

class r extends AbstractBinder
{

            protected void configure()
            {
/* 411*/        bind(val$runtimeCfgState).to(javax/ws/rs/core/Configuration);
            }

            final Builder.to val$runtimeCfgState;
            final Builder.to this$0;

            Builder()
            {
/* 408*/        this$0 = final_builder;
/* 408*/        val$runtimeCfgState = val.runtimeCfgState.this;
/* 408*/        super();
            }
}
