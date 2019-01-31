// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateContext.java

package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.util.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.internal.HandleAndService;
import org.glassfish.hk2.internal.ImmediateLocalLocatorFilter;

// Referenced classes of package org.glassfish.hk2.utilities:
//            ImmediateErrorHandler

public class ImmediateContext
    implements Context
{

            private ImmediateContext(ServiceLocator servicelocator)
            {
/*  83*/        locator = servicelocator;
/*  84*/        validationFilter = new ImmediateLocalLocatorFilter(servicelocator.getLocatorId());
            }

            public Class getScope()
            {
/*  89*/        return org/glassfish/hk2/api/Immediate;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
/* 103*/        ImmediateContext immediatecontext = this;
/* 103*/        JVM INSTR monitorenter ;
                HandleAndService handleandservice;
/* 104*/        if((handleandservice = (HandleAndService)currentImmediateServices.get(activedescriptor)) != null)
/* 106*/            return handleandservice.getService();
/* 109*/        while(creating.containsKey(activedescriptor)) 
                {
                    long l;
/* 110*/            if((l = ((Long)creating.get(activedescriptor)).longValue()) == Thread.currentThread().getId())
/* 112*/                throw new MultiException(new IllegalStateException((new StringBuilder("A circular dependency involving Immediate service ")).append(activedescriptor.getImplementation()).append(" was found.  Full descriptor is ").append(activedescriptor).toString()));
/* 118*/            try
                    {
/* 118*/                wait();
                    }
                    // Misplaced declaration of an exception variable
/* 120*/            catch(ActiveDescriptor activedescriptor)
                    {
/* 121*/                throw new MultiException(activedescriptor);
                    }
                }
/* 126*/        if((handleandservice = (HandleAndService)currentImmediateServices.get(activedescriptor)) == null) goto _L2; else goto _L1
_L1:
/* 128*/        handleandservice.getService();
/* 128*/        immediatecontext;
/* 128*/        JVM INSTR monitorexit ;
/* 128*/        return;
_L2:
/* 131*/        creating.put(activedescriptor, Long.valueOf(Thread.currentThread().getId()));
/* 132*/        immediatecontext;
/* 132*/        JVM INSTR monitorexit ;
/* 132*/        break MISSING_BLOCK_LABEL_187;
/* 132*/        activedescriptor;
/* 132*/        throw activedescriptor;
/* 135*/        Object obj = activedescriptor.create(servicehandle);
/* 138*/        synchronized(this)
                {
/* 139*/            ServiceHandle servicehandle1 = null;
/* 140*/            if(servicehandle != null && servicehandle.getActiveDescriptor().equals(activedescriptor))
/* 142*/                servicehandle1 = servicehandle;
/* 146*/            if(obj != null)
/* 147*/                currentImmediateServices.put(activedescriptor, new HandleAndService(servicehandle1, obj));
/* 150*/            creating.remove(activedescriptor);
/* 151*/            notifyAll();
                }
/* 138*/        break MISSING_BLOCK_LABEL_319;
/* 138*/        obj;
/* 138*/        synchronized(this)
                {
/* 140*/            if(servicehandle != null)
/* 141*/                servicehandle.getActiveDescriptor().equals(activedescriptor);
/* 150*/            creating.remove(activedescriptor);
/* 151*/            notifyAll();
                }
/* 152*/        throw obj;
/* 156*/        return obj;
            }

            public synchronized boolean containsKey(ActiveDescriptor activedescriptor)
            {
/* 165*/        return currentImmediateServices.containsKey(activedescriptor);
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
/* 170*/        destroyOne(activedescriptor, null);
            }

            private void destroyOne(ActiveDescriptor activedescriptor, List list)
            {
/* 181*/        if(list == null)
/* 182*/            list = locator.getAllServices(org/glassfish/hk2/utilities/ImmediateErrorHandler, new Annotation[0]);
/* 185*/        synchronized(this)
                {
                    Object obj;
/* 186*/            obj = ((HandleAndService) (obj = (HandleAndService)currentImmediateServices.remove(activedescriptor))).getService();
/* 190*/            try
                    {
/* 190*/                activedescriptor.dispose(obj);
                    }
/* 192*/            catch(Throwable throwable)
                    {
/* 193*/                for(list = list.iterator(); list.hasNext();)
                        {
/* 193*/                    ImmediateErrorHandler immediateerrorhandler = (ImmediateErrorHandler)list.next();
/* 195*/                    try
                            {
/* 195*/                        immediateerrorhandler.preDestroyFailed(activedescriptor, throwable);
                            }
/* 197*/                    catch(Throwable _ex) { }
                        }

                    }
                }
            }

            public boolean supportsNullCreation()
            {
/* 209*/        return false;
            }

            public boolean isActive()
            {
/* 214*/        return true;
            }

            public void shutdown()
            {
/* 222*/        List list = locator.getAllServices(org/glassfish/hk2/utilities/ImmediateErrorHandler, new Annotation[0]);
/* 224*/        synchronized(this)
                {
                    java.util.Map.Entry entry;
                    Object obj;
/* 226*/            for(Iterator iterator = (new HashSet(currentImmediateServices.entrySet())).iterator(); iterator.hasNext();)
/* 226*/                if((obj = ((HandleAndService) (obj = (HandleAndService)(entry = (java.util.Map.Entry)iterator.next()).getValue())).getHandle()) != null)
/* 232*/                    ((ServiceHandle) (obj)).destroy();
/* 235*/                else
/* 235*/                    destroyOne((ActiveDescriptor)entry.getKey(), list);

                }
            }

            private List getImmediateServices()
            {
                List list;
/* 247*/        try
                {
/* 247*/            list = locator.getDescriptors(validationFilter);
                }
/* 249*/        catch(IllegalStateException _ex)
                {
/* 251*/            list = Collections.emptyList();
                }
/* 254*/        return list;
            }

            public Filter getValidationFilter()
            {
/* 258*/        return validationFilter;
            }

            public void doWork()
            {
/* 262*/        Object obj = getImmediateServices();
                List list;
/* 266*/        try
                {
/* 266*/            list = locator.getAllServices(org/glassfish/hk2/utilities/ImmediateErrorHandler, new Annotation[0]);
                }
/* 268*/        catch(IllegalStateException _ex)
                {
/* 270*/            return;
                }
/* 274*/        LinkedHashSet linkedhashset1 = new LinkedHashSet(((java.util.Collection) (obj)));
/* 275*/        LinkedHashSet linkedhashset2 = new LinkedHashSet();
/* 277*/        synchronized(this)
                {
/* 279*/            while(creating.size() > 0) 
/* 281*/                try
                        {
/* 281*/                    wait();
                        }
                        // Misplaced declaration of an exception variable
/* 283*/                catch(Object obj)
                        {
/* 284*/                    throw new RuntimeException(((Throwable) (obj)));
                        }
/* 288*/            LinkedHashSet linkedhashset = new LinkedHashSet(currentImmediateServices.keySet());
/* 290*/            obj = ((List) (obj)).iterator();
/* 290*/            do
                    {
/* 290*/                if(!((Iterator) (obj)).hasNext())
/* 290*/                    break;
/* 290*/                ActiveDescriptor activedescriptor1 = (ActiveDescriptor)((Iterator) (obj)).next();
/* 291*/                if(!linkedhashset.contains(activedescriptor1))
/* 292*/                    linkedhashset2.add(activedescriptor1);
                    } while(true);
/* 296*/            linkedhashset.removeAll(linkedhashset1);
/* 298*/            for(Iterator iterator = linkedhashset.iterator(); iterator.hasNext();)
                    {
/* 298*/                ActiveDescriptor activedescriptor2 = (ActiveDescriptor)iterator.next();
                        HandleAndService handleandservice;
                        ServiceHandle servicehandle;
/* 299*/                if((servicehandle = (handleandservice = (HandleAndService)currentImmediateServices.get(activedescriptor2)).getHandle()) != null)
/* 303*/                    servicehandle.destroy();
/* 306*/                else
/* 306*/                    destroyOne(activedescriptor2, list);
                    }

                }
/* 311*/        for(Iterator iterator2 = linkedhashset2.iterator(); iterator2.hasNext();)
                {
/* 311*/            ActiveDescriptor activedescriptor = (ActiveDescriptor)iterator2.next();
/* 314*/            try
                    {
/* 314*/                locator.getServiceHandle(activedescriptor).getService();
                    }
/* 316*/            catch(Throwable throwable)
                    {
/* 317*/                Iterator iterator1 = list.iterator();
/* 317*/                while(iterator1.hasNext()) 
                        {
/* 317*/                    ImmediateErrorHandler immediateerrorhandler = (ImmediateErrorHandler)iterator1.next();
/* 319*/                    try
                            {
/* 319*/                        immediateerrorhandler.postConstructFailed(activedescriptor, throwable);
                            }
/* 321*/                    catch(Throwable _ex) { }
                        }
                    }
                }

            }

            private final HashMap currentImmediateServices = new HashMap();
            private final HashMap creating = new HashMap();
            private final ServiceLocator locator;
            private final Filter validationFilter;
}
