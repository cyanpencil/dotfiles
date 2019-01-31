// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.inject.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;
import org.glassfish.hk2.utilities.reflection.*;
import org.jvnet.hk2.annotations.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            AnnotatedElementAnnotationInfo, AutoActiveDescriptor, ClazzCreator, Collector, 
//            ConstantActiveDescriptor, ErrorInformationImpl, ErrorResults, FactoryCreator, 
//            NarrowResults, PerLocatorUtilities, ProxyUtilities, ServiceHandleImpl, 
//            ServiceLocatorImpl, SystemInjecteeImpl, ThreeThirtyResolver

public class Utilities
{
    static class AnnotationInformation
    {

                private final Set qualifiers;
                private final boolean optional;
                private final boolean self;
                private final Unqualified unqualified;





                private AnnotationInformation(Set set, boolean flag, boolean flag1, Unqualified unqualified1)
                {
/*2228*/            qualifiers = set;
/*2229*/            optional = flag;
/*2230*/            self = flag1;
/*2231*/            unqualified = unqualified1;
                }

    }

    public static interface Interceptors
    {

        public abstract Map getMethodInterceptors();

        public abstract List getConstructorInterceptors();
    }


            public Utilities()
            {
            }

            public static ClassAnalyzer getClassAnalyzer(ServiceLocatorImpl servicelocatorimpl, String s, Collector collector)
            {
/* 164*/        return servicelocatorimpl.getAnalyzer(s, collector);
            }

            public static Constructor getConstructor(Class class1, ClassAnalyzer classanalyzer, Collector collector)
            {
                Constructor constructor;
/* 178*/        try
                {
/* 178*/            constructor = classanalyzer.getConstructor(class1);
                }
                // Misplaced declaration of an exception variable
/* 180*/        catch(Class class1)
                {
/* 181*/            collector.addMultiException(class1);
/* 182*/            return null;
                }
                // Misplaced declaration of an exception variable
/* 184*/        catch(Class class1)
                {
/* 185*/            collector.addThrowable(class1);
/* 186*/            return null;
                }
/* 189*/        if(constructor == null)
                {
/* 190*/            collector.addThrowable(new AssertionError((new StringBuilder("null return from getConstructor method of analyzer ")).append(classanalyzer).append(" for class ").append(class1.getName()).toString()));
/* 192*/            return constructor;
                } else
                {
/* 194*/            class1 = constructor;
/* 195*/            AccessController.doPrivileged(new PrivilegedAction(class1) {

                        public final Object run()
                        {
/* 199*/                    result.setAccessible(true);
/* 200*/                    return null;
                        }

                        final Constructor val$result;

                    
                    {
/* 195*/                result = constructor;
/* 195*/                super();
                    }
            });
/* 204*/            return constructor;
                }
            }

            public static Set getInitMethods(Class class1, ClassAnalyzer classanalyzer, Collector collector)
            {
                Set set;
/* 218*/        try
                {
/* 218*/            set = classanalyzer.getInitializerMethods(class1);
                }
                // Misplaced declaration of an exception variable
/* 220*/        catch(Class class1)
                {
/* 221*/            collector.addMultiException(class1);
/* 222*/            return Collections.emptySet();
                }
                // Misplaced declaration of an exception variable
/* 224*/        catch(Class class1)
                {
/* 225*/            collector.addThrowable(class1);
/* 226*/            return Collections.emptySet();
                }
/* 229*/        if(set == null)
                {
/* 230*/            collector.addThrowable(new AssertionError((new StringBuilder("null return from getInitializerMethods method of analyzer ")).append(classanalyzer).append(" for class ").append(class1.getName()).toString()));
/* 232*/            return Collections.emptySet();
                } else
                {
/* 235*/            return set;
                }
            }

            public static Set getInitFields(Class class1, ClassAnalyzer classanalyzer, Collector collector)
            {
                Set set;
/* 249*/        try
                {
/* 249*/            set = classanalyzer.getFields(class1);
                }
                // Misplaced declaration of an exception variable
/* 251*/        catch(Class class1)
                {
/* 252*/            collector.addMultiException(class1);
/* 253*/            return Collections.emptySet();
                }
                // Misplaced declaration of an exception variable
/* 255*/        catch(Class class1)
                {
/* 256*/            collector.addThrowable(class1);
/* 257*/            return Collections.emptySet();
                }
/* 260*/        if(set == null)
                {
/* 261*/            collector.addThrowable(new AssertionError((new StringBuilder("null return from getFields method of analyzer ")).append(classanalyzer).append(" for class ").append(class1.getName()).toString()));
/* 263*/            return Collections.emptySet();
                } else
                {
/* 266*/            return set;
                }
            }

            public static Method getPostConstruct(Class class1, ClassAnalyzer classanalyzer, Collector collector)
            {
/* 279*/        return classanalyzer.getPostConstructMethod(class1);
/* 281*/        class1;
/* 282*/        collector.addMultiException(class1);
/* 283*/        return null;
/* 285*/        class1;
/* 286*/        collector.addThrowable(class1);
/* 287*/        return null;
            }

            public static Method getPreDestroy(Class class1, ClassAnalyzer classanalyzer, Collector collector)
            {
/* 301*/        return classanalyzer.getPreDestroyMethod(class1);
/* 303*/        class1;
/* 304*/        collector.addMultiException(class1);
/* 305*/        return null;
/* 307*/        class1;
/* 308*/        collector.addThrowable(class1);
/* 309*/        return null;
            }

            public static Class getFactoryAwareImplementationClass(ActiveDescriptor activedescriptor)
            {
/* 323*/        if(activedescriptor.getDescriptorType().equals(DescriptorType.CLASS))
/* 324*/            return activedescriptor.getImplementationClass();
/* 327*/        else
/* 327*/            return getFactoryProductionClass(activedescriptor.getImplementationClass());
            }

            public static void checkLookupType(Class class1)
            {
/* 336*/        if(!class1.isAnnotation())
/* 336*/            return;
/* 340*/        if(class1.isAnnotationPresent(javax/inject/Scope))
/* 340*/            return;
/* 341*/        if(class1.isAnnotationPresent(javax/inject/Qualifier))
/* 341*/            return;
/* 343*/        else
/* 343*/            throw new IllegalArgumentException((new StringBuilder("Lookup type ")).append(class1).append(" must be a scope or annotation").toString());
            }

            public static Class translatePrimitiveType(Class class1)
            {
                Class class2;
/* 353*/        if((class2 = (Class)Constants.PRIMITIVE_MAP.get(class1)) == null)
/* 354*/            return class1;
/* 355*/        else
/* 355*/            return class2;
            }

            public static void handleErrors(NarrowResults narrowresults, LinkedList linkedlist)
            {
                Collector collector;
/* 365*/        collector = new Collector();
/* 366*/        narrowresults = narrowresults.getErrors().iterator();
_L4:
                ErrorResults errorresults;
                Iterator iterator;
/* 366*/        if(!narrowresults.hasNext())
/* 366*/            break MISSING_BLOCK_LABEL_158;
/* 366*/        errorresults = (ErrorResults)narrowresults.next();
/* 367*/        iterator = linkedlist.iterator();
_L2:
                Object obj;
/* 367*/        if(!iterator.hasNext())
/* 367*/            break; /* Loop/switch isn't completed */
/* 367*/        obj = (ErrorService)iterator.next();
/* 369*/        ((ErrorService) (obj)).onFailure(new ErrorInformationImpl(ErrorType.FAILURE_TO_REIFY, errorresults.getDescriptor(), errorresults.getInjectee(), errorresults.getMe()));
/* 380*/        continue; /* Loop/switch isn't completed */
/* 374*/        JVM INSTR dup ;
/* 375*/        obj;
/* 375*/        getErrors();
/* 375*/        iterator();
/* 375*/        obj;
/* 375*/        while(((Iterator) (obj)).hasNext()) 
                {
/* 375*/            Throwable throwable1 = (Throwable)((Iterator) (obj)).next();
/* 376*/            collector.addThrowable(throwable1);
                }
/* 377*/        continue; /* Loop/switch isn't completed */
                Throwable throwable;
/* 378*/        throwable;
/* 379*/        collector.addThrowable(throwable);
/* 381*/        if(true) goto _L2; else goto _L1
_L1:
/* 382*/        if(true) goto _L4; else goto _L3
_L3:
/* 384*/        collector.throwIfErrors();
/* 385*/        return;
            }

