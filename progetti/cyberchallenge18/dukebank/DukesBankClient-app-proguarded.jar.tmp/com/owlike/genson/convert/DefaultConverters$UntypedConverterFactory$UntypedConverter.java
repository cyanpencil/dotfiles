// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class 
    implements Converter
{

            public final Object deserialize(ObjectReader objectreader, Context context)
            {
/* 996*/        return context.genson.deserialize(GenericType.of(objectreader.getValueType().toClass()), objectreader, context);
            }

            public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
            {
/*1001*/        if(java/lang/Object.equals(obj.getClass()))
                {
/*1002*/            throw new UnsupportedOperationException("Serialization of type Object is not supported by default serializers.");
                } else
                {
/*1004*/            context.genson.serialize(obj, obj.getClass(), objectwriter, context);
/*1005*/            return;
                }
            }

            static final  instance = new <init>();


            private ()
            {
            }
}
