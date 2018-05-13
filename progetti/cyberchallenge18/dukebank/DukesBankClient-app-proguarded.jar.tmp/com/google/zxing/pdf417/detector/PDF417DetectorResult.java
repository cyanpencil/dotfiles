// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417DetectorResult.java

package com.google.zxing.pdf417.detector;

import com.google.zxing.common.BitMatrix;
import java.util.List;

public final class PDF417DetectorResult
{

            public PDF417DetectorResult(BitMatrix bitmatrix, List list)
            {
/*  33*/        bits = bitmatrix;
/*  34*/        points = list;
            }

            public final BitMatrix getBits()
            {
/*  38*/        return bits;
            }

            public final List getPoints()
            {
/*  42*/        return points;
            }

            private final BitMatrix bits;
            private final List points;
}
