// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JodaTimeBundle.java

package com.owlike.genson.ext.jodatime;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.annotation.JsonDateFormat;
import com.owlike.genson.convert.ContextualFactory;
import com.owlike.genson.reflect.BeanProperty;
import java.util.Locale;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

// Referenced classes of package com.owlike.genson.ext.jodatime:
//            BaseLocalConverter, BaseReadableInstantConverter, JodaTimeBundle

final class <init>
    implements ContextualFactory
{

            public final Converter create(BeanProperty beanproperty, Genson genson)
            {
/*  22*/        if((genson = (JsonDateFormat)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonDateFormat)) != null)
                {
/*  24*/            if(org/joda/time/MutableDateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  25*/                return BaseReadableInstantConverter.makeMutableDateTimeConverter(genson.asTimeInMillis(), formatter(genson, JodaTimeBundle.access$000(JodaTimeBundle.this)));
/*  26*/            if(org/joda/time/DateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  27*/                return BaseReadableInstantConverter.makeDateTimeConverter(genson.asTimeInMillis(), formatter(genson, JodaTimeBundle.access$000(JodaTimeBundle.this)));
/*  30*/            if(org/joda/time/LocalDate.isAssignableFrom(beanproperty.getRawClass()))
/*  31*/                return BaseLocalConverter.makeLocalDateConverter(formatter(genson, JodaTimeBundle.access$100(JodaTimeBundle.this)));
/*  32*/            if(org/joda/time/LocalDateTime.isAssignableFrom(beanproperty.getRawClass()))
/*  33*/                return BaseLocalConverter.makeLocalDateTimeConverter(formatter(genson, JodaTimeBundle.access$200(JodaTimeBundle.this)));
/*  34*/            if(org/joda/time/LocalTime.isAssignableFrom(beanproperty.getRawClass()))
/*  35*/                return BaseLocalConverter.makeLocalTimeConverter(formatter(genson, JodaTimeBundle.access$300(JodaTimeBundle.this)));
                }
/*  38*/        return null;
            }

            private DateTimeFormatter formatter(JsonDateFormat jsondateformat, DateTimeFormatter datetimeformatter)
            {
/*  42*/        Locale locale = jsondateformat.lang().isEmpty() ? Locale.getDefault() : new Locale(jsondateformat.lang());
/*  43*/        if(jsondateformat.value() == null || jsondateformat.value().isEmpty())
/*  43*/            return datetimeformatter;
/*  44*/        else
/*  44*/            return DateTimeFormat.forPattern(jsondateformat.value()).withLocale(locale);
            }

            final JodaTimeBundle this$0;

            private ()
            {
/*  18*/        this$0 = JodaTimeBundle.this;
/*  18*/        super();
            }

            this._cls0(this._cls0 _pcls0)
            {
/*  18*/        this();
            }
}
