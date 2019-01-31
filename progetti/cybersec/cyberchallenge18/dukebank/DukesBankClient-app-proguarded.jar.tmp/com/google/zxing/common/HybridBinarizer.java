// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HybridBinarizer.java

package com.google.zxing.common;

import com.google.zxing.*;

// Referenced classes of package com.google.zxing.common:
//            GlobalHistogramBinarizer, BitMatrix

public final class HybridBinarizer extends GlobalHistogramBinarizer
{

            public HybridBinarizer(LuminanceSource luminancesource)
            {
/*  53*/        super(luminancesource);
            }

            public final BitMatrix getBlackMatrix()
                throws NotFoundException
            {
/*  63*/        if(matrix != null)
/*  64*/            return matrix;
                LuminanceSource luminancesource;
/*  66*/        int i = (luminancesource = getLuminanceSource()).getWidth();
/*  68*/        int j = luminancesource.getHeight();
/*  69*/        if(i >= 40 && j >= 40)
                {
/*  70*/            byte abyte0[] = luminancesource.getMatrix();
/*  71*/            int k = i >> 3;
/*  72*/            if((i & 7) != 0)
/*  73*/                k++;
/*  75*/            int l = j >> 3;
/*  76*/            if((j & 7) != 0)
/*  77*/                l++;
/*  79*/            int ai[][] = calculateBlackPoints(abyte0, k, l, i, j);
/*  81*/            BitMatrix bitmatrix = new BitMatrix(i, j);
/*  82*/            calculateThresholdForBlock(abyte0, k, l, i, j, ai, bitmatrix);
/*  83*/            matrix = bitmatrix;
                } else
                {
/*  86*/            matrix = super.getBlackMatrix();
                }
/*  88*/        return matrix;
            }

            public final Binarizer createBinarizer(LuminanceSource luminancesource)
            {
/*  93*/        return new HybridBinarizer(luminancesource);
            }

            private static void calculateThresholdForBlock(byte abyte0[], int i, int j, int k, int l, int ai[][], BitMatrix bitmatrix)
            {
/* 108*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 109*/            int j1 = i1 << 3;
/* 110*/            int k1 = l - 8;
/* 111*/            if(j1 > k1)
/* 112*/                j1 = k1;
/* 114*/            for(int l1 = 0; l1 < i; l1++)
                    {
/* 115*/                int i2 = l1 << 3;
/* 116*/                int j2 = k - 8;
/* 117*/                if(i2 > j2)
/* 118*/                    i2 = j2;
/* 120*/                j2 = cap(l1, 2, i - 3);
/* 121*/                int k2 = cap(i1, 2, j - 3);
/* 122*/                int l2 = 0;
/* 123*/                for(int i3 = -2; i3 <= 2; i3++)
                        {
/* 124*/                    int ai1[] = ai[k2 + i3];
/* 125*/                    l2 += ai1[j2 - 2] + ai1[j2 - 1] + ai1[j2] + ai1[j2 + 1] + ai1[j2 + 2];
                        }

/* 127*/                int j3 = l2 / 25;
/* 128*/                thresholdBlock(abyte0, i2, j1, j3, k, bitmatrix);
                    }

                }

            }

            private static int cap(int i, int j, int k)
            {
/* 134*/        if(i < j)
/* 134*/            return j;
/* 134*/        if(i > k)
/* 134*/            return k;
/* 134*/        else
/* 134*/            return i;
            }

            private static void thresholdBlock(byte abyte0[], int i, int j, int k, int l, BitMatrix bitmatrix)
            {
/* 146*/        int i1 = 0;
/* 146*/        for(int j1 = j * l + i; i1 < 8; j1 += l)
                {
/* 147*/            for(int k1 = 0; k1 < 8; k1++)
/* 149*/                if((abyte0[j1 + k1] & 0xff) <= k)
/* 150*/                    bitmatrix.set(i + k1, j + i1);

/* 146*/            i1++;
                }

            }

            private static int[][] calculateBlackPoints(byte abyte0[], int i, int j, int k, int l)
            {
/* 166*/        int ai[][] = new int[j][i];
/* 167*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 168*/            int j1 = i1 << 3;
/* 169*/            int k1 = l - 8;
/* 170*/            if(j1 > k1)
/* 171*/                j1 = k1;
/* 173*/            for(int l1 = 0; l1 < i; l1++)
                    {
/* 174*/                int i2 = l1 << 3;
/* 175*/                int k2 = k - 8;
/* 176*/                if(i2 > k2)
/* 177*/                    i2 = k2;
/* 179*/                k2 = 0;
/* 180*/                int l2 = 255;
/* 181*/                int i3 = 0;
/* 182*/                int j3 = 0;
/* 182*/                for(i2 = j1 * k + i2; j3 < 8; i2 += k)
                        {
/* 183*/                    for(int k3 = 0; k3 < 8; k3++)
                            {
/* 184*/                        int i4 = abyte0[i2 + k3] & 0xff;
/* 185*/                        k2 += i4;
/* 187*/                        if(i4 < l2)
/* 188*/                            l2 = i4;
/* 190*/                        if(i4 > i3)
/* 191*/                            i3 = i4;
                            }

/* 195*/                    if(i3 - l2 > 24)
                            {
/* 197*/                        j3++;
/* 197*/                        for(i2 += k; j3 < 8; i2 += k)
                                {
/* 198*/                            for(int l3 = 0; l3 < 8; l3++)
/* 199*/                                k2 += abyte0[i2 + l3] & 0xff;

/* 197*/                            j3++;
                                }

                            }
/* 182*/                    j3++;
                        }

/* 206*/                j3 = k2 >> 6;
/* 207*/                if(i3 - l2 <= 24)
                        {
/* 214*/                    j3 = l2 >> 1;
/* 216*/                    if(i1 > 0 && l1 > 0)
                            {
/* 224*/                        int j2 = ai[i1 - 1][l1] + 2 * ai[i1][l1 - 1] + ai[i1 - 1][l1 - 1] >> 2;
/* 226*/                        if(l2 < j2)
/* 227*/                            j3 = j2;
                            }
                        }
/* 231*/                ai[i1][l1] = j3;
                    }

                }

/* 234*/        return ai;
            }

            private static final int BLOCK_SIZE_POWER = 3;
            private static final int BLOCK_SIZE = 8;
            private static final int BLOCK_SIZE_MASK = 7;
            private static final int MINIMUM_DIMENSION = 40;
            private static final int MIN_DYNAMIC_RANGE = 24;
            private BitMatrix matrix;
}
