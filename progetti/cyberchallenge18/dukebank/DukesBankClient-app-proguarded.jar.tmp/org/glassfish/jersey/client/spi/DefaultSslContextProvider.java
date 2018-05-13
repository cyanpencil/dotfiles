// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultSslContextProvider.java

package org.glassfish.jersey.client.spi;

import javax.net.ssl.SSLContext;

public interface DefaultSslContextProvider
{

    public abstract SSLContext getDefaultSslContext();
}
