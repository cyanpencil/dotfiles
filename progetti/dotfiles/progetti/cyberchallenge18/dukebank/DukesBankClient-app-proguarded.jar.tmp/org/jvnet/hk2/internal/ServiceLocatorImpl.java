// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.inject.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.api.messaging.Topic;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.InjecteeImpl;
import org.glassfish.hk2.utilities.cache.*;
import org.glassfish.hk2.utilities.reflection.*;
import org.glassfish.hk2.utilities.reflection.internal.ClassReflectionHelperImpl;

// Referenced classes of package org.jvnet.hk2.internal:
//            CacheKey, Collector, ConstantActiveDescriptor, DescriptorComparator, 
//            DynamicConfigurationImpl, ErrorInformationImpl, ImmediateResults, IndexedListData, 
//            IterableProviderImpl, NarrowResults, PerLocatorUtilities, PerLookupContext, 
//            ServiceHandleComparator, ServiceHandleImpl, SingletonContext, SystemDescriptor, 
//            SystemInjecteeImpl, TopicImpl, Utilities, ValidationInformationImpl

public class ServiceLocatorImpl
    implements ServiceLocator
{
    static class UnqualifiedIndexedFilter
        implements IndexedFilter
    {

                public boolean matches(Descriptor descriptor)
                {
/*2440*/            if(unqualified == null)
/*2440*/                return true;
                    Class aclass[];
/*2442*/            if((aclass = unqualified.value()).length <= 0)
/*2445*/                return descriptor.getQualifiers().isEmpty();
/*2448*/            HashSet hashset = new HashSet();
/*2449*/            int i = (aclass = aclass).length;
/*2449*/            for(int j = 0; j < i; j++)
                    {
/*2449*/                Class class1 = aclass[j];
/*2450*/                hashset.add(class1.getName());
                    }

/*2453*/            for(Iterator iterator = descriptor.getQualifiers().iterator(); iterator.hasNext();)
                    {
/*2453*/                String s = (String)iterator.next();
/*2454*/                if(hashset.contains(s))
/*2454*/                    return false;
                    }

/*2457*/            return true;
                }

                public String getAdvertisedContract()
                {
/*2462*/            return contract;
                }

                public String getName()
                {
/*2467*/            return name;
                }

                private final String contract;
                private final String name;
                private final Unqualified unqualified;

                private UnqualifiedIndexedFilter(String s, String s1, Unqualified unqualified1)
                {
/*2433*/            contract = s;
/*2434*/            name = s1;
/*2435*/            unqualified = unqualified1;
                }

    }

    static class CheckConfigurationData
    {

                private List getUnbinds()
                {
/*2395*/            return unbinds;
                }

                private boolean getInstanceLifecycleModificationsMade()
                {
/*2399*/            return instanceLifeycleModificationMade;
                }

                private boolean getInjectionResolverModificationMade()
                {
/*2403*/            return injectionResolverModificationMade;
                }

                private boolean getErrorHandlerModificationMade()
                {
/*2407*/            return errorHandlerModificationMade;
                }

                private boolean getClassAnalyzerModificationMade()
                {
/*2411*/            return classAnalyzerModificationMade;
                }

                private boolean getDynamicConfigurationListenerModificationMade()
                {
/*2415*/            return dynamicConfigurationListenerModificationMade;
                }

                private HashSet getAffectedContracts()
                {
/*2419*/            return affectedContracts;
                }

                private boolean getInterceptionServiceModificationMade()
                {
/*2423*/            return interceptionServiceModificationMade;
                }

                private final List unbinds;
                private final boolean instanceLifeycleModificationMade;
                private final boolean injectionResolverModificationMade;
                private final boolean errorHandlerModificationMade;
                private final boolean classAnalyzerModificationMade;
                private final boolean dynamicConfigurationListenerModificationMade;
                private final HashSet affectedContracts;
                private final boolean interceptionServiceModificationMade;









                private CheckConfigurationData(List list, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, HashSet hashset, 
                        boolean flag5)
                {
/*2384*/            unbinds = list;
/*2385*/            instanceLifeycleModificationMade = flag;
/*2386*/            injectionResolverModificationMade = flag1;
/*2387*/            errorHandlerModificationMade = flag2;
/*2388*/            classAnalyzerModificationMade = flag3;
/*2389*/            dynamicConfigurationListenerModificationMade = flag4;
/*2390*/            affectedContracts = hashset;
/*2391*/            interceptionServiceModificationMade = flag5;
                }

    }

    class IgdValue
    {

                final NarrowResults results;
                final ImmediateResults immediate;
                final AtomicInteger freshnessKeeper = new AtomicInteger(1);
                final ServiceLocatorImpl this$0;

                public IgdValue(NarrowResults narrowresults, ImmediateResults immediateresults)
                {
/*1134*/            this$0 = ServiceLocatorImpl.this;
/*1134*/            super();
/*1135*/            results = narrowresults;
/*1136*/            immediate = immediateresults;
                }
    }

    static final class IgdCacheKey
    {

                public final int hashCode()
                {
/*1107*/            return hashCode;
                }

                public final boolean equals(Object obj)
                {
/*1112*/            if(obj == null)
/*1113*/                return false;
/*1115*/            if(!(obj instanceof IgdCacheKey))
/*1116*/                return false;
/*1118*/            obj = (IgdCacheKey)obj;
/*1119*/            if(hashCode != ((IgdCacheKey) (obj)).hashCode)
/*1120*/                return false;
/*1122*/            return cacheKey != null ? cacheKey.equals(((IgdCacheKey) (obj)).cacheKey) : ((IgdCacheKey) (obj)).cacheKey == null;
                }

                private final CacheKey cacheKey;
                private final String name;
                private final Injectee onBehalfOf;
                private final Type contractOrImpl;
                private final Annotation qualifiers[];
                private final Filter filter;
                private final int hashCode;







                IgdCacheKey(CacheKey cachekey, String s, Injectee injectee, Type type, Class class1, Annotation aannotation[], Filter filter1)
                {
/*1092*/            cacheKey = cachekey;
/*1093*/            name = s;
/*1094*/            onBehalfOf = injectee;
/*1095*/            contractOrImpl = type;
/*1096*/            qualifiers = aannotation;
/*1097*/            filter = filter1;
/*1100*/            cachekey = 205 + cacheKey.hashCode();
/*1102*/            hashCode = cachekey;
                }
    }


            private static long getAndIncrementLocatorId()
            {
/* 209*/        Object obj = sLock;
/* 209*/        JVM INSTR monitorenter ;
/* 210*/        return currentLocatorId++;
                Exception exception;
/* 211*/        exception;
/* 211*/        throw exception;
            }

            public ServiceLocatorImpl(String s, ServiceLocatorImpl servicelocatorimpl)
            {
/* 150*/        wLock = readWriteLock.writeLock();
/* 151*/        rLock = readWriteLock.readLock();
/* 156*/        neutralContextClassLoader = true;
/* 174*/        hasInterceptionServices = false;
/* 191*/        defaultClassAnalyzer = "default";
/* 192*/        defaultUnqualified = null;
/* 194*/        allResolvers = new ConcurrentHashMap();
/* 206*/        state = ServiceLocatorState.RUNNING;
/* 221*/        locatorName = s;
/* 222*/        parent = servicelocatorimpl;
/* 223*/        if(servicelocatorimpl != null)
/* 224*/            servicelocatorimpl.addChild(this);
/* 229*/        Logger.getLogger().debug((new StringBuilder("Created ServiceLocator ")).append(this).toString());
/* 230*/        if(BIND_TRACING_PATTERN != null)
/* 231*/            Logger.getLogger().debug((new StringBuilder("HK2 will trace binds and unbinds of ")).append(BIND_TRACING_PATTERN).append(" with stacks ").append(BIND_TRACING_STACKS).append(" in ").append(this).toString());
            }

            private boolean callValidate(ValidationService validationservice, ValidationInformation validationinformation)
            {
/* 244*/        return validationservice.getValidator().validate(validationinformation);
/* 246*/        validationservice;
/* 247*/        LinkedList linkedlist = new LinkedList(errorHandlers);
/* 250*/        if(validationservice instanceof MultiException)
/* 251*/            validationservice = (MultiException)validationservice;
/* 254*/        else
/* 254*/            validationservice = new MultiException(validationservice);
/* 257*/        validationservice = new ErrorInformationImpl(ErrorType.VALIDATE_FAILURE, validationinformation.getCandidate(), validationinformation.getInjectee(), validationservice);
/* 263*/        for(validationinformation = linkedlist.iterator(); validationinformation.hasNext();)
                {
/* 263*/            ErrorService errorservice = (ErrorService)validationinformation.next();
/* 265*/            try
                    {
/* 265*/                errorservice.onFailure(validationservice);
                    }
/* 267*/            catch(Throwable throwable)
                    {
/* 268*/                Logger.getLogger().debug("ServiceLocatorImpl", "callValidate", throwable);
                    }
                }

/* 274*/        return false;
            }

            private boolean validate(SystemDescriptor systemdescriptor, Injectee injectee, Filter filter)
            {
/* 285*/        for(Iterator iterator = getAllValidators().iterator(); iterator.hasNext();)
                {
/* 285*/            ValidationService validationservice = (ValidationService)iterator.next();
/* 286*/            if(systemdescriptor.isValidating(validationservice) && !callValidate(validationservice, new ValidationInformationImpl(Operation.LOOKUP, systemdescriptor, injectee, filter)))
/* 290*/                return false;
                }

/* 294*/        return true;
            }

            private List getDescriptors(Filter filter, Injectee injectee, boolean flag, boolean flag1, boolean flag2)
            {
/* 302*/        if(filter == null)
/* 302*/            throw new IllegalArgumentException("filter is null");
/* 305*/        rLock.lock();
                LinkedList linkedlist;
                Object obj1;
                Object obj2;
/* 308*/        if(filter instanceof IndexedFilter)
                {
/* 309*/            if(((IndexedFilter) (obj2 = (IndexedFilter)filter)).getName() != null)
                    {
/* 314*/                Object obj = ((IndexedFilter) (obj2)).getName();
                        Object obj3;
/* 316*/                if((obj3 = (obj = (IndexedListData)descriptorsByName.get(obj)) != null ? ((Object) (((IndexedListData) (obj)).getSortedList())) : null) == null)
/* 319*/                    obj3 = Collections.emptyList();
/* 322*/                if(((IndexedFilter) (obj2)).getAdvertisedContract() != null)
                        {
/* 323*/                    obj1 = new LinkedList();
/* 325*/                    Iterator iterator = ((Collection) (obj3)).iterator();
/* 325*/                    do
                            {
/* 325*/                        if(!iterator.hasNext())
/* 325*/                            break;
/* 325*/                        if(((SystemDescriptor) (obj3 = (SystemDescriptor)iterator.next())).getAdvertisedContracts().contains(((IndexedFilter) (obj2)).getAdvertisedContract()))
/* 327*/                            ((Collection) (obj1)).add(obj3);
                            } while(true);
                        } else
                        {
/* 332*/                    obj1 = obj3;
                        }
                    } else
/* 335*/            if(((IndexedFilter) (obj2)).getAdvertisedContract() != null)
                    {
/* 336*/                String s = ((IndexedFilter) (obj2)).getAdvertisedContract();
                        IndexedListData indexedlistdata;
/* 338*/                if((obj1 = (indexedlistdata = (IndexedListData)descriptorsByAdvertisedContract.get(s)) != null ? ((Object) (indexedlistdata.getSortedList())) : null) == null)
/* 341*/                    obj1 = Collections.emptyList();
                    } else
                    {
/* 346*/                obj1 = allDescriptors.getSortedList();
                    }
                } else
                {
/* 350*/            obj1 = allDescriptors.getSortedList();
                }
/* 353*/        linkedlist = new LinkedList();
/* 355*/        obj2 = ((Collection) (obj1)).iterator();
/* 355*/        do
                {
/* 355*/            if(!((Iterator) (obj2)).hasNext())
/* 355*/                break;
/* 355*/            SystemDescriptor systemdescriptor = (SystemDescriptor)((Iterator) (obj2)).next();
/* 356*/            if((flag2 || !DescriptorVisibility.LOCAL.equals(systemdescriptor.getDescriptorVisibility())) && (!flag1 || validate(systemdescriptor, injectee, filter)) && filter.matches(systemdescriptor))
/* 363*/                linkedlist.add(systemdescriptor);
                } while(true);
/* 367*/        rLock.unlock();
/* 368*/        break MISSING_BLOCK_LABEL_380;
/* 367*/        filter;
/* 367*/        rLock.unlock();
/* 367*/        throw filter;
/* 371*/        if(flag && parent != null)
                {
                    TreeSet treeset;
/* 372*/            (treeset = new TreeSet(DESCRIPTOR_COMPARATOR)).addAll(linkedlist);
/* 375*/            treeset.addAll(parent.getDescriptors(filter, injectee, flag, flag1, false));
/* 377*/            linkedlist.clear();
/* 379*/            linkedlist.addAll(treeset);
                }
/* 382*/        return linkedlist;
            }

            private List protectedGetDescriptors(final Filter filter)
            {
/* 386*/        return (List)AccessController.doPrivileged(new PrivilegedAction() {

                    public List run()
                    {
/* 390*/                return getDescriptors(filter);
                    }

                    public volatile Object run()
                    {
/* 386*/                return run();
                    }

                    final Filter val$filter;
                    final ServiceLocatorImpl this$0;

                    
                    {
/* 386*/                this$0 = ServiceLocatorImpl.this;
/* 386*/                filter = filter1;
/* 386*/                super();
                    }
        });
            }

            public List getDescriptors(Filter filter)
            {
/* 402*/        checkState();
/* 404*/        return (List)ReflectionHelper.cast(getDescriptors(filter, null, true, true, true));
            }

            public ActiveDescriptor getBestDescriptor(Filter filter)
            {
/* 409*/        if(filter == null)
                {
/* 409*/            throw new IllegalArgumentException("filter is null");
                } else
                {
/* 410*/            checkState();
/* 412*/            return (ActiveDescriptor)Utilities.getFirstThingInList(filter = getDescriptors(filter));
                }
            }

            public ActiveDescriptor reifyDescriptor(Descriptor descriptor, Injectee injectee)
                throws MultiException
            {
/* 423*/        checkState();
/* 424*/        if(descriptor == null)
/* 424*/            throw new IllegalArgumentException();
/* 426*/        if(!(descriptor instanceof ActiveDescriptor))
                {
/* 427*/            SystemDescriptor systemdescriptor = new SystemDescriptor(descriptor, true, this, null);
/* 429*/            Class class1 = loadClass(descriptor, injectee);
/* 431*/            Collector collector = new Collector();
/* 432*/            systemdescriptor.reify(class1, collector);
/* 434*/            collector.throwIfErrors();
/* 436*/            return systemdescriptor;
                }
                ActiveDescriptor activedescriptor;
/* 440*/        if((activedescriptor = (ActiveDescriptor)descriptor).isReified())
/* 441*/            return activedescriptor;
                SystemDescriptor systemdescriptor1;
/* 444*/        if(activedescriptor instanceof SystemDescriptor)
/* 445*/            systemdescriptor1 = (SystemDescriptor)activedescriptor;
/* 448*/        else
/* 448*/            systemdescriptor1 = new SystemDescriptor(descriptor, true, this, null);
                Class class2;
/* 451*/        if((class2 = systemdescriptor1.getPreAnalyzedClass()) == null)
/* 453*/            class2 = loadClass(descriptor, injectee);
/* 456*/        descriptor = new Collector();
/* 458*/        systemdescriptor1.reify(class2, descriptor);
/* 460*/        descriptor.throwIfErrors();
/* 462*/        return systemdescriptor1;
            }

            public ActiveDescriptor reifyDescriptor(Descriptor descriptor)
                throws MultiException
            {
/* 471*/        checkState();
/* 472*/        return reifyDescriptor(descriptor, null);
            }

            private ActiveDescriptor secondChanceResolve(Injectee injectee)
            {
                Collector collector;
                List list;
/* 477*/        collector = new Collector();
/* 479*/        list = (List)ReflectionHelper.cast(getAllServiceHandles(org/glassfish/hk2/api/JustInTimeInjectionResolver, new Annotation[0]));
                boolean flag;
/* 484*/        flag = false;
/* 485*/        Object obj = 0;
/* 486*/        Iterator iterator1 = list.iterator();
/* 486*/        do
                {
/* 486*/            if(!iterator1.hasNext())
/* 486*/                break;
/* 486*/            ServiceHandle servicehandle1 = (ServiceHandle)iterator1.next();
/* 487*/            if(injectee.getInjecteeClass() != null && injectee.getInjecteeClass().getName().equals(servicehandle1.getActiveDescriptor().getImplementation()))
/* 496*/                continue;
                    JustInTimeInjectionResolver justintimeinjectionresolver;
/* 496*/            try
                    {
/* 496*/                justintimeinjectionresolver = (JustInTimeInjectionResolver)servicehandle1.getService();
                    }
/* 498*/            catch(MultiException multiexception)
                    {
/* 500*/                Logger.getLogger().debug(servicehandle1.toString(), "secondChanceResolver", multiexception);
/* 501*/                continue;
                    }
/* 504*/            boolean flag1 = false;
/* 506*/            try
                    {
/* 506*/                flag1 = justintimeinjectionresolver.justInTimeResolution(injectee);
                    }
                    // Misplaced declaration of an exception variable
/* 508*/            catch(Object obj)
                    {
/* 509*/                collector.addThrowable(((Throwable) (obj)));
/* 510*/                obj = 1;
                    }
/* 513*/            flag = flag1 || flag;
                } while(true);
/* 516*/        if(obj != 0)
/* 517*/            collector.throwIfErrors();
/* 520*/        if(!flag)
                {
/* 528*/            Iterator iterator2 = list.iterator();
/* 528*/            do
                    {
/* 528*/                if(!iterator2.hasNext())
/* 528*/                    break;
                        ServiceHandle servicehandle2;
/* 528*/                if((servicehandle2 = (ServiceHandle)iterator2.next()).getActiveDescriptor().getScope() == null || org/glassfish/hk2/api/PerLookup.getName().equals(servicehandle2.getActiveDescriptor().getScope()))
/* 532*/                    servicehandle2.destroy();
                    } while(true);
/* 534*/            return null;
                }
/* 525*/        ActiveDescriptor activedescriptor = internalGetInjecteeDescriptor(injectee, false);
/* 528*/        Iterator iterator3 = list.iterator();
/* 528*/        do
                {
/* 528*/            if(!iterator3.hasNext())
/* 528*/                break;
                    ServiceHandle servicehandle3;
/* 528*/            if((servicehandle3 = (ServiceHandle)iterator3.next()).getActiveDescriptor().getScope() == null || org/glassfish/hk2/api/PerLookup.getName().equals(servicehandle3.getActiveDescriptor().getScope()))
/* 532*/                servicehandle3.destroy();
                } while(true);
/* 534*/        return activedescriptor;
/* 528*/        injectee;
/* 528*/        Iterator iterator = list.iterator();
/* 528*/        do
                {
/* 528*/            if(!iterator.hasNext())
/* 528*/                break;
                    ServiceHandle servicehandle;
/* 528*/            if((servicehandle = (ServiceHandle)iterator.next()).getActiveDescriptor().getScope() == null || org/glassfish/hk2/api/PerLookup.getName().equals(servicehandle.getActiveDescriptor().getScope()))
/* 532*/                servicehandle.destroy();
                } while(true);
/* 534*/        throw injectee;
            }

            private ActiveDescriptor internalGetInjecteeDescriptor(Injectee injectee, boolean flag)
            {
/* 539*/        if(injectee == null)
/* 539*/            throw new IllegalArgumentException();
/* 540*/        checkState();
                boolean flag1;
/* 542*/        if((flag1 = ReflectionHelper.getRawClass(flag = injectee.getRequiredType())) == null)
/* 545*/            throw new MultiException(new IllegalArgumentException((new StringBuilder("Invalid injectee with required type of ")).append(injectee.getRequiredType()).append(" passed to getInjecteeDescriptor").toString()));
/* 549*/        if(javax/inject/Provider.equals(flag1) || org/glassfish/hk2/api/IterableProvider.equals(flag1))
                {
/* 550*/            flag1 = org/glassfish/hk2/api/IterableProvider.equals(flag1);
/* 552*/            IterableProviderImpl iterableproviderimpl = new IterableProviderImpl(this, ReflectionHelper.getFirstTypeArgument(flag), injectee.getRequiredQualifiers(), injectee.getUnqualified(), injectee, flag1);
/* 559*/            return new ConstantActiveDescriptor(iterableproviderimpl, this);
                }
/* 562*/        if(org/glassfish/hk2/api/messaging/Topic.equals(flag1))
                {
/* 563*/            TopicImpl topicimpl = new TopicImpl(this, ReflectionHelper.getFirstTypeArgument(flag), injectee.getRequiredQualifiers());
/* 567*/            return new ConstantActiveDescriptor(topicimpl, this);
                } else
                {
                    Set set;
/* 570*/            String s = ReflectionHelper.getNameFromAllQualifiers(set = injectee.getRequiredQualifiers(), injectee.getParent());
/* 573*/            Annotation aannotation[] = (Annotation[])set.toArray(new Annotation[set.size()]);
/* 575*/            return internalGetDescriptor(injectee, flag, s, injectee.getUnqualified(), false, aannotation);
                }
            }

            public ActiveDescriptor getInjecteeDescriptor(Injectee injectee)
                throws MultiException
            {
/* 584*/        return internalGetInjecteeDescriptor(injectee, true);
            }

            public ServiceHandle getServiceHandle(ActiveDescriptor activedescriptor, Injectee injectee)
                throws MultiException
            {
/* 594*/        if(activedescriptor != null)
                {
/* 595*/            if(!(activedescriptor instanceof SystemDescriptor) && !(activedescriptor instanceof ConstantActiveDescriptor))
/* 597*/                throw new IllegalArgumentException((new StringBuilder("The descriptor passed to getServiceHandle must have been bound into a ServiceLocator.  The descriptor is of type ")).append(activedescriptor.getClass().getName()).toString());
                    Long long1;
/* 601*/            if((long1 = activedescriptor.getLocatorId()) == null)
/* 603*/                throw new IllegalArgumentException("The descriptor passed to getServiceHandle is not associated with any ServiceLocator");
/* 606*/            if(long1.longValue() != id)
/* 607*/                if(parent != null)
/* 608*/                    return parent.getServiceHandle(activedescriptor, injectee);
/* 611*/                else
/* 611*/                    throw new IllegalArgumentException((new StringBuilder("The descriptor passed to getServiceHandle is not associated with this ServiceLocator (id=")).append(id).append(").  It is associated ServiceLocator id=").append(long1).toString());
/* 615*/            long1 = activedescriptor.getServiceId();
/* 616*/            if((activedescriptor instanceof SystemDescriptor) && long1 == null)
/* 617*/                throw new IllegalArgumentException((new StringBuilder("The descriptor passed to getServiceHandle was never added to this ServiceLocator (id=")).append(id).append(")").toString());
                }
/* 622*/        return getServiceHandleImpl(activedescriptor, injectee);
            }

            private ServiceHandleImpl getServiceHandleImpl(ActiveDescriptor activedescriptor, Injectee injectee)
                throws MultiException
            {
/* 628*/        if(activedescriptor == null)
                {
/* 628*/            throw new IllegalArgumentException();
                } else
                {
/* 629*/            checkState();
/* 631*/            return new ServiceHandleImpl(this, activedescriptor, injectee);
                }
            }

            public ServiceHandle getServiceHandle(ActiveDescriptor activedescriptor)
                throws MultiException
            {
/* 640*/        return getServiceHandle(activedescriptor, ((Injectee) (null)));
            }

            private ServiceHandleImpl internalGetServiceHandle(ActiveDescriptor activedescriptor, Type type, Injectee injectee)
            {
/* 647*/        if(activedescriptor == null)
/* 647*/            throw new IllegalArgumentException();
/* 648*/        checkState();
/* 650*/        if(type == null)
                {
/* 651*/            return getServiceHandleImpl(activedescriptor, null);
                } else
                {
/* 654*/            type = ((Type) (injectee == null ? ((Type) (new InjecteeImpl(type))) : ((Type) (injectee))));
/* 655*/            return getServiceHandleImpl(activedescriptor, type);
                }
            }

            /**
             * @deprecated Method getService is deprecated
             */

            public Object getService(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
                throws MultiException
            {
/* 664*/        return getService(activedescriptor, servicehandle, ((Injectee) (null)));
            }

            public Object getService(ActiveDescriptor activedescriptor, ServiceHandle servicehandle, Injectee injectee)
                throws MultiException
            {
                Object obj;
                ServiceHandleImpl servicehandleimpl1;
/* 672*/        checkState();
/* 674*/        Class class1 = ReflectionHelper.getRawClass(((Type) (obj = injectee != null ? ((Object) (injectee.getRequiredType())) : null)));
/* 677*/        if(servicehandle == null)
                {
/* 678*/            ServiceHandleImpl servicehandleimpl = new ServiceHandleImpl(this, activedescriptor, injectee);
/* 679*/            return Utilities.createService(activedescriptor, injectee, this, servicehandleimpl, class1);
                }
/* 682*/        servicehandleimpl1 = (ServiceHandleImpl)servicehandle;
/* 684*/        obj = internalGetServiceHandle(activedescriptor, ((Type) (obj)), injectee);
/* 686*/        if(org/glassfish/hk2/api/PerLookup.equals(activedescriptor.getScopeAnnotation()))
/* 687*/            servicehandleimpl1.addSubHandle(((ServiceHandleImpl) (obj)));
/* 690*/        servicehandleimpl1.pushInjectee(injectee);
/* 692*/        activedescriptor = ((ActiveDescriptor) (((ServiceHandleImpl) (obj)).getService(servicehandle)));
/* 695*/        servicehandleimpl1.popInjectee();
/* 695*/        return activedescriptor;
/* 695*/        activedescriptor;
/* 695*/        servicehandleimpl1.popInjectee();
/* 695*/        throw activedescriptor;
            }

            public transient Object getService(Class class1, Annotation aannotation[])
                throws MultiException
            {
/* 704*/        return internalGetService(class1, null, null, aannotation);
            }

            public transient Object getService(Type type, Annotation aannotation[])
                throws MultiException
            {
/* 712*/        return internalGetService(type, null, null, aannotation);
            }

            public transient Object getService(Class class1, String s, Annotation aannotation[])
                throws MultiException
            {
/* 721*/        return internalGetService(class1, s, null, aannotation);
            }

            public transient Object getService(Type type, String s, Annotation aannotation[])
                throws MultiException
            {
/* 730*/        return internalGetService(type, s, null, aannotation);
            }

            private transient Object internalGetService(Type type, String s, Unqualified unqualified, Annotation aannotation[])
            {
/* 735*/        checkState();
                Object obj;
/* 737*/        if((obj = ReflectionHelper.getRawClass(type)) != null && (javax/inject/Provider.equals(obj) || org/glassfish/hk2/api/IterableProvider.equals(obj)))
                {
/* 740*/            s = org/glassfish/hk2/api/IterableProvider.equals(obj);
/* 742*/            type = ReflectionHelper.getFirstTypeArgument(type);
/* 743*/            obj = new HashSet();
/* 744*/            int i = (aannotation = aannotation).length;
/* 744*/            for(int j = 0; j < i; j++)
                    {
/* 744*/                Annotation annotation = aannotation[j];
/* 745*/                ((HashSet) (obj)).add(annotation);
                    }

/* 748*/            (aannotation = new InjecteeImpl(type)).setRequiredQualifiers(((Set) (obj)));
/* 750*/            aannotation.setUnqualified(unqualified);
                    IterableProviderImpl iterableproviderimpl;
/* 752*/            return iterableproviderimpl = new IterableProviderImpl(this, type, ((Set) (obj)), unqualified, aannotation, s);
                }
/* 762*/        if((s = internalGetDescriptor(null, type, s, unqualified, false, aannotation)) == null)
/* 763*/            return null;
/* 765*/        else
/* 765*/            return type = ((Type) (Utilities.createService(s, null, this, null, ((Class) (obj)))));
            }

            transient Object getUnqualifiedService(Type type, Unqualified unqualified, boolean flag, Annotation aannotation[])
                throws MultiException
            {
/* 772*/        return internalGetService(type, null, unqualified, aannotation);
            }

            private transient List protectedGetAllServices(final Type contractOrImpl, final Annotation qualifiers[])
            {
/* 777*/        return (List)AccessController.doPrivileged(new PrivilegedAction() {

                    public List run()
                    {
/* 781*/                return getAllServices(contractOrImpl, qualifiers);
                    }

                    public volatile Object run()
                    {
/* 777*/                return run();
                    }

                    final Type val$contractOrImpl;
                    final Annotation val$qualifiers[];
                    final ServiceLocatorImpl this$0;

                    
                    {
/* 777*/                this$0 = ServiceLocatorImpl.this;
/* 777*/                contractOrImpl = type;
/* 777*/                qualifiers = aannotation;
/* 777*/                super();
                    }
        });
            }

            public transient List getAllServices(Class class1, Annotation aannotation[])
                throws MultiException
            {
/* 789*/        return getAllServices(((Type) (class1)), aannotation);
            }

            public transient List getAllServices(Type type, Annotation aannotation[])
                throws MultiException
            {
/* 799*/        checkState();
/* 801*/        return type = internalGetAllServiceHandles(type, null, false, false, aannotation);
            }

            public transient List getAllServices(Annotation annotation, Annotation aannotation[])
                throws MultiException
            {
/* 819*/        checkState();
/* 821*/        annotation = getAllServiceHandles(annotation, aannotation);
/* 823*/        aannotation = new LinkedList();
                ServiceHandle servicehandle;
/* 824*/        for(annotation = annotation.iterator(); annotation.hasNext(); aannotation.add(servicehandle.getService()))
/* 824*/            servicehandle = (ServiceHandle)annotation.next();

/* 828*/        return aannotation;
            }

            public List getAllServices(Filter filter)
                throws MultiException
            {
/* 837*/        checkState();
/* 839*/        filter = getAllServiceHandles(filter);
/* 841*/        LinkedList linkedlist = new LinkedList();
                ServiceHandle servicehandle;
/* 842*/        for(filter = filter.iterator(); filter.hasNext(); linkedlist.add(servicehandle.getService()))
/* 842*/            servicehandle = (ServiceHandle)filter.next();

/* 846*/        return linkedlist;
            }

            public String getName()
            {
/* 854*/        return locatorName;
            }

            public ServiceLocatorState getState()
            {
/* 859*/        rLock.lock();
/* 861*/        ServiceLocatorState servicelocatorstate = state;
/* 863*/        rLock.unlock();
/* 863*/        return servicelocatorstate;
                Exception exception;
/* 863*/        exception;
/* 863*/        rLock.unlock();
/* 863*/        throw exception;
            }

            public void shutdown()
            {
/* 874*/        wLock.lock();
/* 876*/        if(state.equals(ServiceLocatorState.SHUTDOWN))
                {
/* 883*/            wLock.unlock();
/* 883*/            return;
                }
/* 878*/        if(parent != null)
/* 879*/            parent.removeChild(this);
/* 883*/        wLock.unlock();
/* 884*/        break MISSING_BLOCK_LABEL_63;
                Exception exception;
/* 883*/        exception;
/* 883*/        wLock.unlock();
/* 883*/        throw exception;
                Object obj;
/* 887*/        obj = ((List) (obj = getAllServiceHandles(new IndexedFilter() {

                    public boolean matches(Descriptor descriptor)
                    {
/* 891*/                return descriptor.getLocatorId().equals(Long.valueOf(id));
                    }

                    public String getAdvertisedContract()
                    {
/* 896*/                return org/glassfish/hk2/api/Context.getName();
                    }

                    public String getName()
                    {
/* 901*/                return null;
                    }

                    final ServiceLocatorImpl this$0;

                    
                    {
/* 887*/                this$0 = ServiceLocatorImpl.this;
/* 887*/                super();
                    }
        }))).iterator();
/* 906*/        do
                {
/* 906*/            if(!((Iterator) (obj)).hasNext())
/* 906*/                break;
                    Object obj1;
/* 906*/            if(((ServiceHandle) (obj1 = (ServiceHandle)((Iterator) (obj)).next())).isActive())
/* 908*/                ((Context) (obj1 = (Context)((ServiceHandle) (obj1)).getService())).shutdown();
                } while(true);
/* 913*/        singletonContext.shutdown();
/* 914*/        wLock.lock();
/* 917*/        state = ServiceLocatorState.SHUTDOWN;
/* 919*/        allDescriptors.clear();
/* 920*/        descriptorsByAdvertisedContract.clear();
/* 921*/        descriptorsByName.clear();
/* 922*/        allResolvers.clear();
/* 923*/        injecteeToResolverCache.clear();
/* 924*/        allValidators.clear();
/* 925*/        errorHandlers.clear();
/* 926*/        igdCache.clear();
/* 927*/        igashCache.clear();
/* 928*/        classReflectionHelper.dispose();
/* 929*/        contextCache.clear();
/* 930*/        perLocatorUtilities.shutdown();
/* 932*/        synchronized(children)
                {
/* 933*/            children.clear();
                }
/* 936*/        Logger.getLogger().debug((new StringBuilder("Shutdown ServiceLocator ")).append(this).toString());
/* 938*/        wLock.unlock();
/* 939*/        break MISSING_BLOCK_LABEL_311;
/* 938*/        map;
/* 938*/        wLock.unlock();
/* 938*/        throw map;
/* 941*/        ServiceLocatorFactory.getInstance().destroy(this);
/* 943*/        Logger.getLogger().debug((new StringBuilder("ServiceLocator ")).append(this).append(" has been shutdown").toString());
/* 944*/        return;
            }

            public Object create(Class class1)
            {
/* 951*/        return create(class1, null);
            }

            public Object create(Class class1, String s)
            {
/* 959*/        checkState();
/* 961*/        return Utilities.justCreate(class1, this, s);
            }

            public void inject(Object obj)
            {
/* 969*/        inject(obj, null);
            }

            public void inject(Object obj, String s)
            {
/* 977*/        checkState();
/* 979*/        Utilities.justInject(obj, this, s);
            }

            public void postConstruct(Object obj)
            {
/* 987*/        postConstruct(obj, null);
            }

            public void postConstruct(Object obj, String s)
            {
/* 996*/        checkState();
/* 998*/        if(obj == null)
/* 999*/            throw new IllegalArgumentException();
/*1002*/        if((s == null || s.equals("default")) && (obj instanceof PostConstruct))
                {
/*1004*/            ((PostConstruct)obj).postConstruct();
/*1004*/            return;
                } else
                {
/*1007*/            Utilities.justPostConstruct(obj, this, s);
/*1010*/            return;
                }
            }

            public void preDestroy(Object obj)
            {
/*1017*/        preDestroy(obj, null);
            }

            public void preDestroy(Object obj, String s)
            {
/*1025*/        checkState();
/*1027*/        if(obj == null)
/*1028*/            throw new IllegalArgumentException();
/*1031*/        if((s == null || s.equals("default")) && (obj instanceof PreDestroy))
                {
/*1033*/            ((PreDestroy)obj).preDestroy();
/*1033*/            return;
                } else
                {
/*1036*/            Utilities.justPreDestroy(obj, this, s);
/*1038*/            return;
                }
            }

            public Object createAndInitialize(Class class1)
            {
/*1045*/        return createAndInitialize(class1, null);
            }

            public Object createAndInitialize(Class class1, String s)
            {
/*1053*/        class1 = ((Class) (create(class1, s)));
/*1054*/        inject(class1, s);
/*1055*/        postConstruct(class1, s);
/*1056*/        return class1;
            }

            private static transient String getName(String s, Annotation aannotation[])
            {
/*1060*/        if(s != null)
/*1060*/            return s;
/*1062*/        aannotation = (s = aannotation).length;
/*1062*/        for(int i = 0; i < aannotation; i++)
                {
                    Named named;
/*1062*/            if(((named = s[i]) instanceof Named) && (named = (Named)named).value() != null && !named.value().isEmpty())
/*1066*/                return named.value();
                }

/*1072*/        return null;
            }

            private IgdValue igdCacheCompute(IgdCacheKey igdcachekey)
            {
/*1149*/        Object obj = getDescriptors(igdcachekey.filter, igdcachekey.onBehalfOf, true, false, true);
/*1150*/        if(!((NarrowResults) (obj = (igdcachekey = narrow(this, ((List) (obj)), igdcachekey.contractOrImpl, igdcachekey.name, igdcachekey.onBehalfOf, true, true, null, igdcachekey.filter, igdcachekey.qualifiers)).getTimelessResults())).getErrors().isEmpty())
                {
/*1163*/            Utilities.handleErrors(((NarrowResults) (obj)), new LinkedList(errorHandlers));
/*1164*/            throw new ComputationErrorException(new IgdValue(((NarrowResults) (obj)), igdcachekey));
                } else
                {
/*1167*/            return new IgdValue(((NarrowResults) (obj)), igdcachekey);
                }
            }

            private Unqualified getEffectiveUnqualified(Unqualified unqualified, boolean flag, Annotation aannotation[])
            {
/*1171*/        if(unqualified != null)
/*1171*/            return unqualified;
/*1172*/        if(aannotation.length > 0)
/*1172*/            return null;
/*1173*/        if(flag)
/*1173*/            return null;
/*1176*/        else
/*1176*/            return defaultUnqualified;
            }

            private transient ActiveDescriptor internalGetDescriptor(Injectee injectee, Type type, String s, Unqualified unqualified, boolean flag, Annotation aannotation[])
                throws MultiException
            {
                LinkedList linkedlist;
                UnqualifiedIndexedFilter unqualifiedindexedfilter;
/*1185*/        if(type == null)
/*1185*/            throw new IllegalArgumentException();
                Class class1;
/*1187*/        if((class1 = ReflectionHelper.getRawClass(type)) == null)
/*1188*/            return null;
/*1190*/        Utilities.checkLookupType(class1);
/*1192*/        class1 = Utilities.translatePrimitiveType(class1);
/*1194*/        s = getName(s, aannotation);
/*1197*/        linkedlist = null;
/*1201*/        unqualified = getEffectiveUnqualified(unqualified, flag, aannotation);
/*1203*/        flag = new CacheKey(type, s, unqualified, aannotation);
/*1204*/        unqualifiedindexedfilter = new UnqualifiedIndexedFilter(class1.getName(), s, unqualified);
/*1205*/        flag = new IgdCacheKey(flag, s, injectee, type, class1, aannotation, unqualifiedindexedfilter);
/*1213*/        rLock.lock();
                boolean flag1;
/*1215*/        if(!(flag1 = ((IgdValue) (flag = (IgdValue)igdCache.compute(flag))).freshnessKeeper.compareAndSet(1, 2)))
                {
/*1218*/            s = (flag = narrow(this, null, type, s, injectee, true, true, ((IgdValue) (flag)).results, unqualifiedindexedfilter, aannotation)).getTimelessResults();
                } else
                {
/*1230*/            s = ((IgdValue) (flag)).results;
/*1231*/            flag = ((IgdValue) (flag)).immediate;
                }
/*1234*/        if(!s.getErrors().isEmpty())
/*1235*/            linkedlist = new LinkedList(errorHandlers);
/*1238*/        rLock.unlock();
/*1239*/        break MISSING_BLOCK_LABEL_237;
/*1238*/        injectee;
/*1238*/        rLock.unlock();
/*1238*/        throw injectee;
/*1241*/        if(linkedlist != null)
/*1243*/            Utilities.handleErrors(s, linkedlist);
/*1247*/        if((flag = flag.getImmediateResults().isEmpty() ? null : ((boolean) ((ActiveDescriptor)flag.getImmediateResults().get(0)))) == null)
                {
                    Object obj1;
/*1253*/            if(injectee == null)
                    {
/*1254*/                injectee = new HashSet();
/*1255*/                if(aannotation != null && aannotation.length > 0)
                        {
/*1256*/                    flag = (s = aannotation).length;
/*1256*/                    for(aannotation = 0; aannotation < flag; aannotation++)
                            {
                                Object obj;
/*1256*/                        if((obj = s[aannotation]) != null)
/*1258*/                            injectee.add(obj);
                            }

                        }
/*1262*/                (s = new InjecteeImpl(type)).setRequiredQualifiers(injectee);
/*1264*/                s.setUnqualified(unqualified);
/*1265*/                obj1 = s;
                    } else
                    {
/*1267*/                obj1 = injectee;
                    }
/*1269*/            flag = secondChanceResolve(((Injectee) (obj1)));
                }
/*1272*/        return flag;
            }

            public transient ServiceHandle getServiceHandle(Class class1, Annotation aannotation[])
                throws MultiException
            {
/*1278*/        return getServiceHandle(((Type) (class1)), aannotation);
            }

            public transient ServiceHandle getServiceHandle(Type type, Annotation aannotation[])
                throws MultiException
            {
/*1287*/        checkState();
/*1289*/        if((aannotation = internalGetDescriptor(null, type, null, null, false, aannotation)) == null)
/*1290*/            return null;
/*1292*/        else
/*1292*/            return getServiceHandle(((ActiveDescriptor) (aannotation)), ((Injectee) (new InjecteeImpl(type))));
            }

            transient ServiceHandle getUnqualifiedServiceHandle(Type type, Unqualified unqualified, boolean flag, Annotation aannotation[])
                throws MultiException
            {
/*1297*/        checkState();
/*1299*/        if((unqualified = internalGetDescriptor(null, type, null, unqualified, flag, aannotation)) == null)
/*1300*/            return null;
/*1302*/        else
/*1302*/            return getServiceHandle(unqualified, new InjecteeImpl(type));
            }

            private transient List protectedGetAllServiceHandles(final Type contractOrImpl, final Annotation qualifiers[])
            {
/*1307*/        return (List)AccessController.doPrivileged(new PrivilegedAction() {

                    public List run()
                    {
/*1311*/                return getAllServiceHandles(contractOrImpl, qualifiers);
                    }

                    public volatile Object run()
                    {
/*1307*/                return run();
                    }

                    final Type val$contractOrImpl;
                    final Annotation val$qualifiers[];
                    final ServiceLocatorImpl this$0;

                    
                    {
/*1307*/                this$0 = ServiceLocatorImpl.this;
/*1307*/                contractOrImpl = type;
/*1307*/                qualifiers = aannotation;
/*1307*/                super();
                    }
        });
            }

            public transient List getAllServiceHandles(Class class1, Annotation aannotation[])
                throws MultiException
            {
/*1321*/        return (List)ReflectionHelper.cast(getAllServiceHandles(((Type) (class1)), aannotation));
            }

            public transient List getAllServiceHandles(Type type, Annotation aannotation[])
                throws MultiException
            {
/*1332*/        return internalGetAllServiceHandles(type, null, true, false, aannotation);
            }

            transient List getAllUnqualifiedServiceHandles(Type type, Unqualified unqualified, boolean flag, Annotation aannotation[])
                throws MultiException
            {
/*1340*/        return internalGetAllServiceHandles(type, unqualified, true, flag, aannotation);
            }

            private transient List internalGetAllServiceHandles(Type type, Unqualified unqualified, boolean flag, boolean flag1, Annotation aannotation[])
                throws MultiException
            {
                Class class1;
                LinkedList linkedlist1;
/*1378*/        if(type == null)
/*1378*/            throw new IllegalArgumentException();
/*1379*/        checkState();
/*1381*/        if((class1 = ReflectionHelper.getRawClass(type)) == null)
/*1383*/            throw new MultiException(new IllegalArgumentException((new StringBuilder("Type must be a class or parameterized type, it was ")).append(type).toString()));
/*1386*/        String s = class1.getName();
/*1389*/        linkedlist1 = null;
/*1393*/        unqualified = getEffectiveUnqualified(unqualified, flag1, aannotation);
/*1395*/        flag1 = new CacheKey(type, null, unqualified, aannotation);
/*1396*/        unqualified = new UnqualifiedIndexedFilter(s, null, unqualified);
/*1397*/        flag1 = new IgdCacheKey(flag1, s, null, type, class1, aannotation, unqualified);
/*1405*/        rLock.lock();
                IgdValue igdvalue;
/*1407*/        if(!(flag1 = (igdvalue = (IgdValue)igashCache.compute(flag1)).freshnessKeeper.compareAndSet(1, 2)))
                {
/*1410*/            unqualified = (flag1 = narrow(this, null, type, null, null, false, true, igdvalue.results, unqualified, aannotation)).getTimelessResults();
                } else
                {
/*1423*/            unqualified = igdvalue.results;
/*1424*/            flag1 = igdvalue.immediate;
                }
/*1427*/        if(!unqualified.getErrors().isEmpty())
/*1428*/            linkedlist1 = new LinkedList(errorHandlers);
/*1432*/        rLock.unlock();
/*1433*/        break MISSING_BLOCK_LABEL_249;
/*1432*/        unqualified;
/*1432*/        rLock.unlock();
/*1432*/        throw unqualified;
/*1435*/        if(linkedlist1 != null)
/*1437*/            Utilities.handleErrors(unqualified, linkedlist1);
/*1440*/        LinkedList linkedlist = new LinkedList();
/*1441*/        for(flag1 = flag1.getImmediateResults().iterator(); flag1.hasNext();)
                {
/*1441*/            unqualified = (ActiveDescriptor)flag1.next();
/*1442*/            if(flag)
                    {
/*1443*/                linkedlist.add(internalGetServiceHandle(unqualified, type, null));
                    } else
                    {
/*1446*/                unqualified = ((Unqualified) (Utilities.createService(unqualified, null, this, null, class1)));
/*1448*/                linkedlist.add(unqualified);
                    }
                }

/*1452*/        return linkedlist;
            }

            public transient ServiceHandle getServiceHandle(Class class1, String s, Annotation aannotation[])
                throws MultiException
            {
/*1458*/        return getServiceHandle(((Type) (class1)), s, aannotation);
            }

            public transient ServiceHandle getServiceHandle(Type type, String s, Annotation aannotation[])
                throws MultiException
            {
/*1467*/        checkState();
/*1469*/        if((s = internalGetDescriptor(null, type, s, null, false, aannotation)) == null)
/*1470*/            return null;
/*1472*/        else
/*1472*/            return internalGetServiceHandle(s, type, null);
            }

            public List getAllServiceHandles(Filter filter)
                throws MultiException
            {
/*1481*/        checkState();
/*1484*/        Object obj = null;
/*1485*/        List list = (List)ReflectionHelper.cast(getDescriptors(filter));
/*1486*/        if(!(filter = (filter = narrow(this, list, null, null, null, false, false, null, filter, new Annotation[0])).getTimelessResults()).getErrors().isEmpty())
/*1497*/            obj = new LinkedList(errorHandlers);
/*1500*/        if(obj != null)
/*1502*/            Utilities.handleErrors(filter, ((LinkedList) (obj)));
/*1505*/        obj = new TreeSet(HANDLE_COMPARATOR);
                ActiveDescriptor activedescriptor;
/*1506*/        for(filter = filter.getResults().iterator(); filter.hasNext(); ((SortedSet) (obj)).add(getServiceHandle(activedescriptor)))
/*1506*/            activedescriptor = (ActiveDescriptor)filter.next();

/*1510*/        return new LinkedList(((Collection) (obj)));
            }

            public transient List getAllServiceHandles(Annotation annotation, Annotation aannotation[])
                throws MultiException
            {
/*1519*/        checkState();
/*1521*/        if(annotation == null)
/*1521*/            throw new IllegalArgumentException("qualifier is null");
                final LinkedHashSet allQualifiers;
/*1523*/        (allQualifiers = new LinkedHashSet()).add(annotation.annotationType().getName());
/*1526*/        aannotation = (annotation = aannotation).length;
/*1526*/        for(int i = 0; i < aannotation; i++)
                {
                    String s;
/*1526*/            s = (s = annotation[i]).annotationType().getName();
/*1528*/            if(allQualifiers.contains(s))
/*1529*/                throw new IllegalArgumentException((new StringBuilder("Multiple qualifiers with name ")).append(s).toString());
/*1532*/            allQualifiers.add(s);
                }

/*1535*/        return getAllServiceHandles(new Filter() {

                    public boolean matches(Descriptor descriptor)
                    {
/*1539*/                return descriptor.getQualifiers().containsAll(allQualifiers);
                    }

                    final Set val$allQualifiers;
                    final ServiceLocatorImpl this$0;

                    
                    {
/*1535*/                this$0 = ServiceLocatorImpl.this;
/*1535*/                allQualifiers = set;
/*1535*/                super();
                    }
        });
            }

            List getInterceptionServices()
            {
/*1546*/        if(!hasInterceptionServices)
/*1546*/            return null;
/*1548*/        rLock.lock();
/*1550*/        LinkedList linkedlist = new LinkedList(interceptionServices);
/*1553*/        rLock.unlock();
/*1553*/        return linkedlist;
                Exception exception;
/*1553*/        exception;
/*1553*/        rLock.unlock();
/*1553*/        throw exception;
            }

            private CheckConfigurationData checkConfiguration(DynamicConfigurationImpl dynamicconfigurationimpl)
            {
/*1564*/        LinkedList linkedlist = new LinkedList();
/*1565*/        boolean flag = false;
/*1566*/        boolean flag1 = false;
/*1567*/        boolean flag2 = false;
/*1568*/        boolean flag3 = false;
/*1569*/        boolean flag4 = false;
/*1570*/        boolean flag5 = false;
/*1571*/        HashSet hashset = new HashSet();
/*1573*/        for(Iterator iterator = dynamicconfigurationimpl.getUnbindFilters().iterator(); iterator.hasNext();)
                {
/*1573*/            Filter filter = (Filter)iterator.next();
                    Object obj;
/*1574*/            obj = ((List) (obj = getDescriptors(filter, null, false, false, true))).iterator();
/*1576*/            while(((Iterator) (obj)).hasNext()) 
                    {
/*1576*/                SystemDescriptor systemdescriptor1 = (SystemDescriptor)((Iterator) (obj)).next();
/*1577*/                hashset.addAll(getAllContracts(systemdescriptor1));
/*1579*/                if(!linkedlist.contains(systemdescriptor1))
                        {
/*1581*/                    for(Iterator iterator2 = getAllValidators().iterator(); iterator2.hasNext();)
                            {
/*1581*/                        ValidationService validationservice = (ValidationService)iterator2.next();
/*1582*/                        if(!callValidate(validationservice, new ValidationInformationImpl(Operation.UNBIND, systemdescriptor1)))
/*1584*/                            throw new MultiException(new IllegalArgumentException((new StringBuilder("Descriptor ")).append(systemdescriptor1).append(" did not pass the UNBIND validation").toString()));
                            }

/*1589*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/InstanceLifecycleListener.getName()))
/*1590*/                        flag = true;
/*1592*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/InjectionResolver.getName()))
/*1593*/                        flag1 = true;
/*1595*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/ErrorService.getName()))
/*1596*/                        flag2 = true;
/*1598*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/ClassAnalyzer.getName()))
/*1599*/                        flag3 = true;
/*1601*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/DynamicConfigurationListener.getName()))
/*1602*/                        flag4 = true;
/*1604*/                    if(systemdescriptor1.getAdvertisedContracts().contains(org/glassfish/hk2/api/InterceptionService.getName()))
/*1605*/                        flag5 = true;
/*1608*/                    linkedlist.add(systemdescriptor1);
                        }
                    }
                }

/*1612*/        Iterator iterator1 = dynamicconfigurationimpl.getAllDescriptors().iterator();
                SystemDescriptor systemdescriptor;
/*1612*/label0:
/*1612*/        do
/*1612*/            if(iterator1.hasNext())
                    {
/*1612*/                systemdescriptor = (SystemDescriptor)iterator1.next();
/*1613*/                hashset.addAll(getAllContracts(systemdescriptor));
/*1615*/                boolean flag6 = false;
/*1616*/                if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ValidationService.getName()) || systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ErrorService.getName()) || systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/InterceptionService.getName()) || systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/InstanceLifecycleListener.getName()))
                        {
/*1621*/                    reifyDescriptor(systemdescriptor);
/*1623*/                    flag6 = true;
/*1625*/                    if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ErrorService.getName()))
/*1626*/                        flag2 = true;
/*1628*/                    if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/InstanceLifecycleListener.getName()))
/*1629*/                        flag = true;
/*1631*/                    if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/InterceptionService.getName()))
/*1632*/                        flag5 = true;
                        }
