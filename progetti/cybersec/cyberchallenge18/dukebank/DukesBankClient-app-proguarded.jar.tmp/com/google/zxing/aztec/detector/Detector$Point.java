// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.aztec.detector;

import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.aztec.detector:
//            Detector

static final class y
{

            final ResultPoint toResultPoint()
            {
/* 578*/        return new ResultPoint(getX(), getY());
            }

            final int getX()
            {
/* 587*/        return x;
            }

            final int getY()
            {
/* 591*/        return y;
            }

            public final String toString()
            {
/* 596*/        return (new StringBuilder("<")).append(x).append(' ').append(y).append('>').toString();
            }

            private final int x;
            private final int y;

            (int i, int j)
            {
/* 582*/        x = i;
/* 583*/        y = j;
            }
}