            public static Class loadClass(String s, Descriptor descriptor, Collector collector)
            {
/* 397*/        if((descriptor = descriptor.getLoader()) != null)
/* 399*/            break MISSING_BLOCK_LABEL_39;
/* 399*/        if((descriptor = org/jvnet/hk2/internal/Utilities.getClassLoader()) == null)
/* 401*/            descriptor = ClassLoader.getSystemClassLoader();
/* 405*/        return descriptor.loadClass(s);
/* 406*/        s;
/* 407*/        collector.addThrowable(s);
/* 408*/        return null;
/* 413*/        return descriptor.loadClass(s);
/* 414*/        JVM INSTR dup ;
/* 415*/        descriptor;
/* 415*/        JVM INSTR instanceof #117 <Class MultiException>;
/* 415*/        JVM INSTR ifeq 100;
                   goto _L1 _L2
_L1:
/* 416*/        break MISSING_BLOCK_LABEL_55;
_L2:
/* 416*/        break MISSING_BLOCK_LABEL_100;
/* 416*/        for(s = (s = (MultiException)descriptor).getErrors().iterator(); s.hasNext(); collector.addThrowable(descriptor))
/* 418*/            descriptor = (Throwable)s.next();

/* 420*/        break MISSING_BLOCK_LABEL_105;
/* 422*/        collector.addThrowable(descriptor);
/* 425*/        return null;
            }

            public static Class loadClass(String s, Injectee injectee)
            {
                AnnotatedElement annotatedelement;
/* 440*/        if(injectee != null)
                {
/* 441*/            if((annotatedelement = injectee.getParent()) instanceof Constructor)
/* 444*/                injectee = ((Constructor)annotatedelement).getDeclaringClass().getClassLoader();
/* 445*/            else
/* 445*/            if(annotatedelement instanceof Method)
/* 446*/                injectee = ((Method)annotatedelement).getDeclaringClass().getClassLoader();
/* 447*/            else
/* 447*/            if(annotatedelement instanceof Field)
/* 448*/                injectee = ((Field)annotatedelement).getDeclaringClass().getClassLoader();
/* 450*/            else
/* 450*/                injectee = injectee.getClass().getClassLoader();
                } else
                {
/* 453*/            injectee = org/jvnet/hk2/internal/Utilities.getClassLoader();
                }
/* 457*/        return injectee.loadClass(s);
                Throwable throwable;
/* 458*/        throwable;
/* 459*/        if((injectee = Thread.currentThread().getContextClassLoader()) == null)
/* 462*/            break MISSING_BLOCK_LABEL_132;
/* 462*/        return injectee.loadClass(s);
/* 464*/        s;
/* 465*/        (injectee = new MultiException(throwable)).addError(s);
/* 468*/        throw injectee;
/* 472*/        throw new MultiException(throwable);
            }

            public static Class getInjectionResolverType(ActiveDescriptor activedescriptor)
            {
/* 485*/        for(activedescriptor = activedescriptor.getContractTypes().iterator(); activedescriptor.hasNext();)
                {
                    Type type;
/* 485*/            Class class1 = ReflectionHelper.getRawClass(type = (Type)activedescriptor.next());
/* 488*/            if(org/glassfish/hk2/api/InjectionResolver.equals(class1))
                    {
/* 491*/                if(!(type instanceof ParameterizedType))
/* 492*/                    return null;
/* 495*/                if(!((activedescriptor = ReflectionHelper.getFirstTypeArgument(type)) instanceof Class))
/* 497*/                    return null;
/* 500*/                activedescriptor = (Class)activedescriptor;
/* 502*/                if(!java/lang/annotation/Annotation.isAssignableFrom(activedescriptor))
/* 503*/                    return null;
/* 506*/                else
/* 506*/                    return activedescriptor;
                    }
                }

/* 509*/        return null;
            }

            private static Class getFactoryProductionClass(Class class1)
            {
                Object obj;
/* 522*/        if((obj = ReflectionHelper.getRawClass(((Type) (obj = getFactoryProductionType(class1))))) != null)
/* 525*/            return ((Class) (obj));
/* 527*/        else
/* 527*/            throw new MultiException(new AssertionError((new StringBuilder("Could not find true produced type of factory ")).append(class1.getName()).toString()));
            }

            public static Type getFactoryProductionType(Class class1)
            {
/* 539*/        return class1 = (class1 = (ParameterizedType)(class1 = ReflectionHelper.getTypeClosure(class1, Collections.singleton(org/glassfish/hk2/api/Factory.getName()))).iterator().next()).getActualTypeArguments()[0];
            }

            public static void checkFactoryType(Class class1, Collector collector)
            {
                Type atype[];
/* 556*/        int i = (atype = class1.getGenericInterfaces()).length;
/* 556*/        for(int j = 0; j < i; j++)
                {
                    Type type;
                    Class class2;
/* 556*/            if((class2 = ReflectionHelper.getRawClass(type = atype[j])) != null && org/glassfish/hk2/api/Factory.equals(class2) && ((type = ReflectionHelper.getFirstTypeArgument(type)) instanceof WildcardType))
/* 566*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("The class ")).append(Pretty.clazz(class1)).append(" has a Wildcard as its type").toString()));
                }

            }

            private static boolean hasContract(Class class1)
            {
/* 574*/        if(class1 == null)
/* 574*/            return false;
/* 577*/        if(class1.isAnnotationPresent(org/jvnet/hk2/annotations/Contract))
/* 577*/            return true;
/* 579*/        int i = (class1 = class1.getAnnotations()).length;
/* 579*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 579*/            if((obj = class1[j]).annotationType().isAnnotationPresent(org/glassfish/hk2/api/ContractIndicator))
/* 581*/                return true;
                }

/* 585*/        return false;
            }

            private static Set getAutoAdvertisedTypes(Type type)
            {
                LinkedHashSet linkedhashset;
/* 589*/        (linkedhashset = new LinkedHashSet()).add(type);
                Object obj;
/* 592*/        if((obj = ReflectionHelper.getRawClass(type)) == null)
/* 593*/            return linkedhashset;
/* 595*/        if((obj = (ContractsProvided)((Class) (obj)).getAnnotation(org/jvnet/hk2/annotations/ContractsProvided)) != null)
                {
/* 599*/            linkedhashset.clear();
/* 601*/            int i = (type = ((ContractsProvided) (obj)).value()).length;
/* 601*/            for(int j = 0; j < i; j++)
                    {
/* 601*/                Object obj1 = type[j];
/* 602*/                linkedhashset.add(obj1);
                    }

/* 605*/            return linkedhashset;
                }
/* 608*/        i = (type = ReflectionHelper.getAllTypes(type)).iterator();
/* 609*/        do
                {
/* 609*/            if(!i.hasNext())
/* 609*/                break;
                    Type type1;
/* 609*/            if(hasContract(ReflectionHelper.getRawClass(type1 = (Type)i.next())))
/* 611*/                linkedhashset.add(type1);
                } while(true);
/* 618*/        return linkedhashset;
            }