/*1636*/                if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/InjectionResolver.getName()))
                        {
/*1638*/                    reifyDescriptor(systemdescriptor);
/*1640*/                    flag6 = true;
/*1642*/                    if(Utilities.getInjectionResolverType(systemdescriptor) == null)
/*1643*/                        throw new MultiException(new IllegalArgumentException("An implementation of InjectionResolver must be a parameterized type and the actual type must be an annotation"));
/*1648*/                    flag1 = true;
                        }
/*1651*/                if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/DynamicConfigurationListener.getName()))
                        {
/*1653*/                    reifyDescriptor(systemdescriptor);
/*1655*/                    flag6 = true;
/*1657*/                    flag4 = true;
                        }
/*1660*/                if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/Context.getName()))
/*1662*/                    flag6 = true;
/*1665*/                if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ClassAnalyzer.getName()))
/*1666*/                    flag3 = true;
                        Object obj1;
/*1669*/                if(flag6 && !((String) (obj1 = systemdescriptor.getScope() != null ? ((Object) (systemdescriptor.getScope())) : ((Object) (org/glassfish/hk2/api/PerLookup.getName())))).equals(javax/inject/Singleton.getName()))
/*1673*/                    throw new MultiException(new IllegalArgumentException((new StringBuilder("The implementation class ")).append(systemdescriptor.getImplementation()).append(" must be in the Singleton scope").toString()));
/*1678*/                obj1 = getAllValidators().iterator();
                        ValidationService validationservice1;
