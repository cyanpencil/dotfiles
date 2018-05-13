// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExpandedProductResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ExpandedProductParsedResult, ParsedResult

public final class ExpandedProductResultParser extends ResultParser
{

            public ExpandedProductResultParser()
            {
            }

            public final ExpandedProductParsedResult parse(Result result)
            {
                Object obj;
/*  45*/        if((obj = result.getBarcodeFormat()) != BarcodeFormat.RSS_EXPANDED)
/*  48*/            return null;
/*  50*/        result = getMassagedText(result);
/*  52*/        obj = null;
/*  53*/        String s = null;
/*  54*/        String s1 = null;
/*  55*/        String s2 = null;
/*  56*/        String s3 = null;
/*  57*/        String s4 = null;
/*  58*/        String s5 = null;
/*  59*/        String s6 = null;
/*  60*/        String s7 = null;
/*  61*/        String s8 = null;
/*  62*/        String s9 = null;
/*  63*/        String s10 = null;
/*  64*/        String s11 = null;
/*  65*/        HashMap hashmap = new HashMap();
/*  67*/        int i = 0;
/*  69*/        do
                {
/*  69*/            if(i >= result.length())
/*  70*/                break;
                    String s12;
/*  70*/            if((s12 = findAIvalue(i, result)) == null)
/*  74*/                return null;
/*  76*/            String s13 = findValue(i += s12.length() + 2, result);
/*  78*/            i += s13.length();
/*  80*/            String s14 = s12;
/*  80*/            byte byte0 = -1;
/*  80*/            switch(s14.hashCode())
                    {
/*  80*/            case 1536: 
/*  80*/                if(s14.equals("00"))
/*  80*/                    byte0 = 0;
                        break;

/*  80*/            case 1537: 
/*  80*/                if(s14.equals("01"))
/*  80*/                    byte0 = 1;
                        break;

/*  80*/            case 1567: 
/*  80*/                if(s14.equals("10"))
/*  80*/                    byte0 = 2;
                        break;

/*  80*/            case 1568: 
/*  80*/                if(s14.equals("11"))
/*  80*/                    byte0 = 3;
                        break;

/*  80*/            case 1570: 
/*  80*/                if(s14.equals("13"))
/*  80*/                    byte0 = 4;
                        break;

/*  80*/            case 1572: 
/*  80*/                if(s14.equals("15"))
/*  80*/                    byte0 = 5;
                        break;

/*  80*/            case 1574: 
/*  80*/                if(s14.equals("17"))
/*  80*/                    byte0 = 6;
                        break;

/*  80*/            case 1567966: 
/*  80*/                if(s14.equals("3100"))
/*  80*/                    byte0 = 7;
                        break;

/*  80*/            case 1567967: 
/*  80*/                if(s14.equals("3101"))
/*  80*/                    byte0 = 8;
                        break;

/*  80*/            case 1567968: 
/*  80*/                if(s14.equals("3102"))
/*  80*/                    byte0 = 9;
                        break;

/*  80*/            case 1567969: 
/*  80*/                if(s14.equals("3103"))
/*  80*/                    byte0 = 10;
                        break;

/*  80*/            case 1567970: 
/*  80*/                if(s14.equals("3104"))
/*  80*/                    byte0 = 11;
                        break;

/*  80*/            case 1567971: 
/*  80*/                if(s14.equals("3105"))
/*  80*/                    byte0 = 12;
                        break;

/*  80*/            case 1567972: 
/*  80*/                if(s14.equals("3106"))
/*  80*/                    byte0 = 13;
                        break;

/*  80*/            case 1567973: 
/*  80*/                if(s14.equals("3107"))
/*  80*/                    byte0 = 14;
                        break;

/*  80*/            case 1567974: 
/*  80*/                if(s14.equals("3108"))
/*  80*/                    byte0 = 15;
                        break;

/*  80*/            case 1567975: 
/*  80*/                if(s14.equals("3109"))
/*  80*/                    byte0 = 16;
                        break;

/*  80*/            case 1568927: 
/*  80*/                if(s14.equals("3200"))
/*  80*/                    byte0 = 17;
                        break;

/*  80*/            case 1568928: 
/*  80*/                if(s14.equals("3201"))
/*  80*/                    byte0 = 18;
                        break;

/*  80*/            case 1568929: 
/*  80*/                if(s14.equals("3202"))
/*  80*/                    byte0 = 19;
                        break;

/*  80*/            case 1568930: 
/*  80*/                if(s14.equals("3203"))
/*  80*/                    byte0 = 20;
                        break;

/*  80*/            case 1568931: 
/*  80*/                if(s14.equals("3204"))
/*  80*/                    byte0 = 21;
                        break;

/*  80*/            case 1568932: 
/*  80*/                if(s14.equals("3205"))
/*  80*/                    byte0 = 22;
                        break;

/*  80*/            case 1568933: 
/*  80*/                if(s14.equals("3206"))
/*  80*/                    byte0 = 23;
                        break;

/*  80*/            case 1568934: 
/*  80*/                if(s14.equals("3207"))
/*  80*/                    byte0 = 24;
                        break;

/*  80*/            case 1568935: 
/*  80*/                if(s14.equals("3208"))
/*  80*/                    byte0 = 25;
                        break;

/*  80*/            case 1568936: 
/*  80*/                if(s14.equals("3209"))
/*  80*/                    byte0 = 26;
                        break;

/*  80*/            case 1575716: 
/*  80*/                if(s14.equals("3920"))
/*  80*/                    byte0 = 27;
                        break;

/*  80*/            case 1575717: 
/*  80*/                if(s14.equals("3921"))
/*  80*/                    byte0 = 28;
                        break;

/*  80*/            case 1575718: 
/*  80*/                if(s14.equals("3922"))
/*  80*/                    byte0 = 29;
                        break;

/*  80*/            case 1575719: 
/*  80*/                if(s14.equals("3923"))
/*  80*/                    byte0 = 30;
                        break;

/*  80*/            case 1575747: 
/*  80*/                if(s14.equals("3930"))
/*  80*/                    byte0 = 31;
                        break;

/*  80*/            case 1575748: 
/*  80*/                if(s14.equals("3931"))
/*  80*/                    byte0 = 32;
                        break;

/*  80*/            case 1575749: 
/*  80*/                if(s14.equals("3932"))
/*  80*/                    byte0 = 33;
                        break;

/*  80*/            case 1575750: 
/*  80*/                if(s14.equals("3933"))
/*  80*/                    byte0 = 34;
                        break;
                    }
/*  80*/            switch(byte0)
                    {
/*  82*/            case 0: // '\0'
/*  82*/                s = s13;
                        break;

/*  85*/            case 1: // '\001'
/*  85*/                obj = s13;
                        break;

/*  88*/            case 2: // '\002'
/*  88*/                s1 = s13;
                        break;

/*  91*/            case 3: // '\003'
/*  91*/                s2 = s13;
                        break;

/*  94*/            case 4: // '\004'
/*  94*/                s3 = s13;
                        break;

/*  97*/            case 5: // '\005'
/*  97*/                s4 = s13;
                        break;

/* 100*/            case 6: // '\006'
/* 100*/                s5 = s13;
                        break;

/* 112*/            case 7: // '\007'
/* 112*/            case 8: // '\b'
/* 112*/            case 9: // '\t'
/* 112*/            case 10: // '\n'
/* 112*/            case 11: // '\013'
/* 112*/            case 12: // '\f'
/* 112*/            case 13: // '\r'
/* 112*/            case 14: // '\016'
/* 112*/            case 15: // '\017'
/* 112*/            case 16: // '\020'
/* 112*/                s6 = s13;
/* 113*/                s7 = "KG";
/* 114*/                s8 = s12.substring(3);
                        break;

/* 126*/            case 17: // '\021'
/* 126*/            case 18: // '\022'
/* 126*/            case 19: // '\023'
/* 126*/            case 20: // '\024'
/* 126*/            case 21: // '\025'
/* 126*/            case 22: // '\026'
/* 126*/            case 23: // '\027'
/* 126*/            case 24: // '\030'
/* 126*/            case 25: // '\031'
/* 126*/            case 26: // '\032'
/* 126*/                s6 = s13;
/* 127*/                s7 = "LB";
/* 128*/                s8 = s12.substring(3);
                        break;

/* 134*/            case 27: // '\033'
/* 134*/            case 28: // '\034'
/* 134*/            case 29: // '\035'
/* 134*/            case 30: // '\036'
/* 134*/                s9 = s13;
/* 135*/                s10 = s12.substring(3);
                        break;

/* 141*/            case 31: // '\037'
/* 141*/            case 32: // ' '
/* 141*/            case 33: // '!'
/* 141*/            case 34: // '"'
/* 141*/                if(s13.length() < 4)
/* 145*/                    return null;
/* 147*/                s9 = s13.substring(3);
/* 148*/                s11 = s13.substring(0, 3);
/* 149*/                s10 = s12.substring(3);
                        break;

/* 153*/            default:
/* 153*/                hashmap.put(s12, s13);
                        break;
                    }
                } while(true);
/* 158*/        return new ExpandedProductParsedResult(result, ((String) (obj)), s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, hashmap);
            }

