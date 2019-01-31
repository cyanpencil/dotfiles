// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientBinder.java

package org.glassfish.jersey.client;

import javax.inject.Provider;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.PropertiesDelegate;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientBinder, ClientRequest

static class requestProvider
    implements Factory
{

            public PropertiesDelegate provide()
            {
/* 102*/        return ((ClientRequest)requestProvider.get()).getPropertiesDelegate();
            }

            public void dispose(PropertiesDelegate propertiesdelegate)
            {
            }

            public volatile void dispose(Object obj)
            {
/*  91*/        dispose((PropertiesDelegate)obj);
            }

            public volatile Object provide()
            {
/*  91*/        return provide();
            }

            private final Provider requestProvider;

            private (Provider provider)
            {
/*  97*/        requestProvider = provider;
            }
}
