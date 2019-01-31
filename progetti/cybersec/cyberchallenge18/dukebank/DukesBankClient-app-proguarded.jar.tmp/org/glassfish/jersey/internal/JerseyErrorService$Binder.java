// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyErrorService.java

package org.glassfish.jersey.internal;

import javax.inject.Singleton;
import org.glassfish.hk2.api.ErrorService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;

// Referenced classes of package org.glassfish.jersey.internal:
//            JerseyErrorService

public static final class r extends AbstractBinder
{

            protected final void configure()
            {
/*  67*/        bind(org/glassfish/jersey/internal/JerseyErrorService).to(org/glassfish/hk2/api/ErrorService).in(javax/inject/Singleton);
            }

            public r()
            {
            }
}