/*1678*/                do
                        {
/*1678*/                    if(!((Iterator) (obj1)).hasNext())
/*1678*/                        continue label0;
                            Validator validator;
/*1678*/                    if((validator = (validationservice1 = (ValidationService)((Iterator) (obj1)).next()).getValidator()) == null)
/*1681*/                        throw new MultiException(new IllegalArgumentException((new StringBuilder("Validator was null from validation service")).append(validationservice1).toString()));
                        } while(callValidate(validationservice1, new ValidationInformationImpl(Operation.BIND, systemdescriptor)));
/*1686*/                break;
                    } else
                    {
/*1691*/                return new CheckConfigurationData(linkedlist, flag, flag1, flag2, flag3, flag4, hashset, flag5);
                    }
/*1684*/        while(true);
/*1686*/        throw new MultiException(new IllegalArgumentException((new StringBuilder("Descriptor ")).append(systemdescriptor).append(" did not pass the BIND validation").toString()));
            }

            private static List getAllContracts(ActiveDescriptor activedescriptor)
            {
                LinkedList linkedlist;
/*1702*/        (linkedlist = new LinkedList(activedescriptor.getAdvertisedContracts())).addAll(activedescriptor.getQualifiers());
/*1704*/        activedescriptor = activedescriptor.getScope() != null ? ((ActiveDescriptor) (activedescriptor.getScope())) : ((ActiveDescriptor) (org/glassfish/hk2/api/PerLookup.getName()));
/*1705*/        linkedlist.add(activedescriptor);
/*1707*/        return linkedlist;
            }

            private void removeConfigurationInternal(List list)
            {
/*1712*/        Iterator iterator = list.iterator();
/*1712*/        do
                {
/*1712*/            if(!iterator.hasNext())
/*1712*/                break;
/*1712*/            SystemDescriptor systemdescriptor = (SystemDescriptor)iterator.next();
/*1713*/            if(BIND_TRACING_PATTERN != null && doTrace(systemdescriptor))
                    {
/*1714*/                Logger.getLogger().debug((new StringBuilder("HK2 Bind Tracing: Removing Descriptor ")).append(systemdescriptor).toString());
/*1715*/                if(BIND_TRACING_STACKS)
/*1716*/                    Logger.getLogger().debug("ServiceLocatorImpl", "removeConfigurationInternal", new Throwable());
                    }
/*1720*/            allDescriptors.removeDescriptor(systemdescriptor);
/*1722*/            Object obj = getAllContracts(systemdescriptor).iterator();
/*1722*/            do
                    {
/*1722*/                if(!((Iterator) (obj)).hasNext())
/*1722*/                    break;
/*1722*/                String s = (String)((Iterator) (obj)).next();
                        IndexedListData indexedlistdata1;
/*1723*/                if((indexedlistdata1 = (IndexedListData)descriptorsByAdvertisedContract.get(s)) != null)
                        {
/*1726*/                    indexedlistdata1.removeDescriptor(systemdescriptor);
/*1727*/                    if(indexedlistdata1.isEmpty())
/*1727*/                        descriptorsByAdvertisedContract.remove(s);
                        }
                    } while(true);
                    IndexedListData indexedlistdata;
/*1730*/            if((obj = systemdescriptor.getName()) != null && (indexedlistdata = (IndexedListData)descriptorsByName.get(obj)) != null)
                    {
/*1734*/                indexedlistdata.removeDescriptor(systemdescriptor);
/*1735*/                if(indexedlistdata.isEmpty())
/*1736*/                    descriptorsByName.remove(obj);
                    }
/*1741*/            if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ValidationService.getName()))
                    {
                        ServiceHandle servicehandle;
/*1742*/                ValidationService validationservice = (ValidationService)(servicehandle = getServiceHandle(systemdescriptor)).getService();
/*1744*/                allValidators.remove(validationservice);
                    }
