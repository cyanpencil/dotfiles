// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonContext.java

package org.jvnet.hk2.internal;

import java.io.Serializable;
import java.util.*;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.ContextualInput;
import org.glassfish.hk2.utilities.cache.*;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl, SystemDescriptor

public class SingletonContext
    implements Context
{
    static class GenerationComparator
        implements Serializable, Comparator
    {

                public int compare(SystemDescriptor systemdescriptor, SystemDescriptor systemdescriptor1)
                {
/* 221*/            if(systemdescriptor.getSingletonGeneration() > systemdescriptor1.getSingletonGeneration())
/* 222*/                return -1;
/* 224*/            return systemdescriptor.getSingletonGeneration() != systemdescriptor1.getSingletonGeneration() ? 1 : 0;
                }

                public volatile int compare(Object obj, Object obj1)
                {
/* 211*/            return compare((SystemDescriptor)obj, (SystemDescriptor)obj1);
                }

                private static final long serialVersionUID = 0x9fcd324f5b74ead5L;

                private GenerationComparator()
                {
                }

    }


            SingletonContext(ServiceLocatorImpl servicelocatorimpl)
            {
/*  67*/        generationNumber = 0x80000000;
/* 102*/        locator = servicelocatorimpl;
            }

            public Class getScope()
            {
/* 110*/        return javax/inject/Singleton;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
/* 122*/        return valueCache.compute(new ContextualInput(activedescriptor, servicehandle));
/* 123*/        JVM INSTR dup ;
/* 124*/        activedescriptor;
/* 124*/        JVM INSTR instanceof #15  <Class MultiException>;
/* 124*/        JVM INSTR ifeq 30;
                   goto _L1 _L2
_L1:
/* 125*/        break MISSING_BLOCK_LABEL_25;
_L2:
/* 125*/        break MISSING_BLOCK_LABEL_30;
/* 125*/        throw (MultiException)activedescriptor;
/* 127*/        throw new MultiException(activedescriptor);
            }

            public boolean containsKey(ActiveDescriptor activedescriptor)
            {
/* 137*/        return valueCache.containsKey(new ContextualInput(activedescriptor, null));
            }

            public boolean isActive()
            {
/* 145*/        return true;
            }

            public boolean supportsNullCreation()
            {
/* 153*/        return false;
            }

            public void shutdown()
            {
                Object obj;
                long l;
                TreeSet treeset;
/* 162*/        obj = locator.getDescriptors(BuilderHelper.allFilter());
/* 164*/        l = locator.getLocatorId();
/* 166*/        treeset = new TreeSet(new GenerationComparator());
/* 168*/        obj = ((List) (obj)).iterator();
_L3:
                ActiveDescriptor activedescriptor;
/* 168*/label0:
                {
/* 168*/            if(!((Iterator) (obj)).hasNext())
/* 168*/                break; /* Loop/switch isn't completed */
/* 168*/            if((activedescriptor = (ActiveDescriptor)((Iterator) (obj)).next()).getScope() == null || !activedescriptor.getScope().equals(javax/inject/Singleton.getName()))
/* 171*/                continue; /* Loop/switch isn't completed */
/* 171*/            synchronized(this)
                    {
/* 172*/                if(activedescriptor.getCache() != null)
/* 172*/                    break label0;
                    }
/* 172*/            continue; /* Loop/switch isn't completed */
                }
/* 173*/        singletoncontext;
/* 173*/        JVM INSTR monitorexit ;
                  goto _L1
/* 173*/        exception;
/* 173*/        throw exception;
_L1:
/* 175*/        if(activedescriptor.getLocatorId() != null && activedescriptor.getLocatorId().longValue() == l)
                {
/* 177*/            SystemDescriptor systemdescriptor1 = (SystemDescriptor)activedescriptor;
/* 179*/            treeset.add(systemdescriptor1);
                }
/* 180*/        if(true) goto _L3; else goto _L2
_L2:
                SystemDescriptor systemdescriptor;
/* 182*/        for(Iterator iterator = treeset.iterator(); iterator.hasNext(); destroyOne(systemdescriptor))
/* 182*/            systemdescriptor = (SystemDescriptor)iterator.next();

/* 185*/        return;
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
/* 196*/        valueCache.remove(new ContextualInput(activedescriptor, null));
/* 197*/        Object obj = activedescriptor.getCache();
/* 198*/        activedescriptor.releaseCache();
/* 200*/        if(obj == null)
/* 200*/            return;
/* 203*/        try
                {
/* 203*/            activedescriptor.dispose(obj);
/* 207*/            return;
                }
                // Misplaced declaration of an exception variable
/* 205*/        catch(ActiveDescriptor activedescriptor)
                {
/* 206*/            Logger.getLogger().debug("SingletonContext", "releaseOne", activedescriptor);
                }
            }

            private int generationNumber;
            private final ServiceLocatorImpl locator;
            private final Cache valueCache = new Cache(new Computable() {

                public Object compute(ContextualInput contextualinput)
                {
                    ActiveDescriptor activedescriptor;
                    Object obj;
/*  76*/            if((obj = (activedescriptor = contextualinput.getDescriptor()).getCache()) != null)
/*  80*/                return obj;
/*  83*/            contextualinput = ((ContextualInput) (activedescriptor.create(contextualinput.getRoot())));
/*  84*/            activedescriptor.setCache(contextualinput);
/*  85*/            if(activedescriptor instanceof SystemDescriptor)
/*  86*/                ((SystemDescriptor)activedescriptor).setSingletonGeneration(generationNumber++);
/*  89*/            return contextualinput;
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  71*/            return compute((ContextualInput)obj);
                }

                final SingletonContext this$0;

                    
                    {
/*  71*/                this$0 = SingletonContext.this;
/*  71*/                super();
                    }
    }, new org.glassfish.hk2.utilities.cache.Cache.CycleHandler() {

                public void handleCycle(ContextualInput contextualinput)
                {
/*  95*/            throw new MultiException(new IllegalStateException((new StringBuilder("A circular dependency involving Singleton service ")).append(contextualinput.getDescriptor().getImplementation()).append(" was found.  Full descriptor is ").append(contextualinput.getDescriptor()).toString()));
                }

                public volatile void handleCycle(Object obj)
                {
/*  91*/            handleCycle((ContextualInput)obj);
                }

                final SingletonContext this$0;

                    
                    {
/*  91*/                this$0 = SingletonContext.this;
/*  91*/                super();
                    }
    });

}
