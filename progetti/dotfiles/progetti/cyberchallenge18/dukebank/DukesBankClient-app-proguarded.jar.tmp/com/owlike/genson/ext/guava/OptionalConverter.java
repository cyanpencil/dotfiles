// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OptionalConverter.java

package com.owlike.genson.ext.guava;

import com.google.common.base.Optional;
import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.*;
import java.lang.reflect.Type;

public class OptionalConverter
    implements Converter
{
    static class OptionalConverterFactory
        implements Factory
    {

                public Converter create(Type type, Genson genson)
                {
/*  20*/            type = TypeUtil.typeOf(0, type);
/*  22*/            return new OptionalConverter(genson.provideConverter(type));
                }

                public volatile Object create(Type type, Genson genson)
                {
/*  16*/            return create(type, genson);
                }

                OptionalConverterFactory()
                {
                }
    }


            public OptionalConverter(Converter converter)
            {
/*  29*/        valueConverter = converter;
            }

            public void serialize(Optional optional, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  34*/        if(optional == null || optional.isPresent())
                {
/*  35*/            valueConverter.serialize(optional.get(), objectwriter, context);
/*  35*/            return;
                } else
                {
/*  36*/            objectwriter.writeNull();
/*  37*/            return;
                }
            }

            public Optional deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  41*/        if(ValueType.NULL.equals(objectreader.getValueType()))
/*  42*/            return Optional.absent();
/*  44*/        else
/*  44*/            return Optional.of(valueConverter.deserialize(objectreader, context));
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  14*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  14*/        serialize((Optional)obj, objectwriter, context);
            }

            private final Converter valueConverter;
}
