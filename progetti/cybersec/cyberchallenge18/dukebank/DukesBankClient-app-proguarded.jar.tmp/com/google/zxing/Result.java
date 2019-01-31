// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Result.java

package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing:
//            ResultMetadataType, ResultPoint, BarcodeFormat

public final class Result
{

            public Result(String s, byte abyte0[], ResultPoint aresultpoint[], BarcodeFormat barcodeformat)
            {
/*  40*/        this(s, abyte0, aresultpoint, barcodeformat, System.currentTimeMillis());
            }

            public Result(String s, byte abyte0[], ResultPoint aresultpoint[], BarcodeFormat barcodeformat, long l)
            {
/*  48*/        text = s;
/*  49*/        rawBytes = abyte0;
/*  50*/        resultPoints = aresultpoint;
/*  51*/        format = barcodeformat;
/*  52*/        resultMetadata = null;
/*  53*/        timestamp = l;
            }

            public final String getText()
            {
/*  60*/        return text;
            }

            public final byte[] getRawBytes()
            {
/*  67*/        return rawBytes;
            }

            public final ResultPoint[] getResultPoints()
            {
/*  76*/        return resultPoints;
            }

            public final BarcodeFormat getBarcodeFormat()
            {
/*  83*/        return format;
            }

            public final Map getResultMetadata()
            {
/*  92*/        return resultMetadata;
            }

            public final void putMetadata(ResultMetadataType resultmetadatatype, Object obj)
            {
/*  96*/        if(resultMetadata == null)
/*  97*/            resultMetadata = new EnumMap(com/google/zxing/ResultMetadataType);
/*  99*/        resultMetadata.put(resultmetadatatype, obj);
            }

            public final void putAllMetadata(Map map)
            {
/* 103*/        if(map != null)
                {
/* 104*/            if(resultMetadata == null)
                    {
/* 105*/                resultMetadata = map;
/* 105*/                return;
                    }
/* 107*/            resultMetadata.putAll(map);
                }
            }

            public final void addResultPoints(ResultPoint aresultpoint[])
            {
                ResultPoint aresultpoint1[];
/* 113*/        if((aresultpoint1 = resultPoints) == null)
                {
/* 115*/            resultPoints = aresultpoint;
/* 115*/            return;
                }
/* 116*/        if(aresultpoint != null && aresultpoint.length > 0)
                {
/* 117*/            ResultPoint aresultpoint2[] = new ResultPoint[aresultpoint1.length + aresultpoint.length];
/* 118*/            System.arraycopy(aresultpoint1, 0, aresultpoint2, 0, aresultpoint1.length);
/* 119*/            System.arraycopy(aresultpoint, 0, aresultpoint2, aresultpoint1.length, aresultpoint.length);
/* 120*/            resultPoints = aresultpoint2;
                }
            }

            public final long getTimestamp()
            {
/* 125*/        return timestamp;
            }

            public final String toString()
            {
/* 130*/        return text;
            }

            private final String text;
            private final byte rawBytes[];
            private ResultPoint resultPoints[];
            private final BarcodeFormat format;
            private Map resultMetadata;
            private final long timestamp;
}
