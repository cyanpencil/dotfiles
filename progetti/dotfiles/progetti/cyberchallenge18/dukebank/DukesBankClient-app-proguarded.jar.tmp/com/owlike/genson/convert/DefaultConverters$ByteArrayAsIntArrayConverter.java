// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class 
    implements Converter
{

            public void serialize(byte abyte0[], ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 338*/        objectwriter.beginArray();
/* 339*/        for(context = 0; context < abyte0.length; context++)
/* 339*/            objectwriter.writeValue(abyte0[context]);

/* 340*/        objectwriter.endArray();
            }

            public byte[] deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 345*/        context = new byte[256];
/* 346*/        objectreader.beginArray();
                int i;
/* 348*/        for(i = 0; objectreader.hasNext(); i++)
                {
/* 349*/            objectreader.next();
/* 350*/            Operations.expandArray(context, i, 2D);
/* 351*/            context[i] = (byte)objectreader.valueAsInt();
                }

/* 353*/        objectreader.endArray();
/* 355*/        return Operations.truncateArray(context, i);
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 329*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 329*/        serialize((byte[])obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
