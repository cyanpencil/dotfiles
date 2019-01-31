// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorRuntimeBean.java

package org.jvnet.hk2.external.runtime;


public interface ServiceLocatorRuntimeBean
{

    public abstract int getNumberOfDescriptors();

    public abstract int getNumberOfChildren();

    public abstract int getServiceCacheSize();

    public abstract int getServiceCacheMaximumSize();

    public abstract void clearServiceCache();

    public abstract int getReflectionCacheSize();

    public abstract void clearReflectionCache();
}
