// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VCardResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult, ParsedResult

public final class VCardResultParser extends ResultParser
{

            public VCardResultParser()
            {
            }

            public final AddressBookParsedResult parse(Result result)
            {
/*  54*/        result = getMassagedText(result);
                Object obj;
/*  55*/        if(!((Matcher) (obj = BEGIN_VCARD.matcher(result))).find() || ((Matcher) (obj)).start() != 0)
/*  57*/            return null;
/*  59*/        if((obj = matchVCardPrefixedField("FN", result, true, false)) == null)
/*  62*/            formatNames(((Iterable) (obj = matchVCardPrefixedField("N", result, true, false))));
                List list;
/*  65*/        String as[] = (list = matchSingleVCardPrefixedField("NICKNAME", result, true, false)) != null ? COMMA.split((CharSequence)list.get(0)) : null;
/*  67*/        List list1 = matchVCardPrefixedField("TEL", result, true, false);
/*  68*/        List list2 = matchVCardPrefixedField("EMAIL", result, true, false);
/*  69*/        List list3 = matchSingleVCardPrefixedField("NOTE", result, false, false);
/*  70*/        List list4 = matchVCardPrefixedField("ADR", result, true, true);
/*  71*/        List list5 = matchSingleVCardPrefixedField("ORG", result, true, true);
                List list6;
/*  72*/        if((list6 = matchSingleVCardPrefixedField("BDAY", result, true, false)) != null && !isLikeVCardDate((CharSequence)list6.get(0)))
/*  74*/            list6 = null;
/*  76*/        List list7 = matchSingleVCardPrefixedField("TITLE", result, true, false);
/*  77*/        List list8 = matchVCardPrefixedField("URL", result, true, false);
/*  78*/        List list9 = matchSingleVCardPrefixedField("IMPP", result, true, false);
/*  79*/        if((result = (result = matchSingleVCardPrefixedField("GEO", result, true, false)) != null ? ((Result) (SEMICOLON_OR_COMMA.split((CharSequence)result.get(0)))) : null) != null && result.length != 2)
/*  82*/            result = null;
/*  84*/        return new AddressBookParsedResult(toPrimaryValues(((Collection) (obj))), as, null, toPrimaryValues(list1), toTypes(list1), toPrimaryValues(list2), toTypes(list2), toPrimaryValue(list9), toPrimaryValue(list3), toPrimaryValues(list4), toTypes(list4), toPrimaryValue(list5), toPrimaryValue(list6), toPrimaryValue(list7), toPrimaryValues(list8), result);
            }

