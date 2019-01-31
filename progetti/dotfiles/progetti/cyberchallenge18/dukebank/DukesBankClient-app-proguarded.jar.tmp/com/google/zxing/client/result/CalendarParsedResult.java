// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CalendarParsedResult.java

package com.google.zxing.client.result;

import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class CalendarParsedResult extends ParsedResult
{

            public CalendarParsedResult(String s, String s1, String s2, String s3, String s4, String s5, String as[], 
                    String s6, double d, double d1)
            {
/*  69*/        super(ParsedResultType.CALENDAR);
/*  70*/        summary = s;
/*  73*/        try
                {
/*  73*/            start = parseDate(s1);
                }
/*  74*/        catch(ParseException parseexception)
                {
/*  75*/            throw new IllegalArgumentException(parseexception.toString());
                }
/*  78*/        if(s2 == null)
                {
/*  79*/            long l = parseDurationMS(s3);
/*  80*/            end = l >= 0L ? new Date(start.getTime() + l) : null;
                } else
                {
/*  83*/            try
                    {
/*  83*/                end = parseDate(s2);
                    }
/*  84*/            catch(ParseException parseexception1)
                    {
/*  85*/                throw new IllegalArgumentException(parseexception1.toString());
                    }
                }
/*  89*/        startAllDay = s1.length() == 8;
/*  90*/        endAllDay = s2 != null && s2.length() == 8;
/*  92*/        location = s4;
/*  93*/        organizer = s5;
/*  94*/        attendees = as;
/*  95*/        description = s6;
/*  96*/        latitude = d;
/*  97*/        longitude = d1;
            }

            public final String getSummary()
            {
/* 101*/        return summary;
            }

            public final Date getStart()
            {
/* 108*/        return start;
            }

            public final boolean isStartAllDay()
            {
/* 115*/        return startAllDay;
            }

            public final Date getEnd()
            {
/* 123*/        return end;
            }

            public final boolean isEndAllDay()
            {
/* 130*/        return endAllDay;
            }

            public final String getLocation()
            {
/* 134*/        return location;
            }

            public final String getOrganizer()
            {
/* 138*/        return organizer;
            }

            public final String[] getAttendees()
            {
/* 142*/        return attendees;
            }

            public final String getDescription()
            {
/* 146*/        return description;
            }

            public final double getLatitude()
            {
/* 150*/        return latitude;
            }

            public final double getLongitude()
            {
/* 154*/        return longitude;
            }

            public final String getDisplayResult()
            {
/* 159*/        StringBuilder stringbuilder = new StringBuilder(100);
/* 160*/        maybeAppend(summary, stringbuilder);
/* 161*/        maybeAppend(format(startAllDay, start), stringbuilder);
/* 162*/        maybeAppend(format(endAllDay, end), stringbuilder);
/* 163*/        maybeAppend(location, stringbuilder);
/* 164*/        maybeAppend(organizer, stringbuilder);
/* 165*/        maybeAppend(attendees, stringbuilder);
/* 166*/        maybeAppend(description, stringbuilder);
/* 167*/        return stringbuilder.toString();
            }

            private static Date parseDate(String s)
                throws ParseException
            {
/* 178*/        if(!DATE_TIME.matcher(s).matches())
/* 179*/            throw new ParseException(s, 0);
/* 181*/        if(s.length() == 8)
/* 183*/            return buildDateFormat().parse(s);
/* 187*/        if(s.length() == 16 && s.charAt(15) == 'Z')
                {
/* 188*/            s = buildDateTimeFormat().parse(s.substring(0, 15));
/* 189*/            GregorianCalendar gregoriancalendar = new GregorianCalendar();
                    long l;
/* 190*/            l = (l = s.getTime()) + (long)gregoriancalendar.get(15);
/* 195*/            gregoriancalendar.setTime(new Date(l));
/* 196*/            l += gregoriancalendar.get(16);
/* 197*/            s = new Date(l);
                } else
                {
/* 199*/            s = buildDateTimeFormat().parse(s);
                }
/* 201*/        return s;
            }

            private static String format(boolean flag, Date date)
            {
/* 206*/        if(date == null)
/* 207*/            return null;
/* 209*/        else
/* 209*/            return (flag = flag ? ((boolean) (DateFormat.getDateInstance(2))) : ((boolean) (DateFormat.getDateTimeInstance(2, 2)))).format(date);
            }

            private static long parseDurationMS(CharSequence charsequence)
            {
/* 216*/        if(charsequence == null)
/* 217*/            return -1L;
/* 219*/        if(!(charsequence = RFC2445_DURATION.matcher(charsequence)).matches())
/* 221*/            return -1L;
/* 223*/        long l = 0L;
/* 224*/        for(int i = 0; i < RFC2445_DURATION_FIELD_UNITS.length; i++)
                {
                    String s;
/* 225*/            if((s = charsequence.group(i + 1)) != null)
/* 227*/                l += RFC2445_DURATION_FIELD_UNITS[i] * (long)Integer.parseInt(s);
                }

/* 230*/        return l;
            }

            private static DateFormat buildDateFormat()
            {
                SimpleDateFormat simpledateformat;
/* 234*/        (simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)).setTimeZone(TimeZone.getTimeZone("GMT"));
/* 239*/        return simpledateformat;
            }

            private static DateFormat buildDateTimeFormat()
            {
/* 243*/        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
            }

            private static final Pattern RFC2445_DURATION = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
            private static final long RFC2445_DURATION_FIELD_UNITS[] = {
/*  37*/        0x240c8400L, 0x5265c00L, 0x36ee80L, 60000L, 1000L
            };
            private static final Pattern DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
            private final String summary;
            private final Date start;
            private final boolean startAllDay;
            private final Date end;
            private final boolean endAllDay;
            private final String location;
            private final String organizer;
            private final String attendees[];
            private final String description;
            private final double latitude;
            private final double longitude;

}
