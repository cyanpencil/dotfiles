// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class 
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/* 367*/        if((type instanceof GenericArrayType) || (type instanceof Class) && ((Class)type).isArray())
                {
/* 369*/            if(Byte.TYPE.equals(TypeUtil.getCollectionType(type)))
                    {
/* 370*/                return tance;
                    } else
                    {
/* 372*/                genson = genson.provideConverter(TypeUtil.getCollectionType(type));
/* 374*/                return new tance(TypeUtil.getRawClass(TypeUtil.getCollectionType(type)), genson);
                    }
                } else
                {
/* 378*/            return null;
                }
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 359*/        return create(type, genson);
            }

            public static final create instance = new <init>();


            private ()
            {
            }
}
