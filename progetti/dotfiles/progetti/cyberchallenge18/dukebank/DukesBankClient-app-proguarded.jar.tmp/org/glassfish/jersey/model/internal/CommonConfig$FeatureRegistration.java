// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonConfig.java

package org.glassfish.jersey.model.internal;

import javax.ws.rs.core.Feature;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.hk2.utilities.Binder;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            CommonConfig

static final class feature
{

            public final Class getFeatureClass()
            {
/* 162*/        return featureClass;
            }

            public final Feature getFeature()
            {
/* 173*/        return feature;
            }

            public final boolean equals(Object obj)
            {
/* 178*/        if(this == obj)
/* 179*/            return true;
/* 181*/        if(!(obj instanceof feature))
/* 182*/            return false;
/* 184*/        obj = (feature)obj;
/* 186*/        return featureClass == ((featureClass) (obj)).featureClass || feature != null && (feature == ((feature) (obj)).feature || feature.equals(((feature) (obj)).feature));
            }

            public final int hashCode()
            {
/* 193*/        int i = 611 + (feature == null ? 0 : feature.hashCode());
/* 194*/        return i = i * 13 + (featureClass == null ? 0 : featureClass.hashCode());
            }

            private final Class featureClass;
            private final Feature feature;

            private (Class class1)
            {
/* 147*/        featureClass = class1;
/* 148*/        feature = null;
            }

            private feature(Feature feature1)
            {
/* 152*/        featureClass = feature1.getClass();
/* 153*/        feature = feature1;
            }



            // Unreferenced inner class org/glassfish/jersey/model/internal/CommonConfig$1

/* anonymous class */
    static class CommonConfig._cls1
        implements Function
    {

                public final Binder apply(Object obj)
                {
/* 102*/            return (Binder)org/glassfish/hk2/utilities/Binder.cast(obj);
                }

                public final volatile Object apply(Object obj)
                {
/*  99*/            return apply(obj);
                }

    }

}