            private static String findAIvalue(int i, String s)
            {
                char c;
/* 176*/        if((c = s.charAt(i)) != '(')
/* 179*/            return null;
/* 182*/        i = s.substring(i + 1);
/* 184*/        s = new StringBuilder();
/* 185*/        for(int j = 0; j < i.length(); j++)
                {
                    char c1;
/* 186*/            if((c1 = i.charAt(j)) == ')')
/* 188*/                return s.toString();
/* 189*/            if(c1 >= '0' && c1 <= '9')
/* 190*/                s.append(c1);
/* 192*/            else
/* 192*/                return null;
                }

/* 195*/        return s.toString();
            }

            private static String findValue(int i, String s)
            {
/* 199*/        StringBuilder stringbuilder = new StringBuilder();
/* 200*/        i = s.substring(i);
/* 202*/        for(s = 0; s < i.length(); s++)
                {
                    char c;
/* 203*/            if((c = i.charAt(s)) == '(')
                    {
/* 207*/                if(findAIvalue(s, i) != null)
/* 208*/                    break;
/* 208*/                stringbuilder.append('(');
                    } else
                    {
/* 213*/                stringbuilder.append(c);
                    }
                }

/* 216*/        return stringbuilder.toString();
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  41*/        return parse(result);
            }
}
