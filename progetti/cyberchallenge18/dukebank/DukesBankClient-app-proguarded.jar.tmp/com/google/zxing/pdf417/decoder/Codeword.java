// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Codeword.java

package com.google.zxing.pdf417.decoder;


final class Codeword
{

            Codeword(int i, int j, int k, int l)
            {
/*  30*/        rowNumber = -1;
/*  33*/        startX = i;
/*  34*/        endX = j;
/*  35*/        bucket = k;
/*  36*/        value = l;
            }

            final boolean hasValidRowNumber()
            {
/*  40*/        return isValidRowNumber(rowNumber);
            }

            final boolean isValidRowNumber(int i)
            {
/*  44*/        return i != -1 && bucket == (i % 3) * 3;
            }

            final void setRowNumberAsRowIndicatorColumn()
            {
/*  48*/        rowNumber = (value / 30) * 3 + bucket / 3;
            }

            final int getWidth()
            {
/*  52*/        return endX - startX;
            }

            final int getStartX()
            {
/*  56*/        return startX;
            }

            final int getEndX()
            {
/*  60*/        return endX;
            }

            final int getBucket()
            {
/*  64*/        return bucket;
            }

            final int getValue()
            {
/*  68*/        return value;
            }

            final int getRowNumber()
            {
/*  72*/        return rowNumber;
            }

            final void setRowNumber(int i)
            {
/*  76*/        rowNumber = i;
            }

            public final String toString()
            {
/*  81*/        return (new StringBuilder()).append(rowNumber).append("|").append(value).toString();
            }

            private static final int BARCODE_ROW_UNKNOWN = -1;
            private final int startX;
            private final int endX;
            private final int bucket;
            private final int value;
            private int rowNumber;
}
