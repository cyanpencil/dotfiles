// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericMultipleBarcodeReader.java

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.*;

// Referenced classes of package com.google.zxing.multi:
//            MultipleBarcodeReader

public final class GenericMultipleBarcodeReader
    implements MultipleBarcodeReader
{

            public GenericMultipleBarcodeReader(Reader reader)
            {
/*  53*/        _flddelegate = reader;
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/*  58*/        return decodeMultiple(binarybitmap, null);
            }

            public final Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException
            {
/*  64*/        ArrayList arraylist = new ArrayList();
/*  65*/        doDecodeMultiple(binarybitmap, map, arraylist, 0, 0, 0);
/*  66*/        if(arraylist.isEmpty())
/*  67*/            throw NotFoundException.getNotFoundInstance();
/*  69*/        else
/*  69*/            return (Result[])arraylist.toArray(new Result[arraylist.size()]);
            }

            private void doDecodeMultiple(BinaryBitmap binarybitmap, Map map, List list, int i, int j, int k)
            {
/*  78*/        do
                {
/*  78*/            if(k > 4)
/*  79*/                return;
                    Result result;
/*  84*/            try
                    {
/*  84*/                result = _flddelegate.decode(binarybitmap, map);
                    }
/*  85*/            catch(ReaderException _ex)
                    {
/*  86*/                return;
                    }
/*  88*/            boolean flag = false;
/*  89*/            Iterator iterator = list.iterator();
/*  89*/            do
                    {
/*  89*/                if(!iterator.hasNext())
/*  89*/                    break;
                        Result result1;
/*  89*/                if(!(result1 = (Result)iterator.next()).getText().equals(result.getText()))
/*  91*/                    continue;
/*  91*/                flag = true;
/*  92*/                break;
                    } while(true);
/*  95*/            if(!flag)
/*  96*/                list.add(translateResultPoints(result, i, j));
                    ResultPoint aresultpoint[];
/*  98*/            if((aresultpoint = result.getResultPoints()) == null || aresultpoint.length == 0)
/* 100*/                return;
/* 102*/            int i1 = binarybitmap.getWidth();
/* 103*/            int l = binarybitmap.getHeight();
/* 104*/            float f = i1;
/* 105*/            float f1 = l;
/* 106*/            float f2 = 0.0F;
/* 107*/            float f3 = 0.0F;
/* 108*/            int j1 = (aresultpoint = aresultpoint).length;
/* 108*/            for(int k1 = 0; k1 < j1; k1++)
                    {
                        ResultPoint resultpoint;
/* 108*/                float f5 = (resultpoint = aresultpoint[k1]).getX();
/* 110*/                float f4 = resultpoint.getY();
/* 111*/                if(f5 < f)
/* 112*/                    f = f5;
/* 114*/                if(f4 < f1)
/* 115*/                    f1 = f4;
/* 117*/                if(f5 > f2)
/* 118*/                    f2 = f5;
/* 120*/                if(f4 > f3)
/* 121*/                    f3 = f4;
                    }

/* 126*/            if(f > 100F)
/* 127*/                doDecodeMultiple(binarybitmap.crop(0, 0, (int)f, l), map, list, i, j, k + 1);
/* 133*/            if(f1 > 100F)
/* 134*/                doDecodeMultiple(binarybitmap.crop(0, 0, i1, (int)f1), map, list, i, j, k + 1);
/* 140*/            if(f2 < (float)(i1 - 100))
/* 141*/                doDecodeMultiple(binarybitmap.crop((int)f2, 0, i1 - (int)f2, l), map, list, i + (int)f2, j, k + 1);
/* 147*/            if(f3 < (float)(l - 100))
                    {
/* 148*/                k++;
/* 148*/                j = j + (int)f3;
/* 148*/                i = i;
/* 148*/                list = list;
/* 148*/                map = map;
/* 148*/                binarybitmap = binarybitmap.crop(0, (int)f3, i1, l - (int)f3);
/* 148*/                this = this;
                    } else
                    {
/* 153*/                return;
                    }
                } while(true);
            }

            private static Result translateResultPoints(Result result, int i, int j)
            {
                ResultPoint aresultpoint[];
/* 156*/        if((aresultpoint = result.getResultPoints()) == null)
/* 158*/            return result;
/* 160*/        ResultPoint aresultpoint1[] = new ResultPoint[aresultpoint.length];
/* 161*/        for(int k = 0; k < aresultpoint.length; k++)
                {
/* 162*/            ResultPoint resultpoint = aresultpoint[k];
/* 163*/            aresultpoint1[k] = new ResultPoint(resultpoint.getX() + (float)i, resultpoint.getY() + (float)j);
                }

                Result result1;
/* 165*/        (result1 = new Result(result.getText(), result.getRawBytes(), aresultpoint1, result.getBarcodeFormat())).putAllMetadata(result.getResultMetadata());
/* 167*/        return result1;
            }

            private static final int MIN_DIMENSION_TO_RECUR = 100;
            private static final int MAX_DEPTH = 4;
            private final Reader _flddelegate;
}
