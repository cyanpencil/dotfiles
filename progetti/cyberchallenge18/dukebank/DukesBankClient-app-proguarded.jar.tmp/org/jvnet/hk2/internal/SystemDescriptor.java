// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SystemDescriptor.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.DescriptorImpl;
import org.glassfish.hk2.utilities.reflection.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            AutoActiveDescriptor, ClazzCreator, Closeable, Collector, 
//            Creator, ErrorInformationImpl, FactoryCreator, InstanceLifecycleEventImpl, 
//            ServiceLocatorImpl, Utilities, IndexedListData

public class SystemDescriptor
    implements ActiveDescriptor, Closeable
{

            SystemDescriptor(Descriptor descriptor, boolean flag, ServiceLocatorImpl servicelocatorimpl, Long long1)
            {
/*  94*/        reifying = false;
/*  95*/        preAnalyzed = false;
/*  96*/        closed = false;
/*  99*/        cacheSet = false;
/* 120*/        singletonGeneration = 0x7fffffff;
/* 124*/        if(flag)
/* 125*/            baseDescriptor = BuilderHelper.deepCopyDescriptor(descriptor);
/* 128*/        else
/* 128*/            baseDescriptor = descriptor;
/* 131*/        sdLocator = servicelocatorimpl;
/* 132*/        id = long1;
/* 134*/        if(descriptor instanceof ActiveDescriptor)
                {
/* 135*/            if((descriptor = (ActiveDescriptor)descriptor).isReified())
                    {
/* 137*/                activeDescriptor = descriptor;
/* 138*/                reified = true;
/* 139*/                if(descriptor instanceof AutoActiveDescriptor)
/* 140*/                    ((AutoActiveDescriptor)descriptor).setHK2Parent(this);
                    } else
                    {
/* 144*/                activeDescriptor = null;
/* 145*/                preAnalyzed = true;
/* 147*/                implClass = descriptor.getImplementationClass();
/* 148*/                scopeAnnotation = descriptor.getScopeAsAnnotation();
/* 149*/                scope = descriptor.getScopeAnnotation();
/* 150*/                contracts = Collections.unmodifiableSet(descriptor.getContractTypes());
/* 151*/                qualifiers = Collections.unmodifiableSet(descriptor.getQualifierAnnotations());
/* 153*/                return;
                    }
                } else
                {
/* 155*/            activeDescriptor = null;
                }
            }

            public String getImplementation()
            {
/* 164*/        return baseDescriptor.getImplementation();
            }

            public Set getAdvertisedContracts()
            {
/* 172*/        return baseDescriptor.getAdvertisedContracts();
            }

            public String getScope()
            {
/* 180*/        return baseDescriptor.getScope();
            }

            public String getName()
            {
/* 188*/        return baseDescriptor.getName();
            }

            public Set getQualifiers()
            {
/* 196*/        return baseDescriptor.getQualifiers();
            }

            public DescriptorType getDescriptorType()
            {
/* 204*/        return baseDescriptor.getDescriptorType();
            }

            public DescriptorVisibility getDescriptorVisibility()
            {
/* 212*/        return baseDescriptor.getDescriptorVisibility();
            }

            public Map getMetadata()
            {
/* 220*/        return baseDescriptor.getMetadata();
            }

            public HK2Loader getLoader()
            {
/* 228*/        return baseDescriptor.getLoader();
            }

            public int getRanking()
            {
/* 236*/        return baseDescriptor.getRanking();
            }

            public Boolean isProxiable()
            {
/* 244*/        return baseDescriptor.isProxiable();
            }

            public Boolean isProxyForSameScope()
            {
/* 252*/        return baseDescriptor.isProxyForSameScope();
            }

            public String getClassAnalysisName()
            {
/* 257*/        return baseDescriptor.getClassAnalysisName();
            }

            public int setRanking(int i)
            {
/* 267*/        return sdLocator.unsortIndexes(i, this, myLists);
            }

            int setRankWithLock(int i)
            {
/* 271*/        return baseDescriptor.setRanking(i);
            }

            void addList(IndexedListData indexedlistdata)
            {
/* 275*/        myLists.add(indexedlistdata);
            }

            void removeList(IndexedListData indexedlistdata)
            {
/* 279*/        myLists.remove(indexedlistdata);
            }

            public Long getServiceId()
            {
/* 287*/        return id;
            }

            public Object getCache()
            {
/* 295*/        return cachedValue;
            }

            public boolean isCacheSet()
            {
/* 303*/        return cacheSet;
            }

            public void setCache(Object obj)
            {
/* 311*/        synchronized(cacheLock)
                {
/* 312*/            cachedValue = obj;
/* 313*/            cacheSet = true;
                }
            }

            public void releaseCache()
            {
/* 323*/        synchronized(cacheLock)
                {
/* 324*/            cacheSet = false;
/* 325*/            cachedValue = null;
                }
            }

            public boolean isReified()
            {
/* 337*/        if(reified)
/* 337*/            return true;
/* 339*/        SystemDescriptor systemdescriptor = this;
/* 339*/        JVM INSTR monitorenter ;
/* 340*/        return reified;
                Exception exception;
/* 341*/        exception;
/* 341*/        throw exception;
            }

            public Class getImplementationClass()
            {
/* 349*/        checkState();
/* 351*/        if(activeDescriptor != null)
/* 352*/            return activeDescriptor.getImplementationClass();
/* 355*/        else
/* 355*/            return implClass;
            }

            public Set getContractTypes()
            {
/* 363*/        checkState();
/* 365*/        if(activeDescriptor != null)
/* 366*/            return activeDescriptor.getContractTypes();
/* 369*/        else
/* 369*/            return contracts;
            }

            public Annotation getScopeAsAnnotation()
            {
/* 374*/        checkState();
/* 376*/        return scopeAnnotation;
            }

            public Class getScopeAnnotation()
            {
/* 384*/        checkState();
/* 386*/        if(activeDescriptor != null)
/* 387*/            return activeDescriptor.getScopeAnnotation();
/* 390*/        else
/* 390*/            return scope;
            }

            public Set getQualifierAnnotations()
            {
/* 398*/        checkState();
/* 400*/        if(activeDescriptor != null)
/* 401*/            return activeDescriptor.getQualifierAnnotations();
/* 404*/        else
/* 404*/            return qualifiers;
            }

            public List getInjectees()
            {
/* 412*/        checkState();
/* 414*/        if(activeDescriptor != null)
/* 415*/            return activeDescriptor.getInjectees();
/* 418*/        else
/* 418*/            return creator.getInjectees();
            }

            public Long getFactoryServiceId()
            {
/* 422*/        if(activeDescriptor != null)
/* 423*/            return activeDescriptor.getFactoryServiceId();
/* 426*/        else
/* 426*/            return factoryServiceId;
            }

            public Long getFactoryLocatorId()
            {
/* 430*/        if(activeDescriptor != null)
/* 431*/            return activeDescriptor.getFactoryLocatorId();
/* 434*/        else
/* 434*/            return factoryLocatorId;
            }

            void setFactoryIds(Long long1, Long long2)
            {
/* 438*/        factoryLocatorId = long1;
/* 439*/        factoryServiceId = long2;
            }

            void invokeInstanceListeners(InstanceLifecycleEvent instancelifecycleevent)
            {
                InstanceLifecycleListener instancelifecyclelistener;
/* 443*/        for(Iterator iterator = instanceListeners.iterator(); iterator.hasNext(); (instancelifecyclelistener = (InstanceLifecycleListener)iterator.next()).lifecycleEvent(instancelifecycleevent));
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 453*/        checkState();
/* 457*/        if(activeDescriptor != null)
                {
/* 458*/            if(!(activeDescriptor instanceof AutoActiveDescriptor))
/* 460*/                invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.PRE_PRODUCTION, null, this));
/* 463*/            servicehandle = ((ServiceHandle) (activeDescriptor.create(servicehandle)));
/* 465*/            if(!(activeDescriptor instanceof AutoActiveDescriptor))
/* 467*/                invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.POST_PRODUCTION, servicehandle, this));
                } else
                {
/* 471*/            servicehandle = ((ServiceHandle) (creator.create(servicehandle, this)));
                }
