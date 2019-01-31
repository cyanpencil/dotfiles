// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Descriptor.java

package org.glassfish.hk2.api;

import java.util.Map;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.api:
//            DescriptorType, DescriptorVisibility, HK2Loader

public interface Descriptor
{

    public abstract String getImplementation();

    public abstract Set getAdvertisedContracts();

    public abstract String getScope();

    public abstract String getName();

    public abstract Set getQualifiers();

    public abstract DescriptorType getDescriptorType();

    public abstract DescriptorVisibility getDescriptorVisibility();

    public abstract Map getMetadata();

    public abstract HK2Loader getLoader();

    public abstract int getRanking();

    public abstract int setRanking(int i);

    public abstract Boolean isProxiable();

    public abstract Boolean isProxyForSameScope();

    public abstract String getClassAnalysisName();

    public abstract Long getServiceId();

    public abstract Long getLocatorId();
}
