// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextResolverFactory.java

package org.glassfish.jersey.internal;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.KeyComparatorHashMap;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.message.internal.MessageBodyFactory;
import org.glassfish.jersey.spi.ContextResolvers;

public class ContextResolverFactory
    implements ContextResolvers
{
    static final class ContextResolverAdapter
        implements ContextResolver
    {

                public final Object getContext(Class class1)
                {
                    ContextResolver acontextresolver[];
/* 173*/            int i = (acontextresolver = cra).length;
/* 173*/            for(int j = 0; j < i; j++)
                    {
                        Object obj;
/* 173*/                if((obj = ((ContextResolver) (obj = acontextresolver[j])).getContext(class1)) != null)
/* 176*/                    return obj;
                    }

/* 179*/            return null;
                }

                final ContextResolver reduce()
                {
/* 183*/            if(cra.length == 0)
/* 184*/                return ContextResolverFactory.NULL_CONTEXT_RESOLVER;
/* 186*/            if(cra.length == 1)
/* 187*/                return cra[0];
/* 189*/            else
/* 189*/                return this;
                }

                private static transient List removeNull(ContextResolver acontextresolver[])
                {
/* 194*/            ArrayList arraylist = new ArrayList(acontextresolver.length);
/* 195*/            int i = (acontextresolver = acontextresolver).length;
/* 195*/            for(int j = 0; j < i; j++)
                    {
                        ContextResolver contextresolver;
/* 195*/                if((contextresolver = acontextresolver[j]) != null)
/* 197*/                    arraylist.add(contextresolver);
                    }

/* 200*/            return arraylist;
                }

                private final ContextResolver cra[];

                transient ContextResolverAdapter(ContextResolver acontextresolver[])
                {
/* 164*/            this(removeNull(acontextresolver));
                }

                ContextResolverAdapter(List list)
                {
/* 168*/            cra = (ContextResolver[])list.toArray(new ContextResolver[list.size()]);
                }
    }

    static final class NullContextResolverAdapter
        implements ContextResolver
    {

                public final Object getContext(Class class1)
                {
/* 155*/            throw new UnsupportedOperationException("Not supported yet.");
                }

                private NullContextResolverAdapter()
                {
                }

    }

    public static class Binder extends AbstractBinder
    {

                protected void configure()
                {
/*  83*/            bindAsContract(org/glassfish/jersey/internal/ContextResolverFactory).to(org/glassfish/jersey/spi/ContextResolvers).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            public ContextResolverFactory(ServiceLocator servicelocator)
            {
/*  99*/        HashMap hashmap = new HashMap();
/* 102*/        for(servicelocator = (servicelocator = Providers.getAllProviders(servicelocator, javax/ws/rs/ext/ContextResolver)).iterator(); servicelocator.hasNext();)
                {
                    ContextResolver contextresolver;
/* 103*/            Object obj = MediaTypes.createFrom((Produces)(contextresolver = (ContextResolver)servicelocator.next()).getClass().getAnnotation(javax/ws/rs/Produces));
/* 106*/            Type type = getParameterizedType(contextresolver.getClass());
                    Object obj1;
/* 108*/            if((obj1 = (Map)hashmap.get(type)) == null)
                    {
/* 110*/                obj1 = new HashMap();
/* 111*/                hashmap.put(type, obj1);
                    }
/* 113*/            obj = ((List) (obj)).iterator();
/* 113*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 113*/                MediaType mediatype = (MediaType)((Iterator) (obj)).next();
                        Object obj2;
/* 114*/                if((obj2 = (List)((Map) (obj1)).get(mediatype)) == null)
                        {
/* 116*/                    obj2 = new ArrayList();
/* 117*/                    ((Map) (obj1)).put(mediatype, obj2);
                        }
/* 119*/                ((List) (obj2)).add(contextresolver);
                    }
                }

/* 126*/        for(servicelocator = hashmap.entrySet().iterator(); servicelocator.hasNext();)
                {
/* 126*/            java.util.Map.Entry entry = (java.util.Map.Entry)servicelocator.next();
/* 127*/            KeyComparatorHashMap keycomparatorhashmap = new KeyComparatorHashMap(4, MessageBodyFactory.MEDIA_TYPE_KEY_COMPARATOR);
/* 129*/            resolver.put(entry.getKey(), keycomparatorhashmap);
/* 131*/            cache.put(entry.getKey(), new ConcurrentHashMap(4));
/* 133*/            Iterator iterator = ((Map)entry.getValue()).entrySet().iterator();
/* 133*/            while(iterator.hasNext()) 
                    {
/* 133*/                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
/* 134*/                keycomparatorhashmap.put(entry1.getKey(), reduce((List)entry1.getValue()));
                    }
                }

            }

            private Type getParameterizedType(Class class1)
            {
/* 140*/        if((class1 = ReflectionHelper.getParameterizedTypeArguments(class1 = ReflectionHelper.getClass(class1, javax/ws/rs/ext/ContextResolver))) != null)
/* 145*/            return class1[0];
/* 145*/        else
/* 145*/            return java/lang/Object;
            }

            private ContextResolver reduce(List list)
            {
/* 205*/        if(list.size() == 1)
/* 206*/            return (ContextResolver)list.iterator().next();
/* 208*/        else
/* 208*/            return new ContextResolverAdapter(list);
            }

            public ContextResolver resolve(Type type, MediaType mediatype)
            {
                ConcurrentHashMap concurrenthashmap;
/* 215*/        if((concurrenthashmap = (ConcurrentHashMap)cache.get(type)) == null)
/* 217*/            return null;
/* 220*/        if(mediatype == null)
/* 221*/            mediatype = MediaType.WILDCARD_TYPE;
                Object obj;
/* 224*/        if((obj = (ContextResolver)concurrenthashmap.get(mediatype)) == null)
                {
/* 226*/            type = (Map)resolver.get(type);
/* 228*/            if(mediatype.isWildcardType())
                    {
/* 229*/                if((obj = (ContextResolver)type.get(MediaType.WILDCARD_TYPE)) == null)
/* 231*/                    obj = NULL_CONTEXT_RESOLVER;
                    } else
/* 233*/            if(mediatype.isWildcardSubtype())
                    {
/* 235*/                ContextResolver contextresolver = (ContextResolver)type.get(mediatype);
/* 236*/                obj = (ContextResolver)type.get(MediaType.WILDCARD_TYPE);
/* 238*/                obj = (new ContextResolverAdapter(new ContextResolver[] {
/* 238*/                    contextresolver, obj
                        })).reduce();
                    } else
                    {
/* 241*/                ContextResolver contextresolver1 = (ContextResolver)type.get(mediatype);
/* 242*/                obj = (ContextResolver)type.get(new MediaType(mediatype.getType(), "*"));
/* 243*/                type = (ContextResolver)type.get(MediaType.WILDCARD_TYPE);
/* 245*/                obj = (new ContextResolverAdapter(new ContextResolver[] {
/* 245*/                    contextresolver1, obj, type
                        })).reduce();
                    }
                    ContextResolver contextresolver2;
/* 248*/            if((contextresolver2 = (ContextResolver)concurrenthashmap.putIfAbsent(mediatype, obj)) != null)
/* 255*/                obj = contextresolver2;
                }
/* 259*/        if(obj != NULL_CONTEXT_RESOLVER)
/* 259*/            return ((ContextResolver) (obj));
/* 259*/        else
/* 259*/            return null;
            }

            private final Map resolver = new HashMap(3);
            private final Map cache = new HashMap(3);
            private static final NullContextResolverAdapter NULL_CONTEXT_RESOLVER = new NullContextResolverAdapter();


}
