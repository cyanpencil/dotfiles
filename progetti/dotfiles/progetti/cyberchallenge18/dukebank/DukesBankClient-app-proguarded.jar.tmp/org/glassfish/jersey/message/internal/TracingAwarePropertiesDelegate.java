// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingAwarePropertiesDelegate.java

package org.glassfish.jersey.message.internal;

import java.util.Collection;
import org.glassfish.jersey.internal.PropertiesDelegate;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

public final class TracingAwarePropertiesDelegate
    implements PropertiesDelegate
{

            public TracingAwarePropertiesDelegate(PropertiesDelegate propertiesdelegate)
            {
/*  65*/        propertiesDelegate = propertiesdelegate;
            }

            public final void removeProperty(String s)
            {
/*  70*/        if(TracingLogger.PROPERTY_NAME.equals(s))
/*  71*/            tracingLogger = null;
/*  73*/        propertiesDelegate.removeProperty(s);
            }

            public final void setProperty(String s, Object obj)
            {
/*  78*/        if(TracingLogger.PROPERTY_NAME.equals(s))
/*  79*/            tracingLogger = (TracingLogger)obj;
/*  81*/        propertiesDelegate.setProperty(s, obj);
            }

            public final Object getProperty(String s)
            {
/*  86*/        if(tracingLogger != null && TracingLogger.PROPERTY_NAME.equals(s))
/*  87*/            return tracingLogger;
/*  89*/        else
/*  89*/            return propertiesDelegate.getProperty(s);
            }

            public final Collection getPropertyNames()
            {
/*  94*/        return propertiesDelegate.getPropertyNames();
            }

            private final PropertiesDelegate propertiesDelegate;
            private TracingLogger tracingLogger;
}
