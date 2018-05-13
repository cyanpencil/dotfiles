// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceHandle.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            ActiveDescriptor

public interface ServiceHandle
{

    public abstract Object getService();

    public abstract ActiveDescriptor getActiveDescriptor();

    public abstract boolean isActive();

    public abstract void destroy();

    public abstract void setServiceData(Object obj);

    public abstract Object getServiceData();
}
