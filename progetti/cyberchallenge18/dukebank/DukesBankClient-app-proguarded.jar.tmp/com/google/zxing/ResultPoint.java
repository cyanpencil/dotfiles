// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResultPoint.java

package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

public class ResultPoint
{

            public ResultPoint(float f, float f1)
            {
/*  33*/        x = f;
/*  34*/        y = f1;
            }

            public final float getX()
            {
/*  38*/        return x;
            }

            public final float getY()
            {
/*  42*/        return y;
            }

            public final boolean equals(Object obj)
            {
/*  47*/        if(obj instanceof ResultPoint)
                {
/*  48*/            obj = (ResultPoint)obj;
/*  49*/            return x == ((ResultPoint) (obj)).x && y == ((ResultPoint) (obj)).y;
                } else
                {
/*  51*/            return false;
                }
            }

            public final int hashCode()
            {
/*  56*/        return 31 * Float.floatToIntBits(x) + Float.floatToIntBits(y);
            }

            public final String toString()
            {
                StringBuilder stringbuilder;
/*  61*/        (stringbuilder = new StringBuilder(25)).append('(');
/*  63*/        stringbuilder.append(x);
/*  64*/        stringbuilder.append(',');
/*  65*/        stringbuilder.append(y);
/*  66*/        stringbuilder.append(')');
/*  67*/        return stringbuilder.toString();
            }

            public static void orderBestPatterns(ResultPoint aresultpoint[])
            {
/*  77*/        float f = distance(aresultpoint[0], aresultpoint[1]);
/*  78*/        float f1 = distance(aresultpoint[1], aresultpoint[2]);
/*  79*/        float f2 = distance(aresultpoint[0], aresultpoint[2]);
/*  85*/        if(f1 >= f && f1 >= f2)
                {
/*  86*/            f1 = aresultpoint[0];
/*  87*/            f = aresultpoint[1];
/*  88*/            f2 = aresultpoint[2];
                } else
/*  89*/        if(f2 >= f1 && f2 >= f)
                {
/*  90*/            f1 = aresultpoint[1];
/*  91*/            f = aresultpoint[0];
/*  92*/            f2 = aresultpoint[2];
                } else
                {
/*  94*/            f1 = aresultpoint[2];
/*  95*/            f = aresultpoint[0];
/*  96*/            f2 = aresultpoint[1];
                }
/* 103*/        if(crossProductZ(f, f1, f2) < 0.0F)
                {
/* 104*/            Object obj = f;
/* 105*/            f = f2;
/* 106*/            f2 = ((float) (obj));
                }
/* 109*/        aresultpoint[0] = f;
/* 110*/        aresultpoint[1] = f1;
/* 111*/        aresultpoint[2] = f2;
            }

            public static float distance(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 119*/        return MathUtils.distance(resultpoint.x, resultpoint.y, resultpoint1.x, resultpoint1.y);
            }

            private static float crossProductZ(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2)
            {
/* 128*/        float f = resultpoint1.x;
/* 129*/        resultpoint1 = resultpoint1.y;
/* 130*/        return (resultpoint2.x - f) * (resultpoint.y - resultpoint1) - (resultpoint2.y - resultpoint1) * (resultpoint.x - f);
            }

            private final float x;
            private final float y;
}
