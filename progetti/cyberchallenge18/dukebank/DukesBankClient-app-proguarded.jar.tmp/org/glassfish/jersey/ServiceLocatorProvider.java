// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorProvider.java

package org.glassfish.jersey;

import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptorContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;

public class ServiceLocatorProvider
{

            public ServiceLocatorProvider()
            {
            }

            public static ServiceLocator getServiceLocator(WriterInterceptorContext writerinterceptorcontext)
            {
/* 123*/        if(!(writerinterceptorcontext instanceof ServiceLocatorSupplier))
/* 124*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_WRITER_INTERCEPTOR_CONTEXT(writerinterceptorcontext.getClass().getName()));
/* 128*/        else
/* 128*/            return ((ServiceLocatorSupplier)writerinterceptorcontext).getServiceLocator();
            }

            public static ServiceLocator getServiceLocator(ReaderInterceptorContext readerinterceptorcontext)
            {
/* 145*/        if(!(readerinterceptorcontext instanceof ServiceLocatorSupplier))
/* 146*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_READER_INTERCEPTOR_CONTEXT(readerinterceptorcontext.getClass().getName()));
/* 150*/        else
/* 150*/            return ((ServiceLocatorSupplier)readerinterceptorcontext).getServiceLocator();
            }

            public static ServiceLocator getServiceLocator(FeatureContext featurecontext)
            {
/* 170*/        if(!(featurecontext instanceof ServiceLocatorSupplier))
/* 171*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_SERVICE_LOCATOR_PROVIDER_INSTANCE_FEATURE_CONTEXT(featurecontext.getClass().getName()));
/* 175*/        else
/* 175*/            return ((ServiceLocatorSupplier)featurecontext).getServiceLocator();
            }
}
