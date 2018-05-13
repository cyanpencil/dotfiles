// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessagingBinders.java

package org.glassfish.jersey.message.internal;

import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CacheControlProvider, CookieProvider, DateProvider, EntityTagProvider, 
//            LinkProvider, LocaleProvider, MediaTypeProvider, MessagingBinders, 
//            NewCookieProvider, StringHeaderProvider, UriProvider

public static class  extends AbstractBinder
{

            protected void configure()
            {
/* 133*/        bind(org/glassfish/jersey/message/internal/CacheControlProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 134*/        bind(org/glassfish/jersey/message/internal/CookieProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 135*/        bind(org/glassfish/jersey/message/internal/DateProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 136*/        bind(org/glassfish/jersey/message/internal/EntityTagProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 137*/        bind(org/glassfish/jersey/message/internal/LinkProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 138*/        bind(org/glassfish/jersey/message/internal/LocaleProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 139*/        bind(org/glassfish/jersey/message/internal/MediaTypeProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 140*/        bind(org/glassfish/jersey/message/internal/NewCookieProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 141*/        bind(org/glassfish/jersey/message/internal/StringHeaderProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
/* 142*/        bind(org/glassfish/jersey/message/internal/UriProvider).to(org/glassfish/jersey/spi/HeaderDelegateProvider).in(javax/inject/Singleton);
            }

            public ()
            {
            }
}
