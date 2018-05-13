// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Providers.java

package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Feature;
import javax.ws.rs.ext.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.spi.Contract;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            CustomAnnotationLiteral, ProviderToService

public final class Providers
{
    static final class ProviderRuntime extends Enum
    {

                public static ProviderRuntime[] values()
                {
/* 137*/            return (ProviderRuntime[])$VALUES.clone();
                }

                public static ProviderRuntime valueOf(String s)
                {
/* 137*/            return (ProviderRuntime)Enum.valueOf(org/glassfish/jersey/internal/inject/Providers$ProviderRuntime, s);
                }

                public final RuntimeType getRuntime()
                {
/* 148*/            return runtime;
                }

                public static final ProviderRuntime BOTH;
                public static final ProviderRuntime SERVER;
                public static final ProviderRuntime CLIENT;
                private final RuntimeType runtime;
                private static final ProviderRuntime $VALUES[];

                static 
                {
/* 139*/            BOTH = new ProviderRuntime("BOTH", 0, null);
/* 139*/            SERVER = new ProviderRuntime("SERVER", 1, RuntimeType.SERVER);
/* 139*/            CLIENT = new ProviderRuntime("CLIENT", 2, RuntimeType.CLIENT);
/* 137*/            $VALUES = (new ProviderRuntime[] {
/* 137*/                BOTH, SERVER, CLIENT
                    });
                }

                private ProviderRuntime(String s, int i, RuntimeType runtimetype)
                {
/* 143*/            super(s, i);
/* 144*/            runtime = runtimetype;
                }
    }


            private static Map getJaxRsProviderInterfaces()
            {
                HashMap hashmap;
/*  98*/        (hashmap = new HashMap()).put(javax/ws/rs/ext/ContextResolver, ProviderRuntime.BOTH);
/* 101*/        hashmap.put(javax/ws/rs/ext/ExceptionMapper, ProviderRuntime.BOTH);
/* 102*/        hashmap.put(javax/ws/rs/ext/MessageBodyReader, ProviderRuntime.BOTH);
/* 103*/        hashmap.put(javax/ws/rs/ext/MessageBodyWriter, ProviderRuntime.BOTH);
/* 104*/        hashmap.put(javax/ws/rs/ext/ReaderInterceptor, ProviderRuntime.BOTH);
/* 105*/        hashmap.put(javax/ws/rs/ext/WriterInterceptor, ProviderRuntime.BOTH);
/* 106*/        hashmap.put(javax/ws/rs/ext/ParamConverterProvider, ProviderRuntime.BOTH);
/* 108*/        hashmap.put(javax/ws/rs/container/ContainerRequestFilter, ProviderRuntime.SERVER);
/* 109*/        hashmap.put(javax/ws/rs/container/ContainerResponseFilter, ProviderRuntime.SERVER);
/* 110*/        hashmap.put(javax/ws/rs/container/DynamicFeature, ProviderRuntime.SERVER);
/* 112*/        hashmap.put(javax/ws/rs/client/ClientResponseFilter, ProviderRuntime.CLIENT);
/* 113*/        hashmap.put(javax/ws/rs/client/ClientRequestFilter, ProviderRuntime.CLIENT);
/* 115*/        return hashmap;
            }

            private static Map getExternalProviderInterfaces()
            {
                HashMap hashmap;
/* 125*/        (hashmap = new HashMap()).putAll(JAX_RS_PROVIDER_INTERFACE_WHITELIST);
/* 129*/        hashmap.put(javax/ws/rs/core/Feature, ProviderRuntime.BOTH);
/* 132*/        hashmap.put(org/glassfish/hk2/utilities/Binder, ProviderRuntime.BOTH);
/* 134*/        return hashmap;
            }

            private Providers()
            {
            }

            public static Factory factoryOf(Object obj)
            {
/* 163*/        return new Factory(obj) {

                    public final Object provide()
                    {
/* 167*/                return instance;
                    }

                    public final void dispose(Object obj1)
                    {
                    }

                    final Object val$instance;

                    
                    {
/* 163*/                instance = obj;
/* 163*/                super();
                    }
        };
            }

            public static Set getProviders(ServiceLocator servicelocator, Class class1)
            {
/* 187*/        return getClasses(servicelocator = getServiceHandles(servicelocator, class1, new Annotation[0]));
            }

            public static Set getCustomProviders(ServiceLocator servicelocator, Class class1)
            {
/* 201*/        return getClasses(servicelocator = getServiceHandles(servicelocator, class1, new Annotation[] {
/* 201*/            CustomAnnotationLiteral.INSTANCE
                }));
            }

            public static Iterable getAllProviders(ServiceLocator servicelocator, Class class1)
            {
/* 215*/        return getAllProviders(servicelocator, class1, ((Comparator) (null)));
            }

