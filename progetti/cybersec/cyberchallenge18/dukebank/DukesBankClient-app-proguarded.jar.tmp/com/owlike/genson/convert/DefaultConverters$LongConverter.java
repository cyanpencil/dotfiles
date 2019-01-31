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

            public final Long deserialize(ObjectReader objectreader, Context context)
            {
/* 442*/        return Long.valueOf(objectreader.valueAsLong());
            }

            public final void serialize(Long long1, ObjectWriter objectwriter, Context context)
            {
/* 446*/        objectwriter.writeValue(long1.longValue());
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 433*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 433*/        serialize((Long)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