            public static AutoActiveDescriptor createAutoDescriptor(Class class1, ServiceLocatorImpl servicelocatorimpl)
                throws MultiException, IllegalArgumentException, IllegalStateException
            {
/* 634*/        if(class1 == null)
/* 634*/            throw new IllegalArgumentException();
/* 636*/        Collector collector = new Collector();
/* 643*/        Boolean boolean1 = null;
/* 644*/        Boolean boolean2 = null;
/* 646*/        Object obj2 = null;
/* 649*/        Object obj1 = null;
                Object obj;
/* 650*/        if((obj = (Service)class1.getAnnotation(org/jvnet/hk2/annotations/Service)) != null)
                {
/* 652*/            if(!"".equals(((Service) (obj)).name()))
/* 653*/                obj1 = ((Service) (obj)).name();
/* 655*/            if(!"".equals(((Service) (obj)).metadata()))
/* 656*/                obj2 = ((Service) (obj)).metadata();
                }
/* 660*/        String s = ReflectionHelper.getNameFromAllQualifiers(((Set) (obj = ReflectionHelper.getQualifierAnnotations(class1))), class1);
/* 663*/        if(obj1 != null && s != null)
                {
/* 665*/            if(!((String) (obj1)).equals(s))
/* 666*/                throw new IllegalArgumentException((new StringBuilder("The class ")).append(class1.getName()).append(" has an @Service name of ").append(((String) (obj1))).append(" and has an @Named value of ").append(s).append(" which names do not match").toString());
                } else
/* 670*/        if(s == null && obj1 != null)
/* 671*/            s = ((String) (obj1));
/* 674*/        obj = getAllQualifiers(class1, s, collector);
/* 676*/        obj1 = getAutoAdvertisedTypes(class1);
                ScopeInfo scopeinfo;
/* 677*/        Class class2 = (scopeinfo = getScopeInfo(class1, null, collector)).getAnnoType();
/* 679*/        String s1 = servicelocatorimpl.getPerLocatorUtilities().getAutoAnalyzerName(class1);
/* 681*/        servicelocatorimpl = new ClazzCreator(servicelocatorimpl, class1);
/* 683*/        HashMap hashmap = new HashMap();
/* 684*/        if(obj2 != null)
/* 686*/            try
                    {
/* 686*/                ReflectionHelper.readMetadataMap(((String) (obj2)), hashmap);
                    }
/* 688*/            catch(IOException _ex)
                    {
/* 691*/                hashmap.clear();
/* 693*/                ReflectionHelper.parseServiceMetadataString(((String) (obj2)), hashmap);
                    }
/* 697*/        collector.throwIfErrors();
/* 699*/        if(scopeinfo.getScope() != null)
/* 700*/            BuilderHelper.getMetadataValues(scopeinfo.getScope(), hashmap);
                Annotation annotation;
/* 703*/        for(obj2 = ((Set) (obj)).iterator(); ((Iterator) (obj2)).hasNext(); BuilderHelper.getMetadataValues(annotation = (Annotation)((Iterator) (obj2)).next(), hashmap));
/* 707*/        if((obj2 = (UseProxy)class1.getAnnotation(org/glassfish/hk2/api/UseProxy)) != null)
/* 709*/            boolean1 = Boolean.valueOf(((UseProxy) (obj2)).value());
                Object obj3;
/* 712*/        if((obj3 = (ProxyForSameScope)class1.getAnnotation(org/glassfish/hk2/api/ProxyForSameScope)) != null)
/* 714*/            boolean2 = Boolean.valueOf(((ProxyForSameScope) (obj3)).value());
/* 717*/        obj2 = DescriptorVisibility.NORMAL;
/* 718*/        if((obj3 = (Visibility)class1.getAnnotation(org/glassfish/hk2/api/Visibility)) != null)
/* 720*/            obj2 = ((Visibility) (obj3)).value();
/* 723*/        int i = 0;
                Rank rank;
/* 724*/        if((rank = (Rank)class1.getAnnotation(org/glassfish/hk2/api/Rank)) != null)
/* 726*/            i = rank.value();
/* 729*/        (class1 = new AutoActiveDescriptor(class1, servicelocatorimpl, ((Set) (obj1)), class2, s, ((Set) (obj)), ((DescriptorVisibility) (obj2)), i, boolean1, boolean2, s1, hashmap, DescriptorType.CLASS)).setScopeAsAnnotation(scopeinfo.getScope());
/* 746*/        servicelocatorimpl.initialize(class1, s1, collector);
/* 748*/        collector.throwIfErrors();
/* 750*/        return class1;
            }

            public static AutoActiveDescriptor createAutoFactoryDescriptor(Class class1, ActiveDescriptor activedescriptor, ServiceLocatorImpl servicelocatorimpl)
                throws MultiException, IllegalArgumentException, IllegalStateException
            {
/* 766*/        if(class1 == null)
/* 766*/            throw new IllegalArgumentException();
/* 768*/        Collector collector = new Collector();
/* 770*/        Object obj = getFactoryProductionType(class1);
                Object obj1;
/* 772*/        if((obj1 = getFactoryProvideMethod(class1)) == null)
                {
/* 774*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("Could not find the provide method on the class ")).append(class1.getName()).toString()));
/* 777*/            collector.throwIfErrors();
                }
/* 785*/        Boolean boolean1 = null;
/* 786*/        Boolean boolean2 = null;
                Set set;
/* 788*/        String s = ReflectionHelper.getNameFromAllQualifiers(set = ReflectionHelper.getQualifierAnnotations(((AnnotatedElement) (obj1))), ((AnnotatedElement) (obj1)));
/* 791*/        obj = getAutoAdvertisedTypes(((Type) (obj)));
                ScopeInfo scopeinfo;
/* 792*/        Class class2 = (scopeinfo = getScopeInfo(((AnnotatedElement) (obj1)), null, collector)).getAnnoType();
/* 795*/        class1 = new FactoryCreator(servicelocatorimpl, activedescriptor);
/* 797*/        collector.throwIfErrors();
/* 799*/        servicelocatorimpl = new HashMap();
/* 800*/        if(scopeinfo.getScope() != null)
/* 801*/            BuilderHelper.getMetadataValues(scopeinfo.getScope(), servicelocatorimpl);
                Annotation annotation;
/* 804*/        for(Iterator iterator = set.iterator(); iterator.hasNext(); BuilderHelper.getMetadataValues(annotation = (Annotation)iterator.next(), servicelocatorimpl));
                Object obj2;
/* 808*/        if((obj2 = (UseProxy)((Method) (obj1)).getAnnotation(org/glassfish/hk2/api/UseProxy)) != null)
/* 810*/            boolean1 = Boolean.valueOf(((UseProxy) (obj2)).value());
                Object obj3;
/* 813*/        if((obj3 = (ProxyForSameScope)((Method) (obj1)).getAnnotation(org/glassfish/hk2/api/ProxyForSameScope)) != null)
/* 815*/            boolean2 = Boolean.valueOf(((ProxyForSameScope) (obj3)).value());
/* 818*/        obj2 = DescriptorVisibility.NORMAL;
/* 819*/        if((obj3 = (Visibility)((Method) (obj1)).getAnnotation(org/glassfish/hk2/api/Visibility)) != null)
/* 821*/            obj2 = ((Visibility) (obj3)).value();
/* 824*/        int i = 0;
/* 825*/        if((obj1 = (Rank)((Method) (obj1)).getAnnotation(org/glassfish/hk2/api/Rank)) != null)
/* 827*/            i = ((Rank) (obj1)).value();
/* 830*/        (class1 = new AutoActiveDescriptor(activedescriptor.getImplementationClass(), class1, ((Set) (obj)), class2, s, set, ((DescriptorVisibility) (obj2)), i, boolean1, boolean2, null, servicelocatorimpl, DescriptorType.PROVIDE_METHOD)).setScopeAsAnnotation(scopeinfo.getScope());
/* 847*/        collector.throwIfErrors();
/* 849*/        return class1;
            }

            public static void justPreDestroy(Object obj, ServiceLocatorImpl servicelocatorimpl, String s)
            {
/* 860*/        if(obj == null)
/* 860*/            throw new IllegalArgumentException();
/* 862*/        Collector collector = new Collector();
/* 864*/        s = getClassAnalyzer(servicelocatorimpl, s, collector);
/* 865*/        collector.throwIfErrors();
/* 867*/        collector.throwIfErrors();
                Class class1;
/* 869*/        s = getPreDestroy(class1 = obj.getClass(), s, collector);
/* 873*/        collector.throwIfErrors();
/* 875*/        if(s == null)
/* 875*/            return;
/* 878*/        try
                {
/* 878*/            ReflectionHelper.invoke(obj, s, new Object[0], servicelocatorimpl.getNeutralContextClassLoader());
/* 881*/            return;
                }
                // Misplaced declaration of an exception variable
/* 879*/        catch(Object obj)
                {
/* 880*/            throw new MultiException(((Throwable) (obj)));
                }
            }

