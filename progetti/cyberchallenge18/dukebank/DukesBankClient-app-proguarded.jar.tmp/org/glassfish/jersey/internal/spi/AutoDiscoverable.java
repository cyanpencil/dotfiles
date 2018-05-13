// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AutoDiscoverable.java

package org.glassfish.jersey.internal.spi;

import javax.ws.rs.core.FeatureContext;

public interface AutoDiscoverable
{

    public abstract void configure(FeatureContext featurecontext);

    public static final int DEFAULT_PRIORITY = 2000;
}