            static List matchVCardPrefixedField(CharSequence charsequence, String s, boolean flag, boolean flag1)
            {
/* 106*/        ArrayList arraylist = null;
/* 107*/        int i = 0;
/* 108*/        int j = s.length();
/* 110*/        do
                {
/* 110*/            if(i >= j)
/* 114*/                break;
/* 114*/            Object obj = Pattern.compile((new StringBuilder("(?:^|\n)")).append(charsequence).append("(?:;([^:]*))?:").toString(), 2).matcher(s);
/* 116*/            if(i > 0)
/* 117*/                i--;
/* 119*/            if(!((Matcher) (obj)).find(i))
/* 122*/                break;
/* 122*/            i = ((Matcher) (obj)).end(0);
/* 124*/            obj = ((Matcher) (obj)).group(1);
/* 125*/            ArrayList arraylist1 = null;
/* 126*/            boolean flag2 = false;
/* 127*/            String s1 = null;
/* 128*/            if(obj != null)
                    {
                        String as[];
/* 129*/                int l = (as = SEMICOLON.split(((CharSequence) (obj)))).length;
/* 129*/                for(int i1 = 0; i1 < l; i1++)
                        {
/* 129*/                    String s3 = as[i1];
/* 130*/                    if(arraylist1 == null)
/* 131*/                        arraylist1 = new ArrayList(1);
/* 133*/                    arraylist1.add(s3);
                            String as1[];
/* 134*/                    if((as1 = EQUALS.split(s3, 2)).length <= 1)
/* 136*/                        continue;
/* 136*/                    String s4 = as1[0];
/* 137*/                    as1 = as1[1];
/* 138*/                    if("ENCODING".equalsIgnoreCase(s4) && "QUOTED-PRINTABLE".equalsIgnoreCase(as1))
                            {
/* 139*/                        flag2 = true;
/* 139*/                        continue;
                            }
/* 140*/                    if("CHARSET".equalsIgnoreCase(s4))
/* 141*/                        s1 = as1;
                        }

                    }
/* 147*/            int k = i;
/* 149*/            do
                    {
/* 149*/                if((i = s.indexOf('\n', i)) < 0)
/* 150*/                    break;
/* 150*/                if(i < s.length() - 1 && (s.charAt(i + 1) == ' ' || s.charAt(i + 1) == '\t'))
                        {
/* 153*/                    i += 2;
/* 153*/                    continue;
                        }
/* 154*/                if(!flag2 || (i <= 0 || s.charAt(i - 1) != '=') && (i < 2 || s.charAt(i - 2) != '='))
/* 157*/                    break;
/* 157*/                i++;
                    } while(true);
/* 163*/            if(i < 0)
/* 165*/                i = j;
/* 166*/            else
/* 166*/            if(i > k)
                    {
/* 168*/                if(arraylist == null)
/* 169*/                    arraylist = new ArrayList(1);
/* 171*/                if(i > 0 && s.charAt(i - 1) == '\r')
/* 172*/                    i--;
/* 174*/                String s2 = s.substring(k, i);
/* 175*/                if(flag)
/* 176*/                    s2 = s2.trim();
/* 178*/                if(flag2)
                        {
/* 179*/                    s2 = decodeQuotedPrintable(s2, s1);
/* 180*/                    if(flag1)
/* 181*/                        s2 = UNESCAPED_SEMICOLONS.matcher(s2).replaceAll("\n").trim();
                        } else
                        {
/* 184*/                    if(flag1)
/* 185*/                        s2 = UNESCAPED_SEMICOLONS.matcher(s2).replaceAll("\n").trim();
/* 187*/                    s2 = CR_LF_SPACE_TAB.matcher(s2).replaceAll("");
/* 188*/                    s2 = NEWLINE_ESCAPE.matcher(s2).replaceAll("\n");
/* 189*/                    s2 = VCARD_ESCAPES.matcher(s2).replaceAll("$1");
                        }
/* 191*/                if(arraylist1 == null)
                        {
                            ArrayList arraylist2;
/* 192*/                    (arraylist2 = new ArrayList(1)).add(s2);
/* 194*/                    arraylist.add(arraylist2);
                        } else
                        {
/* 196*/                    arraylist1.add(0, s2);
/* 197*/                    arraylist.add(arraylist1);
                        }
/* 199*/                i++;
                    } else
                    {
/* 201*/                i++;
                    }
                } while(true);
/* 206*/        return arraylist;
            }

            private static String decodeQuotedPrintable(CharSequence charsequence, String s)
            {
/* 210*/        int i = charsequence.length();
/* 211*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 212*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 213*/        for(int j = 0; j < i; j++)
                {
                    int k;
/* 214*/            switch(k = charsequence.charAt(j))
                    {
/* 210*/            case 10: // '\n'
/* 210*/            case 13: // '\r'
                        break;

/* 220*/            case 61: // '='
/* 220*/                if(j < i - 2 && ((k = charsequence.charAt(j + 1)) != '\r' && k != 10))
                        {
/* 223*/                    int l = charsequence.charAt(j + 2);
/* 224*/                    k = parseHexDigit(k);
/* 225*/                    l = parseHexDigit(l);
/* 226*/                    if(k >= 0 && l >= 0)
/* 227*/                        bytearrayoutputstream.write((k << 4) + l);
/* 229*/                    j += 2;
                        }
                        break;

/* 234*/            default:
/* 234*/                maybeAppendFragment(bytearrayoutputstream, s, stringbuilder);
/* 235*/                stringbuilder.append(k);
                        break;
                    }
                }

/* 238*/        maybeAppendFragment(bytearrayoutputstream, s, stringbuilder);
/* 239*/        return stringbuilder.toString();
            }

            private static void maybeAppendFragment(ByteArrayOutputStream bytearrayoutputstream, String s, StringBuilder stringbuilder)
            {
/* 245*/        if(bytearrayoutputstream.size() > 0)
                {
/* 246*/            byte abyte0[] = bytearrayoutputstream.toByteArray();
/* 248*/            if(s == null)
/* 249*/                s = new String(abyte0, Charset.forName("UTF-8"));
/* 252*/            else
/* 252*/                try
                        {
/* 252*/                    s = new String(abyte0, s);
                        }
/* 253*/                catch(UnsupportedEncodingException _ex)
                        {
/* 254*/                    s = new String(abyte0, Charset.forName("UTF-8"));
                        }
/* 257*/            bytearrayoutputstream.reset();
/* 258*/            stringbuilder.append(s);
                }
            }