/*1747*/            if(systemdescriptor.isReified())
                    {
/*1748*/                Iterator iterator2 = systemdescriptor.getInjectees().iterator();
/*1748*/                do
                        {
/*1748*/                    if(!iterator2.hasNext())
/*1748*/                        break;
                            Injectee injectee;
/*1748*/                    if((injectee = (Injectee)iterator2.next()) instanceof SystemInjecteeImpl)
/*1750*/                        injecteeToResolverCache.remove((SystemInjecteeImpl)injectee);
                        } while(true);
/*1754*/                classReflectionHelper.clean(systemdescriptor.getImplementationClass());
                    }
                } while(true);
/*1758*/        boolean flag = false;
                SystemDescriptor systemdescriptor1;
/*1759*/        for(Iterator iterator1 = list.iterator(); iterator1.hasNext(); systemdescriptor1.close())
                {
/*1759*/            systemdescriptor1 = (SystemDescriptor)iterator1.next();
/*1760*/            flag = true;
                }

/*1767*/        if(flag)
/*1768*/            perLocatorUtilities.releaseCaches();
            }

            private static boolean doTrace(ActiveDescriptor activedescriptor)
            {
                StringTokenizer stringtokenizer;
/*1773*/        if(BIND_TRACING_PATTERN == null)
/*1773*/            return false;
/*1774*/        if("*".equals(BIND_TRACING_PATTERN))
/*1774*/            return true;
/*1776*/        if(activedescriptor.getImplementation() == null)
/*1776*/            return true;
/*1778*/        stringtokenizer = new StringTokenizer(BIND_TRACING_PATTERN, "|");
_L2:
                String s;
                Iterator iterator;
/*1779*/        if(!stringtokenizer.hasMoreTokens())
/*1780*/            break MISSING_BLOCK_LABEL_120;
/*1780*/        s = stringtokenizer.nextToken();
/*1782*/        if(activedescriptor.getImplementation().contains(s))
/*1783*/            return true;
/*1786*/        iterator = activedescriptor.getAdvertisedContracts().iterator();
_L4:
/*1786*/        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
                String s1;
/*1786*/        if(!(s1 = (String)iterator.next()).contains(s)) goto _L4; else goto _L3
_L3:
/*1787*/        return true;
/*1791*/        return false;
            }

            private List addConfigurationInternal(DynamicConfigurationImpl dynamicconfigurationimpl)
            {
/*1796*/        LinkedList linkedlist = new LinkedList();
/*1798*/        dynamicconfigurationimpl = dynamicconfigurationimpl.getAllDescriptors().iterator();
/*1798*/        do
                {
/*1798*/            if(!dynamicconfigurationimpl.hasNext())
/*1798*/                break;
/*1798*/            SystemDescriptor systemdescriptor = (SystemDescriptor)dynamicconfigurationimpl.next();
/*1799*/            if(BIND_TRACING_PATTERN != null && doTrace(systemdescriptor))
                    {
/*1800*/                Logger.getLogger().debug((new StringBuilder("HK2 Bind Tracing: Adding Descriptor ")).append(systemdescriptor).toString());
/*1801*/                if(BIND_TRACING_STACKS)
/*1802*/                    Logger.getLogger().debug("ServiceLocatorImpl", "addConfigurationInternal", new Throwable());
                    }
/*1806*/            linkedlist.add(systemdescriptor);
/*1807*/            allDescriptors.addDescriptor(systemdescriptor);
                    Object obj;
                    IndexedListData indexedlistdata1;
/*1809*/            for(obj = ((List) (obj = getAllContracts(systemdescriptor))).iterator(); ((Iterator) (obj)).hasNext(); indexedlistdata1.addDescriptor(systemdescriptor))
                    {
/*1811*/                String s1 = (String)((Iterator) (obj)).next();
/*1812*/                if((indexedlistdata1 = (IndexedListData)descriptorsByAdvertisedContract.get(s1)) == null)
                        {
/*1814*/                    indexedlistdata1 = new IndexedListData();
/*1815*/                    descriptorsByAdvertisedContract.put(s1, indexedlistdata1);
                        }
                    }

/*1821*/            if(systemdescriptor.getName() != null)
                    {
/*1822*/                String s = systemdescriptor.getName();
                        IndexedListData indexedlistdata;
/*1823*/                if((indexedlistdata = (IndexedListData)descriptorsByName.get(s)) == null)
                        {
/*1825*/                    indexedlistdata = new IndexedListData();
/*1826*/                    descriptorsByName.put(s, indexedlistdata);
                        }
/*1829*/                indexedlistdata.addDescriptor(systemdescriptor);
                    }
/*1832*/            if(systemdescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ValidationService.getName()))
                    {
                        ServiceHandle servicehandle;
/*1833*/                ValidationService validationservice = (ValidationService)(servicehandle = getServiceHandle(systemdescriptor)).getService();
/*1835*/                allValidators.add(validationservice);
                    }
                } while(true);
