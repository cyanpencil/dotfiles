// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBeanDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            AbstractBeanDescriptorProvider

public static final class delegatedFactory
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
                Converter converter;
/*  73*/        if((converter = (Converter)ThreadLocalHolder.get("__GENSON$CREATION_CONTEXT", com/owlike/genson/Converter)) != null)
/*  74*/            return converter;
/*  75*/        else
/*  75*/            return (Converter)delegatedFactory.create(type, genson);
            }

            public final volatile Object create(Type type, Genson genson)
            {
/*  64*/        return create(type, genson);
            }

            private final Factory delegatedFactory;

            public Y(Factory factory)
            {
/*  68*/        delegatedFactory = factory;
            }
}
