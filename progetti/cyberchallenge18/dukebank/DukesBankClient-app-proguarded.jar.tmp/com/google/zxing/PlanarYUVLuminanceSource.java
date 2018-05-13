// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PlanarYUVLuminanceSource.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class PlanarYUVLuminanceSource extends LuminanceSource
{

            public PlanarYUVLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1, 
                    boolean flag)
            {
/*  47*/        super(i1, j1);
/*  49*/        if(k + i1 > i || l + j1 > j)
/*  50*/            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
/*  53*/        yuvData = abyte0;
/*  54*/        dataWidth = i;
/*  55*/        dataHeight = j;
/*  56*/        left = k;
/*  57*/        top = l;
/*  58*/        if(flag)
/*  59*/            reverseHorizontal(i1, j1);
            }

            public final byte[] getRow(int i, byte abyte0[])
            {
/*  65*/        if(i < 0 || i >= getHeight())
/*  66*/            throw new IllegalArgumentException((new StringBuilder("Requested row is outside the image: ")).append(i).toString());
/*  68*/        int j = getWidth();
/*  69*/        if(abyte0 == null || abyte0.length < j)
/*  70*/            abyte0 = new byte[j];
/*  72*/        i = (i + top) * dataWidth + left;
/*  73*/        System.arraycopy(yuvData, i, abyte0, 0, j);
/*  74*/        return abyte0;
            }

            public final byte[] getMatrix()
            {
/*  79*/        int i = getWidth();
/*  80*/        int j = getHeight();
/*  84*/        if(i == dataWidth && j == dataHeight)
/*  85*/            return yuvData;
                int k;
/*  88*/        byte abyte1[] = new byte[k = i * j];
/*  90*/        int l = top * dataWidth + left;
/*  93*/        if(i == dataWidth)
                {
/*  94*/            System.arraycopy(yuvData, l, abyte1, 0, k);
/*  95*/            return abyte1;
                }
/*  99*/        byte abyte0[] = yuvData;
/* 100*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 101*/            int j1 = i1 * i;
/* 102*/            System.arraycopy(abyte0, l, abyte1, j1, i);
/* 103*/            l += dataWidth;
                }

/* 105*/        return abyte1;
            }

            public final boolean isCropSupported()
            {
/* 110*/        return true;
            }

            public final LuminanceSource crop(int i, int j, int k, int l)
            {
/* 115*/        return new PlanarYUVLuminanceSource(yuvData, dataWidth, dataHeight, left + i, top + j, k, l, false);
            }

            public final int[] renderThumbnail()
            {
/* 126*/        int i = getWidth() / 2;
/* 127*/        int j = getHeight() / 2;
/* 128*/        int ai[] = new int[i * j];
/* 129*/        byte abyte0[] = yuvData;
/* 130*/        int k = top * dataWidth + left;
/* 132*/        for(int l = 0; l < j; l++)
                {
/* 133*/            int i1 = l * i;
/* 134*/            for(int j1 = 0; j1 < i; j1++)
                    {
/* 135*/                int k1 = abyte0[k + (j1 << 1)] & 0xff;
/* 136*/                ai[i1 + j1] = 0xff000000 | k1 * 0x10101;
                    }

/* 138*/            k += dataWidth << 1;
                }

/* 140*/        return ai;
            }

            public final int getThumbnailWidth()
            {
/* 147*/        return getWidth() / 2;
            }

            public final int getThumbnailHeight()
            {
/* 154*/        return getHeight() / 2;
            }

            private void reverseHorizontal(int i, int j)
            {
/* 158*/        byte abyte0[] = yuvData;
/* 159*/        int k = 0;
/* 159*/        for(int l = top * dataWidth + left; k < j; l += dataWidth)
                {
/* 160*/            int i1 = l + i / 2;
/* 161*/            int j1 = l;
/* 161*/            for(int k1 = (l + i) - 1; j1 < i1; k1--)
                    {
/* 162*/                byte byte0 = abyte0[j1];
/* 163*/                abyte0[j1] = abyte0[k1];
/* 164*/                abyte0[k1] = byte0;
/* 161*/                j1++;
                    }

/* 159*/            k++;
                }

            }

            private static final int THUMBNAIL_SCALE_FACTOR = 2;
            private final byte yuvData[];
            private final int dataWidth;
            private final int dataHeight;
            private final int left;
            private final int top;
}
