// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VEventResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, CalendarParsedResult, VCardResultParser, ParsedResult

public final class VEventResultParser extends ResultParser
{

            public VEventResultParser()
            {
            }

            public final CalendarParsedResult parse(Result result)
            {
                String s;
                String s1;
                String s2;
                String s3;
                String s4;
                String s5;
                String as[];
                String s6;
                double d;
                double d1;
                int i;
/*  33*/        if((i = (result = getMassagedText(result)).indexOf("BEGIN:VEVENT")) < 0)
/*  36*/            return null;
/*  39*/        s = matchSingleVCardPrefixedField("SUMMARY", result, true);
/*  40*/        if((s1 = matchSingleVCardPrefixedField("DTSTART", result, true)) == null)
/*  42*/            return null;
/*  44*/        s2 = matchSingleVCardPrefixedField("DTEND", result, true);
/*  45*/        s3 = matchSingleVCardPrefixedField("DURATION", result, true);
/*  46*/        s4 = matchSingleVCardPrefixedField("LOCATION", result, true);
/*  47*/        s5 = stripMailto(matchSingleVCardPrefixedField("ORGANIZER", result, true));
/*  49*/        if((as = matchVCardPrefixedField("ATTENDEE", result, true)) != null)
                {
/*  51*/            for(int j = 0; j < as.length; j++)
/*  52*/                as[j] = stripMailto(as[j]);

                }
/*  55*/        s6 = matchSingleVCardPrefixedField("DESCRIPTION", result, true);
/*  57*/        if((result = matchSingleVCardPrefixedField("GEO", result, true)) == null)
                {
/*  61*/            d = (0.0D / 0.0D);
/*  62*/            d1 = (0.0D / 0.0D);
                } else
                {
                    int k;
/*  64*/            if((k = result.indexOf(';')) < 0)
/*  66*/                return null;
/*  69*/            try
                    {
/*  69*/                d = Double.parseDouble(result.substring(0, k));
/*  70*/                d1 = Double.parseDouble(result.substring(k + 1));
                    }
/*  71*/            catch(NumberFormatException _ex)
                    {
/*  72*/                return null;
                    }
                }
/*  77*/        return new CalendarParsedResult(s, s1, s2, s3, s4, s5, as, s6, d, d1);
/*  87*/        JVM INSTR pop ;
/*  88*/        return null;
            }

            private static String matchSingleVCardPrefixedField(CharSequence charsequence, String s, boolean flag)
            {
/*  95*/        if((charsequence = VCardResultParser.matchSingleVCardPrefixedField(charsequence, s, flag, false)) == null || charsequence.isEmpty())
/*  96*/            return null;
/*  96*/        else
/*  96*/            return (String)charsequence.get(0);
            }

            private static String[] matchVCardPrefixedField(CharSequence charsequence, String s, boolean flag)
            {
/* 100*/        if((charsequence = VCardResultParser.matchVCardPrefixedField(charsequence, s, flag, false)) == null || charsequence.isEmpty())
/* 102*/            return null;
/* 104*/        flag = new String[s = charsequence.size()];
/* 106*/        for(int i = 0; i < s; i++)
/* 107*/            flag[i] = (String)((List)charsequence.get(i)).get(0);

/* 109*/        return flag;
            }

            private static String stripMailto(String s)
            {
/* 113*/        if(s != null && (s.startsWith("mailto:") || s.startsWith("MAILTO:")))
/* 114*/            s = s.substring(7);
/* 116*/        return s;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  29*/        return parse(result);
            }
}
