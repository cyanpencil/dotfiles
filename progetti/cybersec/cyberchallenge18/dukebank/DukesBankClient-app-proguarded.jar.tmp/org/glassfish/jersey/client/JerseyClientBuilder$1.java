// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClientBuilder.java

package org.glassfish.jersey.client;

import javax.net.ssl.SSLContext;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;

// Referenced classes of package org.glassfish.jersey.client:
//            JerseyClientBuilder

class val.sslConfiguratorCopy
    implements UnsafeValue
{

            public SSLContext get()
            {
/* 154*/        return val$sslConfiguratorCopy.createSSLContext();
            }

            public volatile Object get()
                throws Throwable
            {
/* 151*/        return get();
            }

            final SslConfigurator val$sslConfiguratorCopy;
            final JerseyClientBuilder this$0;

            feValue()
            {
/* 151*/        this$0 = final_jerseyclientbuilder;
/* 151*/        val$sslConfiguratorCopy = SslConfigurator.this;
/* 151*/        super();
            }
}
