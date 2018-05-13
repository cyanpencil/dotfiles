// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMapperFactory.java

package org.glassfish.jersey.internal;

import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.spi.ExceptionMappers;

// Referenced classes of package org.glassfish.jersey.internal:
//            ExceptionMapperFactory

public static class A extends AbstractBinder
{

            protected void configure()
            {
/*  93*/        bindAsContract(org/glassfish/jersey/internal/ExceptionMapperFactory).to(org/glassfish/jersey/spi/ExceptionMappers).in(javax/inject/Singleton);
            }

            public A()
            {
            }
}
