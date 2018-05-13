// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorFileFinder.java

package org.glassfish.hk2.api;

import java.io.IOException;
import java.util.List;

public interface DescriptorFileFinder
{

    public abstract List findDescriptorFiles()
        throws IOException;

    public static final String RESOURCE_BASE = "META-INF/hk2-locator/";
}
