// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClazzCreator.java

package org.jvnet.hk2.internal;

import java.lang.reflect.*;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            Collector, ConstructorActionImpl, ConstructorInterceptorHandler, Creator, 
//            InstanceLifecycleEventImpl, ServiceLocatorImpl, SystemDescriptor, SystemInjecteeImpl, 
//            Utilities

public class ClazzCreator
    implements Creator
{
    static class ResolutionInfo
    {

                public String toString()
                {
/* 450*/            return (new StringBuilder("ResolutionInfo(")).append(baseElement).append(",").append(injectees).append(",").append(System.identityHashCode(this)).append(")").toString();
                }

                private final AnnotatedElement baseElement;
                private final List injectees;



                private ResolutionInfo(AnnotatedElement annotatedelement, List list)
                {
/* 441*/            injectees = new LinkedList();
/* 444*/            baseElement = annotatedelement;
/* 445*/            injectees.addAll(list);
                }

    }


            ClazzCreator(ServiceLocatorImpl servicelocatorimpl, Class class1)
            {
/*  88*/        locator = servicelocatorimpl;
/*  89*/        implClass = class1;
            }

            void initialize(ActiveDescriptor activedescriptor, String s, Collector collector)
            {
/*  96*/        selfDescriptor = activedescriptor;
/*  98*/        if(activedescriptor != null && activedescriptor.getAdvertisedContracts().contains(org/glassfish/hk2/api/ClassAnalyzer.getName()))
                {
                    String s1;
/* 101*/            if((s1 = activedescriptor.getName()) == null)
/* 102*/                s1 = locator.getDefaultClassAnalyzerName();
                    String s2;
/* 104*/            if((s2 = s) == null)
/* 105*/                s2 = locator.getDefaultClassAnalyzerName();
/* 107*/            if(s1.equals(s2))
                    {
/* 108*/                collector.addThrowable(new IllegalArgumentException((new StringBuilder("The ClassAnalyzer named ")).append(s1).append(" is its own ClassAnalyzer. Ensure that an implementation of ClassAnalyzer is not its own ClassAnalyzer").toString()));
/* 112*/                myConstructor = null;
/* 113*/                return;
                    }
                }
                ClassAnalyzer classanalyzer;
/* 117*/        if((classanalyzer = Utilities.getClassAnalyzer(locator, s, collector)) == null)
                {
/* 119*/            myConstructor = null;
/* 120*/            return;
                }
/* 123*/        LinkedList linkedlist = new LinkedList();
/* 128*/        if((s = Utilities.getConstructor(implClass, classanalyzer, collector)) == null)
                {
/* 130*/            myConstructor = null;
/* 131*/            return;
                }
                List list;
/* 134*/        if((list = Utilities.getConstructorInjectees((Constructor)s, activedescriptor)) == null)
                {
/* 136*/            myConstructor = null;
/* 137*/            return;
                }
/* 140*/        linkedlist.addAll(list);
/* 142*/        myConstructor = new ResolutionInfo(s, list);
                List list1;
/* 144*/        for(Iterator iterator = (s = Utilities.getInitMethods(implClass, classanalyzer, collector)).iterator(); iterator.hasNext(); myInitializers.add(new ResolutionInfo(s, list1)))
                {
                    Method method;
/* 145*/            s = method = (Method)iterator.next();
/* 148*/            if((list1 = Utilities.getMethodInjectees(implClass, method, activedescriptor)) == null)
/* 149*/                return;
/* 151*/            linkedlist.addAll(list1);
                }

                Object obj;
                Set set;
/* 156*/        for(Iterator iterator1 = (set = Utilities.getInitFields(implClass, classanalyzer, collector)).iterator(); iterator1.hasNext(); myFields.add(new ResolutionInfo(s, ((List) (obj)))))
                {
/* 157*/            s = ((String) (obj = (Field)iterator1.next()));
/* 160*/            if((obj = Utilities.getFieldInjectees(implClass, ((Field) (obj)), activedescriptor)) == null)
/* 161*/                return;
/* 163*/            linkedlist.addAll(((java.util.Collection) (obj)));
                }

/* 168*/        postConstructMethod = Utilities.getPostConstruct(implClass, classanalyzer, collector);
/* 169*/        preDestroyMethod = Utilities.getPreDestroy(implClass, classanalyzer, collector);
/* 171*/        allInjectees = Collections.unmodifiableList(linkedlist);
/* 173*/        Utilities.validateSelfInjectees(activedescriptor, allInjectees, collector);
            }

            void initialize(ActiveDescriptor activedescriptor, Collector collector)
            {
/* 179*/        initialize(activedescriptor, activedescriptor != null ? activedescriptor.getClassAnalysisName() : null, collector);
            }

            void resetSelfDescriptor(ActiveDescriptor activedescriptor)
            {
/* 190*/        selfDescriptor = activedescriptor;
/* 192*/        Iterator iterator = allInjectees.iterator();
/* 192*/        do
                {
/* 192*/            if(!iterator.hasNext())
/* 192*/                break;
                    Injectee injectee;
/* 192*/            if((injectee = (Injectee)iterator.next()) instanceof SystemInjecteeImpl)
/* 195*/                ((SystemInjecteeImpl)injectee).resetInjecteeDescriptor(activedescriptor);
                } while(true);
            }

            private void resolve(Map map, InjectionResolver injectionresolver, SystemInjecteeImpl systeminjecteeimpl, ServiceHandle servicehandle, Collector collector)
            {
/* 204*/        if(systeminjecteeimpl.isSelf())
                {
/* 205*/            map.put(systeminjecteeimpl, selfDescriptor);
/* 206*/            return;
                }
/* 209*/        Object obj = null;
/* 211*/        try
                {
/* 211*/            obj = injectionresolver.resolve(systeminjecteeimpl, servicehandle);
                }
                // Misplaced declaration of an exception variable
/* 212*/        catch(InjectionResolver injectionresolver)
                {
/* 213*/            collector.addThrowable(injectionresolver);
                }
/* 216*/        if(obj != null)
/* 217*/            map.put(systeminjecteeimpl, obj);
            }

            private Map resolveAllDependencies(ServiceHandle servicehandle)
                throws MultiException, IllegalStateException
            {
/* 222*/        Collector collector = new Collector();
/* 224*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
                SystemInjecteeImpl systeminjecteeimpl;
                InjectionResolver injectionresolver;
/* 226*/        for(Iterator iterator = myConstructor.injectees.iterator(); iterator.hasNext(); resolve(linkedhashmap, injectionresolver, systeminjecteeimpl, servicehandle, collector))
                {
/* 226*/            systeminjecteeimpl = (SystemInjecteeImpl)iterator.next();
/* 227*/            injectionresolver = locator.getInjectionResolverForInjectee(systeminjecteeimpl);
                }

/* 231*/        for(Iterator iterator1 = myFields.iterator(); iterator1.hasNext();)
                {
                    ResolutionInfo resolutioninfo;
/* 231*/            Iterator iterator3 = (resolutioninfo = (ResolutionInfo)iterator1.next()).injectees.iterator();
/* 232*/            while(iterator3.hasNext()) 
                    {
/* 232*/                SystemInjecteeImpl systeminjecteeimpl1 = (SystemInjecteeImpl)iterator3.next();
/* 233*/                InjectionResolver injectionresolver1 = locator.getInjectionResolverForInjectee(systeminjecteeimpl1);
/* 234*/                resolve(linkedhashmap, injectionresolver1, systeminjecteeimpl1, servicehandle, collector);
                    }
                }

/* 238*/        for(Iterator iterator2 = myInitializers.iterator(); iterator2.hasNext();)
                {
                    ResolutionInfo resolutioninfo1;
/* 238*/            Iterator iterator4 = (resolutioninfo1 = (ResolutionInfo)iterator2.next()).injectees.iterator();
/* 239*/            while(iterator4.hasNext()) 
                    {
/* 239*/                SystemInjecteeImpl systeminjecteeimpl2 = (SystemInjecteeImpl)iterator4.next();
/* 240*/                InjectionResolver injectionresolver2 = locator.getInjectionResolverForInjectee(systeminjecteeimpl2);
/* 241*/                resolve(linkedhashmap, injectionresolver2, systeminjecteeimpl2, servicehandle, collector);
                    }
                }

/* 245*/        if(collector.hasErrors())
                {
/* 246*/            collector.addThrowable(new IllegalArgumentException((new StringBuilder("While attempting to resolve the dependencies of ")).append(implClass.getName()).append(" errors were found").toString()));
/* 249*/            collector.throwIfErrors();
                }
/* 252*/        return linkedhashmap;
            }

            private Object createMe(Map map)
                throws Throwable
            {
/* 256*/        Constructor constructor = (Constructor)myConstructor.baseElement;
                Object obj;
/* 257*/        Object aobj[] = new Object[((List) (obj = myConstructor.injectees)).size()];
/* 260*/        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                {
/* 260*/            Injectee injectee = (Injectee)((Iterator) (obj)).next();
/* 261*/            aobj[injectee.getPosition()] = map.get(injectee);
                }

/* 264*/        Map map1 = ((Utilities.Interceptors) (obj = Utilities.getAllInterceptors(locator, selfDescriptor, implClass, constructor))).getMethodInterceptors();
/* 266*/        map = ((Utilities.Interceptors) (obj)).getConstructorInterceptors();
/* 268*/        if((map1 == null || map1.isEmpty()) && (map == null || map.isEmpty()))
/* 271*/            return ReflectionHelper.makeMe(constructor, aobj, locator.getNeutralContextClassLoader());
/* 274*/        if(!Utilities.proxiesAvailable())
/* 275*/            throw new IllegalStateException((new StringBuilder("A service ")).append(selfDescriptor).append(" needs either method or constructor interception, but proxies are not available").toString());
/* 278*/        boolean flag = locator.getNeutralContextClassLoader();
/* 280*/        if(map1 == null || map1.isEmpty())
/* 282*/            return ConstructorInterceptorHandler.construct(constructor, aobj, flag, map);
/* 285*/        else
/* 285*/            return ConstructorInterceptorHandler.construct(constructor, aobj, flag, map, new ConstructorActionImpl(this, map1));
            }

            private void fieldMe(Map map, Object obj)
                throws Throwable
            {
                Object obj1;
                Field field;
/* 293*/        for(Iterator iterator = myFields.iterator(); iterator.hasNext(); ReflectionHelper.setField(field, obj, obj1))
                {
/* 293*/            field = (Field)((ResolutionInfo) (obj1 = (ResolutionInfo)iterator.next())).baseElement;
/* 295*/            obj1 = ((ResolutionInfo) (obj1)).injectees;
/* 297*/            Injectee injectee = null;
/* 298*/            for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
/* 298*/                injectee = injectee = (Injectee)((Iterator) (obj1)).next();

/* 302*/            obj1 = map.get(injectee);
                }

            }

            private void methodMe(Map map, Object obj)
                throws Throwable
            {
                Method method;
                Object aobj[];
/* 309*/        for(Iterator iterator = myInitializers.iterator(); iterator.hasNext(); ReflectionHelper.invoke(obj, method, aobj, locator.getNeutralContextClassLoader()))
                {
                    Object obj1;
/* 309*/            method = (Method)((ResolutionInfo) (obj1 = (ResolutionInfo)iterator.next())).baseElement;
/* 311*/            aobj = new Object[((List) (obj1 = ((ResolutionInfo) (obj1)).injectees)).size()];
/* 314*/            for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
                    {
/* 314*/                Injectee injectee = (Injectee)((Iterator) (obj1)).next();
/* 315*/                aobj[injectee.getPosition()] = map.get(injectee);
                    }

                }

            }

            private void postConstructMe(Object obj)
                throws Throwable
            {
/* 323*/        if(obj == null)
/* 323*/            return;
/* 325*/        if(obj instanceof PostConstruct)
                {
/* 326*/            ((PostConstruct)obj).postConstruct();
/* 327*/            return;
                }
/* 330*/        if(postConstructMethod == null)
                {
/* 330*/            return;
                } else
                {
/* 332*/            ReflectionHelper.invoke(obj, postConstructMethod, new Object[0], locator.getNeutralContextClassLoader());
/* 333*/            return;
                }
            }

            private void preDestroyMe(Object obj)
                throws Throwable
            {
/* 336*/        if(obj == null)
/* 336*/            return;
/* 338*/        if(obj instanceof PreDestroy)
                {
/* 339*/            ((PreDestroy)obj).preDestroy();
/* 340*/            return;
                }
/* 343*/        if(preDestroyMethod == null)
                {
/* 343*/            return;
                } else
                {
/* 345*/            ReflectionHelper.invoke(obj, preDestroyMethod, new Object[0], locator.getNeutralContextClassLoader());
/* 346*/            return;
                }
            }

            public Object create(ServiceHandle servicehandle, SystemDescriptor systemdescriptor)
            {
/* 354*/        String s = "resolve";
                Object obj;
/* 357*/        servicehandle = resolveAllDependencies(servicehandle);
/* 359*/        if(systemdescriptor != null)
/* 360*/            systemdescriptor.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.PRE_PRODUCTION, null, (Map)ReflectionHelper.cast(servicehandle), systemdescriptor));
/* 364*/        s = "create";
/* 365*/        obj = createMe(servicehandle);
/* 367*/        s = "field inject";
/* 368*/        fieldMe(servicehandle, obj);
/* 370*/        s = "method inject";
/* 371*/        methodMe(servicehandle, obj);
/* 373*/        s = "post construct";
/* 374*/        postConstructMe(obj);
/* 376*/        if(systemdescriptor != null)
/* 377*/            systemdescriptor.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.POST_PRODUCTION, obj, (Map)ReflectionHelper.cast(servicehandle), systemdescriptor));
/* 381*/        return obj;
/* 382*/        JVM INSTR dup ;
/* 383*/        servicehandle;
/* 383*/        JVM INSTR instanceof #42  <Class MultiException>;
/* 383*/        JVM INSTR ifeq 165;
                   goto _L1 _L2
