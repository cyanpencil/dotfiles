// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorUtilities.java

package org.glassfish.hk2.utilities;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.internal.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            Binder, BuilderHelper, DescriptorBuilder, DescriptorImpl, 
//            FactoryDescriptorsImpl, ImmediateContext, RethrowErrorService

public abstract class ServiceLocatorUtilities
{
    static class SingletonImpl extends AnnotationLiteral
        implements Singleton
    {

                private static final long serialVersionUID = 0xde56725d29d9ab9eL;

                private SingletonImpl()
                {
                }

    }

    static class InheritableThreadImpl extends AnnotationLiteral
        implements InheritableThread
    {

                private static final long serialVersionUID = 0xc91a39139e16fcdcL;

                private InheritableThreadImpl()
                {
                }

    }

    static class PerThreadImpl extends AnnotationLiteral
        implements PerThread
    {

                private static final long serialVersionUID = 0x73dc823414dca6dL;

                private PerThreadImpl()
                {
                }

    }

    static class PerLookupImpl extends AnnotationLiteral
        implements PerLookup
    {

                private static final long serialVersionUID = 0x5af4871ffb3d1dbaL;

                private PerLookupImpl()
                {
                }

    }

    static class ImmediateImpl extends AnnotationLiteral
        implements Immediate
    {

                private static final long serialVersionUID = 0xc5dc06427737809bL;

                private ImmediateImpl()
                {
                }

    }

    static class AliasFilter
        implements Filter
    {

                public boolean matches(Descriptor descriptor)
                {
/* 869*/            if((descriptor = (List)descriptor.getMetadata().get("__AliasOf")) == null || descriptor.isEmpty())
                    {
/* 870*/                return false;
                    } else
                    {
/* 872*/                descriptor = (String)descriptor.get(0);
/* 874*/                return values.contains(descriptor);
                    }
                }

                private final Set values;

                private AliasFilter(List list)
                {
/* 858*/            values = new HashSet();
                    Object obj;
/* 861*/            for(list = list.iterator(); list.hasNext(); values.add(obj))
                    {
/* 861*/                obj = (ActiveDescriptor)list.next();
/* 862*/                obj = (new StringBuilder()).append(((ActiveDescriptor) (obj)).getLocatorId()).append(".").append(((ActiveDescriptor) (obj)).getServiceId()).toString();
                    }

                }

    }


            public ServiceLocatorUtilities()
            {
            }

            public static void enablePerThreadScope(ServiceLocator servicelocator)
            {
                Context context;
/* 108*/        if((context = (Context)servicelocator.getService((new TypeLiteral() {

    }).getType(), new Annotation[0])) != null)
                {
/* 109*/            return;
                } else
                {
/* 111*/            servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 113*/            DescriptorImpl descriptorimpl = BuilderHelper.link(org/glassfish/hk2/internal/PerThreadContext).to(org/glassfish/hk2/api/Context).in(javax/inject/Singleton.getName()).visibility(DescriptorVisibility.LOCAL).build();
                    ClassLoader classloader;
/* 119*/            classloader = (classloader = org/glassfish/hk2/utilities/ServiceLocatorUtilities.getClassLoader()) != null ? classloader : ClassLoader.getSystemClassLoader();
/* 121*/            descriptorimpl.setLoader(new HK2Loader(classloader) {

                        public final Class loadClass(String s)
                            throws MultiException
                        {
/* 125*/                    return binderClassLoader.loadClass(s);
/* 126*/                    s;
/* 127*/                    throw new MultiException(s);
                        }

                        final ClassLoader val$binderClassLoader;

                    
                    {
/* 121*/                binderClassLoader = classloader;
/* 121*/                super();
                    }
            });
/* 133*/            servicelocator.bind(descriptorimpl, false);
/* 134*/            servicelocator.commit();
/* 135*/            return;
                }
            }

