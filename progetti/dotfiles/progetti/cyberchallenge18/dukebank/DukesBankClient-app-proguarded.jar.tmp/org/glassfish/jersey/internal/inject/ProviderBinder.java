// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProviderBinder.java

package org.glassfish.jersey.internal.inject;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.RuntimeType;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.internal.ComponentBag;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            CustomAnnotationLiteral, Injections, Providers

public class ProviderBinder
{

            public ProviderBinder(ServiceLocator servicelocator)
            {
/*  94*/        locator = servicelocator;
            }

            public void bindInstances(Iterable iterable)
            {
/* 104*/        DynamicConfiguration dynamicconfiguration = Injections.getConfiguration(locator);
                Object obj;
/* 105*/        for(iterable = iterable.iterator(); iterable.hasNext(); bindInstance(obj, dynamicconfiguration))
/* 105*/            obj = iterable.next();

/* 108*/        dynamicconfiguration.commit();
            }

            public transient void bindClasses(Class aclass[])
            {
/* 118*/        if(aclass != null && aclass.length > 0)
                {
/* 119*/            DynamicConfiguration dynamicconfiguration = Injections.getConfiguration(locator);
/* 120*/            int i = (aclass = aclass).length;
/* 120*/            for(int j = 0; j < i; j++)
                    {
/* 120*/                Class class1 = aclass[j];
/* 121*/                bindClass(class1, locator, dynamicconfiguration, false);
                    }

/* 123*/            dynamicconfiguration.commit();
                }
            }

            public void bindClasses(Iterable iterable)
            {
/* 134*/        bindClasses(iterable, false);
            }

            public void bindClasses(Iterable iterable, boolean flag)
            {
/* 152*/        if(iterable == null || !iterable.iterator().hasNext())
/* 153*/            return;
/* 156*/        DynamicConfiguration dynamicconfiguration = Injections.getConfiguration(locator);
                Class class1;
/* 157*/        for(iterable = iterable.iterator(); iterable.hasNext(); bindClass(class1, locator, dynamicconfiguration, flag))
/* 157*/            class1 = (Class)iterable.next();

/* 160*/        dynamicconfiguration.commit();
            }

            public static void bindProvider(Class class1, ContractProvider contractprovider, DynamicConfiguration dynamicconfiguration)
            {
                ScopedBindingBuilder scopedbindingbuilder;
/* 173*/        for(Iterator iterator = contractprovider.getContracts().iterator(); iterator.hasNext(); Injections.addBinding(scopedbindingbuilder, dynamicconfiguration))
                {
/* 173*/            Class class2 = (Class)iterator.next();
/* 174*/            (scopedbindingbuilder = Injections.newBinder(class1).in(contractprovider.getScope()).qualifiedBy(CustomAnnotationLiteral.INSTANCE)).to(class2);
                    int i;
/* 181*/            if((i = contractprovider.getPriority(class2)) >= 0)
/* 183*/                scopedbindingbuilder.ranked(i);
                }

            }

            public static void bindProvider(Object obj, ContractProvider contractprovider, DynamicConfiguration dynamicconfiguration)
            {
                ScopedBindingBuilder scopedbindingbuilder;
/* 204*/        for(Iterator iterator = contractprovider.getContracts().iterator(); iterator.hasNext(); Injections.addBinding(scopedbindingbuilder, dynamicconfiguration))
                {
/* 204*/            Class class1 = (Class)iterator.next();
/* 205*/            (scopedbindingbuilder = Injections.newBinder(obj).qualifiedBy(CustomAnnotationLiteral.INSTANCE)).to(class1);
                    int i;
/* 211*/            if((i = contractprovider.getPriority(class1)) >= 0)
/* 213*/                scopedbindingbuilder.ranked(i);
                }

            }

            public static void bindProviders(ComponentBag componentbag, ServiceLocator servicelocator)
            {
/* 228*/        bindProviders(componentbag, null, Collections.emptySet(), servicelocator);
            }

            public static void bindProviders(ComponentBag componentbag, RuntimeType runtimetype, Set set, ServiceLocator servicelocator)
            {
/* 244*/        servicelocator = Injections.getConfiguration(servicelocator);
/* 245*/        bindProviders(componentbag, runtimetype, set, ((DynamicConfiguration) (servicelocator)));
/* 246*/        servicelocator.commit();
            }

