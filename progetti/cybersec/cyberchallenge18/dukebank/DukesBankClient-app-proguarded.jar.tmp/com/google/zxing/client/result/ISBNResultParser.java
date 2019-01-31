// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ISBNResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ISBNParsedResult, ParsedResult

public final class ISBNResultParser extends ResultParser
{

            public ISBNResultParser()
            {
            }

            public final ISBNParsedResult parse(Result result)
            {
                BarcodeFormat barcodeformat;
/*  34*/        if((barcodeformat = result.getBarcodeFormat()) != BarcodeFormat.EAN_13)
/*  36*/            return null;
                int i;
/*  38*/        if((i = (result = getMassagedText(result)).length()) != 13)
/*  41*/            return null;
/*  43*/        if(!result.startsWith("978") && !result.startsWith("979"))
/*  44*/            return null;
/*  47*/        else
/*  47*/            return new ISBNParsedResult(result);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  27*/        return parse(result);
            }
}
