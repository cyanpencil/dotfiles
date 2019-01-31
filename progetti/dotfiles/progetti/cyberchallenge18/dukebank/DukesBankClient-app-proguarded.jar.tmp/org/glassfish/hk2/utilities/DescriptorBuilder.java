// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorBuilder.java

package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl

public interface DescriptorBuilder
{

    public abstract DescriptorBuilder named(String s)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder to(Class class1)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder to(String s)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder in(Class class1)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder in(String s)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder qualifiedBy(Annotation annotation)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder qualifiedBy(String s)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder has(String s, String s1)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder has(String s, List list)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder ofRank(int i);

    public abstract DescriptorBuilder proxy();

    public abstract DescriptorBuilder proxy(boolean flag);

    public abstract DescriptorBuilder proxyForSameScope();

    public abstract DescriptorBuilder proxyForSameScope(boolean flag);

    public abstract DescriptorBuilder localOnly();

    public abstract DescriptorBuilder visibility(DescriptorVisibility descriptorvisibility);

    public abstract DescriptorBuilder andLoadWith(HK2Loader hk2loader)
        throws IllegalArgumentException;

    public abstract DescriptorBuilder analyzeWith(String s);

    public abstract DescriptorImpl build()
        throws IllegalArgumentException;

    public abstract FactoryDescriptors buildFactory()
        throws IllegalArgumentException;

    public abstract FactoryDescriptors buildFactory(String s)
        throws IllegalArgumentException;

    public abstract FactoryDescriptors buildFactory(Class class1)
        throws IllegalArgumentException;
}
