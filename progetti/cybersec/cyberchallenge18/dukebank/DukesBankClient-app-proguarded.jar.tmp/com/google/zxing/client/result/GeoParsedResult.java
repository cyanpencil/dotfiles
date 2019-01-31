// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GeoParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class GeoParsedResult extends ParsedResult
{

            GeoParsedResult(double d, double d1, double d2, String s)
            {
/*  30*/        super(ParsedResultType.GEO);
/*  31*/        latitude = d;
/*  32*/        longitude = d1;
/*  33*/        altitude = d2;
/*  34*/        query = s;
            }

            public final String getGeoURI()
            {
                StringBuilder stringbuilder;
/*  38*/        (stringbuilder = new StringBuilder()).append("geo:");
/*  40*/        stringbuilder.append(latitude);
/*  41*/        stringbuilder.append(',');
/*  42*/        stringbuilder.append(longitude);
/*  43*/        if(altitude > 0.0D)
                {
/*  44*/            stringbuilder.append(',');
/*  45*/            stringbuilder.append(altitude);
                }
/*  47*/        if(query != null)
                {
/*  48*/            stringbuilder.append('?');
/*  49*/            stringbuilder.append(query);
                }
/*  51*/        return stringbuilder.toString();
            }

            public final double getLatitude()
            {
/*  58*/        return latitude;
            }

            public final double getLongitude()
            {
/*  65*/        return longitude;
            }

            public final double getAltitude()
            {
/*  72*/        return altitude;
            }

            public final String getQuery()
            {
/*  79*/        return query;
            }

            public final String getDisplayResult()
            {
                StringBuilder stringbuilder;
/*  84*/        (stringbuilder = new StringBuilder(20)).append(latitude);
/*  86*/        stringbuilder.append(", ");
/*  87*/        stringbuilder.append(longitude);
/*  88*/        if(altitude > 0.0D)
                {
/*  89*/            stringbuilder.append(", ");
/*  90*/            stringbuilder.append(altitude);
/*  91*/            stringbuilder.append('m');
                }
/*  93*/        if(query != null)
                {
/*  94*/            stringbuilder.append(" (");
/*  95*/            stringbuilder.append(query);
/*  96*/            stringbuilder.append(')');
                }
/*  98*/        return stringbuilder.toString();
            }

            private final double latitude;
            private final double longitude;
            private final double altitude;
            private final String query;
}
