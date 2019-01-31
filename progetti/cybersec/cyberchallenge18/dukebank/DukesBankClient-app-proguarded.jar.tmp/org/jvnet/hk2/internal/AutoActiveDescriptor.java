// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AutoActiveDescriptor.java

package org.jvnet.hk2.internal;

import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

// Referenced classes of package org.jvnet.hk2.internal:
//            ClazzCreator, Creator, SystemDescriptor

public class AutoActiveDescriptor extends AbstractActiveDescriptor
{

            public AutoActiveDescriptor()
            {
            }

            public AutoActiveDescriptor(Class class1, Creator creator1, Set set, Class class2, String s, Set set1, DescriptorVisibility descriptorvisibility, 
                    int i, Boolean boolean1, Boolean boolean2, String s1, Map map, DescriptorType descriptortype)
            {
/* 107*/        super(set, class2, s, set1, DescriptorType.CLASS, descriptorvisibility, i, boolean1, boolean2, s1, map);
/* 119*/        implClass = class1;
/* 120*/        creator = creator1;
/* 122*/        setImplementation(implClass.getName());
/* 123*/        setDescriptorType(descriptortype);
            }

            void resetSelfDescriptor(ActiveDescriptor activedescriptor)
            {
/* 127*/        if(!(creator instanceof ClazzCreator))
                {
/* 127*/            return;
                } else
                {
                    ClazzCreator clazzcreator;
/* 128*/            (clazzcreator = (ClazzCreator)creator).resetSelfDescriptor(activedescriptor);
/* 131*/            return;
                }
            }

            void setHK2Parent(SystemDescriptor systemdescriptor)
            {
/* 134*/        hk2Parent = systemdescriptor;
            }

            public Class getImplementationClass()
            {
/* 142*/        return implClass;
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 150*/        return creator.create(servicehandle, hk2Parent);
            }

            public void dispose(Object obj)
            {
/* 158*/        creator.dispose(obj);
            }

            public List getInjectees()
            {
/* 166*/        return creator.getInjectees();
            }

            private static final long serialVersionUID = 0x9210ea503a27f6ffL;
            private Class implClass;
            private Creator creator;
            private SystemDescriptor hk2Parent;
}
