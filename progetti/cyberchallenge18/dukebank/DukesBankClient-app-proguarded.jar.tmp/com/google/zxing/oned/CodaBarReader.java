// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodaBarReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class CodaBarReader extends OneDReader
{

            public CodaBarReader()
            {
/*  75*/        counters = new int[80];
/*  76*/        counterLength = 0;
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException
            {
/*  82*/        Arrays.fill(counters, 0);
/*  83*/        setCounters(bitarray);
/*  84*/        int j = bitarray = findStartPattern();
/*  87*/        decodeRowResult.setLength(0);
                float f;
/*  89*/        do
                {
/*  89*/            if((f = toNarrowWidePattern(j)) == -1)
/*  91*/                throw NotFoundException.getNotFoundInstance();
/*  96*/            decodeRowResult.append((char)f);
/*  97*/            j += 8;
                } while((decodeRowResult.length() <= 1 || !arrayContains(STARTEND_ENCODING, ALPHABET[f])) && j < counterLength);
/* 106*/        f = counters[j - 1];
/* 107*/        int k = 0;
/* 108*/        for(int l = -8; l < -1; l++)
/* 109*/            k += counters[j + l];

/* 115*/        if(j < counterLength && f < k / 2)
/* 116*/            throw NotFoundException.getNotFoundInstance();
/* 119*/        validatePattern(bitarray);
/* 122*/        for(int i1 = 0; i1 < decodeRowResult.length(); i1++)
/* 123*/            decodeRowResult.setCharAt(i1, ALPHABET[decodeRowResult.charAt(i1)]);

/* 126*/        char c = decodeRowResult.charAt(0);
/* 127*/        if(!arrayContains(STARTEND_ENCODING, c))
/* 128*/            throw NotFoundException.getNotFoundInstance();
/* 130*/        f = decodeRowResult.charAt(decodeRowResult.length() - 1);
/* 131*/        if(!arrayContains(STARTEND_ENCODING, f))
/* 132*/            throw NotFoundException.getNotFoundInstance();
/* 136*/        if(decodeRowResult.length() <= 3)
/* 138*/            throw NotFoundException.getNotFoundInstance();
/* 141*/        if(map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END))
                {
/* 142*/            decodeRowResult.deleteCharAt(decodeRowResult.length() - 1);
/* 143*/            decodeRowResult.deleteCharAt(0);
                }
/* 146*/        map = 0;
/* 147*/        for(f = 0; f < bitarray; f++)
/* 148*/            map += counters[f];

/* 150*/        f = (float)map;
/* 151*/        for(bitarray = bitarray; bitarray < j - 1; bitarray++)
/* 152*/            map += counters[bitarray];

/* 154*/        bitarray = (float)map;
/* 155*/        return new Result(decodeRowResult.toString(), null, new ResultPoint[] {
/* 155*/            new ResultPoint(f, i), new ResultPoint(bitarray, i)
                }, BarcodeFormat.CODABAR);
            }

            final void validatePattern(int i)
                throws NotFoundException
            {
/* 166*/        int ai[] = {
/* 166*/            0, 0, 0, 0
                };
/* 167*/        int ai1[] = {
/* 167*/            0, 0, 0, 0
                };
/* 168*/        int l = decodeRowResult.length() - 1;
/* 172*/        int i1 = i;
/* 173*/        int j1 = 0;
/* 174*/        do
                {
/* 174*/            int k1 = CHARACTER_ENCODINGS[decodeRowResult.charAt(j1)];
/* 175*/            for(int l1 = 6; l1 >= 0; l1--)
                    {
/* 178*/                int k2 = (l1 & 1) + ((k1 & 1) << 1);
/* 179*/                ai[k2] += counters[i1 + l1];
/* 180*/                ai1[k2]++;
/* 181*/                k1 >>= 1;
                    }

/* 183*/            if(j1 >= l)
/* 187*/                break;
/* 187*/            i1 += 8;
/* 173*/            j1++;
                } while(true);
/* 191*/        int ai2[] = new int[4];
/* 192*/        int ai3[] = new int[4];
/* 196*/        for(int i2 = 0; i2 < 2; i2++)
                {
/* 197*/            ai3[i2] = 0;
/* 198*/            ai3[i2 + 2] = (ai[i2] << 8) / ai1[i2] + (ai[i2 + 2] << 8) / ai1[i2 + 2] >> 1;
/* 200*/            ai2[i2] = ai3[i2 + 2];
/* 201*/            ai2[i2 + 2] = ((ai[i2 + 2] << 9) + 384) / ai1[i2 + 2];
                }

/* 205*/        i1 = i;
/* 206*/        int j2 = 0;
/* 207*/        do
                {
/* 207*/            int l2 = CHARACTER_ENCODINGS[decodeRowResult.charAt(j2)];
/* 208*/            for(i = 6; i >= 0; i--)
                    {
/* 211*/                int j = (i & 1) + ((l2 & 1) << 1);
                        int k;
/* 212*/                if((k = counters[i1 + i] << 8) < ai3[j] || k > ai2[j])
/* 214*/                    throw NotFoundException.getNotFoundInstance();
/* 216*/                l2 >>= 1;
                    }

/* 218*/            if(j2 < l)
                    {
/* 221*/                i1 += 8;
/* 206*/                j2++;
                    } else
                    {
/* 223*/                return;
                    }
                } while(true);
            }

            private void setCounters(BitArray bitarray)
                throws NotFoundException
            {
/* 232*/        counterLength = 0;
/* 234*/        int i = bitarray.getNextUnset(0);
/* 235*/        int j = bitarray.getSize();
/* 236*/        if(i >= j)
/* 237*/            throw NotFoundException.getNotFoundInstance();
/* 239*/        boolean flag = true;
/* 240*/        int k = 0;
/* 241*/        for(; i < j; i++)
/* 242*/            if(bitarray.get(i) ^ flag)
                    {
/* 243*/                k++;
                    } else
                    {
/* 245*/                counterAppend(k);
/* 246*/                k = 1;
/* 247*/                flag = !flag;
                    }

/* 251*/        counterAppend(k);
            }

            private void counterAppend(int i)
            {
/* 255*/        counters[counterLength] = i;
/* 256*/        counterLength++;
/* 257*/        if(counterLength >= counters.length)
                {
/* 258*/            i = new int[counterLength << 1];
/* 259*/            System.arraycopy(counters, 0, i, 0, counterLength);
/* 260*/            counters = i;
                }
            }

            private int findStartPattern()
                throws NotFoundException
            {
/* 265*/        for(int i = 1; i < counterLength; i += 2)
                {
                    int j;
/* 266*/            if((j = toNarrowWidePattern(i)) == -1 || !arrayContains(STARTEND_ENCODING, ALPHABET[j]))
/* 270*/                continue;
/* 270*/            j = 0;
/* 271*/            for(int k = i; k < i + 7; k++)
/* 272*/                j += counters[k];

/* 274*/            if(i == 1 || counters[i - 1] >= j / 2)
/* 275*/                return i;
                }

/* 279*/        throw NotFoundException.getNotFoundInstance();
            }

            static boolean arrayContains(char ac[], char c)
            {
/* 283*/        if(ac != null)
                {
/* 284*/            int i = (ac = ac).length;
/* 284*/            for(int j = 0; j < i; j++)
                    {
                        char c1;
/* 284*/                if((c1 = ac[j]) == c)
/* 286*/                    return true;
                    }

                }
/* 290*/        return false;
            }

            private int toNarrowWidePattern(int i)
            {
                int j;
/* 295*/        if((j = i + 7) >= counterLength)
/* 297*/            return -1;
/* 300*/        int ai[] = counters;
/* 302*/        int k = 0;
/* 303*/        int j1 = 0x7fffffff;
/* 304*/        for(int k1 = i; k1 < j; k1 += 2)
                {
                    int i2;
/* 305*/            if((i2 = ai[k1]) < j1)
/* 307*/                j1 = i2;
/* 309*/            if(i2 > k)
/* 310*/                k = i2;
                }

/* 313*/        int l1 = (j1 + k) / 2;
/* 315*/        int j2 = 0;
/* 316*/        k = 0x7fffffff;
/* 317*/        for(j1 = i + 1; j1 < j; j1 += 2)
                {
                    int l2;
/* 318*/            if((l2 = ai[j1]) < k)
/* 320*/                k = l2;
/* 322*/            if(l2 > j2)
/* 323*/                j2 = l2;
                }

/* 326*/        j1 = (k + j2) / 2;
/* 328*/        int i3 = 128;
/* 329*/        j = 0;
/* 330*/        for(int l = 0; l < 7; l++)
                {
/* 331*/            int k2 = (l & 1) != 0 ? j1 : l1;
/* 332*/            i3 >>= 1;
/* 333*/            if(ai[i + l] > k2)
/* 334*/                j |= i3;
                }

/* 338*/        for(int i1 = 0; i1 < CHARACTER_ENCODINGS.length; i1++)
/* 339*/            if(CHARACTER_ENCODINGS[i1] == j)
/* 340*/                return i1;

/* 343*/        return -1;
            }

            private static final int MAX_ACCEPTABLE = 512;
            private static final int PADDING = 384;
            private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
            static final char ALPHABET[] = "0123456789-$:/.+ABCD".toCharArray();
            static final int CHARACTER_ENCODINGS[] = {
/*  50*/        3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 
/*  50*/        12, 24, 69, 81, 84, 21, 26, 41, 11, 14
            };
            private static final int MIN_CHARACTER_LENGTH = 3;
            private static final char STARTEND_ENCODING[] = {
/*  61*/        'A', 'B', 'C', 'D'
            };
            private final StringBuilder decodeRowResult = new StringBuilder(20);
            private int counters[];
            private int counterLength;

}
