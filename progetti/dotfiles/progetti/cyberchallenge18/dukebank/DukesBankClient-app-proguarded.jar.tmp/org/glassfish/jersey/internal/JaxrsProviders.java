// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JaxrsProviders.java

package org.glassfish.jersey.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.*;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.spi.ContextResolvers;
import org.glassfish.jersey.spi.ExceptionMappers;

public class JaxrsProviders
    implements Providers
{
    public static class Binder extends AbstractBinder
    {

                protected void configure()
                {
/*  75*/            bind(org/glassfish/jersey/internal/JaxrsProviders).to(javax/ws/rs/ext/Providers).in(org/glassfish/hk2/api/PerLookup);
                }

                public Binder()
                {
                }
    }


            public JaxrsProviders()
            {
            }

            public MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  91*/        return ((MessageBodyWorkers)workers.get()).getMessageBodyReader(class1, type, aannotation, mediatype);
            }

            public MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  99*/        return ((MessageBodyWorkers)workers.get()).getMessageBodyWriter(class1, type, aannotation, mediatype);
            }

            public ExceptionMapper getExceptionMapper(Class class1)
            {
                ExceptionMappers exceptionmappers;
/* 105*/        if((exceptionmappers = (ExceptionMappers)mappers.get()) != null)
/* 106*/            return exceptionmappers.find(class1);
/* 106*/        else
/* 106*/            return null;
            }

            public ContextResolver getContextResolver(Class class1, MediaType mediatype)
            {
/* 111*/        return ((ContextResolvers)resolvers.get()).resolve(class1, mediatype);
            }

            private Provider workers;
            private Provider resolvers;
            private Provider mappers;
}