            public static void bindProviders(ComponentBag componentbag, RuntimeType runtimetype, Set set, DynamicConfiguration dynamicconfiguration)
            {
/* 262*/        Predicate predicate = new Predicate() {

                    public final boolean apply(ContractProvider contractprovider1)
                    {
/* 265*/                return ComponentBag.EXCLUDE_EMPTY.apply(contractprovider1) && ComponentBag.EXCLUDE_META_PROVIDERS.apply(contractprovider1);
                    }

                    public final volatile boolean apply(Object obj2)
                    {
/* 262*/                return apply((ContractProvider)obj2);
                    }

        };
/* 270*/        Object obj = Sets.newLinkedHashSet(componentbag.getClasses(predicate));
/* 271*/        if(runtimetype != null)
/* 272*/            obj = Sets.filter(((Set) (obj)), new Predicate(componentbag, runtimetype, set) {

                        public final boolean apply(Class class2)
                        {
/* 275*/                    return Providers.checkProviderRuntime(class2, componentBag.getModel(class2), constrainedTo, registeredClasses == null || !registeredClasses.contains(class2), false);
                        }

                        public final volatile boolean apply(Object obj2)
                        {
/* 272*/                    return apply((Class)obj2);
                        }

                        final ComponentBag val$componentBag;
                        final RuntimeType val$constrainedTo;
                        final Set val$registeredClasses;

                    
                    {
/* 272*/                componentBag = componentbag;
/* 272*/                constrainedTo = runtimetype;
/* 272*/                registeredClasses = set;
/* 272*/                super();
                    }
            });
                Class class1;
                ContractProvider contractprovider;
/* 284*/        for(obj = ((Set) (obj)).iterator(); ((Iterator) (obj)).hasNext(); bindProvider(class1, contractprovider, dynamicconfiguration))
                {
/* 284*/            class1 = (Class)((Iterator) (obj)).next();
/* 285*/            contractprovider = componentbag.getModel(class1);
                }

/* 290*/        obj = componentbag.getInstances(predicate);
/* 291*/        if(runtimetype != null)
/* 292*/            obj = Sets.filter(((Set) (obj)), new Predicate(componentbag, runtimetype, set) {

                        public final boolean apply(Object obj2)
                        {
/* 295*/                    return Providers.checkProviderRuntime(((Class) (obj2 = obj2.getClass())), componentBag.getModel(((Class) (obj2))), constrainedTo, registeredClasses == null || !registeredClasses.contains(obj2), false);
                        }

                        final ComponentBag val$componentBag;
                        final RuntimeType val$constrainedTo;
                        final Set val$registeredClasses;

                    
                    {
/* 292*/                componentBag = componentbag;
/* 292*/                constrainedTo = runtimetype;
/* 292*/                registeredClasses = set;
/* 292*/                super();
                    }
            });
                Object obj1;
/* 305*/        for(Iterator iterator = ((Set) (obj)).iterator(); iterator.hasNext(); bindProvider(obj1, runtimetype, dynamicconfiguration))
                {
/* 305*/            obj1 = iterator.next();
/* 306*/            runtimetype = componentbag.getModel(obj1.getClass());
                }

            }

            private void bindInstance(Object obj, DynamicConfiguration dynamicconfiguration)
            {
                Class class1;
/* 313*/        for(Iterator iterator = Providers.getProviderContracts(obj.getClass()).iterator(); iterator.hasNext(); Injections.addBinding(Injections.newBinder(obj).to(class1).qualifiedBy(CustomAnnotationLiteral.INSTANCE), dynamicconfiguration))
/* 313*/            class1 = (Class)iterator.next();

            }

            private void bindClass(Class class1, ServiceLocator servicelocator, DynamicConfiguration dynamicconfiguration, boolean flag)
            {
/* 323*/        Class class2 = getProviderScope(class1);
/* 325*/        if(flag)
                {
/* 326*/            flag = dynamicconfiguration.bind(BuilderHelper.activeLink(class1).to(class1).in(class2).build());
                    Object obj;
/* 328*/            for(class1 = Providers.getProviderContracts(class1).iterator(); class1.hasNext(); dynamicconfiguration.bind(((org.glassfish.hk2.api.Descriptor) (obj))))
                    {
/* 328*/                obj = (Class)class1.next();
/* 329*/                ((AliasDescriptor) (obj = new AliasDescriptor(servicelocator, flag, ((Class) (obj)).getName(), null))).setScope(class2.getName());
/* 331*/                ((AliasDescriptor) (obj)).addQualifierAnnotation(CustomAnnotationLiteral.INSTANCE);
                    }

/* 335*/            return;
                }
/* 336*/        flag = Injections.newBinder(class1).in(class2).qualifiedBy(CustomAnnotationLiteral.INSTANCE);
                Class class3;
/* 338*/        for(class1 = Providers.getProviderContracts(class1).iterator(); class1.hasNext(); flag.to(class3))
/* 338*/            class3 = (Class)class1.next();

/* 341*/        Injections.addBinding(flag, dynamicconfiguration);
            }

            private Class getProviderScope(Class class1)
            {
/* 346*/        Class class2 = javax/inject/Singleton;
/* 347*/        if(class1.isAnnotationPresent(org/glassfish/hk2/api/PerLookup))
/* 348*/            class2 = org/glassfish/hk2/api/PerLookup;
/* 350*/        return class2;
            }

            private final ServiceLocator locator;
}
