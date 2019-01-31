// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ITFReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class ITFReader extends OneDReader
{

            public ITFReader()
            {
/*  57*/        narrowLineWidth = -1;
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws FormatException, NotFoundException
            {
/*  89*/        int ai[] = decodeStart(bitarray);
/*  90*/        int ai1[] = decodeEnd(bitarray);
/*  92*/        StringBuilder stringbuilder = new StringBuilder(20);
/*  93*/        decodeMiddle(bitarray, ai[1], ai1[0], stringbuilder);
/*  94*/        bitarray = stringbuilder.toString();
/*  96*/        int ai2[] = null;
/*  97*/        if(map != null)
/*  98*/            ai2 = (int[])map.get(DecodeHintType.ALLOWED_LENGTHS);
/* 101*/        if(ai2 == null)
/* 102*/            ai2 = DEFAULT_ALLOWED_LENGTHS;
/* 107*/        map = bitarray.length();
/* 108*/        boolean flag = false;
/* 109*/        int j = 0;
/* 110*/        int k = (ai2 = ai2).length;
/* 110*/        for(int l = 0; l < k; l++)
                {
/* 110*/            int i1 = ai2[l];
/* 111*/            if(map == i1)
                    {
/* 112*/                flag = true;
/* 113*/                break;
                    }
/* 115*/            if(i1 > j)
/* 116*/                j = i1;
                }

/* 119*/        if(!flag && map > j)
/* 120*/            flag = true;
/* 122*/        if(!flag)
/* 123*/            throw FormatException.getFormatInstance();
/* 126*/        else
/* 126*/            return new Result(bitarray, null, new ResultPoint[] {
/* 126*/                new ResultPoint(ai[1], i), new ResultPoint(ai1[0], i)
                    }, BarcodeFormat.ITF);
            }

            private static void decodeMiddle(BitArray bitarray, int i, int j, StringBuilder stringbuilder)
                throws NotFoundException
            {
/* 150*/        int ai[] = new int[10];
/* 151*/        int ai1[] = new int[5];
/* 152*/        int ai2[] = new int[5];
/* 154*/        while(i < j) 
                {
/* 157*/            recordPattern(bitarray, i, ai);
/* 159*/            for(int k = 0; k < 5; k++)
                    {
/* 160*/                int i1 = k << 1;
/* 161*/                ai1[k] = ai[i1];
/* 162*/                ai2[k] = ai[i1 + 1];
                    }

/* 165*/            int l = decodeDigit(ai1);
/* 166*/            stringbuilder.append((char)(l + 48));
/* 167*/            l = decodeDigit(ai2);
/* 168*/            stringbuilder.append((char)(l + 48));
/* 170*/            int ai3[] = ai;
/* 170*/            l = 0;
/* 170*/            while(l < 10) 
                    {
/* 170*/                int j1 = ai3[l];
/* 171*/                i += j1;
/* 170*/                l++;
                    }
                }
            }

            final int[] decodeStart(BitArray bitarray)
                throws NotFoundException
            {
/* 185*/        int i = skipWhiteSpace(bitarray);
/* 186*/        int ai[] = findGuardPattern(bitarray, i, START_PATTERN);
/* 191*/        narrowLineWidth = ai[1] - ai[0] >> 2;
/* 193*/        validateQuietZone(bitarray, ai[0]);
/* 195*/        return ai;
            }

            private void validateQuietZone(BitArray bitarray, int i)
                throws NotFoundException
            {
                int j;
/* 215*/        j = (j = narrowLineWidth * 10) >= i ? i : j;
/* 220*/        for(i--; j > 0 && i >= 0 && !bitarray.get(i); i--)
/* 224*/            j--;

/* 226*/        if(j != 0)
/* 228*/            throw NotFoundException.getNotFoundInstance();
/* 230*/        else
/* 230*/            return;
            }

            private static int skipWhiteSpace(BitArray bitarray)
                throws NotFoundException
            {
/* 240*/        int i = bitarray.getSize();
/* 241*/        if((bitarray = bitarray.getNextSet(0)) == i)
/* 243*/            throw NotFoundException.getNotFoundInstance();
/* 246*/        else
/* 246*/            return bitarray;
            }

            final int[] decodeEnd(BitArray bitarray)
                throws NotFoundException
            {
/* 261*/        bitarray.reverse();
                int ai[];
/* 263*/        int i = skipWhiteSpace(bitarray);
/* 264*/        ai = findGuardPattern(bitarray, i, END_PATTERN_REVERSED);
/* 269*/        validateQuietZone(bitarray, ai[0]);
/* 274*/        int j = ai[0];
/* 275*/        ai[0] = bitarray.getSize() - ai[1];
/* 276*/        ai[1] = bitarray.getSize() - j;
/* 278*/        ai = ai;
/* 281*/        bitarray.reverse();
/* 281*/        return ai;
                Exception exception;
/* 281*/        exception;
/* 281*/        bitarray.reverse();
/* 281*/        throw exception;
            }

            private static int[] findGuardPattern(BitArray bitarray, int i, int ai[])
                throws NotFoundException
            {
                int j;
/* 300*/        int ai1[] = new int[j = ai.length];
/* 302*/        int k = bitarray.getSize();
/* 303*/        boolean flag = false;
/* 305*/        int l = 0;
/* 306*/        int i1 = i;
/* 307*/        for(i = i; i < k; i++)
                {
/* 308*/            if(bitarray.get(i) ^ flag)
                    {
/* 309*/                ai1[l]++;
/* 309*/                continue;
                    }
/* 311*/            if(l == j - 1)
                    {
/* 312*/                if(patternMatchVariance(ai1, ai, 199) < 107)
/* 313*/                    return (new int[] {
/* 313*/                        i1, i
                            });
/* 315*/                i1 += ai1[0] + ai1[1];
/* 316*/                System.arraycopy(ai1, 2, ai1, 0, j - 2);
/* 317*/                ai1[j - 2] = 0;
/* 318*/                ai1[j - 1] = 0;
/* 319*/                l--;
                    } else
                    {
/* 321*/                l++;
                    }
/* 323*/            ai1[l] = 1;
/* 324*/            flag = !flag;
                }

/* 327*/        throw NotFoundException.getNotFoundInstance();
            }

            private static int decodeDigit(int ai[])
                throws NotFoundException
            {
/* 340*/        int i = 107;
/* 341*/        int j = -1;
/* 342*/        int k = PATTERNS.length;
/* 343*/        for(int l = 0; l < k; l++)
                {
/* 344*/            int ai1[] = PATTERNS[l];
                    int i1;
/* 345*/            if((i1 = patternMatchVariance(ai, ai1, 199)) < i)
                    {
/* 347*/                i = i1;
/* 348*/                j = l;
                    }
                }

/* 351*/        if(j >= 0)
/* 352*/            return j;
/* 354*/        else
/* 354*/            throw NotFoundException.getNotFoundInstance();
            }

            private static final int MAX_AVG_VARIANCE = 107;
            private static final int MAX_INDIVIDUAL_VARIANCE = 199;
            private static final int W = 3;
            private static final int N = 1;
            private static final int DEFAULT_ALLOWED_LENGTHS[] = {
/*  54*/        6, 8, 10, 12, 14
            };
            private int narrowLineWidth;
            private static final int START_PATTERN[] = {
/*  65*/        1, 1, 1, 1
            };
            private static final int END_PATTERN_REVERSED[] = {
/*  66*/        1, 1, 3
            };
            static final int PATTERNS[][] = {
/*  71*/        {
/*  71*/            1, 1, 3, 3, 1
                }, {
/*  71*/            3, 1, 1, 1, 3
                }, {
/*  71*/            1, 3, 1, 1, 3
                }, {
/*  71*/            3, 3, 1, 1, 1
                }, {
/*  71*/            1, 1, 3, 1, 3
                }, {
/*  71*/            3, 1, 3, 1, 1
                }, {
/*  71*/            1, 3, 3, 1, 1
                }, {
/*  71*/            1, 1, 1, 3, 3
                }, {
/*  71*/            3, 1, 1, 3, 1
                }, {
/*  71*/            1, 3, 1, 3, 1
                }
            };

}
