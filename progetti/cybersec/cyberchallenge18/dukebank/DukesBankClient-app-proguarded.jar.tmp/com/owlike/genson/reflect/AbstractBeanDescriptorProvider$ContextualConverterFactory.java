// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBeanDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.convert.ContextualFactory;
import java.lang.reflect.Type;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            AbstractBeanDescriptorProvider, BeanProperty, TypeUtil

public static final class contextualFactories
{

            final Converter provide(BeanProperty beanproperty, Genson genson)
            {
/*  43*/        Object obj = beanproperty.getType();
/*  44*/        for(Iterator iterator = contextualFactories.iterator(); iterator.hasNext();)
                {
/*  46*/            Object obj1 = (ContextualFactory)iterator.next();
                    Type type;
/*  48*/            type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/convert/ContextualFactory, obj1.getClass()), obj1.getClass());
/*  50*/            type = TypeUtil.typeOf(0, type);
/*  52*/            if((obj instanceof Class) && ((Class)obj).isPrimitive())
/*  53*/                obj = TypeUtil.wrap((Class)obj);
/*  55*/            if(TypeUtil.match(((Type) (obj)), type, false) && (obj1 = ((ContextualFactory) (obj1)).create(beanproperty, genson)) != null)
/*  57*/                return ((Converter) (obj1));
                }

/*  60*/        return null;
            }

            private final List contextualFactories;

            public Y(List list)
            {
/*  38*/        contextualFactories = list == null ? ((List) (new ArrayList())) : ((List) (new ArrayList(list)));
            }
}
