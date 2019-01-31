// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonContext.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.utilities.ContextualInput;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            SingletonContext, SystemDescriptor

class this._cls0
    implements Computable
{

            public Object compute(ContextualInput contextualinput)
            {
                ActiveDescriptor activedescriptor;
                Object obj;
/*  76*/        if((obj = (activedescriptor = contextualinput.getDescriptor()).getCache()) != null)
/*  80*/            return obj;
/*  83*/        contextualinput = ((ContextualInput) (activedescriptor.create(contextualinput.getRoot())));
/*  84*/        activedescriptor.setCache(contextualinput);
/*  85*/        if(activedescriptor instanceof SystemDescriptor)
/*  86*/            ((SystemDescriptor)activedescriptor).setSingletonGeneration(SingletonContext.access$008(SingletonContext.this));
/*  89*/        return contextualinput;
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*  71*/        return compute((ContextualInput)obj);
            }

            final SingletonContext this$0;

            ionErrorException()
            {
/*  71*/        this$0 = SingletonContext.this;
/*  71*/        super();
            }
}
