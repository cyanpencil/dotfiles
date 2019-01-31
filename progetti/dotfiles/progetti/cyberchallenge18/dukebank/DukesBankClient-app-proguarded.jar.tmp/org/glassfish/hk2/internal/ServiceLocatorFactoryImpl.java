// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorFactoryImpl.java

package org.glassfish.hk2.internal;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.api.ServiceLocatorListener;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.utilities.reflection.Logger;

public class ServiceLocatorFactoryImpl extends ServiceLocatorFactory
{
    static final class DefaultGeneratorInitializer
    {

                private static final ServiceLocatorGenerator defaultGenerator = ServiceLocatorFactoryImpl.getGeneratorSecure();



                private DefaultGeneratorInitializer()
                {
                }
    }


            private static ServiceLocatorGenerator getGeneratorSecure()
            {
/*  88*/        return (ServiceLocatorGenerator)AccessController.doPrivileged(new PrivilegedAction() {

                    public final ServiceLocatorGenerator run()
                    {
/*  93*/                return ServiceLocatorFactoryImpl.getGenerator();
                        Throwable throwable;
/*  95*/                throwable;
/*  96*/                Logger.getLogger().warning("Error finding implementation of hk2:", throwable);
/*  97*/                return null;
                    }

                    public final volatile Object run()
                    {
/*  88*/                return run();
                    }

        });
            }

            public ServiceLocatorFactoryImpl()
            {
            }

            private static Iterable getOSGiSafeGenerators()
            {
/* 113*/        return org.glassfish.hk2.osgiresourcelocator.ServiceLoader.lookupProviderInstances(org/glassfish/hk2/extension/ServiceLocatorGenerator);
/* 115*/        JVM INSTR pop ;
/* 117*/        return null;
            }

            private static ServiceLocatorGenerator getGenerator()
            {
                Object obj;
/* 122*/        if((obj = getOSGiSafeGenerators()) != null)
/* 129*/            if(((Iterator) (obj = ((Iterable) (obj)).iterator())).hasNext())
/* 130*/                return (ServiceLocatorGenerator)((Iterator) (obj)).next();
/* 130*/            else
/* 130*/                return null;
/* 135*/        obj = org/glassfish/hk2/internal/ServiceLocatorFactoryImpl.getClassLoader();
/* 136*/        obj = ServiceLoader.load(org/glassfish/hk2/extension/ServiceLocatorGenerator, ((ClassLoader) (obj))).iterator();
_L2:
/* 138*/        if(!((Iterator) (obj)).hasNext())
/* 140*/            break; /* Loop/switch isn't completed */
/* 140*/        return (ServiceLocatorGenerator)((Iterator) (obj)).next();
                ServiceConfigurationError serviceconfigurationerror;
/* 141*/        serviceconfigurationerror;
/* 143*/        Logger.getLogger().debug("ServiceLocatorFactoryImpl", "getGenerator", serviceconfigurationerror);
/* 145*/        if(true) goto _L2; else goto _L1
_L1:
/* 148*/        Logger.getLogger().warning("Cannot find a default implementation of the HK2 ServiceLocatorGenerator");
/* 149*/        return null;
            }

            public ServiceLocator create(String s)
            {
/* 157*/        return create(s, null, null, org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.RETURN);
            }

            public ServiceLocator find(String s)
            {
/* 165*/        Object obj = lock;
/* 165*/        JVM INSTR monitorenter ;
/* 166*/        return (ServiceLocator)serviceLocators.get(s);
/* 167*/        s;
/* 167*/        throw s;
            }

            public void destroy(String s)
            {
/* 175*/        destroy(s, null);
            }

