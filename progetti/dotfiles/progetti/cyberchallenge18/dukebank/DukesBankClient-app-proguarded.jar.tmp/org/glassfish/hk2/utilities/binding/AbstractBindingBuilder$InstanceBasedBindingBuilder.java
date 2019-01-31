// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.jvnet.hk2.component.MultiMap;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBindingBuilder, ServiceBindingBuilder, NamedBindingBuilder, ScopedBindingBuilder, 
//            ScopedNamedBindingBuilder

static class service extends AbstractBindingBuilder
{

            void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/* 283*/        if(loader == null)
/* 284*/            loader = hk2loader;
/* 286*/        (hk2loader = BuilderHelper.createConstantDescriptor(service)).setName(name);
/* 288*/        hk2loader.setLoader(loader);
/* 289*/        hk2loader.setClassAnalysisName(analyzer);
/* 291*/        if(scope != null)
/* 292*/            hk2loader.setScope(scope.getName());
/* 295*/        if(ranked != null)
/* 296*/            hk2loader.setRanking(ranked.intValue());
/* 299*/        for(Iterator iterator = metadata.keySet().iterator(); iterator.hasNext();)
                {
/* 299*/            String s = (String)iterator.next();
/* 300*/            Iterator iterator3 = metadata.get(s).iterator();
/* 300*/            while(iterator3.hasNext()) 
                    {
/* 300*/                String s1 = (String)iterator3.next();
/* 301*/                hk2loader.addMetadata(s, s1);
                    }
                }

                Annotation annotation;
/* 305*/        for(Iterator iterator1 = qualifiers.iterator(); iterator1.hasNext(); hk2loader.addQualifierAnnotation(annotation))
/* 305*/            annotation = (Annotation)iterator1.next();

                Type type;
/* 309*/        for(Iterator iterator2 = contracts.iterator(); iterator2.hasNext(); hk2loader.addContractType(type))
/* 309*/            type = (Type)iterator2.next();

/* 313*/        if(proxiable != null)
/* 314*/            hk2loader.setProxiable(proxiable);
/* 317*/        if(proxyForSameScope != null)
/* 318*/            hk2loader.setProxyForSameScope(proxyForSameScope);
/* 321*/        dynamicconfiguration.bind(hk2loader, false);
            }

            public volatile ServiceBindingBuilder analyzeWith(String s)
            {
/* 274*/        return super.analyzeWith(s);
            }

            public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
            {
/* 274*/        return super.proxyForSameScope(flag);
            }

            public volatile ServiceBindingBuilder proxy(boolean flag)
            {
/* 274*/        return super.proxy(flag);
            }

            public volatile NamedBindingBuilder named(String s)
            {
/* 274*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder in(Class class1)
            {
/* 274*/        return super.in(class1);
            }

            public volatile ScopedBindingBuilder in(Annotation annotation)
            {
/* 274*/        return super.in(annotation);
            }

            public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 274*/        return super.qualifiedBy(annotation);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, List list)
            {
/* 274*/        return super.withMetadata(s, list);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, String s1)
            {
/* 274*/        return super.withMetadata(s, s1);
            }

            public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 274*/        return super.loadedBy(hk2loader);
            }

            public volatile ServiceBindingBuilder to(Type type)
            {
/* 274*/        return super.to(type);
            }

            public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
            {
/* 274*/        return super.to(typeliteral);
            }

            public volatile ServiceBindingBuilder to(Class class1)
            {
/* 274*/        return super.to(class1);
            }

            public volatile NamedBindingBuilder proxy(boolean flag)
            {
/* 274*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder in(Class class1)
            {
/* 274*/        return super.in(class1);
            }

            public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 274*/        return super.qualifiedBy(annotation);
            }

            public volatile NamedBindingBuilder withMetadata(String s, List list)
            {
/* 274*/        return super.withMetadata(s, list);
            }

            public volatile NamedBindingBuilder withMetadata(String s, String s1)
            {
/* 274*/        return super.withMetadata(s, s1);
            }

            public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 274*/        return super.loadedBy(hk2loader);
            }

            public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 274*/        return super.to(typeliteral);
            }

            public volatile NamedBindingBuilder to(Class class1)
            {
/* 274*/        return super.to(class1);
            }

            public volatile ScopedBindingBuilder analyzeWith(String s)
            {
/* 274*/        return super.analyzeWith(s);
            }

            public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
            {
/* 274*/        return super.proxyForSameScope(flag);
            }

            public volatile ScopedBindingBuilder proxy(boolean flag)
            {
/* 274*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder named(String s)
            {
/* 274*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 274*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, List list)
            {
/* 274*/        return super.withMetadata(s, list);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, String s1)
            {
/* 274*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 274*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 274*/        return super.to(typeliteral);
            }

            public volatile ScopedBindingBuilder to(Class class1)
            {
/* 274*/        return super.to(class1);
            }

            public volatile ScopedNamedBindingBuilder analyzeWith(String s)
            {
/* 274*/        return super.analyzeWith(s);
            }

            public volatile ScopedNamedBindingBuilder proxy(boolean flag)
            {
/* 274*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 274*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
            {
/* 274*/        return super.withMetadata(s, list);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
            {
/* 274*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 274*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 274*/        return super.to(typeliteral);
            }

            public volatile ScopedNamedBindingBuilder to(Class class1)
            {
/* 274*/        return super.to(class1);
            }

            private final Object service;

            public (Object obj)
            {
/* 278*/        service = obj;
            }
}
