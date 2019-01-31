// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseReadableInstantConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseReadableInstantConverter

static class nit> extends BaseReadableInstantConverter
{

            protected final Instant fromLong(long l)
            {
/*  64*/        return new Instant(l);
            }

            protected final Instant fromString(String s)
            {
/*  68*/        return new Instant(s);
            }

            protected final volatile ReadableInstant fromString(String s)
            {
/*  62*/        return fromString(s);
            }

            protected final volatile ReadableInstant fromLong(long l)
            {
/*  62*/        return fromLong(l);
            }

            public final volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  62*/        return super.deserialize(objectreader, context);
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  62*/        super.serialize((Instant)obj, objectwriter, context);
            }

            (boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  62*/        super(flag, datetimeformatter);
            }
}