            public static Iterable getAllRankedProviders(ServiceLocator servicelocator, Class class1)
            {
                List list;
/* 228*/        (list = getServiceHandles(servicelocator, class1, new Annotation[] {
/* 228*/            CustomAnnotationLiteral.INSTANCE
                })).addAll(getServiceHandles(servicelocator, class1, new Annotation[0]));
/* 231*/        servicelocator = new LinkedHashMap();
/* 234*/        class1 = list.iterator();
/* 234*/        do
                {
/* 234*/            if(!class1.hasNext())
/* 234*/                break;
                    ServiceHandle servicehandle;
/* 234*/            ActiveDescriptor activedescriptor = (servicehandle = (ServiceHandle)class1.next()).getActiveDescriptor();
/* 236*/            if(servicelocator.containsKey(activedescriptor))
/* 237*/                continue;
/* 237*/            Set set = activedescriptor.getContractTypes();
/* 238*/            Class class2 = activedescriptor.getImplementationClass();
/* 239*/            boolean flag = true;
/* 240*/            Iterator iterator = set.iterator();
/* 240*/            do
                    {
/* 240*/                if(!iterator.hasNext())
/* 240*/                    break;
                        Type type;
/* 240*/                if(!((Class)(type = (Type)iterator.next())).isAssignableFrom(class2))
/* 242*/                    continue;
/* 242*/                flag = false;
/* 243*/                break;
                    } while(true);
/* 246*/            servicelocator.put(activedescriptor, new RankedProvider(servicehandle.getService(), activedescriptor.getRanking(), flag ? set : null));
                } while(true);
/* 251*/        return servicelocator.values();
            }

            public static Iterable sortRankedProviders(RankedComparator rankedcomparator, Iterable iterable)
            {
/* 266*/        Collections.sort(iterable = Lists.newArrayList(iterable), rankedcomparator);
/* 270*/        return Collections2.transform(iterable, new Function() {

                    public final Object apply(RankedProvider rankedprovider)
                    {
/* 273*/                return rankedprovider.getProvider();
                    }

                    public final volatile Object apply(Object obj)
                    {
/* 270*/                return apply((RankedProvider)obj);
                    }

        });
            }

            public static Iterable mergeAndSortRankedProviders(RankedComparator rankedcomparator, Iterable iterable)
            {
/* 290*/        ArrayList arraylist = Lists.newArrayList();
                Iterable iterable1;
/* 292*/        for(iterable = iterable.iterator(); iterable.hasNext(); arraylist.addAll(Lists.newLinkedList(iterable1)))
/* 292*/            iterable1 = (Iterable)iterable.next();

/* 296*/        Collections.sort(arraylist, rankedcomparator);
/* 298*/        return Collections2.transform(arraylist, new Function() {

                    public final Object apply(RankedProvider rankedprovider)
                    {
/* 301*/                return rankedprovider.getProvider();
                    }

                    public final volatile Object apply(Object obj)
                    {
/* 298*/                return apply((RankedProvider)obj);
                    }

        });
            }

            public static Iterable getAllProviders(ServiceLocator servicelocator, Class class1, RankedComparator rankedcomparator)
            {
/* 320*/        return sortRankedProviders(rankedcomparator, getAllRankedProviders(servicelocator, class1));
            }

            public static Collection getAllServiceHandles(ServiceLocator servicelocator, Class class1)
            {
                List list;
/* 333*/        (list = getServiceHandles(servicelocator, class1, new Annotation[] {
/* 333*/            CustomAnnotationLiteral.INSTANCE
                })).addAll(getServiceHandles(servicelocator, class1, new Annotation[0]));
/* 336*/        servicelocator = new LinkedHashMap();
/* 339*/        class1 = list.iterator();
/* 339*/        do
                {
/* 339*/            if(!class1.hasNext())
/* 339*/                break;
                    ServiceHandle servicehandle;
/* 339*/            ActiveDescriptor activedescriptor = (servicehandle = (ServiceHandle)class1.next()).getActiveDescriptor();
/* 341*/            if(!servicelocator.containsKey(activedescriptor))
/* 342*/                servicelocator.put(activedescriptor, servicehandle);
                } while(true);
/* 346*/        return servicelocator.values();
            }

            private static transient List getServiceHandles(ServiceLocator servicelocator, Class class1, Annotation aannotation[])
            {
/* 352*/        servicelocator = aannotation != null ? ((ServiceLocator) (servicelocator.getAllServiceHandles(class1, aannotation))) : ((ServiceLocator) (servicelocator.getAllServiceHandles(class1, new Annotation[0])));
/* 356*/        class1 = new ArrayList();
/* 357*/        for(servicelocator = servicelocator.iterator(); servicelocator.hasNext(); class1.add(aannotation))
/* 357*/            aannotation = (ServiceHandle)servicelocator.next();

/* 361*/        return class1;
            }