            public static void enableInheritableThreadScope(ServiceLocator servicelocator)
            {
                Context context;
/* 147*/        if((context = (Context)servicelocator.getService((new TypeLiteral() {

    }).getType(), new Annotation[0])) != null)
                {
/* 150*/            return;
                } else
                {
/* 153*/            servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 155*/            DescriptorImpl descriptorimpl = BuilderHelper.link(org/glassfish/hk2/internal/InheritableThreadContext).to(org/glassfish/hk2/api/Context).in(javax/inject/Singleton.getName()).visibility(DescriptorVisibility.LOCAL).build();
                    ClassLoader classloader;
/* 161*/            classloader = (classloader = org/glassfish/hk2/utilities/ServiceLocatorUtilities.getClassLoader()) != null ? classloader : ClassLoader.getSystemClassLoader();
/* 163*/            descriptorimpl.setLoader(new HK2Loader(classloader) {

                        public final Class loadClass(String s)
                            throws MultiException
                        {
/* 167*/                    return binderClassLoader.loadClass(s);
/* 168*/                    s;
/* 169*/                    throw new MultiException(s);
                        }

                        final ClassLoader val$binderClassLoader;

                    
                    {
/* 163*/                binderClassLoader = classloader;
/* 163*/                super();
                    }
            });
/* 175*/            servicelocator.bind(descriptorimpl, false);
/* 176*/            servicelocator.commit();
/* 177*/            return;
                }
            }

            public static void enableImmediateScope(ServiceLocator servicelocator)
            {
/* 193*/        (servicelocator = enableImmediateScopeSuspended(servicelocator)).setImmediateState(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.RUNNING);
            }

            public static ImmediateController enableImmediateScopeSuspended(ServiceLocator servicelocator)
            {
                Object obj;
                Object obj1;
/* 211*/        for(obj = ((List) (obj = servicelocator.getAllServiceHandles((new TypeLiteral() {

    }).getType(), new Annotation[0]))).iterator(); ((Iterator) (obj)).hasNext();)
/* 212*/            if(((ActiveDescriptor) (obj1 = ((ServiceHandle) (obj1 = (ServiceHandle)((Iterator) (obj)).next())).getActiveDescriptor())).getLocatorId().longValue() == servicelocator.getLocatorId())
/* 215*/                return (ImmediateController)servicelocator.getService(org/glassfish/hk2/api/ImmediateController, new Annotation[0]);

/* 219*/        addClasses(servicelocator, new Class[] {
/* 219*/            org/glassfish/hk2/utilities/ImmediateContext, org/glassfish/hk2/internal/ImmediateHelper
                });
/* 220*/        return (ImmediateController)servicelocator.getService(org/glassfish/hk2/api/ImmediateController, new Annotation[0]);
            }

            public static transient void bind(ServiceLocator servicelocator, Binder abinder[])
            {
/* 232*/        servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 235*/        int i = (abinder = abinder).length;
/* 235*/        for(int j = 0; j < i; j++)
                {
                    Binder binder;
/* 235*/            (binder = abinder[j]).bind(servicelocator);
                }

/* 239*/        servicelocator.commit();
            }

            public static transient ServiceLocator bind(String s, Binder abinder[])
            {
                ServiceLocatorFactory servicelocatorfactory;
/* 252*/        bind(((ServiceLocator) (s = (servicelocatorfactory = ServiceLocatorFactory.getInstance()).create(s))), abinder);
/* 257*/        return s;
            }

            public static transient ServiceLocator bind(Binder abinder[])
            {
/* 269*/        return bind("default", abinder);
            }

            public static ActiveDescriptor addOneConstant(ServiceLocator servicelocator, Object obj)
            {
/* 285*/        if(servicelocator == null || obj == null)
/* 285*/            throw new IllegalArgumentException();
/* 287*/        else
/* 287*/            return addOneDescriptor(servicelocator, BuilderHelper.createConstantDescriptor(obj), false);
            }

