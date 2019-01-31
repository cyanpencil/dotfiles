// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ActiveDescriptor.java

package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.api:
//            Descriptor, SingleCache, ServiceHandle

public interface ActiveDescriptor
    extends Descriptor, SingleCache
{

    public abstract boolean isReified();

    public abstract Class getImplementationClass();

    public abstract Set getContractTypes();

    public abstract Annotation getScopeAsAnnotation();

    public abstract Class getScopeAnnotation();

    public abstract Set getQualifierAnnotations();

    public abstract List getInjectees();

    public abstract Long getFactoryServiceId();

    public abstract Long getFactoryLocatorId();

    public abstract Object create(ServiceHandle servicehandle);

    public abstract void dispose(Object obj);
}
