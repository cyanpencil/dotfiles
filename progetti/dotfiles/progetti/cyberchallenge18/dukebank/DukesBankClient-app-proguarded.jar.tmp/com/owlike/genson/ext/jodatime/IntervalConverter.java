// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntervalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Type;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class IntervalConverter
    implements Converter
{
    public static class ConverterFactory
        implements Factory
    {

                public Converter create(Type type, Genson genson)
                {
/*  23*/            return new IntervalConverter(genson.provideConverter(org/joda/time/DateTime));
                }

                public volatile Object create(Type type, Genson genson)
                {
/*  19*/            return create(type, genson);
                }

                public ConverterFactory()
                {
                }
    }


            public IntervalConverter(Converter converter)
            {
/*  30*/        dateTimeConverter = converter;
            }

            public void serialize(Interval interval, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  35*/        objectwriter.beginObject();
/*  36*/        objectwriter.writeName("start");
/*  37*/        dateTimeConverter.serialize(interval.getStart(), objectwriter, context);
/*  38*/        objectwriter.writeName("end");
/*  39*/        dateTimeConverter.serialize(interval.getEnd(), objectwriter, context);
/*  40*/        objectwriter.endObject();
            }

            public Interval deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  45*/        DateTime datetime = null;
/*  45*/        DateTime datetime1 = null;
/*  47*/        objectreader.beginObject();
/*  48*/        while(objectreader.hasNext()) 
                {
/*  49*/            objectreader.next();
/*  50*/            if("start".equals(objectreader.name()))
/*  50*/                datetime = (DateTime)dateTimeConverter.deserialize(objectreader, context);
/*  51*/            else
/*  51*/            if("end".equals(objectreader.name()))
/*  51*/                datetime1 = (DateTime)dateTimeConverter.deserialize(objectreader, context);
/*  53*/            else
/*  53*/                throw new IllegalStateException((new StringBuilder("Encountered unexpected property ")).append(objectreader.name()).append(" and value ").append(objectreader.valueAsString()).toString());
                }
/*  56*/        objectreader.endObject();
/*  58*/        return new Interval(datetime, datetime1);
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  16*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  16*/        serialize((Interval)obj, objectwriter, context);
            }

            private final Converter dateTimeConverter;
}
