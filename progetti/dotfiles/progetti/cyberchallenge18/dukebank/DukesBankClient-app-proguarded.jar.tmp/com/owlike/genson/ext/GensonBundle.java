// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonBundle.java

package com.owlike.genson.ext;

import com.owlike.genson.GensonBuilder;
import com.owlike.genson.reflect.*;

public abstract class GensonBundle
{

            public GensonBundle()
            {
            }

            public abstract void configure(GensonBuilder gensonbuilder);

            public BeanDescriptorProvider createBeanDescriptorProvider(com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualConverterFactory contextualconverterfactory, BeanPropertyFactory beanpropertyfactory, BeanMutatorAccessorResolver beanmutatoraccessorresolver, PropertyNameResolver propertynameresolver, GensonBuilder gensonbuilder)
            {
/*  43*/        return null;
            }
}
