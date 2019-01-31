// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateLocalLocatorFilter.java

package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.*;

public class ImmediateLocalLocatorFilter
    implements Filter
{

            public ImmediateLocalLocatorFilter(long l)
            {
/*  56*/        locatorId = l;
            }

            public boolean matches(Descriptor descriptor)
            {
                String s;
/*  61*/        if((s = descriptor.getScope()) == null)
/*  62*/            return false;
/*  63*/        if(descriptor.getLocatorId().longValue() != locatorId)
/*  63*/            return false;
/*  65*/        else
/*  65*/            return org/glassfish/hk2/api/Immediate.getName().equals(s);
            }

            private final long locatorId;
}
