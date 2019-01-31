// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Initializable.java

package org.glassfish.jersey.client;


// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig

public interface Initializable
{

    public abstract Initializable preInitialize();

    public abstract ClientConfig getConfiguration();
}
