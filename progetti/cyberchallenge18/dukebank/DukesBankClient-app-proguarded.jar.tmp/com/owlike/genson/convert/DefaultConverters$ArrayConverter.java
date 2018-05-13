// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Array;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class elementConverter
    implements Converter
{

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 274*/        objectwriter.beginArray();
/* 275*/        int i = Array.getLength(obj);
/* 276*/        for(int j = 0; j < i; j++)
                {
/* 278*/            Object obj1 = Array.get(obj, j);
/* 279*/            elementConverter.serialize(obj1, objectwriter, context);
                }

/* 281*/        objectwriter.endArray();
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 285*/        objectreader.beginArray();
/* 286*/        int i = 10;
/* 287*/        Object obj = Array.newInstance(eClass, 10);
/* 288*/        int j = 0;
/* 289*/        for(; objectreader.hasNext(); Array.set(obj, j++, elementConverter.deserialize(objectreader, context)))
                {
/* 290*/            objectreader.next();
/* 291*/            if(j >= i)
                    {
/* 292*/                i = (i << 1) + 1;
/* 293*/                obj = expandArray(obj, j, i);
                    }
                }

/* 297*/        objectreader.endArray();
/* 298*/        if(j < i)
/* 299*/            obj = expandArray(obj, j, j);
/* 301*/        return obj;
            }

            private Object expandArray(Object obj, int i, int j)
            {
/* 305*/        j = ((int) (Array.newInstance(eClass, j)));
/* 306*/        System.arraycopy(obj, 0, j, 0, i);
/* 307*/        return j;
            }

            private final Class eClass;
            private final Converter elementConverter;

            public (Class class1, Converter converter)
            {
/* 269*/        eClass = class1;
/* 270*/        elementConverter = converter;
            }
}
