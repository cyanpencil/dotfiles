// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BinaryBitmap.java

package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing:
//            Binarizer, LuminanceSource, NotFoundException

public final class BinaryBitmap
{

            public BinaryBitmap(Binarizer binarizer1)
            {
/*  34*/        if(binarizer1 == null)
                {
/*  35*/            throw new IllegalArgumentException("Binarizer must be non-null.");
                } else
                {
/*  37*/            binarizer = binarizer1;
/*  38*/            return;
                }
            }

            public final int getWidth()
            {
/*  44*/        return binarizer.getWidth();
            }

            public final int getHeight()
            {
/*  51*/        return binarizer.getHeight();
            }

            public final BitArray getBlackRow(int i, BitArray bitarray)
                throws NotFoundException
            {
/*  65*/        return binarizer.getBlackRow(i, bitarray);
            }

            public final BitMatrix getBlackMatrix()
                throws NotFoundException
            {
/*  82*/        if(matrix == null)
/*  83*/            matrix = binarizer.getBlackMatrix();
/*  85*/        return matrix;
            }

            public final boolean isCropSupported()
            {
/*  92*/        return binarizer.getLuminanceSource().isCropSupported();
            }

            public final BinaryBitmap crop(int i, int j, int k, int l)
            {
/* 106*/        i = binarizer.getLuminanceSource().crop(i, j, k, l);
/* 107*/        return new BinaryBitmap(binarizer.createBinarizer(i));
            }

            public final boolean isRotateSupported()
            {
/* 114*/        return binarizer.getLuminanceSource().isRotateSupported();
            }

            public final BinaryBitmap rotateCounterClockwise()
            {
/* 124*/        LuminanceSource luminancesource = binarizer.getLuminanceSource().rotateCounterClockwise();
/* 125*/        return new BinaryBitmap(binarizer.createBinarizer(luminancesource));
            }

            public final BinaryBitmap rotateCounterClockwise45()
            {
/* 135*/        LuminanceSource luminancesource = binarizer.getLuminanceSource().rotateCounterClockwise45();
/* 136*/        return new BinaryBitmap(binarizer.createBinarizer(luminancesource));
            }

            public final String toString()
            {
/* 142*/        return getBlackMatrix().toString();
/* 143*/        JVM INSTR pop ;
/* 144*/        return "";
            }

            private final Binarizer binarizer;
            private BitMatrix matrix;
}
