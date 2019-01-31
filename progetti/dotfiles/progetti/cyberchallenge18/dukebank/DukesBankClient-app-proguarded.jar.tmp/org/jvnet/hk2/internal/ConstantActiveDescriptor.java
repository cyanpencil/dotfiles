// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstantActiveDescriptor.java

package org.jvnet.hk2.internal;

import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

// Referenced classes of package org.jvnet.hk2.internal:
//            PerLocatorUtilities, ServiceLocatorImpl

public class ConstantActiveDescriptor extends AbstractActiveDescriptor
{

            public ConstantActiveDescriptor()
            {
            }

            public ConstantActiveDescriptor(Object obj, ServiceLocatorImpl servicelocatorimpl)
            {
/*  84*/        super(new HashSet(), org/glassfish/hk2/api/PerLookup, null, new HashSet(), DescriptorType.CLASS, DescriptorVisibility.NORMAL, 0, null, null, servicelocatorimpl.getPerLocatorUtilities().getAutoAnalyzerName(obj.getClass()), null);
/*  96*/        theOne = obj;
/*  97*/        locatorId = Long.valueOf(servicelocatorimpl.getLocatorId());
            }

            public ConstantActiveDescriptor(Object obj, Set set, Class class1, String s, Set set1, DescriptorVisibility descriptorvisibility, int i, 
                    Boolean boolean1, Boolean boolean2, String s1, long l, Map map)
            {
/* 128*/        super(set, class1, s, set1, DescriptorType.CLASS, descriptorvisibility, i, boolean1, boolean2, s1, map);
/* 139*/        if(obj == null)
                {
/* 139*/            throw new IllegalArgumentException();
                } else
                {
/* 141*/            theOne = obj;
/* 142*/            locatorId = Long.valueOf(l);
/* 143*/            return;
                }
            }

            public String getImplementation()
            {
/* 150*/        return theOne.getClass().getName();
            }

            public Long getLocatorId()
            {
/* 154*/        return locatorId;
            }

            public Object getCache()
            {
/* 162*/        return theOne;
            }

            public boolean isCacheSet()
            {
/* 170*/        return true;
            }

            public Class getImplementationClass()
            {
/* 178*/        return theOne.getClass();
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 186*/        return theOne;
            }

            public void dispose(Object obj)
            {
            }

            private static final long serialVersionUID = 0x32d5c917c904fa05L;
            private Object theOne;
            private Long locatorId;
}
