// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbortException.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse

class AbortException extends ProcessingException
{

            AbortException(ClientResponse clientresponse)
            {
/*  62*/        super("Request processing has been aborted");
/*  63*/        abortResponse = clientresponse;
            }

            public ClientResponse getAbortResponse()
            {
/*  72*/        return abortResponse;
            }

            private final transient ClientResponse abortResponse;
}
