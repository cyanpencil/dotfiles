// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryDescriptorsImpl.java

package org.glassfish.hk2.utilities;

import java.util.Set;
import org.glassfish.hk2.api.*;

public class FactoryDescriptorsImpl
    implements FactoryDescriptors
{

            public FactoryDescriptorsImpl(Descriptor descriptor, Descriptor descriptor1)
            {
/*  73*/        if(descriptor == null || descriptor1 == null)
/*  73*/            throw new IllegalArgumentException();
/*  74*/        if(!DescriptorType.CLASS.equals(descriptor.getDescriptorType()))
/*  75*/            throw new IllegalArgumentException("Creation of FactoryDescriptors must have first argument of type CLASS");
/*  77*/        if(!descriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/Factory.getName()))
/*  78*/            throw new IllegalArgumentException("Creation of FactoryDescriptors must have Factory as a contract of the first argument");
/*  80*/        if(!DescriptorType.PROVIDE_METHOD.equals(descriptor1.getDescriptorType()))
                {
/*  81*/            throw new IllegalArgumentException("Creation of FactoryDescriptors must have second argument of type PROVIDE_METHOD");
                } else
                {
/*  84*/            asService = descriptor;
/*  85*/            asProvideMethod = descriptor1;
/*  86*/            return;
                }
            }

            public Descriptor getFactoryAsAService()
            {
/*  93*/        return asService;
            }

            public Descriptor getFactoryAsAFactory()
            {
/* 101*/        return asProvideMethod;
            }

            public int hashCode()
            {
/* 106*/        return asService.hashCode() ^ asProvideMethod.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 111*/        if(obj == null)
/* 111*/            return false;
/* 112*/        if(!(obj instanceof FactoryDescriptors))
/* 112*/            return false;
/* 114*/        Descriptor descriptor = ((FactoryDescriptors) (obj = (FactoryDescriptors)obj)).getFactoryAsAService();
/* 116*/        obj = ((FactoryDescriptors) (obj)).getFactoryAsAFactory();
/* 118*/        if(descriptor == null || obj == null)
/* 118*/            return false;
/* 120*/        return asService.equals(descriptor) && asProvideMethod.equals(obj);
            }

            public String toString()
            {
/* 125*/        return (new StringBuilder("FactoryDescriptorsImpl(\n")).append(asService).append(",\n").append(asProvideMethod).append(",\n\t").append(System.identityHashCode(this)).append(")").toString();
            }

            private final Descriptor asService;
            private final Descriptor asProvideMethod;
}
