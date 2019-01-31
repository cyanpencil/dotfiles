// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorBuilderImpl.java

package org.glassfish.hk2.internal;

import java.lang.annotation.Annotation;
import java.util.*;
import javax.inject.Named;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;

public class DescriptorBuilderImpl
    implements DescriptorBuilder
{

            public DescriptorBuilderImpl()
            {
/*  70*/        contracts = new HashSet();
/*  72*/        qualifiers = new HashSet();
/*  73*/        metadatas = new HashMap();
/*  75*/        loader = null;
/*  76*/        rank = 0;
/*  77*/        proxy = null;
/*  78*/        proxyForSameScope = null;
/*  79*/        visibility = DescriptorVisibility.NORMAL;
/*  80*/        analysisName = null;
            }

            public DescriptorBuilderImpl(String s, boolean flag)
            {
/*  70*/        contracts = new HashSet();
/*  72*/        qualifiers = new HashSet();
/*  73*/        metadatas = new HashMap();
/*  75*/        loader = null;
/*  76*/        rank = 0;
/*  77*/        proxy = null;
/*  78*/        proxyForSameScope = null;
/*  79*/        visibility = DescriptorVisibility.NORMAL;
/*  80*/        analysisName = null;
/*  98*/        implementation = s;
/*  99*/        if(flag)
/* 100*/            contracts.add(s);
            }

            public DescriptorBuilder named(String s)
                throws IllegalArgumentException
            {
/* 112*/        if(name != null)
                {
/* 113*/            throw new IllegalArgumentException();
                } else
                {
/* 116*/            name = s;
/* 117*/            qualifiers.add(javax/inject/Named.getName());
/* 119*/            return this;
                }
            }

            public DescriptorBuilder to(Class class1)
                throws IllegalArgumentException
            {
/* 132*/        if(class1 == null)
/* 132*/            throw new IllegalArgumentException();
/* 134*/        else
/* 134*/            return to(class1.getName());
            }

            public DescriptorBuilder to(String s)
                throws IllegalArgumentException
            {
/* 147*/        if(s == null)
                {
/* 147*/            throw new IllegalArgumentException();
                } else
                {
/* 149*/            contracts.add(s);
/* 151*/            return this;
                }
            }

            public DescriptorBuilder in(Class class1)
                throws IllegalArgumentException
            {
/* 163*/        if(class1 == null)
/* 164*/            throw new IllegalArgumentException();
/* 167*/        else
/* 167*/            return in(class1.getName());
            }

            public DescriptorBuilder in(String s)
                throws IllegalArgumentException
            {
/* 177*/        if(s == null)
                {
/* 178*/            throw new IllegalArgumentException();
                } else
                {
/* 181*/            scope = s;
/* 182*/            return this;
                }
            }

            public DescriptorBuilder qualifiedBy(Annotation annotation)
                throws IllegalArgumentException
            {
/* 195*/        if(annotation == null)
/* 195*/            throw new IllegalArgumentException();
/* 197*/        if(javax/inject/Named.equals(annotation.annotationType()))
/* 198*/            name = ((Named)annotation).value();
/* 201*/        return qualifiedBy(annotation.annotationType().getName());
            }

            public DescriptorBuilder qualifiedBy(String s)
                throws IllegalArgumentException
            {
/* 214*/        if(s == null)
                {
/* 214*/            throw new IllegalArgumentException();
                } else
                {
/* 216*/            qualifiers.add(s);
/* 217*/            return this;
                }
            }

            public DescriptorBuilder has(String s, String s1)
                throws IllegalArgumentException
            {
/* 229*/        if(s == null || s1 == null)
                {
/* 230*/            throw new IllegalArgumentException();
                } else
                {
                    LinkedList linkedlist;
/* 233*/            (linkedlist = new LinkedList()).add(s1);
/* 236*/            return has(s, ((List) (linkedlist)));
                }
            }

            public DescriptorBuilder has(String s, List list)
                throws IllegalArgumentException
            {
/* 248*/        if(s == null || list == null || list.size() <= 0)
                {
/* 249*/            throw new IllegalArgumentException();
                } else
                {
/* 252*/            metadatas.put(s, list);
/* 254*/            return this;
                }
            }

            public DescriptorBuilder ofRank(int i)
            {
/* 264*/        rank = i;
/* 265*/        return this;
            }

            public DescriptorBuilder proxy()
            {
/* 270*/        return proxy(true);
            }

            public DescriptorBuilder proxy(boolean flag)
            {
/* 275*/        if(flag)
/* 276*/            proxy = Boolean.TRUE;
/* 279*/        else
/* 279*/            proxy = Boolean.FALSE;
/* 282*/        return this;
            }

            public DescriptorBuilder proxyForSameScope()
            {
/* 287*/        return proxyForSameScope(true);
            }

            public DescriptorBuilder proxyForSameScope(boolean flag)
            {
/* 292*/        if(flag)
/* 293*/            proxyForSameScope = Boolean.TRUE;
/* 296*/        else
/* 296*/            proxyForSameScope = Boolean.FALSE;
/* 299*/        return this;
            }

            public DescriptorBuilder localOnly()
            {
/* 304*/        visibility = DescriptorVisibility.LOCAL;
/* 306*/        return this;
            }

            public DescriptorBuilder visibility(DescriptorVisibility descriptorvisibility)
            {
/* 311*/        if(descriptorvisibility == null)
                {
/* 311*/            throw new IllegalArgumentException();
                } else
                {
/* 313*/            visibility = descriptorvisibility;
/* 315*/            return this;
                }
            }

            public DescriptorBuilder andLoadWith(HK2Loader hk2loader)
                throws IllegalArgumentException
            {
/* 328*/        if(loader != null)
                {
/* 328*/            throw new IllegalArgumentException();
                } else
                {
/* 330*/            loader = hk2loader;
/* 331*/            return this;
                }
            }

            public DescriptorBuilder analyzeWith(String s)
            {
/* 336*/        analysisName = s;
/* 338*/        return this;
            }

            public DescriptorImpl build()
                throws IllegalArgumentException
            {
/* 348*/        return new DescriptorImpl(contracts, name, scope, implementation, metadatas, qualifiers, DescriptorType.CLASS, visibility, loader, rank, proxy, proxyForSameScope, analysisName, null, null);
            }

            public FactoryDescriptors buildFactory(String s)
                throws IllegalArgumentException
            {
                Object obj;
/* 361*/        ((Set) (obj = new HashSet())).add(implementation);
/* 363*/        ((Set) (obj)).add(org/glassfish/hk2/api/Factory.getName());
/* 364*/        Set set = Collections.emptySet();
/* 365*/        java.util.Map map = Collections.emptyMap();
/* 367*/        s = new DescriptorImpl(((Set) (obj)), null, s, implementation, map, set, DescriptorType.CLASS, DescriptorVisibility.NORMAL, loader, rank, null, null, analysisName, null, null);
/* 374*/        obj = new HashSet(contracts);
/* 375*/        if(implementation != null)
/* 375*/            ((Set) (obj)).remove(implementation);
/* 377*/        obj = new DescriptorImpl(((Set) (obj)), name, scope, implementation, metadatas, qualifiers, DescriptorType.PROVIDE_METHOD, visibility, loader, rank, proxy, proxyForSameScope, null, null, null);
/* 382*/        return new FactoryDescriptorsImpl(s, ((org.glassfish.hk2.api.Descriptor) (obj)));
            }

            public FactoryDescriptors buildFactory()
                throws IllegalArgumentException
            {
/* 392*/        return buildFactory(org/glassfish/hk2/api/PerLookup.getName());
            }

            public FactoryDescriptors buildFactory(Class class1)
                throws IllegalArgumentException
            {
/* 406*/        if(class1 == null)
/* 406*/            class1 = org/glassfish/hk2/api/PerLookup;
/* 408*/        return buildFactory(class1.getName());
            }

            private String name;
            private final HashSet contracts;
            private String scope;
            private final HashSet qualifiers;
            private final HashMap metadatas;
            private String implementation;
            private HK2Loader loader;
            private int rank;
            private Boolean proxy;
            private Boolean proxyForSameScope;
            private DescriptorVisibility visibility;
            private String analysisName;
}
