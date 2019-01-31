// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417Writer.java

package com.google.zxing.pdf417;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer
    implements Writer
{

            public PDF417Writer()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  48*/        if(barcodeformat != BarcodeFormat.PDF_417)
/*  49*/            throw new IllegalArgumentException((new StringBuilder("Can only encode PDF_417, but got ")).append(barcodeformat).toString());
/*  52*/        barcodeformat = new PDF417();
/*  53*/        int k = 30;
/*  55*/        if(map != null)
                {
/*  56*/            if(map.containsKey(EncodeHintType.PDF417_COMPACT))
/*  57*/                barcodeformat.setCompact(((Boolean)map.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
/*  59*/            if(map.containsKey(EncodeHintType.PDF417_COMPACTION))
/*  60*/                barcodeformat.setCompaction((Compaction)map.get(EncodeHintType.PDF417_COMPACTION));
/*  62*/            if(map.containsKey(EncodeHintType.PDF417_DIMENSIONS))
                    {
/*  63*/                Dimensions dimensions = (Dimensions)map.get(EncodeHintType.PDF417_DIMENSIONS);
/*  64*/                barcodeformat.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
                    }
/*  69*/            if(map.containsKey(EncodeHintType.MARGIN))
/*  70*/                k = ((Number)map.get(EncodeHintType.MARGIN)).intValue();
/*  72*/            if(map.containsKey(EncodeHintType.CHARACTER_SET))
                    {
/*  73*/                String s1 = (String)map.get(EncodeHintType.CHARACTER_SET);
/*  74*/                barcodeformat.setEncoding(Charset.forName(s1));
                    }
                }
/*  78*/        return bitMatrixFromEncoder(barcodeformat, s, i, j, k);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
                throws WriterException
            {
/*  86*/        return encode(s, barcodeformat, i, j, null);
            }

            private static BitMatrix bitMatrixFromEncoder(PDF417 pdf417, String s, int i, int j, int k)
                throws WriterException
            {
/*  98*/        pdf417.generateBarcodeLogic(s, 2);
/* 102*/        s = pdf417.getBarcodeMatrix().getScaledMatrix(2, 8);
/* 103*/        boolean flag = false;
/* 104*/        if((j > i) ^ (s[0].length < s.length))
                {
/* 105*/            s = rotateArray(s);
/* 106*/            flag = true;
                }
/* 109*/        i /= s[0].length;
/* 110*/        j /= s.length;
/* 113*/        if(i < j)
/* 114*/            i = i;
/* 116*/        else
/* 116*/            i = j;
/* 119*/        if(i > 1)
                {
/* 120*/            pdf417 = pdf417.getBarcodeMatrix().getScaledMatrix(i << 1, i << 2 << 1);
/* 122*/            if(flag)
/* 123*/                pdf417 = rotateArray(pdf417);
/* 125*/            return bitMatrixFrombitArray(pdf417, k);
                } else
                {
/* 127*/            return bitMatrixFrombitArray(s, k);
                }
            }

            private static BitMatrix bitMatrixFrombitArray(byte abyte0[][], int i)
            {
                BitMatrix bitmatrix;
/* 139*/        (bitmatrix = new BitMatrix(abyte0[0].length + 2 * i, abyte0.length + 2 * i)).clear();
/* 141*/        int j = 0;
/* 141*/        for(int k = bitmatrix.getHeight() - i - 1; j < abyte0.length; k--)
                {
/* 142*/            for(int l = 0; l < abyte0[0].length; l++)
/* 144*/                if(abyte0[j][l] == 1)
/* 145*/                    bitmatrix.set(l + i, k);

/* 141*/            j++;
                }

/* 149*/        return bitmatrix;
            }

            private static byte[][] rotateArray(byte abyte0[][])
            {
/* 156*/        byte abyte1[][] = new byte[abyte0[0].length][abyte0.length];
/* 157*/        for(int i = 0; i < abyte0.length; i++)
                {
/* 160*/            int j = abyte0.length - i - 1;
/* 161*/            for(int k = 0; k < abyte0[0].length; k++)
/* 162*/                abyte1[k][j] = abyte0[i][k];

                }

/* 165*/        return abyte1;
            }

            static final int WHITE_SPACE = 30;
}
