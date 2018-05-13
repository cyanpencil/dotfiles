// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.math.BigInteger;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class 
    implements Converter
{

            public BigInteger deserialize(ObjectReader objectreader, Context context)
            {
/*1132*/        return new BigInteger(objectreader.valueAsString());
            }

            public void serialize(BigInteger biginteger, ObjectWriter objectwriter, Context context)
            {
/*1137*/        objectwriter.writeValue(biginteger);
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*1122*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*1122*/        serialize((BigInteger)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
