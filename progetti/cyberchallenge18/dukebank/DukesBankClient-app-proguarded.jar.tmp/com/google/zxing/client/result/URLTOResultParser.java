// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   URLTOResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, URIParsedResult, ParsedResult

public final class URLTOResultParser extends ResultParser
{

            public URLTOResultParser()
            {
            }

            public final URIParsedResult parse(Result result)
            {
/*  32*/        if(!(result = getMassagedText(result)).startsWith("urlto:") && !result.startsWith("URLTO:"))
/*  34*/            return null;
                int i;
/*  36*/        if((i = result.indexOf(':', 6)) < 0)
                {
/*  38*/            return null;
                } else
                {
/*  40*/            String s = i > 6 ? result.substring(6, i) : null;
/*  41*/            result = result.substring(i + 1);
/*  42*/            return new URIParsedResult(result, s);
                }
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  28*/        return parse(result);
            }
}
