// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProductParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class ProductParsedResult extends ParsedResult
{

            ProductParsedResult(String s)
            {
/*  28*/        this(s, s);
            }

            ProductParsedResult(String s, String s1)
            {
/*  32*/        super(ParsedResultType.PRODUCT);
/*  33*/        productID = s;
/*  34*/        normalizedProductID = s1;
            }

            public final String getProductID()
            {
/*  38*/        return productID;
            }

            public final String getNormalizedProductID()
            {
/*  42*/        return normalizedProductID;
            }

            public final String getDisplayResult()
            {
/*  47*/        return productID;
            }

            private final String productID;
            private final String normalizedProductID;
}