            public static void justPostConstruct(Object obj, ServiceLocatorImpl servicelocatorimpl, String s)
            {
/* 892*/        if(obj == null)
/* 892*/            throw new IllegalArgumentException();
/* 894*/        Collector collector = new Collector();
/* 896*/        s = getClassAnalyzer(servicelocatorimpl, s, collector);
/* 897*/        collector.throwIfErrors();
                Class class1;
/* 899*/        s = getPostConstruct(class1 = obj.getClass(), s, collector);
/* 903*/        collector.throwIfErrors();
/* 905*/        if(s == null)
/* 905*/            return;
/* 908*/        try
                {
/* 908*/            ReflectionHelper.invoke(obj, s, new Object[0], servicelocatorimpl.getNeutralContextClassLoader());
/* 911*/            return;
                }
                // Misplaced declaration of an exception variable
/* 909*/        catch(Object obj)
                {
/* 910*/            throw new MultiException(((Throwable) (obj)));
                }
            }

            public static void justInject(Object obj, ServiceLocatorImpl servicelocatorimpl, String s)
            {
/* 921*/        if(obj == null)
/* 921*/            throw new IllegalArgumentException();
/* 923*/        Collector collector = new Collector();
/* 925*/        s = getClassAnalyzer(servicelocatorimpl, s, collector);
/* 926*/        collector.throwIfErrors();
                Class class1;
/* 928*/        Object obj1 = getInitFields(class1 = obj.getClass(), s, collector);
/* 931*/        s = getInitMethods(class1, s, collector);
/* 933*/        collector.throwIfErrors();
/* 935*/        for(obj1 = ((Set) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
                {
/* 935*/            Field field = (Field)((Iterator) (obj1)).next();
/* 936*/            Object obj2 = servicelocatorimpl.getPerLocatorUtilities().getInjectionResolver(servicelocatorimpl, field);
/* 938*/            List list1 = getFieldInjectees(class1, field, null);
/* 940*/            validateSelfInjectees(null, list1, collector);
/* 941*/            collector.throwIfErrors();
/* 943*/            Injectee injectee = (Injectee)list1.get(0);
/* 945*/            obj2 = ((InjectionResolver) (obj2)).resolve(injectee, null);
/* 948*/            try
                    {
/* 948*/                ReflectionHelper.setField(field, obj, obj2);
                    }
                    // Misplaced declaration of an exception variable
/* 950*/            catch(String s)
                    {
/* 951*/                throw new MultiException(s);
                    }
                }

/* 955*/        for(Iterator iterator = s.iterator(); iterator.hasNext();)
                {
/* 955*/            Method method = (Method)iterator.next();
/* 956*/            List list = getMethodInjectees(class1, method, null);
/* 958*/            validateSelfInjectees(null, list, collector);
/* 959*/            collector.throwIfErrors();
/* 961*/            Object aobj[] = new Object[list.size()];
/* 963*/            for(Iterator iterator1 = list.iterator(); iterator1.hasNext();)
                    {
/* 963*/                SystemInjecteeImpl systeminjecteeimpl = (SystemInjecteeImpl)iterator1.next();
/* 964*/                s = servicelocatorimpl.getPerLocatorUtilities().getInjectionResolver(servicelocatorimpl, systeminjecteeimpl);
/* 965*/                aobj[systeminjecteeimpl.getPosition()] = s.resolve(systeminjecteeimpl, null);
                    }

/* 969*/            try
                    {
/* 969*/                ReflectionHelper.invoke(obj, method, aobj, servicelocatorimpl.getNeutralContextClassLoader());
                    }
/* 970*/            catch(Throwable throwable)
                    {
/* 971*/                throw new MultiException(throwable);
                    }
                }

            }

            public static Object justCreate(Class class1, ServiceLocatorImpl servicelocatorimpl, String s)
            {
                Object aobj[];
/* 986*/        if(class1 == null)
/* 986*/            throw new IllegalArgumentException();
/* 988*/        Collector collector = new Collector();
/* 989*/        s = getClassAnalyzer(servicelocatorimpl, s, collector);
/* 990*/        collector.throwIfErrors();
/* 992*/        class1 = getConstructor(class1, s, collector);
/* 994*/        collector.throwIfErrors();
/* 996*/        s = getConstructorInjectees(class1, null);
/* 998*/        validateSelfInjectees(null, s, collector);
/* 999*/        collector.throwIfErrors();
/*1001*/        aobj = new Object[s.size()];
/*1003*/        for(s = s.iterator(); s.hasNext();)
                {
/*1003*/            SystemInjecteeImpl systeminjecteeimpl = (SystemInjecteeImpl)s.next();
/*1004*/            InjectionResolver injectionresolver = servicelocatorimpl.getPerLocatorUtilities().getInjectionResolver(servicelocatorimpl, systeminjecteeimpl);
/*1005*/            aobj[systeminjecteeimpl.getPosition()] = injectionresolver.resolve(systeminjecteeimpl, null);
                }

/*1009*/        return ReflectionHelper.makeMe(class1, aobj, servicelocatorimpl.getNeutralContextClassLoader());
/*1010*/        s;
/*1011*/        throw new MultiException(s);
            }

            public static Class[] getInterfacesForProxy(Set set)
            {
                LinkedList linkedlist;
/*1021*/        (linkedlist = new LinkedList()).add(org/glassfish/hk2/api/ProxyCtl);
/*1024*/        set = set.iterator();
/*1024*/        do
                {
/*1024*/            if(!set.hasNext())
/*1024*/                break;
                    Object obj;
/*1024*/            if((obj = ReflectionHelper.getRawClass(((Type) (obj = (Type)set.next())))) != null && ((Class) (obj)).isInterface())
/*1029*/                linkedlist.add(obj);
                } while(true);
/*1032*/        return (Class[])linkedlist.toArray(new Class[linkedlist.size()]);
            }

            public static boolean isProxiableScope(Class class1)
            {
/*1042*/        return class1.isAnnotationPresent(org/glassfish/hk2/api/Proxiable);
            }

            public static boolean isUnproxiableScope(Class class1)
            {
/*1052*/        return class1.isAnnotationPresent(org/glassfish/hk2/api/Unproxiable);
            }

            private static boolean isProxiable(ActiveDescriptor activedescriptor, Injectee injectee)
            {
                Object obj;
/*1066*/        if((obj = activedescriptor.isProxiable()) != null)
                {
/*1069*/            if(injectee == null)
/*1071*/                return ((Boolean) (obj)).booleanValue();
/*1074*/            if(!((Boolean) (obj)).booleanValue())
/*1076*/                return false;
/*1079*/            if((obj = injectee.getInjecteeDescriptor()) == null)
/*1082*/                return true;
/*1085*/            if((injectee = activedescriptor.isProxyForSameScope()) == null || injectee.booleanValue())
/*1088*/                return true;
/*1094*/            return !activedescriptor.getScope().equals(((ActiveDescriptor) (obj)).getScope());
                }
/*1104*/        if(!((Class) (obj = activedescriptor.getScopeAnnotation())).isAnnotationPresent(org/glassfish/hk2/api/Proxiable))
/*1105*/            return false;
/*1108*/        if(injectee == null)
/*1110*/            return true;
/*1113*/        if((injectee = injectee.getInjecteeDescriptor()) == null)
/*1116*/            return true;
/*1119*/        obj = (Proxiable)((Class) (obj)).getAnnotation(org/glassfish/hk2/api/Proxiable);
                Boolean boolean1;
/*1120*/        if((boolean1 = activedescriptor.isProxyForSameScope()) != null)
                {
/*1123*/            if(boolean1.booleanValue())
/*1124*/                return true;
                } else
/*1127*/        if(obj == null || ((Proxiable) (obj)).proxyForSameScope())
/*1129*/            return true;
/*1135*/        return !activedescriptor.getScope().equals(injectee.getScope());
            }

            public static Object getFirstThingInList(List list)
            {
/*1153*/        if((list = list.iterator()).hasNext())
/*1153*/            return list = ((List) (list.next()));
/*1157*/        else
/*1157*/            return null;
            }

            public static ActiveDescriptor getLocatorDescriptor(ServiceLocator servicelocator)
            {
                HashSet hashset;
/*1173*/        (hashset = new HashSet()).add(org/glassfish/hk2/api/ServiceLocator);
/*1176*/        Set set = Collections.emptySet();
/*1178*/        return servicelocator = new ConstantActiveDescriptor(servicelocator, hashset, org/glassfish/hk2/api/PerLookup, null, set, DescriptorVisibility.LOCAL, 0, null, null, null, servicelocator.getLocatorId(), null);
            }

            public static ActiveDescriptor getThreeThirtyDescriptor(ServiceLocatorImpl servicelocatorimpl)
            {
/*1204*/        ThreeThirtyResolver threethirtyresolver = new ThreeThirtyResolver(servicelocatorimpl);
/*1206*/        HashSet hashset = new HashSet();
                Type atype[];
/*1208*/        (atype = new Type[1])[0] = javax/inject/Inject;
/*1211*/        hashset.add(new ParameterizedTypeImpl(org/glassfish/hk2/api/InjectionResolver, atype));
                HashSet hashset1;
/*1213*/        (hashset1 = new HashSet()).add(new NamedImpl("SystemInjectResolver"));
/*1216*/        return servicelocatorimpl = new ConstantActiveDescriptor(threethirtyresolver, hashset, javax/inject/Singleton, "SystemInjectResolver", hashset1, DescriptorVisibility.LOCAL, 0, null, null, null, servicelocatorimpl.getLocatorId(), null);
            }

            public static Constructor findProducerConstructor(Class class1, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
/*1246*/        Constructor constructor = null;
/*1247*/        Constructor constructor1 = null;
                Object obj;
/*1249*/        for(obj = ((Set) (obj = getAllConstructors(class1))).iterator(); ((Iterator) (obj)).hasNext();)
                {
                    Constructor constructor2;
                    Type atype[];
/*1250*/            if((atype = (constructor2 = (Constructor)((Iterator) (obj)).next()).getGenericParameterTypes()).length <= 0)
/*1254*/                constructor = constructor2;
/*1257*/            if(servicelocatorimpl.hasInjectAnnotation(constructor2))
                    {
/*1258*/                if(constructor1 != null)
                        {
/*1259*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("There is more than one constructor on class ")).append(Pretty.clazz(class1)).toString()));
/*1261*/                    return null;
                        }
/*1264*/                constructor1 = constructor2;
                    }
/*1267*/            if(!isProperConstructor(constructor2))
                    {
/*1268*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("The constructor for ")).append(Pretty.clazz(class1)).append(" may not have an annotation as a parameter").toString()));
/*1270*/                return null;
                    }
                }

/*1276*/        if(constructor1 != null)
/*1277*/            return constructor1;
/*1280*/        if(constructor == null)
                {
/*1281*/            collector.addThrowable(new NoSuchMethodException((new StringBuilder("The class ")).append(Pretty.clazz(class1)).append(" has no constructor marked @Inject and no zero argument constructor").toString()));
/*1283*/            return null;
                } else
                {
/*1286*/            return constructor;
                }
            }

            private static boolean isProperConstructor(Constructor constructor)
            {
/*1290*/        int i = (constructor = constructor.getParameterTypes()).length;
/*1290*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*1290*/            if((obj = constructor[j]).isAnnotation())
/*1291*/                return false;
                }

/*1294*/        return true;
            }

            private static Set getAllConstructors(Class class1)
            {
/*1304*/        LinkedHashSet linkedhashset = new LinkedHashSet();
/*1306*/        int i = (class1 = class1 = (Constructor[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                    public final Constructor[] run()
                    {
/*1310*/                return clazz.getDeclaredConstructors();
                    }

                    public final volatile Object run()
                    {
/*1306*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/*1306*/                clazz = class1;
/*1306*/                super();
                    }
        })).length;
/*1315*/        for(int j = 0; j < i; j++)
                {
/*1315*/            Object obj = class1[j];
/*1316*/            linkedhashset.add(obj);
                }

/*1319*/        return linkedhashset;
            }

            public static Set findInitializerMethods(Class class1, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
/*1340*/        LinkedHashSet linkedhashset = new LinkedHashSet();
                ClassReflectionHelper classreflectionhelper;
/*1341*/        class1 = (classreflectionhelper = servicelocatorimpl.getClassReflectionHelper()).getAllMethods(class1).iterator();
/*1343*/        do
                {
/*1343*/            if(!class1.hasNext())
/*1343*/                break;
                    Object obj;
/*1343*/            obj = ((MethodWrapper) (obj = (MethodWrapper)class1.next())).getMethod();
/*1346*/            if(servicelocatorimpl.hasInjectAnnotation(((AnnotatedElement) (obj))))
/*1351*/                if(!isProperMethod(((Method) (obj))))
/*1352*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("An initializer method ")).append(Pretty.method(((Method) (obj)))).append(" is static, abstract or has a parameter that is an annotation").toString()));
/*1358*/                else
/*1358*/                    linkedhashset.add(obj);
                } while(true);
/*1361*/        return linkedhashset;
            }

            public static Method findPostConstruct(Class class1, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
/*1372*/        return servicelocatorimpl.getClassReflectionHelper().findPostConstruct(class1, org/glassfish/hk2/api/PostConstruct);
/*1374*/        class1;
/*1375*/        collector.addThrowable(class1);
/*1376*/        return null;
            }

            public static Method findPreDestroy(Class class1, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
/*1388*/        return servicelocatorimpl.getClassReflectionHelper().findPreDestroy(class1, org/glassfish/hk2/api/PreDestroy);
/*1390*/        class1;
/*1391*/        collector.addThrowable(class1);
/*1392*/        return null;
            }

            public static Set findInitializerFields(Class class1, ServiceLocatorImpl servicelocatorimpl, Collector collector)
            {
/*1407*/        LinkedHashSet linkedhashset = new LinkedHashSet();
                ClassReflectionHelper classreflectionhelper;
/*1408*/        class1 = (class1 = (classreflectionhelper = servicelocatorimpl.getClassReflectionHelper()).getAllFields(class1)).iterator();
/*1412*/        do
                {
/*1412*/            if(!class1.hasNext())
/*1412*/                break;
/*1412*/            Field field = (Field)class1.next();
/*1413*/            if(servicelocatorimpl.hasInjectAnnotation(field))
/*1418*/                if(!isProperField(field))
/*1419*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("The field ")).append(Pretty.field(field)).append(" may not be static, final or have an Annotation type").toString()));
/*1424*/                else
/*1424*/                    linkedhashset.add(field);
                } while(true);
/*1427*/        return linkedhashset;
            }

            static AnnotatedElementAnnotationInfo computeAEAI(AnnotatedElement annotatedelement)
            {
/*1440*/        if(annotatedelement instanceof Method)
                {
/*1442*/            annotatedelement = (Method)annotatedelement;
/*1443*/            return new AnnotatedElementAnnotationInfo(annotatedelement.getAnnotations(), true, annotatedelement.getParameterAnnotations(), false);
                }
/*1445*/        if(annotatedelement instanceof Constructor)
                {
/*1447*/            annotatedelement = (Constructor)annotatedelement;
/*1448*/            return new AnnotatedElementAnnotationInfo(annotatedelement.getAnnotations(), true, annotatedelement.getParameterAnnotations(), true);
                } else
                {
/*1451*/            return new AnnotatedElementAnnotationInfo(annotatedelement.getAnnotations(), false, new Annotation[0][], false);
                }
            }

            private static boolean isProperMethod(Method method)
            {
/*1456*/        if(ReflectionHelper.isStatic(method))
/*1456*/            return false;
/*1457*/        if(isAbstract(method))
/*1457*/            return false;
/*1458*/        int i = (method = method.getParameterTypes()).length;
/*1458*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*1458*/            if((obj = method[j]).isAnnotation())
/*1460*/                return false;
                }

/*1464*/        return true;
            }

