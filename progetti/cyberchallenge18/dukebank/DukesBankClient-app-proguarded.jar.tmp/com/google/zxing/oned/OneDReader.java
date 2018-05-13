// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OneDReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

public abstract class OneDReader
    implements Reader
{

            public OneDReader()
            {
            }

            public Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, FormatException
            {
/*  49*/        return decode(binarybitmap, null);
            }

            public Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, FormatException
            {
/*  57*/        return doDecode(binarybitmap, map);
                Object obj;
/*  58*/        obj;
                boolean flag;
/*  59*/        if((flag = map != null && map.containsKey(DecodeHintType.TRY_HARDER)) && binarybitmap.isRotateSupported())
                {
/*  61*/            binarybitmap = binarybitmap.rotateCounterClockwise();
/*  62*/            obj = (map = doDecode(binarybitmap, map)).getResultMetadata();
/*  65*/            int i = 270;
/*  66*/            if(obj != null && ((Map) (obj)).containsKey(ResultMetadataType.ORIENTATION))
/*  68*/                i = (270 + ((Integer)((Map) (obj)).get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
/*  71*/            map.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
/*  73*/            if((obj = map.getResultPoints()) != null)
                    {
/*  75*/                binarybitmap = binarybitmap.getHeight();
/*  76*/                for(int j = 0; j < obj.length; j++)
/*  77*/                    obj[j] = new ResultPoint((float)binarybitmap - obj[j].getY() - 1.0F, obj[j].getX());

                    }
/*  80*/            return map;
                } else
                {
/*  82*/            throw obj;
                }
            }

            public void reset()
            {
            }

            private Result doDecode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException
            {
                int i;
                int j;
                BitArray bitarray;
                int k;
                int l;
                int i1;
                int j1;
/* 108*/        i = binarybitmap.getWidth();
/* 109*/        j = binarybitmap.getHeight();
/* 110*/        bitarray = new BitArray(i);
/* 112*/        k = j >> 1;
/* 113*/        boolean flag = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
/* 114*/        i1 = Math.max(1, j >> (flag ? 8 : 5));
/* 116*/        if(flag)
/* 117*/            l = j;
/* 119*/        else
/* 119*/            l = 15;
/* 122*/        j1 = 0;
_L5:
/* 122*/        if(j1 >= l) goto _L2; else goto _L1
_L1:
                int k1;
                int l1;
/* 125*/        k1 = j1 + 1 >> 1;
/* 126*/        boolean flag1 = (j1 & 1) == 0;
/* 127*/        if((k1 = k + i1 * (flag1 ? k1 : -k1)) < 0 || k1 >= j)
/* 135*/            break; /* Loop/switch isn't completed */
/* 135*/        try
                {
/* 135*/            bitarray = binarybitmap.getBlackRow(k1, bitarray);
                }
/* 136*/        catch(NotFoundException _ex)
                {
/* 137*/            continue; /* Loop/switch isn't completed */
                }
/* 142*/        l1 = 0;
_L4:
/* 142*/        if(l1 >= 2)
/* 143*/            continue; /* Loop/switch isn't completed */
/* 143*/        if(l1 == 1)
                {
/* 144*/            bitarray.reverse();
/* 149*/            if(map != null && map.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
                    {
                        EnumMap enummap;
/* 150*/                (enummap = new EnumMap(com/google/zxing/DecodeHintType)).putAll(map);
/* 152*/                enummap.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
/* 153*/                map = enummap;
                    }
                }
                Result result;
/* 158*/        result = decodeRow(k1, bitarray, map);
/* 160*/        if(l1 == 1)
                {
/* 162*/            result.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                    ResultPoint aresultpoint[];
/* 164*/            if((aresultpoint = result.getResultPoints()) != null)
                    {
/* 166*/                aresultpoint[0] = new ResultPoint((float)i - aresultpoint[0].getX() - 1.0F, aresultpoint[0].getY());
/* 167*/                aresultpoint[1] = new ResultPoint((float)i - aresultpoint[1].getX() - 1.0F, aresultpoint[1].getY());
                    }
                }
/* 170*/        return result;
/* 171*/        JVM INSTR pop ;
/* 142*/        l1++;
/* 142*/        if(true) goto _L4; else goto _L3
_L3:
/* 122*/        j1++;
                  goto _L5
_L2:
/* 177*/        throw NotFoundException.getNotFoundInstance();
            }

            protected static void recordPattern(BitArray bitarray, int i, int ai[])
                throws NotFoundException
            {
/* 196*/        int j = ai.length;
/* 197*/        Arrays.fill(ai, 0, j, 0);
/* 198*/        int k = bitarray.getSize();
/* 199*/        if(i >= k)
/* 200*/            throw NotFoundException.getNotFoundInstance();
/* 202*/        boolean flag = !bitarray.get(i);
/* 203*/        int l = 0;
/* 204*/        for(i = i; i < k; i++)
                {
/* 206*/            if(bitarray.get(i) ^ flag)
                    {
/* 207*/                ai[l]++;
/* 207*/                continue;
                    }
/* 209*/            if(++l == j)
/* 213*/                break;
/* 213*/            ai[l] = 1;
/* 214*/            flag = !flag;
                }

/* 221*/        if(l != j && (l != j - 1 || i != k))
/* 222*/            throw NotFoundException.getNotFoundInstance();
/* 224*/        else
/* 224*/            return;
            }

            protected static void recordPatternInReverse(BitArray bitarray, int i, int ai[])
                throws NotFoundException
            {
/* 229*/        int j = ai.length;
/* 230*/        boolean flag = bitarray.get(i);
/* 231*/        do
                {
/* 231*/            if(i <= 0 || j < 0)
/* 232*/                break;
/* 232*/            if(bitarray.get(--i) != flag)
                    {
/* 233*/                j--;
/* 234*/                flag = !flag;
                    }
                } while(true);
/* 237*/        if(j >= 0)
                {
/* 238*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 240*/            recordPattern(bitarray, i + 1, ai);
/* 241*/            return;
                }
            }

            protected static int patternMatchVariance(int ai[], int ai1[], int i)
            {
/* 259*/        int j = ai.length;
/* 260*/        int k = 0;
/* 261*/        int l = 0;
/* 262*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 263*/            k += ai[i1];
/* 264*/            l += ai1[i1];
                }

/* 266*/        if(k < l)
/* 269*/            return 0x7fffffff;
/* 274*/        int j1 = (k << 8) / l;
/* 275*/        i = i * j1 >> 8;
/* 277*/        l = 0;
/* 278*/        for(int k1 = 0; k1 < j; k1++)
                {
/* 279*/            int l1 = ai[k1] << 8;
/* 280*/            int i2 = ai1[k1] * j1;
/* 281*/            if((l1 = l1 <= i2 ? i2 - l1 : l1 - i2) > i)
/* 283*/                return 0x7fffffff;
/* 285*/            l += l1;
                }

/* 287*/        return l / k;
            }

            public abstract Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, ChecksumException, FormatException;

            protected static final int INTEGER_MATH_SHIFT = 8;
            protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
}
