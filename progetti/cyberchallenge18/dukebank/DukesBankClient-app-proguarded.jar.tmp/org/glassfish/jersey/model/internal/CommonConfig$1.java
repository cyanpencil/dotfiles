// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonConfig.java

package org.glassfish.jersey.model.internal;

import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.hk2.utilities.Binder;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            CommonConfig

static class 
    implements Function
{

            public final Binder apply(Object obj)
            {
/* 102*/        return (Binder)org/glassfish/hk2/utilities/Binder.cast(obj);
            }

            public final volatile Object apply(Object obj)
            {
/*  99*/        return apply(obj);
            }

            ()
            {
            }
}
