// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ActiveDescriptorBuilderImpl.java

package org.glassfish.hk2.internal;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

// Referenced classes of package org.glassfish.hk2.internal:
//            ActiveDescriptorBuilderImpl

static class <init> extends AbstractActiveDescriptor
{

            public Class getImplementationClass()
            {
/* 363*/        return implementationClass;
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 371*/        throw new AssertionError("Should not be called directly");
            }

            private static final long serialVersionUID = 0x21c7cb494b190b62L;
            private Class implementationClass;

            public ()
            {
            }

            private (Class class1, Set set, Annotation annotation, Class class2, String s, Set set1, DescriptorType descriptortype, 
                    DescriptorVisibility descriptorvisibility, int i, Boolean boolean1, Boolean boolean2, String s1, Map map, HK2Loader hk2loader)
            {
/* 338*/        super(set, class2, s, set1, descriptortype, descriptorvisibility, i, boolean1, boolean2, s1, map);
/* 350*/        super.setReified(false);
/* 351*/        super.setLoader(hk2loader);
/* 352*/        super.setScopeAsAnnotation(annotation);
/* 354*/        implementationClass = class1;
/* 355*/        super.setImplementation(class1.getName());
            }

            implementationClass(Class class1, Set set, Annotation annotation, Class class2, String s, Set set1, DescriptorType descriptortype, 
                    DescriptorVisibility descriptorvisibility, int i, Boolean boolean1, Boolean boolean2, String s1, Map map, HK2Loader hk2loader, 
                    implementationClass implementationclass)
            {
/* 308*/        this(class1, set, annotation, class2, s, set1, descriptortype, descriptorvisibility, i, boolean1, boolean2, s1, map, hk2loader);
            }
}
