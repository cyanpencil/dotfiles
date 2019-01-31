// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseLocalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.LocalDateTime;
import org.joda.time.base.BaseLocal;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseLocalConverter

static class nit> extends BaseLocalConverter
{

            protected final LocalDateTime fromString(String s)
            {
/*  56*/        return localFormatter.parseLocalDateTime(s);
            }

            protected final LocalDateTime fromLong(long l)
            {
/*  60*/        return new LocalDateTime(l);
            }

            protected final volatile BaseLocal fromString(String s)
            {
/*  53*/        return fromString(s);
            }

            protected final volatile BaseLocal fromLong(long l)
            {
/*  53*/        return fromLong(l);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  53*/        return super.deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  53*/        super.serialize((LocalDateTime)obj, objectwriter, context);
            }

            private final DateTimeFormatter localFormatter = ISODateTimeFormat.localDateOptionalTimeParser();

            _cls9(DateTimeFormatter datetimeformatter)
            {
/*  53*/        super(datetimeformatter);
            }
}
