// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeTypeConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory, RuntimeTypeConverter

public static class  extends ChainedFactory
{

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  24*/        if(converter == null)
/*  25*/            throw new IllegalArgumentException("RuntimeTypeConverter can not be last Converter in the chain.");
/*  27*/        else
/*  27*/            return new RuntimeTypeConverter(TypeUtil.getRawClass(type), converter);
            }

            public ()
            {
            }
}
