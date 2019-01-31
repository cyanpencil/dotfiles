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

            public final void serialize(Boolean boolean1, ObjectWriter objectwriter, Context context)
            {
/* 408*/        objectwriter.writeValue(boolean1.booleanValue());
            }

            public final Boolean deserialize(ObjectReader objectreader, Context context)
            {
/* 412*/        return Boolean.valueOf(objectreader.valueAsBoolean());
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 399*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 399*/        serialize((Boolean)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
