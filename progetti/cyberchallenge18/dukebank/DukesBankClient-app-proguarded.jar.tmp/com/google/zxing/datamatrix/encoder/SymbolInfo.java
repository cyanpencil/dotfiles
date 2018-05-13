// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SymbolInfo.java

package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;

// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            DataMatrixSymbolInfo144, SymbolShapeHint

public class SymbolInfo
{

            public static void overrideSymbolSet(SymbolInfo asymbolinfo[])
            {
/*  72*/        symbols = asymbolinfo;
            }

            public SymbolInfo(boolean flag, int i, int j, int k, int l, int i1)
            {
/*  86*/        this(flag, i, j, k, l, i1, i, j);
            }

            SymbolInfo(boolean flag, int i, int j, int k, int l, int i1, int j1, 
                    int k1)
            {
/*  93*/        rectangular = flag;
/*  94*/        dataCapacity = i;
/*  95*/        errorCodewords = j;
/*  96*/        matrixWidth = k;
/*  97*/        matrixHeight = l;
/*  98*/        dataRegions = i1;
/*  99*/        rsBlockData = j1;
/* 100*/        rsBlockError = k1;
            }

            public static SymbolInfo lookup(int i)
            {
/* 104*/        return lookup(i, SymbolShapeHint.FORCE_NONE, true);
            }

            public static SymbolInfo lookup(int i, SymbolShapeHint symbolshapehint)
            {
/* 108*/        return lookup(i, symbolshapehint, true);
            }

            public static SymbolInfo lookup(int i, boolean flag, boolean flag1)
            {
/* 112*/        flag = flag ? ((boolean) (SymbolShapeHint.FORCE_NONE)) : ((boolean) (SymbolShapeHint.FORCE_SQUARE));
/* 114*/        return lookup(i, flag, flag1);
            }

            private static SymbolInfo lookup(int i, SymbolShapeHint symbolshapehint, boolean flag)
            {
/* 118*/        return lookup(i, symbolshapehint, null, null, flag);
            }

            public static SymbolInfo lookup(int i, SymbolShapeHint symbolshapehint, Dimension dimension, Dimension dimension1, boolean flag)
            {
                SymbolInfo asymbolinfo[];
/* 126*/        int j = (asymbolinfo = symbols).length;
/* 126*/        for(int k = 0; k < j; k++)
                {
/* 126*/            SymbolInfo symbolinfo = asymbolinfo[k];
/* 127*/            if((symbolshapehint != SymbolShapeHint.FORCE_SQUARE || !symbolinfo.rectangular) && (symbolshapehint != SymbolShapeHint.FORCE_RECTANGLE || symbolinfo.rectangular) && (dimension == null || symbolinfo.getSymbolWidth() >= dimension.getWidth() && symbolinfo.getSymbolHeight() >= dimension.getHeight()) && (dimension1 == null || symbolinfo.getSymbolWidth() <= dimension1.getWidth() && symbolinfo.getSymbolHeight() <= dimension1.getHeight()) && i <= symbolinfo.dataCapacity)
/* 144*/                return symbolinfo;
                }

/* 147*/        if(flag)
/* 148*/            throw new IllegalArgumentException((new StringBuilder("Can't find a symbol arrangement that matches the message. Data codewords: ")).append(i).toString());
/* 152*/        else
/* 152*/            return null;
            }

            final int getHorizontalDataRegions()
            {
/* 156*/        switch(dataRegions)
                {
/* 158*/        case 1: // '\001'
/* 158*/            return 1;

/* 160*/        case 2: // '\002'
/* 160*/            return 2;

/* 162*/        case 4: // '\004'
/* 162*/            return 2;

/* 164*/        case 16: // '\020'
/* 164*/            return 4;

/* 166*/        case 36: // '$'
/* 166*/            return 6;
                }
/* 168*/        throw new IllegalStateException("Cannot handle this number of data regions");
            }

            final int getVerticalDataRegions()
            {
/* 173*/        switch(dataRegions)
                {
/* 175*/        case 1: // '\001'
/* 175*/            return 1;

/* 177*/        case 2: // '\002'
/* 177*/            return 1;

/* 179*/        case 4: // '\004'
/* 179*/            return 2;

/* 181*/        case 16: // '\020'
/* 181*/            return 4;

/* 183*/        case 36: // '$'
/* 183*/            return 6;
                }
/* 185*/        throw new IllegalStateException("Cannot handle this number of data regions");
            }

            public final int getSymbolDataWidth()
            {
/* 190*/        return getHorizontalDataRegions() * matrixWidth;
            }

            public final int getSymbolDataHeight()
            {
/* 194*/        return getVerticalDataRegions() * matrixHeight;
            }

            public final int getSymbolWidth()
            {
/* 198*/        return getSymbolDataWidth() + (getHorizontalDataRegions() << 1);
            }

            public final int getSymbolHeight()
            {
/* 202*/        return getSymbolDataHeight() + (getVerticalDataRegions() << 1);
            }

            public int getCodewordCount()
            {
/* 206*/        return dataCapacity + errorCodewords;
            }

            public int getInterleavedBlockCount()
            {
/* 210*/        return dataCapacity / rsBlockData;
            }

            public final int getDataCapacity()
            {
/* 214*/        return dataCapacity;
            }

            public final int getErrorCodewords()
            {
/* 218*/        return errorCodewords;
            }

            public int getDataLengthForInterleavedBlock(int i)
            {
/* 222*/        return rsBlockData;
            }

            public final int getErrorLengthForInterleavedBlock(int i)
            {
/* 226*/        return rsBlockError;
            }

            public final String toString()
            {
                StringBuilder stringbuilder;
/* 231*/        (stringbuilder = new StringBuilder()).append(rectangular ? "Rectangular Symbol:" : "Square Symbol:");
/* 233*/        stringbuilder.append(" data region ").append(matrixWidth).append('x').append(matrixHeight);
/* 234*/        stringbuilder.append(", symbol size ").append(getSymbolWidth()).append('x').append(getSymbolHeight());
/* 235*/        stringbuilder.append(", symbol data size ").append(getSymbolDataWidth()).append('x').append(getSymbolDataHeight());
/* 236*/        stringbuilder.append(", codewords ").append(dataCapacity).append('+').append(errorCodewords);
/* 237*/        return stringbuilder.toString();
            }

            static final SymbolInfo PROD_SYMBOLS[];
            private static SymbolInfo symbols[];
            private final boolean rectangular;
            private final int dataCapacity;
            private final int errorCodewords;
            public final int matrixWidth;
            public final int matrixHeight;
            private final int dataRegions;
            private final int rsBlockData;
            private final int rsBlockError;

            static 
            {
/*  28*/        symbols = PROD_SYMBOLS = (new SymbolInfo[] {
/*  28*/            new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), 
/*  28*/            new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), 
/*  28*/            new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()
                });
            }
}
