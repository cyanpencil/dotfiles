// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProviderToService.java

package org.glassfish.jersey.internal.inject;

import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.hk2.api.ServiceHandle;

public final class ProviderToService
    implements Function
{

            public ProviderToService()
            {
            }

            public final Object apply(ServiceHandle servicehandle)
            {
/*  58*/        if(servicehandle != null)
/*  58*/            return servicehandle.getService();
/*  58*/        else
/*  58*/            return null;
            }

            public final volatile Object apply(Object obj)
            {
/*  54*/        return apply((ServiceHandle)obj);
            }
}
