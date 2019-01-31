// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpDateFormat.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class HttpDateFormat
{

            private HttpDateFormat()
            {
            }

            private static List createDateFormats()
            {
                SimpleDateFormat asimpledateformat[];
/*  85*/        (asimpledateformat = (new SimpleDateFormat[] {
/*  85*/            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US), new SimpleDateFormat("EEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US), new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.US)
                }))[0].setTimeZone(GMT_TIME_ZONE);
/*  91*/        asimpledateformat[1].setTimeZone(GMT_TIME_ZONE);
/*  92*/        asimpledateformat[2].setTimeZone(GMT_TIME_ZONE);
/*  94*/        return Collections.unmodifiableList(Arrays.asList(asimpledateformat));
            }

            private static List getDateFormats()
            {
/* 108*/        return (List)dateFormats.get();
            }

            public static SimpleDateFormat getPreferredDateFormat()
            {
/* 122*/        return (SimpleDateFormat)((SimpleDateFormat)((List)dateFormats.get()).get(0)).clone();
            }

            public static Date readDate(String s)
                throws ParseException
            {
                Object obj;
                Iterator iterator;
/* 134*/        obj = null;
/* 135*/        iterator = getDateFormats().iterator();
_L2:
                SimpleDateFormat simpledateformat;
/* 135*/        if(!iterator.hasNext())
/* 135*/            break; /* Loop/switch isn't completed */
/* 135*/        simpledateformat = (SimpleDateFormat)iterator.next();
                Date date;
/* 137*/        date = simpledateformat.parse(s);
/* 139*/        simpledateformat.setTimeZone(GMT_TIME_ZONE);
/* 140*/        return date;
                ParseException parseexception;
/* 141*/        parseexception;
/* 142*/        obj = obj != null ? obj : ((Object) (parseexception));
/* 144*/        if(true) goto _L2; else goto _L1
_L1:
/* 146*/        throw obj;
            }

            private static final String RFC1123_DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
            private static final String RFC1036_DATE_FORMAT_PATTERN = "EEEE, dd-MMM-yy HH:mm:ss zzz";
            private static final String ANSI_C_ASCTIME_DATE_FORMAT_PATTERN = "EEE MMM d HH:mm:ss yyyy";
            private static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
            private static final ThreadLocal dateFormats = new ThreadLocal() {

                protected final synchronized List initialValue()
                {
/*  80*/            return HttpDateFormat.createDateFormats();
                }

                protected final volatile Object initialValue()
                {
/*  76*/            return initialValue();
                }

    };


}
