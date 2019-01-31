// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExpandedProductParsedResult.java

package com.google.zxing.client.result;

import java.util.Map;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class ExpandedProductParsedResult extends ParsedResult
{

            public ExpandedProductParsedResult(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
                    String s7, String s8, String s9, String s10, String s11, String s12, String s13, 
                    Map map)
            {
/*  72*/        super(ParsedResultType.PRODUCT);
/*  73*/        rawText = s;
/*  74*/        productID = s1;
/*  75*/        sscc = s2;
/*  76*/        lotNumber = s3;
/*  77*/        productionDate = s4;
/*  78*/        packagingDate = s5;
/*  79*/        bestBeforeDate = s6;
/*  80*/        expirationDate = s7;
/*  81*/        weight = s8;
/*  82*/        weightType = s9;
/*  83*/        weightIncrement = s10;
/*  84*/        price = s11;
/*  85*/        priceIncrement = s12;
/*  86*/        priceCurrency = s13;
/*  87*/        uncommonAIs = map;
            }

            public final boolean equals(Object obj)
            {
/*  92*/        if(!(obj instanceof ExpandedProductParsedResult))
/*  93*/            return false;
/*  96*/        obj = (ExpandedProductParsedResult)obj;
/*  98*/        return equalsOrNull(productID, ((ExpandedProductParsedResult) (obj)).productID) && equalsOrNull(sscc, ((ExpandedProductParsedResult) (obj)).sscc) && equalsOrNull(lotNumber, ((ExpandedProductParsedResult) (obj)).lotNumber) && equalsOrNull(productionDate, ((ExpandedProductParsedResult) (obj)).productionDate) && equalsOrNull(bestBeforeDate, ((ExpandedProductParsedResult) (obj)).bestBeforeDate) && equalsOrNull(expirationDate, ((ExpandedProductParsedResult) (obj)).expirationDate) && equalsOrNull(weight, ((ExpandedProductParsedResult) (obj)).weight) && equalsOrNull(weightType, ((ExpandedProductParsedResult) (obj)).weightType) && equalsOrNull(weightIncrement, ((ExpandedProductParsedResult) (obj)).weightIncrement) && equalsOrNull(price, ((ExpandedProductParsedResult) (obj)).price) && equalsOrNull(priceIncrement, ((ExpandedProductParsedResult) (obj)).priceIncrement) && equalsOrNull(priceCurrency, ((ExpandedProductParsedResult) (obj)).priceCurrency) && equalsOrNull(uncommonAIs, ((ExpandedProductParsedResult) (obj)).uncommonAIs);
            }

            private static boolean equalsOrNull(Object obj, Object obj1)
            {
/* 114*/        if(obj == null)
/* 114*/            return obj1 == null;
/* 114*/        else
/* 114*/            return obj.equals(obj1);
            }

            public final int hashCode()
            {
                int i;
/* 120*/        return i = (i = (i = (i = (i = (i = (i = (i = (i = (i = (i = (i = (i = 0 ^ hashNotNull(productID)) ^ hashNotNull(sscc)) ^ hashNotNull(lotNumber)) ^ hashNotNull(productionDate)) ^ hashNotNull(bestBeforeDate)) ^ hashNotNull(expirationDate)) ^ hashNotNull(weight)) ^ hashNotNull(weightType)) ^ hashNotNull(weightIncrement)) ^ hashNotNull(price)) ^ hashNotNull(priceIncrement)) ^ hashNotNull(priceCurrency)) ^ hashNotNull(uncommonAIs);
            }

            private static int hashNotNull(Object obj)
            {
/* 137*/        if(obj == null)
/* 137*/            return 0;
/* 137*/        else
/* 137*/            return obj.hashCode();
            }

            public final String getRawText()
            {
/* 141*/        return rawText;
            }

            public final String getProductID()
            {
/* 145*/        return productID;
            }

            public final String getSscc()
            {
/* 149*/        return sscc;
            }

            public final String getLotNumber()
            {
/* 153*/        return lotNumber;
            }

            public final String getProductionDate()
            {
/* 157*/        return productionDate;
            }

            public final String getPackagingDate()
            {
/* 161*/        return packagingDate;
            }

            public final String getBestBeforeDate()
            {
/* 165*/        return bestBeforeDate;
            }

            public final String getExpirationDate()
            {
/* 169*/        return expirationDate;
            }

            public final String getWeight()
            {
/* 173*/        return weight;
            }

            public final String getWeightType()
            {
/* 177*/        return weightType;
            }

            public final String getWeightIncrement()
            {
/* 181*/        return weightIncrement;
            }

            public final String getPrice()
            {
/* 185*/        return price;
            }

            public final String getPriceIncrement()
            {
/* 189*/        return priceIncrement;
            }

            public final String getPriceCurrency()
            {
/* 193*/        return priceCurrency;
            }

            public final Map getUncommonAIs()
            {
/* 197*/        return uncommonAIs;
            }

            public final String getDisplayResult()
            {
/* 202*/        return String.valueOf(rawText);
            }

            public static final String KILOGRAM = "KG";
            public static final String POUND = "LB";
            private final String rawText;
            private final String productID;
            private final String sscc;
            private final String lotNumber;
            private final String productionDate;
            private final String packagingDate;
            private final String bestBeforeDate;
            private final String expirationDate;
            private final String weight;
            private final String weightType;
            private final String weightIncrement;
            private final String price;
            private final String priceIncrement;
            private final String priceCurrency;
            private final Map uncommonAIs;
}
