// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlobalHistogramBinarizer.java

package com.google.zxing.common;

import com.google.zxing.*;

// Referenced classes of package com.google.zxing.common:
//            BitArray, BitMatrix

public class GlobalHistogramBinarizer extends Binarizer
{

            public GlobalHistogramBinarizer(LuminanceSource luminancesource)
            {
/*  45*/        super(luminancesource);
/*  46*/        luminances = EMPTY;
            }

            public BitArray getBlackRow(int i, BitArray bitarray)
                throws NotFoundException
            {
                LuminanceSource luminancesource;
/*  53*/        int k = (luminancesource = getLuminanceSource()).getWidth();
/*  55*/        if(bitarray == null || bitarray.getSize() < k)
/*  56*/            bitarray = new BitArray(k);
/*  58*/        else
/*  58*/            bitarray.clear();
/*  61*/        initArrays(k);
/*  62*/        i = luminancesource.getRow(i, luminances);
/*  63*/        int ai[] = buckets;
/*  64*/        for(int l = 0; l < k; l++)
                {
/*  65*/            int j1 = i[l] & 0xff;
/*  66*/            ai[j1 >> 3]++;
                }

/*  68*/        int i1 = estimateBlackPoint(ai);
/*  70*/        int k1 = i[0] & 0xff;
/*  71*/        int j = i[1] & 0xff;
/*  72*/        for(int l1 = 1; l1 < k - 1; l1++)
                {
/*  73*/            int i2 = i[l1 + 1] & 0xff;
/*  75*/            if((k1 = (j << 2) - k1 - i2 >> 1) < i1)
/*  77*/                bitarray.set(l1);
/*  79*/            k1 = j;
/*  80*/            j = i2;
                }

/*  82*/        return bitarray;
            }

            public BitMatrix getBlackMatrix()
                throws NotFoundException
            {
                LuminanceSource luminancesource;
/*  88*/        int i = (luminancesource = getLuminanceSource()).getWidth();
/*  90*/        int j = luminancesource.getHeight();
/*  91*/        BitMatrix bitmatrix = new BitMatrix(i, j);
/*  95*/        initArrays(i);
/*  96*/        int ai[] = buckets;
/*  97*/        for(int k = 1; k < 5; k++)
                {
/*  98*/            int i1 = (j * k) / 5;
/*  99*/            byte abyte1[] = luminancesource.getRow(i1, luminances);
/* 100*/            int k1 = (i << 2) / 5;
/* 101*/            for(int i2 = i / 5; i2 < k1; i2++)
                    {
/* 102*/                int k2 = abyte1[i2] & 0xff;
/* 103*/                ai[k2 >> 3]++;
                    }

                }

/* 106*/        int l = estimateBlackPoint(ai);
/* 111*/        byte abyte0[] = luminancesource.getMatrix();
/* 112*/        for(int j1 = 0; j1 < j; j1++)
                {
/* 113*/            int l1 = j1 * i;
/* 114*/            for(int j2 = 0; j2 < i; j2++)
                    {
                        int l2;
/* 115*/                if((l2 = abyte0[l1 + j2] & 0xff) < l)
/* 117*/                    bitmatrix.set(j2, j1);
                    }

                }

/* 122*/        return bitmatrix;
            }

            public Binarizer createBinarizer(LuminanceSource luminancesource)
            {
/* 127*/        return new GlobalHistogramBinarizer(luminancesource);
            }

            private void initArrays(int i)
            {
/* 131*/        if(luminances.length < i)
/* 132*/            luminances = new byte[i];
/* 134*/        for(i = 0; i < 32; i++)
/* 135*/            buckets[i] = 0;

            }

            private static int estimateBlackPoint(int ai[])
                throws NotFoundException
            {
/* 141*/        int i = ai.length;
/* 142*/        int k = 0;
/* 143*/        int l = 0;
/* 144*/        int i1 = 0;
/* 145*/        for(int j1 = 0; j1 < i; j1++)
                {
/* 146*/            if(ai[j1] > i1)
                    {
/* 147*/                l = j1;
/* 148*/                i1 = ai[j1];
                    }
/* 150*/            if(ai[j1] > k)
/* 151*/                k = ai[j1];
                }

/* 156*/        int k1 = 0;
/* 157*/        i1 = 0;
/* 158*/        for(int l1 = 0; l1 < i; l1++)
                {
/* 159*/            int k2 = l1 - l;
                    int i3;
/* 161*/            if((i3 = ai[l1] * k2 * k2) > i1)
                    {
/* 163*/                k1 = l1;
/* 164*/                i1 = i3;
                    }
                }

/* 169*/        if(l > k1)
                {
/* 170*/            int i2 = l;
/* 171*/            l = k1;
/* 172*/            k1 = i2;
                }
/* 177*/        if(k1 - l <= i >> 4)
/* 178*/            throw NotFoundException.getNotFoundInstance();
/* 182*/        int j2 = k1 - 1;
/* 183*/        int l2 = -1;
/* 184*/        for(int j3 = k1 - 1; j3 > l; j3--)
                {
                    int j;
/* 185*/            if((j = (j = j3 - l) * j * (k1 - j3) * (k - ai[j3])) > l2)
                    {
/* 188*/                j2 = j3;
/* 189*/                l2 = j;
                    }
                }

/* 193*/        return j2 << 3;
            }

            private static final int LUMINANCE_BITS = 5;
            private static final int LUMINANCE_SHIFT = 3;
            private static final int LUMINANCE_BUCKETS = 32;
            private static final byte EMPTY[] = new byte[0];
            private byte luminances[];
            private final int buckets[] = new int[32];

}
