// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AlignmentPattern.java

package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class AlignmentPattern extends ResultPoint
{

            AlignmentPattern(float f, float f1, float f2)
            {
/*  32*/        super(f, f1);
/*  33*/        estimatedModuleSize = f2;
            }

            final boolean aboutEquals(float f, float f1, float f2)
            {
/*  41*/        if(Math.abs(f1 - getY()) <= f && Math.abs(f2 - getX()) <= f)
/*  42*/            return (f = Math.abs(f - estimatedModuleSize)) <= 1.0F || f <= estimatedModuleSize;
/*  45*/        else
/*  45*/            return false;
            }

            final AlignmentPattern combineEstimate(float f, float f1, float f2)
            {
/*  53*/        f1 = (getX() + f1) / 2.0F;
/*  54*/        f = (getY() + f) / 2.0F;
/*  55*/        f2 = (estimatedModuleSize + f2) / 2.0F;
/*  56*/        return new AlignmentPattern(f1, f, f2);
            }

            private final float estimatedModuleSize;
}
