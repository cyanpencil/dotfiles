// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataMatrixReader.java

package com.google.zxing.datamatrix;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Map;

public final class DataMatrixReader
    implements Reader
{

            public DataMatrixReader()
            {
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  59*/        return decode(binarybitmap, null);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  67*/        if(map != null && map.containsKey(DecodeHintType.PURE_BARCODE))
                {
/*  68*/            map = extractPureBits(binarybitmap.getBlackMatrix());
/*  69*/            binarybitmap = decoder.decode(map);
/*  70*/            map = NO_POINTS;
                } else
                {
/*  72*/            map = (new Detector(binarybitmap.getBlackMatrix())).detect();
/*  73*/            binarybitmap = decoder.decode(map.getBits());
/*  74*/            map = map.getPoints();
                }
/*  76*/        map = new Result(binarybitmap.getText(), binarybitmap.getRawBytes(), map, BarcodeFormat.DATA_MATRIX);
                java.util.List list;
/*  78*/        if((list = binarybitmap.getByteSegments()) != null)
/*  80*/            map.putMetadata(ResultMetadataType.BYTE_SEGMENTS, list);
/*  82*/        if((binarybitmap = binarybitmap.getECLevel()) != null)
/*  84*/            map.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, binarybitmap);
/*  86*/        return map;
            }

            public final void reset()
            {
            }

            private static BitMatrix extractPureBits(BitMatrix bitmatrix)
                throws NotFoundException
            {
/* 104*/        int ai[] = bitmatrix.getTopLeftOnBit();
/* 105*/        int ai1[] = bitmatrix.getBottomRightOnBit();
/* 106*/        if(ai == null || ai1 == null)
/* 107*/            throw NotFoundException.getNotFoundInstance();
/* 110*/        int k = moduleSize(ai, bitmatrix);
/* 112*/        int l = ai[1];
/* 113*/        int i1 = ai1[1];
/* 114*/        int i = ai[0];
                int j;
/* 115*/        j = (((j = ai1[0]) - i) + 1) / k;
/* 118*/        i1 = ((i1 - l) + 1) / k;
/* 119*/        if(j <= 0 || i1 <= 0)
/* 120*/            throw NotFoundException.getNotFoundInstance();
/* 126*/        int j1 = k >> 1;
/* 127*/        l += j1;
/* 128*/        i += j1;
/* 131*/        BitMatrix bitmatrix1 = new BitMatrix(j, i1);
/* 132*/        for(int k1 = 0; k1 < i1; k1++)
                {
/* 133*/            int l1 = l + k1 * k;
/* 134*/            for(int i2 = 0; i2 < j; i2++)
/* 135*/                if(bitmatrix.get(i + i2 * k, l1))
/* 136*/                    bitmatrix1.set(i2, k1);

                }

/* 140*/        return bitmatrix1;
            }

            private static int moduleSize(int ai[], BitMatrix bitmatrix)
                throws NotFoundException
            {
/* 144*/        int i = bitmatrix.getWidth();
/* 145*/        int j = ai[0];
/* 146*/        for(int k = ai[1]; j < i && bitmatrix.get(j, k); j++);
/* 150*/        if(j == i)
/* 151*/            throw NotFoundException.getNotFoundInstance();
/* 154*/        if((ai = j - ai[0]) == 0)
/* 156*/            throw NotFoundException.getNotFoundInstance();
/* 158*/        else
/* 158*/            return ai;
            }

            private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
            private final Decoder decoder = new Decoder();

}
