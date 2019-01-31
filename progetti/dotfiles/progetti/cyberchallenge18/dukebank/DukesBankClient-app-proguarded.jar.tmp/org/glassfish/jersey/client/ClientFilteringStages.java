// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientFilteringStages.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.util.Iterator;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.process.internal.*;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, InboundJaxrsResponse, AbortException, ClientRequest

class ClientFilteringStages
{
    static class ResponseFilterStage extends AbstractChainableStage
    {

                public org.glassfish.jersey.process.internal.Stage.Continuation apply(ClientResponse clientresponse)
                {
/* 139*/            try
                    {
                        ClientResponseFilter clientresponsefilter;
/* 139*/                for(Iterator iterator = filters.iterator(); iterator.hasNext(); (clientresponsefilter = (ClientResponseFilter)iterator.next()).filter(clientresponse.getRequestContext(), clientresponse));
                    }
/* 142*/            catch(IOException ioexception)
                    {
/* 143*/                InboundJaxrsResponse inboundjaxrsresponse = new InboundJaxrsResponse(clientresponse, null);
/* 144*/                throw new ResponseProcessingException(inboundjaxrsresponse, ioexception);
                    }
/* 147*/            return org.glassfish.jersey.process.internal.Stage.Continuation.of(clientresponse, getDefaultNext());
                }

                public volatile org.glassfish.jersey.process.internal.Stage.Continuation apply(Object obj)
                {
/* 128*/            return apply((ClientResponse)obj);
                }

                private final Iterable filters;

                private ResponseFilterStage(Iterable iterable)
                {
/* 133*/            filters = iterable;
                }

    }

    static final class RequestFilteringStage extends AbstractChainableStage
    {

                public final org.glassfish.jersey.process.internal.Stage.Continuation apply(ClientRequest clientrequest)
                {
/* 113*/            for(Iterator iterator = requestFilters.iterator(); iterator.hasNext();)
                    {
/* 113*/                Object obj = (ClientRequestFilter)iterator.next();
/* 115*/                try
                        {
/* 115*/                    ((ClientRequestFilter) (obj)).filter(clientrequest);
/* 116*/                    if((obj = clientrequest.getAbortResponse()) != null)
/* 118*/                        throw new AbortException(new ClientResponse(clientrequest, ((javax.ws.rs.core.Response) (obj))));
                        }
/* 120*/                catch(IOException ioexception)
                        {
/* 121*/                    throw new ProcessingException(ioexception);
                        }
                    }

/* 124*/            return org.glassfish.jersey.process.internal.Stage.Continuation.of(clientrequest, getDefaultNext());
                }

                public final volatile org.glassfish.jersey.process.internal.Stage.Continuation apply(Object obj)
                {
/* 103*/            return apply((ClientRequest)obj);
                }

                private final Iterable requestFilters;

                private RequestFilteringStage(Iterable iterable)
                {
/* 108*/            requestFilters = iterable;
                }

    }


            private ClientFilteringStages()
            {
            }

            static ChainableStage createRequestFilteringStage(ServiceLocator servicelocator)
            {
/*  78*/        RankedComparator rankedcomparator = new RankedComparator(org.glassfish.jersey.model.internal.RankedComparator.Order.ASCENDING);
/*  80*/        if((servicelocator = Providers.getAllProviders(servicelocator, javax/ws/rs/client/ClientRequestFilter, rankedcomparator)).iterator().hasNext())
/*  83*/            return new RequestFilteringStage(servicelocator);
/*  83*/        else
/*  83*/            return null;
            }

            static ChainableStage createResponseFilteringStage(ServiceLocator servicelocator)
            {
/*  95*/        RankedComparator rankedcomparator = new RankedComparator(org.glassfish.jersey.model.internal.RankedComparator.Order.DESCENDING);
/*  97*/        if((servicelocator = Providers.getAllProviders(servicelocator, javax/ws/rs/client/ClientResponseFilter, rankedcomparator)).iterator().hasNext())
/* 100*/            return new ResponseFilterStage(servicelocator);
/* 100*/        else
/* 100*/            return null;
            }
}
