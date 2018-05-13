// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DynamicConfiguration.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            MultiException, Descriptor, ActiveDescriptor, FactoryDescriptors, 
//            Filter

public interface DynamicConfiguration
{

    public abstract ActiveDescriptor bind(Descriptor descriptor);

    public abstract ActiveDescriptor bind(Descriptor descriptor, boolean flag);

    public abstract FactoryDescriptors bind(FactoryDescriptors factorydescriptors);

    public abstract FactoryDescriptors bind(FactoryDescriptors factorydescriptors, boolean flag);

    public abstract ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor)
        throws IllegalArgumentException;

    public abstract ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor, boolean flag)
        throws IllegalArgumentException;

    public abstract ActiveDescriptor addActiveDescriptor(Class class1)
        throws MultiException, IllegalArgumentException;

    public abstract FactoryDescriptors addActiveFactoryDescriptor(Class class1)
        throws MultiException, IllegalArgumentException;

    public abstract void addUnbindFilter(Filter filter)
        throws IllegalArgumentException;

    public abstract void commit()
        throws MultiException;
}
