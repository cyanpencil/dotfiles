// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class A
    implements Converter
{

            public final Number deserialize(ObjectReader objectreader, Context context)
            {
/* 518*/        context = objectreader.getValueType();
/* 519*/        if(ValueType.INTEGER.equals(context))
/* 520*/            return Integer.valueOf(objectreader.valueAsInt());
/* 521*/        if(ValueType.DOUBLE.equals(context))
/* 522*/            return Double.valueOf(objectreader.valueAsDouble());
/* 524*/        objectreader = objectreader.valueAsString();
/* 525*/        if("".equals(objectreader))
/* 525*/            return null;
/* 525*/        else
/* 525*/            return parse(objectreader, context);
            }

            public final void serialize(Number number, ObjectWriter objectwriter, Context context)
            {
/* 530*/        if(isSpecialNumber(number))
                {
/* 530*/            objectwriter.writeUnsafeValue(number.toString());
/* 530*/            return;
                } else
                {
/* 530*/            objectwriter.writeValue(number);
/* 531*/            return;
                }
            }

            private boolean isSpecialNumber(Number number)
            {
/* 534*/        if((number instanceof Double) || (number instanceof Float))
/* 535*/            return (number = (Double)number).isInfinite() || number.isNaN();
/* 538*/        else
/* 538*/            return false;
            }

            private Number parse(String s, ValueType valuetype)
            {
/* 544*/        if(s.indexOf('.') >= 0)
/* 545*/            return Double.valueOf(Double.parseDouble(s));
                long l;
/* 547*/        if((l = Long.parseLong(s)) <= 0x7fffffffL && l >= 0xffffffff80000000L)
/* 549*/            return Integer.valueOf((int)l);
/* 551*/        return Long.valueOf(Long.parseLong(s));
                NumberFormatException numberformatexception;
/* 552*/        numberformatexception;
/* 553*/        throw new JsonBindingException((new StringBuilder("Could not convert input value ")).append(s).append(" of type ").append(valuetype.toClass()).append(" to a Number type.").toString(), numberformatexception);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 509*/        return deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 509*/        serialize((Number)obj, objectwriter, context);
            }

            public static final serialize instance = new <init>();


            private A()
            {
            }
}
