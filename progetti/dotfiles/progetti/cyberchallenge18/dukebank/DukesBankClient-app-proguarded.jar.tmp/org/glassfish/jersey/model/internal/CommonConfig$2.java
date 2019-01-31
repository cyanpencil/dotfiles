// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonConfig.java

package org.glassfish.jersey.model.internal;

import java.util.Comparator;
import javax.annotation.Priority;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            CommonConfig

class this._cls0
    implements Comparator
{

            public int compare(AutoDiscoverable autodiscoverable, AutoDiscoverable autodiscoverable1)
            {
/* 597*/        autodiscoverable = autodiscoverable.getClass().isAnnotationPresent(javax/annotation/Priority) ? ((AutoDiscoverable) (((Priority)autodiscoverable.getClass().getAnnotation(javax/annotation/Priority)).value())) : 5000;
/* 599*/        autodiscoverable1 = autodiscoverable1.getClass().isAnnotationPresent(javax/annotation/Priority) ? ((AutoDiscoverable) (((Priority)autodiscoverable1.getClass().getAnnotation(javax/annotation/Priority)).value())) : 5000;
/* 602*/        return autodiscoverable >= autodiscoverable1 && autodiscoverable != autodiscoverable1 ? 1 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 594*/        return compare((AutoDiscoverable)obj, (AutoDiscoverable)obj1);
            }

            final CommonConfig this$0;

            ()
            {
/* 594*/        this$0 = CommonConfig.this;
/* 594*/        super();
            }
}
