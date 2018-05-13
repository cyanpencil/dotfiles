// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstantActiveDescriptor.java

package org.glassfish.hk2.internal;

import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

public class ConstantActiveDescriptor extends AbstractActiveDescriptor
{

            public ConstantActiveDescriptor()
            {
/*  71*/        theOne = null;
            }

            public ConstantActiveDescriptor(Object obj, Set set, Class class1, String s, Set set1, DescriptorVisibility descriptorvisibility, Boolean boolean1, 
                    Boolean boolean2, String s1, Map map, int i)
            {
/* 100*/        super(set, class1, s, set1, DescriptorType.CLASS, descriptorvisibility, i, boolean1, boolean2, s1, map);
/* 111*/        if(obj == null)
                {
/* 111*/            throw new IllegalArgumentException();
                } else
                {
/* 113*/            theOne = obj;
/* 114*/            return;
                }
            }

            public String getImplementation()
            {
/* 121*/        return theOne.getClass().getName();
            }

            public Object getCache()
            {
/* 129*/        return theOne;
            }

            public boolean isCacheSet()
            {
/* 137*/        return true;
            }

            public Class getImplementationClass()
            {
/* 145*/        return theOne.getClass();
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 153*/        return theOne;
            }

            private static final long serialVersionUID = 0x805fdb5dd17437a1L;
            private final Object theOne;
}
