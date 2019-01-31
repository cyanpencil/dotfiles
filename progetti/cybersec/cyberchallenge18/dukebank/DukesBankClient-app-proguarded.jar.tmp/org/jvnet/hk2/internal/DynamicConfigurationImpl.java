// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DynamicConfigurationImpl.java

package org.jvnet.hk2.internal;

import java.util.LinkedList;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.FactoryDescriptorsImpl;
import org.glassfish.hk2.utilities.reflection.Pretty;

// Referenced classes of package org.jvnet.hk2.internal:
//            AutoActiveDescriptor, Collector, ServiceLocatorImpl, SystemDescriptor, 
//            Utilities

public class DynamicConfigurationImpl
    implements DynamicConfiguration
{

            public DynamicConfigurationImpl(ServiceLocatorImpl servicelocatorimpl)
            {
/*  67*/        committed = false;
/*  75*/        locator = servicelocatorimpl;
            }

            public ActiveDescriptor bind(Descriptor descriptor)
            {
/*  83*/        return bind(descriptor, true);
            }

            public ActiveDescriptor bind(Descriptor descriptor, boolean flag)
            {
/*  88*/        checkState();
/*  89*/        checkDescriptor(descriptor);
/*  91*/        descriptor = new SystemDescriptor(descriptor, flag, locator, Long.valueOf(locator.getNextServiceId()));
/*  96*/        allDescriptors.add(descriptor);
/*  98*/        return descriptor;
            }

            public FactoryDescriptors bind(FactoryDescriptors factorydescriptors)
            {
/* 106*/        return bind(factorydescriptors, true);
            }

            public FactoryDescriptors bind(FactoryDescriptors factorydescriptors, boolean flag)
            {
/* 111*/        if(factorydescriptors == null)
/* 111*/            throw new IllegalArgumentException("factoryDescriptors is null");
/* 114*/        Descriptor descriptor = factorydescriptors.getFactoryAsAService();
/* 115*/        factorydescriptors = factorydescriptors.getFactoryAsAFactory();
/* 117*/        checkDescriptor(descriptor);
/* 118*/        checkDescriptor(factorydescriptors);
/* 120*/        Object obj = descriptor.getImplementation();
/* 121*/        String s = factorydescriptors.getImplementation();
/* 123*/        if(!((String) (obj)).equals(s))
/* 124*/            throw new IllegalArgumentException((new StringBuilder("The implementation classes must match (")).append(((String) (obj))).append("/").append(s).append(")").toString());
/* 128*/        if(!descriptor.getDescriptorType().equals(DescriptorType.CLASS))
/* 129*/            throw new IllegalArgumentException("The getFactoryAsService descriptor must be of type CLASS");
/* 131*/        if(!factorydescriptors.getDescriptorType().equals(DescriptorType.PROVIDE_METHOD))
/* 132*/            throw new IllegalArgumentException("The getFactoryAsFactory descriptor must be of type PROVIDE_METHOD");
/* 135*/        obj = new SystemDescriptor(descriptor, flag, locator, Long.valueOf(locator.getNextServiceId()));
/* 141*/        factorydescriptors = new SystemDescriptor(factorydescriptors, flag, locator, Long.valueOf(locator.getNextServiceId()));
/* 146*/        if(descriptor instanceof ActiveDescriptor)
/* 147*/            factorydescriptors.setFactoryIds(((SystemDescriptor) (obj)).getLocatorId(), ((SystemDescriptor) (obj)).getServiceId());
/* 152*/        allDescriptors.add(factorydescriptors);
/* 153*/        allDescriptors.add(obj);
/* 155*/        return new FactoryDescriptorsImpl(((Descriptor) (obj)), factorydescriptors);
            }

            public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor)
                throws IllegalArgumentException
            {
/* 164*/        return addActiveDescriptor(activedescriptor, true);
            }

            public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor, boolean flag)
                throws IllegalArgumentException
            {
/* 170*/        checkState();
/* 171*/        checkDescriptor(activedescriptor);
/* 173*/        if(!activedescriptor.isReified())
                {
/* 174*/            throw new IllegalArgumentException();
                } else
                {
/* 177*/            checkReifiedDescriptor(activedescriptor);
/* 179*/            activedescriptor = new SystemDescriptor(activedescriptor, flag, locator, Long.valueOf(locator.getNextServiceId()));
/* 184*/            allDescriptors.add(activedescriptor);
/* 186*/            return activedescriptor;
                }
            }

            public ActiveDescriptor addActiveDescriptor(Class class1)
                throws IllegalArgumentException
            {
/* 195*/        checkReifiedDescriptor(class1 = Utilities.createAutoDescriptor(class1, locator));
/* 199*/        ActiveDescriptor activedescriptor = addActiveDescriptor(((ActiveDescriptor) (class1)), false);
/* 201*/        class1.resetSelfDescriptor(activedescriptor);
/* 203*/        return activedescriptor;
            }

            public FactoryDescriptors addActiveFactoryDescriptor(Class class1)
                throws MultiException, IllegalArgumentException
            {
/* 213*/        Object obj = new Collector();
/* 214*/        Utilities.checkFactoryType(class1, ((Collector) (obj)));
/* 215*/        ((Collector) (obj)).throwIfErrors();
/* 217*/        obj = addActiveDescriptor(class1);
/* 218*/        class1 = Utilities.createAutoFactoryDescriptor(class1, ((ActiveDescriptor) (obj)), locator);
/* 219*/        class1 = addActiveDescriptor(class1);
/* 221*/        return new FactoryDescriptorsImpl(((Descriptor) (obj)), class1);
            }

            public void addUnbindFilter(Filter filter)
                throws IllegalArgumentException
            {
/* 230*/        if(filter == null)
                {
/* 230*/            throw new IllegalArgumentException();
                } else
                {
/* 232*/            allUnbindFilters.add(filter);
/* 233*/            return;
                }
            }

            public void commit()
                throws MultiException
            {
/* 240*/        synchronized(lock)
                {
/* 241*/            checkState();
/* 243*/            committed = true;
                }
/* 246*/        locator.addConfiguration(this);
            }

            private void checkState()
            {
/* 250*/        synchronized(lock)
                {
/* 251*/            if(committed)
/* 251*/                throw new IllegalStateException();
                }
            }

            private static void checkDescriptor(Descriptor descriptor)
            {
/* 256*/        if(descriptor == null)
/* 256*/            throw new IllegalArgumentException();
/* 257*/        if(descriptor.getImplementation() == null)
/* 257*/            throw new IllegalArgumentException();
/* 258*/        if(descriptor.getAdvertisedContracts() == null)
/* 258*/            throw new IllegalArgumentException();
/* 259*/        if(descriptor.getDescriptorType() == null)
/* 259*/            throw new IllegalArgumentException();
/* 260*/        if(descriptor.getDescriptorVisibility() == null)
/* 260*/            throw new IllegalArgumentException();
/* 261*/        if(descriptor.getMetadata() == null)
/* 261*/            throw new IllegalArgumentException();
/* 262*/        if(descriptor.getQualifiers() == null)
/* 262*/            throw new IllegalArgumentException();
/* 263*/        else
/* 263*/            return;
            }

            private static void checkReifiedDescriptor(ActiveDescriptor activedescriptor)
            {
/* 266*/        if(activedescriptor.isProxiable() == null)
/* 266*/            return;
/* 267*/        if(!activedescriptor.isProxiable().booleanValue())
/* 267*/            return;
/* 270*/        if(Utilities.isUnproxiableScope(activedescriptor.getScopeAnnotation()))
/* 270*/            throw new IllegalArgumentException();
/* 271*/        else
/* 271*/            return;
            }

            LinkedList getAllDescriptors()
            {
/* 277*/        return allDescriptors;
            }

            LinkedList getUnbindFilters()
            {
/* 281*/        return allUnbindFilters;
            }

            public String toString()
            {
/* 285*/        return (new StringBuilder("DynamicConfigurationImpl(")).append(locator).append(",").append(Pretty.collection(allDescriptors)).append(",").append(Pretty.collection(allUnbindFilters)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ServiceLocatorImpl locator;
            private final LinkedList allDescriptors = new LinkedList();
            private final LinkedList allUnbindFilters = new LinkedList();
            private final Object lock = new Object();
            private boolean committed;
}
