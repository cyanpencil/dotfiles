// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class delegateConverter
    implements Converter
{

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*1298*/        if(objectwriter.enclosingType() == JsonType.EMPTY)
                {
/*1299*/            objectwriter.beginObject().writeName(outputName);
/*1300*/            delegateConverter.serialize(obj, objectwriter, context);
/*1301*/            objectwriter.endObject();
                }
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*1307*/        Object obj = null;
/*1308*/        if(objectreader.enclosingType() == JsonType.EMPTY)
                {
/*1309*/            objectreader.beginObject();
/*1311*/            if(objectreader.hasNext())
                    {
/*1312*/                objectreader.next();
/*1314*/                if(!inputName.equalsIgnoreCase(objectreader.name()))
/*1315*/                    throw new JsonBindingException(String.format("Expected key %s for unwrapping the value, but encountered key %s", new Object[] {
/*1315*/                        inputName, objectreader.name()
                            }));
/*1324*/                obj = delegateConverter.deserialize(objectreader, context);
                    }
/*1326*/            objectreader.endObject();
                }
/*1328*/        return obj;
            }

            private final String inputName;
            private final String outputName;
            private final Converter delegateConverter;

            public (String s, String s1, Converter converter)
            {
/*1291*/        inputName = s;
/*1292*/        outputName = s1;
/*1293*/        delegateConverter = converter;
            }
}
