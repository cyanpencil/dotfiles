// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractRuntimeDelegate.java

package org.glassfish.jersey.internal;

import java.net.URI;
import java.util.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.RuntimeDelegate;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.message.internal.*;
import org.glassfish.jersey.spi.HeaderDelegateProvider;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

public abstract class AbstractRuntimeDelegate extends RuntimeDelegate
{

            protected AbstractRuntimeDelegate(ServiceLocator servicelocator)
            {
/*  86*/        hps = Providers.getProviders(servicelocator, org/glassfish/jersey/spi/HeaderDelegateProvider);
/*  91*/        map = new WeakHashMap();
/*  92*/        map.put(javax/ws/rs/core/EntityTag, _createHeaderDelegate(javax/ws/rs/core/EntityTag));
/*  93*/        map.put(javax/ws/rs/core/MediaType, _createHeaderDelegate(javax/ws/rs/core/MediaType));
/*  94*/        map.put(javax/ws/rs/core/CacheControl, _createHeaderDelegate(javax/ws/rs/core/CacheControl));
/*  95*/        map.put(javax/ws/rs/core/NewCookie, _createHeaderDelegate(javax/ws/rs/core/NewCookie));
/*  96*/        map.put(javax/ws/rs/core/Cookie, _createHeaderDelegate(javax/ws/rs/core/Cookie));
/*  97*/        map.put(java/net/URI, _createHeaderDelegate(java/net/URI));
/*  98*/        map.put(java/util/Date, _createHeaderDelegate(java/util/Date));
/*  99*/        map.put(java/lang/String, _createHeaderDelegate(java/lang/String));
/* 101*/        servicelocator.shutdown();
/* 102*/        return;
                Exception exception;
/* 101*/        exception;
/* 101*/        servicelocator.shutdown();
/* 101*/        throw exception;
            }

            public javax.ws.rs.core.Variant.VariantListBuilder createVariantListBuilder()
            {
/* 107*/        return new VariantListBuilder();
            }

            public javax.ws.rs.core.Response.ResponseBuilder createResponseBuilder()
            {
/* 112*/        return new org.glassfish.jersey.message.internal.OutboundJaxrsResponse.Builder(new OutboundMessageContext());
            }

            public UriBuilder createUriBuilder()
            {
/* 117*/        return new JerseyUriBuilder();
            }

            public javax.ws.rs.core.Link.Builder createLinkBuilder()
            {
/* 122*/        return new org.glassfish.jersey.message.internal.JerseyLink.Builder();
            }

            public javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate createHeaderDelegate(Class class1)
            {
/* 127*/        if(class1 == null)
/* 128*/            throw new IllegalArgumentException("type parameter cannot be null");
                javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate headerdelegate;
/* 131*/        if((headerdelegate = (javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate)map.get(class1)) != null)
/* 133*/            return headerdelegate;
/* 136*/        else
/* 136*/            return _createHeaderDelegate(class1);
            }

            private javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate _createHeaderDelegate(Class class1)
            {
                HeaderDelegateProvider headerdelegateprovider;
/* 141*/        for(Iterator iterator = hps.iterator(); iterator.hasNext();)
/* 141*/            if((headerdelegateprovider = (HeaderDelegateProvider)iterator.next()).supports(class1))
/* 143*/                return headerdelegateprovider;

/* 147*/        return null;
            }

            private final Set hps;
            private final Map map;
}
