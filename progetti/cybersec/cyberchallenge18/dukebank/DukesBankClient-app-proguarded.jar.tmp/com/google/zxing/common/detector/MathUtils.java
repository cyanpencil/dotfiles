// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MathUtils.java

package com.google.zxing.common.detector;


public final class MathUtils
{

            private MathUtils()
            {
            }

            public static int round(float f)
            {
/*  29*/        return (int)(f + 0.5F);
            }

            public static float distance(float f, float f1, float f2, float f3)
            {
/*  33*/        f -= f2;
/*  34*/        f1 -= f3;
/*  35*/        return (float)Math.sqrt(f * f + f1 * f1);
            }

            public static float distance(int i, int j, int k, int l)
            {
/*  39*/        i -= k;
/*  40*/        j -= l;
/*  41*/        return (float)Math.sqrt(i * i + j * j);
            }
}
