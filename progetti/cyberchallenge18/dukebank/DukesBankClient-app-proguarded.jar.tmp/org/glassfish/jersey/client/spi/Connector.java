// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Connector.java

package org.glassfish.jersey.client.spi;

import java.util.concurrent.Future;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.client.spi:
//            AsyncConnectorCallback

public interface Connector
    extends Inflector
{

    public abstract ClientResponse apply(ClientRequest clientrequest);

    public abstract Future apply(ClientRequest clientrequest, AsyncConnectorCallback asyncconnectorcallback);

    public abstract String getName();

    public abstract void close();
}
