// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeTypeConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory

public class RuntimeTypeConverter extends Wrapper
    implements Converter
{
    public static class RuntimeTypeConverterFactory extends ChainedFactory
    {

                protected Converter create(Type type, Genson genson, Converter converter)
                {
/*  24*/            if(converter == null)
/*  25*/                throw new IllegalArgumentException("RuntimeTypeConverter can not be last Converter in the chain.");
/*  27*/            else
/*  27*/                return new RuntimeTypeConverter(TypeUtil.getRawClass(type), converter);
                }

                public RuntimeTypeConverterFactory()
                {
                }
    }


            public RuntimeTypeConverter(Class class1, Converter converter)
            {
/*  35*/        super(converter);
/*  36*/        tClass = class1;
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  40*/        if(obj != null && !tClass.equals(obj.getClass()))
                {
/*  41*/            context.genson.serialize(obj, obj.getClass(), objectwriter, context);
/*  41*/            return;
                } else
                {
/*  43*/            ((Converter)wrapped).serialize(obj, objectwriter, context);
/*  44*/            return;
                }
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  47*/        return ((Converter)wrapped).deserialize(objectreader, context);
            }

            private final Class tClass;
}
