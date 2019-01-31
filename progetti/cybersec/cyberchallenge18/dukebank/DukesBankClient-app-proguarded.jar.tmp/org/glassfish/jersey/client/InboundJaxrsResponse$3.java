// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundJaxrsResponse.java

package org.glassfish.jersey.client;

import java.lang.annotation.Annotation;
import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse, InboundJaxrsResponse

class val.annotations
    implements Producer
{

            public Object call()
            {
/* 137*/        return InboundJaxrsResponse.access$000(InboundJaxrsResponse.this).readEntity(val$entityType, val$annotations);
            }

            final Class val$entityType;
            final Annotation val$annotations[];
            final InboundJaxrsResponse this$0;

            ()
            {
/* 134*/        this$0 = final_inboundjaxrsresponse;
/* 134*/        val$entityType = class1;
/* 134*/        val$annotations = _5B_Ljava.lang.annotation.Annotation_3B_.this;
/* 134*/        super();
            }
}
