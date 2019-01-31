// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BindingBuilderFactory.java

package org.glassfish.hk2.utilities.binding;

import org.glassfish.hk2.api.*;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBindingBuilder, BindingBuilder, ServiceBindingBuilder, ScopedBindingBuilder

public class BindingBuilderFactory
{

            public BindingBuilderFactory()
            {
            }

            public static void addBinding(BindingBuilder bindingbuilder, DynamicConfiguration dynamicconfiguration)
            {
/*  66*/        if(bindingbuilder instanceof AbstractBindingBuilder)
                {
/*  67*/            ((AbstractBindingBuilder)bindingbuilder).complete(dynamicconfiguration, null);
/*  67*/            return;
                } else
                {
/*  69*/            throw new IllegalArgumentException((new StringBuilder("Unknown binding builder type: ")).append(bindingbuilder.getClass().getName()).toString());
                }
            }

            public static void addBinding(BindingBuilder bindingbuilder, DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/*  82*/        if(bindingbuilder instanceof AbstractBindingBuilder)
                {
/*  83*/            ((AbstractBindingBuilder)bindingbuilder).complete(dynamicconfiguration, hk2loader);
/*  83*/            return;
                } else
                {
/*  85*/            throw new IllegalArgumentException((new StringBuilder("Unknown binding builder type: ")).append(bindingbuilder.getClass().getName()).toString());
                }
            }

            public static ServiceBindingBuilder newFactoryBinder(Class class1, Class class2)
            {
/*  99*/        return AbstractBindingBuilder.createFactoryBinder(class1, class2);
            }

            public static ServiceBindingBuilder newFactoryBinder(Class class1)
            {
/* 112*/        return AbstractBindingBuilder.createFactoryBinder(class1, null);
            }

            public static ServiceBindingBuilder newFactoryBinder(Factory factory)
            {
/* 123*/        return AbstractBindingBuilder.createFactoryBinder(factory);
            }

            public static ServiceBindingBuilder newBinder(Class class1)
            {
/* 136*/        return AbstractBindingBuilder.create(class1, false);
            }

            public static ScopedBindingBuilder newBinder(Object obj)
            {
/* 150*/        return AbstractBindingBuilder.create(obj);
            }
}
