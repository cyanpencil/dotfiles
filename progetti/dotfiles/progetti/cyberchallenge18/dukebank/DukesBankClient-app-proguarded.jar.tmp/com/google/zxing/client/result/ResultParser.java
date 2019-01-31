// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            AddressBookAUResultParser, AddressBookDoCoMoResultParser, BizcardResultParser, BookmarkDoCoMoResultParser, 
//            EmailAddressResultParser, EmailDoCoMoResultParser, ExpandedProductResultParser, GeoResultParser, 
//            ISBNResultParser, ProductResultParser, SMSMMSResultParser, SMSTOMMSTOResultParser, 
//            SMTPResultParser, TelResultParser, TextParsedResult, URIResultParser, 
//            URLTOResultParser, VCardResultParser, VEventResultParser, VINResultParser, 
//            WifiResultParser, ParsedResult

public abstract class ResultParser
{

            public ResultParser()
            {
            }

            public abstract ParsedResult parse(Result result);

            protected static String getMassagedText(Result result)
            {
/*  78*/        if((result = result.getText()).startsWith("\uFEFF"))
/*  80*/            result = result.substring(1);
/*  82*/        return result;
            }

            public static ParsedResult parseResult(Result result)
            {
                ResultParser aresultparser[];
/*  86*/        int i = (aresultparser = PARSERS).length;
/*  86*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  86*/            if((obj = ((ResultParser) (obj = aresultparser[j])).parse(result)) != null)
/*  89*/                return ((ParsedResult) (obj));
                }

/*  92*/        return new TextParsedResult(result.getText(), null);
            }

            protected static void maybeAppend(String s, StringBuilder stringbuilder)
            {
/*  96*/        if(s != null)
                {
/*  97*/            stringbuilder.append('\n');
/*  98*/            stringbuilder.append(s);
                }
            }

            protected static void maybeAppend(String as[], StringBuilder stringbuilder)
            {
/* 103*/        if(as != null)
                {
/* 104*/            int i = (as = as).length;
/* 104*/            for(int j = 0; j < i; j++)
                    {
/* 104*/                String s = as[j];
/* 105*/                stringbuilder.append('\n');
/* 106*/                stringbuilder.append(s);
                    }

                }
            }

            protected static String[] maybeWrap(String s)
            {
/* 112*/        if(s == null)
/* 112*/            return null;
/* 112*/        else
/* 112*/            return (new String[] {
/* 112*/                s
                    });
            }

            protected static String unescapeBackslash(String s)
            {
                int i;
/* 116*/        if((i = s.indexOf('\\')) < 0)
/* 118*/            return s;
/* 120*/        int j = s.length();
                StringBuilder stringbuilder;
/* 121*/        (stringbuilder = new StringBuilder(j - 1)).append(s.toCharArray(), 0, i);
/* 123*/        boolean flag = false;
/* 124*/        for(i = i; i < j; i++)
                {
/* 125*/            char c = s.charAt(i);
/* 126*/            if(flag || c != '\\')
                    {
/* 127*/                stringbuilder.append(c);
/* 128*/                flag = false;
                    } else
                    {
/* 130*/                flag = true;
                    }
                }

/* 133*/        return stringbuilder.toString();
            }

            protected static int parseHexDigit(char c)
            {
/* 137*/        if(c >= '0' && c <= '9')
/* 138*/            return c - 48;
/* 140*/        if(c >= 'a' && c <= 'f')
/* 141*/            return 10 + (c - 97);
/* 143*/        if(c >= 'A' && c <= 'F')
/* 144*/            return 10 + (c - 65);
/* 146*/        else
/* 146*/            return -1;
            }

            protected static boolean isStringOfDigits(CharSequence charsequence, int i)
            {
/* 150*/        return charsequence != null && i > 0 && i == charsequence.length() && DIGITS.matcher(charsequence).matches();
            }

            protected static boolean isSubstringOfDigits(CharSequence charsequence, int i, int j)
            {
/* 154*/        if(charsequence == null || j <= 0)
/* 155*/            return false;
/* 157*/        j = i + j;
/* 158*/        return charsequence.length() >= j && DIGITS.matcher(charsequence.subSequence(i, j)).matches();
            }

            static Map parseNameValuePairs(String s)
            {
                int i;
/* 162*/        if((i = s.indexOf('?')) < 0)
/* 164*/            return null;
/* 166*/        HashMap hashmap = new HashMap(3);
/* 167*/        i = (s = AMPERSAND.split(s.substring(i + 1))).length;
/* 167*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 167*/            appendKeyValue(obj = s[j], hashmap);
                }

/* 170*/        return hashmap;
            }

            private static void appendKeyValue(CharSequence charsequence, Map map)
            {
/* 174*/        if((charsequence = EQUALS.split(charsequence, 2)).length == 2)
                {
/* 176*/            Object obj = charsequence[0];
/* 177*/            charsequence = charsequence[1];
/* 179*/            try
                    {
/* 179*/                charsequence = urlDecode(charsequence);
/* 180*/                map.put(obj, charsequence);
/* 183*/                return;
                    }
/* 181*/            catch(IllegalArgumentException _ex) { }
                }
            }

            static String urlDecode(String s)
            {
/* 189*/        return URLDecoder.decode(s, "UTF-8");
/* 190*/        s;
/* 191*/        throw new IllegalStateException(s);
            }

            static String[] matchPrefixedField(String s, String s1, char c, boolean flag)
            {
/* 196*/        ArrayList arraylist = null;
/* 197*/        int i = 0;
/* 198*/        for(int j = s1.length(); i < j && (i = s1.indexOf(s, i)) >= 0;)
                {
/* 204*/            int k = i += s.length();
/* 206*/            Object obj = 1;
/* 207*/            while(obj != 0) 
/* 208*/                if((i = s1.indexOf(c, i)) < 0)
                        {
/* 211*/                    i = s1.length();
/* 212*/                    obj = 0;
                        } else
/* 213*/                if(s1.charAt(i - 1) == '\\')
                        {
/* 215*/                    i++;
                        } else
                        {
/* 218*/                    if(arraylist == null)
/* 219*/                        arraylist = new ArrayList(3);
/* 221*/                    obj = unescapeBackslash(s1.substring(k, i));
/* 222*/                    if(flag)
/* 223*/                        obj = ((String) (obj)).trim();
/* 225*/                    if(!((String) (obj)).isEmpty())
/* 226*/                        arraylist.add(obj);
/* 228*/                    i++;
/* 229*/                    obj = 0;
                        }
                }

/* 233*/        if(arraylist == null || arraylist.isEmpty())
/* 234*/            return null;
/* 236*/        else
/* 236*/            return (String[])arraylist.toArray(new String[arraylist.size()]);
            }

            static String matchSinglePrefixedField(String s, String s1, char c, boolean flag)
            {
/* 240*/        if((s = matchPrefixedField(s, s1, c, flag)) == null)
/* 241*/            return null;
/* 241*/        else
/* 241*/            return s[0];
            }

            private static final ResultParser PARSERS[] = {
/*  42*/        new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), 
/*  42*/        new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()
            };
            private static final Pattern DIGITS = Pattern.compile("\\d+");
            private static final Pattern AMPERSAND = Pattern.compile("&");
            private static final Pattern EQUALS = Pattern.compile("=");
            private static final String BYTE_ORDER_MARK = "\uFEFF";

}
