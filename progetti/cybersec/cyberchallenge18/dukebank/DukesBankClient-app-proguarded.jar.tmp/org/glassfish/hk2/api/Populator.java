// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Populator.java

package org.glassfish.hk2.api;

import java.io.IOException;
import java.util.List;

// Referenced classes of package org.glassfish.hk2.api:
//            MultiException, DescriptorFileFinder, PopulatorPostProcessor

public interface Populator
{

    public transient abstract List populate(DescriptorFileFinder descriptorfilefinder, PopulatorPostProcessor apopulatorpostprocessor[])
        throws IOException, MultiException;

    public abstract List populate()
        throws IOException, MultiException;
}
