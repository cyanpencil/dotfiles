// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class deserializationNames
    implements Converter
{

            public void serialize(Enum enum, ObjectWriter objectwriter, Context context)
            {
/*1038*/        objectwriter.writeUnsafeValue(enum.name());
            }

            public Enum deserialize(ObjectReader objectreader, Context context)
            {
/*1042*/        objectreader = caseSensitive ? ((ObjectReader) (objectreader.valueAsString())) : ((ObjectReader) (objectreader.valueAsString().toUpperCase()));
/*1043*/        if((context = (Enum)deserializationNames.get(objectreader)) == null)
/*1044*/            throw new JsonBindingException((new StringBuilder("No enum constant ")).append(eClass.getCanonicalName()).append(".").append(objectreader).toString());
/*1045*/        else
/*1045*/            return context;
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*1016*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*1016*/        serialize((Enum)obj, objectwriter, context);
            }

            private final Class eClass;
            private final Map deserializationNames = new HashMap();
            private final boolean caseSensitive;

            public (Class class1, boolean flag)
            {
/*1024*/        eClass = class1;
/*1025*/        caseSensitive = flag;
/*1027*/        int i = (class1 = class1.getFields()).length;
/*1027*/        for(int j = 0; j < i; j++)
                {
/*1027*/            Field field = class1[j];
/*1029*/            try
                    {
/*1029*/                String s = flag ? field.getName() : field.getName().toUpperCase();
/*1030*/                deserializationNames.put(s, (Enum)field.get(null));
                    }
/*1031*/            catch(IllegalAccessException illegalaccessexception)
                    {
/*1032*/                throw new JsonBindingException((new StringBuilder("Failed to get enum value ")).append(field.getName()).toString(), illegalaccessexception);
                    }
                }

            }
}
