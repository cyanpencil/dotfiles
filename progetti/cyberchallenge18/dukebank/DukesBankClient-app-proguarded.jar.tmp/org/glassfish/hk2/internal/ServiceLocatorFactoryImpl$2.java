// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorFactoryImpl.java

package org.glassfish.hk2.internal;

import java.security.PrivilegedAction;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.utilities.reflection.Logger;

// Referenced classes of package org.glassfish.hk2.internal:
//            ServiceLocatorFactoryImpl

static class 
    implements PrivilegedAction
{

            public final ServiceLocatorGenerator run()
            {
/*  93*/        return ServiceLocatorFactoryImpl.access$100();
                Throwable throwable;
/*  95*/        throwable;
/*  96*/        Logger.getLogger().warning("Error finding implementation of hk2:", throwable);
/*  97*/        return null;
            }

            public final volatile Object run()
            {
/*  88*/        return run();
            }

            ()
            {
            }
}