            private static boolean isProperField(Field field)
            {
/*1468*/        if(ReflectionHelper.isStatic(field))
/*1468*/            return false;
/*1469*/        if(isFinal(field))
/*1469*/            return false;
/*1470*/        return !(field = field.getType()).isAnnotation();
            }

            public static boolean isAbstract(Member member)
            {
/*1483*/        return ((member = member.getModifiers()) & 0x400) != 0;
            }

            public static boolean isFinal(Member member)
            {
/*1495*/        return ((member = member.getModifiers()) & 0x10) != 0;
            }

            private static boolean isFinal(Class class1)
            {
/*1501*/        return ((class1 = class1.getModifiers()) & 0x10) != 0;
            }

            private static ScopeInfo getScopeInfo(AnnotatedElement annotatedelement, Descriptor descriptor, Collector collector)
            {
/*1513*/        AnnotatedElement annotatedelement1 = annotatedelement;
/*1515*/        Annotation annotation = null;
/*1516*/label0:
/*1516*/        do
                {
                    Object obj;
/*1516*/            if(annotatedelement != null)
/*1517*/                if((obj = internalGetScopeAnnotationType(annotatedelement, collector)) != null)
                        {
/*1521*/                    if(annotatedelement.equals(annotatedelement1))
/*1523*/                        annotation = ((Annotation) (obj));
/*1527*/                    else
/*1527*/                    if(((Annotation) (obj)).annotationType().isAnnotationPresent(java/lang/annotation/Inherited))
/*1528*/                        annotation = ((Annotation) (obj));
                        } else
                        {
/*1536*/                    if(annotatedelement instanceof Class)
                            {
/*1537*/                        annotatedelement = ((Class)annotatedelement).getSuperclass();
/*1537*/                        continue;
                            }
/*1539*/                    obj = (annotatedelement = (Method)annotatedelement).getDeclaringClass();
/*1542*/                    annotatedelement = null;
/*1543*/                    obj = ((Class) (obj)).getSuperclass();
/*1544*/                    do
                            {
/*1544*/                        if(obj == null)
/*1545*/                            continue label0;
/*1545*/                        if(org/glassfish/hk2/api/Factory.isAssignableFrom(((Class) (obj))))
                                {
/*1546*/                            annotatedelement = getFactoryProvideMethod(((Class) (obj)));
/*1547*/                            continue label0;
                                }
/*1550*/                        obj = ((Class) (obj)).getSuperclass();
                            } while(true);
                        }
/*1555*/            if(annotation != null)
/*1556*/                return new ScopeInfo(annotation, annotation.annotationType());
/*1560*/            if(annotatedelement1.isAnnotationPresent(org/jvnet/hk2/annotations/Service))
/*1561*/                return new ScopeInfo(ServiceLocatorUtilities.getSingletonAnnotation(), javax/inject/Singleton);
/*1564*/            if(descriptor != null && descriptor.getScope() != null && (obj = loadClass(descriptor.getScope(), descriptor, collector)) != null)
/*1568*/                return new ScopeInfo(null, ((Class) (obj)));
/*1572*/            return new ScopeInfo(ServiceLocatorUtilities.getPerLookupAnnotation(), org/glassfish/hk2/api/PerLookup);
                } while(true);
            }

