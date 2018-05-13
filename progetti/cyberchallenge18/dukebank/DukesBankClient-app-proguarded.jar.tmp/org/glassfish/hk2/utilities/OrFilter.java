// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OrFilter.java

package org.glassfish.hk2.utilities;

import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.general.GeneralUtilities;

public class OrFilter
    implements Filter
{

            public transient OrFilter(Filter afilter[])
            {
/*  72*/        allFilters = new ArrayList(afilter.length);
/*  74*/        int i = (afilter = afilter).length;
/*  74*/        for(int j = 0; j < i; j++)
                {
                    Filter filter;
/*  74*/            if((filter = afilter[j]) != null)
/*  76*/                allFilters.add(filter);
                }

            }

            public boolean matches(Descriptor descriptor)
            {
                Filter filter;
                Object obj;
                String s;
/*  86*/        for(Iterator iterator = allFilters.iterator(); iterator.hasNext();)
/*  86*/            if((!((filter = (Filter)iterator.next()) instanceof IndexedFilter) || ((s = ((IndexedFilter) (obj = (IndexedFilter)filter)).getName()) == null || GeneralUtilities.safeEquals(s, descriptor.getName())) && ((obj = ((IndexedFilter) (obj)).getAdvertisedContract()) == null || descriptor.getAdvertisedContracts().contains(obj))) && filter.matches(descriptor))
/* 101*/                return true;

/* 104*/        return false;
            }

            private final ArrayList allFilters;
}
