// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorSupplier.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.ServiceLocator;

public interface ServiceLocatorSupplier
{

    public abstract ServiceLocator getServiceLocator();
}