            public static Class getScopeAnnotationType(Class class1, Descriptor descriptor)
            {
/*1587*/        Collector collector = new Collector();
/*1589*/        class1 = getScopeInfo(class1, descriptor, collector);
/*1591*/        collector.throwIfErrors();
/*1593*/        return class1.getAnnoType();
            }

            public static ScopeInfo getScopeAnnotationType(AnnotatedElement annotatedelement, Descriptor descriptor, Collector collector)
            {
/*1609*/        return annotatedelement = getScopeInfo(annotatedelement, descriptor, collector);
            }

            private static Annotation internalGetScopeAnnotationType(AnnotatedElement annotatedelement, Collector collector)
            {
/*1620*/        boolean flag = false;
/*1621*/        Annotation annotation = null;
                Annotation aannotation[];
/*1622*/        int i = (aannotation = annotatedelement.getDeclaredAnnotations()).length;
/*1622*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation1;
/*1622*/            if(!(annotation1 = aannotation[j]).annotationType().isAnnotationPresent(javax/inject/Scope))
/*1624*/                continue;
/*1624*/            if(annotation != null)
                    {
/*1625*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("The type ")).append(annotatedelement).append(" may not have more than one scope.  It has at least ").append(Pretty.clazz(annotation.annotationType())).append(" and ").append(Pretty.clazz(annotation1.annotationType())).toString()));
/*1629*/                flag = true;
                    } else
                    {
/*1633*/                annotation = annotation1;
                    }
                }

/*1637*/        if(flag)
/*1637*/            return null;
/*1639*/        else
/*1639*/            return annotation;
            }

            public static Method getFactoryProvideMethod(Class class1)
            {
/*1652*/        return class1.getMethod("provide", new Class[0]);
/*1653*/        JVM INSTR pop ;
/*1654*/        return null;
            }

            public static String getDefaultNameFromMethod(Method method, Collector collector)
            {
/*1667*/        if((method = (Named)method.getAnnotation(javax/inject/Named)) == null)
/*1669*/            return null;
/*1672*/        if(method.value() == null || method.value().equals(""))
/*1673*/            collector.addThrowable(new IllegalArgumentException("@Named on the provide method of a factory must have an explicit value"));
/*1677*/        return method.value();
            }

            public static Set getAllQualifiers(AnnotatedElement annotatedelement, String s, Collector collector)
            {
/*1693*/        Object obj = null;
                Set set;
/*1694*/        Iterator iterator = (set = ReflectionHelper.getQualifierAnnotations(annotatedelement)).iterator();
/*1695*/        do
                {
/*1695*/            if(!iterator.hasNext())
/*1695*/                break;
                    Annotation annotation;
/*1695*/            if(!((annotation = (Annotation)iterator.next()) instanceof Named))
/*1697*/                continue;
/*1697*/            obj = (Named)annotation;
/*1698*/            break;
                } while(true);
/*1702*/        if(s == null)
                {
/*1703*/            if(obj != null)
                    {
/*1704*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("No name was in the descriptor, but this element(")).append(annotatedelement).append(" has a Named annotation with value: ").append(((Named) (obj)).value()).toString()));
/*1707*/                set.remove(obj);
                    }
/*1710*/            return set;
                }
/*1713*/        if(obj == null || ((Named) (obj)).value().equals(""))
                {
/*1714*/            if(obj != null)
/*1715*/                set.remove(obj);
/*1718*/            obj = new NamedImpl(s);
/*1720*/            set.add(obj);
                }
/*1723*/        if(!s.equals(((Named) (obj)).value()))
/*1724*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("The class had an @Named qualifier that was inconsistent.  The expected name is ")).append(s).append(" but the annotation has name ").append(((Named) (obj)).value()).toString()));
/*1729*/        return set;
            }

            private static AnnotationInformation getParamInformation(Annotation aannotation[])
            {
/*1733*/        boolean flag = true;
/*1735*/        Object obj = null;
/*1736*/        boolean flag1 = false;
/*1737*/        boolean flag2 = false;
/*1738*/        Unqualified unqualified = null;
/*1740*/        int i = (aannotation = aannotation).length;
/*1740*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation;
/*1740*/            if(ReflectionHelper.isAnnotationAQualifier(annotation = aannotation[j]))
                    {
/*1742*/                if(obj == null)
/*1742*/                    obj = new HashSet();
/*1743*/                ((Set) (obj)).add(annotation);
/*1744*/                flag = false;
/*1744*/                continue;
                    }
/*1746*/            if(org/jvnet/hk2/annotations/Optional.equals(annotation.annotationType()))
                    {
/*1747*/                flag1 = true;
/*1748*/                flag = false;
/*1748*/                continue;
                    }
/*1750*/            if(org/glassfish/hk2/api/Self.equals(annotation.annotationType()))
                    {
/*1751*/                flag2 = true;
/*1752*/                flag = false;
/*1752*/                continue;
                    }
/*1754*/            if(org/glassfish/hk2/api/Unqualified.equals(annotation.annotationType()))
                    {
/*1755*/                unqualified = (Unqualified)annotation;
/*1756*/                flag = false;
                    }
                }

