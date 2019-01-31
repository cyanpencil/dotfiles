// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NullConverterFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;

// Referenced classes of package com.owlike.genson.convert:
//            NullConverterFactory

class defaultValue extends Wrapper
    implements Converter
{

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  66*/        if(obj == null)
                {
/*  67*/            objectwriter.writeNull();
/*  67*/            return;
                } else
                {
/*  69*/            ((Converter)wrapped).serialize(obj, objectwriter, context);
/*  71*/            return;
                }
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  74*/        if(ValueType.NULL == objectreader.getValueType())
/*  75*/            return defaultValue;
/*  77*/        else
/*  77*/            return ((Converter)wrapped).deserialize(objectreader, context);
            }

            private final Object defaultValue;
            final NullConverterFactory this$0;

            public I(Object obj, Converter converter)
            {
/*  60*/        this$0 = NullConverterFactory.this;
/*  61*/        super(converter);
/*  62*/        defaultValue = obj;
            }
}
