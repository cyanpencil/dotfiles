// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FinderPattern.java

package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint
{

            FinderPattern(float f, float f1, float f2)
            {
/*  34*/        this(f, f1, f2, 1);
            }

            private FinderPattern(float f, float f1, float f2, int i)
            {
/*  38*/        super(f, f1);
/*  39*/        estimatedModuleSize = f2;
/*  40*/        count = i;
            }

            public final float getEstimatedModuleSize()
            {
/*  44*/        return estimatedModuleSize;
            }

            final int getCount()
            {
/*  48*/        return count;
            }

            final boolean aboutEquals(float f, float f1, float f2)
            {
/*  62*/        if(Math.abs(f1 - getY()) <= f && Math.abs(f2 - getX()) <= f)
/*  63*/            return (f = Math.abs(f - estimatedModuleSize)) <= 1.0F || f <= estimatedModuleSize;
/*  66*/        else
/*  66*/            return false;
            }

            final FinderPattern combineEstimate(float f, float f1, float f2)
            {
/*  75*/        int i = count + 1;
/*  76*/        f1 = ((float)count * getX() + f1) / (float)i;
/*  77*/        f = ((float)count * getY() + f) / (float)i;
/*  78*/        f2 = ((float)count * estimatedModuleSize + f2) / (float)i;
/*  79*/        return new FinderPattern(f1, f, f2, i);
            }

            private final float estimatedModuleSize;
            private final int count;
}
