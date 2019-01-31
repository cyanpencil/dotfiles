// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Dimensions.java

package com.google.zxing.pdf417.encoder;


public final class Dimensions
{

            public Dimensions(int i, int j, int k, int l)
            {
/*  32*/        minCols = i;
/*  33*/        maxCols = j;
/*  34*/        minRows = k;
/*  35*/        maxRows = l;
            }

            public final int getMinCols()
            {
/*  39*/        return minCols;
            }

            public final int getMaxCols()
            {
/*  43*/        return maxCols;
            }

            public final int getMinRows()
            {
/*  47*/        return minRows;
            }

            public final int getMaxRows()
            {
/*  51*/        return maxRows;
            }

            private final int minCols;
            private final int maxCols;
            private final int minRows;
            private final int maxRows;
}
