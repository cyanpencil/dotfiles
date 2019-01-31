// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExtendedConfig.java

package org.glassfish.jersey;

import javax.ws.rs.core.Configuration;

public interface ExtendedConfig
    extends Configuration
{

    public abstract boolean isProperty(String s);
}
