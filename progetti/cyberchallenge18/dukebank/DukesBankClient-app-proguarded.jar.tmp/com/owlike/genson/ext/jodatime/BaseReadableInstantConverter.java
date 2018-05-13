// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BaseReadableInstantConverter.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.*;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormatter;

public abstract class BaseReadableInstantConverter
    implements Converter
{

            protected BaseReadableInstantConverter(boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  19*/        dateAsMillis = flag;
/*  20*/        formatter = datetimeformatter;
            }

            protected abstract ReadableInstant fromLong(long l);

            protected abstract ReadableInstant fromString(String s);

            public void serialize(ReadableInstant readableinstant, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  28*/        if(dateAsMillis)
                {
/*  28*/            objectwriter.writeValue(readableinstant.getMillis());
/*  28*/            return;
                } else
                {
/*  29*/            objectwriter.writeString(formatter.print(readableinstant));
/*  30*/            return;
                }
            }

            public ReadableInstant deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  34*/        if(ValueType.INTEGER == objectreader.getValueType())
/*  34*/            return fromLong(objectreader.valueAsLong());
/*  35*/        else
/*  35*/            return fromString(objectreader.valueAsString());
            }

            static BaseReadableInstantConverter makeDateTimeConverter(boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  39*/        return new BaseReadableInstantConverter(flag, datetimeformatter) {

                    protected final DateTime fromLong(long l)
                    {
/*  41*/                return new DateTime(l);
                    }

                    protected final DateTime fromString(String s)
                    {
/*  44*/                return formatter.parseDateTime(s);
                    }

                    protected final volatile ReadableInstant fromString(String s)
                    {
/*  39*/                return fromString(s);
                    }

                    protected final volatile ReadableInstant fromLong(long l)
                    {
/*  39*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  39*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  39*/                serialize((DateTime)obj, objectwriter, context);
                    }

        };
            }

            static BaseReadableInstantConverter makeMutableDateTimeConverter(boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  51*/        return new BaseReadableInstantConverter(flag, datetimeformatter) {

                    protected final MutableDateTime fromLong(long l)
                    {
/*  53*/                return new MutableDateTime(l);
                    }

                    protected final MutableDateTime fromString(String s)
                    {
/*  56*/                return formatter.parseMutableDateTime(s);
                    }

                    protected final volatile ReadableInstant fromString(String s)
                    {
/*  51*/                return fromString(s);
                    }

                    protected final volatile ReadableInstant fromLong(long l)
                    {
/*  51*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  51*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  51*/                serialize((MutableDateTime)obj, objectwriter, context);
                    }

        };
            }

            static BaseReadableInstantConverter makeInstantConverter(boolean flag, DateTimeFormatter datetimeformatter)
            {
/*  62*/        return new BaseReadableInstantConverter(flag, datetimeformatter) {

                    protected final Instant fromLong(long l)
                    {
/*  64*/                return new Instant(l);
                    }

                    protected final Instant fromString(String s)
                    {
/*  68*/                return new Instant(s);
                    }

                    protected final volatile ReadableInstant fromString(String s)
                    {
/*  62*/                return fromString(s);
                    }

                    protected final volatile ReadableInstant fromLong(long l)
                    {
/*  62*/                return fromLong(l);
                    }

                    public final volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/*  62*/                return deserialize(objectreader, context);
                    }

                    public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/*  62*/                serialize((Instant)obj, objectwriter, context);
                    }

        };
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/*  12*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  12*/        serialize((ReadableInstant)obj, objectwriter, context);
            }

            protected final boolean dateAsMillis;
            protected final DateTimeFormatter formatter;
}
