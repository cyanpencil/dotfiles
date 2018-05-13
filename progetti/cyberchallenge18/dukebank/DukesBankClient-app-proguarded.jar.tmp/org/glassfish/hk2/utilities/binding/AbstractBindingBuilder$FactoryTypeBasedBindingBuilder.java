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

static class factoryScope extends AbstractBindingBuilder
{

            void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/* 399*/        if(loader == null)
/* 400*/            loader = hk2loader;
/* 403*/        hk2loader = BuilderHelper.activeLink(factoryClass).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 408*/        if(factoryScope != null)
/* 409*/            hk2loader.in(factoryScope);
/* 412*/        ActiveDescriptorBuilder activedescriptorbuilder = BuilderHelper.activeLink(factoryClass).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 417*/        if(scope != null)
/* 418*/            activedescriptorbuilder.in(scope);
/* 421*/        if(ranked != null)
/* 423*/            activedescriptorbuilder.ofRank(ranked.intValue());
                Annotation annotation;
/* 426*/        for(Iterator iterator = qualifiers.iterator(); iterator.hasNext(); activedescriptorbuilder.qualifiedBy(annotation))
                {
/* 426*/            annotation = (Annotation)iterator.next();
/* 427*/            hk2loader.qualifiedBy(annotation);
                }

                Type type;
/* 431*/        for(Iterator iterator1 = contracts.iterator(); iterator1.hasNext(); activedescriptorbuilder.to(type))
                {
/* 431*/            type = (Type)iterator1.next();
/* 432*/            hk2loader.to(new ParameterizedTypeImpl(org/glassfish/hk2/api/Factory, new Type[] {
/* 432*/                type
                    }));
                }

                Set set;
/* 436*/        for(Iterator iterator2 = (set = metadata.keySet()).iterator(); iterator2.hasNext();)
                {
/* 437*/            String s = (String)iterator2.next();
                    Object obj;
/* 438*/            obj = ((List) (obj = metadata.get(s))).iterator();
/* 439*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 439*/                String s1 = (String)((Iterator) (obj)).next();
/* 440*/                hk2loader.has(s, s1);
/* 441*/                activedescriptorbuilder.has(s, s1);
                    }
                }

/* 445*/        if(proxiable != null)
/* 446*/            activedescriptorbuilder.proxy(proxiable.booleanValue());
/* 449*/        if(proxyForSameScope != null)
/* 450*/            activedescriptorbuilder.proxyForSameScope(proxyForSameScope.booleanValue());
/* 453*/        dynamicconfiguration.bind(new FactoryDescriptorsImpl(hk2loader.build(), activedescriptorbuilder.buildProvideMethod()));
            }

            public volatile ServiceBindingBuilder analyzeWith(String s)
            {
/* 388*/        return super.analyzeWith(s);
            }

            public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
            {
/* 388*/        return super.proxyForSameScope(flag);
            }

            public volatile ServiceBindingBuilder proxy(boolean flag)
            {
/* 388*/        return super.proxy(flag);
            }

            public volatile NamedBindingBuilder named(String s)
            {
/* 388*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder in(Class class1)
            {
/* 388*/        return super.in(class1);
            }

            public volatile ScopedBindingBuilder in(Annotation annotation)
            {
/* 388*/        return super.in(annotation);
            }

            public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 388*/        return super.qualifiedBy(annotation);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, List list)
            {
/* 388*/        return super.withMetadata(s, list);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, String s1)
            {
/* 388*/        return super.withMetadata(s, s1);
            }

            public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 388*/        return super.loadedBy(hk2loader);
            }

            public volatile ServiceBindingBuilder to(Type type)
            {
/* 388*/        return super.to(type);
            }

            public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
            {
/* 388*/        return super.to(typeliteral);
            }

            public volatile ServiceBindingBuilder to(Class class1)
            {
/* 388*/        return super.to(class1);
            }

            public volatile NamedBindingBuilder proxy(boolean flag)
            {
/* 388*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder in(Class class1)
            {
/* 388*/        return super.in(class1);
            }

            public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 388*/        return super.qualifiedBy(annotation);
            }

            public volatile NamedBindingBuilder withMetadata(String s, List list)
            {
/* 388*/        return super.withMetadata(s, list);
            }

            public volatile NamedBindingBuilder withMetadata(String s, String s1)
            {
/* 388*/        return super.withMetadata(s, s1);
            }

            public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 388*/        return super.loadedBy(hk2loader);
            }

            public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 388*/        return super.to(typeliteral);
            }

            public volatile NamedBindingBuilder to(Class class1)
            {
/* 388*/        return super.to(class1);
            }

            public volatile ScopedBindingBuilder analyzeWith(String s)
            {
/* 388*/        return super.analyzeWith(s);
            }

            public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
            {
/* 388*/        return super.proxyForSameScope(flag);
            }

            public volatile ScopedBindingBuilder proxy(boolean flag)
            {
/* 388*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder named(String s)
            {
/* 388*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 388*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, List list)
            {
/* 388*/        return super.withMetadata(s, list);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, String s1)
            {
/* 388*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 388*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 388*/        return super.to(typeliteral);
            }

            public volatile ScopedBindingBuilder to(Class class1)
            {
/* 388*/        return super.to(class1);
            }

            public volatile ScopedNamedBindingBuilder analyzeWith(String s)
            {
/* 388*/        return super.analyzeWith(s);
            }

            public volatile ScopedNamedBindingBuilder proxy(boolean flag)
            {
/* 388*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 388*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
            {
/* 388*/        return super.withMetadata(s, list);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
            {
/* 388*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 388*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 388*/        return super.to(typeliteral);
            }

            public volatile ScopedNamedBindingBuilder to(Class class1)
            {
/* 388*/        return super.to(class1);
            }

            private final Class factoryClass;
            private final Class factoryScope;

            public (Class class1, Class class2)
            {
/* 393*/        factoryClass = class1;
/* 394*/        factoryScope = class2;
            }
}
