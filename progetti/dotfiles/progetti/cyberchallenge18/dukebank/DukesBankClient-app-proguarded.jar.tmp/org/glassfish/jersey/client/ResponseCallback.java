// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResponseCallback.java

package org.glassfish.jersey.client;

import javax.ws.rs.ProcessingException;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse

interface ResponseCallback
{

    public abstract void completed(ClientResponse clientresponse, RequestScope requestscope);

    public abstract void failed(ProcessingException processingexception);
}