/* 474*/        return servicehandle;
/* 476*/        JVM INSTR dup ;
/* 477*/        servicehandle;
/* 477*/        JVM INSTR instanceof #49  <Class MultiException>;
/* 477*/        JVM INSTR ifne 108;
                   goto _L1 _L2
_L1:
/* 478*/        break MISSING_BLOCK_LABEL_99;
_L2:
/* 478*/        break MISSING_BLOCK_LABEL_108;
/* 478*/        servicehandle = new MultiException(servicehandle);
                MultiException multiexception;
/* 480*/        if(!(multiexception = (MultiException)servicehandle).getReportToErrorService())
/* 484*/            throw (RuntimeException)servicehandle;
                Object obj;
/* 487*/        for(obj = ((LinkedList) (obj = sdLocator.getErrorHandlers())).iterator(); ((Iterator) (obj)).hasNext();)
                {
/* 488*/            ErrorService errorservice = (ErrorService)((Iterator) (obj)).next();
/* 489*/            ErrorInformationImpl errorinformationimpl = new ErrorInformationImpl(ErrorType.SERVICE_CREATION_FAILURE, this, null, multiexception);
/* 495*/            try
                    {
/* 495*/                errorservice.onFailure(errorinformationimpl);
                    }
/* 497*/            catch(Throwable _ex) { }
                }

