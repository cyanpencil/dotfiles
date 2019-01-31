// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BarcodeMetadata.java

package com.google.zxing.pdf417.decoder;


final class BarcodeMetadata
{

            BarcodeMetadata(int i, int j, int k, int l)
            {
/*  31*/        columnCount = i;
/*  32*/        errorCorrectionLevel = l;
/*  33*/        rowCountUpperPart = j;
/*  34*/        rowCountLowerPart = k;
/*  35*/        rowCount = j + k;
            }

            final int getColumnCount()
            {
/*  39*/        return columnCount;
            }

            final int getErrorCorrectionLevel()
            {
/*  43*/        return errorCorrectionLevel;
            }

            final int getRowCount()
            {
/*  47*/        return rowCount;
            }

            final int getRowCountUpperPart()
            {
/*  51*/        return rowCountUpperPart;
            }

            final int getRowCountLowerPart()
            {
/*  55*/        return rowCountLowerPart;
            }

            private final int columnCount;
            private final int errorCorrectionLevel;
            private final int rowCountUpperPart;
            private final int rowCountLowerPart;
            private final int rowCount;
}
