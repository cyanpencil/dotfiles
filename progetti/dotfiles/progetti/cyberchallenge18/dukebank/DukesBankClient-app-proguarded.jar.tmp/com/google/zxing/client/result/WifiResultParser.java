// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WifiResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, WifiParsedResult, ParsedResult

public final class WifiResultParser extends ResultParser
{

            public WifiResultParser()
            {
            }

            public final WifiParsedResult parse(Result result)
            {
/*  35*/        if(!(result = getMassagedText(result)).startsWith("WIFI:"))
/*  37*/            return null;
                String s;
/*  39*/        if((s = matchSinglePrefixedField("S:", result, ';', false)) == null || s.isEmpty())
/*  41*/            return null;
/*  43*/        String s1 = matchSinglePrefixedField("P:", result, ';', false);
                String s2;
/*  44*/        if((s2 = matchSinglePrefixedField("T:", result, ';', false)) == null)
/*  46*/            s2 = "nopass";
/*  48*/        result = Boolean.parseBoolean(matchSinglePrefixedField("H:", result, ';', false));
/*  49*/        return new WifiParsedResult(s2, s, s1, result);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  31*/        return parse(result);
            }
}
