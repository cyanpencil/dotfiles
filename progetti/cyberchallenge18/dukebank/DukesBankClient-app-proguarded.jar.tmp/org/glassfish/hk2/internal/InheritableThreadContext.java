// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InheritableThreadContext.java

package org.glassfish.hk2.internal;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.Logger;

public class InheritableThreadContext
    implements Context
{
    static class InheritableContextThreadWrapper
    {

                public boolean has(ActiveDescriptor activedescriptor)
                {
/* 146*/            return instances.containsKey(activedescriptor);
                }

                public Object get(ActiveDescriptor activedescriptor)
                {
/* 150*/            return instances.get(activedescriptor);
                }

                public void put(ActiveDescriptor activedescriptor, Object obj)
                {
/* 154*/            instances.put(activedescriptor, obj);
                }

                public void finalize()
                    throws Throwable
                {
/* 159*/            instances.clear();
/* 161*/            if(InheritableThreadContext.LOG_THREAD_DESTRUCTION)
/* 162*/                Logger.getLogger().debug((new StringBuilder("Removing PerThreadContext data for thread ")).append(id).toString());
                }

                private final HashMap instances;
                private final long id;

                private InheritableContextThreadWrapper()
                {
/* 141*/            instances = new HashMap();
/* 143*/            id = Thread.currentThread().getId();
                }

    }


            public InheritableThreadContext()
            {
/*  70*/        threadMap = new InheritableThreadLocal() {

                    public InheritableContextThreadWrapper initialValue()
                    {
/*  73*/                return new InheritableContextThreadWrapper();
                    }

                    public volatile Object initialValue()
                    {
/*  71*/                return initialValue();
                    }

                    final InheritableThreadContext this$0;

                    
                    {
/*  71*/                this$0 = InheritableThreadContext.this;
/*  71*/                super();
                    }
        };
            }

            public Class getScope()
            {
/*  82*/        return org/glassfish/hk2/api/InheritableThread;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
                Object obj;
/*  92*/        if((obj = ((InheritableContextThreadWrapper)threadMap.get()).get(activedescriptor)) == null)
                {
/*  94*/            obj = activedescriptor.create(servicehandle);
/*  95*/            ((InheritableContextThreadWrapper)threadMap.get()).put(activedescriptor, obj);
                }
/*  98*/        return obj;
            }

            public boolean containsKey(ActiveDescriptor activedescriptor)
            {
/* 106*/        return ((InheritableContextThreadWrapper)threadMap.get()).has(activedescriptor);
            }

            public boolean isActive()
            {
/* 114*/        return true;
            }

            public boolean supportsNullCreation()
            {
/* 122*/        return false;
            }

            public void shutdown()
            {
/* 130*/        threadMap = null;
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
            }

            private static final boolean LOG_THREAD_DESTRUCTION = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  65*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.hk2.debug.inheritablethreadcontext.log", "false")));
                }

                public final volatile Object run()
                {
/*  61*/            return run();
                }

    })).booleanValue();
            private InheritableThreadLocal threadMap;


}
