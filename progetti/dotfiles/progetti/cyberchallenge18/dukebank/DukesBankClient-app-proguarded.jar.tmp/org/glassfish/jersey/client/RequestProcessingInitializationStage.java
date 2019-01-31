// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestProcessingInitializationStage.java

package org.glassfish.jersey.client;

import java.util.Collections;
import javax.inject.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.WriterInterceptor;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.model.internal.RankedComparator;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest

public class RequestProcessingInitializationStage
    implements Function
{

            public RequestProcessingInitializationStage(Provider provider, Provider provider1, ServiceLocator servicelocator)
            {
/*  86*/        requestRefProvider = provider;
/*  87*/        workersProvider = provider1;
/*  88*/        writerInterceptors = Collections.unmodifiableList(Lists.newArrayList(Providers.getAllProviders(servicelocator, javax/ws/rs/ext/WriterInterceptor, new RankedComparator())));
/*  90*/        readerInterceptors = Collections.unmodifiableList(Lists.newArrayList(Providers.getAllProviders(servicelocator, javax/ws/rs/ext/ReaderInterceptor, new RankedComparator())));
            }

            public ClientRequest apply(ClientRequest clientrequest)
            {
/*  96*/        ((Ref)requestRefProvider.get()).set(clientrequest);
/*  97*/        clientrequest.setWorkers((MessageBodyWorkers)workersProvider.get());
/*  98*/        clientrequest.setWriterInterceptors(writerInterceptors);
/*  99*/        clientrequest.setReaderInterceptors(readerInterceptors);
/* 101*/        return clientrequest;
            }

            public volatile Object apply(Object obj)
            {
/*  67*/        return apply((ClientRequest)obj);
            }

            private final Provider requestRefProvider;
            private final Provider workersProvider;
            private final Iterable writerInterceptors;
            private final Iterable readerInterceptors;
}
