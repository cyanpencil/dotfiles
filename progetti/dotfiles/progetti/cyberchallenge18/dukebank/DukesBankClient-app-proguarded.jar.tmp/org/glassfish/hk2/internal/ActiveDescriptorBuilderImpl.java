// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ActiveDescriptorBuilderImpl.java

package org.glassfish.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import javax.inject.Named;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;
import org.glassfish.hk2.utilities.ActiveDescriptorBuilder;

public class ActiveDescriptorBuilderImpl
    implements ActiveDescriptorBuilder
{
    static class BuiltActiveDescriptor extends AbstractActiveDescriptor
    {

                public Class getImplementationClass()
                {
/* 363*/            return implementationClass;
                }

                public Object create(ServiceHandle servicehandle)
                {
/* 371*/            throw new AssertionError("Should not be called directly");
                }

                private static final long serialVersionUID = 0x21c7cb494b190b62L;
                private Class implementationClass;

                public BuiltActiveDescriptor()
                {
                }

                private BuiltActiveDescriptor(Class class1, Set set, Annotation annotation, Class class2, String s, Set set1, DescriptorType descriptortype, 
                        DescriptorVisibility descriptorvisibility, int i, Boolean boolean1, Boolean boolean2, String s1, Map map, HK2Loader hk2loader)
                {
/* 338*/            super(set, class2, s, set1, descriptortype, descriptorvisibility, i, boolean1, boolean2, s1, map);
/* 350*/            super.setReified(false);
/* 351*/            super.setLoader(hk2loader);
/* 352*/            super.setScopeAsAnnotation(annotation);
/* 354*/            implementationClass = class1;
/* 355*/            super.setImplementation(class1.getName());
                }

    }


            public ActiveDescriptorBuilderImpl(Class class1)
            {
/*  68*/        scopeAnnotation = null;
/*  69*/        scope = org/glassfish/hk2/api/PerLookup;
/*  73*/        loader = null;
/*  74*/        rank = 0;
/*  75*/        proxy = null;
/*  76*/        proxyForSameScope = null;
/*  77*/        visibility = DescriptorVisibility.NORMAL;
/*  78*/        classAnalysisName = null;
/*  86*/        implementation = class1;
            }

            public ActiveDescriptorBuilder named(String s)
                throws IllegalArgumentException
            {
/*  94*/        name = s;
/*  96*/        return this;
            }

            public ActiveDescriptorBuilder to(Type type)
                throws IllegalArgumentException
            {
/* 104*/        if(type != null)
/* 104*/            contracts.add(type);
/* 106*/        return this;
            }

            public ActiveDescriptorBuilder in(Annotation annotation)
                throws IllegalArgumentException
            {
/* 112*/        if(annotation == null)
                {
/* 112*/            throw new IllegalArgumentException();
                } else
                {
/* 114*/            scopeAnnotation = annotation;
/* 115*/            scope = annotation.annotationType();
/* 117*/            return this;
                }
            }

            public ActiveDescriptorBuilder in(Class class1)
                throws IllegalArgumentException
            {
/* 126*/        scope = class1;
/* 127*/        if(class1 == null)
/* 128*/            scopeAnnotation = null;
/* 130*/        else
/* 130*/        if(scopeAnnotation != null && !class1.equals(scopeAnnotation.annotationType()))
/* 131*/            throw new IllegalArgumentException((new StringBuilder("Scope set to different class (")).append(class1.getName()).append(") from the scope annotation (").append(scopeAnnotation.annotationType().getName()).toString());
/* 137*/        return this;
            }

            public ActiveDescriptorBuilder qualifiedBy(Annotation annotation)
                throws IllegalArgumentException
            {
/* 146*/        if(annotation != null)
                {
/* 147*/            if(javax/inject/Named.equals(annotation.annotationType()))
/* 148*/                name = ((Named)annotation).value();
/* 150*/            qualifiers.add(annotation);
                }
/* 153*/        return this;
            }

            public ActiveDescriptorBuilder has(String s, String s1)
                throws IllegalArgumentException
            {
/* 161*/        return has(s, Collections.singletonList(s1));
            }

            public ActiveDescriptorBuilder has(String s, List list)
                throws IllegalArgumentException
            {
/* 169*/        if(s == null || list == null || list.size() <= 0)
                {
/* 170*/            throw new IllegalArgumentException();
                } else
                {
/* 173*/            metadatas.put(s, list);
/* 175*/            return this;
                }
            }

            public ActiveDescriptorBuilder ofRank(int i)
            {
/* 183*/        rank = i;
/* 185*/        return this;
            }

            public ActiveDescriptorBuilder proxy()
            {
/* 190*/        return proxy(true);
            }

            public ActiveDescriptorBuilder proxy(boolean flag)
            {
/* 195*/        if(flag)
/* 196*/            proxy = Boolean.TRUE;
/* 199*/        else
/* 199*/            proxy = Boolean.FALSE;
/* 202*/        return this;
            }

            public ActiveDescriptorBuilder proxyForSameScope()
            {
/* 207*/        return proxy(true);
            }

            public ActiveDescriptorBuilder proxyForSameScope(boolean flag)
            {
/* 212*/        if(flag)
/* 213*/            proxyForSameScope = Boolean.TRUE;
/* 216*/        else
/* 216*/            proxyForSameScope = Boolean.FALSE;
/* 219*/        return this;
            }

            public ActiveDescriptorBuilder andLoadWith(HK2Loader hk2loader)
                throws IllegalArgumentException
            {
/* 228*/        loader = hk2loader;
/* 230*/        return this;
            }

            public ActiveDescriptorBuilder analyzeWith(String s)
            {
/* 234*/        classAnalysisName = s;
/* 236*/        return this;
            }

            public ActiveDescriptorBuilder localOnly()
            {
/* 241*/        visibility = DescriptorVisibility.LOCAL;
/* 243*/        return this;
            }

            public ActiveDescriptorBuilder visibility(DescriptorVisibility descriptorvisibility)
            {
/* 248*/        if(descriptorvisibility == null)
                {
/* 248*/            throw new IllegalArgumentException();
                } else
                {
/* 250*/            visibility = descriptorvisibility;
/* 252*/            return this;
                }
            }

            public AbstractActiveDescriptor build()
                throws IllegalArgumentException
            {
/* 260*/        return new BuiltActiveDescriptor(implementation, contracts, scopeAnnotation, scope, name, qualifiers, DescriptorType.CLASS, visibility, rank, proxy, proxyForSameScope, classAnalysisName, metadatas, loader);
            }

            /**
             * @deprecated Method buildFactory is deprecated
             */

            public AbstractActiveDescriptor buildFactory()
                throws IllegalArgumentException
            {
/* 283*/        return buildProvideMethod();
            }

            public AbstractActiveDescriptor buildProvideMethod()
                throws IllegalArgumentException
            {
/* 291*/        return new BuiltActiveDescriptor(implementation, contracts, scopeAnnotation, scope, name, qualifiers, DescriptorType.PROVIDE_METHOD, visibility, rank, proxy, proxyForSameScope, classAnalysisName, metadatas, loader);
            }

            private String name;
            private final HashSet contracts = new HashSet();
            private Annotation scopeAnnotation;
            private Class scope;
            private final HashSet qualifiers = new HashSet();
            private final HashMap metadatas = new HashMap();
            private final Class implementation;
            private HK2Loader loader;
            private int rank;
            private Boolean proxy;
            private Boolean proxyForSameScope;
            private DescriptorVisibility visibility;
            private String classAnalysisName;
}
