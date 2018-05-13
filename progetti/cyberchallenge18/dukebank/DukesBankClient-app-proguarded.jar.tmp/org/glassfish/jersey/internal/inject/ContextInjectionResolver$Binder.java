// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextInjectionResolver.java

package org.glassfish.jersey.internal.inject;

import javax.inject.Singleton;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ContextInjectionResolver

public static final class _cls1.this._cls0 extends AbstractBinder
{

            protected final void configure()
            {
/*  89*/        bind(org/glassfish/jersey/internal/inject/ContextInjectionResolver).to(new TypeLiteral() {

                    final ContextInjectionResolver.Binder this$0;

                    
                    {
/*  89*/                this$0 = ContextInjectionResolver.Binder.this;
/*  89*/                super();
                    }
        }).in(javax/inject/Singleton);
            }

            public _cls1.this._cls0()
            {
            }
}
