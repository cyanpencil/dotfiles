// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import java.lang.annotation.Annotation;
import java.util.*;
import javax.annotation.Priority;
import javax.inject.Scope;
import javax.ws.rs.NameBinding;
import javax.ws.rs.core.Feature;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.base.Predicates;
import jersey.repackaged.com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

public class ComponentBag
{
    static class ImmutableComponentBag extends ComponentBag
    {

                public boolean register(Class class1, Inflector inflector)
                {
/* 651*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Class class1, int i, Inflector inflector)
                {
/* 658*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Class class1, Set set, Inflector inflector)
                {
/* 665*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Class class1, Map map, Inflector inflector)
                {
/* 672*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Object obj, Inflector inflector)
                {
/* 677*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Object obj, int i, Inflector inflector)
                {
/* 684*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Object obj, Set set, Inflector inflector)
                {
/* 691*/            throw new IllegalStateException("This instance is read-only.");
                }

                public boolean register(Object obj, Map map, Inflector inflector)
                {
/* 698*/            throw new IllegalStateException("This instance is read-only.");
                }

                public ComponentBag copy()
                {
/* 704*/            return this;
                }

                public ComponentBag immutableCopy()
                {
/* 710*/            return this;
                }

                public void clear()
                {
/* 715*/            throw new IllegalStateException("This instance is read-only.");
                }

                public ImmutableComponentBag(ComponentBag componentbag)
                {
/* 643*/            super(componentbag.registrationStrategy, Sets.newLinkedHashSet(componentbag.classes), Sets.newLinkedHashSet(componentbag.instances), new IdentityHashMap(componentbag.models), null);
                }
    }


            public static ComponentBag newInstance(Predicate predicate)
            {
/* 196*/        return new ComponentBag(predicate);
            }

            private ComponentBag(Predicate predicate)
            {
/* 200*/        registrationStrategy = predicate;
/* 202*/        classes = Sets.newLinkedHashSet();
/* 203*/        instances = Sets.newLinkedHashSet();
/* 204*/        models = Maps.newIdentityHashMap();
/* 206*/        classesView = Collections.unmodifiableSet(classes);
/* 207*/        instancesView = Collections.unmodifiableSet(instances);
/* 208*/        modelKeysView = Collections.unmodifiableSet(models.keySet());
            }

            private ComponentBag(Predicate predicate, Set set, Set set1, Map map)
            {
/* 215*/        registrationStrategy = predicate;
/* 217*/        classes = set;
/* 218*/        instances = set1;
/* 219*/        models = map;
/* 221*/        classesView = Collections.unmodifiableSet(set);
/* 222*/        instancesView = Collections.unmodifiableSet(set1);
/* 223*/        modelKeysView = Collections.unmodifiableSet(map.keySet());
            }

            public boolean register(Class class1, Inflector inflector)
            {
/* 235*/        if((inflector = registerModel(class1, -1, null, inflector)) != 0)
/* 237*/            classes.add(class1);
/* 239*/        return inflector;
            }

            public boolean register(Class class1, int i, Inflector inflector)
            {
/* 254*/        if((i = registerModel(class1, i, null, inflector)) != 0)
/* 256*/            classes.add(class1);
/* 258*/        return i;
            }

            public boolean register(Class class1, Set set, Inflector inflector)
            {
/* 272*/        if((set = registerModel(class1, -1, asMap(set), inflector)) != 0)
/* 275*/            classes.add(class1);
/* 277*/        return set;
            }

            public boolean register(Class class1, Map map, Inflector inflector)
            {
/* 291*/        if((map = registerModel(class1, -1, map, inflector)) != 0)
/* 294*/            classes.add(class1);
/* 296*/        return map;
            }

            public boolean register(Object obj, Inflector inflector)
            {
/* 308*/        Class class1 = obj.getClass();
/* 309*/        if((inflector = registerModel(class1, -1, null, inflector)) != 0)
/* 311*/            instances.add(obj);
/* 313*/        return inflector;
            }

            public boolean register(Object obj, int i, Inflector inflector)
            {
/* 329*/        Class class1 = obj.getClass();
/* 330*/        if((i = registerModel(class1, i, null, inflector)) != 0)
/* 332*/            instances.add(obj);
/* 334*/        return i;
            }

            public boolean register(Object obj, Set set, Inflector inflector)
            {
/* 349*/        Class class1 = obj.getClass();
/* 350*/        if((set = registerModel(class1, -1, asMap(set), inflector)) != 0)
/* 353*/            instances.add(obj);
/* 355*/        return set;
            }

