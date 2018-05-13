// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonConfig.java

package org.glassfish.jersey.model.internal;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Singleton;
import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.collect.*;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.ExtendedConfig;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.internal.spi.ForcedAutoDiscoverable;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag, FeatureContextWrapper, ManagedObjectsFinalizer

public class CommonConfig
    implements FeatureContext, ExtendedConfig
{
    static final class FeatureRegistration
    {

                public final Class getFeatureClass()
                {
/* 162*/            return featureClass;
                }

                public final Feature getFeature()
                {
/* 173*/            return feature;
                }

                public final boolean equals(Object obj)
                {
/* 178*/            if(this == obj)
/* 179*/                return true;
/* 181*/            if(!(obj instanceof FeatureRegistration))
/* 182*/                return false;
/* 184*/            obj = (FeatureRegistration)obj;
/* 186*/            return featureClass == ((FeatureRegistration) (obj)).featureClass || feature != null && (feature == ((FeatureRegistration) (obj)).feature || feature.equals(((FeatureRegistration) (obj)).feature));
                }

                public final int hashCode()
                {
/* 193*/            int i = 611 + (feature == null ? 0 : feature.hashCode());
/* 194*/            return i = i * 13 + (featureClass == null ? 0 : featureClass.hashCode());
                }

                private final Class featureClass;
                private final Feature feature;

                private FeatureRegistration(Class class1)
                {
/* 147*/            featureClass = class1;
/* 148*/            feature = null;
                }

                private FeatureRegistration(Feature feature1)
                {
/* 152*/            featureClass = feature1.getClass();
/* 153*/            feature = feature1;
                }


    }


            public CommonConfig(RuntimeType runtimetype, Predicate predicate)
            {
/* 220*/        type = runtimetype;
/* 222*/        properties = new HashMap();
/* 223*/        immutablePropertiesView = Collections.unmodifiableMap(properties);
/* 224*/        immutablePropertyNames = Collections.unmodifiableCollection(properties.keySet());
/* 226*/        componentBag = ComponentBag.newInstance(predicate);
/* 228*/        newFeatureRegistrations = new LinkedList();
/* 230*/        enabledFeatureClasses = Sets.newIdentityHashSet();
/* 231*/        enabledFeatures = Sets.newHashSet();
/* 233*/        disableMetaProviderConfiguration = false;
            }

            public CommonConfig(CommonConfig commonconfig)
            {
/* 242*/        type = commonconfig.type;
/* 244*/        properties = new HashMap(commonconfig.properties.size());
/* 245*/        immutablePropertiesView = Collections.unmodifiableMap(properties);
/* 246*/        immutablePropertyNames = Collections.unmodifiableCollection(properties.keySet());
/* 248*/        componentBag = commonconfig.componentBag.copy();
/* 250*/        newFeatureRegistrations = Lists.newLinkedList();
/* 251*/        enabledFeatureClasses = Sets.newIdentityHashSet();
/* 252*/        enabledFeatures = Sets.newHashSet();
/* 254*/        copy(commonconfig, false);
            }

            private void copy(CommonConfig commonconfig, boolean flag)
            {
/* 264*/        properties.clear();
/* 265*/        properties.putAll(commonconfig.properties);
/* 267*/        newFeatureRegistrations.clear();
/* 268*/        newFeatureRegistrations.addAll(commonconfig.newFeatureRegistrations);
/* 270*/        enabledFeatureClasses.clear();
/* 271*/        enabledFeatureClasses.addAll(commonconfig.enabledFeatureClasses);
/* 273*/        enabledFeatures.clear();
/* 274*/        enabledFeatures.addAll(commonconfig.enabledFeatures);
/* 276*/        disableMetaProviderConfiguration = commonconfig.disableMetaProviderConfiguration;
/* 278*/        if(flag)
/* 279*/            componentBag.loadFrom(commonconfig.componentBag);
            }

            public ExtendedConfig getConfiguration()
            {
/* 285*/        return this;
            }

            public RuntimeType getRuntimeType()
            {
/* 290*/        return type;
            }

            public Map getProperties()
            {
/* 295*/        return immutablePropertiesView;
            }

            public Object getProperty(String s)
            {
/* 300*/        return properties.get(s);
            }

            public boolean isProperty(String s)
            {
/* 305*/        return PropertiesHelper.isProperty(getProperty(s));
            }

            public Collection getPropertyNames()
            {
/* 310*/        return immutablePropertyNames;
            }

            public boolean isEnabled(Class class1)
            {
/* 315*/        return enabledFeatureClasses.contains(class1);
            }

            public boolean isEnabled(Feature feature)
            {
/* 320*/        return enabledFeatures.contains(feature);
            }

            public boolean isRegistered(Object obj)
            {
/* 325*/        return componentBag.getInstances().contains(obj);
            }

            public boolean isRegistered(Class class1)
            {
/* 330*/        return componentBag.getRegistrations().contains(class1);
            }

            public Map getContracts(Class class1)
            {
/* 335*/        if((class1 = componentBag.getModel(class1)) == null)
/* 336*/            return Collections.emptyMap();
/* 336*/        else
/* 336*/            return class1.getContractMap();
            }

            public Set getClasses()
            {
/* 341*/        return componentBag.getClasses();
            }

            public Set getInstances()
            {
/* 346*/        return componentBag.getInstances();
            }

            public final ComponentBag getComponentBag()
            {
/* 355*/        return componentBag;
            }

            protected Inflector getModelEnhancer(Class class1)
            {
/* 373*/        return ComponentBag.AS_IS;
            }

            public CommonConfig setProperties(Map map)
            {
/* 383*/        properties.clear();
/* 385*/        if(map != null)
/* 386*/            properties.putAll(map);
/* 388*/        return this;
            }

            public CommonConfig addProperties(Map map)
            {
/* 401*/        if(map != null)
/* 402*/            properties.putAll(map);
/* 404*/        return this;
            }

            public CommonConfig property(String s, Object obj)
            {
/* 409*/        if(obj == null)
/* 410*/            properties.remove(s);
/* 412*/        else
/* 412*/            properties.put(s, obj);
/* 414*/        return this;
            }

            public CommonConfig register(Class class1)
            {
/* 419*/        checkComponentClassNotNull(class1);
/* 420*/        if(componentBag.register(class1, getModelEnhancer(class1)))
/* 421*/            processFeatureRegistration(null, class1);
/* 424*/        return this;
            }

            public CommonConfig register(Class class1, int i)
            {
/* 429*/        checkComponentClassNotNull(class1);
/* 430*/        if(componentBag.register(class1, i, getModelEnhancer(class1)))
/* 431*/            processFeatureRegistration(null, class1);
/* 434*/        return this;
            }

            public transient CommonConfig register(Class class1, Class aclass[])
            {
/* 439*/        checkComponentClassNotNull(class1);
/* 440*/        if(aclass == null || aclass.length == 0)
                {
/* 441*/            LOGGER.warning(LocalizationMessages.COMPONENT_CONTRACTS_EMPTY_OR_NULL(class1));
/* 442*/            return this;
                }
/* 444*/        if(componentBag.register(class1, asNewIdentitySet(aclass), getModelEnhancer(class1)))
/* 445*/            processFeatureRegistration(null, class1);
/* 448*/        return this;
            }

            public CommonConfig register(Class class1, Map map)
            {
/* 453*/        checkComponentClassNotNull(class1);
/* 454*/        if(componentBag.register(class1, map, getModelEnhancer(class1)))
/* 455*/            processFeatureRegistration(null, class1);
/* 458*/        return this;
            }

            public CommonConfig register(Object obj)
            {
/* 463*/        checkProviderNotNull(obj);
/* 465*/        Class class1 = obj.getClass();
/* 466*/        if(componentBag.register(obj, getModelEnhancer(class1)))
/* 467*/            processFeatureRegistration(obj, class1);
/* 470*/        return this;
            }

            public CommonConfig register(Object obj, int i)
            {
/* 475*/        checkProviderNotNull(obj);
/* 476*/        Class class1 = obj.getClass();
/* 477*/        if(componentBag.register(obj, i, getModelEnhancer(class1)))
/* 478*/            processFeatureRegistration(obj, class1);
/* 481*/        return this;
            }

            public transient CommonConfig register(Object obj, Class aclass[])
            {
/* 486*/        checkProviderNotNull(obj);
/* 487*/        Class class1 = obj.getClass();
/* 488*/        if(aclass == null || aclass.length == 0)
                {
/* 489*/            LOGGER.warning(LocalizationMessages.COMPONENT_CONTRACTS_EMPTY_OR_NULL(class1));
/* 490*/            return this;
                }
/* 492*/        if(componentBag.register(obj, asNewIdentitySet(aclass), getModelEnhancer(class1)))
/* 493*/            processFeatureRegistration(obj, class1);
/* 496*/        return this;
            }

            public CommonConfig register(Object obj, Map map)
            {
/* 501*/        checkProviderNotNull(obj);
/* 502*/        Class class1 = obj.getClass();
/* 503*/        if(componentBag.register(obj, map, getModelEnhancer(class1)))
/* 504*/            processFeatureRegistration(obj, class1);
/* 507*/        return this;
            }

            private void processFeatureRegistration(Object obj, Class class1)
            {
                ContractProvider contractprovider;
/* 511*/        if((contractprovider = componentBag.getModel(class1)).getContracts().contains(javax/ws/rs/core/Feature))
                {
/* 514*/            obj = obj == null ? ((Object) (new FeatureRegistration(class1))) : ((Object) (new FeatureRegistration((Feature)obj)));
/* 517*/            newFeatureRegistrations.add(obj);
                }
            }

            public CommonConfig loadFrom(Configuration configuration)
            {
/* 532*/        if(configuration instanceof CommonConfig)
                {
/* 534*/            CommonConfig commonconfig = (CommonConfig)configuration;
/* 536*/            copy(commonconfig, true);
/* 537*/            disableMetaProviderConfiguration = !commonconfig.enabledFeatureClasses.isEmpty();
                } else
                {
/* 539*/            setProperties(configuration.getProperties());
/* 541*/            enabledFeatures.clear();
/* 542*/            enabledFeatureClasses.clear();
/* 544*/            componentBag.clear();
/* 545*/            resetRegistrations();
                    Class class1;
/* 547*/            for(Iterator iterator = configuration.getClasses().iterator(); iterator.hasNext(); register(class1, configuration.getContracts(class1)))
                    {
/* 547*/                class1 = (Class)iterator.next();
/* 548*/                if(javax/ws/rs/core/Feature.isAssignableFrom(class1) && configuration.isEnabled(class1))
/* 549*/                    disableMetaProviderConfiguration = true;
                    }

                    Object obj;
/* 555*/            for(Iterator iterator1 = configuration.getInstances().iterator(); iterator1.hasNext(); register(obj, configuration.getContracts(obj.getClass())))
/* 555*/                if(((obj = iterator1.next()) instanceof Feature) && configuration.isEnabled((Feature)obj))
/* 557*/                    disableMetaProviderConfiguration = true;

                }
/* 564*/        return this;
            }

            private transient Set asNewIdentitySet(Class aclass[])
            {
                Set set;
/* 568*/        (set = Sets.newIdentityHashSet()).addAll(Arrays.asList(aclass));
/* 570*/        return set;
            }

            private void checkProviderNotNull(Object obj)
            {
/* 574*/        if(obj == null)
/* 575*/            throw new IllegalArgumentException(LocalizationMessages.COMPONENT_CANNOT_BE_NULL());
/* 577*/        else
/* 577*/            return;
            }

            private void checkComponentClassNotNull(Class class1)
            {
/* 580*/        if(class1 == null)
/* 581*/            throw new IllegalArgumentException(LocalizationMessages.COMPONENT_CLASS_CANNOT_BE_NULL());
/* 583*/        else
/* 583*/            return;
            }

            public void configureAutoDiscoverableProviders(ServiceLocator servicelocator, boolean flag)
            {
/* 593*/        if(!disableMetaProviderConfiguration)
                {
/* 594*/            TreeSet treeset = new TreeSet(new Comparator() {

                        public int compare(AutoDiscoverable autodiscoverable1, AutoDiscoverable autodiscoverable2)
                        {
/* 597*/                    autodiscoverable1 = autodiscoverable1.getClass().isAnnotationPresent(javax/annotation/Priority) ? ((AutoDiscoverable) (((Priority)autodiscoverable1.getClass().getAnnotation(javax/annotation/Priority)).value())) : 5000;
/* 599*/                    autodiscoverable2 = autodiscoverable2.getClass().isAnnotationPresent(javax/annotation/Priority) ? ((AutoDiscoverable) (((Priority)autodiscoverable2.getClass().getAnnotation(javax/annotation/Priority)).value())) : 5000;
/* 602*/                    return autodiscoverable1 >= autodiscoverable2 && autodiscoverable1 != autodiscoverable2 ? 1 : -1;
                        }

                        public volatile int compare(Object obj, Object obj1)
                        {
/* 594*/                    return compare((AutoDiscoverable)obj, (AutoDiscoverable)obj1);
                        }

                        final CommonConfig this$0;

                    
                    {
/* 594*/                this$0 = CommonConfig.this;
/* 594*/                super();
                    }
            });
/* 607*/            LinkedList linkedlist = new LinkedList();
                    Class aclass[];
/* 608*/            int i = (aclass = ServiceFinder.find(org/glassfish/jersey/internal/spi/ForcedAutoDiscoverable, true).toClassArray()).length;
/* 608*/            for(int j = 0; j < i; j++)
                    {
/* 608*/                Class class1 = aclass[j];
/* 610*/                linkedlist.add(servicelocator.createAndInitialize(class1));
                    }

/* 612*/            treeset.addAll(linkedlist);
/* 615*/            if(!flag)
/* 616*/                treeset.addAll(Providers.getProviders(servicelocator, org/glassfish/jersey/internal/spi/AutoDiscoverable));
/* 619*/            Iterator iterator = treeset.iterator();
/* 619*/            do
                    {
/* 619*/                if(!iterator.hasNext())
/* 619*/                    break;
                        AutoDiscoverable autodiscoverable;
                        ConstrainedTo constrainedto;
/* 619*/                if((constrainedto = (ConstrainedTo)(autodiscoverable = (AutoDiscoverable)iterator.next()).getClass().getAnnotation(javax/ws/rs/ConstrainedTo)) == null || type.equals(constrainedto.value()))
/* 624*/                    try
                            {
/* 624*/                        autodiscoverable.configure(this);
                            }
/* 625*/                    catch(Exception exception)
                            {
/* 626*/                        LOGGER.log(Level.FINE, LocalizationMessages.AUTODISCOVERABLE_CONFIGURATION_FAILED(autodiscoverable.getClass()), exception);
                            }
                    } while(true);
                }
            }

            public void configureMetaProviders(ServiceLocator servicelocator)
            {
/* 641*/        Set set = configureBinders(servicelocator, Collections.emptySet());
/* 644*/        if(!disableMetaProviderConfiguration)
                {
/* 646*/            registerManagedObjectsFinalizer(servicelocator);
/* 648*/            configureFeatures(servicelocator, new HashSet(), resetRegistrations());
/* 654*/            configureBinders(servicelocator, set);
                }
            }

            private void registerManagedObjectsFinalizer(ServiceLocator servicelocator)
            {
/* 659*/        servicelocator = Injections.getConfiguration(servicelocator);
                org.glassfish.hk2.utilities.binding.ScopedBindingBuilder scopedbindingbuilder;
/* 660*/        Injections.addBinding(scopedbindingbuilder = Injections.newBinder(org/glassfish/jersey/model/internal/ManagedObjectsFinalizer).to(org/glassfish/jersey/model/internal/ManagedObjectsFinalizer).in(javax/inject/Singleton), servicelocator);
/* 664*/        servicelocator.commit();
            }

            private Set configureBinders(ServiceLocator servicelocator, Set set)
            {
                Set set1;
/* 668*/        (set1 = Sets.newIdentityHashSet()).addAll(set);
/* 671*/        if(!(set = getBinders(set)).isEmpty())
                {
/* 673*/            servicelocator = Injections.getConfiguration(servicelocator);
                    Binder binder;
/* 675*/            for(set = set.iterator(); set.hasNext(); set1.add(binder))
/* 675*/                (binder = (Binder)set.next()).bind(servicelocator);

/* 679*/            servicelocator.commit();
                }
/* 682*/        return set1;
            }

            private Collection getBinders(final Set configured)
            {
/* 686*/        return Collections2.filter(Collections2.transform(componentBag.getInstances(ComponentBag.BINDERS_ONLY), CAST_TO_BINDER), new Predicate() {

                    public boolean apply(Binder binder)
                    {
/* 691*/                return !configured.contains(binder);
                    }

                    public volatile boolean apply(Object obj)
                    {
/* 688*/                return apply((Binder)obj);
                    }

                    final Set val$configured;
                    final CommonConfig this$0;

                    
                    {
/* 688*/                this$0 = CommonConfig.this;
/* 688*/                configured = set;
/* 688*/                super();
                    }
        });
            }

            private void configureFeatures(ServiceLocator servicelocator, Set set, List list)
            {
/* 700*/        ManagedObjectsFinalizer managedobjectsfinalizer = (ManagedObjectsFinalizer)servicelocator.getService(org/glassfish/jersey/model/internal/ManagedObjectsFinalizer, new Annotation[0]);
/* 702*/        FeatureContextWrapper featurecontextwrapper = null;
/* 703*/        list = list.iterator();
/* 703*/        do
                {
/* 703*/            if(!list.hasNext())
/* 703*/                break;
/* 703*/            FeatureRegistration featureregistration = (FeatureRegistration)list.next();
/* 704*/            if(set.contains(featureregistration))
                    {
/* 705*/                LOGGER.config(LocalizationMessages.FEATURE_HAS_ALREADY_BEEN_PROCESSED(featureregistration.getFeatureClass()));
                    } else
                    {
                        Feature feature;
/* 709*/                if((feature = featureregistration.getFeature()) == null)
                        {
/* 711*/                    feature = (Feature)servicelocator.createAndInitialize(featureregistration.getFeatureClass());
/* 712*/                    managedobjectsfinalizer.registerForPreDestroyCall(feature);
                        } else
/* 716*/                if(!RuntimeType.CLIENT.equals(type))
/* 717*/                    servicelocator.inject(feature);
/* 721*/                if(enabledFeatures.contains(feature))
                        {
/* 722*/                    LOGGER.config(LocalizationMessages.FEATURE_HAS_ALREADY_BEEN_PROCESSED(feature));
                        } else
                        {
/* 726*/                    if(featurecontextwrapper == null)
/* 728*/                        featurecontextwrapper = new FeatureContextWrapper(this, servicelocator);
                            boolean flag;
/* 730*/                    if(flag = feature.configure(featurecontextwrapper))
                            {
/* 733*/                        set.add(featureregistration);
/* 735*/                        configureFeatures(servicelocator, set, resetRegistrations());
/* 737*/                        enabledFeatureClasses.add(featureregistration.getFeatureClass());
/* 738*/                        enabledFeatures.add(feature);
                            }
                        }
                    }
                } while(true);
            }

            private List resetRegistrations()
            {
/* 744*/        ArrayList arraylist = new ArrayList(newFeatureRegistrations);
/* 745*/        newFeatureRegistrations.clear();
/* 746*/        return arraylist;
            }

            public boolean equals(Object obj)
            {
/* 751*/        if(this == obj)
/* 752*/            return true;
/* 754*/        if(!(obj instanceof CommonConfig))
/* 755*/            return false;
/* 758*/        obj = (CommonConfig)obj;
/* 760*/        if(type != ((CommonConfig) (obj)).type)
/* 761*/            return false;
/* 763*/        if(!properties.equals(((CommonConfig) (obj)).properties))
/* 764*/            return false;
/* 766*/        if(!componentBag.equals(((CommonConfig) (obj)).componentBag))
/* 767*/            return false;
/* 769*/        if(!enabledFeatureClasses.equals(((CommonConfig) (obj)).enabledFeatureClasses))
/* 770*/            return false;
/* 772*/        if(!enabledFeatures.equals(((CommonConfig) (obj)).enabledFeatures))
/* 773*/            return false;
/* 775*/        return newFeatureRegistrations.equals(((CommonConfig) (obj)).newFeatureRegistrations);
            }

            public int hashCode()
            {
/* 784*/        int i = type.hashCode();
/* 785*/        i = i * 31 + properties.hashCode();
/* 786*/        i = i * 31 + componentBag.hashCode();
/* 787*/        i = i * 31 + newFeatureRegistrations.hashCode();
/* 788*/        i = i * 31 + enabledFeatures.hashCode();
/* 789*/        return i = i * 31 + enabledFeatureClasses.hashCode();
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  96*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  96*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  96*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  96*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  96*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  96*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  96*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  96*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  96*/        return property(s, obj);
            }

            public volatile Configuration getConfiguration()
            {
/*  96*/        return getConfiguration();
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/model/internal/CommonConfig.getName());
            private static final Function CAST_TO_BINDER = new Function() {

                public final Binder apply(Object obj)
                {
/* 102*/            return (Binder)org/glassfish/hk2/utilities/Binder.cast(obj);
                }

                public final volatile Object apply(Object obj)
                {
/*  99*/            return apply(obj);
                }

    };
            private final RuntimeType type;
            private final Map properties;
            private final Map immutablePropertiesView;
            private final Collection immutablePropertyNames;
            private final ComponentBag componentBag;
            private final List newFeatureRegistrations;
            private final Set enabledFeatureClasses;
            private final Set enabledFeatures;
            private boolean disableMetaProviderConfiguration;

}
