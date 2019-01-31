// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientFilteringStages.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.util.Iterator;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.ResponseProcessingException;
import org.glassfish.jersey.process.internal.AbstractChainableStage;
import org.glassfish.jersey.process.internal.Stage;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientFilteringStages, ClientResponse, InboundJaxrsResponse

static class filters extends AbstractChainableStage
{

            public org.glassfish.jersey.process.internal. apply(ClientResponse clientresponse)
            {
/* 139*/        try
                {
                    ClientResponseFilter clientresponsefilter;
/* 139*/            for(Iterator iterator = filters.iterator(); iterator.hasNext(); (clientresponsefilter = (ClientResponseFilter)iterator.next()).filter(clientresponse.getRequestContext(), clientresponse));
                }
/* 142*/        catch(IOException ioexception)
                {
/* 143*/            InboundJaxrsResponse inboundjaxrsresponse = new InboundJaxrsResponse(clientresponse, null);
/* 144*/            throw new ResponseProcessingException(inboundjaxrsresponse, ioexception);
                }
/* 147*/        return org.glassfish.jersey.process.internal.(clientresponse, getDefaultNext());
            }

            public volatile org.glassfish.jersey.process.internal.Next apply(Object obj)
            {
/* 128*/        return apply((ClientResponse)obj);
            }

            private final Iterable filters;

            private (Iterable iterable)
            {
/* 133*/        filters = iterable;
            }

}
