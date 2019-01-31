// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.*;
import java.util.Collection;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

class val.defaultConverter
    implements Converter
{

            public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 214*/        val$defaultConverter.ze(collection, objectwriter, context);
            }

            public Collection deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
                ValueType valuetype;
/* 219*/        if((valuetype = objectreader.getValueType()) != ValueType.ARRAY && valuetype != ValueType.NULL)
                {
/* 221*/            objectreader = ((ObjectReader) (val$defaultConverter.entConverter().deserialize(objectreader, context)));
/* 222*/            (context = val$defaultConverter.defaultConverter()).add(objectreader);
/* 224*/            return context;
                } else
                {
/* 225*/            return val$defaultConverter.lize(objectreader, context);
                }
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 211*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 211*/        serialize((Collection)obj, objectwriter, context);
            }

            final serialize val$defaultConverter;
            final serialize this$0;

            ()
            {
/* 211*/        this$0 = final_;
/* 211*/        val$defaultConverter = val.defaultConverter.this;
/* 211*/        super();
            }
}
