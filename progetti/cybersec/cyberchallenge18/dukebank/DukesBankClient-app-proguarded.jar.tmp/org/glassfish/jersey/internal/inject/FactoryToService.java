// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryToService.java

package org.glassfish.jersey.internal.inject;

import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.hk2.api.Factory;

public final class FactoryToService
    implements Function
{

            public FactoryToService()
            {
            }

            public final Object apply(Factory factory)
            {
/*  59*/        if(factory != null)
/*  59*/            return factory.provide();
/*  59*/        else
/*  59*/            return null;
            }

            public final volatile Object apply(Object obj)
            {
/*  55*/        return apply((Factory)obj);
            }
}
