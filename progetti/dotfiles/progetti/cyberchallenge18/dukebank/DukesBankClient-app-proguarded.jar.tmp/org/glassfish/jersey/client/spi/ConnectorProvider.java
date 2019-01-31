// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConnectorProvider.java

package org.glassfish.jersey.client.spi;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Configuration;

// Referenced classes of package org.glassfish.jersey.client.spi:
//            Connector

public interface ConnectorProvider
{

    public abstract Connector getConnector(Client client, Configuration configuration);
}
