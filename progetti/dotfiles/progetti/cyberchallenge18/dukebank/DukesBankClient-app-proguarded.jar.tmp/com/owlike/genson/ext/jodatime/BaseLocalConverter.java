// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseLocalConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.*;
import org.joda.time.*;
import org.joda.time.base.BaseLocal;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class BaseLocalConverter
    implements Converter
{

            protected BaseLocalConverter(DateTimeFormatter datetimeformatter)
            {
/*  23*/        formatter = datetimeformatter;
            }

            public void serialize(BaseLocal baselocal, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  28*/        objectwriter.writeString(formatter.print(baselocal));
            }

            public BaseLocal deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  33*/        if(ValueType.INTEGER == objectreader.getValueType())
/*  33*/            return fromLong(objectreader.valueAsLong());
/*  34*/        else
/*  34*/            return fromString(objectreader.valueAsString());
            }

            protected abstract BaseLocal fromLong(long l);

            protected abstract BaseLocal fromString(String s);

            static BaseLocalConverter makeLocalDateConverter(DateTimeFormatter datetimeformatter)
            {
/*  41*/        return new BaseLocalConverter(datetimeformatter) {

                    protected final LocalDate fromString(String s)
                    {
/*  43*/                return formatter.parseLocalDate(s);
                    }

                    protected final LocalDate fromLong(long l)
                    {
/*  47*/                return new LocalDate(l);
                    }

                    protected final volatile BaseLocal fromString(String s)
                    {
/*  41*/                return fromString(s);
                    }

                    protected final volatile BaseLocal fromLong(long l)
                    {
/*  41*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  41*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  41*/                serialize((LocalDate)obj, objectwriter, context);
                    }

        };
            }

            static BaseLocalConverter makeLocalDateTimeConverter(DateTimeFormatter datetimeformatter)
            {
/*  53*/        return new BaseLocalConverter(datetimeformatter) {

                    protected final LocalDateTime fromString(String s)
                    {
/*  56*/                return localFormatter.parseLocalDateTime(s);
                    }

                    protected final LocalDateTime fromLong(long l)
                    {
/*  60*/                return new LocalDateTime(l);
                    }

                    protected final volatile BaseLocal fromString(String s)
                    {
/*  53*/                return fromString(s);
                    }

                    protected final volatile BaseLocal fromLong(long l)
                    {
/*  53*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  53*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  53*/                serialize((LocalDateTime)obj, objectwriter, context);
                    }

                    private final DateTimeFormatter localFormatter = ISODateTimeFormat.localDateOptionalTimeParser();

        };
            }

            static BaseLocalConverter makeLocalTimeConverter(DateTimeFormatter datetimeformatter)
            {
/*  66*/        return new BaseLocalConverter(datetimeformatter) {

                    protected final LocalTime fromString(String s)
                    {
/*  69*/                return localFormatter.parseLocalTime(s);
                    }

                    protected final LocalTime fromLong(long l)
                    {
/*  73*/                return new LocalTime(l);
                    }

                    protected final volatile BaseLocal fromString(String s)
                    {
/*  66*/                return fromString(s);
                    }

                    protected final volatile BaseLocal fromLong(long l)
                    {
/*  66*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  66*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  66*/                serialize((LocalTime)obj, objectwriter, context);
                    }

                    private final DateTimeFormatter localFormatter = ISODateTimeFormat.localTimeParser();

        };
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  17*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  17*/        serialize((BaseLocal)obj, objectwriter, context);
            }

            protected final DateTimeFormatter formatter;
}
