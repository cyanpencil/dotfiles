// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncodingFeature.java

package org.glassfish.jersey.client.filter;

import java.util.Map;
import javax.ws.rs.core.*;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.spi.ContentEncoder;

// Referenced classes of package org.glassfish.jersey.client.filter:
//            EncodingFilter

public class EncodingFeature
    implements Feature
{

            public transient EncodingFeature(Class aclass[])
            {
/*  69*/        this(null, aclass);
            }

            public transient EncodingFeature(String s, Class aclass[])
            {
/*  81*/        useEncoding = s;
/*  83*/        Providers.ensureContract(org/glassfish/jersey/spi/ContentEncoder, aclass);
/*  84*/        encodingProviders = aclass;
            }

            public boolean configure(FeatureContext featurecontext)
            {
/*  90*/        if(useEncoding != null && !featurecontext.getConfiguration().getProperties().containsKey("jersey.config.client.useEncoding"))
/*  93*/            featurecontext.property("jersey.config.client.useEncoding", useEncoding);
                Class aclass[];
/*  96*/        int i = (aclass = encodingProviders).length;
/*  96*/        for(int j = 0; j < i; j++)
                {
/*  96*/            Class class1 = aclass[j];
/*  97*/            featurecontext.register(class1);
                }

                boolean flag;
/*  99*/        if(flag = useEncoding != null || encodingProviders.length > 0)
/* 101*/            featurecontext.register(org/glassfish/jersey/client/filter/EncodingFilter);
/* 103*/        return flag;
            }

            private final String useEncoding;
            private final Class encodingProviders[];
}
