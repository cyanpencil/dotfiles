// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryDescriptors.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            Descriptor

public interface FactoryDescriptors
{

    public abstract Descriptor getFactoryAsAService();

    public abstract Descriptor getFactoryAsAFactory();
}
