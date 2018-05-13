// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorGenerator.java

package org.glassfish.hk2.extension;

import org.glassfish.hk2.api.ServiceLocator;

public interface ServiceLocatorGenerator
{

    public abstract ServiceLocator create(String s, ServiceLocator servicelocator);
}
