// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundJaxrsResponse.java

package org.glassfish.jersey.client;

import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, InboundJaxrsResponse

class val.entityType
    implements Producer
{

            public Object call()
            {
/* 115*/        return InboundJaxrsResponse.access$000(InboundJaxrsResponse.this).readEntity(val$entityType);
            }

            final Class val$entityType;
            final InboundJaxrsResponse this$0;

            ()
            {
/* 112*/        this$0 = final_inboundjaxrsresponse;
/* 112*/        val$entityType = Class.this;
/* 112*/        super();
            }
}
