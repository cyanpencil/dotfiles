// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PopulatorPostProcessor.java

package org.glassfish.hk2.api;

import org.glassfish.hk2.utilities.DescriptorImpl;

// Referenced classes of package org.glassfish.hk2.api:
//            ServiceLocator

public interface PopulatorPostProcessor
{

    public abstract DescriptorImpl process(ServiceLocator servicelocator, DescriptorImpl descriptorimpl);
}