_L1:
/* 384*/        break MISSING_BLOCK_LABEL_114;
_L2:
/* 384*/        break MISSING_BLOCK_LABEL_165;
                MultiException multiexception;
/* 384*/        (multiexception = (MultiException)servicehandle).addError(new IllegalStateException((new StringBuilder("Unable to perform operation: ")).append(s).append(" on ").append(implClass.getName()).toString()));
/* 388*/        throw multiexception;
                MultiException multiexception1;
/* 391*/        (multiexception1 = new MultiException(servicehandle)).addError(new IllegalStateException((new StringBuilder("Unable to perform operation: ")).append(s).append(" on ").append(implClass.getName()).toString()));
/* 394*/        throw multiexception1;
            }

            public void dispose(Object obj)
            {
/* 404*/        preDestroyMe(obj);
/* 411*/        return;
/* 405*/        JVM INSTR dup ;
/* 406*/        obj;
/* 406*/        JVM INSTR instanceof #42  <Class MultiException>;
/* 406*/        JVM INSTR ifeq 19;
                   goto _L1 _L2
_L1:
/* 407*/        break MISSING_BLOCK_LABEL_14;
_L2:
/* 407*/        break MISSING_BLOCK_LABEL_19;
/* 407*/        throw (MultiException)obj;
/* 410*/        throw new MultiException(((Throwable) (obj)));
            }

            public List getInjectees()
            {
/* 420*/        return (List)ReflectionHelper.cast(allInjectees);
            }

            ServiceLocatorImpl getServiceLocator()
            {
/* 424*/        return locator;
            }

            Class getImplClass()
            {
/* 428*/        return implClass;
            }

            ActiveDescriptor getUnderlyingDescriptor()
            {
/* 432*/        return selfDescriptor;
            }

            public String toString()
            {
/* 436*/        return (new StringBuilder("ClazzCreator(")).append(locator).append(",").append(implClass.getName()).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ServiceLocatorImpl locator;
            private final Class implClass;
            private final Set myInitializers = new LinkedHashSet();
            private final Set myFields = new LinkedHashSet();
            private ActiveDescriptor selfDescriptor;
            private ResolutionInfo myConstructor;
            private List allInjectees;
            private Method postConstructMethod;
            private Method preDestroyMethod;
}
