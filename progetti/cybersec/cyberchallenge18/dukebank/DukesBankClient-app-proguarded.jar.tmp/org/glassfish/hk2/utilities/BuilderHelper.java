// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BuilderHelper.java

package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.internal.*;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;
import org.jvnet.hk2.annotations.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, ServiceLocatorUtilities, DescriptorBuilder, ActiveDescriptorBuilder, 
//            AbstractActiveDescriptor

public class BuilderHelper
{

            public BuilderHelper()
            {
            }

            public static IndexedFilter createContractFilter(String s)
            {
/*  96*/        return new IndexedFilterImpl(s, null);
            }

            public static IndexedFilter createNameFilter(String s)
            {
/* 107*/        return new IndexedFilterImpl(null, s);
            }

            public static IndexedFilter createNameAndContractFilter(String s, String s1)
            {
/* 119*/        return new IndexedFilterImpl(s, s1);
            }

            public static IndexedFilter createTokenizedFilter(String s)
                throws IllegalArgumentException
            {
/* 152*/        if(s == null)
/* 152*/            throw new IllegalArgumentException("null passed to createTokenizedFilter");
/* 154*/        StringTokenizer stringtokenizer = new StringTokenizer(s, ";");
/* 156*/        String s1 = null;
/* 157*/        String s2 = null;
/* 158*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/* 160*/        boolean flag = true;
/* 161*/        if(s.startsWith(";"))
/* 162*/            flag = false;
/* 165*/        do
                {
/* 165*/            if(!stringtokenizer.hasMoreTokens())
/* 166*/                break;
/* 166*/            s = stringtokenizer.nextToken();
/* 167*/            if(flag)
                    {
/* 168*/                flag = false;
/* 170*/                if(s.length() > 0)
/* 171*/                    s1 = s;
                    } else
                    {
                        int i;
/* 174*/                if((i = s.indexOf('=')) < 0)
/* 176*/                    throw new IllegalArgumentException((new StringBuilder("No = character found in token ")).append(s).toString());
/* 179*/                String s5 = s.substring(0, i);
                        String s3;
/* 180*/                if((s3 = s.substring(i + 1)).length() <= 0)
/* 182*/                    throw new IllegalArgumentException((new StringBuilder("No value found in token ")).append(s).toString());
/* 185*/                if("name".equals(s5))
/* 186*/                    s2 = s3;
/* 188*/                else
/* 188*/                if("qualifier".equals(s5))
/* 189*/                    linkedhashset.add(s3);
/* 192*/                else
/* 192*/                    throw new IllegalArgumentException((new StringBuilder("Unknown key: ")).append(s5).toString());
                    }
                } while(true);
/* 197*/        s = s1;
/* 198*/        String s4 = s2;
/* 200*/        return new IndexedFilter(linkedhashset, s, s4) {

                    public final boolean matches(Descriptor descriptor)
                    {
/* 204*/                if(qualifiers.isEmpty())
/* 204*/                    return true;
/* 206*/                else
/* 206*/                    return descriptor.getQualifiers().containsAll(qualifiers);
                    }

                    public final String getAdvertisedContract()
                    {
/* 211*/                return fContract;
                    }

                    public final String getName()
                    {
/* 216*/                return fName;
                    }

                    public final String toString()
                    {
/* 221*/                String s6 = fContract != null ? fContract : "";
/* 222*/                String s7 = fName != null ? (new StringBuilder(";name=")).append(fName).toString() : "";
/* 224*/                StringBuffer stringbuffer = new StringBuffer();
                        String s8;
/* 225*/                for(Iterator iterator = qualifiers.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder(";qualifier=")).append(s8).toString()))
/* 225*/                    s8 = (String)iterator.next();

/* 229*/                return (new StringBuilder("TokenizedFilter(")).append(s6).append(s7).append(stringbuffer.toString()).append(")").toString();
                    }

