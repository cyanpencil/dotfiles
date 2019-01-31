// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class A
    implements Converter
{

            public final Double deserialize(ObjectReader objectreader, Context context)
            {
/* 476*/        return Double.valueOf(objectreader.valueAsDouble());
            }

            public final void serialize(Double double1, ObjectWriter objectwriter, Context context)
            {
/* 480*/        if(double1.isNaN() || double1.isInfinite())
                {
/* 481*/            objectwriter.writeUnsafeValue(double1.toString());
/* 481*/            return;
                } else
                {
/* 483*/            objectwriter.writeValue(double1.doubleValue());
/* 485*/            return;
                }
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 467*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 467*/        serialize((Double)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private A()
            {
            }
}
