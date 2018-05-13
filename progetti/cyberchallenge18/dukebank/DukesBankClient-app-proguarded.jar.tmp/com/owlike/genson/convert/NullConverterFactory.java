// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NullConverterFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.HandleNull;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.*;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory

public class NullConverterFactory extends ChainedFactory
{
    class NullConverterWrapper extends Wrapper
        implements Converter
    {

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  66*/            if(obj == null)
                    {
/*  67*/                objectwriter.writeNull();
/*  67*/                return;
                    } else
                    {
/*  69*/                ((Converter)wrapped).serialize(obj, objectwriter, context);
/*  71*/                return;
                    }
                }

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*  74*/            if(ValueType.NULL == objectreader.getValueType())
/*  75*/                return defaultValue;
/*  77*/            else
/*  77*/                return ((Converter)wrapped).deserialize(objectreader, context);
                }

                private final Object defaultValue;
                final NullConverterFactory this$0;

                public NullConverterWrapper(Object obj, Converter converter)
                {
/*  60*/            this$0 = NullConverterFactory.this;
/*  61*/            super(converter);
/*  62*/            defaultValue = obj;
                }
    }

    class FailIfNullConverter extends Wrapper
        implements Converter
    {

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  38*/            if(obj == null)
                    {
/*  39*/                throw new JsonBindingException("Serialization of null primitives is forbidden");
                    } else
                    {
/*  41*/                ((Converter)wrapped).serialize(obj, objectwriter, context);
/*  43*/                return;
                    }
                }

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*  47*/            if(ValueType.NULL == objectreader.getValueType())
/*  48*/                throw new JsonBindingException("Can not deserialize null to a primitive type");
/*  50*/            else
/*  50*/                return ((Converter)wrapped).deserialize(objectreader, context);
                }

                final NullConverterFactory this$0;

                public FailIfNullConverter(Converter converter)
                {
/*  32*/            this$0 = NullConverterFactory.this;
/*  33*/            super(converter);
                }
    }


            public NullConverterFactory(boolean flag)
            {
/*  28*/        failOnNullPrimitive = flag;
            }

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  85*/        if(Wrapper.toAnnotatedElement(converter).isAnnotationPresent(com/owlike/genson/annotation/HandleNull))
/*  86*/            return converter;
/*  88*/        type = TypeUtil.getRawClass(type);
/*  89*/        if(failOnNullPrimitive && type.isPrimitive())
/*  90*/            return new FailIfNullConverter(converter);
/*  92*/        else
/*  92*/            return new NullConverterWrapper(genson.defaultValue(type), converter);
            }

            private final boolean failOnNullPrimitive;
}