            public static transient List addFactoryConstants(ServiceLocator servicelocator, Factory afactory[])
            {
/* 304*/        if(servicelocator == null)
/* 304*/            throw new IllegalArgumentException();
/* 306*/        DynamicConfiguration dynamicconfiguration = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 309*/        LinkedList linkedlist = new LinkedList();
                Factory afactory1[];
/* 310*/        int i = (afactory1 = afactory).length;
/* 310*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 310*/            if((obj = afactory1[j]) == null)
/* 312*/                throw new IllegalArgumentException((new StringBuilder("One of the factories in ")).append(Arrays.toString(afactory)).append(" is null").toString());
/* 317*/            obj = dynamicconfiguration.addActiveFactoryDescriptor(obj.getClass());
/* 318*/            linkedlist.add(obj);
                }

/* 322*/        dynamicconfiguration = servicelocator.createDynamicConfiguration();
/* 324*/        LinkedList linkedlist1 = new LinkedList();
/* 325*/        i = 0;
/* 326*/        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext(); linkedlist1.add(dynamicconfiguration.bind(servicelocator)))
                {
                    Object obj1;
/* 326*/            obj1 = (ActiveDescriptor)((FactoryDescriptors) (obj1 = (FactoryDescriptors)iterator.next())).getFactoryAsAFactory();
/* 328*/            servicelocator = BuilderHelper.createConstantDescriptor(servicelocator = afactory[i++]);
/* 331*/            DescriptorImpl descriptorimpl = new DescriptorImpl(((Descriptor) (obj1)));
/* 333*/            servicelocator = new FactoryDescriptorsImpl(servicelocator, descriptorimpl);
                }

