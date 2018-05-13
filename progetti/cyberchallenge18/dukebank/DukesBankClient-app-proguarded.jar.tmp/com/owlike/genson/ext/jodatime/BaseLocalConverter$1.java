// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseLocalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.LocalDate;
import org.joda.time.base.BaseLocal;
import org.joda.time.format.DateTimeFormatter;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseLocalConverter

static class nit> extends BaseLocalConverter
{

            protected final LocalDate fromString(String s)
            {
/*  43*/        return formatter.parseLocalDate(s);
            }

            protected final LocalDate fromLong(long l)
            {
/*  47*/        return new LocalDate(l);
            }

            protected final volatile BaseLocal fromString(String s)
            {
/*  41*/        return fromString(s);
            }

            protected final volatile BaseLocal fromLong(long l)
            {
/*  41*/        return fromLong(l);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  41*/        return super.deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  41*/        super.serialize((LocalDate)obj, objectwriter, context);
            }

            _cls9(DateTimeFormatter datetimeformatter)
            {
/*  41*/        super(datetimeformatter);
            }
}
