// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JodaTimeBundle.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.*;
import com.owlike.genson.annotation.JsonDateFormat;
import com.owlike.genson.convert.ContextualFactory;
import com.owlike.genson.ext.GensonBundle;
import com.owlike.genson.reflect.BeanProperty;
import java.util.Locale;
import org.joda.time.*;
import org.joda.time.format.*;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseLocalConverter, BaseReadableInstantConverter, DurationConverter, IntervalConverter, 
//            PeriodConverter

public class JodaTimeBundle extends GensonBundle
{
    final class JodaTimeConverterContextualFactory
        implements ContextualFactory
    {

                public final Converter create(BeanProperty beanproperty, Genson genson)
                {
/*  22*/            if((genson = (JsonDateFormat)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonDateFormat)) != null)
                    {
/*  24*/                if(org/joda/time/MutableDateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  25*/                    return BaseReadableInstantConverter.makeMutableDateTimeConverter(genson.asTimeInMillis(), formatter(genson, dateTimeFormatter));
/*  26*/                if(org/joda/time/DateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  27*/                    return BaseReadableInstantConverter.makeDateTimeConverter(genson.asTimeInMillis(), formatter(genson, dateTimeFormatter));
/*  30*/                if(org/joda/time/LocalDate.isAssignableFrom(beanproperty.getRawClass()))
/*  31*/                    return BaseLocalConverter.makeLocalDateConverter(formatter(genson, localDateFormatter));
/*  32*/                if(org/joda/time/LocalDateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  33*/                    return BaseLocalConverter.makeLocalDateTimeConverter(formatter(genson, localDateTimeFormatter));
/*  34*/                if(org/joda/time/LocalTime.isAssignableFrom(beanproperty.getRawClass()))
/*  35*/                    return BaseLocalConverter.makeLocalTimeConverter(formatter(genson, localTimeFormatter));
                    }
/*  38*/            return null;
                }

                private DateTimeFormatter formatter(JsonDateFormat jsondateformat, DateTimeFormatter datetimeformatter)
                {
/*  42*/            Locale locale = jsondateformat.lang().isEmpty() ? Locale.getDefault() : new Locale(jsondateformat.lang());
/*  43*/            if(jsondateformat.value() == null || jsondateformat.value().isEmpty())
/*  43*/                return datetimeformatter;
/*  44*/            else
/*  44*/                return DateTimeFormat.forPattern(jsondateformat.value()).withLocale(locale);
                }

                final JodaTimeBundle this$0;

                private JodaTimeConverterContextualFactory()
                {
/*  18*/            this$0 = JodaTimeBundle.this;
/*  18*/            super();
                }

    }


            public JodaTimeBundle()
            {
/*  48*/        dateTimeFormatter = ISODateTimeFormat.dateTime();
/*  49*/        localDateFormatter = ISODateTimeFormat.date().withZone(DateTimeZone.getDefault());
/*  50*/        localDateTimeFormatter = ISODateTimeFormat.dateTime().withZone(DateTimeZone.getDefault());
/*  51*/        localTimeFormatter = ISODateTimeFormat.time().withZone(DateTimeZone.getDefault());
            }

            public void configure(GensonBuilder gensonbuilder)
            {
/*  55*/        gensonbuilder.withContextualFactory(new ContextualFactory[] {
/*  55*/            new JodaTimeConverterContextualFactory()
                }).withConverters(new Converter[] {
/*  55*/            new DurationConverter(), new PeriodConverter(), BaseReadableInstantConverter.makeDateTimeConverter(gensonbuilder.isDateAsTimestamp(), dateTimeFormatter), BaseReadableInstantConverter.makeMutableDateTimeConverter(gensonbuilder.isDateAsTimestamp(), dateTimeFormatter), BaseReadableInstantConverter.makeInstantConverter(gensonbuilder.isDateAsTimestamp(), dateTimeFormatter), BaseLocalConverter.makeLocalDateConverter(localDateFormatter), BaseLocalConverter.makeLocalDateTimeConverter(localDateTimeFormatter), BaseLocalConverter.makeLocalTimeConverter(localTimeFormatter)
                }).withConverterFactory(new IntervalConverter.ConverterFactory());
            }

            public JodaTimeBundle useDateTimeFormatter(DateTimeFormatter datetimeformatter)
            {
/*  70*/        dateTimeFormatter = datetimeformatter;
/*  71*/        return this;
            }

            public JodaTimeBundle useLocalDateFormatter(DateTimeFormatter datetimeformatter)
            {
/*  75*/        localDateFormatter = datetimeformatter;
/*  76*/        return this;
            }

            public JodaTimeBundle useLocalDateTimeFormatter(DateTimeFormatter datetimeformatter)
            {
/*  80*/        localDateTimeFormatter = datetimeformatter;
/*  81*/        return this;
            }

            public JodaTimeBundle useLocalTimeFormatter(DateTimeFormatter datetimeformatter)
            {
/*  85*/        localTimeFormatter = datetimeformatter;
/*  86*/        return this;
            }

            private DateTimeFormatter dateTimeFormatter;
            private DateTimeFormatter localDateFormatter;
            private DateTimeFormatter localDateTimeFormatter;
            private DateTimeFormatter localTimeFormatter;




}
