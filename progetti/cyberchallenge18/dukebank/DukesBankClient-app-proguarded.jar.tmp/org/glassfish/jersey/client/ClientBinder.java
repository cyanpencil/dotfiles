// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientBinder.java

package org.glassfish.jersey.client;

import java.util.Map;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.ext.MessageBodyReader;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.*;
import org.glassfish.jersey.internal.*;
import org.glassfish.jersey.internal.inject.*;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.message.internal.MessageBodyFactory;
import org.glassfish.jersey.message.internal.MessagingBinders;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.spi.ExecutorServiceProvider;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkedInputReader, ClientProperties, ClientRequest, DefaultClientAsyncExecutorProvider

class ClientBinder extends AbstractBinder
{
    static class PropertiesDelegateFactory
        implements Factory
    {

                public PropertiesDelegate provide()
                {
/* 102*/            return ((ClientRequest)requestProvider.get()).getPropertiesDelegate();
                }

                public void dispose(PropertiesDelegate propertiesdelegate)
                {
                }

                public volatile void dispose(Object obj)
                {
/*  91*/            dispose((PropertiesDelegate)obj);
                }

                public volatile Object provide()
                {
/*  91*/            return provide();
                }

                private final Provider requestProvider;

                private PropertiesDelegateFactory(Provider provider)
                {
/*  97*/            requestProvider = provider;
                }
    }

    static class RequestContextInjectionFactory extends ReferencingFactory
    {

                public RequestContextInjectionFactory(Provider provider)
                {
/*  87*/            super(provider);
                }
    }


            ClientBinder(Map map)
            {
/* 117*/        clientRuntimeProperties = map;
            }

            protected void configure()
            {
/* 122*/        install(new Binder[] {
/* 122*/            new org.glassfish.jersey.process.internal.RequestScope.Binder(), new org.glassfish.jersey.internal.JerseyErrorService.Binder(), new org.glassfish.jersey.internal.inject.ContextInjectionResolver.Binder(), new org.glassfish.jersey.internal.inject.JerseyClassAnalyzer.Binder(), new org.glassfish.jersey.message.internal.MessagingBinders.MessageBodyProviders(clientRuntimeProperties, RuntimeType.CLIENT), new org.glassfish.jersey.message.internal.MessagingBinders.HeaderDelegateProviders(), new org.glassfish.jersey.message.internal.MessageBodyFactory.Binder(), new org.glassfish.jersey.internal.ContextResolverFactory.Binder(), new org.glassfish.jersey.internal.JaxrsProviders.Binder(), new ServiceFinderBinder(org/glassfish/jersey/internal/spi/AutoDiscoverable, clientRuntimeProperties, RuntimeType.CLIENT)
                });
/* 133*/        bindFactory(ReferencingFactory.referenceFactory()).to(new TypeLiteral() {

                    final ClientBinder this$0;

                    
                    {
/* 133*/                this$0 = ClientBinder.this;
/* 133*/                super();
                    }
        }).in(org/glassfish/jersey/process/internal/RequestScoped);
/* 136*/        bindFactory(org/glassfish/jersey/client/ClientBinder$RequestContextInjectionFactory).to(org/glassfish/jersey/client/ClientRequest).in(org/glassfish/jersey/process/internal/RequestScoped);
/* 140*/        bindFactory(ReferencingFactory.referenceFactory()).to(new TypeLiteral() {

                    final ClientBinder this$0;

                    
                    {
/* 140*/                this$0 = ClientBinder.this;
/* 140*/                super();
                    }
        }).in(org/glassfish/jersey/process/internal/RequestScoped);
/* 143*/        bindFactory(org/glassfish/jersey/client/ClientBinder$PropertiesDelegateFactory, javax/inject/Singleton).to(org/glassfish/jersey/internal/PropertiesDelegate).in(org/glassfish/jersey/process/internal/RequestScoped);
/* 146*/        bind(org/glassfish/jersey/client/ChunkedInputReader).to(javax/ws/rs/ext/MessageBodyReader).in(javax/inject/Singleton);
                int i;
/* 149*/        i = (i = ((Integer)ClientProperties.getValue(clientRuntimeProperties, "jersey.config.client.async.threadPoolSize", Integer.valueOf(0))).intValue()) >= 0 ? i : 0;
/* 152*/        bind(Integer.valueOf(i)).named("ClientAsyncThreadPoolSize");
/* 154*/        bind(org/glassfish/jersey/client/DefaultClientAsyncExecutorProvider).to(org/glassfish/jersey/spi/ExecutorServiceProvider).in(javax/inject/Singleton);
            }

            private final Map clientRuntimeProperties;
}
