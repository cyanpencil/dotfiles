// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextResolverFactory.java

package org.glassfish.jersey.internal;

import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.spi.ContextResolvers;

// Referenced classes of package org.glassfish.jersey.internal:
//            ContextResolverFactory

public static class A extends AbstractBinder
{

            protected void configure()
            {
/*  83*/        bindAsContract(org/glassfish/jersey/internal/ContextResolverFactory).to(org/glassfish/jersey/spi/ContextResolvers).in(javax/inject/Singleton);
            }

            public A()
            {
            }
}
