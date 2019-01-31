// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomProvidersFeature.java

package org.glassfish.jersey.client;

import java.util.Collection;
import java.util.Iterator;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class CustomProvidersFeature
    implements Feature
{

            public CustomProvidersFeature(Collection collection)
            {
/*  63*/        providers = collection;
            }

            public boolean configure(FeatureContext featurecontext)
            {
                Class class1;
/*  68*/        for(Iterator iterator = providers.iterator(); iterator.hasNext(); featurecontext.register(class1))
/*  68*/            class1 = (Class)iterator.next();

/*  71*/        return true;
            }

            private final Collection providers;
}
