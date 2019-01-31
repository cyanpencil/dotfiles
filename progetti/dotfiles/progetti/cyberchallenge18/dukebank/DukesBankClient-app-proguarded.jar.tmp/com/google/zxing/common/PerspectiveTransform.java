// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerspectiveTransform.java

package com.google.zxing.common;


public final class PerspectiveTransform
{

            private PerspectiveTransform(float f, float f1, float f2, float f3, float f4, float f5, float f6, 
                    float f7, float f8)
            {
/*  41*/        a11 = f;
/*  42*/        a12 = f3;
/*  43*/        a13 = f6;
/*  44*/        a21 = f1;
/*  45*/        a22 = f4;
/*  46*/        a23 = f7;
/*  47*/        a31 = f2;
/*  48*/        a32 = f5;
/*  49*/        a33 = f8;
            }

            public static PerspectiveTransform quadrilateralToQuadrilateral(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, 
                    float f8, float f9, float f10, float f11, float f12, float f13, float f14, 
                    float f15)
            {
/*  61*/        f = quadrilateralToSquare(f, f1, f2, f3, f4, f5, f6, f7);
/*  62*/        return (f1 = squareToQuadrilateral(f8, f9, f10, f11, f12, f13, f14, f15)).times(f);
            }

            public final void transformPoints(float af[])
            {
/*  67*/        int i = af.length;
/*  68*/        float f = a11;
/*  69*/        float f1 = a12;
/*  70*/        float f2 = a13;
/*  71*/        float f3 = a21;
/*  72*/        float f4 = a22;
/*  73*/        float f5 = a23;
/*  74*/        float f6 = a31;
/*  75*/        float f7 = a32;
/*  76*/        float f8 = a33;
/*  77*/        for(int j = 0; j < i; j += 2)
                {
/*  78*/            float f9 = af[j];
/*  79*/            float f10 = af[j + 1];
/*  80*/            float f11 = f2 * f9 + f5 * f10 + f8;
/*  81*/            af[j] = (f * f9 + f3 * f10 + f6) / f11;
/*  82*/            af[j + 1] = (f1 * f9 + f4 * f10 + f7) / f11;
                }

            }

            public final void transformPoints(float af[], float af1[])
            {
/*  88*/        int i = af.length;
/*  89*/        for(int j = 0; j < i; j++)
                {
/*  90*/            float f = af[j];
/*  91*/            float f1 = af1[j];
/*  92*/            float f2 = a13 * f + a23 * f1 + a33;
/*  93*/            af[j] = (a11 * f + a21 * f1 + a31) / f2;
/*  94*/            af1[j] = (a12 * f + a22 * f1 + a32) / f2;
                }

            }

            public static PerspectiveTransform squareToQuadrilateral(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7)
            {
/* 102*/        float f8 = ((f - f2) + f4) - f6;
/* 103*/        float f9 = ((f1 - f3) + f5) - f7;
/* 104*/        if(f8 == 0.0F && f9 == 0.0F)
                {
/* 106*/            return new PerspectiveTransform(f2 - f, f4 - f2, f, f3 - f1, f5 - f3, f1, 0.0F, 0.0F, 1.0F);
                } else
                {
/* 110*/            float f10 = f2 - f4;
/* 111*/            f4 = f6 - f4;
/* 112*/            float f11 = f3 - f5;
/* 113*/            f5 = f7 - f5;
/* 114*/            float f12 = f10 * f5 - f4 * f11;
/* 115*/            f4 = (f8 * f5 - f4 * f9) / f12;
/* 116*/            f5 = (f10 * f9 - f8 * f11) / f12;
/* 117*/            return new PerspectiveTransform((f2 - f) + f4 * f2, (f6 - f) + f5 * f6, f, (f3 - f1) + f4 * f3, (f7 - f1) + f5 * f7, f1, f4, f5, 1.0F);
                }
            }

            public static PerspectiveTransform quadrilateralToSquare(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7)
            {
/* 128*/        return squareToQuadrilateral(f, f1, f2, f3, f4, f5, f6, f7).buildAdjoint();
            }

            final PerspectiveTransform buildAdjoint()
            {
/* 133*/        return new PerspectiveTransform(a22 * a33 - a23 * a32, a23 * a31 - a21 * a33, a21 * a32 - a22 * a31, a13 * a32 - a12 * a33, a11 * a33 - a13 * a31, a12 * a31 - a11 * a32, a12 * a23 - a13 * a22, a13 * a21 - a11 * a23, a11 * a22 - a12 * a21);
            }

            final PerspectiveTransform times(PerspectiveTransform perspectivetransform)
            {
/* 145*/        return new PerspectiveTransform(a11 * perspectivetransform.a11 + a21 * perspectivetransform.a12 + a31 * perspectivetransform.a13, a11 * perspectivetransform.a21 + a21 * perspectivetransform.a22 + a31 * perspectivetransform.a23, a11 * perspectivetransform.a31 + a21 * perspectivetransform.a32 + a31 * perspectivetransform.a33, a12 * perspectivetransform.a11 + a22 * perspectivetransform.a12 + a32 * perspectivetransform.a13, a12 * perspectivetransform.a21 + a22 * perspectivetransform.a22 + a32 * perspectivetransform.a23, a12 * perspectivetransform.a31 + a22 * perspectivetransform.a32 + a32 * perspectivetransform.a33, a13 * perspectivetransform.a11 + a23 * perspectivetransform.a12 + a33 * perspectivetransform.a13, a13 * perspectivetransform.a21 + a23 * perspectivetransform.a22 + a33 * perspectivetransform.a23, a13 * perspectivetransform.a31 + a23 * perspectivetransform.a32 + a33 * perspectivetransform.a33);
            }

            private final float a11;
            private final float a12;
            private final float a13;
            private final float a21;
            private final float a22;
            private final float a23;
            private final float a31;
            private final float a32;
            private final float a33;
}
