// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FinderPattern.java

package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern
{

            public FinderPattern(int i, int ai[], int j, int k, int l)
            {
/*  28*/        value = i;
/*  29*/        startEnd = ai;
/*  30*/        resultPoints = (new ResultPoint[] {
/*  30*/            new ResultPoint(j, l), new ResultPoint(k, l)
                });
            }

            public final int getValue()
            {
/*  37*/        return value;
            }

            public final int[] getStartEnd()
            {
/*  41*/        return startEnd;
            }

            public final ResultPoint[] getResultPoints()
            {
/*  45*/        return resultPoints;
            }

            public final boolean equals(Object obj)
            {
/*  50*/        if(!(obj instanceof FinderPattern))
/*  51*/            return false;
/*  53*/        obj = (FinderPattern)obj;
/*  54*/        return value == ((FinderPattern) (obj)).value;
            }

            public final int hashCode()
            {
/*  59*/        return value;
            }

            private final int value;
            private final int startEnd[];
            private final ResultPoint resultPoints[];
}
