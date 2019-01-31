// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProductResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ProductParsedResult, ParsedResult

public final class ProductResultParser extends ResultParser
{

            public ProductResultParser()
            {
            }

            public final ProductParsedResult parse(Result result)
            {
                Object obj;
/*  33*/        if((obj = result.getBarcodeFormat()) != BarcodeFormat.UPC_A && obj != BarcodeFormat.UPC_E && obj != BarcodeFormat.EAN_8 && obj != BarcodeFormat.EAN_13)
/*  36*/            return null;
/*  38*/        if(!isStringOfDigits(result = getMassagedText(result), result.length()))
/*  40*/            return null;
/*  46*/        if(obj == BarcodeFormat.UPC_E && result.length() == 8)
/*  47*/            obj = UPCEReader.convertUPCEtoUPCA(result);
/*  49*/        else
/*  49*/            obj = result;
/*  52*/        return new ProductParsedResult(result, ((String) (obj)));
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  28*/        return parse(result);
            }
}
