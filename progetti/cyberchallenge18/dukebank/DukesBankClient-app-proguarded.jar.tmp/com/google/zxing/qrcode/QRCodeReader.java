// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCodeReader.java

package com.google.zxing.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Map;

public class QRCodeReader
    implements Reader
{

            public QRCodeReader()
            {
            }

            protected final Decoder getDecoder()
            {
/*  51*/        return decoder;
            }

            public Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  64*/        return decode(binarybitmap, null);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  72*/        if(map != null && map.containsKey(DecodeHintType.PURE_BARCODE))
                {
/*  73*/            BitMatrix bitmatrix = extractPureBits(binarybitmap.getBlackMatrix());
/*  74*/            binarybitmap = decoder.decode(bitmatrix, map);
/*  75*/            map = NO_POINTS;
                } else
                {
/*  77*/            DetectorResult detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect(map);
/*  78*/            binarybitmap = decoder.decode(detectorresult.getBits(), map);
/*  79*/            map = detectorresult.getPoints();
                }
/*  83*/        if(binarybitmap.getOther() instanceof QRCodeDecoderMetaData)
/*  84*/            ((QRCodeDecoderMetaData)binarybitmap.getOther()).applyMirroredCorrection(map);
/*  87*/        Result result = new Result(binarybitmap.getText(), binarybitmap.getRawBytes(), map, BarcodeFormat.QR_CODE);
/*  88*/        if((map = binarybitmap.getByteSegments()) != null)
/*  90*/            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
/*  92*/        if((map = binarybitmap.getECLevel()) != null)
/*  94*/            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
/*  96*/        if(binarybitmap.hasStructuredAppend())
                {
/*  97*/            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(binarybitmap.getStructuredAppendSequenceNumber()));
/*  99*/            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(binarybitmap.getStructuredAppendParity()));
                }
/* 102*/        return result;
            }

            public void reset()
            {
            }

            private static BitMatrix extractPureBits(BitMatrix bitmatrix)
                throws NotFoundException
            {
/* 120*/        int ai[] = bitmatrix.getTopLeftOnBit();
/* 121*/        int ai1[] = bitmatrix.getBottomRightOnBit();
/* 122*/        if(ai == null || ai1 == null)
/* 123*/            throw NotFoundException.getNotFoundInstance();
/* 126*/        float f = moduleSize(ai, bitmatrix);
/* 128*/        int k = ai[1];
/* 129*/        int l = ai1[1];
/* 130*/        int i = ai[0];
/* 131*/        int j = ai1[0];
/* 134*/        if(i >= j || k >= l)
/* 135*/            throw NotFoundException.getNotFoundInstance();
/* 138*/        if(l - k != j - i)
/* 141*/            j = i + (l - k);
/* 144*/        int j1 = Math.round((float)((j - i) + 1) / f);
/* 145*/        int k1 = Math.round((float)((l - k) + 1) / f);
/* 146*/        if(j1 <= 0 || k1 <= 0)
/* 147*/            throw NotFoundException.getNotFoundInstance();
/* 149*/        if(k1 != j1)
/* 151*/            throw NotFoundException.getNotFoundInstance();
/* 157*/        int l1 = (int)(f / 2.0F);
/* 158*/        k += l1;
/* 159*/        if((j = ((i += l1) + (int)((float)(j1 - 1) * f)) - (j - 1)) > 0)
                {
/* 164*/            if(j > l1)
/* 166*/                throw NotFoundException.getNotFoundInstance();
/* 168*/            i -= j;
                }
/* 170*/        if((j = (k + (int)((float)(k1 - 1) * f)) - (l - 1)) > 0)
                {
/* 172*/            if(j > l1)
/* 174*/                throw NotFoundException.getNotFoundInstance();
/* 176*/            k -= j;
                }
/* 180*/        BitMatrix bitmatrix1 = new BitMatrix(j1, k1);
/* 181*/        for(int i1 = 0; i1 < k1; i1++)
                {
/* 182*/            int i2 = k + (int)((float)i1 * f);
/* 183*/            for(int j2 = 0; j2 < j1; j2++)
/* 184*/                if(bitmatrix.get(i + (int)((float)j2 * f), i2))
/* 185*/                    bitmatrix1.set(j2, i1);

                }

/* 189*/        return bitmatrix1;
            }

            private static float moduleSize(int ai[], BitMatrix bitmatrix)
                throws NotFoundException
            {
/* 193*/        int i = bitmatrix.getHeight();
/* 194*/        int j = bitmatrix.getWidth();
/* 195*/        int k = ai[0];
/* 196*/        int l = ai[1];
/* 197*/        boolean flag = true;
/* 198*/        int i1 = 0;
/* 199*/        for(; k < j && l < i; l++)
                {
/* 200*/            if(flag != bitmatrix.get(k, l))
                    {
/* 201*/                if(++i1 == 5)
/* 204*/                    break;
/* 204*/                flag = !flag;
                    }
/* 206*/            k++;
                }

/* 209*/        if(k == j || l == i)
/* 210*/            throw NotFoundException.getNotFoundInstance();
/* 212*/        else
/* 212*/            return (float)(k - ai[0]) / 7F;
            }

            private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
            private final Decoder decoder = new Decoder();

}