                    final Set val$qualifiers;
                    final String val$fContract;
                    final String val$fName;

                    
                    {
/* 200*/                qualifiers = set;
/* 200*/                fContract = s;
/* 200*/                fName = s1;
/* 200*/                super();
                    }
        };
            }

            public static IndexedFilter createSpecificDescriptorFilter(Descriptor descriptor)
            {
/* 245*/        String s = ServiceLocatorUtilities.getBestContract(descriptor);
/* 247*/        String s1 = descriptor.getName();
/* 249*/        if(descriptor.getServiceId() == null)
/* 250*/            throw new IllegalArgumentException("The descriptor must have a specific service ID");
/* 253*/        if(descriptor.getLocatorId() == null)
/* 254*/            throw new IllegalArgumentException("The descriptor must have a specific locator ID");
/* 257*/        else
/* 257*/            return new SpecificFilterImpl(s, s1, descriptor.getServiceId().longValue(), descriptor.getLocatorId().longValue());
            }

            public static Filter allFilter()
            {
/* 270*/        return StarFilter.getDescriptorFilter();
            }

            public static DescriptorBuilder link(String s, boolean flag)
                throws IllegalArgumentException
            {
/* 287*/        if(s == null)
/* 287*/            throw new IllegalArgumentException();
/* 289*/        else
/* 289*/            return new DescriptorBuilderImpl(s, flag);
            }

            public static DescriptorBuilder link(String s)
                throws IllegalArgumentException
            {
/* 305*/        return link(s, true);
            }

            public static DescriptorBuilder link(Class class1, boolean flag)
                throws IllegalArgumentException
            {
/* 321*/        if(class1 == null)
/* 321*/            throw new IllegalArgumentException();
/* 323*/        else
/* 323*/            return class1 = link(class1.getName(), flag);
            }

            public static DescriptorBuilder link(Class class1)
                throws IllegalArgumentException
            {
/* 339*/        if(class1 == null)
                {
/* 339*/            throw new IllegalArgumentException();
                } else
                {
/* 341*/            boolean flag = org/glassfish/hk2/api/Factory.isAssignableFrom(class1);
/* 343*/            return class1 = link(class1, !flag);
                }
            }

            public static ActiveDescriptorBuilder activeLink(Class class1)
                throws IllegalArgumentException
            {
/* 361*/        if(class1 == null)
/* 361*/            throw new IllegalArgumentException();
/* 363*/        else
/* 363*/            return new ActiveDescriptorBuilderImpl(class1);
            }

            public static AbstractActiveDescriptor createConstantDescriptor(Object obj)
            {
/* 381*/        if(obj == null)
/* 381*/            throw new IllegalArgumentException();
                Object obj1;
                ContractsProvided contractsprovided;
/* 385*/        if((contractsprovided = (ContractsProvided)((Class) (obj1 = obj.getClass())).getAnnotation(org/jvnet/hk2/annotations/ContractsProvided)) != null)
                {
/* 389*/            obj1 = new HashSet();
                    Class aclass[];
/* 390*/            int i = (aclass = contractsprovided.value()).length;
/* 390*/            for(int j = 0; j < i; j++)
                    {
/* 390*/                Class class1 = aclass[j];
/* 391*/                ((Set) (obj1)).add(class1);
                    }

                } else
                {
/* 395*/            obj1 = ReflectionHelper.getAdvertisedTypesFromObject(obj, org/jvnet/hk2/annotations/Contract);
                }
/* 398*/        return createConstantDescriptor(obj, ReflectionHelper.getName(obj.getClass()), (Type[])((Set) (obj1)).toArray(new Type[((Set) (obj1)).size()]));
            }

            public static transient AbstractActiveDescriptor createConstantDescriptor(Object obj, String s, Type atype[])
            {
/* 418*/        if(obj == null)
/* 418*/            throw new IllegalArgumentException();
                Object obj1;
/* 420*/        Object obj2 = (obj1 = ReflectionHelper.getScopeAnnotationFromObject(obj)) != null ? ((Object) (((Annotation) (obj1)).annotationType())) : org/glassfish/hk2/api/PerLookup;
/* 425*/        Set set = ReflectionHelper.getQualifiersFromObject(obj);
/* 428*/        HashMap hashmap = new HashMap();
/* 429*/        if(obj1 != null)
/* 430*/            getMetadataValues(((Annotation) (obj1)), hashmap);
                Annotation annotation;
/* 433*/        for(obj1 = set.iterator(); ((Iterator) (obj1)).hasNext(); getMetadataValues(annotation = (Annotation)((Iterator) (obj1)).next(), hashmap));
/* 438*/        if(atype.length <= 0)
                {
/* 439*/            obj1 = ReflectionHelper.getAdvertisedTypesFromObject(obj, org/jvnet/hk2/annotations/Contract);
                } else
                {
/* 442*/            obj1 = new LinkedHashSet();
                    Type atype1[];
/* 444*/            atype = (atype1 = atype).length;
/* 444*/            for(int i = 0; i < atype; i++)
                    {
/* 444*/                Type type = atype1[i];
/* 445*/                ((Set) (obj1)).add(type);
                    }

                }
/* 449*/        Boolean boolean1 = null;
/* 450*/        if((atype = (UseProxy)obj.getClass().getAnnotation(org/glassfish/hk2/api/UseProxy)) != null)
/* 452*/            boolean1 = Boolean.valueOf(atype.value());
/* 455*/        Boolean boolean2 = null;
                Object obj3;
/* 456*/        if((obj3 = (ProxyForSameScope)obj.getClass().getAnnotation(org/glassfish/hk2/api/ProxyForSameScope)) != null)
/* 458*/            boolean2 = Boolean.valueOf(((ProxyForSameScope) (obj3)).value());
/* 461*/        atype = DescriptorVisibility.NORMAL;
/* 462*/        if((obj3 = (Visibility)obj.getClass().getAnnotation(org/glassfish/hk2/api/Visibility)) != null)
/* 464*/            atype = ((Visibility) (obj3)).value();
/* 467*/        obj3 = null;
                Service service;
/* 468*/        if((service = (Service)obj.getClass().getAnnotation(org/jvnet/hk2/annotations/Service)) != null)
/* 470*/            obj3 = service.analyzer();
/* 473*/        int j = 0;
                Rank rank;
/* 474*/        if((rank = (Rank)obj.getClass().getAnnotation(org/glassfish/hk2/api/Rank)) != null)
/* 476*/            j = rank.value();
/* 479*/        return new ConstantActiveDescriptor(obj, ((Set) (obj1)), ((Class) (obj2)), s, set, atype, boolean1, boolean2, ((String) (obj3)), hashmap, j);
            }

            public static DescriptorImpl createDescriptorFromClass(Class class1)
            {
/* 502*/        if(class1 == null)
/* 502*/            return new DescriptorImpl();
/* 504*/        Set set = ReflectionHelper.getContractsFromClass(class1, org/jvnet/hk2/annotations/Contract);
/* 505*/        String s = ReflectionHelper.getName(class1);
/* 506*/        String s1 = ReflectionHelper.getScopeFromClass(class1, ServiceLocatorUtilities.getPerLookupAnnotation()).annotationType().getName();
/* 507*/        Set set1 = ReflectionHelper.getQualifiersFromClass(class1);
/* 508*/        DescriptorType descriptortype = DescriptorType.CLASS;
/* 509*/        if(org/glassfish/hk2/api/Factory.isAssignableFrom(class1))
/* 510*/            descriptortype = DescriptorType.PROVIDE_METHOD;
/* 513*/        Boolean boolean1 = null;
                Object obj;
/* 514*/        if((obj = (UseProxy)class1.getAnnotation(org/glassfish/hk2/api/UseProxy)) != null)
/* 516*/            boolean1 = Boolean.valueOf(((UseProxy) (obj)).value());
/* 519*/        obj = null;
                Object obj1;
/* 520*/        if((obj1 = (ProxyForSameScope)class1.getAnnotation(org/glassfish/hk2/api/ProxyForSameScope)) != null)
/* 522*/            obj = Boolean.valueOf(((ProxyForSameScope) (obj1)).value());
/* 525*/        obj1 = DescriptorVisibility.NORMAL;
                Visibility visibility;
/* 526*/        if((visibility = (Visibility)class1.getAnnotation(org/glassfish/hk2/api/Visibility)) != null)
/* 528*/            obj1 = visibility.value();
/* 531*/        int i = 0;
                Rank rank;
/* 532*/        if((rank = (Rank)class1.getAnnotation(org/glassfish/hk2/api/Rank)) != null)
/* 534*/            i = rank.value();
/* 538*/        return new DescriptorImpl(set, s, s1, class1.getName(), new HashMap(), set1, descriptortype, ((DescriptorVisibility) (obj1)), null, i, boolean1, ((Boolean) (obj)), null, null, null);
            }

            public static DescriptorImpl deepCopyDescriptor(Descriptor descriptor)
            {
/* 563*/        return new DescriptorImpl(descriptor);
            }

            public static void getMetadataValues(Annotation annotation, Map map)
            {
/* 578*/        if(annotation == null || map == null)
/* 579*/            throw new IllegalArgumentException();
/* 582*/        Class class1 = annotation.annotationType();
                Method amethod[];
/* 583*/        int i = (amethod = amethod = (Method[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                    public final Method[] run()
                    {
/* 587*/                return annotationClass.getDeclaredMethods();
                    }

                    public final volatile Object run()
                    {
/* 583*/                return run();
                    }

                    final Class val$annotationClass;

                    
                    {
/* 583*/                annotationClass = class1;
/* 583*/                super();
                    }
        })).length;
/* 591*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
                    Object obj1;
/* 591*/            if((obj1 = (Metadata)((Method) (obj = amethod[j])).getAnnotation(org/glassfish/hk2/api/Metadata)) == null)
/* 595*/                continue;
/* 595*/            obj1 = ((Metadata) (obj1)).value();
/* 599*/            try
                    {
/* 599*/                obj = ReflectionHelper.invoke(annotation, ((Method) (obj)), new Object[0], false);
                    }
                    // Misplaced declaration of an exception variable
/* 601*/            catch(Object obj)
                    {
/* 602*/                throw new MultiException(((Throwable) (obj)));
                    }
/* 605*/            if(obj == null)
/* 608*/                continue;
/* 608*/            if(obj instanceof Class)
/* 609*/                obj = ((Class)obj).getName();
/* 611*/            else
/* 611*/            if(obj.getClass().isArray())
                    {
/* 612*/                int k = Array.getLength(obj);
/* 614*/                for(int l = 0; l < k; l++)
                        {
                            Object obj2;
/* 615*/                    if((obj2 = Array.get(obj, l)) == null)
/* 619*/                        continue;
/* 619*/                    if(obj2 instanceof Class)
                            {
/* 620*/                        obj2 = ((Class)obj2).getName();
/* 622*/                        ReflectionHelper.addMetadata(map, ((String) (obj1)), ((String) (obj2)));
                            } else
                            {
/* 625*/                        ReflectionHelper.addMetadata(map, ((String) (obj1)), obj2.toString());
                            }
                        }

/* 629*/                obj = null;
                    } else
                    {
/* 632*/                obj = obj.toString();
                    }
/* 635*/            if(obj != null)
/* 636*/                ReflectionHelper.addMetadata(map, ((String) (obj1)), ((String) (obj)));
                }

            }

            public static ServiceHandle createConstantServiceHandle(Object obj)
            {
/* 651*/        return new ServiceHandle(obj) {

                    public final Object getService()
                    {
/* 656*/                return obj;
                    }

                    public final ActiveDescriptor getActiveDescriptor()
                    {
/* 661*/                return null;
                    }

                    public final boolean isActive()
                    {
/* 666*/                return true;
                    }

                    public final void destroy()
                    {
                    }

                    public final synchronized void setServiceData(Object obj1)
                    {
/* 676*/                serviceData = obj1;
                    }

                    public final synchronized Object getServiceData()
                    {
/* 682*/                return serviceData;
                    }

                    private Object serviceData;
                    final Object val$obj;

                    
                    {
/* 651*/                obj = obj1;
/* 651*/                super();
                    }
        };
            }

            public static boolean filterMatches(Descriptor descriptor, Filter filter)
            {
/* 699*/        if(descriptor == null)
/* 699*/            throw new IllegalArgumentException();
/* 701*/        if(filter == null)
/* 701*/            return true;
/* 703*/        if(filter instanceof IndexedFilter)
                {
                    Object obj;
                    String s;
/* 704*/            if((s = ((IndexedFilter) (obj = (IndexedFilter)filter)).getAdvertisedContract()) != null && !descriptor.getAdvertisedContracts().contains(s))
/* 709*/                return false;
/* 713*/            if((obj = ((IndexedFilter) (obj)).getName()) != null)
                    {
/* 715*/                if(descriptor.getName() == null)
/* 715*/                    return false;
/* 717*/                if(!((String) (obj)).equals(descriptor.getName()))
/* 718*/                    return false;
                    }
                }
/* 725*/        return filter.matches(descriptor);
            }

            public static final String NAME_KEY = "name";
            public static final String QUALIFIER_KEY = "qualifier";
            public static final String TOKEN_SEPARATOR = ";";
}
