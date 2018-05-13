// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VINResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, VINParsedResult, ParsedResult

public final class VINResultParser extends ResultParser
{

            public VINResultParser()
            {
            }

            public final VINParsedResult parse(Result result)
            {
/*  36*/        if(result.getBarcodeFormat() != BarcodeFormat.CODE_39)
/*  37*/            return null;
/*  39*/        result = result.getText();
/*  40*/        result = IOQ.matcher(result).replaceAll("").trim();
/*  41*/        if(!AZ09.matcher(result).matches())
/*  42*/            return null;
/*  45*/        if(!checkChecksum(result))
/*  46*/            return null;
/*  48*/        String s = result.substring(0, 3);
/*  49*/        return new VINParsedResult(result, s, result.substring(3, 9), result.substring(9, 17), countryCode(s), result.substring(3, 8), modelYear(result.charAt(9)), result.charAt(10), result.substring(11));
/*  58*/        JVM INSTR pop ;
/*  59*/        return null;
            }

            private static boolean checkChecksum(CharSequence charsequence)
            {
/*  64*/        int i = 0;
/*  65*/        for(int j = 0; j < charsequence.length(); j++)
/*  66*/            i += vinPositionWeight(j + 1) * vinCharValue(charsequence.charAt(j));

/*  68*/        char c = charsequence.charAt(8);
/*  69*/        charsequence = checkChar(i % 11);
/*  70*/        return c == charsequence;
            }

            private static int vinCharValue(char c)
            {
/*  74*/        if(c >= 'A' && c <= 'I')
/*  75*/            return (c - 65) + 1;
/*  77*/        if(c >= 'J' && c <= 'R')
/*  78*/            return (c - 74) + 1;
/*  80*/        if(c >= 'S' && c <= 'Z')
/*  81*/            return (c - 83) + 2;
/*  83*/        if(c >= '0' && c <= '9')
/*  84*/            return c - 48;
/*  86*/        else
/*  86*/            throw new IllegalArgumentException();
            }

            private static int vinPositionWeight(int i)
            {
/*  90*/        if(i > 0 && i <= 7)
/*  91*/            return 9 - i;
/*  93*/        if(i == 8)
/*  94*/            return 10;
/*  96*/        if(i == 9)
/*  97*/            return 0;
/*  99*/        if(i >= 10 && i <= 17)
/* 100*/            return 19 - i;
/* 102*/        else
/* 102*/            throw new IllegalArgumentException();
            }

            private static char checkChar(int i)
            {
/* 106*/        if(i < 10)
/* 107*/            return (char)(i + 48);
/* 109*/        if(i == 10)
/* 110*/            return 'X';
/* 112*/        else
/* 112*/            throw new IllegalArgumentException();
            }

            private static int modelYear(char c)
            {
/* 116*/        if(c >= 'E' && c <= 'H')
/* 117*/            return (c - 69) + 1984;
/* 119*/        if(c >= 'J' && c <= 'N')
/* 120*/            return (c - 74) + 1988;
/* 122*/        if(c == 'P')
/* 123*/            return 1993;
/* 125*/        if(c >= 'R' && c <= 'T')
/* 126*/            return (c - 82) + 1994;
/* 128*/        if(c >= 'V' && c <= 'Y')
/* 129*/            return (c - 86) + 1997;
/* 131*/        if(c >= '1' && c <= '9')
/* 132*/            return (c - 49) + 2001;
/* 134*/        if(c >= 'A' && c <= 'D')
/* 135*/            return (c - 65) + 2010;
/* 137*/        else
/* 137*/            throw new IllegalArgumentException();
            }

            private static String countryCode(CharSequence charsequence)
            {
/* 141*/        char c = charsequence.charAt(0);
/* 142*/        charsequence = charsequence.charAt(1);
/* 143*/        switch(c)
                {
/* 141*/        case 54: // '6'
/* 141*/        case 55: // '7'
/* 141*/        case 56: // '8'
/* 141*/        case 58: // ':'
/* 141*/        case 59: // ';'
/* 141*/        case 60: // '<'
/* 141*/        case 61: // '='
/* 141*/        case 62: // '>'
/* 141*/        case 63: // '?'
/* 141*/        case 64: // '@'
/* 141*/        case 65: // 'A'
/* 141*/        case 66: // 'B'
/* 141*/        case 67: // 'C'
/* 141*/        case 68: // 'D'
/* 141*/        case 69: // 'E'
/* 141*/        case 70: // 'F'
/* 141*/        case 71: // 'G'
/* 141*/        case 72: // 'H'
/* 141*/        case 73: // 'I'
/* 141*/        case 78: // 'N'
/* 141*/        case 79: // 'O'
/* 141*/        case 80: // 'P'
/* 141*/        case 81: // 'Q'
/* 141*/        case 82: // 'R'
/* 141*/        case 84: // 'T'
/* 141*/        case 85: // 'U'
/* 141*/        case 89: // 'Y'
/* 141*/        default:
                    break;

/* 147*/        case 49: // '1'
/* 147*/        case 52: // '4'
/* 147*/        case 53: // '5'
/* 147*/            return "US";

/* 149*/        case 50: // '2'
/* 149*/            return "CA";

/* 151*/        case 51: // '3'
/* 151*/            if(charsequence >= 65 && charsequence <= 87)
/* 152*/                return "MX";
/* 156*/            break;

/* 156*/        case 57: // '9'
/* 156*/            if(charsequence >= 65 && charsequence <= 69 || charsequence >= 51 && charsequence <= 57)
/* 157*/                return "BR";
/* 161*/            break;

/* 161*/        case 74: // 'J'
/* 161*/            if(charsequence >= 65 && charsequence <= 84)
/* 162*/                return "JP";
/* 166*/            break;

/* 166*/        case 75: // 'K'
/* 166*/            if(charsequence >= 76 && charsequence <= 82)
/* 167*/                return "KO";
/* 171*/            break;

/* 171*/        case 76: // 'L'
/* 171*/            return "CN";

/* 173*/        case 77: // 'M'
/* 173*/            if(charsequence >= 65 && charsequence <= 69)
/* 174*/                return "IN";
/* 178*/            break;

/* 178*/        case 83: // 'S'
/* 178*/            if(charsequence >= 65 && charsequence <= 77)
/* 179*/                return "UK";
/* 181*/            if(charsequence >= 78 && charsequence <= 84)
/* 182*/                return "DE";
/* 186*/            break;

/* 186*/        case 86: // 'V'
/* 186*/            if(charsequence >= 70 && charsequence <= 82)
/* 187*/                return "FR";
/* 189*/            if(charsequence >= 83 && charsequence <= 87)
/* 190*/                return "ES";
/* 194*/            break;

/* 194*/        case 87: // 'W'
/* 194*/            return "DE";

/* 196*/        case 88: // 'X'
/* 196*/            if(charsequence == 48 || charsequence >= 51 && charsequence <= 57)
/* 197*/                return "RU";
/* 201*/            break;

/* 201*/        case 90: // 'Z'
/* 201*/            if(charsequence >= 65 && charsequence <= 82)
/* 202*/                return "IT";
                    break;
                }
/* 206*/        return null;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  29*/        return parse(result);
            }

            private static final Pattern IOQ = Pattern.compile("[IOQ]");
            private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");

}