/*1760*/        if(flag)
/*1760*/            return DEFAULT_ANNOTATION_INFORMATION;
/*1762*/        if(obj == null)
/*1762*/            obj = DEFAULT_ANNOTATION_INFORMATION.qualifiers;
/*1764*/        return new AnnotationInformation(((Set) (obj)), flag1, flag2, unqualified);
            }

            public static List getConstructorInjectees(Constructor constructor, ActiveDescriptor activedescriptor)
            {
/*1778*/        Type atype[] = constructor.getGenericParameterTypes();
/*1779*/        Annotation aannotation[][] = constructor.getParameterAnnotations();
/*1781*/        LinkedList linkedlist = new LinkedList();
/*1783*/        for(int i = 0; i < atype.length; i++)
                {
/*1784*/            AnnotationInformation annotationinformation = getParamInformation(aannotation[i]);
/*1786*/            linkedlist.add(new SystemInjecteeImpl(atype[i], annotationinformation.qualifiers, i, constructor, annotationinformation.optional, annotationinformation.self, annotationinformation.unqualified, activedescriptor));
                }

/*1796*/        return linkedlist;
            }

            public static List getMethodInjectees(Class class1, Method method, ActiveDescriptor activedescriptor)
            {
/*1806*/        Type atype[] = method.getGenericParameterTypes();
/*1807*/        Annotation aannotation[][] = method.getParameterAnnotations();
/*1809*/        LinkedList linkedlist = new LinkedList();
/*1810*/        Class class2 = method.getDeclaringClass();
/*1812*/        for(int i = 0; i < atype.length; i++)
                {
/*1813*/            AnnotationInformation annotationinformation = getParamInformation(aannotation[i]);
/*1815*/            Type type = ReflectionHelper.resolveMember(class1, atype[i], class2);
/*1818*/            linkedlist.add(new SystemInjecteeImpl(type, annotationinformation.qualifiers, i, method, annotationinformation.optional, annotationinformation.self, annotationinformation.unqualified, activedescriptor));
                }

/*1828*/        return linkedlist;
            }

            private static Set getFieldAdjustedQualifierAnnotations(Field field, Set set)
            {
                Object obj;
/*1832*/        if((obj = (Named)field.getAnnotation(javax/inject/Named)) == null)
/*1833*/            return set;
/*1834*/        if(((Named) (obj)).value() != null && !"".equals(((Named) (obj)).value()))
/*1835*/            return set;
/*1838*/        obj = new HashSet();
                Annotation annotation;
/*1839*/        for(set = set.iterator(); set.hasNext();)
/*1839*/            if((annotation = (Annotation)set.next()).annotationType().equals(javax/inject/Named))
/*1841*/                ((HashSet) (obj)).add(new NamedImpl(field.getName()));
/*1844*/            else
/*1844*/                ((HashSet) (obj)).add(annotation);

/*1848*/        return ((Set) (obj));
            }

            public static List getFieldInjectees(Class class1, Field field, ActiveDescriptor activedescriptor)
            {
/*1858*/        LinkedList linkedlist = new LinkedList();
/*1859*/        AnnotationInformation annotationinformation = getParamInformation(field.getAnnotations());
/*1861*/        class1 = ReflectionHelper.resolveField(class1, field);
/*1863*/        linkedlist.add(new SystemInjecteeImpl(class1, getFieldAdjustedQualifierAnnotations(field, annotationinformation.qualifiers), -1, field, annotationinformation.optional, annotationinformation.self, annotationinformation.unqualified, activedescriptor));
/*1872*/        return linkedlist;
            }

            public static void validateSelfInjectees(ActiveDescriptor activedescriptor, List list, Collector collector)
            {
/*1888*/        list = list.iterator();
/*1888*/        do
                {
/*1888*/            if(!list.hasNext())
/*1888*/                break;
                    Injectee injectee;
/*1888*/            if((injectee = (Injectee)list.next()).isSelf())
                    {
                        Class class1;
/*1891*/                if((class1 = ReflectionHelper.getRawClass(injectee.getRequiredType())) == null || !org/glassfish/hk2/api/ActiveDescriptor.equals(class1))
/*1893*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("Injection point ")).append(injectee).append(" does not have the required type of ActiveDescriptor").toString()));
/*1897*/                if(injectee.isOptional())
/*1898*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("Injection point ")).append(injectee).append(" is marked both @Optional and @Self").toString()));
/*1902*/                if(!injectee.getRequiredQualifiers().isEmpty())
/*1903*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("Injection point ")).append(injectee).append(" is marked @Self but has other qualifiers").toString()));
/*1907*/                if(activedescriptor == null)
/*1908*/                    collector.addThrowable(new IllegalArgumentException((new StringBuilder("A class with injection point ")).append(injectee).append(" is being created or injected via the non-managed ServiceLocator API").toString()));
                    }
                } while(true);
            }

            public static Set fixAndCheckQualifiers(Annotation aannotation[], String s)
            {
/*1931*/        HashSet hashset = new HashSet();
/*1933*/        HashSet hashset1 = new HashSet();
/*1934*/        Named named = null;
/*1935*/        int i = (aannotation = aannotation).length;
/*1935*/        for(int j = 0; j < i; j++)
                {
                    Annotation annotation;
/*1935*/            String s1 = (annotation = aannotation[j]).annotationType().getName();
/*1937*/            if(hashset1.contains(s1))
/*1938*/                throw new IllegalArgumentException((new StringBuilder()).append(s1).append(" appears more than once in the qualifier list").toString());
/*1940*/            hashset1.add(s1);
/*1942*/            hashset.add(annotation);
/*1943*/            if(!(annotation instanceof Named))
/*1944*/                continue;
/*1944*/            if((named = (Named)annotation).value().equals(""))
/*1947*/                throw new IllegalArgumentException("The @Named qualifier must have a value");
/*1950*/            if(s != null && !s.equals(named.value()))
/*1951*/                throw new IllegalArgumentException((new StringBuilder("The name passed to the method (")).append(s).append(") does not match the value of the @Named qualifier (").append(named.value()).append(")").toString());
                }

/*1957*/        if(named == null && s != null)
/*1958*/            hashset.add(new NamedImpl(s));
/*1961*/        return hashset;
            }

            public static Object createService(ActiveDescriptor activedescriptor, Injectee injectee, ServiceLocatorImpl servicelocatorimpl, ServiceHandle servicehandle, Class class1)
            {
/*1981*/        if(activedescriptor == null)
/*1981*/            throw new IllegalArgumentException();
/*1985*/        if(!activedescriptor.isReified())
/*1986*/            activedescriptor = servicelocatorimpl.reifyDescriptor(activedescriptor, injectee);
/*1989*/        if(isProxiable(activedescriptor, injectee))
/*1990*/            if(!proxiesAvailable())
/*1991*/                throw new IllegalStateException((new StringBuilder("A descriptor ")).append(activedescriptor).append(" requires a proxy, but the proxyable library is not on the classpath").toString());
/*1994*/            else
/*1994*/                return servicelocatorimpl.getPerLocatorUtilities().getProxyUtilities().generateProxy(class1, servicelocatorimpl, activedescriptor, (ServiceHandleImpl)servicehandle, injectee);
/*2000*/        try
                {
/*2000*/            servicelocatorimpl = servicelocatorimpl.resolveContext(activedescriptor.getScopeAnnotation());
                }
                // Misplaced declaration of an exception variable
/*2002*/        catch(ServiceLocatorImpl servicelocatorimpl)
                {
/*2003*/            if(injectee != null && injectee.isOptional())
/*2003*/                return null;
/*2005*/            activedescriptor = new IllegalStateException((new StringBuilder("While attempting to create a service for ")).append(activedescriptor).append(" in scope ").append(activedescriptor.getScope()).append(" an error occured while locating the context").toString());
/*2008*/            if(servicelocatorimpl instanceof MultiException)
                    {
/*2009*/                (injectee = (MultiException)servicelocatorimpl).addError(activedescriptor);
/*2013*/                throw injectee;
                    } else
                    {
/*2016*/                (injectee = new MultiException(servicelocatorimpl)).addError(activedescriptor);
/*2018*/                throw injectee;
                    }
                }
/*2022*/        injectee = ((Injectee) (servicelocatorimpl.findOrCreate(activedescriptor, servicehandle)));
/*2029*/        break MISSING_BLOCK_LABEL_224;
/*2024*/        JVM INSTR dup ;
/*2025*/        servicelocatorimpl;
/*2025*/        throw ;
/*2027*/        servicelocatorimpl;
/*2028*/        throw new MultiException(servicelocatorimpl);
/*2031*/        if(injectee == null && !servicelocatorimpl.supportsNullCreation())
/*2032*/            throw new MultiException(new IllegalStateException((new StringBuilder("Context ")).append(servicelocatorimpl).append(" findOrCreate returned a null for descriptor ").append(activedescriptor).append(" and handle ").append(servicehandle).toString()));
/*2037*/        else
/*2037*/            return injectee;
            }

            static Interceptors getAllInterceptors(ServiceLocatorImpl servicelocatorimpl, ActiveDescriptor activedescriptor, Class class1, Constructor constructor)
            {
/*2076*/        if(activedescriptor == null || class1 == null || isFinal(class1))
/*2076*/            return EMTPY_INTERCEPTORS;
/*2077*/        ClassReflectionHelper classreflectionhelper = servicelocatorimpl.getClassReflectionHelper();
/*2079*/        if((servicelocatorimpl = servicelocatorimpl.getInterceptionServices()) == null || servicelocatorimpl.isEmpty())
/*2080*/            return EMTPY_INTERCEPTORS;
/*2083*/        for(Iterator iterator = activedescriptor.getAdvertisedContracts().iterator(); iterator.hasNext();)
                {
/*2083*/            String s = (String)iterator.next();
/*2084*/            if(NOT_INTERCEPTED.contains(s))
/*2084*/                return EMTPY_INTERCEPTORS;
                }

/*2087*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
/*2089*/        ArrayList arraylist = new ArrayList();
/*2091*/        servicelocatorimpl = servicelocatorimpl.iterator();
/*2091*/        do
                {
/*2091*/            if(!servicelocatorimpl.hasNext())
/*2091*/                break;
                    InterceptionService interceptionservice;
/*2091*/            org.glassfish.hk2.api.Filter filter = (interceptionservice = (InterceptionService)servicelocatorimpl.next()).getDescriptorFilter();
/*2093*/            if(BuilderHelper.filterMatches(activedescriptor, filter))
                    {
/*2094*/                Object obj = classreflectionhelper.getAllMethods(class1).iterator();
/*2094*/                do
                        {
/*2094*/                    if(!((Iterator) (obj)).hasNext())
/*2094*/                        break;
                            Object obj1;
                            List list;
/*2094*/                    if(!isFinal(((Member) (obj1 = ((MethodWrapper) (obj1 = (MethodWrapper)((Iterator) (obj)).next())).getMethod()))) && ((list = interceptionservice.getMethodInterceptors(((Method) (obj1)))) != null && !list.isEmpty()))
                            {
                                Object obj2;
/*2101*/                        if((obj2 = (List)linkedhashmap.get(obj1)) == null)
                                {
/*2103*/                            obj2 = new ArrayList();
/*2104*/                            linkedhashmap.put(obj1, obj2);
                                }
/*2107*/                        ((List) (obj2)).addAll(list);
                            }
                        } while(true);
/*2111*/                if((obj = interceptionservice.getConstructorInterceptors(constructor)) != null && !((List) (obj)).isEmpty())
/*2113*/                    arraylist.addAll(((java.util.Collection) (obj)));
                    }
                } while(true);
/*2118*/        return new Interceptors(linkedhashmap, arraylist) {

                    public final Map getMethodInterceptors()
                    {
/*2122*/                return retVal;
                    }

                    public final List getConstructorInterceptors()
                    {
/*2127*/                return cRetVal;
                    }

                    final LinkedHashMap val$retVal;
                    final ArrayList val$cRetVal;

                    
                    {
/*2118*/                retVal = linkedhashmap;
/*2118*/                cRetVal = arraylist;
/*2118*/                super();
                    }
        };
            }

            public static boolean isTypeSafe(Type type, Type type1)
            {
/*2143*/        if(TypeChecker.isRawTypeSafe(type, type1))
/*2143*/            return true;
/*2145*/        if((type = ReflectionHelper.getRawClass(type)) == null)
/*2147*/            return false;
/*2151*/        if(!type.isAnnotation())
/*2151*/            return false;
/*2153*/        if((type1 = ReflectionHelper.getRawClass(type1)) == null)
/*2155*/            return false;
/*2158*/        if(type1.isAnnotationPresent(type))
/*2159*/            return true;
/*2162*/        return (type1 = getScopeAnnotationType(type1, null)).equals(type);
            }

            public static synchronized boolean proxiesAvailable()
            {
                ClassLoader classloader;
/*2178*/        if(proxiesAvailable != null)
/*2179*/            return proxiesAvailable.booleanValue();
/*2182*/        if((classloader = org/jvnet/hk2/internal/Utilities.getClassLoader()) == null)
/*2184*/            classloader = ClassLoader.getSystemClassLoader();
/*2188*/        classloader.loadClass("javassist.util.proxy.MethodHandler");
/*2189*/        proxiesAvailable = Boolean.valueOf(true);
/*2190*/        return true;
/*2192*/        JVM INSTR pop ;
/*2193*/        proxiesAvailable = Boolean.valueOf(false);
/*2194*/        return false;
            }

            private static final String USE_SOFT_REFERENCE_PROPERTY = "org.jvnet.hk2.properties.useSoftReference";
            static final boolean USE_SOFT_REFERENCE = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/* 140*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.useSoftReference", "true")));
                }

                public final volatile Object run()
                {
/* 136*/            return run();
                }

    })).booleanValue();
            private static final AnnotationInformation DEFAULT_ANNOTATION_INFORMATION = new AnnotationInformation(Collections.emptySet(), false, false, null);
            private static final String PROVIDE_METHOD = "provide";
            private static final HashSet NOT_INTERCEPTED;
            private static final Interceptors EMTPY_INTERCEPTORS = new Interceptors() {

                public final Map getMethodInterceptors()
                {
/*2061*/            return null;
                }

                public final List getConstructorInterceptors()
                {
/*2066*/            return null;
                }

    };
            private static Boolean proxiesAvailable = null;

            static 
            {
/*2042*/        (NOT_INTERCEPTED = new HashSet()).add(org/glassfish/hk2/api/ServiceLocator.getName());
/*2046*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/InstanceLifecycleListener.getName());
/*2047*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/InjectionResolver.getName());
/*2048*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/ErrorService.getName());
/*2049*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/ClassAnalyzer.getName());
/*2050*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/DynamicConfigurationListener.getName());
/*2051*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/DynamicConfigurationService.getName());
/*2052*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/InterceptionService.getName());
/*2053*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/ValidationService.getName());
/*2054*/        NOT_INTERCEPTED.add(org/glassfish/hk2/api/Context.getName());
            }
}
