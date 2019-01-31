// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerThreadContext.java

package org.glassfish.hk2.internal;

import java.security.PrivilegedAction;
import java.util.HashMap;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.glassfish.hk2.internal:
//            PerThreadContext

static class id
{

            public boolean has(ActiveDescriptor activedescriptor)
            {
/* 148*/        return instances.containsKey(activedescriptor);
            }

            public Object get(ActiveDescriptor activedescriptor)
            {
/* 152*/        return instances.get(activedescriptor);
            }

            public void put(ActiveDescriptor activedescriptor, Object obj)
            {
/* 156*/        instances.put(activedescriptor, obj);
            }

            public void finalize()
                throws Throwable
            {
/* 161*/        instances.clear();
/* 163*/        if(PerThreadContext.access$100())
/* 164*/            Logger.getLogger().debug((new StringBuilder("Removing PerThreadContext data for thread ")).append(id).toString());
            }

            private final HashMap instances;
            private final long id;

            private I()
            {
/* 143*/        instances = new HashMap();
/* 145*/        id = Thread.currentThread().getId();
            }


            // Unreferenced inner class org/glassfish/hk2/internal/PerThreadContext$1

/* anonymous class */
    static class PerThreadContext._cls1
        implements PrivilegedAction
    {

                public final Boolean run()
                {
/*  67*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.hk2.debug.perthreadcontext.log", "false")));
                }

                public final volatile Object run()
                {
/*  63*/            return run();
                }

    }

}
