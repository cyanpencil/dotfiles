// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeDelegateImpl.java

package org.glassfish.jersey.internal;

import javax.ws.rs.core.Application;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.message.internal.MessagingBinders;

// Referenced classes of package org.glassfish.jersey.internal:
//            AbstractRuntimeDelegate, LocalizationMessages

public class RuntimeDelegateImpl extends AbstractRuntimeDelegate
{

            public RuntimeDelegateImpl()
            {
/*  63*/        super(Injections.createLocator("jersey-common-rd-locator", new Binder[] {
/*  63*/            new org.glassfish.jersey.message.internal.MessagingBinders.HeaderDelegateProviders()
                }));
            }

            public Object createEndpoint(Application application, Class class1)
                throws IllegalArgumentException, UnsupportedOperationException
            {
/*  69*/        throw new UnsupportedOperationException(LocalizationMessages.NO_CONTAINER_AVAILABLE());
            }
}
