// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientFilteringStages.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.util.Iterator;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientRequestFilter;
import org.glassfish.jersey.process.internal.AbstractChainableStage;
import org.glassfish.jersey.process.internal.Stage;

// Referenced classes of package org.glassfish.jersey.client:
//            AbortException, ClientFilteringStages, ClientRequest, ClientResponse

static final class requestFilters extends AbstractChainableStage
{

            public final org.glassfish.jersey.process.internal.ters apply(ClientRequest clientrequest)
            {
/* 113*/        for(Iterator iterator = requestFilters.iterator(); iterator.hasNext();)
                {
/* 113*/            Object obj = (ClientRequestFilter)iterator.next();
/* 115*/            try
                    {
/* 115*/                ((ClientRequestFilter) (obj)).filter(clientrequest);
/* 116*/                if((obj = clientrequest.getAbortResponse()) != null)
/* 118*/                    throw new AbortException(new ClientResponse(clientrequest, ((javax.ws.rs.core.Response) (obj))));
                    }
/* 120*/            catch(IOException ioexception)
                    {
/* 121*/                throw new ProcessingException(ioexception);
                    }
                }

/* 124*/        return org.glassfish.jersey.process.internal.ters(clientrequest, getDefaultNext());
            }

            public final volatile org.glassfish.jersey.process.internal.Next apply(Object obj)
            {
/* 103*/        return apply((ClientRequest)obj);
            }

            private final Iterable requestFilters;

            private (Iterable iterable)
            {
/* 108*/        requestFilters = iterable;
            }

}
