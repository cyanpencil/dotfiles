// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SMSTOMMSTOResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, SMSParsedResult, ParsedResult

public final class SMSTOMMSTOResultParser extends ResultParser
{

            public SMSTOMMSTOResultParser()
            {
            }

            public final SMSParsedResult parse(Result result)
            {
/*  35*/        if(!(result = getMassagedText(result)).startsWith("smsto:") && !result.startsWith("SMSTO:") && !result.startsWith("mmsto:") && !result.startsWith("MMSTO:"))
/*  38*/            return null;
/*  42*/        result = result.substring(6);
/*  43*/        String s = null;
                int i;
/*  44*/        if((i = result.indexOf(':')) >= 0)
                {
/*  46*/            s = result.substring(i + 1);
/*  47*/            result = result.substring(0, i);
                }
/*  49*/        return new SMSParsedResult(result, null, null, s);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  31*/        return parse(result);
            }
}