            public boolean register(Object obj, Map map, Inflector inflector)
            {
/* 370*/        Class class1 = obj.getClass();
/* 371*/        if((map = registerModel(class1, -1, map, inflector)) != 0)
/* 374*/            instances.add(obj);
/* 376*/        return map;
            }

            private boolean registerModel(final Class componentClass, final int defaultPriority, final Map contractMap, final Inflector modelEnhancer)
            {
/* 398*/        return ((Boolean)Errors.process(new Producer() {

                    public Boolean call()
                    {
/* 401*/                if(models.containsKey(componentClass))
                        {
/* 402*/                    Errors.error(LocalizationMessages.COMPONENT_TYPE_ALREADY_REGISTERED(componentClass), Severity.WARNING);
/* 404*/                    return Boolean.valueOf(false);
                        }
/* 408*/                ContractProvider contractprovider = ComponentBag.modelFor(componentClass, defaultPriority, contractMap, modelEnhancer);
/* 411*/                if(!registrationStrategy.apply(contractprovider))
                        {
/* 412*/                    return Boolean.valueOf(false);
                        } else
                        {
/* 415*/                    models.put(componentClass, contractprovider);
/* 416*/                    return Boolean.valueOf(true);
                        }
                    }

                    public volatile Object call()
                    {
/* 398*/                return call();
                    }

                    final Class val$componentClass;
                    final int val$defaultPriority;
                    final Map val$contractMap;
                    final Inflector val$modelEnhancer;
                    final ComponentBag this$0;

                    
                    {
/* 398*/                this$0 = ComponentBag.this;
/* 398*/                componentClass = class1;
/* 398*/                defaultPriority = i;
/* 398*/                contractMap = map;
/* 398*/                modelEnhancer = inflector;
/* 398*/                super();
                    }
        })).booleanValue();
            }

            public static ContractProvider modelFor(Class class1)
            {
/* 428*/        return modelFor(class1, -1, null, AS_IS);
            }

            private static ContractProvider modelFor(Class class1, int i, Map map, Inflector inflector)
            {
/* 448*/        if((map = map) == null)
                {
/* 450*/            map = asMap(Providers.getProviderContracts(class1));
                } else
                {
/* 452*/            Iterator iterator = map.keySet().iterator();
/* 453*/            do
                    {
/* 453*/                if(!iterator.hasNext())
/* 454*/                    break;
                        Class class2;
/* 454*/                if((class2 = (Class)iterator.next()) == null)
                        {
/* 456*/                    iterator.remove();
                        } else
                        {
/* 460*/                    boolean flag1 = false;
/* 461*/                    if(!Providers.isSupportedContract(class2))
                            {
/* 462*/                        Errors.error(LocalizationMessages.CONTRACT_NOT_SUPPORTED(class2, class1), Severity.WARNING);
/* 464*/                        flag1 = true;
                            }
/* 466*/                    if(!class2.isAssignableFrom(class1))
                            {
/* 467*/                        Errors.error(LocalizationMessages.CONTRACT_NOT_ASSIGNABLE(class2, class1), Severity.WARNING);
/* 469*/                        flag1 = true;
                            }
/* 471*/                    if(flag1)
/* 472*/                        iterator.remove();
                        }
                    } while(true);
                }
/* 476*/        org.glassfish.jersey.model.ContractProvider.Builder builder = ContractProvider.builder().addContracts(map).defaultPriority(i);
/* 481*/        boolean flag = i == -1;
                Annotation aannotation[];
/* 482*/        class1 = (aannotation = class1.getAnnotations()).length;
/* 482*/        for(i = 0; i < class1; i++)
                {
/* 482*/            if((map = aannotation[i]) instanceof Priority)
                    {
/* 484*/                if(flag)
/* 485*/                    builder.defaultPriority(((Priority)map).value());
/* 485*/                continue;
                    }
                    Annotation aannotation1[];
/* 488*/            int j = (aannotation1 = map.annotationType().getAnnotations()).length;
/* 488*/            for(int k = 0; k < j; k++)
                    {
                        Annotation annotation;
/* 488*/                if((annotation = aannotation1[k]) instanceof NameBinding)
/* 490*/                    builder.addNameBinding(map.annotationType());
/* 492*/                if(annotation instanceof Scope)
/* 493*/                    builder.scope(map.annotationType());
                    }

                }

/* 499*/        return (ContractProvider)inflector.apply(builder);
            }

