// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextInjectionResolver.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ContextInjectionResolver

class this._cls0
    implements Computable
{

            public ActiveDescriptor compute(Injectee injectee)
            {
/* 102*/        return ContextInjectionResolver.access$000(ContextInjectionResolver.this).getInjecteeDescriptor(injectee);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*  98*/        return compute((Injectee)obj);
            }

            final ContextInjectionResolver this$0;

            ()
            {
/*  98*/        this$0 = ContextInjectionResolver.this;
/*  98*/        super();
            }
}
