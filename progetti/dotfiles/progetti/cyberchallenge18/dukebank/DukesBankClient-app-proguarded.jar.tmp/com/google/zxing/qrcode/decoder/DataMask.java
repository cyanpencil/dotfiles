// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataMask.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask
{
    static final class DataMask111 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 160*/            return ((i + j & 1) + (i * j) % 3 & 1) == 0;
                }

                private DataMask111()
                {
                }

    }

    static final class DataMask110 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 149*/            return (((i *= j) & 1) + i % 3 & 1) == 0;
                }

                private DataMask110()
                {
                }

    }

    static final class DataMask101 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 138*/            return ((i *= j) & 1) + i % 3 == 0;
                }

                private DataMask101()
                {
                }

    }

    static final class DataMask100 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 128*/            return ((i >>> 1) + j / 3 & 1) == 0;
                }

                private DataMask100()
                {
                }

    }

    static final class DataMask011 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 118*/            return (i + j) % 3 == 0;
                }

                private DataMask011()
                {
                }

    }

    static final class DataMask010 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/* 108*/            return j % 3 == 0;
                }

                private DataMask010()
                {
                }

    }

    static final class DataMask001 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/*  98*/            return (i & 1) == 0;
                }

                private DataMask001()
                {
                }

    }

    static final class DataMask000 extends DataMask
    {

                final boolean isMasked(int i, int j)
                {
/*  88*/            return (i + j & 1) == 0;
                }

                private DataMask000()
                {
                }

    }


            private DataMask()
            {
            }

            final void unmaskBitMatrix(BitMatrix bitmatrix, int i)
            {
/*  59*/        for(int j = 0; j < i; j++)
                {
/*  60*/            for(int k = 0; k < i; k++)
/*  61*/                if(isMasked(j, k))
/*  62*/                    bitmatrix.flip(k, j);

                }

            }

            abstract boolean isMasked(int i, int j);

            static DataMask forReference(int i)
            {
/*  76*/        if(i < 0 || i > 7)
/*  77*/            throw new IllegalArgumentException();
/*  79*/        else
/*  79*/            return DATA_MASKS[i];
            }


            private static final DataMask DATA_MASKS[] = {
/*  37*/        new DataMask000(), new DataMask001(), new DataMask010(), new DataMask011(), new DataMask100(), new DataMask101(), new DataMask110(), new DataMask111()
            };

}
