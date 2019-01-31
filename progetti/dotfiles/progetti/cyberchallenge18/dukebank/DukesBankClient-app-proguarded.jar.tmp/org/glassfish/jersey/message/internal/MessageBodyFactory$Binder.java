// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

public static class  extends AbstractBinder
{

            protected void configure()
            {
/* 123*/        bindAsContract(org/glassfish/jersey/message/internal/MessageBodyFactory).to(org/glassfish/jersey/message/MessageBodyWorkers).in(javax/inject/Singleton);
            }

            public ()
            {
            }
}
