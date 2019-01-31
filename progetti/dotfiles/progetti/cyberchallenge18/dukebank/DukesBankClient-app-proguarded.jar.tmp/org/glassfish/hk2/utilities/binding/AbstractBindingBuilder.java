// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import javax.inject.Named;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;
import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;
import org.jvnet.hk2.component.MultiMap;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            NamedBindingBuilder, ScopedBindingBuilder, ScopedNamedBindingBuilder, ServiceBindingBuilder

abstract class AbstractBindingBuilder
    implements NamedBindingBuilder, ScopedBindingBuilder, ScopedNamedBindingBuilder, ServiceBindingBuilder
{
    static class FactoryTypeBasedBindingBuilder extends AbstractBindingBuilder
    {

                void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
                {
/* 399*/            if(loader == null)
/* 400*/                loader = hk2loader;
/* 403*/            hk2loader = BuilderHelper.activeLink(factoryClass).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 408*/            if(factoryScope != null)
/* 409*/                hk2loader.in(factoryScope);
/* 412*/            ActiveDescriptorBuilder activedescriptorbuilder = BuilderHelper.activeLink(factoryClass).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 417*/            if(scope != null)
/* 418*/                activedescriptorbuilder.in(scope);
/* 421*/            if(ranked != null)
/* 423*/                activedescriptorbuilder.ofRank(ranked.intValue());
                    Annotation annotation;
/* 426*/            for(Iterator iterator = qualifiers.iterator(); iterator.hasNext(); activedescriptorbuilder.qualifiedBy(annotation))
                    {
/* 426*/                annotation = (Annotation)iterator.next();
/* 427*/                hk2loader.qualifiedBy(annotation);
                    }

                    Type type;
/* 431*/            for(Iterator iterator1 = contracts.iterator(); iterator1.hasNext(); activedescriptorbuilder.to(type))
                    {
/* 431*/                type = (Type)iterator1.next();
/* 432*/                hk2loader.to(new ParameterizedTypeImpl(org/glassfish/hk2/api/Factory, new Type[] {
/* 432*/                    type
                        }));
                    }

                    Set set;
/* 436*/            for(Iterator iterator2 = (set = metadata.keySet()).iterator(); iterator2.hasNext();)
                    {
/* 437*/                String s = (String)iterator2.next();
                        Object obj;
/* 438*/                obj = ((List) (obj = metadata.get(s))).iterator();
/* 439*/                while(((Iterator) (obj)).hasNext()) 
                        {
/* 439*/                    String s1 = (String)((Iterator) (obj)).next();
/* 440*/                    hk2loader.has(s, s1);
/* 441*/                    activedescriptorbuilder.has(s, s1);
                        }
                    }

/* 445*/            if(proxiable != null)
/* 446*/                activedescriptorbuilder.proxy(proxiable.booleanValue());
/* 449*/            if(proxyForSameScope != null)
/* 450*/                activedescriptorbuilder.proxyForSameScope(proxyForSameScope.booleanValue());
/* 453*/            dynamicconfiguration.bind(new FactoryDescriptorsImpl(hk2loader.build(), activedescriptorbuilder.buildProvideMethod()));
                }

                public volatile ServiceBindingBuilder analyzeWith(String s)
                {
/* 388*/            return analyzeWith(s);
                }

                public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
                {
/* 388*/            return proxyForSameScope(flag);
                }

                public volatile ServiceBindingBuilder proxy(boolean flag)
                {
/* 388*/            return proxy(flag);
                }

                public volatile NamedBindingBuilder named(String s)
                {
/* 388*/            return named(s);
                }

                public volatile ScopedBindingBuilder in(Class class1)
                {
/* 388*/            return in(class1);
                }

                public volatile ScopedBindingBuilder in(Annotation annotation)
                {
/* 388*/            return in(annotation);
                }

                public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 388*/            return qualifiedBy(annotation);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, List list)
                {
/* 388*/            return withMetadata(s, list);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, String s1)
                {
/* 388*/            return withMetadata(s, s1);
                }

                public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 388*/            return loadedBy(hk2loader);
                }

                public volatile ServiceBindingBuilder to(Type type)
                {
/* 388*/            return to(type);
                }

                public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
                {
/* 388*/            return to(typeliteral);
                }

                public volatile ServiceBindingBuilder to(Class class1)
                {
/* 388*/            return to(class1);
                }

                public volatile NamedBindingBuilder proxy(boolean flag)
                {
/* 388*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder in(Class class1)
                {
/* 388*/            return in(class1);
                }

                public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 388*/            return qualifiedBy(annotation);
                }

                public volatile NamedBindingBuilder withMetadata(String s, List list)
                {
/* 388*/            return withMetadata(s, list);
                }

                public volatile NamedBindingBuilder withMetadata(String s, String s1)
                {
/* 388*/            return withMetadata(s, s1);
                }

                public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 388*/            return loadedBy(hk2loader);
                }

                public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 388*/            return to(typeliteral);
                }

                public volatile NamedBindingBuilder to(Class class1)
                {
/* 388*/            return to(class1);
                }

                public volatile ScopedBindingBuilder analyzeWith(String s)
                {
/* 388*/            return analyzeWith(s);
                }

                public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
                {
/* 388*/            return proxyForSameScope(flag);
                }

                public volatile ScopedBindingBuilder proxy(boolean flag)
                {
/* 388*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder named(String s)
                {
/* 388*/            return named(s);
                }

                public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 388*/            return qualifiedBy(annotation);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, List list)
                {
/* 388*/            return withMetadata(s, list);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, String s1)
                {
/* 388*/            return withMetadata(s, s1);
                }

                public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 388*/            return loadedBy(hk2loader);
                }

                public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 388*/            return to(typeliteral);
                }

                public volatile ScopedBindingBuilder to(Class class1)
                {
/* 388*/            return to(class1);
                }

                public volatile ScopedNamedBindingBuilder analyzeWith(String s)
                {
/* 388*/            return analyzeWith(s);
                }

                public volatile ScopedNamedBindingBuilder proxy(boolean flag)
                {
/* 388*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 388*/            return qualifiedBy(annotation);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
                {
/* 388*/            return withMetadata(s, list);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
                {
/* 388*/            return withMetadata(s, s1);
                }

                public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 388*/            return loadedBy(hk2loader);
                }

                public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 388*/            return to(typeliteral);
                }

                public volatile ScopedNamedBindingBuilder to(Class class1)
                {
/* 388*/            return to(class1);
                }

                private final Class factoryClass;
                private final Class factoryScope;

                public FactoryTypeBasedBindingBuilder(Class class1, Class class2)
                {
/* 393*/            factoryClass = class1;
/* 394*/            factoryScope = class2;
                }
    }

    static class FactoryInstanceBasedBindingBuilder extends AbstractBindingBuilder
    {

                void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
                {
/* 334*/            if(loader == null)
/* 335*/                loader = hk2loader;
/* 338*/            (hk2loader = BuilderHelper.createConstantDescriptor(factory)).addContractType(factory.getClass());
/* 340*/            hk2loader.setLoader(loader);
/* 342*/            ActiveDescriptorBuilder activedescriptorbuilder = BuilderHelper.activeLink(factory.getClass()).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 347*/            if(scope != null)
/* 348*/                activedescriptorbuilder.in(scope);
/* 351*/            if(ranked != null)
/* 352*/                activedescriptorbuilder.ofRank(ranked.intValue());
                    Annotation annotation;
/* 355*/            for(Iterator iterator = qualifiers.iterator(); iterator.hasNext(); activedescriptorbuilder.qualifiedBy(annotation))
                    {
/* 355*/                annotation = (Annotation)iterator.next();
/* 356*/                hk2loader.addQualifierAnnotation(annotation);
                    }

                    Type type;
/* 360*/            for(Iterator iterator1 = contracts.iterator(); iterator1.hasNext(); activedescriptorbuilder.to(type))
                    {
/* 360*/                type = (Type)iterator1.next();
/* 361*/                hk2loader.addContractType(new ParameterizedTypeImpl(org/glassfish/hk2/api/Factory, new Type[] {
/* 361*/                    type
                        }));
                    }

                    Set set;
/* 365*/            for(Iterator iterator2 = (set = metadata.keySet()).iterator(); iterator2.hasNext();)
                    {
/* 366*/                String s = (String)iterator2.next();
                        Object obj;
/* 367*/                obj = ((List) (obj = metadata.get(s))).iterator();
/* 368*/                while(((Iterator) (obj)).hasNext()) 
                        {
/* 368*/                    String s1 = (String)((Iterator) (obj)).next();
/* 369*/                    hk2loader.addMetadata(s, s1);
/* 370*/                    activedescriptorbuilder.has(s, s1);
                        }
                    }

/* 374*/            if(proxiable != null)
/* 375*/                activedescriptorbuilder.proxy(proxiable.booleanValue());
/* 378*/            if(proxyForSameScope != null)
/* 379*/                activedescriptorbuilder.proxyForSameScope(proxyForSameScope.booleanValue());
/* 382*/            dynamicconfiguration.bind(new FactoryDescriptorsImpl(hk2loader, activedescriptorbuilder.buildProvideMethod()));
                }

                public volatile ServiceBindingBuilder analyzeWith(String s)
                {
/* 325*/            return analyzeWith(s);
                }

                public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
                {
/* 325*/            return proxyForSameScope(flag);
                }

                public volatile ServiceBindingBuilder proxy(boolean flag)
                {
/* 325*/            return proxy(flag);
                }

                public volatile NamedBindingBuilder named(String s)
                {
/* 325*/            return named(s);
                }

                public volatile ScopedBindingBuilder in(Class class1)
                {
/* 325*/            return in(class1);
                }

                public volatile ScopedBindingBuilder in(Annotation annotation)
                {
/* 325*/            return in(annotation);
                }

                public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 325*/            return qualifiedBy(annotation);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, List list)
                {
/* 325*/            return withMetadata(s, list);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, String s1)
                {
/* 325*/            return withMetadata(s, s1);
                }

                public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 325*/            return loadedBy(hk2loader);
                }

                public volatile ServiceBindingBuilder to(Type type)
                {
/* 325*/            return to(type);
                }

                public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
                {
/* 325*/            return to(typeliteral);
                }

                public volatile ServiceBindingBuilder to(Class class1)
                {
/* 325*/            return to(class1);
                }

                public volatile NamedBindingBuilder proxy(boolean flag)
                {
/* 325*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder in(Class class1)
                {
/* 325*/            return in(class1);
                }

                public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 325*/            return qualifiedBy(annotation);
                }

                public volatile NamedBindingBuilder withMetadata(String s, List list)
                {
/* 325*/            return withMetadata(s, list);
                }

                public volatile NamedBindingBuilder withMetadata(String s, String s1)
                {
/* 325*/            return withMetadata(s, s1);
                }

                public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 325*/            return loadedBy(hk2loader);
                }

                public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 325*/            return to(typeliteral);
                }

                public volatile NamedBindingBuilder to(Class class1)
                {
/* 325*/            return to(class1);
                }

                public volatile ScopedBindingBuilder analyzeWith(String s)
                {
/* 325*/            return analyzeWith(s);
                }

                public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
                {
/* 325*/            return proxyForSameScope(flag);
                }

                public volatile ScopedBindingBuilder proxy(boolean flag)
                {
/* 325*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder named(String s)
                {
/* 325*/            return named(s);
                }

                public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 325*/            return qualifiedBy(annotation);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, List list)
                {
/* 325*/            return withMetadata(s, list);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, String s1)
                {
/* 325*/            return withMetadata(s, s1);
                }

                public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 325*/            return loadedBy(hk2loader);
                }

                public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 325*/            return to(typeliteral);
                }

                public volatile ScopedBindingBuilder to(Class class1)
                {
/* 325*/            return to(class1);
                }

                public volatile ScopedNamedBindingBuilder analyzeWith(String s)
                {
/* 325*/            return analyzeWith(s);
                }

                public volatile ScopedNamedBindingBuilder proxy(boolean flag)
                {
/* 325*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 325*/            return qualifiedBy(annotation);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
                {
/* 325*/            return withMetadata(s, list);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
                {
/* 325*/            return withMetadata(s, s1);
                }

                public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 325*/            return loadedBy(hk2loader);
                }

                public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 325*/            return to(typeliteral);
                }

                public volatile ScopedNamedBindingBuilder to(Class class1)
                {
/* 325*/            return to(class1);
                }

                private final Factory factory;

                public FactoryInstanceBasedBindingBuilder(Factory factory1)
                {
/* 329*/            factory = factory1;
                }
    }

    static class InstanceBasedBindingBuilder extends AbstractBindingBuilder
    {

                void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
                {
/* 283*/            if(loader == null)
/* 284*/                loader = hk2loader;
/* 286*/            (hk2loader = BuilderHelper.createConstantDescriptor(service)).setName(name);
/* 288*/            hk2loader.setLoader(loader);
/* 289*/            hk2loader.setClassAnalysisName(analyzer);
/* 291*/            if(scope != null)
/* 292*/                hk2loader.setScope(scope.getName());
/* 295*/            if(ranked != null)
/* 296*/                hk2loader.setRanking(ranked.intValue());
/* 299*/            for(Iterator iterator = metadata.keySet().iterator(); iterator.hasNext();)
                    {
/* 299*/                String s = (String)iterator.next();
/* 300*/                Iterator iterator3 = metadata.get(s).iterator();
/* 300*/                while(iterator3.hasNext()) 
                        {
/* 300*/                    String s1 = (String)iterator3.next();
/* 301*/                    hk2loader.addMetadata(s, s1);
                        }
                    }

                    Annotation annotation;
/* 305*/            for(Iterator iterator1 = qualifiers.iterator(); iterator1.hasNext(); hk2loader.addQualifierAnnotation(annotation))
/* 305*/                annotation = (Annotation)iterator1.next();

                    Type type;
/* 309*/            for(Iterator iterator2 = contracts.iterator(); iterator2.hasNext(); hk2loader.addContractType(type))
/* 309*/                type = (Type)iterator2.next();

/* 313*/            if(proxiable != null)
/* 314*/                hk2loader.setProxiable(proxiable);
/* 317*/            if(proxyForSameScope != null)
/* 318*/                hk2loader.setProxyForSameScope(proxyForSameScope);
/* 321*/            dynamicconfiguration.bind(hk2loader, false);
                }

                public volatile ServiceBindingBuilder analyzeWith(String s)
                {
/* 274*/            return analyzeWith(s);
                }

                public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
                {
/* 274*/            return proxyForSameScope(flag);
                }

                public volatile ServiceBindingBuilder proxy(boolean flag)
                {
/* 274*/            return proxy(flag);
                }

                public volatile NamedBindingBuilder named(String s)
                {
/* 274*/            return named(s);
                }

                public volatile ScopedBindingBuilder in(Class class1)
                {
/* 274*/            return in(class1);
                }

                public volatile ScopedBindingBuilder in(Annotation annotation)
                {
/* 274*/            return in(annotation);
                }

                public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 274*/            return qualifiedBy(annotation);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, List list)
                {
/* 274*/            return withMetadata(s, list);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, String s1)
                {
/* 274*/            return withMetadata(s, s1);
                }

                public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 274*/            return loadedBy(hk2loader);
                }

                public volatile ServiceBindingBuilder to(Type type)
                {
/* 274*/            return to(type);
                }

                public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
                {
/* 274*/            return to(typeliteral);
                }

                public volatile ServiceBindingBuilder to(Class class1)
                {
/* 274*/            return to(class1);
                }

                public volatile NamedBindingBuilder proxy(boolean flag)
                {
/* 274*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder in(Class class1)
                {
/* 274*/            return in(class1);
                }

                public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 274*/            return qualifiedBy(annotation);
                }

                public volatile NamedBindingBuilder withMetadata(String s, List list)
                {
/* 274*/            return withMetadata(s, list);
                }

                public volatile NamedBindingBuilder withMetadata(String s, String s1)
                {
/* 274*/            return withMetadata(s, s1);
                }

                public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 274*/            return loadedBy(hk2loader);
                }

                public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 274*/            return to(typeliteral);
                }

                public volatile NamedBindingBuilder to(Class class1)
                {
/* 274*/            return to(class1);
                }

                public volatile ScopedBindingBuilder analyzeWith(String s)
                {
/* 274*/            return analyzeWith(s);
                }

                public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
                {
/* 274*/            return proxyForSameScope(flag);
                }

                public volatile ScopedBindingBuilder proxy(boolean flag)
                {
/* 274*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder named(String s)
                {
/* 274*/            return named(s);
                }

                public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 274*/            return qualifiedBy(annotation);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, List list)
                {
/* 274*/            return withMetadata(s, list);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, String s1)
                {
/* 274*/            return withMetadata(s, s1);
                }

                public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 274*/            return loadedBy(hk2loader);
                }

                public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 274*/            return to(typeliteral);
                }

                public volatile ScopedBindingBuilder to(Class class1)
                {
/* 274*/            return to(class1);
                }

                public volatile ScopedNamedBindingBuilder analyzeWith(String s)
                {
/* 274*/            return analyzeWith(s);
                }

                public volatile ScopedNamedBindingBuilder proxy(boolean flag)
                {
/* 274*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 274*/            return qualifiedBy(annotation);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
                {
/* 274*/            return withMetadata(s, list);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
                {
/* 274*/            return withMetadata(s, s1);
                }

                public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 274*/            return loadedBy(hk2loader);
                }

                public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 274*/            return to(typeliteral);
                }

                public volatile ScopedNamedBindingBuilder to(Class class1)
                {
/* 274*/            return to(class1);
                }

                private final Object service;

                public InstanceBasedBindingBuilder(Object obj)
                {
/* 278*/            service = obj;
                }
    }

    static class ClassBasedBindingBuilder extends AbstractBindingBuilder
    {

                void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
                {
/* 228*/            if(loader == null)
/* 229*/                loader = hk2loader;
/* 232*/            hk2loader = BuilderHelper.activeLink(service).named(name).andLoadWith(loader).analyzeWith(analyzer);
/* 237*/            if(scopeAnnotation != null)
/* 238*/                hk2loader.in(scopeAnnotation);
/* 240*/            if(scope != null)
/* 241*/                hk2loader.in(scope);
/* 244*/            if(ranked != null)
/* 245*/                hk2loader.ofRank(ranked.intValue());
/* 248*/            for(Iterator iterator = metadata.keySet().iterator(); iterator.hasNext();)
                    {
/* 248*/                String s = (String)iterator.next();
/* 249*/                Iterator iterator3 = metadata.get(s).iterator();
/* 249*/                while(iterator3.hasNext()) 
                        {
/* 249*/                    String s1 = (String)iterator3.next();
/* 250*/                    hk2loader.has(s, s1);
                        }
                    }

                    Annotation annotation;
/* 254*/            for(Iterator iterator1 = qualifiers.iterator(); iterator1.hasNext(); hk2loader.qualifiedBy(annotation))
/* 254*/                annotation = (Annotation)iterator1.next();

                    Type type;
/* 258*/            for(Iterator iterator2 = contracts.iterator(); iterator2.hasNext(); hk2loader.to(type))
/* 258*/                type = (Type)iterator2.next();

/* 262*/            if(proxiable != null)
/* 263*/                hk2loader.proxy(proxiable.booleanValue());
/* 266*/            if(proxyForSameScope != null)
/* 267*/                hk2loader.proxyForSameScope(proxyForSameScope.booleanValue());
/* 270*/            dynamicconfiguration.bind(hk2loader.build(), false);
                }

                public volatile ServiceBindingBuilder analyzeWith(String s)
                {
/* 216*/            return analyzeWith(s);
                }

                public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
                {
/* 216*/            return proxyForSameScope(flag);
                }

                public volatile ServiceBindingBuilder proxy(boolean flag)
                {
/* 216*/            return proxy(flag);
                }

                public volatile NamedBindingBuilder named(String s)
                {
/* 216*/            return named(s);
                }

                public volatile ScopedBindingBuilder in(Class class1)
                {
/* 216*/            return in(class1);
                }

                public volatile ScopedBindingBuilder in(Annotation annotation)
                {
/* 216*/            return in(annotation);
                }

                public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 216*/            return qualifiedBy(annotation);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, List list)
                {
/* 216*/            return withMetadata(s, list);
                }

                public volatile ServiceBindingBuilder withMetadata(String s, String s1)
                {
/* 216*/            return withMetadata(s, s1);
                }

                public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 216*/            return loadedBy(hk2loader);
                }

                public volatile ServiceBindingBuilder to(Type type)
                {
/* 216*/            return to(type);
                }

                public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
                {
/* 216*/            return to(typeliteral);
                }

                public volatile ServiceBindingBuilder to(Class class1)
                {
/* 216*/            return to(class1);
                }

                public volatile NamedBindingBuilder proxy(boolean flag)
                {
/* 216*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder in(Class class1)
                {
/* 216*/            return in(class1);
                }

                public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 216*/            return qualifiedBy(annotation);
                }

                public volatile NamedBindingBuilder withMetadata(String s, List list)
                {
/* 216*/            return withMetadata(s, list);
                }

                public volatile NamedBindingBuilder withMetadata(String s, String s1)
                {
/* 216*/            return withMetadata(s, s1);
                }

                public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 216*/            return loadedBy(hk2loader);
                }

                public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 216*/            return to(typeliteral);
                }

                public volatile NamedBindingBuilder to(Class class1)
                {
/* 216*/            return to(class1);
                }

                public volatile ScopedBindingBuilder analyzeWith(String s)
                {
/* 216*/            return analyzeWith(s);
                }

                public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
                {
/* 216*/            return proxyForSameScope(flag);
                }

                public volatile ScopedBindingBuilder proxy(boolean flag)
                {
/* 216*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder named(String s)
                {
/* 216*/            return named(s);
                }

                public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 216*/            return qualifiedBy(annotation);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, List list)
                {
/* 216*/            return withMetadata(s, list);
                }

                public volatile ScopedBindingBuilder withMetadata(String s, String s1)
                {
/* 216*/            return withMetadata(s, s1);
                }

                public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 216*/            return loadedBy(hk2loader);
                }

                public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 216*/            return to(typeliteral);
                }

                public volatile ScopedBindingBuilder to(Class class1)
                {
/* 216*/            return to(class1);
                }

                public volatile ScopedNamedBindingBuilder analyzeWith(String s)
                {
/* 216*/            return analyzeWith(s);
                }

                public volatile ScopedNamedBindingBuilder proxy(boolean flag)
                {
/* 216*/            return proxy(flag);
                }

                public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
                {
/* 216*/            return qualifiedBy(annotation);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
                {
/* 216*/            return withMetadata(s, list);
                }

                public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
                {
/* 216*/            return withMetadata(s, s1);
                }

                public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
                {
/* 216*/            return loadedBy(hk2loader);
                }

                public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
                {
/* 216*/            return to(typeliteral);
                }

                public volatile ScopedNamedBindingBuilder to(Class class1)
                {
/* 216*/            return to(class1);
                }

                private final Class service;

                public ClassBasedBindingBuilder(Class class1, Type type)
                {
/* 220*/            service = class1;
/* 221*/            if(type != null)
/* 222*/                contracts.add(type);
                }
    }


            AbstractBindingBuilder()
            {
/*  75*/        contracts = new HashSet();
/*  79*/        loader = null;
/*  87*/        qualifiers = new HashSet();
/*  91*/        scopeAnnotation = null;
/*  95*/        scope = null;
/*  99*/        ranked = null;
/* 103*/        name = null;
/* 107*/        proxiable = null;
/* 111*/        proxyForSameScope = null;
/* 128*/        analyzer = null;
            }

            public AbstractBindingBuilder proxy(boolean flag)
            {
/* 115*/        proxiable = Boolean.valueOf(flag);
/* 116*/        return this;
            }

            public AbstractBindingBuilder proxyForSameScope(boolean flag)
            {
/* 121*/        proxyForSameScope = Boolean.valueOf(flag);
/* 122*/        return this;
            }

            public AbstractBindingBuilder analyzeWith(String s)
            {
/* 132*/        analyzer = s;
/* 133*/        return this;
            }

            public AbstractBindingBuilder to(Class class1)
            {
/* 138*/        contracts.add(class1);
/* 139*/        return this;
            }

            public AbstractBindingBuilder to(TypeLiteral typeliteral)
            {
/* 144*/        contracts.add(typeliteral.getType());
/* 145*/        return this;
            }

            public AbstractBindingBuilder to(Type type)
            {
/* 150*/        contracts.add(type);
/* 151*/        return this;
            }

            public AbstractBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/* 156*/        loader = hk2loader;