            private void destroy(String s, ServiceLocator servicelocator)
            {
/* 179*/        ServiceLocator servicelocator1 = null;
/* 181*/        synchronized(lock)
                {
/* 182*/            if(s != null)
/* 183*/                servicelocator1 = (ServiceLocator)serviceLocators.remove(s);
/* 186*/            if(DEBUG_SERVICE_LOCATOR_LIFECYCLE)
/* 187*/                Logger.getLogger().debug((new StringBuilder("ServiceFactoryImpl destroying locator with name ")).append(s).append(" and locator ").append(servicelocator).append(" with found locator ").append(servicelocator1).toString(), new Throwable());
/* 192*/            if(servicelocator1 == null)
/* 193*/                servicelocator1 = servicelocator;
/* 196*/            if(servicelocator1 != null)
/* 197*/                for(s = listeners.iterator(); s.hasNext();)
                        {
/* 197*/                    servicelocator = (ServiceLocatorListener)s.next();
/* 199*/                    try
                            {
/* 199*/                        servicelocator.locatorDestroyed(servicelocator1);
                            }
/* 201*/                    catch(Throwable throwable)
                            {
/* 202*/                        Logger.getLogger().debug(getClass().getName(), (new StringBuilder("destroy ")).append(servicelocator).toString(), throwable);
                            }
                        }

                }
/* 208*/        if(servicelocator1 != null)
/* 209*/            servicelocator1.shutdown();
            }

            public void destroy(ServiceLocator servicelocator)
            {
/* 214*/        if(servicelocator == null)
                {
/* 214*/            return;
                } else
                {
/* 216*/            destroy(servicelocator.getName(), servicelocator);
/* 217*/            return;
                }
            }

            public ServiceLocator create(String s, ServiceLocator servicelocator)
            {
/* 225*/        return create(s, servicelocator, null, org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.RETURN);
            }

            private static String getGeneratedName()
            {
/* 229*/        Object obj = sLock;
/* 229*/        JVM INSTR monitorenter ;
/* 230*/        return (new StringBuilder("__HK2_Generated_")).append(name_count++).toString();
                Exception exception;
/* 231*/        exception;
/* 231*/        throw exception;
            }

            public ServiceLocator create(String s, ServiceLocator servicelocator, ServiceLocatorGenerator servicelocatorgenerator)
            {
/* 242*/        return create(s, servicelocator, servicelocatorgenerator, org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.RETURN);
            }

            private void callListenerAdded(ServiceLocator servicelocator)
            {
/* 246*/        for(Iterator iterator = listeners.iterator(); iterator.hasNext();)
                {
/* 246*/            ServiceLocatorListener servicelocatorlistener = (ServiceLocatorListener)iterator.next();
/* 248*/            try
                    {
/* 248*/                servicelocatorlistener.locatorAdded(servicelocator);
                    }
/* 250*/            catch(Throwable throwable)
                    {
/* 251*/                Logger.getLogger().debug(getClass().getName(), (new StringBuilder("create ")).append(servicelocatorlistener).toString(), throwable);
                    }
                }

            }

            public ServiceLocator create(String s, ServiceLocator servicelocator, ServiceLocatorGenerator servicelocatorgenerator, org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy createpolicy)
            {
/* 259*/        if(DEBUG_SERVICE_LOCATOR_LIFECYCLE)
/* 260*/            Logger.getLogger().debug((new StringBuilder("ServiceFactoryImpl given create of ")).append(s).append(" with parent ").append(servicelocator).append(" with generator ").append(servicelocatorgenerator).append(" and policy ").append(createpolicy).toString(), new Throwable());
/* 263*/        Object obj = lock;
/* 263*/        JVM INSTR monitorenter ;
/* 266*/        if(s != null)
/* 267*/            break MISSING_BLOCK_LABEL_125;
/* 267*/        s = getGeneratedName();
/* 268*/        s = internalCreate(s, servicelocator, servicelocatorgenerator);
/* 269*/        callListenerAdded(s);
/* 270*/        if(DEBUG_SERVICE_LOCATOR_LIFECYCLE)
/* 271*/            Logger.getLogger().debug((new StringBuilder("ServiceFactoryImpl added untracked listener ")).append(s).toString());
/* 273*/        return s;
                ServiceLocator servicelocator1;
/* 276*/        if((servicelocator1 = (ServiceLocator)serviceLocators.get(s)) == null)
/* 278*/            break MISSING_BLOCK_LABEL_242;
/* 278*/        if(createpolicy != null && !org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.RETURN.equals(createpolicy)) goto _L2; else goto _L1
_L1:
/* 279*/        if(DEBUG_SERVICE_LOCATOR_LIFECYCLE)
/* 280*/            Logger.getLogger().debug((new StringBuilder("ServiceFactoryImpl added found listener under RETURN policy of ")).append(servicelocator1).toString());
/* 282*/        servicelocator1;
/* 282*/        obj;
/* 282*/        JVM INSTR monitorexit ;
/* 282*/        return;
_L2:
/* 285*/        if(createpolicy.equals(org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.DESTROY))
/* 286*/            destroy(servicelocator1);
/* 289*/        else
/* 289*/            throw new IllegalStateException((new StringBuilder("A ServiceLocator named ")).append(s).append(" already exists").toString());
/* 293*/        servicelocator1 = internalCreate(s, servicelocator, servicelocatorgenerator);
/* 294*/        serviceLocators.put(s, servicelocator1);
/* 296*/        callListenerAdded(servicelocator1);
/* 298*/        if(DEBUG_SERVICE_LOCATOR_LIFECYCLE)
/* 299*/            Logger.getLogger().debug((new StringBuilder("ServiceFactoryImpl created locator ")).append(servicelocator1).toString());
/* 301*/        servicelocator1;
/* 301*/        obj;
/* 301*/        JVM INSTR monitorexit ;
/* 301*/        return;
/* 302*/        s;
/* 302*/        throw s;
            }

