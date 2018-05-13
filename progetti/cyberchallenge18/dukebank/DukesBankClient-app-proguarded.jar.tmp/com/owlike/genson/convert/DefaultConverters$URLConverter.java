// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class 
    implements Converter
{

            public URL deserialize(ObjectReader objectreader, Context context)
            {
/*1075*/        return new URL(objectreader.valueAsString());
/*1076*/        JVM INSTR pop ;
/*1077*/        throw new JsonBindingException((new StringBuilder("Can not deserializer <")).append(objectreader.valueAsString()).append("> to URL.").toString());
            }

            public void serialize(URL url, ObjectWriter objectwriter, Context context)
            {
/*1082*/        objectwriter.writeValue(url.toExternalForm());
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*1065*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*1065*/        serialize((URL)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private ()
            {
            }
}
