// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FactoryCreator.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.general.ThreadSpecificObject;
import org.glassfish.hk2.utilities.reflection.Pretty;

// Referenced classes of package org.jvnet.hk2.internal:
//            Creator, InstanceLifecycleEventImpl, InstantiationServiceImpl, ServiceHandleImpl, 
//            SystemDescriptor

public class FactoryCreator
    implements Creator
{

            FactoryCreator(ServiceLocator servicelocator, ActiveDescriptor activedescriptor)
            {
/*  73*/        locator = servicelocator;
/*  74*/        factoryDescriptor = activedescriptor;
/*  76*/        if(!activedescriptor.isReified())
/*  77*/            activedescriptor = servicelocator.reifyDescriptor(activedescriptor);
/*  80*/        InstantiationServiceImpl instantiationserviceimpl = null;
/*  81*/        activedescriptor = activedescriptor.getInjectees().iterator();
/*  81*/        do
                {
/*  81*/            if(!activedescriptor.hasNext())
/*  81*/                break;
/*  81*/            Injectee injectee = (Injectee)activedescriptor.next();
/*  82*/            if(!org/glassfish/hk2/api/InstantiationService.equals(injectee.getRequiredType()))
/*  83*/                continue;
/*  83*/            instantiationserviceimpl = (InstantiationServiceImpl)servicelocator.getService(org/jvnet/hk2/internal/InstantiationServiceImpl, new Annotation[0]);
/*  84*/            break;
                } while(true);
/*  89*/        instantiationService = instantiationserviceimpl;
            }

            public List getInjectees()
            {
/*  97*/        return Collections.emptyList();
            }

            private ServiceHandle getFactoryHandle()
            {
/* 103*/        return locator.getServiceHandle(factoryDescriptor);
                Throwable throwable;
/* 105*/        throwable;
/* 106*/        throw new MultiException(throwable);
            }

            public Object create(ServiceHandle servicehandle, SystemDescriptor systemdescriptor)
                throws MultiException
            {
                Object obj;
                ThreadSpecificObject threadspecificobject;
/* 115*/        obj = getFactoryHandle();
/* 117*/        systemdescriptor.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.PRE_PRODUCTION, null, systemdescriptor));
/* 120*/        threadspecificobject = new ThreadSpecificObject(((ServiceHandle) (obj)).getActiveDescriptor());
/* 122*/        if(cycleFinder.containsKey(threadspecificobject))
                {
/* 123*/            obj = new HashSet();
/* 124*/            Iterator iterator = cycleFinder.keySet().iterator();
/* 124*/            do
                    {
/* 124*/                if(!iterator.hasNext())
/* 124*/                    break;
/* 124*/                if((servicehandle = (ThreadSpecificObject)iterator.next()).getThreadIdentifier() == threadspecificobject.getThreadIdentifier())
/* 126*/                    ((HashSet) (obj)).add(((ActiveDescriptor)servicehandle.getIncomingObject()).getImplementation());
                    } while(true);
/* 129*/            throw new AssertionError((new StringBuilder("A cycle was detected involving these Factory implementations: ")).append(Pretty.collection(((java.util.Collection) (obj)))).toString());
                }
/* 132*/        cycleFinder.put(threadspecificobject, MAP_VALUE);
/* 135*/        obj = (Factory)((ServiceHandle) (obj)).getService();
/* 138*/        cycleFinder.remove(threadspecificobject);
/* 139*/        break MISSING_BLOCK_LABEL_200;
/* 138*/        servicehandle;
/* 138*/        cycleFinder.remove(threadspecificobject);
/* 138*/        throw servicehandle;
/* 141*/        if(instantiationService != null)
                {
/* 142*/            Injectee injectee = null;
/* 143*/            if(servicehandle != null && (servicehandle instanceof ServiceHandleImpl))
/* 144*/                injectee = ((ServiceHandleImpl)servicehandle).getOriginalRequest();
/* 148*/            instantiationService.pushInjecteeParent(injectee);
                }
/* 153*/        Object obj1 = ((Factory) (obj)).provide();
/* 156*/        if(instantiationService != null)
/* 157*/            instantiationService.popInjecteeParent();
/* 157*/        break MISSING_BLOCK_LABEL_281;
/* 156*/        servicehandle;
/* 156*/        if(instantiationService != null)
/* 157*/            instantiationService.popInjecteeParent();
/* 157*/        throw servicehandle;
/* 161*/        systemdescriptor.invokeInstanceListeners(new InstanceLifecycleEventImpl(InstanceLifecycleEventType.POST_PRODUCTION, obj1, systemdescriptor));
/* 164*/        return obj1;
            }

            public void dispose(Object obj)
            {
                Object obj1;
/* 173*/        ((Factory) (obj1 = (Factory)((ServiceHandle) (obj1 = getFactoryHandle())).getService())).dispose(obj);
/* 185*/        return;
/* 179*/        JVM INSTR dup ;
                Throwable throwable;
/* 180*/        throwable;
/* 180*/        JVM INSTR instanceof #22  <Class MultiException>;
/* 180*/        JVM INSTR ifeq 36;
                   goto _L1 _L2
_L1:
/* 181*/        break MISSING_BLOCK_LABEL_31;
_L2:
/* 181*/        break MISSING_BLOCK_LABEL_36;
/* 181*/        throw (MultiException)throwable;
/* 184*/        throw new MultiException(throwable);
            }

            public String toString()
            {
/* 189*/        return (new StringBuilder("FactoryCreator(")).append(locator).append(",").append(factoryDescriptor).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private static final Object MAP_VALUE = new Object();
            private final ConcurrentHashMap cycleFinder = new ConcurrentHashMap();
            private final ServiceLocator locator;
            private final ActiveDescriptor factoryDescriptor;
            private final InstantiationServiceImpl instantiationService;

}