/* 502*/        throw (RuntimeException)servicehandle;
            }

            public void dispose(Object obj)
            {
/* 511*/        checkState();
/* 513*/        InstanceLifecycleEventImpl instancelifecycleeventimpl = new InstanceLifecycleEventImpl(InstanceLifecycleEventType.PRE_DESTRUCTION, obj, this);
/* 518*/        invokeInstanceListeners(instancelifecycleeventimpl);
/* 521*/        if(activeDescriptor != null)
                {
/* 522*/            activeDescriptor.dispose(obj);
/* 523*/            return;
                }
/* 526*/        creator.dispose(obj);
/* 555*/        return;
/* 528*/        JVM INSTR dup ;
/* 529*/        obj;
/* 529*/        JVM INSTR instanceof #49  <Class MultiException>;
/* 529*/        JVM INSTR ifne 68;
                   goto _L1 _L2
_L1:
/* 530*/        break MISSING_BLOCK_LABEL_59;
_L2:
/* 530*/        break MISSING_BLOCK_LABEL_68;
/* 530*/        obj = new MultiException(((Throwable) (obj)));
                MultiException multiexception;
/* 532*/        if(!(multiexception = (MultiException)obj).getReportToErrorService())
/* 536*/            throw (RuntimeException)obj;
                Object obj1;
/* 539*/        for(obj1 = ((LinkedList) (obj1 = sdLocator.getErrorHandlers())).iterator(); ((Iterator) (obj1)).hasNext();)
                {
/* 540*/            ErrorService errorservice = (ErrorService)((Iterator) (obj1)).next();
/* 541*/            ErrorInformationImpl errorinformationimpl = new ErrorInformationImpl(ErrorType.SERVICE_DESTRUCTION_FAILURE, this, null, multiexception);
/* 547*/            try
                    {
/* 547*/                errorservice.onFailure(errorinformationimpl);
                    }
/* 549*/            catch(Throwable _ex) { }
                }

/* 554*/        throw (RuntimeException)obj;
            }

            private void checkState()
            {
/* 559*/        if(reified)
/* 559*/            return;
/* 561*/        synchronized(this)
                {
/* 562*/            if(!reified)
/* 562*/                throw new IllegalStateException();
                }
            }

            private ActiveDescriptor getFactoryDescriptor(final Method fFactoryServiceId, Type type, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
                ServiceHandle servicehandle;
/* 570*/label0:
                {
/* 570*/            if(factoryServiceId != null && factoryLocatorId != null)
                    {
/* 572*/                fFactoryServiceId = factoryServiceId;
/* 573*/                final Long fFactoryLocatorId = factoryLocatorId;
/* 575*/                if((fFactoryServiceId = servicelocatorimpl.getBestDescriptor(new IndexedFilter() {

                public boolean matches(Descriptor descriptor)
                {
/* 579*/            if(descriptor.getServiceId().longValue() != fFactoryServiceId.longValue())
/* 579*/                return false;
/* 580*/            return descriptor.getLocatorId().longValue() == fFactoryLocatorId.longValue();
                }

                public String getAdvertisedContract()
                {
/* 587*/            return org/glassfish/hk2/api/Factory.getName();
                }

                public String getName()
                {
/* 592*/            return null;
                }

                final Long val$fFactoryServiceId;
                final Long val$fFactoryLocatorId;
                final SystemDescriptor this$0;

                    
                    {
/* 575*/                this$0 = SystemDescriptor.this;
/* 575*/                fFactoryServiceId = long1;
/* 575*/                fFactoryLocatorId = long2;
/* 575*/                super();
                    }
    })) == null)
/* 598*/                    collector.addThrowable(new IllegalStateException((new StringBuilder("Could not find a pre-determined factory service for ")).append(type).toString()));
/* 603*/                return fFactoryServiceId;
                    }
/* 606*/            fFactoryServiceId = servicelocatorimpl.getAllServiceHandles(new ParameterizedTypeImpl(org/glassfish/hk2/api/Factory, new Type[] {
/* 606*/                type
                    }), new Annotation[0]);
/* 608*/            servicehandle = null;
/* 609*/            fFactoryServiceId = fFactoryServiceId.iterator();
                    ServiceHandle servicehandle1;
                    Object obj;
/* 609*/label1:
/* 609*/            do
/* 609*/                do
                        {
/* 609*/                    if(!fFactoryServiceId.hasNext())
/* 609*/                        break label0;
/* 609*/                    servicehandle1 = (ServiceHandle)fFactoryServiceId.next();
/* 610*/                    if(qualifiers.isEmpty())
                            {
/* 612*/                        servicehandle = servicehandle1;
/* 613*/                        break label0;
                            }
/* 616*/                    obj = servicehandle1.getActiveDescriptor();
/* 619*/                    try
                            {
/* 619*/                        obj = servicelocatorimpl.reifyDescriptor(((Descriptor) (obj)));
/* 624*/                        continue label1;
                            }
                            // Misplaced declaration of an exception variable
/* 621*/                    catch(Object obj)
                            {
/* 622*/                        collector.addThrowable(((Throwable) (obj)));
                            }
                        } while(true);
/* 626*/            while(!ReflectionHelper.annotationContainsAll(((Set) (obj = Utilities.getAllQualifiers(((java.lang.reflect.AnnotatedElement) (obj = Utilities.getFactoryProvideMethod(((ActiveDescriptor) (obj)).getImplementationClass()))), Utilities.getDefaultNameFromMethod(((Method) (obj)), collector), collector))), qualifiers));
/* 635*/            servicehandle = servicehandle1;
                }
/* 640*/        if(servicehandle == null)
                {
/* 641*/            collector.addThrowable(new IllegalStateException((new StringBuilder("Could not find a factory service for ")).append(type).toString()));
/* 643*/            return null;
                } else
                {
/* 646*/            fFactoryServiceId = servicehandle.getActiveDescriptor();
/* 647*/            factoryServiceId = fFactoryServiceId.getServiceId();
/* 648*/            factoryLocatorId = fFactoryServiceId.getLocatorId();
/* 650*/            return fFactoryServiceId;
                }
            }

            void reify(Class class1, Collector collector)
            {
/* 654*/label0:
                {
/* 654*/            if(reified)
/* 654*/                return;
/* 656*/            synchronized(this)
                    {
/* 657*/                if(!reified)
/* 657*/                    break label0;
                    }
/* 657*/            return;
                }
_L2:
/* 659*/        if(!reifying)
/* 661*/            break MISSING_BLOCK_LABEL_45;
/* 661*/        wait();
/* 666*/        if(true) goto _L2; else goto _L1
_L1:
/* 663*/        class1;
/* 664*/        collector.addThrowable(class1);
/* 665*/        systemdescriptor;
/* 665*/        JVM INSTR monitorexit ;
/* 665*/        return;
/* 669*/        if(!reified)
/* 669*/            break MISSING_BLOCK_LABEL_55;
/* 669*/        systemdescriptor;
/* 669*/        JVM INSTR monitorexit ;
/* 669*/        return;
/* 670*/        reifying = true;
/* 671*/        systemdescriptor;
/* 671*/        JVM INSTR monitorexit ;
/* 671*/        break MISSING_BLOCK_LABEL_70;
/* 671*/        class1;
/* 671*/        throw class1;
/* 678*/        internalReify(class1, collector);
/* 681*/        synchronized(this)
                {
/* 682*/            reifying = false;
/* 683*/            notifyAll();
/* 685*/            if(!collector.hasErrors())
/* 686*/                reified = true;
/* 689*/            else
/* 689*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("Errors were discovered while reifying ")).append(this).toString()));
                }
/* 681*/        break MISSING_BLOCK_LABEL_209;
/* 681*/        class1;
/* 681*/        synchronized(this)
                {
/* 682*/            reifying = false;
/* 683*/            notifyAll();
/* 685*/            if(!collector.hasErrors())
/* 686*/                reified = true;
/* 689*/            else
/* 689*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("Errors were discovered while reifying ")).append(this).toString()));
                }
/* 691*/        throw class1;
            }

            private void internalReify(Class class1, Collector collector)
            {
/* 703*/        if(!preAnalyzed)
/* 704*/            implClass = class1;
/* 707*/        else
/* 707*/        if(!class1.equals(implClass))
/* 708*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("During reification a class mistmatch was found ")).append(class1.getName()).append(" is not the same as ").append(implClass.getName()).toString()));
/* 714*/        if(getDescriptorType().equals(DescriptorType.CLASS))
                {
/* 715*/            if(!preAnalyzed)
/* 716*/                qualifiers = Collections.unmodifiableSet(Utilities.getAllQualifiers(class1, baseDescriptor.getName(), collector));
                    ClazzCreator clazzcreator;
/* 722*/            (clazzcreator = new ClazzCreator(sdLocator, class1)).initialize(this, collector);
/* 724*/            creator = clazzcreator;
/* 726*/            if(!preAnalyzed)
                    {
/* 727*/                ScopeInfo scopeinfo = Utilities.getScopeAnnotationType(class1, baseDescriptor, collector);
/* 728*/                scopeAnnotation = scopeinfo.getScope();
/* 729*/                scope = scopeinfo.getAnnoType();
/* 731*/                contracts = Collections.unmodifiableSet(ReflectionHelper.getTypeClosure(class1, baseDescriptor.getAdvertisedContracts()));
                    }
                } else
                {
/* 736*/            Utilities.checkFactoryType(class1, collector);
                    Object obj;
/* 739*/            if((obj = Utilities.getFactoryProvideMethod(class1)) == null)
                    {
/* 741*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("Could not find the provide method on the class ")).append(class1.getName()).toString()));
/* 744*/                return;
                    }
/* 747*/            if(!preAnalyzed)
/* 748*/                qualifiers = Collections.unmodifiableSet(Utilities.getAllQualifiers(((java.lang.reflect.AnnotatedElement) (obj)), Utilities.getDefaultNameFromMethod(((Method) (obj)), collector), collector));
                    Type type;
/* 755*/            if((type = ((Method) (obj)).getGenericReturnType()) instanceof TypeVariable)
/* 757*/                type = Utilities.getFactoryProductionType(class1);
                    ActiveDescriptor activedescriptor;
/* 760*/            if((activedescriptor = getFactoryDescriptor(((Method) (obj)), type, sdLocator, collector)) != null)
/* 766*/                creator = new FactoryCreator(sdLocator, activedescriptor);
/* 769*/            if(!preAnalyzed)
                    {
/* 770*/                obj = Utilities.getScopeAnnotationType(((java.lang.reflect.AnnotatedElement) (obj)), baseDescriptor, collector);
/* 771*/                scopeAnnotation = ((ScopeInfo) (obj)).getScope();
/* 772*/                scope = ((ScopeInfo) (obj)).getAnnoType();
/* 774*/                contracts = Collections.unmodifiableSet(ReflectionHelper.getTypeClosure(type, baseDescriptor.getAdvertisedContracts()));
                    }
                }
/* 781*/        if(baseDescriptor.getScope() == null && scope == null)
/* 782*/            scope = org/glassfish/hk2/api/PerLookup;
                String s;
/* 785*/        if(baseDescriptor.getScope() != null && scope != null && !(s = scope.getName()).equals(baseDescriptor.getScope()))
/* 789*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("The scope name given in the descriptor (")).append(baseDescriptor.getScope()).append(") did not match the scope annotation on the class (").append(scope.getName()).append(") in class ").append(Pretty.clazz(class1)).toString()));
/* 797*/        if(scope.isAnnotationPresent(org/glassfish/hk2/api/Proxiable) && scope.isAnnotationPresent(org/glassfish/hk2/api/Unproxiable))
/* 798*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("The scope ")).append(scope.getName()).append(" is marked both @Proxiable and @Unproxiable").toString()));
/* 802*/        if(isProxiable() != null && isProxiable().booleanValue() && Utilities.isUnproxiableScope(scope))
/* 803*/            collector.addThrowable(new IllegalArgumentException("The descriptor is in an Unproxiable scope but has  isProxiable set to true"));
            }

            public Long getLocatorId()
            {
/* 817*/        return Long.valueOf(sdLocator.getLocatorId());
            }

            public boolean close()
            {
/* 825*/        if(closed)
/* 825*/            return true;
/* 827*/        SystemDescriptor systemdescriptor = this;
/* 827*/        JVM INSTR monitorenter ;
/* 828*/        if(closed)
/* 828*/            return true;
/* 830*/        closed = true;
/* 831*/        false;
/* 831*/        systemdescriptor;
/* 831*/        JVM INSTR monitorexit ;
/* 831*/        return;
                Exception exception;
/* 832*/        exception;
/* 832*/        throw exception;
            }

            public boolean isClosed()
            {
/* 842*/        if(closed)
/* 842*/            return true;
/* 844*/        SystemDescriptor systemdescriptor = this;
/* 844*/        JVM INSTR monitorenter ;
/* 845*/        return closed;
                Exception exception;
/* 846*/        exception;
/* 846*/        throw exception;
            }

            boolean isValidating(ValidationService validationservice)
            {
                Boolean boolean1;
/* 857*/        if((boolean1 = (Boolean)validationServiceCache.get(validationservice)) != null)
/* 859*/            return boolean1.booleanValue();
/* 862*/        boolean flag = true;
/* 864*/        try
                {
/* 864*/            flag = BuilderHelper.filterMatches(this, validationservice.getLookupFilter());
                }
/* 866*/        catch(Throwable _ex) { }
/* 870*/        if(flag)
/* 871*/            validationServiceCache.put(validationservice, Boolean.TRUE);
/* 874*/        else
/* 874*/            validationServiceCache.put(validationservice, Boolean.FALSE);
/* 877*/        return flag;
            }

            void reupInstanceListeners(List list)
            {
/* 881*/        instanceListeners.clear();
/* 883*/        list = list.iterator();
/* 883*/        do
                {
/* 883*/            if(!list.hasNext())
/* 883*/                break;
                    InstanceLifecycleListener instancelifecyclelistener;
/* 883*/            org.glassfish.hk2.api.Filter filter = (instancelifecyclelistener = (InstanceLifecycleListener)list.next()).getFilter();
/* 885*/            if(BuilderHelper.filterMatches(this, filter))
/* 886*/                instanceListeners.add(instancelifecyclelistener);
                } while(true);
            }

            Class getPreAnalyzedClass()
            {
/* 892*/        return implClass;
            }

            int getSingletonGeneration()
            {
/* 896*/        return singletonGeneration;
            }

            void setSingletonGeneration(int i)
            {
/* 900*/        singletonGeneration = i;
            }

            public int hashCode()
            {
/* 905*/        int i = id.intValue();
/* 906*/        int j = (int)(id.longValue() >> 32);
/* 908*/        int k = (int)sdLocator.getLocatorId();
/* 909*/        int l = (int)(sdLocator.getLocatorId() >> 32);
/* 911*/        return i ^ j ^ k ^ l;
            }

            public boolean equals(Object obj)
            {
/* 917*/        if(obj == null)
/* 917*/            return false;
/* 918*/        if(!(obj instanceof SystemDescriptor))
/* 918*/            return false;
/* 920*/        if(!((SystemDescriptor) (obj = (SystemDescriptor)obj)).getServiceId().equals(id))
/* 922*/            return false;
/* 924*/        else
/* 924*/            return ((SystemDescriptor) (obj)).getLocatorId().equals(Long.valueOf(sdLocator.getLocatorId()));
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 929*/        DescriptorImpl.pretty(stringbuffer = new StringBuffer("SystemDescriptor("), this);
/* 933*/        stringbuffer.append((new StringBuilder("\n\treified=")).append(reified).toString());
/* 935*/        stringbuffer.append(")");
/* 937*/        return stringbuffer.toString();
            }

            private final Descriptor baseDescriptor;
            private final Long id;
            private final ActiveDescriptor activeDescriptor;
            private final ServiceLocatorImpl sdLocator;
            private volatile boolean reified;
            private boolean reifying;
            private boolean preAnalyzed;
            private volatile boolean closed;
            private final Object cacheLock = new Object();
            private boolean cacheSet;
            private Object cachedValue;
            private Class implClass;
            private Annotation scopeAnnotation;
            private Class scope;
            private Set contracts;
            private Set qualifiers;
            private Creator creator;
            private Long factoryLocatorId;
            private Long factoryServiceId;
            private final HashMap validationServiceCache = new HashMap();
            private final List instanceListeners = new LinkedList();
            private final Set myLists = new HashSet();
            private int singletonGeneration;
}
