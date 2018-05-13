// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataMatrixWriter.java

package com.google.zxing.datamatrix;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter
    implements Writer
{

            public DataMatrixWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
            {
/*  43*/        return encode(s, barcodeformat, i, j, null);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
            {
/*  49*/        if(s.isEmpty())
/*  50*/            throw new IllegalArgumentException("Found empty contents");
/*  53*/        if(barcodeformat != BarcodeFormat.DATA_MATRIX)
/*  54*/            throw new IllegalArgumentException((new StringBuilder("Can only encode DATA_MATRIX, but got ")).append(barcodeformat).toString());
/*  57*/        if(i < 0 || j < 0)
/*  58*/            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
/*  62*/        barcodeformat = SymbolShapeHint.FORCE_NONE;
/*  63*/        i = null;
/*  64*/        j = null;
/*  65*/        if(map != null)
                {
                    SymbolShapeHint symbolshapehint;
/*  66*/            if((symbolshapehint = (SymbolShapeHint)map.get(EncodeHintType.DATA_MATRIX_SHAPE)) != null)
/*  68*/                barcodeformat = symbolshapehint;
                    Dimension dimension;
/*  70*/            if((dimension = (Dimension)map.get(EncodeHintType.MIN_SIZE)) != null)
/*  72*/                i = dimension;
/*  74*/            if((map = (Dimension)map.get(EncodeHintType.MAX_SIZE)) != null)
/*  76*/                j = map;
                }
                String s1;
/*  82*/        SymbolInfo symbolinfo = SymbolInfo.lookup((s1 = HighLevelEncoder.encodeHighLevel(s, barcodeformat, i, j)).length(), barcodeformat, i, j, true);
/*  87*/        map = ErrorCorrection.encodeECC200(s1, symbolinfo);
/*  90*/        (s = new DefaultPlacement(map, symbolinfo.getSymbolDataWidth(), symbolinfo.getSymbolDataHeight())).place();
/*  95*/        return encodeLowLevel(s, symbolinfo);
            }

            private static BitMatrix encodeLowLevel(DefaultPlacement defaultplacement, SymbolInfo symbolinfo)
            {
/* 106*/        int i = symbolinfo.getSymbolDataWidth();
/* 107*/        int j = symbolinfo.getSymbolDataHeight();
/* 109*/        ByteMatrix bytematrix = new ByteMatrix(symbolinfo.getSymbolWidth(), symbolinfo.getSymbolHeight());
/* 111*/        int k = 0;
/* 113*/        for(int l = 0; l < j; l++)
                {
/* 116*/            if(l % symbolinfo.matrixHeight == 0)
                    {
/* 117*/                int i1 = 0;
/* 118*/                for(int k1 = 0; k1 < symbolinfo.getSymbolWidth(); k1++)
                        {
/* 119*/                    bytematrix.set(i1, k, k1 % 2 == 0);
/* 120*/                    i1++;
                        }

/* 122*/                k++;
                    }
/* 124*/            int j1 = 0;
/* 125*/            for(int l1 = 0; l1 < i; l1++)
                    {
/* 127*/                if(l1 % symbolinfo.matrixWidth == 0)
                        {
/* 128*/                    bytematrix.set(j1, k, true);
/* 129*/                    j1++;
                        }
/* 131*/                bytematrix.set(j1, k, defaultplacement.getBit(l1, l));
/* 132*/                j1++;
/* 134*/                if(l1 % symbolinfo.matrixWidth == symbolinfo.matrixWidth - 1)
                        {
/* 135*/                    bytematrix.set(j1, k, l % 2 == 0);
/* 136*/                    j1++;
                        }
                    }

/* 139*/            k++;
/* 141*/            if(l % symbolinfo.matrixHeight != symbolinfo.matrixHeight - 1)
/* 142*/                continue;
/* 142*/            j1 = 0;
/* 143*/            for(int i2 = 0; i2 < symbolinfo.getSymbolWidth(); i2++)
                    {
/* 144*/                bytematrix.set(j1, k, true);
/* 145*/                j1++;
                    }

/* 147*/            k++;
                }

/* 151*/        return convertByteMatrixToBitMatrix(bytematrix);
            }

            private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix bytematrix)
            {
/* 161*/        int i = bytematrix.getWidth();
/* 162*/        int j = bytematrix.getHeight();
                BitMatrix bitmatrix;
/* 164*/        (bitmatrix = new BitMatrix(i, j)).clear();
/* 166*/        for(int k = 0; k < i; k++)
                {
/* 167*/            for(int l = 0; l < j; l++)
/* 169*/                if(bytematrix.get(k, l) == 1)
/* 170*/                    bitmatrix.set(k, l);

                }

/* 175*/        return bitmatrix;
            }
}
