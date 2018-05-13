// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClient.java

package org.glassfish.jersey.client;

import javax.net.ssl.SSLContext;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.spi.DefaultSslContextProvider;

// Referenced classes of package org.glassfish.jersey.client:
//            JerseyClient

static class ontextProvider
    implements DefaultSslContextProvider
{

            public final SSLContext getDefaultSslContext()
            {
/*  82*/        return SslConfigurator.getDefaultContext();
            }

            ontextProvider()
            {
            }
}
