// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.*;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class UntypedConverter
    implements Factory
{
    public static final class UntypedConverter
        implements Converter
    {

                public final Object deserialize(ObjectReader objectreader, Context context)
                {
/* 996*/            return context.genson.deserialize(GenericType.of(objectreader.getValueType().toClass()), objectreader, context);
                }

                public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
                {
/*1001*/            if(java/lang/Object.equals(obj.getClass()))
                    {
/*1002*/                throw new UnsupportedOperationException("Serialization of type Object is not supported by default serializers.");
                    } else
                    {
/*1004*/                context.genson.serialize(obj, obj.getClass(), objectwriter, context);
/*1005*/                return;
                    }
                }

                static final UntypedConverter instance = new UntypedConverter();


                private UntypedConverter()
                {
                }
    }


            public final Converter create(Type type, Genson genson)
            {
/*1009*/        if(TypeUtil.match(type, java/lang/Object, true))
/*1010*/            return UntypedConverter.instance;
/*1012*/        else
/*1012*/            return null;
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 983*/        return create(type, genson);
            }

            public static final create instance = new <init>();


            private UntypedConverter()
            {
            }
}
