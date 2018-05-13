// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanPropertyFactory.java

package com.owlike.genson.reflect;

import com.owlike.genson.Genson;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanPropertyFactory, PropertyAccessor, BeanCreator, PropertyMutator

public static class factories
    implements BeanPropertyFactory
{

            public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
            {
                Object obj;
/*  38*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  38*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createAccessor(s, field, type, genson)) != null)
/*  40*/                return ((PropertyAccessor) (obj));

/*  42*/        throw new RuntimeException((new StringBuilder("Failed to create a accessor for field ")).append(field).toString());
            }

            public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
            {
                Object obj;
/*  48*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  48*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createAccessor(s, method, type, genson)) != null)
/*  50*/                return ((PropertyAccessor) (obj));

/*  52*/        throw new RuntimeException((new StringBuilder("Failed to create a accessor for method ")).append(method).toString());
            }

            public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
            {
                Object obj;
/*  58*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  58*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createCreator(type, constructor, as, genson)) != null)
/*  60*/                return ((BeanCreator) (obj));

/*  62*/        throw new RuntimeException((new StringBuilder("Failed to create a BeanCreator for constructor ")).append(constructor).toString());
            }

            public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
            {
                Object obj;
/*  68*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  68*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createCreator(type, method, as, genson)) != null)
/*  70*/                return ((BeanCreator) (obj));

/*  72*/        throw new RuntimeException((new StringBuilder("Failed to create a BeanCreator for method ")).append(method).toString());
            }

            public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
            {
                Object obj;
/*  77*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  77*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createMutator(s, field, type, genson)) != null)
/*  79*/                return ((PropertyMutator) (obj));

/*  81*/        throw new RuntimeException((new StringBuilder("Failed to create a mutator for field ")).append(field).toString());
            }

            public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
            {
                Object obj;
/*  86*/        for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  86*/            if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createMutator(s, method, type, genson)) != null)
/*  88*/                return ((PropertyMutator) (obj));

/*  90*/        throw new RuntimeException((new StringBuilder("Failed to create a mutator for method ")).append(method).toString());
            }

            private final List factories;

            public (List list)
            {
/*  33*/        factories = new ArrayList(list);
            }
}
