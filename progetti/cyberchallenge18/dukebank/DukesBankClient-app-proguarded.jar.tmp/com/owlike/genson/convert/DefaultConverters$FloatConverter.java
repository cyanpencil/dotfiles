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

public static final class 
    implements Converter
{

            public final Float deserialize(ObjectReader objectreader, Context context)
            {
/* 497*/        return Float.valueOf(objectreader.valueAsFloat());
            }

            public final void serialize(Float float1, ObjectWriter objectwriter, Context context)
            {
/* 501*/        if(float1.isNaN() || float1.isInfinite())
                {
/* 502*/            objectwriter.writeUnsafeValue(float1.toString());
/* 502*/            return;
                } else
                {
/* 504*/            objectwriter.writeValue(float1.floatValue());
/* 506*/            return;
                }
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 488*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 488*/        serialize((Float)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
