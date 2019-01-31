// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerLookupContext.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

public class PerLookupContext
    implements Context
{

            public PerLookupContext()
            {
            }

            public Class getScope()
            {
/*  61*/        return org/glassfish/hk2/api/PerLookup;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
/*  70*/        return activedescriptor.create(servicehandle);
            }

            public boolean containsKey(ActiveDescriptor activedescriptor)
            {
/*  78*/        return false;
            }

            public boolean isActive()
            {
/*  86*/        return true;
            }

            public boolean supportsNullCreation()
            {
/*  94*/        return true;
            }

            public void shutdown()
            {
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
            }
}
