// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NullConverterFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;

// Referenced classes of package com.owlike.genson.convert:
//            NullConverterFactory

class this._cls0 extends Wrapper
    implements Converter
{

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  38*/        if(obj == null)
                {
/*  39*/            throw new JsonBindingException("Serialization of null primitives is forbidden");
                } else
                {
/*  41*/            ((Converter)wrapped).serialize(obj, objectwriter, context);
/*  43*/            return;
                }
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  47*/        if(ValueType.NULL == objectreader.getValueType())
/*  48*/            throw new JsonBindingException("Can not deserialize null to a primitive type");
/*  50*/        else
/*  50*/            return ((Converter)wrapped).deserialize(objectreader, context);
            }

            final NullConverterFactory this$0;

            public (Converter converter)
            {
/*  32*/        this$0 = NullConverterFactory.this;
/*  33*/        super(converter);
            }
}