            private ServiceLocator internalCreate(String s, ServiceLocator servicelocator, ServiceLocatorGenerator servicelocatorgenerator)
            {
/* 306*/        if(servicelocatorgenerator == null)
                {
/* 307*/            if(DefaultGeneratorInitializer.defaultGenerator == null)
/* 308*/                throw new IllegalStateException("No generator was provided and there is no default generator registered");
/* 310*/            servicelocatorgenerator = DefaultGeneratorInitializer.defaultGenerator;
                }
/* 312*/        return servicelocatorgenerator.create(s, servicelocator);
            }

            public void addListener(ServiceLocatorListener servicelocatorlistener)
            {
/* 317*/label0:
                {
/* 317*/            if(servicelocatorlistener == null)
/* 317*/                throw new IllegalArgumentException();
                    HashSet hashset;
                    Throwable throwable;
/* 319*/            synchronized(lock)
                    {
/* 320*/                if(!listeners.contains(servicelocatorlistener))
/* 320*/                    break label0;
                    }
/* 320*/            return;
                }
/* 323*/        try
                {
/* 323*/            hashset = new HashSet(serviceLocators.values());
/* 324*/            servicelocatorlistener.initialize(Collections.unmodifiableSet(hashset));
/* 330*/            break MISSING_BLOCK_LABEL_95;
                }
                // Misplaced declaration of an exception variable
/* 326*/        catch(Throwable throwable)
                {
/* 328*/            Logger.getLogger().debug(getClass().getName(), (new StringBuilder("addListener ")).append(servicelocatorlistener).toString(), throwable);
                }
/* 329*/        obj;
/* 329*/        JVM INSTR monitorexit ;
/* 329*/        return;
/* 332*/        listeners.add(servicelocatorlistener);
/* 333*/        obj;
/* 333*/        JVM INSTR monitorexit ;
            }

            public void removeListener(ServiceLocatorListener servicelocatorlistener)
            {
/* 339*/        if(servicelocatorlistener == null)
/* 339*/            throw new IllegalArgumentException();
/* 341*/        synchronized(lock)
                {
/* 342*/            listeners.remove(servicelocatorlistener);
                }
            }

            private static final String DEBUG_SERVICE_LOCATOR_PROPERTY = "org.jvnet.hk2.properties.debug.service.locator.lifecycle";
            private static final boolean DEBUG_SERVICE_LOCATOR_LIFECYCLE = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  70*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.service.locator.lifecycle", "false")));
                }

                public final volatile Object run()
                {
/*  67*/            return run();
                }

    })).booleanValue();
            private static final Object sLock = new Object();
            private static int name_count = 0;
            private static final String GENERATED_NAME_PREFIX = "__HK2_Generated_";
            private final Object lock = new Object();
            private final HashMap serviceLocators = new HashMap();
            private final HashSet listeners = new HashSet();



}
