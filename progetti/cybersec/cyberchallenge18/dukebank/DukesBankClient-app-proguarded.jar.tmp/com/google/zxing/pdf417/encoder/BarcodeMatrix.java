// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BarcodeMatrix.java

package com.google.zxing.pdf417.encoder;


// Referenced classes of package com.google.zxing.pdf417.encoder:
//            BarcodeRow

public final class BarcodeMatrix
{

            BarcodeMatrix(int i, int j)
            {
/*  36*/        matrix = new BarcodeRow[i];
/*  38*/        int k = 0;
/*  38*/        for(int l = matrix.length; k < l; k++)
/*  39*/            matrix[k] = new BarcodeRow((j + 4) * 17 + 1);

/*  41*/        width = j * 17;
/*  42*/        height = i;
/*  43*/        currentRow = -1;
            }

            final void set(int i, int j, byte byte0)
            {
/*  47*/        matrix[j].set(i, byte0);
            }

            final void startRow()
            {
/*  57*/        currentRow++;
            }

            final BarcodeRow getCurrentRow()
            {
/*  61*/        return matrix[currentRow];
            }

            public final byte[][] getMatrix()
            {
/*  65*/        return getScaledMatrix(1, 1);
            }

            public final byte[][] getScaledMatrix(int i, int j)
            {
/*  75*/        byte abyte0[][] = new byte[height * j][width * i];
/*  76*/        int k = height * j;
/*  77*/        for(int l = 0; l < k; l++)
/*  78*/            abyte0[k - l - 1] = matrix[l / j].getScaledRow(i);

/*  80*/        return abyte0;
            }

            private final BarcodeRow matrix[];
            private int currentRow;
            private final int height;
            private final int width;
}
