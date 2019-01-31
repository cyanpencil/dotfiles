// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextInjectionResolver.java

package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.hk2.utilities.cache.*;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.process.internal.RequestScoped;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ForeignRequestScopeBridge

public class ContextInjectionResolver
    implements InjectionResolver
{
    static class DescriptorOverridingInjectee extends InjecteeImpl
    {

                private static final long serialVersionUID = 0xcc15ab5193261b03L;

                private DescriptorOverridingInjectee(Injectee injectee, ActiveDescriptor activedescriptor)
                {
/* 165*/            super(injectee);
/* 166*/            setInjecteeDescriptor(activedescriptor);
                }

    }

    static class RequiredTypeOverridingInjectee extends InjecteeImpl
    {

                private static final long serialVersionUID = 0xcc15ab5193261b05L;

                private RequiredTypeOverridingInjectee(Injectee injectee, Type type)
                {
/* 155*/            super(injectee);
/* 156*/            setRequiredType(type);
                }

    }

    public static final class Binder extends AbstractBinder
    {

                protected final void configure()
                {
/*  89*/            bind(org/glassfish/jersey/internal/inject/ContextInjectionResolver).to(new TypeLiteral() {

                        final Binder this$0;

                        
                        {
/*  89*/                    this$0 = Binder.this;
/*  89*/                    super();
                        }
            }).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            public ContextInjectionResolver()
            {
/* 206*/        foreignRequestScopedComponents = Values.lazy(new Value() {

                    public Set get()
                    {
/* 209*/                return getForeignRequestScopedComponents();
                    }

                    public volatile Object get()
                    {
/* 206*/                return get();
                    }

                    final ContextInjectionResolver this$0;

                    
                    {
/* 206*/                this$0 = ContextInjectionResolver.this;
/* 206*/                super();
                    }
        });
            }

            public Object resolve(Injectee injectee, ServiceHandle servicehandle)
            {
                boolean flag;
/* 108*/        if(flag = ReflectionHelper.isSubClassOf(servicehandle = injectee.getRequiredType(), org/glassfish/hk2/api/Factory))
/* 113*/            injectee = getFactoryInjectee(injectee, ReflectionHelper.getTypeArgument(servicehandle, 0));
/* 115*/        else
/* 115*/            injectee = (Injectee)foreignRequestScopedInjecteeCache.compute(injectee);
/* 118*/        if((servicehandle = (ActiveDescriptor)descriptorCache.compute(injectee)) != null)
                {
/* 121*/            injectee = serviceLocator.getServiceHandle(servicehandle, injectee);
/* 123*/            if(flag)
/* 124*/                return asFactory(injectee);
/* 126*/            else
/* 126*/                return injectee.getService();
                } else
                {
/* 129*/            return null;
                }
            }

            private Factory asFactory(final ServiceHandle handle)
            {
/* 133*/        return new Factory() {

                    public Object provide()
                    {
/* 136*/                return handle.getService();
                    }

                    public void dispose(Object obj)
                    {
                    }

                    final ServiceHandle val$handle;
                    final ContextInjectionResolver this$0;

                    
                    {
/* 133*/                this$0 = ContextInjectionResolver.this;
/* 133*/                handle = servicehandle;
/* 133*/                super();
                    }
        };
            }

            private Injectee getFactoryInjectee(Injectee injectee, Type type)
            {
/* 147*/        return new RequiredTypeOverridingInjectee(injectee, type);
            }

            public boolean isConstructorParameterIndicator()
            {
/* 172*/        return true;
            }

            public boolean isMethodParameterIndicator()
            {
/* 177*/        return false;
            }

            private Set getForeignRequestScopedComponents()
            {
/* 214*/        Object obj = serviceLocator.getAllServices(org/glassfish/jersey/internal/inject/ForeignRequestScopeBridge, new Annotation[0]);
/* 215*/        HashSet hashset = new HashSet();
/* 216*/        obj = ((List) (obj)).iterator();
/* 216*/        do
                {
/* 216*/            if(!((Iterator) (obj)).hasNext())
/* 216*/                break;
                    Object obj1;
/* 216*/            if((obj1 = ((ForeignRequestScopeBridge) (obj1 = (ForeignRequestScopeBridge)((Iterator) (obj)).next())).getRequestScopedComponents()) != null)
/* 219*/                hashset.addAll(((java.util.Collection) (obj1)));
                } while(true);
/* 222*/        return hashset;
            }

            private ServiceLocator serviceLocator;
            private final Cache descriptorCache = new Cache(new Computable() {

                public ActiveDescriptor compute(Injectee injectee)
                {
/* 102*/            return serviceLocator.getInjecteeDescriptor(injectee);
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
    });
            private final Cache foreignRequestScopedInjecteeCache = new Cache(new Computable() {

                public Injectee compute(Injectee injectee)
                {
/* 185*/            if(injectee.getParent() != null && java/lang/reflect/Field.isAssignableFrom(injectee.getParent().getClass()))
                    {
/* 187*/                Object obj = (Field)injectee.getParent();
/* 188*/                if(((Set)foreignRequestScopedComponents.get()).contains(((Field) (obj)).getDeclaringClass()))
                        {
/* 189*/                    obj = ((Field) (obj)).getType();
/* 190*/                    if(serviceLocator.getServiceHandle(((Class) (obj)), new Annotation[0]).getActiveDescriptor().getScopeAnnotation() == org/glassfish/jersey/process/internal/RequestScoped)
                            {
/* 192*/                        obj = BuilderHelper.activeLink(((Class) (obj))).to(((Type) (obj))).in(org/glassfish/jersey/process/internal/RequestScoped).build();
/* 197*/                        return new DescriptorOverridingInjectee(injectee, ((ActiveDescriptor) (obj)));
                            }
                        }
                    }
/* 202*/            return injectee;
                }

                public volatile Object compute(Object obj)
                    throws ComputationErrorException
                {
/* 182*/            return compute((Injectee)obj);
                }

                final ContextInjectionResolver this$0;

                    
                    {
/* 182*/                this$0 = ContextInjectionResolver.this;
/* 182*/                super();
                    }
    });
            LazyValue foreignRequestScopedComponents;


}
