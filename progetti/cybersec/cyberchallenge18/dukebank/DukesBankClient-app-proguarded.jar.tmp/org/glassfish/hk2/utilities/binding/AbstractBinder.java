// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBinder.java

package org.glassfish.hk2.utilities.binding;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.DescriptorImpl;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBindingBuilder, ServiceBindingBuilder, ScopedBindingBuilder

public abstract class AbstractBinder
    implements DynamicConfiguration, Binder
{

            public AbstractBinder()
            {
            }

            public ServiceBindingBuilder bind(Class class1)
            {
/*  81*/        return resetBuilder(AbstractBindingBuilder.create(class1, false));
            }

            public ServiceBindingBuilder bindAsContract(Class class1)
            {
/*  94*/        return resetBuilder(AbstractBindingBuilder.create(class1, true));
            }

            public ServiceBindingBuilder bindAsContract(TypeLiteral typeliteral)
            {
/* 107*/        return resetBuilder(AbstractBindingBuilder.create(typeliteral, true));
            }

            public ScopedBindingBuilder bind(Object obj)
            {
/* 121*/        return resetBuilder(AbstractBindingBuilder.create(obj));
            }

            public ServiceBindingBuilder bindFactory(Class class1, Class class2)
            {
/* 134*/        return resetBuilder(AbstractBindingBuilder.createFactoryBinder(class1, class2));
            }

            public ServiceBindingBuilder bindFactory(Class class1)
            {
/* 147*/        return resetBuilder(AbstractBindingBuilder.createFactoryBinder(class1, null));
            }

            public ServiceBindingBuilder bindFactory(Factory factory)
            {
/* 158*/        return resetBuilder(AbstractBindingBuilder.createFactoryBinder(factory));
            }

            public void bind(DynamicConfiguration dynamicconfiguration)
            {
/* 163*/        if(configuration != null)
/* 164*/            throw new IllegalArgumentException("Recursive configuration call detected.");
/* 167*/        if(dynamicconfiguration == null)
/* 168*/            throw new NullPointerException("configuration");
/* 170*/        configuration = dynamicconfiguration;
/* 172*/        configure();
/* 174*/        complete();
/* 175*/        return;
/* 174*/        dynamicconfiguration;
/* 174*/        complete();
/* 174*/        throw dynamicconfiguration;
            }

            private AbstractBindingBuilder resetBuilder(AbstractBindingBuilder abstractbindingbuilder)
            {
/* 179*/        if(currentBuilder != null)
/* 180*/            currentBuilder.complete(configuration(), getDefaultBinderLoader());
/* 183*/        currentBuilder = abstractbindingbuilder;
/* 185*/        return abstractbindingbuilder;
            }

            private void complete()
            {
/* 190*/        resetBuilder(null);
/* 192*/        configuration = null;
/* 193*/        return;
                Exception exception;
/* 192*/        exception;
/* 192*/        configuration = null;
/* 192*/        throw exception;
            }

            protected abstract void configure();

            private DynamicConfiguration configuration()
            {
/* 212*/        if(configuration == null)
/* 213*/            throw new IllegalArgumentException("Dynamic configuration accessed from outside of an active binder configuration scope.");
/* 215*/        else
/* 215*/            return configuration;
            }

            public ActiveDescriptor bind(Descriptor descriptor)
            {
/* 227*/        return bind(descriptor, true);
            }

            public ActiveDescriptor bind(Descriptor descriptor, boolean flag)
            {
/* 232*/        setLoader(descriptor);
/* 233*/        return configuration().bind(descriptor, flag);
            }

            public FactoryDescriptors bind(FactoryDescriptors factorydescriptors)
            {
/* 245*/        return bind(factorydescriptors, true);
            }

            public FactoryDescriptors bind(FactoryDescriptors factorydescriptors, boolean flag)
            {
/* 250*/        setLoader(factorydescriptors.getFactoryAsAService());
/* 251*/        setLoader(factorydescriptors.getFactoryAsAFactory());
/* 253*/        return configuration().bind(factorydescriptors, flag);
            }

            public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor)
                throws IllegalArgumentException
            {
/* 265*/        return addActiveDescriptor(activedescriptor, true);
            }

            public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activedescriptor, boolean flag)
                throws IllegalArgumentException
            {
/* 270*/        return configuration().addActiveDescriptor(activedescriptor, flag);
            }

            public ActiveDescriptor addActiveDescriptor(Class class1)
                throws MultiException, IllegalArgumentException
            {
/* 282*/        return configuration().addActiveDescriptor(class1);
            }

            public FactoryDescriptors addActiveFactoryDescriptor(Class class1)
                throws MultiException, IllegalArgumentException
            {
/* 294*/        return configuration().addActiveFactoryDescriptor(class1);
            }

            public void addUnbindFilter(Filter filter)
                throws IllegalArgumentException
            {
/* 308*/        configuration().addUnbindFilter(filter);
            }

            public void commit()
                throws MultiException
            {
/* 320*/        configuration().commit();
            }

            public final transient void install(Binder abinder[])
            {
/* 329*/        int i = (abinder = abinder).length;
/* 329*/        for(int j = 0; j < i; j++)
                {
                    Binder binder;
/* 329*/            (binder = abinder[j]).bind(this);
                }

            }

            private void setLoader(Descriptor descriptor)
            {
/* 335*/        if(descriptor.getLoader() == null && (descriptor instanceof DescriptorImpl))
/* 336*/            ((DescriptorImpl)descriptor).setLoader(getDefaultBinderLoader());
            }

            private HK2Loader getDefaultBinderLoader()
            {
/* 341*/        if(defaultLoader == null)
                {
/* 342*/            final ClassLoader binderClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                        public ClassLoader run()
                        {
                            ClassLoader classloader;
/* 346*/                    if((classloader = getClass().getClassLoader()) == null)
/* 348*/                        return ClassLoader.getSystemClassLoader();
/* 350*/                    else
/* 350*/                        return classloader;
                        }

                        public volatile Object run()
                        {
/* 342*/                    return run();
                        }

                        final AbstractBinder this$0;

                    
                    {
/* 342*/                this$0 = AbstractBinder.this;
/* 342*/                super();
                    }
            });
/* 355*/            defaultLoader = new HK2Loader() {

                        public Class loadClass(String s)
                            throws MultiException
                        {
/* 359*/                    return binderClassLoader.loadClass(s);
/* 360*/                    s;
/* 361*/                    throw new MultiException(s);
                        }

                        final ClassLoader val$binderClassLoader;
                        final AbstractBinder this$0;

                    
                    {
/* 355*/                this$0 = AbstractBinder.this;
/* 355*/                binderClassLoader = classloader;
/* 355*/                super();
                    }
            };
                }
/* 366*/        return defaultLoader;
            }

            private transient DynamicConfiguration configuration;
            private transient AbstractBindingBuilder currentBuilder;
            private transient HK2Loader defaultLoader;
}
