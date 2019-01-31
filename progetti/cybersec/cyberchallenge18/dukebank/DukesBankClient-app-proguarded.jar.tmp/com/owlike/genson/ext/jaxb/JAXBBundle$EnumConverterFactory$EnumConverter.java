// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.util.Map;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class enumToValue
    implements Converter
{

            public void serialize(Enum enum, ObjectWriter objectwriter, Context context)
            {
/* 270*/        objectwriter.writeUnsafeValue((String)enumToValue.get(enum));
            }

            public Enum deserialize(ObjectReader objectreader, Context context)
            {
/* 275*/        return (Enum)valueToEnum.get(objectreader.valueAsString());
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 256*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 256*/        serialize((Enum)obj, objectwriter, context);
            }

            private final Map valueToEnum;
            private final Map enumToValue;
            final serialize this$1;

            public (Map map, Map map1)
            {
/* 262*/        this$1 = this._cls1.this;
/* 263*/        super();
/* 264*/        valueToEnum = map;
/* 265*/        enumToValue = map1;
            }
}
