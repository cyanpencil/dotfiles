// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientLifecycleListener.java

package org.glassfish.jersey.client;


public interface ClientLifecycleListener
{

    public abstract void onInit();

    public abstract void onClose();
}
