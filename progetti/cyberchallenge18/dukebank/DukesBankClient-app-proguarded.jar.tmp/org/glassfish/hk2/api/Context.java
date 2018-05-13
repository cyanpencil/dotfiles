// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Context.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            ActiveDescriptor, ServiceHandle

public interface Context
{

    public abstract Class getScope();

    public abstract Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle);

    public abstract boolean containsKey(ActiveDescriptor activedescriptor);

    public abstract void destroyOne(ActiveDescriptor activedescriptor);

    public abstract boolean supportsNullCreation();

    public abstract boolean isActive();

    public abstract void shutdown();
}
