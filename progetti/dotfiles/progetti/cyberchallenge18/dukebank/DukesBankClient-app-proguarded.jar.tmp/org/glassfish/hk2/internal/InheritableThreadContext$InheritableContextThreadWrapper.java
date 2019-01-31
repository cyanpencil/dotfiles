// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InheritableThreadContext.java

package org.glassfish.hk2.internal;

import java.security.PrivilegedAction;
import java.util.HashMap;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.glassfish.hk2.internal:
//            InheritableThreadContext

static class id
{

            public boolean has(ActiveDescriptor activedescriptor)
            {
/* 146*/        return instances.containsKey(activedescriptor);
            }

            public Object get(ActiveDescriptor activedescriptor)
            {
/* 150*/        return instances.get(activedescriptor);
            }

            public void put(ActiveDescriptor activedescriptor, Object obj)
            {
/* 154*/        instances.put(activedescriptor, obj);
            }

            public void finalize()
                throws Throwable
            {
/* 159*/        instances.clear();
/* 161*/        if(InheritableThreadContext.access$100())
/* 162*/            Logger.getLogger().debug((new StringBuilder("Removing PerThreadContext data for thread ")).append(id).toString());
            }

            private final HashMap instances;
            private final long id;

            private Y()
            {
/* 141*/        instances = new HashMap();
/* 143*/        id = Thread.currentThread().getId();
            }


            // Unreferenced inner class org/glassfish/hk2/internal/InheritableThreadContext$1

/* anonymous class */
    static class InheritableThreadContext._cls1
        implements PrivilegedAction
    {

                public final Boolean run()
                {
/*  65*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.hk2.debug.inheritablethreadcontext.log", "false")));
                }

                public final volatile Object run()
                {
/*  61*/            return run();
                }

    }

}
