// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingletonContext.java

package org.jvnet.hk2.internal;

import java.io.Serializable;
import java.util.Comparator;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.utilities.ContextualInput;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            SingletonContext, SystemDescriptor

static class 
    implements Serializable, Comparator
{

            public int compare(SystemDescriptor systemdescriptor, SystemDescriptor systemdescriptor1)
            {
/* 221*/        if(systemdescriptor.getSingletonGeneration() > systemdescriptor1.getSingletonGeneration())
/* 222*/            return -1;
/* 224*/        return systemdescriptor.getSingletonGeneration() != systemdescriptor1.getSingletonGeneration() ? 1 : 0;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 211*/        return compare((SystemDescriptor)obj, (SystemDescriptor)obj1);
            }

            private static final long serialVersionUID = 0x9fcd324f5b74ead5L;

            private ()
            {
            }


            // Unreferenced inner class org/jvnet/hk2/internal/SingletonContext$1

/* anonymous class */
    class SingletonContext._cls1
        implements Computable
    {

                public Object compute(ContextualInput contextualinput)
                {
                    ActiveDescriptor activedescriptor;
                    Object obj;
/*  76*/            if((obj = (activedescriptor = contextualinput.getDescriptor()).getCache()) != null)
/*  80*/                return obj;
/*  83*/            contextualinput = ((ContextualInput) (activedescriptor.create(contextualinput.getRoot())));
/*  84*/            activedescriptor.setCache(contextualinput);
/*  85*/            if(activedescriptor instanceof SystemDescriptor)
/*  86*/                ((SystemDescriptor)activedescriptor).setSingletonGeneration(SingletonContext.access$008(SingletonContext.this));
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
    }

}