/* 337*/        dynamicconfiguration.commit();
/* 339*/        return linkedlist1;
            }

            public static transient ActiveDescriptor addOneConstant(ServiceLocator servicelocator, Object obj, String s, Type atype[])
            {
/* 358*/        if(servicelocator == null || obj == null)
/* 358*/            throw new IllegalArgumentException();
/* 360*/        else
/* 360*/            return addOneDescriptor(servicelocator, BuilderHelper.createConstantDescriptor(obj, s, atype), false);
            }

            public static ActiveDescriptor addOneDescriptor(ServiceLocator servicelocator, Descriptor descriptor)
            {
/* 376*/        return addOneDescriptor(servicelocator, descriptor, true);
            }

            public static ActiveDescriptor addOneDescriptor(ServiceLocator servicelocator, Descriptor descriptor, boolean flag)
            {
/* 398*/        servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
                ActiveDescriptor activedescriptor;
/* 402*/        if(descriptor instanceof ActiveDescriptor)
                {
/* 403*/            if((activedescriptor = (ActiveDescriptor)descriptor).isReified())
/* 406*/                descriptor = servicelocator.addActiveDescriptor(activedescriptor, flag);
/* 409*/            else
/* 409*/                descriptor = servicelocator.bind(descriptor, flag);
                } else
                {
/* 414*/            descriptor = servicelocator.bind(descriptor, flag);
                }
/* 417*/        servicelocator.commit();
/* 419*/        return descriptor;
            }

            public static transient List addFactoryDescriptors(ServiceLocator servicelocator, FactoryDescriptors afactorydescriptors[])
            {
/* 431*/        return addFactoryDescriptors(servicelocator, true, afactorydescriptors);
            }

            public static transient List addFactoryDescriptors(ServiceLocator servicelocator, boolean flag, FactoryDescriptors afactorydescriptors[])
            {
/* 444*/        if(afactorydescriptors == null || servicelocator == null)
/* 444*/            throw new IllegalArgumentException();
/* 446*/        ArrayList arraylist = new ArrayList(afactorydescriptors.length);
/* 448*/        servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 451*/        int i = (afactorydescriptors = afactorydescriptors).length;
/* 451*/        for(int j = 0; j < i; j++)
                {
/* 451*/            FactoryDescriptors factorydescriptors = afactorydescriptors[j];
/* 452*/            factorydescriptors = servicelocator.bind(factorydescriptors, flag);
/* 454*/            arraylist.add(factorydescriptors);
                }

/* 457*/        servicelocator.commit();
/* 459*/        return arraylist;
            }

            public static transient List addClasses(ServiceLocator servicelocator, Class aclass[])
            {
/* 482*/        servicelocator = (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
/* 485*/        LinkedList linkedlist = new LinkedList();
/* 486*/        int i = (aclass = aclass).length;
/* 486*/        for(int j = 0; j < i; j++)
                {
/* 486*/            Object obj = aclass[j];
/* 487*/            if(org/glassfish/hk2/api/Factory.isAssignableFrom(((Class) (obj))))
                    {
/* 488*/                obj = servicelocator.addActiveFactoryDescriptor(((Class) (obj)));
/* 489*/                linkedlist.add((ActiveDescriptor)((FactoryDescriptors) (obj)).getFactoryAsAService());
/* 490*/                linkedlist.add((ActiveDescriptor)((FactoryDescriptors) (obj)).getFactoryAsAFactory());
                    } else
                    {
/* 493*/                obj = servicelocator.addActiveDescriptor(((Class) (obj)));
/* 494*/                linkedlist.add(obj);
                    }
                }

/* 498*/        servicelocator.commit();
/* 500*/        return linkedlist;
            }

            static String getBestContract(Descriptor descriptor)
            {
/* 504*/        String s = descriptor.getImplementation();
/* 505*/        if((descriptor = descriptor.getAdvertisedContracts()).contains(s))
/* 506*/            return s;
/* 508*/        if((descriptor = descriptor.iterator()).hasNext())
/* 508*/            return descriptor = (String)descriptor.next();
/* 512*/        else
/* 512*/            return s;
            }

            public static ActiveDescriptor findOneDescriptor(ServiceLocator servicelocator, Descriptor descriptor)
            {
/* 528*/        if(servicelocator == null || descriptor == null)
/* 528*/            throw new IllegalArgumentException();
                Object obj;
/* 530*/        if(descriptor.getServiceId() != null && descriptor.getLocatorId() != null && (obj = servicelocator.getBestDescriptor(BuilderHelper.createSpecificDescriptorFilter(descriptor))) != null)
/* 534*/            return ((ActiveDescriptor) (obj));
/* 540*/        if(descriptor instanceof DescriptorImpl)
/* 541*/            obj = (DescriptorImpl)descriptor;
/* 544*/        else
/* 544*/            obj = new DescriptorImpl(descriptor);
/* 547*/        String s = getBestContract(descriptor);
/* 548*/        descriptor = descriptor.getName();
/* 550*/        return servicelocator = servicelocator.getBestDescriptor(new IndexedFilter(((DescriptorImpl) (obj)), s, descriptor) {

                    public final boolean matches(Descriptor descriptor1)
                    {
/* 554*/                return di.equals(descriptor1);
                    }

                    public final String getAdvertisedContract()
                    {
/* 559*/                return contract;
                    }

                    public final String getName()
                    {
/* 564*/                return name;
                    }

                    final DescriptorImpl val$di;
                    final String val$contract;
                    final String val$name;

                    
                    {
/* 550*/                di = descriptorimpl;
/* 550*/                contract = s;
/* 550*/                name = s1;
/* 550*/                super();
                    }
        });
            }

            public static void removeOneDescriptor(ServiceLocator servicelocator, Descriptor descriptor)
            {
/* 584*/        removeOneDescriptor(servicelocator, descriptor, false);
            }

            public static void removeOneDescriptor(ServiceLocator servicelocator, Descriptor descriptor, boolean flag)
            {
/* 601*/        if(servicelocator == null || descriptor == null)
/* 601*/            throw new IllegalArgumentException();
                Object obj;
/* 603*/        obj = ((DynamicConfigurationService) (obj = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0]))).createDynamicConfiguration();
/* 606*/        if(descriptor.getLocatorId() != null && descriptor.getServiceId() != null)
                {
/* 607*/            descriptor = BuilderHelper.createSpecificDescriptorFilter(descriptor);
/* 609*/            ((DynamicConfiguration) (obj)).addUnbindFilter(descriptor);
/* 611*/            if(flag && !(descriptor = servicelocator.getDescriptors(descriptor)).isEmpty())
                    {
/* 614*/                servicelocator = new AliasFilter(descriptor);
/* 616*/                ((DynamicConfiguration) (obj)).addUnbindFilter(servicelocator);
                    }
/* 620*/            ((DynamicConfiguration) (obj)).commit();
/* 622*/            return;
                }
