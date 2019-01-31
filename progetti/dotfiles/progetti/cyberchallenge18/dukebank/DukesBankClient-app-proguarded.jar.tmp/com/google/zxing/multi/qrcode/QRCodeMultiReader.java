// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCodeMultiReader.java

package com.google.zxing.multi.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import java.io.Serializable;
import java.util.*;

public final class QRCodeMultiReader extends QRCodeReader
    implements MultipleBarcodeReader
{
    static final class SAComparator
        implements Serializable, Comparator
    {

                public final int compare(Result result, Result result1)
                {
/* 169*/            result = ((Integer)result.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
/* 170*/            result1 = ((Integer)result1.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
/* 171*/            if(result < result1)
/* 172*/                return -1;
/* 174*/            return result <= result1 ? 0 : 1;
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 166*/            return compare((Result)obj, (Result)obj1);
                }

                private SAComparator()
                {
                }

    }


            public QRCodeMultiReader()
            {
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/*  55*/        return decodeMultiple(binarybitmap, null);
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException
            {
/*  60*/        Object obj = new ArrayList();
/*  61*/        int i = (binarybitmap = binarybitmap = (new MultiDetector(binarybitmap.getBlackMatrix())).detectMulti(map)).length;
/*  62*/        for(int j = 0; j < i; j++)
                {
/*  62*/            Object obj1 = binarybitmap[j];
/*  64*/            try
                    {
/*  64*/                DecoderResult decoderresult = getDecoder().decode(((DetectorResult) (obj1)).getBits(), map);
/*  65*/                ResultPoint aresultpoint[] = ((DetectorResult) (obj1)).getPoints();
/*  67*/                if(decoderresult.getOther() instanceof QRCodeDecoderMetaData)
/*  68*/                    ((QRCodeDecoderMetaData)decoderresult.getOther()).applyMirroredCorrection(aresultpoint);
/*  70*/                aresultpoint = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.QR_CODE);
                        Object obj2;
/*  72*/                if((obj2 = decoderresult.getByteSegments()) != null)
/*  74*/                    aresultpoint.putMetadata(ResultMetadataType.BYTE_SEGMENTS, obj2);
/*  76*/                if((obj2 = decoderresult.getECLevel()) != null)
/*  78*/                    aresultpoint.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, obj2);
/*  80*/                if(decoderresult.hasStructuredAppend())
                        {
/*  81*/                    aresultpoint.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderresult.getStructuredAppendSequenceNumber()));
/*  83*/                    aresultpoint.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderresult.getStructuredAppendParity()));
                        }
/*  86*/                ((List) (obj)).add(aresultpoint);
                    }
/*  87*/            catch(ReaderException _ex) { }
                }

/*  91*/        if(((List) (obj)).isEmpty())
/*  92*/            return EMPTY_RESULT_ARRAY;
/*  94*/        else
/*  94*/            return (Result[])((List) (obj = processStructuredAppend(((List) (obj))))).toArray(new Result[((List) (obj)).size()]);
            }

            private static List processStructuredAppend(List list)
            {
/* 100*/        boolean flag = false;
/* 103*/        Object obj1 = list.iterator();
/* 103*/        do
                {
/* 103*/            if(!((Iterator) (obj1)).hasNext())
/* 103*/                break;
                    Result result1;
/* 103*/            if(!(result1 = (Result)((Iterator) (obj1)).next()).getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE))
/* 105*/                continue;
/* 105*/            flag = true;
/* 106*/            break;
                } while(true);
/* 109*/        if(!flag)
/* 110*/            return list;
/* 114*/        obj1 = new ArrayList();
/* 115*/        ArrayList arraylist1 = new ArrayList();
/* 116*/        list = list.iterator();
/* 116*/        do
                {
/* 116*/            if(!list.hasNext())
/* 116*/                break;
/* 116*/            Result result = (Result)list.next();
/* 117*/            ((List) (obj1)).add(result);
/* 118*/            if(result.getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE))
/* 119*/                arraylist1.add(result);
                } while(true);
/* 123*/        Collections.sort(arraylist1, new SAComparator());
/* 124*/        list = new StringBuilder();
/* 125*/        int i = 0;
/* 126*/        int j = 0;
/* 127*/        Iterator iterator = arraylist1.iterator();
/* 127*/        do
                {
/* 127*/            if(!iterator.hasNext())
/* 127*/                break;
/* 127*/            Result result2 = (Result)iterator.next();
/* 128*/            list.append(result2.getText());
/* 129*/            i += result2.getRawBytes().length;
/* 130*/            if(result2.getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS))
                    {
                        Iterable iterable;
/* 132*/                Iterator iterator1 = (iterable = (Iterable)result2.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS)).iterator();
/* 134*/                while(iterator1.hasNext()) 
                        {
/* 134*/                    byte abyte3[] = (byte[])iterator1.next();
/* 135*/                    j += abyte3.length;
                        }
                    }
                } while(true);
/* 139*/        byte abyte1[] = new byte[i];
/* 140*/        byte abyte2[] = new byte[j];
/* 141*/        int k = 0;
/* 142*/        int l = 0;
/* 143*/        Object obj2 = arraylist1.iterator();
/* 143*/        do
                {
/* 143*/            if(!((Iterator) (obj2)).hasNext())
/* 143*/                break;
                    Object obj;
/* 143*/            System.arraycopy(((Result) (obj = (Result)((Iterator) (obj2)).next())).getRawBytes(), 0, abyte1, k, ((Result) (obj)).getRawBytes().length);
/* 145*/            k += ((Result) (obj)).getRawBytes().length;
/* 146*/            if(((Result) (obj)).getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS))
                    {
/* 148*/                obj = ((Iterable) (obj = (Iterable)((Result) (obj)).getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS))).iterator();
/* 150*/                while(((Iterator) (obj)).hasNext()) 
                        {
                            byte abyte0[];
/* 150*/                    System.arraycopy(abyte0 = (byte[])((Iterator) (obj)).next(), 0, abyte2, l, abyte0.length);
/* 152*/                    l += abyte0.length;
                        }
                    }
                } while(true);
/* 156*/        obj2 = new Result(list.toString(), abyte1, NO_POINTS, BarcodeFormat.QR_CODE);
/* 157*/        if(j > 0)
                {
                    ArrayList arraylist;
/* 158*/            (arraylist = new ArrayList()).add(abyte2);
/* 160*/            ((Result) (obj2)).putMetadata(ResultMetadataType.BYTE_SEGMENTS, arraylist);
                }
/* 162*/        ((List) (obj1)).add(obj2);
/* 163*/        return ((List) (obj1));
            }

            private static final Result EMPTY_RESULT_ARRAY[] = new Result[0];
            private static final ResultPoint NO_POINTS[] = new ResultPoint[0];

}
