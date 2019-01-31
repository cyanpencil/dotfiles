// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.Genson;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanDescriptor, BeanDescriptorProvider

public static class providers
    implements BeanDescriptorProvider
{

            public BeanDescriptor provide(Class class1, Genson genson)
            {
/*  52*/        return provide(class1, ((Type) (class1)), genson);
            }

            public BeanDescriptor provide(Class class1, Type type, Genson genson)
            {
                Object obj;
/*  57*/        if((obj = (BeanDescriptor)cache.get(type)) == null)
                {
/*  59*/            for(Iterator iterator = providers.iterator(); iterator.hasNext() && (obj = ((BeanDescriptorProvider) (obj = (BeanDescriptorProvider)iterator.next())).provide(class1, type, genson)) == null;);
/*  64*/            cache.putIfAbsent(type, obj);
                }
/*  67*/        return ((BeanDescriptor) (obj));
            }

            private final List providers;
            private final ConcurrentHashMap cache = new ConcurrentHashMap();

            public (List list)
            {
/*  47*/        providers = new ArrayList(list);
            }
}
