// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ActiveDescriptorBuilder.java

package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.HK2Loader;

// Referenced classes of package org.glassfish.hk2.utilities:
//            AbstractActiveDescriptor

public interface ActiveDescriptorBuilder
{

    public abstract ActiveDescriptorBuilder named(String s)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder to(Type type)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder in(Annotation annotation)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder in(Class class1)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder qualifiedBy(Annotation annotation)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder has(String s, String s1)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder has(String s, List list)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder ofRank(int i);

    public abstract ActiveDescriptorBuilder localOnly();

    public abstract ActiveDescriptorBuilder visibility(DescriptorVisibility descriptorvisibility);

    public abstract ActiveDescriptorBuilder proxy();

    public abstract ActiveDescriptorBuilder proxy(boolean flag);

    public abstract ActiveDescriptorBuilder proxyForSameScope();

    public abstract ActiveDescriptorBuilder proxyForSameScope(boolean flag);

    public abstract ActiveDescriptorBuilder andLoadWith(HK2Loader hk2loader)
        throws IllegalArgumentException;

    public abstract ActiveDescriptorBuilder analyzeWith(String s);

    public abstract AbstractActiveDescriptor build()
        throws IllegalArgumentException;

    /**
     * @deprecated Method buildFactory is deprecated
     */

    public abstract AbstractActiveDescriptor buildFactory()
        throws IllegalArgumentException;

    public abstract AbstractActiveDescriptor buildProvideMethod()
        throws IllegalArgumentException;
}