/*1839*/        return linkedlist;
            }

            private void reupInjectionResolvers()
            {
/*1843*/        HashMap hashmap = new HashMap();
/*1846*/        Object obj = BuilderHelper.createContractFilter(org/glassfish/hk2/api/InjectionResolver.getName());
/*1849*/        obj = ((List) (obj = protectedGetDescriptors(((Filter) (obj))))).iterator();
/*1851*/        do
                {
/*1851*/            if(!((Iterator) (obj)).hasNext())
/*1851*/                break;
                    Object obj1;
                    Class class1;
/*1851*/            if((class1 = Utilities.getInjectionResolverType(((ActiveDescriptor) (obj1 = (ActiveDescriptor)((Iterator) (obj)).next())))) != null && !hashmap.containsKey(class1))
                    {
/*1855*/                obj1 = (InjectionResolver)getServiceHandle(((ActiveDescriptor) (obj1))).getService();
/*1858*/                hashmap.put(class1, obj1);
                    }
                } while(true);
/*1862*/        synchronized(allResolvers)
                {
/*1863*/            allResolvers.clear();
/*1864*/            allResolvers.putAll(hashmap);
                }
/*1866*/        injecteeToResolverCache.clear();
            }

            private void reupInterceptionServices()
            {
/*1870*/        List list = protectedGetAllServices(org/glassfish/hk2/api/InterceptionService, new Annotation[0]);
/*1872*/        interceptionServices.clear();
/*1873*/        interceptionServices.addAll(list);
/*1875*/        hasInterceptionServices = !interceptionServices.isEmpty();
            }

            private void reupErrorHandlers()
            {
/*1879*/        List list = protectedGetAllServices(org/glassfish/hk2/api/ErrorService, new Annotation[0]);
/*1881*/        errorHandlers.clear();
/*1882*/        errorHandlers.addAll(list);
            }

            private void reupConfigListeners()
            {
/*1886*/        List list = protectedGetAllServiceHandles(org/glassfish/hk2/api/DynamicConfigurationListener, new Annotation[0]);
/*1888*/        configListeners.clear();
/*1889*/        configListeners.addAll(list);
            }

            private void reupInstanceListenersHandlers(Collection collection)
            {
/*1893*/        List list = protectedGetAllServices(org/glassfish/hk2/api/InstanceLifecycleListener, new Annotation[0]);
                SystemDescriptor systemdescriptor;
/*1895*/        for(collection = collection.iterator(); collection.hasNext(); (systemdescriptor = (SystemDescriptor)collection.next()).reupInstanceListeners(list));
            }

            private void reupClassAnalyzers()
            {
/*1902*/        Object obj = protectedGetAllServiceHandles(org/glassfish/hk2/api/ClassAnalyzer, new Annotation[0]);
/*1904*/        synchronized(classAnalyzerLock)
                {
/*1905*/            classAnalyzers.clear();
/*1907*/            obj = ((List) (obj)).iterator();
/*1907*/            do
                    {
/*1907*/                if(!((Iterator) (obj)).hasNext())
/*1907*/                    break;
                        Object obj2;
                        Object obj3;
/*1907*/                if((obj3 = ((ActiveDescriptor) (obj3 = ((ServiceHandle) (obj2 = (ServiceHandle)((Iterator) (obj)).next())).getActiveDescriptor())).getName()) != null && (obj2 = (ClassAnalyzer)((ServiceHandle) (obj2)).getService()) != null)
/*1915*/                    classAnalyzers.put(obj3, obj2);
                    } while(true);
                }
            }

            private void reupCache(HashSet hashset)
            {
/*1922*/        wLock.lock();
                final String fAffectedContract;
/*1924*/        for(hashset = hashset.iterator(); hashset.hasNext(); igashCache.releaseMatching(fAffectedContract))
                {
/*1924*/            fAffectedContract = fAffectedContract = (String)hashset.next();
/*1926*/            fAffectedContract = new CacheKeyFilter() {

                        public boolean matches(IgdCacheKey igdcachekey)
                        {
/*1929*/                    return igdcachekey.cacheKey.matchesRemovalName(fAffectedContract);
                        }

                        public volatile boolean matches(Object obj)
                        {
/*1926*/                    return matches((IgdCacheKey)obj);
                        }

                        final String val$fAffectedContract;
                        final ServiceLocatorImpl this$0;

                    
                    {
/*1926*/                this$0 = ServiceLocatorImpl.this;
/*1926*/                fAffectedContract = s;
/*1926*/                super();
                    }
            };
/*1933*/            igdCache.releaseMatching(fAffectedContract);
                }

/*1937*/        wLock.unlock();
/*1938*/        return;
/*1937*/        hashset;
/*1937*/        wLock.unlock();
/*1937*/        throw hashset;
            }

            private void reup(List list, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, HashSet hashset, 
                    boolean flag5)
            {
/*1952*/        reupCache(hashset);
/*1954*/        if(flag1)
/*1955*/            reupInjectionResolvers();
/*1958*/        if(flag2)
/*1959*/            reupErrorHandlers();
/*1962*/        if(flag4)
/*1963*/            reupConfigListeners();
/*1966*/        if(flag)
/*1967*/            reupInstanceListenersHandlers(allDescriptors.getSortedList());
/*1970*/        else
/*1970*/            reupInstanceListenersHandlers(list);
/*1973*/        if(flag3)
/*1974*/            reupClassAnalyzers();
/*1979*/        if(flag5)
/*1980*/            reupInterceptionServices();
/*1983*/        contextCache.clear();
            }

            private void getAllChildren(LinkedList linkedlist)
            {
/*1988*/        synchronized(children)
                {
/*1989*/            obj = new LinkedList(children.keySet());
                }
/*1992*/        linkedlist.addAll(((Collection) (obj)));
                ServiceLocatorImpl servicelocatorimpl;
/*1994*/        for(Iterator iterator = ((LinkedList) (obj)).iterator(); iterator.hasNext(); (servicelocatorimpl = (ServiceLocatorImpl)iterator.next()).getAllChildren(linkedlist));
            }

            private void callAllConfigurationListeners(List list)
            {
/*2000*/        if(list == null)
/*2000*/            return;
/*2002*/        list = list.iterator();
/*2002*/        do
                {
/*2002*/            if(!list.hasNext())
/*2002*/                break;
                    ServiceHandle servicehandle;
                    ActiveDescriptor activedescriptor;
/*2002*/            if((activedescriptor = (servicehandle = (ServiceHandle)list.next()).getActiveDescriptor()).getLocatorId().longValue() == id)
/*2007*/                try
                        {
/*2007*/                    ((DynamicConfigurationListener)servicehandle.getService()).configurationChanged();
                        }
/*2009*/                catch(Throwable _ex) { }
                } while(true);
            }

            void addConfiguration(DynamicConfigurationImpl dynamicconfigurationimpl)
            {
                MultiException multiexception;
/*2019*/        multiexception = null;
/*2021*/        wLock.lock();
                Object obj;
/*2023*/        obj = checkConfiguration(dynamicconfigurationimpl);
/*2025*/        removeConfigurationInternal(((CheckConfigurationData) (obj)).getUnbinds());
/*2027*/        List list = addConfigurationInternal(dynamicconfigurationimpl);
/*2029*/        reup(list, ((CheckConfigurationData) (obj)).getInstanceLifecycleModificationsMade(), ((CheckConfigurationData) (obj)).getInjectionResolverModificationMade(), ((CheckConfigurationData) (obj)).getErrorHandlerModificationMade(), ((CheckConfigurationData) (obj)).getClassAnalyzerModificationMade(), ((CheckConfigurationData) (obj)).getDynamicConfigurationListenerModificationMade(), ((CheckConfigurationData) (obj)).getAffectedContracts(), ((CheckConfigurationData) (obj)).getInterceptionServiceModificationMade());
/*2038*/        dynamicconfigurationimpl = new LinkedList(configListeners);
/*2048*/        wLock.unlock();
/*2065*/        break MISSING_BLOCK_LABEL_188;
/*2039*/        JVM INSTR dup ;
                MultiException multiexception1;
/*2040*/        multiexception1;
/*2040*/        multiexception;
/*2041*/        throw multiexception1;
/*2043*/        dynamicconfigurationimpl;
/*2043*/        obj = null;
/*2044*/        if(multiexception != null)
/*2045*/            obj = new LinkedList(errorHandlers);
/*2048*/        wLock.unlock();
/*2050*/        if(obj != null && !((List) (obj)).isEmpty())
/*2051*/            for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                    {
/*2051*/                ErrorService errorservice = (ErrorService)((Iterator) (obj)).next();
/*2053*/                try
                        {
/*2053*/                    errorservice.onFailure(new ErrorInformationImpl(ErrorType.DYNAMIC_CONFIGURATION_FAILURE, null, null, multiexception));
                        }
/*2059*/                catch(Throwable _ex) { }
                    }

/*2065*/        throw dynamicconfigurationimpl;
/*2067*/        Object obj1 = new LinkedList();
/*2068*/        getAllChildren(((LinkedList) (obj1)));
/*2070*/        for(Iterator iterator = ((LinkedList) (obj1)).iterator(); iterator.hasNext(); ((ServiceLocatorImpl) (obj1 = (ServiceLocatorImpl)iterator.next())).reupCache(((CheckConfigurationData) (obj)).getAffectedContracts()));
/*2074*/        callAllConfigurationListeners(dynamicconfigurationimpl);
/*2075*/        return;
            }

            boolean isInjectAnnotation(Annotation annotation)
            {
/*2078*/        return allResolvers.containsKey(annotation.annotationType());
            }

            boolean isInjectAnnotation(Annotation annotation, boolean flag)
            {
/*2083*/        if((annotation = (InjectionResolver)allResolvers.get(annotation.annotationType())) == null)
/*2085*/            return false;
/*2087*/        if(flag)
/*2088*/            return annotation.isConstructorParameterIndicator();
/*2091*/        else
/*2091*/            return annotation.isMethodParameterIndicator();
            }

            InjectionResolver getInjectionResolver(Class class1)
            {
/*2095*/        return (InjectionResolver)allResolvers.get(class1);
            }

            private Context _resolveContext(Class class1)
                throws IllegalStateException
            {
/*2099*/        Context context = null;
                Type atype[];
/*2100*/        (atype = new Type[1])[0] = class1;
/*2102*/        Object obj = new ParameterizedTypeImpl(org/glassfish/hk2/api/Context, atype);
/*2103*/        obj = ((List) (obj = (List)ReflectionHelper.cast(protectedGetAllServiceHandles(((Type) (obj)), new Annotation[0])))).iterator();
/*2105*/        do
                {
/*2105*/            if(!((Iterator) (obj)).hasNext())
/*2105*/                break;
                    Object obj1;
/*2105*/            if(((Context) (obj1 = (Context)((ServiceHandle) (obj1 = (ServiceHandle)((Iterator) (obj)).next())).getService())).isActive())
                    {
/*2110*/                if(context != null)
/*2111*/                    throw new IllegalStateException((new StringBuilder("There is more than one active context for ")).append(class1.getName()).toString());
/*2114*/                context = ((Context) (obj1));
                    }
                } while(true);
/*2116*/        if(context == null)
/*2117*/            throw new IllegalStateException((new StringBuilder("Could not find an active context for ")).append(class1.getName()).toString());
/*2119*/        else
/*2119*/            return context;
            }

            Context resolveContext(Class class1)
                throws IllegalStateException
            {
/*2123*/        if(class1.equals(javax/inject/Singleton))
/*2123*/            return singletonContext;
/*2124*/        if(class1.equals(org/glassfish/hk2/api/PerLookup))
/*2124*/            return perLookupContext;
                Context context;
/*2125*/        if((context = (Context)contextCache.compute(class1)).isActive())
                {
/*2126*/            return context;
                } else
                {
/*2129*/            contextCache.remove(class1);
/*2130*/            return (Context)contextCache.compute(class1);
                }
            }

            private Class loadClass(Descriptor descriptor, Injectee injectee)
            {
                HK2Loader hk2loader;
/*2134*/        if(descriptor == null)
/*2134*/            throw new IllegalArgumentException();
/*2136*/        if((hk2loader = descriptor.getLoader()) == null)
/*2138*/            return Utilities.loadClass(descriptor.getImplementation(), injectee);
/*2143*/        injectee = hk2loader.loadClass(descriptor.getImplementation());
/*2155*/        break MISSING_BLOCK_LABEL_119;
/*2145*/        JVM INSTR dup ;
/*2146*/        injectee;
/*2146*/        new IllegalStateException((new StringBuilder("Could not load descriptor ")).append(descriptor).toString());
/*2146*/        addError();
/*2148*/        throw injectee;
/*2150*/        injectee;
/*2151*/        (injectee = new MultiException(injectee)).addError(new IllegalStateException((new StringBuilder("Could not load descriptor ")).append(descriptor).toString()));
/*2154*/        throw injectee;
/*2157*/        return injectee;
            }

            private transient ImmediateResults narrow(ServiceLocator servicelocator, List list, Type type, String s, Injectee injectee, boolean flag, boolean flag1, 
                    NarrowResults narrowresults, Filter filter, Annotation aannotation[])
            {
                ImmediateResults immediateresults;
/*2170*/        narrowresults = (immediateresults = new ImmediateResults(narrowresults)).getTimelessResults();
/*2173*/        if(list != null)
                {
/*2174*/            list = (List)ReflectionHelper.cast(list);
/*2175*/            narrowresults.setUnnarrowedResults(list);
                }
/*2178*/        list = Utilities.fixAndCheckQualifiers(aannotation, s);
/*2180*/        for(s = narrowresults.getResults().iterator(); s.hasNext();)
                {
/*2180*/            aannotation = (ActiveDescriptor)s.next();
/*2181*/            if(!flag1 || validate((SystemDescriptor)aannotation, injectee, filter))
                    {
/*2183*/                immediateresults.addValidatedResult(aannotation);
/*2185*/                if(flag)
/*2185*/                    return immediateresults;
                    }
                }

/*2188*/        if(type != null && (type instanceof Class) && ((Class)type).isAnnotation())
/*2192*/            type = null;
/*2196*/label0:
/*2196*/        do
                {
/*2196*/label1:
/*2196*/            do
                    {
/*2196*/                if((s = narrowresults.removeUnnarrowedResult()) == null)
/*2197*/                    break label0;
/*2197*/                aannotation = 0;
/*2198*/                if((type != null || !list.isEmpty()) && !s.isReified())
/*2200*/                    aannotation = 1;
/*2203*/                if(aannotation != 0)
/*2205*/                    try
                            {
/*2205*/                        s = servicelocator.reifyDescriptor(s, injectee);
                            }
                            // Misplaced declaration of an exception variable
/*2207*/                    catch(Annotation aannotation[])
                            {
/*2208*/                        narrowresults.addError(s, injectee, aannotation);
/*2209*/                        continue label0;
                            }
                            // Misplaced declaration of an exception variable
/*2211*/                    catch(Annotation aannotation[])
                            {
/*2212*/                        narrowresults.addError(s, injectee, new MultiException(aannotation));
/*2213*/                        continue label0;
                            }
/*2218*/                if(type == null)
/*2219*/                    break;
/*2219*/                aannotation = 0;
/*2220*/                Iterator iterator = s.getContractTypes().iterator();
                        Type type1;
/*2220*/                do
                        {
/*2220*/                    if(!iterator.hasNext())
/*2220*/                        continue label1;
/*2220*/                    type1 = (Type)iterator.next();
                        } while(!Utilities.isTypeSafe(type, type1));
/*2222*/                aannotation = 1;
                    } while(aannotation == 0);
/*2237*/            if(list.isEmpty() || ReflectionHelper.annotationContainsAll(aannotation = s.getQualifierAnnotations(), list))
                    {
/*2247*/                narrowresults.addGoodResult(s);
/*2249*/                if(!flag1 || validate((SystemDescriptor)s, injectee, filter))
                        {
/*2250*/                    immediateresults.addValidatedResult(s);
/*2252*/                    if(flag)
/*2252*/                        return immediateresults;
                        }
                    }
                } while(true);
/*2255*/        return immediateresults;
            }

            public long getLocatorId()
            {
/*2263*/        return id;
            }

            long getNextServiceId()
            {
/*2269*/        return nextServiceId.getAndIncrement();
            }

            private void addChild(ServiceLocatorImpl servicelocatorimpl)
            {
/*2276*/        synchronized(children)
                {
/*2277*/            children.put(servicelocatorimpl, null);
                }
            }

            private void removeChild(ServiceLocatorImpl servicelocatorimpl)
            {
/*2282*/        synchronized(children)
                {
/*2283*/            children.remove(servicelocatorimpl);
                }
            }

            private void checkState()
            {
/*2288*/        if(ServiceLocatorState.SHUTDOWN.equals(state))
/*2288*/            throw new IllegalStateException((new StringBuilder()).append(this).append(" has been shut down").toString());
/*2289*/        else
/*2289*/            return;
            }

            private LinkedHashSet getAllValidators()
            {
/*2292*/        if(parent == null)
                {
/*2293*/            return allValidators;
                } else
                {
                    LinkedHashSet linkedhashset;
/*2296*/            (linkedhashset = new LinkedHashSet()).addAll(parent.getAllValidators());
/*2299*/            linkedhashset.addAll(allValidators);
/*2301*/            return linkedhashset;
                }
            }

            public String getDefaultClassAnalyzerName()
            {
/*2306*/        Object obj = classAnalyzerLock;
/*2306*/        JVM INSTR monitorenter ;
/*2307*/        return defaultClassAnalyzer;
                Exception exception;
/*2308*/        exception;
/*2308*/        throw exception;
            }

            public void setDefaultClassAnalyzerName(String s)
            {
/*2313*/        synchronized(classAnalyzerLock)
                {
/*2314*/            if(s == null)
/*2315*/                defaultClassAnalyzer = "default";
/*2318*/            else
/*2318*/                defaultClassAnalyzer = s;
                }
            }

            public Unqualified getDefaultUnqualified()
            {
/*2325*/        rLock.lock();
/*2327*/        Unqualified unqualified = defaultUnqualified;
/*2330*/        rLock.unlock();
/*2330*/        return unqualified;
                Exception exception;
/*2330*/        exception;
/*2330*/        rLock.unlock();
/*2330*/        throw exception;
            }

            public void setDefaultUnqualified(Unqualified unqualified)
            {
/*2336*/        wLock.lock();
/*2338*/        defaultUnqualified = unqualified;
/*2341*/        wLock.unlock();
/*2342*/        return;
/*2341*/        unqualified;
/*2341*/        wLock.unlock();
/*2341*/        throw unqualified;
            }

            ClassAnalyzer getAnalyzer(String s, Collector collector)
            {
                ClassAnalyzer classanalyzer;
/*2348*/        synchronized(classAnalyzerLock)
                {
/*2349*/            if(s == null)
/*2350*/                s = defaultClassAnalyzer;
/*2353*/            classanalyzer = (ClassAnalyzer)classAnalyzers.get(s);
                }
/*2356*/        if(classanalyzer == null)
                {
/*2357*/            collector.addThrowable(new IllegalStateException((new StringBuilder("Could not find an implementation of ClassAnalyzer with name ")).append(s).toString()));
/*2360*/            return null;
                } else
                {
/*2363*/            return classanalyzer;
                }
            }

            public ServiceLocator getParent()
            {
/*2474*/        return parent;
            }

            public boolean getNeutralContextClassLoader()
            {
/*2479*/        return neutralContextClassLoader;
            }

            public void setNeutralContextClassLoader(boolean flag)
            {
/*2484*/        wLock.lock();
/*2486*/        neutralContextClassLoader = flag;
/*2489*/        wLock.unlock();
/*2490*/        return;
/*2489*/        flag;
/*2489*/        wLock.unlock();
/*2489*/        throw flag;
            }

            private ServiceLocatorImpl getMe()
            {
/*2500*/        return this;
            }

            boolean hasInjectAnnotation(AnnotatedElement annotatedelement)
            {
/*2504*/        return perLocatorUtilities.hasInjectAnnotation(annotatedelement);
            }

            InjectionResolver getInjectionResolverForInjectee(SystemInjecteeImpl systeminjecteeimpl)
            {
/*2508*/        return (InjectionResolver)injecteeToResolverCache.compute(systeminjecteeimpl);
            }

            ClassReflectionHelper getClassReflectionHelper()
            {
/*2512*/        return classReflectionHelper;
            }

            LinkedList getErrorHandlers()
            {
/*2516*/        rLock.lock();
/*2518*/        LinkedList linkedlist = new LinkedList(errorHandlers);
/*2521*/        rLock.unlock();
/*2521*/        return linkedlist;
                Exception exception;
/*2521*/        exception;
/*2521*/        rLock.unlock();
/*2521*/        throw exception;
            }

            PerLocatorUtilities getPerLocatorUtilities()
            {
/*2526*/        return perLocatorUtilities;
            }

            int getNumberOfDescriptors()
            {
/*2530*/        rLock.lock();
/*2532*/        int i = allDescriptors.size();
/*2535*/        rLock.unlock();
/*2535*/        return i;
                Exception exception;
/*2535*/        exception;
/*2535*/        rLock.unlock();
/*2535*/        throw exception;
            }

            int getNumberOfChildren()
            {
/*2540*/        return children.size();
            }

            int getServiceCacheSize()
            {
/*2544*/        return igdCache.getValueSize();
            }

            int getServiceCacheMaximumSize()
            {
/*2548*/        return igdCache.getMaxSize();
            }

            void clearServiceCache()
            {
/*2552*/        igdCache.clear();
            }

            int getReflectionCacheSize()
            {
/*2557*/        return classReflectionHelper.size();
            }

            void clearReflectionCache()
            {
/*2561*/        wLock.lock();
/*2563*/        classReflectionHelper.dispose();
/*2566*/        wLock.unlock();
/*2567*/        return;
                Exception exception;
/*2566*/        exception;
/*2566*/        wLock.unlock();
/*2566*/        throw exception;
            }

            int unsortIndexes(int i, SystemDescriptor systemdescriptor, Set set)
            {
/*2571*/        wLock.lock();
/*2573*/        i = systemdescriptor.setRankWithLock(i);
/*2575*/        for(systemdescriptor = set.iterator(); systemdescriptor.hasNext(); (set = (IndexedListData)systemdescriptor.next()).unSort());
/*2582*/        wLock.unlock();
/*2582*/        return i;
/*2582*/        i;
/*2582*/        wLock.unlock();
/*2582*/        throw i;
            }

            public String toString()
            {
/*2589*/        return (new StringBuilder("ServiceLocatorImpl(")).append(locatorName).append(",").append(id).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private static final String BIND_TRACING_PATTERN_PROPERTY = "org.jvnet.hk2.properties.bind.tracing.pattern";
            private static final String BIND_TRACING_PATTERN = (String)AccessController.doPrivileged(new PrivilegedAction() {

                public final String run()
                {
/* 127*/            return System.getProperty("org.jvnet.hk2.properties.bind.tracing.pattern");
                }

                public final volatile Object run()
                {
/* 124*/            return run();
                }

    });
            private static final String BIND_TRACING_STACKS_PROPERTY = "org.jvnet.hk2.properties.bind.tracing.stacks";
            private static boolean BIND_TRACING_STACKS = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/* 136*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.bind.tracing.stacks", "false")));
                }

                public final volatile Object run()
                {
/* 133*/            return run();
                }

    })).booleanValue();
            private static final int CACHE_SIZE = 20000;
            private static final Object sLock = new Object();
            private static long currentLocatorId = 0L;
            static final DescriptorComparator DESCRIPTOR_COMPARATOR = new DescriptorComparator();
            private static final ServiceHandleComparator HANDLE_COMPARATOR = new ServiceHandleComparator();
            private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            private final java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock wLock;
            private final java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock rLock;
            private final AtomicLong nextServiceId = new AtomicLong();
            private final String locatorName;
            private final long id = getAndIncrementLocatorId();
            private final ServiceLocatorImpl parent;
            private volatile boolean neutralContextClassLoader;
            private final ClassReflectionHelper classReflectionHelper = new ClassReflectionHelperImpl();
            private final PerLocatorUtilities perLocatorUtilities = new PerLocatorUtilities(this);
            private final IndexedListData allDescriptors = new IndexedListData();
            private final HashMap descriptorsByAdvertisedContract = new HashMap();
            private final HashMap descriptorsByName = new HashMap();
            private final Context singletonContext = new SingletonContext(this);
            private final Context perLookupContext = new PerLookupContext();
            private final LinkedHashSet allValidators = new LinkedHashSet();
            private final LinkedList errorHandlers = new LinkedList();
            private final LinkedList configListeners = new LinkedList();
            private volatile boolean hasInterceptionServices;
            private final LinkedList interceptionServices = new LinkedList();
            private final Cache contextCache = new Cache(new Computable() {

                public Context compute(Class class1)
                {
/* 182*/            return _resolveContext(class1);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/* 178*/            return compute((Class)obj);
                }

                final ServiceLocatorImpl this$0;

                    
                    {
/* 178*/                this$0 = ServiceLocatorImpl.this;
/* 178*/                super();
                    }
    });
            private final Map children = new WeakHashMap();
            private final Object classAnalyzerLock = new Object();
            private final HashMap classAnalyzers = new HashMap();
            private String defaultClassAnalyzer;
            private volatile Unqualified defaultUnqualified;
            private ConcurrentHashMap allResolvers;
            private final Cache injecteeToResolverCache = new Cache(new Computable() {

                public InjectionResolver compute(SystemInjecteeImpl systeminjecteeimpl)
                {
/* 201*/            return perLocatorUtilities.getInjectionResolver(getMe(), systeminjecteeimpl);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/* 197*/            return compute((SystemInjecteeImpl)obj);
                }

                final ServiceLocatorImpl this$0;

                    
                    {
/* 197*/                this$0 = ServiceLocatorImpl.this;
/* 197*/                super();
                    }
    });
            private ServiceLocatorState state;
            private final WeakCARCache igdCache = CacheUtilities.createWeakCARCache(new Computable() {

                public IgdValue compute(IgdCacheKey igdcachekey)
                {
/*1144*/            return igdCacheCompute(igdcachekey);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*1141*/            return compute((IgdCacheKey)obj);
                }

                final ServiceLocatorImpl this$0;

                    
                    {
/*1141*/                this$0 = ServiceLocatorImpl.this;
/*1141*/                super();
                    }
    }, 20000, false);
            private final WeakCARCache igashCache = CacheUtilities.createWeakCARCache(new Computable() {

                public IgdValue compute(IgdCacheKey igdcachekey)
                {
/*1349*/            Object obj = getDescriptors(igdcachekey.filter, null, true, false, true);
/*1350*/            if(!((NarrowResults) (obj = (igdcachekey = narrow(ServiceLocatorImpl.this, ((List) (obj)), igdcachekey.contractOrImpl, null, null, false, true, null, igdcachekey.filter, igdcachekey.qualifiers)).getTimelessResults())).getErrors().isEmpty())
                    {
/*1362*/                Utilities.handleErrors(((NarrowResults) (obj)), new LinkedList(errorHandlers));
/*1363*/                throw new ComputationErrorException(new IgdValue(((NarrowResults) (obj)), igdcachekey));
                    } else
                    {
/*1366*/                return new IgdValue(((NarrowResults) (obj)), igdcachekey);
                    }
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*1345*/            return compute((IgdCacheKey)obj);
                }

                final ServiceLocatorImpl this$0;

                    
                    {
/*1345*/                this$0 = ServiceLocatorImpl.this;
/*1345*/                super();
                    }
    }, 20000, false);









}
