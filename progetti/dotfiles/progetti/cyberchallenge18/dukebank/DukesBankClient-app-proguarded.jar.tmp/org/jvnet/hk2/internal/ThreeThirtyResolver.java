// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreeThirtyResolver.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

public class ThreeThirtyResolver
    implements InjectionResolver
{

            ThreeThirtyResolver(ServiceLocatorImpl servicelocatorimpl)
            {
/*  62*/        locator = servicelocatorimpl;
            }

            public Object resolve(Injectee injectee, ServiceHandle servicehandle)
            {
                ActiveDescriptor activedescriptor;
/*  70*/        if((activedescriptor = locator.getInjecteeDescriptor(injectee)) == null)
                {
/*  73*/            if(injectee.isOptional())
/*  73*/                return null;
/*  75*/            else
/*  75*/                throw new MultiException(new UnsatisfiedDependencyException(injectee));
                } else
                {
/*  78*/            return locator.getService(activedescriptor, servicehandle, injectee);
                }
            }

            public boolean isConstructorParameterIndicator()
            {
/*  86*/        return false;
            }

            public boolean isMethodParameterIndicator()
            {
/*  94*/        return false;
            }

            public String toString()
            {
/*  99*/        return (new StringBuilder("ThreeThirtyResolver(")).append(locator).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ServiceLocatorImpl locator;
}
