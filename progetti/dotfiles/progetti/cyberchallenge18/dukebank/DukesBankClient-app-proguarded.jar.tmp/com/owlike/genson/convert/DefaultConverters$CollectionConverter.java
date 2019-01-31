// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.util.*;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class elementConverter
    implements Converter
{

            public Collection deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 172*/        objectreader.beginArray();
/* 173*/        Collection collection = create();
                Object obj;
/* 174*/        for(; objectreader.hasNext(); collection.add(obj))
                {
/* 175*/            objectreader.next();
/* 176*/            obj = elementConverter.deserialize(objectreader, context);
                }

/* 179*/        objectreader.endArray();
/* 180*/        return collection;
            }

            public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 184*/        objectwriter.beginArray();
                Object obj;
/* 185*/        for(collection = collection.iterator(); collection.hasNext(); elementConverter.serialize(obj, objectwriter, context))
/* 185*/            obj = collection.next();

/* 188*/        objectwriter.endArray();
            }

            public Converter getElementConverter()
            {
/* 192*/        return elementConverter;
            }

            protected Collection create()
            {
/* 196*/        return new ArrayList();
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 159*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 159*/        serialize((Collection)obj, objectwriter, context);
            }

            private final Class eClass;
            private final Converter elementConverter;

            public (Class class1, Converter converter)
            {
/* 167*/        eClass = class1;
/* 168*/        elementConverter = converter;
            }
}
