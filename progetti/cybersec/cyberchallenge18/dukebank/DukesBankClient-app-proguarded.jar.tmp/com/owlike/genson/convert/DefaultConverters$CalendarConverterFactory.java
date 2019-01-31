// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;
import java.util.Calendar;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class calendarConverter
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/*1189*/        if(!java/util/Calendar.isAssignableFrom(TypeUtil.getRawClass(type)))
/*1190*/            throw new IllegalStateException("CalendarConverterFactory create method can be called only for Calendar type and subtypes.");
/*1192*/        else
/*1192*/            return calendarConverter;
            }

            public final volatile Object create(Type type, Genson genson)
            {
/*1180*/        return create(type, genson);
            }

            private final create calendarConverter;

            public ( )
            {
/*1184*/        calendarConverter = new calendarConverter();
            }
}