            private static Map asMap(Set set)
            {
/* 503*/        IdentityHashMap identityhashmap = new IdentityHashMap();
                Class class1;
/* 504*/        for(set = set.iterator(); set.hasNext(); identityhashmap.put(class1, Integer.valueOf(-1)))
/* 504*/            class1 = (Class)set.next();

/* 507*/        return identityhashmap;
            }

            public Set getClasses()
            {
/* 517*/        return classesView;
            }

            public Set getInstances()
            {
/* 527*/        return instancesView;
            }

            public Set getClasses(final Predicate filter)
            {
/* 540*/        return Sets.filter(classesView, new Predicate() {

                    public boolean apply(Class class1)
                    {
/* 543*/                class1 = getModel(class1);
/* 544*/                return filter.apply(class1);
                    }

                    public volatile boolean apply(Object obj)
                    {
/* 540*/                return apply((Class)obj);
                    }

                    final Predicate val$filter;
                    final ComponentBag this$0;

                    
                    {
/* 540*/                this$0 = ComponentBag.this;
/* 540*/                filter = predicate;
/* 540*/                super();
                    }
        });
            }

            public Set getInstances(final Predicate filter)
            {
/* 559*/        return Sets.filter(instancesView, new Predicate() {

                    public boolean apply(Object obj)
                    {
/* 562*/                obj = getModel(obj.getClass());
/* 563*/                return filter.apply(obj);
                    }

                    final Predicate val$filter;
                    final ComponentBag this$0;

                    
                    {
/* 559*/                this$0 = ComponentBag.this;
/* 559*/                filter = predicate;
/* 559*/                super();
                    }
        });
            }

            public Set getRegistrations()
            {
/* 576*/        return modelKeysView;
            }

            public ContractProvider getModel(Class class1)
            {
/* 588*/        return (ContractProvider)models.get(class1);
            }

            public ComponentBag copy()
            {
/* 597*/        return new ComponentBag(registrationStrategy, Sets.newLinkedHashSet(classes), Sets.newLinkedHashSet(instances), new IdentityHashMap(models));
            }

            public ComponentBag immutableCopy()
            {
/* 610*/        return new ImmutableComponentBag(this);
            }

            public void clear()
            {
/* 618*/        classes.clear();
/* 619*/        instances.clear();
/* 620*/        models.clear();
            }

            public void loadFrom(ComponentBag componentbag)
            {
/* 629*/        clear();
/* 631*/        classes.addAll(componentbag.classes);
/* 632*/        instances.addAll(componentbag.instances);
/* 633*/        models.putAll(componentbag.models);
            }


            public static final Predicate EXCLUDE_META_PROVIDERS = new Predicate() {

                public final boolean apply(ContractProvider contractprovider)
                {
/* 101*/            if((contractprovider = contractprovider.getContracts()).isEmpty())
/* 103*/                return true;
/* 106*/            byte byte0 = 0;
/* 107*/            if(contractprovider.contains(javax/ws/rs/core/Feature))
/* 108*/                byte0 = 1;
/* 110*/            if(contractprovider.contains(org/glassfish/hk2/utilities/Binder))
/* 111*/                byte0++;
/* 113*/            return contractprovider.size() > byte0;
                }

                public final volatile boolean apply(Object obj)
                {
/*  98*/            return apply((ContractProvider)obj);
                }

    };
            public static final Predicate BINDERS_ONLY = new Predicate() {

                public final boolean apply(ContractProvider contractprovider)
                {
/* 127*/            return contractprovider.getContracts().contains(org/glassfish/hk2/utilities/Binder);
                }

                public final volatile boolean apply(Object obj)
                {
/* 124*/            return apply((ContractProvider)obj);
                }

    };
            public static final Predicate EXCLUDE_EMPTY = new Predicate() {

                public final boolean apply(ContractProvider contractprovider)
                {
/* 141*/            return !contractprovider.getContracts().isEmpty();
                }

                public final volatile boolean apply(Object obj)
                {
/* 138*/            return apply((ContractProvider)obj);
                }

    };
            public static final Predicate INCLUDE_ALL = Predicates.alwaysTrue();
            public static final Inflector AS_IS = new Inflector() {

                public final ContractProvider apply(org.glassfish.jersey.model.ContractProvider.Builder builder)
                {
/* 161*/            return builder.build();
                }

                public final volatile Object apply(Object obj)
                {
/* 158*/            return apply((org.glassfish.jersey.model.ContractProvider.Builder)obj);
                }

    };
            private final Predicate registrationStrategy;
            private final Set classes;
            private final Set classesView;
            private final Set instances;
            private final Set instancesView;
            private final Map models;
            private final Set modelKeysView;






}
