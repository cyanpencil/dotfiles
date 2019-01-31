// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IndexedFilterImpl.java

package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

public class IndexedFilterImpl
    implements IndexedFilter
{

            public IndexedFilterImpl(String s, String s1)
            {
/*  62*/        contract = s;
/*  63*/        name = s1;
            }

            public boolean matches(Descriptor descriptor)
            {
/*  71*/        return true;
            }

            public String getAdvertisedContract()
            {
/*  79*/        return contract;
            }

            public String getName()
            {
/*  87*/        return name;
            }

            public String toString()
            {
/*  92*/        return (new StringBuilder("IndexedFilterImpl(")).append(contract).append(",").append(name).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final String contract;
            private final String name;
}
