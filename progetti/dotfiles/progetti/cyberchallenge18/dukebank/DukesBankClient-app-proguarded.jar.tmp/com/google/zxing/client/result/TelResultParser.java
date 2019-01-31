// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TelResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, TelParsedResult, ParsedResult

public final class TelResultParser extends ResultParser
{

            public TelResultParser()
            {
            }

            public final TelParsedResult parse(Result result)
            {
/*  30*/        if(!(result = getMassagedText(result)).startsWith("tel:") && !result.startsWith("TEL:"))
                {
/*  32*/            return null;
                } else
                {
/*  35*/            Object obj = result.startsWith("TEL:") ? ((Object) ((new StringBuilder("tel:")).append(result.substring(4)).toString())) : ((Object) (result));
                    int i;
/*  37*/            result = (i = result.indexOf('?', 4)) >= 0 ? ((Result) (result.substring(4, i))) : ((Result) (result.substring(4)));
/*  39*/            return new TelParsedResult(result, ((String) (obj)), null);
                }
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  26*/        return parse(result);
            }
}
