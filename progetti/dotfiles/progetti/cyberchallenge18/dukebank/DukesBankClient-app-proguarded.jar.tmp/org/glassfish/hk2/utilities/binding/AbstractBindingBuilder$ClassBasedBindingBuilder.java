// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.ActiveDescriptorBuilder;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.jvnet.hk2.component.MultiMap;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBindingBuilder, ServiceBindingBuilder, NamedBindingBuilder, ScopedBindingBuilder, 
//            ScopedNamedBindingBuilder

static class service extends AbstractBindingBuilder
{

            void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/* 228*/        if(loader == null)
/* 229*/            loader = hk2loader;
/* 232*/        hk2loader = BuilderHelper.activeLink(service).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 237*/        if(scopeAnnotation != null)
/* 238*/            hk2loader.in(scopeAnnotation);
/* 240*/        if(scope != null)
/* 241*/            hk2loader.in(scope);
/* 244*/        if(ranked != null)
/* 245*/            hk2loader.ofRank(ranked.intValue());
/* 248*/        for(Iterator iterator = metadata.keySet().iterator(); iterator.hasNext();)
                {
/* 248*/            String s = (String)iterator.next();
/* 249*/            Iterator iterator3 = metadata.get(s).iterator();
/* 249*/            while(iterator3.hasNext()) 
                    {
/* 249*/                String s1 = (String)iterator3.next();
/* 250*/                hk2loader.has(s, s1);
                    }
                }

                Annotation annotation;
/* 254*/        for(Iterator iterator1 = qualifiers.iterator(); iterator1.hasNext(); hk2loader.qualifiedBy(annotation))
/* 254*/            annotation = (Annotation)iterator1.next();

                Type type;
/* 258*/        for(Iterator iterator2 = contracts.iterator(); iterator2.hasNext(); hk2loader.to(type))
/* 258*/            type = (Type)iterator2.next();

/* 262*/        if(proxiable != null)
/* 263*/            hk2loader.proxy(proxiable.booleanValue());
/* 266*/        if(proxyForSameScope != null)
/* 267*/            hk2loader.proxyForSameScope(proxyForSameScope.booleanValue());
/* 270*/        dynamicconfiguration.bind(hk2loader.build(), false);
            }

            public volatile ServiceBindingBuilder analyzeWith(String s)
            {
/* 216*/        return super.analyzeWith(s);
            }

            public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
            {
/* 216*/        return super.proxyForSameScope(flag);
            }

            public volatile ServiceBindingBuilder proxy(boolean flag)
            {
/* 216*/        return super.proxy(flag);
            }

            public volatile NamedBindingBuilder named(String s)
            {
/* 216*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder in(Class class1)
            {
/* 216*/        return super.in(class1);
            }

            public volatile ScopedBindingBuilder in(Annotation annotation)
            {
/* 216*/        return super.in(annotation);
            }

            public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 216*/        return super.qualifiedBy(annotation);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, List list)
            {
/* 216*/        return super.withMetadata(s, list);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, String s1)
            {
/* 216*/        return super.withMetadata(s, s1);
            }

            public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 216*/        return super.loadedBy(hk2loader);
            }

            public volatile ServiceBindingBuilder to(Type type)
            {
/* 216*/        return super.to(type);
            }

            public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
            {
/* 216*/        return super.to(typeliteral);
            }

            public volatile ServiceBindingBuilder to(Class class1)
            {
/* 216*/        return super.to(class1);
            }

            public volatile NamedBindingBuilder proxy(boolean flag)
            {
/* 216*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder in(Class class1)
            {
/* 216*/        return super.in(class1);
            }

            public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 216*/        return super.qualifiedBy(annotation);
            }

            public volatile NamedBindingBuilder withMetadata(String s, List list)
            {
/* 216*/        return super.withMetadata(s, list);
            }

            public volatile NamedBindingBuilder withMetadata(String s, String s1)
            {
/* 216*/        return super.withMetadata(s, s1);
            }

            public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 216*/        return super.loadedBy(hk2loader);
            }

            public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 216*/        return super.to(typeliteral);
            }

            public volatile NamedBindingBuilder to(Class class1)
            {
/* 216*/        return super.to(class1);
            }

            public volatile ScopedBindingBuilder analyzeWith(String s)
            {
/* 216*/        return super.analyzeWith(s);
            }

            public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
            {
/* 216*/        return super.proxyForSameScope(flag);
            }

            public volatile ScopedBindingBuilder proxy(boolean flag)
            {
/* 216*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder named(String s)
            {
/* 216*/        return super.named(s);
            }

            public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 216*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, List list)
            {
/* 216*/        return super.withMetadata(s, list);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, String s1)
            {
/* 216*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 216*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 216*/        return super.to(typeliteral);
            }

            public volatile ScopedBindingBuilder to(Class class1)
            {
/* 216*/        return super.to(class1);
            }

            public volatile ScopedNamedBindingBuilder analyzeWith(String s)
            {
/* 216*/        return super.analyzeWith(s);
            }

            public volatile ScopedNamedBindingBuilder proxy(boolean flag)
            {
/* 216*/        return super.proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 216*/        return super.qualifiedBy(annotation);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
            {
/* 216*/        return super.withMetadata(s, list);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
            {
/* 216*/        return super.withMetadata(s, s1);
            }

            public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 216*/        return super.loadedBy(hk2loader);
            }

            public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
            {
/* 216*/        return super.to(typeliteral);
            }

            public volatile ScopedNamedBindingBuilder to(Class class1)
            {
/* 216*/        return super.to(class1);
            }

            private final Class service;

            public Y(Class class1, Type type)
            {
/* 220*/        service = class1;
/* 221*/        if(type != null)
/* 222*/            super.contracts.add(type);
            }
}
