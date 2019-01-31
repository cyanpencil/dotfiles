// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AddressBookDoCoMoResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, AddressBookParsedResult, ParsedResult

public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser
{

            public AddressBookDoCoMoResultParser()
            {
            }

            public final AddressBookParsedResult parse(Result result)
            {
/*  40*/        if(!(result = getMassagedText(result)).startsWith("MECARD:"))
/*  42*/            return null;
                String as[];
/*  44*/        if((as = matchDoCoMoPrefixedField("N:", result, true)) == null)
/*  46*/            return null;
/*  48*/        String s = parseName(as[0]);
/*  49*/        String s1 = matchSingleDoCoMoPrefixedField("SOUND:", result, true);
/*  50*/        String as1[] = matchDoCoMoPrefixedField("TEL:", result, true);
/*  51*/        String as2[] = matchDoCoMoPrefixedField("EMAIL:", result, true);
/*  52*/        String s2 = matchSingleDoCoMoPrefixedField("NOTE:", result, false);
/*  53*/        String as3[] = matchDoCoMoPrefixedField("ADR:", result, true);
                String s3;
/*  54*/        if(!isStringOfDigits(s3 = matchSingleDoCoMoPrefixedField("BDAY:", result, true), 8))
/*  57*/            s3 = null;
/*  59*/        String as4[] = matchDoCoMoPrefixedField("URL:", result, true);
/*  63*/        result = matchSingleDoCoMoPrefixedField("ORG:", result, true);
/*  65*/        return new AddressBookParsedResult(maybeWrap(s), null, s1, as1, null, as2, null, null, s2, as3, null, result, s3, null, as4, null);
            }

            private static String parseName(String s)
            {
                int i;
/*  84*/        if((i = s.indexOf(',')) >= 0)
/*  87*/            return (new StringBuilder()).append(s.substring(i + 1)).append(' ').append(s.substring(0, i)).toString();
/*  89*/        else
/*  89*/            return s;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  36*/        return parse(result);
            }
}
