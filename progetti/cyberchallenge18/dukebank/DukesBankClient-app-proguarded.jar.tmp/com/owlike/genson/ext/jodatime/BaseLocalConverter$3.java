// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseLocalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.LocalTime;
import org.joda.time.base.BaseLocal;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseLocalConverter

static class nit> extends BaseLocalConverter
{

            protected final LocalTime fromString(String s)
            {
/*  69*/        return localFormatter.parseLocalTime(s);
            }

            protected final LocalTime fromLong(long l)
            {
/*  73*/        return new LocalTime(l);
            }

            protected final volatile BaseLocal fromString(String s)
            {
/*  66*/        return fromString(s);
            }

            protected final volatile BaseLocal fromLong(long l)
            {
/*  66*/        return fromLong(l);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  66*/        return super.deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  66*/        super.serialize((LocalTime)obj, objectwriter, context);
            }

            private final DateTimeFormatter localFormatter = ISODateTimeFormat.localTimeParser();

            _cls9(DateTimeFormatter datetimeformatter)
            {
/*  66*/        super(datetimeformatter);
            }
}