/* 157*/        return this;
            }

            public AbstractBindingBuilder withMetadata(String s, String s1)
            {
/* 162*/        metadata.add(s, s1);
/* 163*/        return this;
            }

            public AbstractBindingBuilder withMetadata(String s, List list)
            {
                String s1;
/* 168*/        for(list = list.iterator(); list.hasNext(); metadata.add(s, s1))
/* 168*/            s1 = (String)list.next();

/* 171*/        return this;
            }

            public AbstractBindingBuilder qualifiedBy(Annotation annotation)
            {
/* 176*/        if(javax/inject/Named.equals(annotation.annotationType()))
/* 177*/            name = ((Named)annotation).value();
/* 179*/        qualifiers.add(annotation);
/* 180*/        return this;
            }

            public AbstractBindingBuilder in(Annotation annotation)
            {
/* 185*/        scopeAnnotation = annotation;
/* 186*/        return this;
            }

            public AbstractBindingBuilder in(Class class1)
            {
/* 191*/        scope = class1;
/* 192*/        return this;
            }

            public AbstractBindingBuilder named(String s)
            {
/* 197*/        name = s;
/* 198*/        return this;
            }

            public void ranked(int i)
            {
/* 203*/        ranked = Integer.valueOf(i);
            }

            abstract void complete(DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader);

            static AbstractBindingBuilder create(Class class1, boolean flag)
            {
/* 469*/        return new ClassBasedBindingBuilder(class1, flag ? ((Type) (class1)) : null);
            }

            static AbstractBindingBuilder create(TypeLiteral typeliteral, boolean flag)
            {
/* 481*/        return new ClassBasedBindingBuilder(typeliteral.getRawType(), flag ? typeliteral.getType() : null);
            }

            static AbstractBindingBuilder create(Object obj)
            {
/* 491*/        return new InstanceBasedBindingBuilder(obj);
            }

            static AbstractBindingBuilder createFactoryBinder(Factory factory)
            {
/* 501*/        return new FactoryInstanceBasedBindingBuilder(factory);
            }

            static AbstractBindingBuilder createFactoryBinder(Class class1, Class class2)
            {
/* 512*/        return new FactoryTypeBasedBindingBuilder(class1, class2);
            }

            public volatile ServiceBindingBuilder analyzeWith(String s)
            {
/*  69*/        return analyzeWith(s);
            }

            public volatile ServiceBindingBuilder proxyForSameScope(boolean flag)
            {
/*  69*/        return proxyForSameScope(flag);
            }

            public volatile ServiceBindingBuilder proxy(boolean flag)
            {
/*  69*/        return proxy(flag);
            }

            public volatile NamedBindingBuilder named(String s)
            {
/*  69*/        return named(s);
            }

            public volatile ScopedBindingBuilder in(Class class1)
            {
/*  69*/        return in(class1);
            }

            public volatile ScopedBindingBuilder in(Annotation annotation)
            {
/*  69*/        return in(annotation);
            }

            public volatile ServiceBindingBuilder qualifiedBy(Annotation annotation)
            {
/*  69*/        return qualifiedBy(annotation);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, List list)
            {
/*  69*/        return withMetadata(s, list);
            }

            public volatile ServiceBindingBuilder withMetadata(String s, String s1)
            {
/*  69*/        return withMetadata(s, s1);
            }

            public volatile ServiceBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/*  69*/        return loadedBy(hk2loader);
            }

            public volatile ServiceBindingBuilder to(Type type)
            {
/*  69*/        return to(type);
            }

            public volatile ServiceBindingBuilder to(TypeLiteral typeliteral)
            {
/*  69*/        return to(typeliteral);
            }

            public volatile ServiceBindingBuilder to(Class class1)
            {
/*  69*/        return to(class1);
            }

            public volatile NamedBindingBuilder proxy(boolean flag)
            {
/*  69*/        return proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder in(Class class1)
            {
/*  69*/        return in(class1);
            }

            public volatile NamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/*  69*/        return qualifiedBy(annotation);
            }

            public volatile NamedBindingBuilder withMetadata(String s, List list)
            {
/*  69*/        return withMetadata(s, list);
            }

            public volatile NamedBindingBuilder withMetadata(String s, String s1)
            {
/*  69*/        return withMetadata(s, s1);
            }

            public volatile NamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/*  69*/        return loadedBy(hk2loader);
            }

            public volatile NamedBindingBuilder to(TypeLiteral typeliteral)
            {
/*  69*/        return to(typeliteral);
            }

            public volatile NamedBindingBuilder to(Class class1)
            {
/*  69*/        return to(class1);
            }

            public volatile ScopedBindingBuilder analyzeWith(String s)
            {
/*  69*/        return analyzeWith(s);
            }

            public volatile ScopedBindingBuilder proxyForSameScope(boolean flag)
            {
/*  69*/        return proxyForSameScope(flag);
            }

            public volatile ScopedBindingBuilder proxy(boolean flag)
            {
/*  69*/        return proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder named(String s)
            {
/*  69*/        return named(s);
            }

            public volatile ScopedBindingBuilder qualifiedBy(Annotation annotation)
            {
/*  69*/        return qualifiedBy(annotation);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, List list)
            {
/*  69*/        return withMetadata(s, list);
            }

            public volatile ScopedBindingBuilder withMetadata(String s, String s1)
            {
/*  69*/        return withMetadata(s, s1);
            }

            public volatile ScopedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/*  69*/        return loadedBy(hk2loader);
            }

            public volatile ScopedBindingBuilder to(TypeLiteral typeliteral)
            {
/*  69*/        return to(typeliteral);
            }

            public volatile ScopedBindingBuilder to(Class class1)
            {
/*  69*/        return to(class1);
            }

            public volatile ScopedNamedBindingBuilder analyzeWith(String s)
            {
/*  69*/        return analyzeWith(s);
            }

            public volatile ScopedNamedBindingBuilder proxy(boolean flag)
            {
/*  69*/        return proxy(flag);
            }

            public volatile ScopedNamedBindingBuilder qualifiedBy(Annotation annotation)
            {
/*  69*/        return qualifiedBy(annotation);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, List list)
            {
/*  69*/        return withMetadata(s, list);
            }

            public volatile ScopedNamedBindingBuilder withMetadata(String s, String s1)
            {
/*  69*/        return withMetadata(s, s1);
            }

            public volatile ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader)
            {
/*  69*/        return loadedBy(hk2loader);
            }

            public volatile ScopedNamedBindingBuilder to(TypeLiteral typeliteral)
            {
/*  69*/        return to(typeliteral);
            }

            public volatile ScopedNamedBindingBuilder to(Class class1)
            {
/*  69*/        return to(class1);
            }

            Set contracts;
            HK2Loader loader;
            final MultiMap metadata = new MultiMap();
            Set qualifiers;
            Annotation scopeAnnotation;
            Class scope;
            Integer ranked;
            String name;
            Boolean proxiable;
            Boolean proxyForSameScope;
            String analyzer;
}
