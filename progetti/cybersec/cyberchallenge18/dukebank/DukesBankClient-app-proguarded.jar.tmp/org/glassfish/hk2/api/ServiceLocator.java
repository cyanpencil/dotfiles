// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocator.java

package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

// Referenced classes of package org.glassfish.hk2.api:
//            MultiException, Filter, ServiceHandle, ActiveDescriptor, 
//            Descriptor, Injectee, Unqualified, ServiceLocatorState

public interface ServiceLocator
{

    public transient abstract Object getService(Class class1, Annotation aannotation[])
        throws MultiException;

    public transient abstract Object getService(Type type, Annotation aannotation[])
        throws MultiException;

    public transient abstract Object getService(Class class1, String s, Annotation aannotation[])
        throws MultiException;

    public transient abstract Object getService(Type type, String s, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServices(Class class1, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServices(Type type, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServices(Annotation annotation, Annotation aannotation[])
        throws MultiException;

    public abstract List getAllServices(Filter filter)
        throws MultiException;

    public transient abstract ServiceHandle getServiceHandle(Class class1, Annotation aannotation[])
        throws MultiException;

    public transient abstract ServiceHandle getServiceHandle(Type type, Annotation aannotation[])
        throws MultiException;

    public transient abstract ServiceHandle getServiceHandle(Class class1, String s, Annotation aannotation[])
        throws MultiException;

    public transient abstract ServiceHandle getServiceHandle(Type type, String s, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServiceHandles(Class class1, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServiceHandles(Type type, Annotation aannotation[])
        throws MultiException;

    public transient abstract List getAllServiceHandles(Annotation annotation, Annotation aannotation[])
        throws MultiException;

    public abstract List getAllServiceHandles(Filter filter)
        throws MultiException;

    public abstract List getDescriptors(Filter filter);

    public abstract ActiveDescriptor getBestDescriptor(Filter filter);

    public abstract ActiveDescriptor reifyDescriptor(Descriptor descriptor, Injectee injectee)
        throws MultiException;

    public abstract ActiveDescriptor reifyDescriptor(Descriptor descriptor)
        throws MultiException;

    public abstract ActiveDescriptor getInjecteeDescriptor(Injectee injectee)
        throws MultiException;

    public abstract ServiceHandle getServiceHandle(ActiveDescriptor activedescriptor, Injectee injectee)
        throws MultiException;

    public abstract ServiceHandle getServiceHandle(ActiveDescriptor activedescriptor)
        throws MultiException;

    /**
     * @deprecated Method getService is deprecated
     */

    public abstract Object getService(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
        throws MultiException;

    public abstract Object getService(ActiveDescriptor activedescriptor, ServiceHandle servicehandle, Injectee injectee)
        throws MultiException;

    public abstract String getDefaultClassAnalyzerName();

    public abstract void setDefaultClassAnalyzerName(String s);

    public abstract Unqualified getDefaultUnqualified();

    public abstract void setDefaultUnqualified(Unqualified unqualified);

    public abstract String getName();

    public abstract long getLocatorId();

    public abstract ServiceLocator getParent();

    public abstract void shutdown();

    public abstract ServiceLocatorState getState();

    public abstract boolean getNeutralContextClassLoader();

    public abstract void setNeutralContextClassLoader(boolean flag);

    public abstract Object create(Class class1);

    public abstract Object create(Class class1, String s);

    public abstract void inject(Object obj);

    public abstract void inject(Object obj, String s);

    public abstract void postConstruct(Object obj);

    public abstract void postConstruct(Object obj, String s);

    public abstract void preDestroy(Object obj);

    public abstract void preDestroy(Object obj, String s);

    public abstract Object createAndInitialize(Class class1);

    public abstract Object createAndInitialize(Class class1, String s);
}
