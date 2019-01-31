// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DateProvider.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpDateFormat, HttpHeaderReader, Utils

public class DateProvider
    implements HeaderDelegateProvider
{

            public DateProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  63*/        return java/util/Date.isAssignableFrom(class1);
            }

            public String toString(Date date)
            {
/*  68*/        Utils.throwIllegalArgumentExceptionIfNull(date, LocalizationMessages.DATE_IS_NULL());
/*  69*/        return HttpDateFormat.getPreferredDateFormat().format(date);
            }

            public Date fromString(String s)
            {
/*  75*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.DATE_IS_NULL());
/*  78*/        return HttpHeaderReader.readDate(s);
                ParseException parseexception;
/*  79*/        parseexception;
/*  80*/        throw new IllegalArgumentException((new StringBuilder("Error parsing date '")).append(s).append("'").toString(), parseexception);
            }

            public volatile String toString(Object obj)
            {
/*  58*/        return toString((Date)obj);
            }

            public volatile Object fromString(String s)
            {
/*  58*/        return fromString(s);
            }
}
