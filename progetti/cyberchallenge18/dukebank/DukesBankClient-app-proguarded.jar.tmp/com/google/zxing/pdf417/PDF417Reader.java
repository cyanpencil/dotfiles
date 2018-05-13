// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417Reader.java

package com.google.zxing.pdf417;

import com.google.zxing.*;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.pdf417.decoder.PDF417ScanningDecoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.pdf417.detector.PDF417DetectorResult;
import java.util.*;

// Referenced classes of package com.google.zxing.pdf417:
//            PDF417ResultMetadata

public final class PDF417Reader
    implements Reader, MultipleBarcodeReader
{

            public PDF417Reader()
            {
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  55*/        return decode(binarybitmap, null);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  61*/        if((binarybitmap = decode(binarybitmap, map, false)) == null || binarybitmap.length == 0 || binarybitmap[0] == null)
/*  63*/            throw NotFoundException.getNotFoundInstance();
/*  65*/        else
/*  65*/            return binarybitmap[0];
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/*  70*/        return decodeMultiple(binarybitmap, null);
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException
            {
/*  76*/        return decode(binarybitmap, map, true);
/*  77*/        JVM INSTR pop ;
/*  78*/        throw NotFoundException.getNotFoundInstance();
            }

            private static Result[] decode(BinaryBitmap binarybitmap, Map map, boolean flag)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  84*/        ArrayList arraylist = new ArrayList();
/*  85*/        for(map = (binarybitmap = Detector.detect(binarybitmap, map, flag)).getPoints().iterator(); map.hasNext(); arraylist.add(flag))
                {
/*  86*/            flag = (ResultPoint[])map.next();
/*  87*/            Object obj = PDF417ScanningDecoder.decode(binarybitmap.getBits(), flag[4], flag[5], flag[6], flag[7], getMinCodewordWidth(flag), getMaxCodewordWidth(flag));
/*  89*/            (flag = new Result(((DecoderResult) (obj)).getText(), ((DecoderResult) (obj)).getRawBytes(), flag, BarcodeFormat.PDF_417)).putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, ((DecoderResult) (obj)).getECLevel());
/*  91*/            if((obj = (PDF417ResultMetadata)((DecoderResult) (obj)).getOther()) != null)
/*  93*/                flag.putMetadata(ResultMetadataType.PDF417_EXTRA_METADATA, obj);
                }

/*  97*/        return (Result[])arraylist.toArray(new Result[arraylist.size()]);
            }

            private static int getMaxWidth(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 101*/        if(resultpoint == null || resultpoint1 == null)
/* 102*/            return 0;
/* 104*/        else
/* 104*/            return (int)Math.abs(resultpoint.getX() - resultpoint1.getX());
            }

            private static int getMinWidth(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 108*/        if(resultpoint == null || resultpoint1 == null)
/* 109*/            return 0x7fffffff;
/* 111*/        else
/* 111*/            return (int)Math.abs(resultpoint.getX() - resultpoint1.getX());
            }

            private static int getMaxCodewordWidth(ResultPoint aresultpoint[])
            {
/* 115*/        return Math.max(Math.max(getMaxWidth(aresultpoint[0], aresultpoint[4]), (getMaxWidth(aresultpoint[6], aresultpoint[2]) * 17) / 18), Math.max(getMaxWidth(aresultpoint[1], aresultpoint[5]), (getMaxWidth(aresultpoint[7], aresultpoint[3]) * 17) / 18));
            }

            private static int getMinCodewordWidth(ResultPoint aresultpoint[])
            {
/* 123*/        return Math.min(Math.min(getMinWidth(aresultpoint[0], aresultpoint[4]), (getMinWidth(aresultpoint[6], aresultpoint[2]) * 17) / 18), Math.min(getMinWidth(aresultpoint[1], aresultpoint[5]), (getMinWidth(aresultpoint[7], aresultpoint[3]) * 17) / 18));
            }

            public final void reset()
            {
            }
}
