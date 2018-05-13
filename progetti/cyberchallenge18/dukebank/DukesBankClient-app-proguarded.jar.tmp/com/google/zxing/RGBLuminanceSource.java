// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RGBLuminanceSource.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class RGBLuminanceSource extends LuminanceSource
{

            public RGBLuminanceSource(int i, int j, int ai[])
            {
/*  35*/        super(i, j);
/*  37*/        dataWidth = i;
/*  38*/        dataHeight = j;
/*  39*/        left = 0;
/*  40*/        top = 0;
/*  44*/        luminances = new byte[i * j];
/*  45*/        for(int k = 0; k < j; k++)
                {
/*  46*/            int l = k * i;
/*  47*/            for(int i1 = 0; i1 < i; i1++)
                    {
                        int j1;
/*  48*/                int k1 = (j1 = ai[l + i1]) >> 16 & 0xff;
/*  50*/                int l1 = j1 >> 8 & 0xff;
/*  51*/                j1 &= 0xff;
/*  52*/                if(k1 == l1 && l1 == j1)
/*  54*/                    luminances[l + i1] = (byte)k1;
/*  57*/                else
/*  57*/                    luminances[l + i1] = (byte)(k1 + l1 + l1 + j1 >> 2);
                    }

                }

            }

            private RGBLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1)
            {
/*  70*/        super(i1, j1);
/*  71*/        if(k + i1 > i || l + j1 > j)
                {
/*  72*/            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
                } else
                {
/*  74*/            luminances = abyte0;
/*  75*/            dataWidth = i;
/*  76*/            dataHeight = j;
/*  77*/            left = k;
/*  78*/            top = l;
/*  79*/            return;
                }
            }

            public final byte[] getRow(int i, byte abyte0[])
            {
/*  83*/        if(i < 0 || i >= getHeight())
/*  84*/            throw new IllegalArgumentException((new StringBuilder("Requested row is outside the image: ")).append(i).toString());
/*  86*/        int j = getWidth();
/*  87*/        if(abyte0 == null || abyte0.length < j)
/*  88*/            abyte0 = new byte[j];
/*  90*/        i = (i + top) * dataWidth + left;
/*  91*/        System.arraycopy(luminances, i, abyte0, 0, j);
/*  92*/        return abyte0;
            }

            public final byte[] getMatrix()
            {
/*  97*/        int i = getWidth();
/*  98*/        int j = getHeight();
/* 102*/        if(i == dataWidth && j == dataHeight)
/* 103*/            return luminances;
                int k;
/* 106*/        byte abyte1[] = new byte[k = i * j];
/* 108*/        int l = top * dataWidth + left;
/* 111*/        if(i == dataWidth)
                {
/* 112*/            System.arraycopy(luminances, l, abyte1, 0, k);
/* 113*/            return abyte1;
                }
/* 117*/        byte abyte0[] = luminances;
/* 118*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 119*/            int j1 = i1 * i;
/* 120*/            System.arraycopy(abyte0, l, abyte1, j1, i);
/* 121*/            l += dataWidth;
                }

/* 123*/        return abyte1;
            }

            public final boolean isCropSupported()
            {
/* 128*/        return true;
            }

            public final LuminanceSource crop(int i, int j, int k, int l)
            {
/* 133*/        return new RGBLuminanceSource(luminances, dataWidth, dataHeight, left + i, top + j, k, l);
            }

            private final byte luminances[];
            private final int dataWidth;
            private final int dataHeight;
            private final int left;
            private final int top;
}
