// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonContext.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.utilities.ContextualInput;
import org.glassfish.hk2.utilities.cache.Cache;

// Referenced classes of package org.jvnet.hk2.internal:
//            SingletonContext

class this._cls0
    implements org.glassfish.hk2.utilities.cache.
{

            public void handleCycle(ContextualInput contextualinput)
            {
/*  95*/        throw new MultiException(new IllegalStateException((new StringBuilder("A circular dependency involving Singleton service ")).append(contextualinput.getDescriptor().getImplementation()).append(" was found.  Full descriptor is ").append(contextualinput.getDescriptor()).toString()));
            }

            public volatile void handleCycle(Object obj)
            {
/*  91*/        handleCycle((ContextualInput)obj);
            }

            final SingletonContext this$0;

            t()
            {
/*  91*/        this$0 = SingletonContext.this;
/*  91*/        super();
            }
}
