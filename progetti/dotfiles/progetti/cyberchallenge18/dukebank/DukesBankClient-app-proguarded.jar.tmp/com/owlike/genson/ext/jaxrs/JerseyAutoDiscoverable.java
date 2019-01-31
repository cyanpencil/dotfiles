// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyAutoDiscoverable.java

package com.owlike.genson.ext.jaxrs;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.FeatureContext;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;

// Referenced classes of package com.owlike.genson.ext.jaxrs:
//            GensonJsonConverter

public class JerseyAutoDiscoverable
    implements AutoDiscoverable
{

            public JerseyAutoDiscoverable()
            {
            }

            public void configure(FeatureContext featurecontext)
            {
                Configuration configuration;
/*  14*/        if(!(configuration = featurecontext.getConfiguration()).isRegistered(com/owlike/genson/ext/jaxrs/GensonJsonConverter))
/*  17*/            featurecontext.register(com/owlike/genson/ext/jaxrs/GensonJsonConverter);
            }
}