            public static Iterable getAllProviders(ServiceLocator servicelocator, Class class1, Comparator comparator)
            {
/* 379*/        servicelocator = new ArrayList(getClasses(getAllServiceHandles(servicelocator, class1)));
/* 381*/        if(comparator != null)
/* 382*/            Collections.sort(servicelocator, comparator);
/* 385*/        return servicelocator;
            }

            private static Set getClasses(Collection collection)
            {
/* 389*/        if(collection.isEmpty())
/* 390*/            return Sets.newLinkedHashSet();
/* 392*/        else
/* 392*/            return Sets.newLinkedHashSet(Collections2.transform(collection, new ProviderToService()));
            }

            public static SortedSet getProviders(ServiceLocator servicelocator, Class class1, Comparator comparator)
            {
/* 410*/        if((servicelocator = getServiceHandles(servicelocator, class1, new Annotation[0])).isEmpty())
                {
/* 412*/            return Sets.newTreeSet(comparator);
                } else
                {
/* 414*/            (class1 = Sets.newTreeSet(comparator)).addAll(Collections2.transform(servicelocator, new ProviderToService()));
/* 416*/            return class1;
                }
            }

            public static Set getProviderContracts(Class class1)
            {
/* 429*/        Set set = Sets.newIdentityHashSet();
/* 430*/        computeProviderContracts(class1, set);
/* 431*/        return set;
            }

            private static void computeProviderContracts(Class class1, Set set)
            {
                Class class2;
/* 435*/        for(class1 = getImplementedContracts(class1).iterator(); class1.hasNext(); computeProviderContracts(class2, set))
/* 435*/            if(isSupportedContract(class2 = (Class)class1.next()))
/* 437*/                set.add(class2);

            }

            public static boolean checkProviderRuntime(Class class1, ContractProvider contractprovider, RuntimeType runtimetype, boolean flag, boolean flag1)
            {
                Object obj;
                StringBuilder stringbuilder;
/* 465*/        contractprovider = contractprovider.getContracts();
/* 466*/        obj = (obj = (ConstrainedTo)class1.getAnnotation(javax/ws/rs/ConstrainedTo)) != null ? ((Object) (((ConstrainedTo) (obj)).value())) : null;
/* 468*/        if(javax/ws/rs/core/Feature.isAssignableFrom(class1))
/* 469*/            return true;
/* 472*/        stringbuilder = new StringBuilder();
                boolean flag3;
/* 478*/        boolean flag2 = obj == null;
/* 479*/        flag3 = flag1 && runtimetype == RuntimeType.SERVER;
/* 480*/        contractprovider = contractprovider.iterator();
/* 480*/        do
                {
/* 480*/            if(!contractprovider.hasNext())
/* 480*/                break;
                    Class class2;
/* 480*/            RuntimeType runtimetype1 = getContractConstraint(class2 = (Class)contractprovider.next(), ((RuntimeType) (obj)));
/* 483*/            flag3 |= runtimetype1 == null || runtimetype1 == runtimetype;
/* 485*/            if(obj != null)
/* 486*/                if(runtimetype1 != obj)
/* 488*/                    stringbuilder.append(LocalizationMessages.WARNING_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(class1.getName(), ((RuntimeType) (obj)).name(), class2.getName(), runtimetype1.name())).append(" ");
/* 495*/                else
/* 495*/                    flag2 = true;
                } while(true);
/* 500*/        if(flag2)
/* 502*/            break MISSING_BLOCK_LABEL_254;
/* 502*/        stringbuilder.append(LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(class1.getName(), ((RuntimeType) (obj)).name())).append(" ");
/* 506*/        logProviderSkipped(stringbuilder, class1, flag1);
/* 536*/        if(stringbuilder.length() > 0)
/* 537*/            LOGGER.log(Level.WARNING, stringbuilder.toString());
/* 537*/        return false;
/* 512*/        if((contractprovider = obj != null && obj != runtimetype ? 0 : 1) == 0 && !flag)
                {
/* 515*/            stringbuilder.append(LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_WRONG_RUNTIME(class1.getName(), ((RuntimeType) (obj)).name(), runtimetype.name())).append(" ");
/* 521*/            logProviderSkipped(stringbuilder, class1, flag1);
                }
/* 525*/        if(flag3 || flag)
/* 526*/            break MISSING_BLOCK_LABEL_377;
/* 526*/        stringbuilder.append(LocalizationMessages.ERROR_PROVIDER_REGISTERED_WRONG_RUNTIME(class1.getName(), runtimetype.name())).append(" ");
/* 530*/        logProviderSkipped(stringbuilder, class1, flag1);
/* 536*/        if(stringbuilder.length() > 0)
/* 537*/            LOGGER.log(Level.WARNING, stringbuilder.toString());
/* 537*/        return false;
/* 534*/        boolean flag4 = contractprovider != 0 && flag3;
/* 536*/        if(stringbuilder.length() > 0)
/* 537*/            LOGGER.log(Level.WARNING, stringbuilder.toString());
/* 537*/        return flag4;
/* 536*/        class1;
/* 536*/        if(stringbuilder.length() > 0)
/* 537*/            LOGGER.log(Level.WARNING, stringbuilder.toString());
/* 537*/        throw class1;
            }

