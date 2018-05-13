// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;
import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;
import org.jvnet.hk2.component.MultiMap;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBindingBuilder, ServiceBindingBuilder, NamedBindingBuilder, ScopedBindingBuilder, 
//            ScopedNamedBindingBuilder

static class factory extends AbstractBindingBuilder
{

            void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/* 334*/        if(loader == null)
/* 335*/            loader = hk2loader;
/* 338*/        (hk2loader = BuilderHelper.createConstantDescriptor(factory)).addContractType(factory.getClass());
/* 340*/        hk2loader.setLoader(loader);
/* 342*/        ActiveDescriptorBuilder activedescriptorbuilder = BuilderHelper.activeLink(factory.getClass()).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 347*/        if(scope != null)
/* 348*/            activedescriptorbuilder.in(scope);
/* 351*/        if(ranked != null)
/* 352*/            activedescriptorbuilder.ofRank(ranked.intValue());
                Annotation annotation;
/* 355*/        for(Iterator iterator = qualifiers.iterator(); iterator.hasNext(); activedescriptorbuilder.qualifiedBy(annotation))
                {
/* 355*/            annotation = (Annotation)iterator.next();
/* 356*/            hk2loader.addQualifierAnnotation(annotation);
                }

                Type type;
/* 360*/        for(Iterator iterator1 = contracts.iterator(); iterator1.hasNext(); activedescriptorbuilder.to(type))
                {
/* 360*/            type = (Type)iterator1.next();
/* 361*/            hk2loader.addContractType(new ParameterizedTypeImpl(org/glassfish/hk2/api/Factory, new Type[] {
/* 361*/                type
                    }));
                }

                Set set;
/* 365*/        for(Iterator iterator2 = (set = metadata.keySet()).iterator(); iterator2.hasNext();)
                {
/* 366*/            String s = (String)iterator2.next();
                    Object obj;
/* 367*/            obj = ((List) (obj = metadata.get(s))).iterator();
/* 368*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 368*/                String s1 = (String)((Iterator) (obj)).next();
/* 369*/                hk2loader.addMetadata(s, s1);
/* 370*/                activedescriptorbuilder.has(s, s1);
                    }
                }

/* 374*/        if(proxiable != null)
/* 375*/            activedescriptorbuilder.proxy(proxiable.booleanValue());
/* 378*/        if(proxyForSameScope != null)
/* 379*/            activedescriptorbuilder.proxyForSameScope(proxyForSameScope.booleanValue());
/* 382*/        dynamicconfiguration.bind(new FactoryDescriptorsImpl(hk2loader, activedescriptorbuilder.buildProvideMethod()));
            }

            public volatile ServiceBindingBuilder analyzeWith(String s)
            {
/* 325*/        return super.analyzeWith(s);
            }

            public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
            {
/* 325*/        return super.proxyForSameScope(flag);
            }

            public volatile ServiceBindingBuilder proxy(boolean flag)
            {
/* 325*/        return super.proxy(flag);
            }

            public volatile NamedBindingBuilder named(String s)
            {
/* 325*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder in(Class class1)
            {
/* 325*/        return super.in(class1);
            }

            public volatile ScopedBindingBuilder in(Annotation annotation)
            {
/* 325*/        return super.in(annotation);
            }

            public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 325*/        return super.qualifiedBy(annotation);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, List list)
            {
/* 325*/        return super.withMetadata(s, list);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, String s1)
            {
/* 325*/        return super.withMetadata(s, s1);
            }

            public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 325*/        return super.loadedBy(hk2loader);
            }

            public volatile ServiceBindingBuilder to(Type type)
            {
/* 325*/        return super.to(type);
            }

            public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
            {
/* 325*/        return super.to(typeliteral);
            }

            public volatile ServiceBindingBuilder to(Class class1)
            {
/* 325*/        return super.to(class1);
            }

            public volatile NamedBindingBuilder proxy(boolean flag)
            {
/* 325*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder in(Class class1)
            {
/* 325*/        return super.in(class1);
            }

            public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 325*/        return super.qualifiedBy(annotation);
            }

            public volatile NamedBindingBuilder withMetadata(String s, List list)
            {
/* 325*/        return super.withMetadata(s, list);
            }

            public volatile NamedBindingBuilder withMetadata(String s, String s1)
            {
/* 325*/        return super.withMetadata(s, s1);
            }

            public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 325*/        return super.loadedBy(hk2loader);
            }

            public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 325*/        return super.to(typeliteral);
            }

            public volatile NamedBindingBuilder to(Class class1)
            {
/* 325*/        return super.to(class1);
            }

            public volatile ScopedBindingBuilder analyzeWith(String s)
            {
/* 325*/        return super.analyzeWith(s);
            }

            public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
            {
/* 325*/        return super.proxyForSameScope(flag);
            }

            public volatile ScopedBindingBuilder proxy(boolean flag)
            {
/* 325*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder named(String s)
            {
/* 325*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 325*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, List list)
            {
/* 325*/        return super.withMetadata(s, list);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, String s1)
            {
/* 325*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 325*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 325*/        return super.to(typeliteral);
            }

            public volatile ScopedBindingBuilder to(Class class1)
            {
/* 325*/        return super.to(class1);
            }

            public volatile ScopedNamedBindingBuilder analyzeWith(String s)
            {
/* 325*/        return super.analyzeWith(s);
            }

            public volatile ScopedNamedBindingBuilder proxy(boolean flag)
            {
/* 325*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 325*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
            {
/* 325*/        return super.withMetadata(s, list);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
            {
/* 325*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 325*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 325*/        return super.to(typeliteral);
            }

            public volatile ScopedNamedBindingBuilder to(Class class1)
            {
/* 325*/        return super.to(class1);
            }

            private final Factory factory;

            public (Factory factory1)
            {
/* 329*/        factory = factory1;
            }
}
