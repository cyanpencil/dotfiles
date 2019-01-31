// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerThreadContext.java

package org.glassfish.hk2.internal;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.general.Hk2ThreadLocal;
import org.glassfish.hk2.utilities.reflection.Logger;

public class PerThreadContext
    implements Context
{
    static class PerContextThreadWrapper
    {

                public boolean has(ActiveDescriptor activedescriptor)
                {
/* 148*/            return instances.containsKey(activedescriptor);
                }

                public Object get(ActiveDescriptor activedescriptor)
                {
/* 152*/            return instances.get(activedescriptor);
                }

                public void put(ActiveDescriptor activedescriptor, Object obj)
                {
/* 156*/            instances.put(activedescriptor, obj);
                }

                public void finalize()
                    throws Throwable
                {
/* 161*/            instances.clear();
/* 163*/            if(PerThreadContext.LOG_THREAD_DESTRUCTION)
/* 164*/                Logger.getLogger().debug((new StringBuilder("Removing PerThreadContext data for thread ")).append(id).toString());
                }

                private final HashMap instances;
                private final long id;

                private PerContextThreadWrapper()
                {
/* 143*/            instances = new HashMap();
/* 145*/            id = Thread.currentThread().getId();
                }

    }


            public PerThreadContext()
            {
            }

            public Class getScope()
            {
/*  84*/        return org/glassfish/hk2/api/PerThread;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
                Object obj;
/*  94*/        if((obj = ((PerContextThreadWrapper)threadMap.get()).get(activedescriptor)) == null)
                {
/*  96*/            obj = activedescriptor.create(servicehandle);
/*  97*/            ((PerContextThreadWrapper)threadMap.get()).put(activedescriptor, obj);
                }
/* 100*/        return obj;
            }

            public boolean containsKey(ActiveDescriptor activedescriptor)
            {
/* 108*/        return ((PerContextThreadWrapper)threadMap.get()).has(activedescriptor);
            }

            public boolean isActive()
            {
/* 116*/        return true;
            }

            public boolean supportsNullCreation()
            {
/* 124*/        return false;
            }

            public void shutdown()
            {
/* 132*/        threadMap.removeAll();
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
            }

            private static final boolean LOG_THREAD_DESTRUCTION = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  67*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.hk2.debug.perthreadcontext.log", "false")));
                }

                public final volatile Object run()
                {
/*  63*/            return run();
                }

    })).booleanValue();
            private final Hk2ThreadLocal threadMap = new Hk2ThreadLocal() {

                public PerContextThreadWrapper initialValue()
                {
/*  75*/            return new PerContextThreadWrapper();
                }

                public volatile Object initialValue()
                {
/*  73*/            return initialValue();
                }

                final PerThreadContext this$0;

                    
                    {
/*  73*/                this$0 = PerThreadContext.this;
/*  73*/                super();
                    }
    };


}
