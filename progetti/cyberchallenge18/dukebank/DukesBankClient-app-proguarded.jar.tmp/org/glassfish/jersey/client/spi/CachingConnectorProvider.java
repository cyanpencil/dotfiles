// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CachingConnectorProvider.java

package org.glassfish.jersey.client.spi;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Configuration;

// Referenced classes of package org.glassfish.jersey.client.spi:
//            ConnectorProvider, Connector

public class CachingConnectorProvider
    implements ConnectorProvider
{

            public CachingConnectorProvider(ConnectorProvider connectorprovider)
            {
/*  73*/        _flddelegate = connectorprovider;
            }

            public synchronized Connector getConnector(Client client, Configuration configuration)
            {
/*  78*/        if(connector == null)
/*  79*/            connector = _flddelegate.getConnector(client, configuration);
/*  81*/        return connector;
            }

            private final ConnectorProvider _flddelegate;
            private Connector connector;
}
