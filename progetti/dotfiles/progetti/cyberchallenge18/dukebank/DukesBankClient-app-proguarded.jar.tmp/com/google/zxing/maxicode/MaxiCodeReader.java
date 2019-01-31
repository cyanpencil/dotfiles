// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MaxiCodeReader.java

package com.google.zxing.maxicode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.maxicode.decoder.Decoder;
import java.util.Map;

public final class MaxiCodeReader
    implements Reader
{

            public MaxiCodeReader()
            {
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  62*/        return decode(binarybitmap, null);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  69*/        if(map != null && map.containsKey(DecodeHintType.PURE_BARCODE))
                {
/*  70*/            BitMatrix bitmatrix = extractPureBits(binarybitmap.getBlackMatrix());
/*  71*/            binarybitmap = decoder.decode(bitmatrix, map);
                } else
                {
/*  73*/            throw NotFoundException.getNotFoundInstance();
                }
/*  76*/        ResultPoint aresultpoint[] = NO_POINTS;
/*  77*/        map = new Result(binarybitmap.getText(), binarybitmap.getRawBytes(), aresultpoint, BarcodeFormat.MAXICODE);
/*  79*/        if((binarybitmap = binarybitmap.getECLevel()) != null)
/*  81*/            map.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, binarybitmap);
/*  83*/        return map;
            }

            public final void reset()
            {
            }

            private static BitMatrix extractPureBits(BitMatrix bitmatrix)
                throws NotFoundException
            {
                int ai[];
/* 102*/        if((ai = bitmatrix.getEnclosingRectangle()) == null)
/* 104*/            throw NotFoundException.getNotFoundInstance();
/* 107*/        int j = ai[0];
/* 108*/        int k = ai[1];
/* 109*/        int l = ai[2];
/* 110*/        int i = ai[3];
/* 113*/        BitMatrix bitmatrix1 = new BitMatrix(30, 33);
/* 114*/        for(int i1 = 0; i1 < 33; i1++)
                {
/* 115*/            int j1 = k + (i1 * i + i / 2) / 33;
/* 116*/            for(int k1 = 0; k1 < 30; k1++)
                    {
/* 117*/                int l1 = j + (k1 * l + l / 2 + ((i1 & 1) * l) / 2) / 30;
/* 118*/                if(bitmatrix.get(l1, j1))
/* 119*/                    bitmatrix1.set(k1, i1);
                    }

                }

/* 123*/        return bitmatrix1;
            }

            private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
            private static final int MATRIX_WIDTH = 30;
            private static final int MATRIX_HEIGHT = 33;
            private final Decoder decoder = new Decoder();

}
