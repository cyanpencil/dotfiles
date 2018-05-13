// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AsyncConnectorCallback.java

package org.glassfish.jersey.client.spi;

import org.glassfish.jersey.client.ClientResponse;

public interface AsyncConnectorCallback
{

    public abstract void response(ClientResponse clientresponse);

    public abstract void failure(Throwable throwable);
}
