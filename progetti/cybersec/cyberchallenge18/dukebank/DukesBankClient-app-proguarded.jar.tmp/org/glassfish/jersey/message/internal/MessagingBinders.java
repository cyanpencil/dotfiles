// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessagingBinders.java

package org.glassfish.jersey.message.internal;

import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.internal.ServiceFinderBinder;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CacheControlProvider, CookieProvider, DateProvider, EntityTagProvider, 
//            LinkProvider, LocaleProvider, MediaTypeProvider, NewCookieProvider, 
//            StringHeaderProvider, UriProvider, BasicTypesMessageProvider, ByteArrayProvider, 
//            DataSourceProvider, FileProvider, FormMultivaluedMapProvider, FormProvider, 
//            InputStreamProvider, ReaderProvider, RenderedImageProvider, SourceProvider, 
//            StreamingOutputProvider, StringMessageProvider

public final class MessagingBinders
{
    public static class HeaderDelegateProviders extends AbstractBinder
    {

                protected void configure()
                {
/* 133*/            bind(org/glassfish/jersey/message/internal/CacheControlProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 134*/            bind(org/glassfish/jersey/message/internal/CookieProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 135*/            bind(org/glassfish/jersey/message/internal/DateProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 136*/            bind(org/glassfish/jersey/message/internal/EntityTagProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 137*/            bind(org/glassfish/jersey/message/internal/LinkProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 138*/            bind(org/glassfish/jersey/message/internal/LocaleProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 139*/            bind(org/glassfish/jersey/message/internal/MediaTypeProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 140*/            bind(org/glassfish/jersey/message/internal/NewCookieProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 141*/            bind(org/glassfish/jersey/message/internal/StringHeaderProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 142*/            bind(org/glassfish/jersey/message/internal/UriProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
                }

                public HeaderDelegateProviders()
                {
                }
    }

    public static class MessageBodyProviders extends AbstractBinder
    {

                protected void configure()
                {
/*  94*/            bindSingletonWorker(org/glassfish/jersey/message/internal/ByteArrayProvider);
/*  95*/            bindSingletonWorker(org/glassfish/jersey/message/internal/DataSourceProvider);
/*  96*/            bindSingletonWorker(org/glassfish/jersey/message/internal/FileProvider);
/*  97*/            bindSingletonWorker(org/glassfish/jersey/message/internal/FormMultivaluedMapProvider);
/*  98*/            bindSingletonWorker(org/glassfish/jersey/message/internal/FormProvider);
/*  99*/            bindSingletonWorker(org/glassfish/jersey/message/internal/InputStreamProvider);
/* 100*/            bindSingletonWorker(org/glassfish/jersey/message/internal/BasicTypesMessageProvider);
/* 101*/            bindSingletonWorker(org/glassfish/jersey/message/internal/ReaderProvider);
/* 102*/            bindSingletonWorker(org/glassfish/jersey/message/internal/RenderedImageProvider);
/* 103*/            bindSingletonWorker(org/glassfish/jersey/message/internal/StringMessageProvider);
/* 106*/            bind(org/glassfish/jersey/message/internal/SourceProvider$StreamSourceReader).to(javax/ws/rs/ext/MessageBodyReader).in(javax/inject/Singleton);
/* 107*/            bind(org/glassfish/jersey/message/internal/SourceProvider$SaxSourceReader).to(javax/ws/rs/ext/MessageBodyReader).in(javax/inject/Singleton);
/* 108*/            bind(org/glassfish/jersey/message/internal/SourceProvider$DomSourceReader).to(javax/ws/rs/ext/MessageBodyReader).in(javax/inject/Singleton);
/* 114*/            bind(org/glassfish/jersey/message/internal/StreamingOutputProvider).to(javax/ws/rs/ext/MessageBodyWriter).in(javax/inject/Singleton);
/* 115*/            bind(org/glassfish/jersey/message/internal/SourceProvider$SourceWriter).to(javax/ws/rs/ext/MessageBodyWriter).in(javax/inject/Singleton);
/* 116*/            install(new Binder[] {
/* 116*/                new ServiceFinderBinder(org/glassfish/jersey/spi/HeaderDelegateProvider, applicationProperties, runtimeType)
                    });
                }

                private void bindSingletonWorker(Class class1)
                {
/* 122*/            bind(class1).to(javax/ws/rs/ext/MessageBodyReader).to(javax/ws/rs/ext/MessageBodyWriter).in(javax/inject/Singleton);
                }

                private final Map applicationProperties;
                private final RuntimeType runtimeType;

                public MessageBodyProviders(Map map, RuntimeType runtimetype)
                {
/*  86*/            applicationProperties = map;
/*  87*/            runtimeType = runtimetype;
                }
    }


            private MessagingBinders()
            {
            }
}
