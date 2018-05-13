// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PeriodConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public class PeriodConverter
    implements Converter
{

            public PeriodConverter()
            {
            }

            public void serialize(Period period, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  20*/        objectwriter.writeString(formatter.print(period));
            }

            public Period deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  25*/        return formatter.parsePeriod(objectreader.valueAsString());
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  13*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  13*/        serialize((Period)obj, objectwriter, context);
            }

            private final PeriodFormatter formatter = ISOPeriodFormat.standard();
}