            private static void logProviderSkipped(StringBuilder stringbuilder, Class class1, boolean flag)
            {
/* 543*/        stringbuilder.append(flag ? LocalizationMessages.ERROR_PROVIDER_AND_RESOURCE_CONSTRAINED_TO_IGNORED(class1.getName()) : LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_IGNORED(class1.getName())).append(" ");
            }

            public static boolean isSupportedContract(Class class1)
            {
/* 555*/        return EXTERNAL_PROVIDER_INTERFACE_WHITELIST.get(class1) != null || class1.isAnnotationPresent(org/glassfish/jersey/spi/Contract);
            }

            private static RuntimeType getContractConstraint(Class class1, RuntimeType runtimetype)
            {
/* 559*/        ProviderRuntime providerruntime = (ProviderRuntime)EXTERNAL_PROVIDER_INTERFACE_WHITELIST.get(class1);
/* 561*/        RuntimeType runtimetype1 = null;
/* 562*/        if(providerruntime != null)
/* 563*/            runtimetype1 = providerruntime.getRuntime();
/* 564*/        else
/* 564*/        if(class1.getAnnotation(org/glassfish/jersey/spi/Contract) != null && (class1 = (ConstrainedTo)class1.getAnnotation(javax/ws/rs/ConstrainedTo)) != null)
/* 567*/            runtimetype1 = class1.value();
/* 571*/        if(runtimetype1 == null)
/* 571*/            return runtimetype;
/* 571*/        else
/* 571*/            return runtimetype1;
            }

            private static Iterable getImplementedContracts(Class class1)
            {
                LinkedList linkedlist;
/* 575*/        Collections.addAll(linkedlist = new LinkedList(), class1.getInterfaces());
/* 579*/        if((class1 = class1.getSuperclass()) != null)
/* 581*/            linkedlist.add(class1);
/* 584*/        return linkedlist;
            }

            public static boolean isProvider(Class class1)
            {
/* 595*/        return findFirstProviderContract(class1);
            }

            public static boolean isJaxRsProvider(Class class1)
            {
                Class class2;
/* 605*/        for(Iterator iterator = JAX_RS_PROVIDER_INTERFACE_WHITELIST.keySet().iterator(); iterator.hasNext();)
/* 605*/            if((class2 = (Class)iterator.next()).isAssignableFrom(class1))
/* 607*/                return true;

/* 610*/        return false;
            }

            public static transient void ensureContract(Class class1, Class aclass[])
            {
/* 622*/        if(aclass == null || aclass.length <= 0)
/* 623*/            return;
/* 626*/        StringBuilder stringbuilder = new StringBuilder();
/* 627*/        int i = (aclass = aclass).length;
/* 627*/        for(int j = 0; j < i; j++)
                {
/* 627*/            Class class2 = aclass[j];
/* 628*/            if(class1.isAssignableFrom(class2))
/* 629*/                continue;
/* 629*/            if(stringbuilder.length() > 0)
/* 630*/                stringbuilder.append(", ");
/* 632*/            stringbuilder.append(class2.getName());
                }

/* 636*/        if(stringbuilder.length() > 0)
/* 637*/            throw new IllegalArgumentException(LocalizationMessages.INVALID_SPI_CLASSES(class1.getName(), stringbuilder.toString()));
/* 642*/        else
/* 642*/            return;
            }

            public static void injectProviders(Iterable iterable, ServiceLocator servicelocator)
            {
                Object obj;
/* 652*/        for(iterable = iterable.iterator(); iterable.hasNext(); servicelocator.inject(obj))
/* 652*/            obj = iterable.next();

            }

            private static boolean findFirstProviderContract(Class class1)
            {
/* 659*/        for(class1 = getImplementedContracts(class1).iterator(); class1.hasNext();)
                {
                    Class class2;
/* 659*/            if(isSupportedContract(class2 = (Class)class1.next()))
/* 661*/                return true;
/* 663*/            if(findFirstProviderContract(class2))
/* 664*/                return true;
                }

/* 667*/        return false;
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/inject/Providers.getName());
            private static final Map JAX_RS_PROVIDER_INTERFACE_WHITELIST = getJaxRsProviderInterfaces();
            private static final Map EXTERNAL_PROVIDER_INTERFACE_WHITELIST = getExternalProviderInterfaces();

}