            static List matchSingleVCardPrefixedField(CharSequence charsequence, String s, boolean flag, boolean flag1)
            {
/* 266*/        if((charsequence = matchVCardPrefixedField(charsequence, s, flag, flag1)) == null || charsequence.isEmpty())
/* 267*/            return null;
/* 267*/        else
/* 267*/            return (List)charsequence.get(0);
            }

            private static String toPrimaryValue(List list)
            {
/* 271*/        if(list == null || list.isEmpty())
/* 271*/            return null;
/* 271*/        else
/* 271*/            return (String)list.get(0);
            }

            private static String[] toPrimaryValues(Collection collection)
            {
/* 275*/        if(collection == null || collection.isEmpty())
/* 276*/            return null;
/* 278*/        ArrayList arraylist = new ArrayList(collection.size());
/* 279*/        Iterator iterator = collection.iterator();
/* 279*/        do
                {
/* 279*/            if(!iterator.hasNext())
/* 279*/                break;
                    Object obj;
/* 279*/            if((obj = (String)((List) (obj = (List)iterator.next())).get(0)) != null && !((String) (obj)).isEmpty())
/* 282*/                arraylist.add(obj);
                } while(true);
/* 285*/        return (String[])arraylist.toArray(new String[collection.size()]);
            }

            private static String[] toTypes(Collection collection)
            {
/* 289*/        if(collection == null || collection.isEmpty())
/* 290*/            return null;
/* 292*/        ArrayList arraylist = new ArrayList(collection.size());
                String s;
/* 293*/label0:
/* 293*/        for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(s))
                {
/* 293*/            List list = (List)iterator.next();
/* 294*/            s = null;
/* 295*/            int i = 1;
/* 295*/            do
                    {
/* 295*/                if(i >= list.size())
/* 296*/                    continue label0;
                        String s1;
                        int j;
/* 296*/                if((j = (s1 = (String)list.get(i)).indexOf('=')) < 0)
                        {
/* 300*/                    s = s1;
/* 301*/                    continue label0;
                        }
/* 303*/                if("TYPE".equalsIgnoreCase(s1.substring(0, j)))
                        {
/* 304*/                    s = s1.substring(j + 1);
/* 305*/                    continue label0;
                        }
/* 295*/                i++;
                    } while(true);
                }

/* 310*/        return (String[])arraylist.toArray(new String[collection.size()]);
            }

            private static boolean isLikeVCardDate(CharSequence charsequence)
            {
/* 314*/        return charsequence == null || VCARD_LIKE_DATE.matcher(charsequence).matches();
            }

            private static void formatNames(Iterable iterable)
            {
/* 324*/        if(iterable != null)
                {
                    List list;
                    Object obj;
/* 325*/            for(iterable = iterable.iterator(); iterable.hasNext(); list.set(0, ((StringBuilder) (obj)).toString().trim()))
                    {
/* 325*/                obj = (String)(list = (List)iterable.next()).get(0);
/* 327*/                String as[] = new String[5];
/* 328*/                int i = 0;
                        int j;
                        int k;
/* 330*/                for(k = 0; k < 4 && (j = ((String) (obj)).indexOf(';', i)) >= 0;)
                        {
/* 332*/                    as[k] = ((String) (obj)).substring(i, j);
/* 333*/                    k++;
/* 334*/                    i = j + 1;
                        }

/* 336*/                as[k] = ((String) (obj)).substring(i);
/* 337*/                obj = new StringBuilder(100);
/* 338*/                maybeAppendComponent(as, 3, ((StringBuilder) (obj)));
/* 339*/                maybeAppendComponent(as, 1, ((StringBuilder) (obj)));
/* 340*/                maybeAppendComponent(as, 2, ((StringBuilder) (obj)));
/* 341*/                maybeAppendComponent(as, 0, ((StringBuilder) (obj)));
/* 342*/                maybeAppendComponent(as, 4, ((StringBuilder) (obj)));
                    }

                }
            }

            private static void maybeAppendComponent(String as[], int i, StringBuilder stringbuilder)
            {
/* 349*/        if(as[i] != null && !as[i].isEmpty())
                {
/* 350*/            if(stringbuilder.length() > 0)
/* 351*/                stringbuilder.append(' ');
/* 353*/            stringbuilder.append(as[i]);
                }
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  36*/        return parse(result);
            }

            private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
            private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
            private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
            private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
            private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
            private static final Pattern EQUALS = Pattern.compile("=");
            private static final Pattern SEMICOLON = Pattern.compile(";");
            private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
            private static final Pattern COMMA = Pattern.compile(",");
            private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");

}
