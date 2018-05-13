// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateErrorHandler.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.ActiveDescriptor;

public interface ImmediateErrorHandler
{

    public abstract void postConstructFailed(ActiveDescriptor activedescriptor, Throwable throwable);

    public abstract void preDestroyFailed(ActiveDescriptor activedescriptor, Throwable throwable);
}
