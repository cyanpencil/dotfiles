// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DurationConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.Duration;

public class DurationConverter
    implements Converter
{

            public DurationConverter()
            {
            }

            public void serialize(Duration duration, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  16*/        objectwriter.writeValue(duration.getMillis());
            }

            public Duration deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  21*/        return new Duration(objectreader.valueAsLong());
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  11*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  11*/        serialize((Duration)obj, objectwriter, context);
            }
}
