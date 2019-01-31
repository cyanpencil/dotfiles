// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextInjectionResolver.java

package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.ActiveDescriptorBuilder;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.process.internal.RequestScoped;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ContextInjectionResolver

class this._cls0
    implements Computable
{

            public Injectee compute(Injectee injectee)
            {
/* 185*/        if(injectee.getParent() != null && java/lang/reflect/Field.isAssignableFrom(injectee.getParent().getClass()))
                {
/* 187*/            Object obj = (Field)injectee.getParent();
/* 188*/            if(((Set)foreignRequestScopedComponents.get()).contains(((Field) (obj)).getDeclaringClass()))
                    {
/* 189*/                obj = ((Field) (obj)).getType();
/* 190*/                if(ContextInjectionResolver.access$000(ContextInjectionResolver.this).getServiceHandle(((Class) (obj)), new Annotation[0]).getActiveDescriptor().getScopeAnnotation() == org/glassfish/jersey/process/internal/RequestScoped)
                        {
/* 192*/                    obj = BuilderHelper.activeLink(((Class) (obj))).to(((java.lang.reflect.Type) (obj))).in(org/glassfish/jersey/process/internal/RequestScoped).build();
/* 197*/                    return new scriptorOverridingInjectee(injectee, ((ActiveDescriptor) (obj)));
                        }
                    }
                }
/* 202*/        return injectee;
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/* 182*/        return compute((Injectee)obj);
            }

            final ContextInjectionResolver this$0;

            this._cls0()
            {
/* 182*/        this$0 = ContextInjectionResolver.this;
/* 182*/        super();
            }

            // Unreferenced inner class org/glassfish/jersey/internal/inject/ContextInjectionResolver$1

/* anonymous class */
    class ContextInjectionResolver._cls1
        implements Computable
    {

                public ActiveDescriptor compute(Injectee injectee)
                {
/* 102*/            return ContextInjectionResolver.access$000(ContextInjectionResolver.this).getInjecteeDescriptor(injectee);
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/*  98*/            return compute((Injectee)obj);
                }

                final ContextInjectionResolver this$0;

                    
                    {
/*  98*/                this$0 = ContextInjectionResolver.this;
/*  98*/                super();
                    }
    }

}
