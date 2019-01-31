// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class dateConverter
    implements Converter
{

            public void serialize(Calendar calendar, ObjectWriter objectwriter, Context context)
            {
/*1207*/        dateConverter.alize(calendar.getTime(), objectwriter, context);
            }

            public Calendar deserialize(ObjectReader objectreader, Context context)
            {
/*1212*/        GregorianCalendar gregoriancalendar = null;
/*1213*/        if(ValueType.NULL != objectreader.getValueType())
/*1214*/            (gregoriancalendar = new GregorianCalendar()).setTime(dateConverter.rialize(objectreader, context));
/*1217*/        return gregoriancalendar;
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*1196*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*1196*/        serialize((Calendar)obj, objectwriter, context);
            }

            private final serialize dateConverter;

            ( )
            {
/*1202*/        dateConverter = ;
            }
}
