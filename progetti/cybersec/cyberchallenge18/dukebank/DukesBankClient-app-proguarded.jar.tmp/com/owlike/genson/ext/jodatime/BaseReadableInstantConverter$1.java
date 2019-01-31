// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseReadableInstantConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseReadableInstantConverter

static class nit> extends BaseReadableInstantConverter
{

            protected final DateTime fromLong(long l)
            {
/*  41*/        return new DateTime(l);
            }

            protected final DateTime fromString(String s)
            {
/*  44*/        return formatter.parseDateTime(s);
            }

            protected final volatile ReadableInstant fromString(String s)
            {
/*  39*/        return fromString(s);
            }

            protected final volatile ReadableInstant fromLong(long l)
            {
/*  39*/        return fromLong(l);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  39*/        return super.deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  39*/        super.serialize((DateTime)obj, objectwriter, context);
            }

            (boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  39*/        super(flag, datetimeformatter);
            }
}
