// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientResponse.java

package org.glassfish.jersey.client;

import java.io.*;
import java.util.Collections;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest, ClientResponse

class byteArrayInputStream extends InputStream
{

            public int read()
                throws IOException
            {
                ByteArrayOutputStream bytearrayoutputstream;
/* 104*/        if(byteArrayInputStream != null)
/* 105*/            break MISSING_BLOCK_LABEL_99;
/* 105*/        bytearrayoutputstream = new ByteArrayOutputStream();
/* 109*/        Object obj = val$requestContext.getWorkers().writeTo(val$entity, val$entity.getClass(), null, null, val$response.getMediaType(), val$response.getMetadata(), val$requestContext.getPropertiesDelegate(), bytearrayoutputstream, Collections.emptyList());
/* 114*/        if(obj != null)
/* 115*/            ((OutputStream) (obj)).close();
/* 115*/        break MISSING_BLOCK_LABEL_84;
/* 114*/        JVM INSTR dup ;
/* 115*/        obj;
/* 115*/        throw ;
/* 118*/        JVM INSTR pop ;
/* 122*/        byteArrayInputStream = new ByteArrayInputStream(bytearrayoutputstream.toByteArray());
/* 125*/        return byteArrayInputStream.read();
            }

            private ByteArrayInputStream byteArrayInputStream;
            final ClientRequest val$requestContext;
            final Object val$entity;
            final Response val$response;
            final ClientResponse this$0;

            rs()
            {
/*  98*/        this$0 = final_clientresponse;
/*  98*/        val$requestContext = clientrequest;
/*  98*/        val$entity = obj;
/*  98*/        val$response = Response.this;
/*  98*/        super();
/* 100*/        byteArrayInputStream = null;
            }
}
