// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorClientProvider.java

package org.glassfish.jersey.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;

public class ServiceLocatorClientProvider extends ServiceLocatorProvider
{

            public ServiceLocatorClientProvider()
            {
            }

            public static ServiceLocator getServiceLocator(ClientRequestContext clientrequestcontext)
            {
/*  78*/        if(!(clientrequestcontext instanceof ServiceLocatorSupplier))
/*  79*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_REQUEST(clientrequestcontext.getClass().getName()));
/*  83*/        else
/*  83*/            return ((ServiceLocatorSupplier)clientrequestcontext).getServiceLocator();
            }

            public static ServiceLocator getServiceLocator(ClientResponseContext clientresponsecontext)
            {
/* 100*/        if(!(clientresponsecontext instanceof ServiceLocatorSupplier))
/* 101*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_RESPONSE(clientresponsecontext.getClass().getName()));
/* 105*/        else
/* 105*/            return ((ServiceLocatorSupplier)clientresponsecontext).getServiceLocator();
            }
}