/* 627*/        if(descriptor instanceof DescriptorImpl)
/* 628*/            descriptor = (DescriptorImpl)descriptor;
/* 631*/        else
/* 631*/            descriptor = new DescriptorImpl(descriptor);
/* 634*/        descriptor = new Filter(descriptor) {

                    public final boolean matches(Descriptor descriptor1)
                    {
/* 638*/                return di.equals(descriptor1);
                    }

                    final DescriptorImpl val$di;

                    
                    {
/* 634*/                di = descriptorimpl;
/* 634*/                super();
                    }
        };
/* 643*/        ((DynamicConfiguration) (obj)).addUnbindFilter(descriptor);
/* 645*/        if(flag && !(servicelocator = servicelocator.getDescriptors(descriptor)).isEmpty())
                {
/* 648*/            servicelocator = new AliasFilter(servicelocator);
/* 650*/            ((DynamicConfiguration) (obj)).addUnbindFilter(servicelocator);
                }
/* 654*/        ((DynamicConfiguration) (obj)).commit();
            }

            public static void removeFilter(ServiceLocator servicelocator, Filter filter)
            {
/* 665*/        removeFilter(servicelocator, filter, false);
            }

            public static void removeFilter(ServiceLocator servicelocator, Filter filter, boolean flag)
            {
/* 678*/        if(servicelocator == null || filter == null)
/* 678*/            throw new IllegalArgumentException();
                Object obj;
/* 680*/        ((DynamicConfiguration) (obj = ((DynamicConfigurationService) (obj = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0]))).createDynamicConfiguration())).addUnbindFilter(filter);
/* 685*/        if(flag && !(servicelocator = servicelocator.getDescriptors(filter)).isEmpty())
                {
/* 688*/            servicelocator = new AliasFilter(servicelocator);
/* 690*/            ((DynamicConfiguration) (obj)).addUnbindFilter(servicelocator);
                }
/* 694*/        ((DynamicConfiguration) (obj)).commit();
            }

            public static Object getService(ServiceLocator servicelocator, String s)
            {
/* 707*/        if(servicelocator == null || s == null)
/* 707*/            throw new IllegalArgumentException();
/* 709*/        if((s = servicelocator.getBestDescriptor(BuilderHelper.createContractFilter(s))) == null)
/* 710*/            return null;
/* 712*/        else
/* 712*/            return servicelocator.getServiceHandle(s).getService();
            }

            public static Object getService(ServiceLocator servicelocator, Descriptor descriptor)
            {
/* 725*/        if(servicelocator == null || descriptor == null)
/* 725*/            throw new IllegalArgumentException();
                Long long1;
/* 727*/        if((long1 = descriptor.getLocatorId()) != null && long1.longValue() == servicelocator.getLocatorId() && (descriptor instanceof ActiveDescriptor))
/* 731*/            return servicelocator.getServiceHandle((ActiveDescriptor)descriptor).getService();
/* 735*/        if((descriptor = findOneDescriptor(servicelocator, descriptor)) == null)
/* 736*/            return null;
/* 738*/        else
/* 738*/            return servicelocator.getServiceHandle(descriptor).getService();
            }

            public static DynamicConfiguration createDynamicConfiguration(ServiceLocator servicelocator)
                throws IllegalStateException
            {
/* 753*/        if(servicelocator == null)
/* 753*/            throw new IllegalArgumentException();
/* 755*/        if((servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])) == null)
/* 756*/            throw new IllegalStateException();
/* 758*/        else
/* 758*/            return servicelocator.createDynamicConfiguration();
            }

            public static transient Object findOrCreateService(ServiceLocator servicelocator, Class class1, Annotation aannotation[])
                throws MultiException
            {
/* 777*/        if(servicelocator == null || class1 == null)
/* 777*/            throw new IllegalArgumentException();
/* 779*/        if((aannotation = servicelocator.getServiceHandle(class1, aannotation)) == null)
/* 781*/            return servicelocator.createAndInitialize(class1);
/* 784*/        else
/* 784*/            return aannotation.getService();
            }

            public static String getOneMetadataField(Descriptor descriptor, String s)
            {
/* 795*/        if((descriptor = (List)(descriptor = descriptor.getMetadata()).get(s)) == null || descriptor.isEmpty())
/* 797*/            return null;
/* 799*/        else
/* 799*/            return (String)descriptor.get(0);
            }

            public static String getOneMetadataField(ServiceHandle servicehandle, String s)
            {
/* 810*/        return getOneMetadataField(((Descriptor) (servicehandle.getActiveDescriptor())), s);
            }

            public static ServiceLocator createAndPopulateServiceLocator(String s)
                throws MultiException
            {
                Object obj;
/* 829*/        obj = ((DynamicConfigurationService) (obj = (DynamicConfigurationService)(s = ServiceLocatorFactory.getInstance().create(s)).getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0]))).getPopulator();
/* 835*/        try
                {
/* 835*/            ((Populator) (obj)).populate();
                }
                // Misplaced declaration of an exception variable
