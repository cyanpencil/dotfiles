// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DetectorResult.java

package com.google.zxing.common;

import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix

public class DetectorResult
{

            public DetectorResult(BitMatrix bitmatrix, ResultPoint aresultpoint[])
            {
/*  34*/        bits = bitmatrix;
/*  35*/        points = aresultpoint;
            }

            public final BitMatrix getBits()
            {
/*  39*/        return bits;
            }

            public final ResultPoint[] getPoints()
            {
/*  43*/        return points;
            }

            private final BitMatrix bits;
            private final ResultPoint points[];
}
