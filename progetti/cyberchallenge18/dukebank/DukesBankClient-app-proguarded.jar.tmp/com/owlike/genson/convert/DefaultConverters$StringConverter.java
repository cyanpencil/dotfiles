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

            public final void serialize(String s, ObjectWriter objectwriter, Context context)
            {
/* 391*/        objectwriter.writeValue(s);
            }

            public final String deserialize(ObjectReader objectreader, Context context)
            {
/* 395*/        return objectreader.valueAsString();
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 382*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 382*/        serialize((String)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private A()
            {
            }
}
