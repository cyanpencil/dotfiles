// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StarFilter.java

package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;

public class StarFilter
    implements Filter
{

            public StarFilter()
            {
            }

            public static StarFilter getDescriptorFilter()
            {
/*  57*/        return INSTANCE;
            }

            public boolean matches(Descriptor descriptor)
            {
/*  64*/        return true;
            }

            private static StarFilter INSTANCE = new StarFilter();

}