/* 837*/        catch(String s)
                {
/* 838*/            throw new MultiException(s);
                }
/* 841*/        return s;
            }

            public static ServiceLocator createAndPopulateServiceLocator()
            {
/* 854*/        return createAndPopulateServiceLocator(null);
            }

            public static void enableLookupExceptions(ServiceLocator servicelocator)
            {
/* 892*/        if(servicelocator == null)
/* 892*/            throw new IllegalArgumentException();
/* 894*/        if(servicelocator.getService(org/glassfish/hk2/utilities/RethrowErrorService, new Annotation[0]) != null)
                {
/* 894*/            return;
                } else
                {
/* 896*/            addClasses(servicelocator, new Class[] {
/* 896*/                org/glassfish/hk2/utilities/RethrowErrorService
                    });
/* 897*/            return;
                }
            }

            /**
             * @deprecated Method enableTopicDistribution is deprecated
             */

            public static void enableTopicDistribution(ServiceLocator servicelocator)
            {
/* 918*/        throw new AssertionError("ServiceLocatorUtilities.enableTopicDistribution method has been removed, use ExtrasUtilities.enableTopicDistribution");
            }

            public static void dumpAllDescriptors(ServiceLocator servicelocator)
            {
/* 927*/        dumpAllDescriptors(servicelocator, System.err);
            }

            public static void dumpAllDescriptors(ServiceLocator servicelocator, PrintStream printstream)
            {
/* 937*/        if(servicelocator == null || printstream == null)
/* 937*/            throw new IllegalArgumentException();
                ActiveDescriptor activedescriptor;
/* 939*/        for(servicelocator = (servicelocator = servicelocator.getDescriptors(BuilderHelper.allFilter())).iterator(); servicelocator.hasNext(); printstream.println(activedescriptor.toString()))
/* 941*/            activedescriptor = (ActiveDescriptor)servicelocator.next();

            }

            public static Singleton getSingletonAnnotation()
            {
/* 951*/        return SINGLETON;
            }

            public static PerLookup getPerLookupAnnotation()
            {
/* 958*/        return PER_LOOKUP;
            }

            public static PerThread getPerThreadAnnotation()
            {
/* 966*/        return PER_THREAD;
            }

            public static InheritableThread getInheritableThreadAnnotation()
            {
/* 975*/        return INHERITABLE_THREAD;
            }

            public static Immediate getImmediateAnnotation()
            {
/* 983*/        return IMMEDIATE;
            }

            private static final String DEFAULT_LOCATOR_NAME = "default";
            private static final Singleton SINGLETON = new SingletonImpl();
            private static final PerLookup PER_LOOKUP = new PerLookupImpl();
            private static final PerThread PER_THREAD = new PerThreadImpl();
            private static final InheritableThread INHERITABLE_THREAD = new InheritableThreadImpl();
            private static final Immediate IMMEDIATE = new ImmediateImpl();

}
