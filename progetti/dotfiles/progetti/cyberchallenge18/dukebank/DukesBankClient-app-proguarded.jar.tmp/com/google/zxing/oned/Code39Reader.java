// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code39Reader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code39Reader extends OneDReader
{

            public Code39Reader()
            {
/*  67*/        this(false);
            }

            public Code39Reader(boolean flag)
            {
/*  78*/        this(flag, false);
            }

            public Code39Reader(boolean flag, boolean flag1)
            {
/*  92*/        usingCheckDigit = flag;
/*  93*/        extendedMode = flag1;
/*  94*/        decodeRowResult = new StringBuilder(20);
/*  95*/        counters = new int[9];
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/* 102*/        Arrays.fill(map = counters, 0);
                StringBuilder stringbuilder;
/* 104*/        (stringbuilder = decodeRowResult).setLength(0);
/* 107*/        int ai[] = findAsteriskPattern(bitarray, map);
/* 109*/        int j = bitarray.getNextSet(ai[1]);
/* 110*/        int k = bitarray.getSize();
                int l;
                int i1;
/* 115*/        do
                {
/* 115*/            recordPattern(bitarray, j, map);
/* 116*/            if((l = toNarrowWidePattern(map)) < 0)
/* 118*/                throw NotFoundException.getNotFoundInstance();
/* 120*/            l = patternToChar(l);
/* 121*/            stringbuilder.append(l);
/* 122*/            i1 = j;
                    Map map1;
/* 123*/            int k1 = (map1 = map).length;
/* 123*/            for(int j2 = 0; j2 < k1; j2++)
                    {
/* 123*/                int i3 = map1[j2];
/* 124*/                j += i3;
                    }

/* 127*/            j = bitarray.getNextSet(j);
                } while(l != '*');
/* 129*/        stringbuilder.setLength(stringbuilder.length() - 1);
/* 132*/        l = 0;
                Map map2;
/* 133*/        int l1 = (map2 = map).length;
/* 133*/        for(int k2 = 0; k2 < l1; k2++)
                {
/* 133*/            int j3 = map2[k2];
/* 134*/            l += j3;
                }

/* 136*/        int j1 = j - i1 - l;
/* 139*/        if(j != k && j1 >> 1 < l)
/* 140*/            throw NotFoundException.getNotFoundInstance();
/* 143*/        if(usingCheckDigit)
                {
/* 144*/            int i2 = stringbuilder.length() - 1;
/* 145*/            int l2 = 0;
/* 146*/            for(int k3 = 0; k3 < i2; k3++)
/* 147*/                l2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(decodeRowResult.charAt(k3));

/* 149*/            if(stringbuilder.charAt(i2) != ALPHABET[l2 % 43])
/* 150*/                throw ChecksumException.getChecksumInstance();
/* 152*/            stringbuilder.setLength(i2);
                }
/* 155*/        if(stringbuilder.length() == 0)
/* 157*/            throw NotFoundException.getNotFoundInstance();
                String s;
/* 161*/        if(extendedMode)
/* 162*/            s = decodeExtended(stringbuilder);
/* 164*/        else
/* 164*/            s = stringbuilder.toString();
/* 167*/        float f = (float)(ai[1] + ai[0]) / 2.0F;
/* 168*/        float f1 = (float)i1 + (float)l / 2.0F;
/* 169*/        return new Result(s, null, new ResultPoint[] {
/* 169*/            new ResultPoint(f, i), new ResultPoint(f1, i)
                }, BarcodeFormat.CODE_39);
            }

            private static int[] findAsteriskPattern(BitArray bitarray, int ai[])
                throws NotFoundException
            {
/* 180*/        int i = bitarray.getSize();
/* 181*/        int j = bitarray.getNextSet(0);
/* 183*/        int k = 0;
/* 184*/        int l = j;
/* 185*/        boolean flag = false;
/* 186*/        int i1 = ai.length;
/* 188*/        for(j = j; j < i; j++)
                {
/* 189*/            if(bitarray.get(j) ^ flag)
                    {
/* 190*/                ai[k]++;
/* 190*/                continue;
                    }
/* 192*/            if(k == i1 - 1)
                    {
/* 194*/                if(toNarrowWidePattern(ai) == ASTERISK_ENCODING && bitarray.isRange(Math.max(0, l - (j - l >> 1)), l, false))
/* 196*/                    return (new int[] {
/* 196*/                        l, j
                            });
/* 198*/                l += ai[0] + ai[1];
/* 199*/                System.arraycopy(ai, 2, ai, 0, i1 - 2);
/* 200*/                ai[i1 - 2] = 0;
/* 201*/                ai[i1 - 1] = 0;
/* 202*/                k--;
                    } else
                    {
/* 204*/                k++;
                    }
/* 206*/            ai[k] = 1;
/* 207*/            flag = !flag;
                }

/* 210*/        throw NotFoundException.getNotFoundInstance();
            }

            private static int toNarrowWidePattern(int ai[])
            {
/* 216*/        int i = ai.length;
/* 217*/        int j = 0;
                int k;
/* 220*/        do
                {
/* 220*/            k = 0x7fffffff;
                    int ai1[];
/* 221*/            int i1 = (ai1 = ai).length;
/* 221*/            for(int j1 = 0; j1 < i1; j1++)
                    {
                        int i2;
/* 221*/                if((i2 = ai1[j1]) < k && i2 > j)
/* 223*/                    k = i2;
                    }

/* 226*/            j = k;
/* 227*/            k = 0;
/* 228*/            int l = 0;
/* 229*/            i1 = 0;
/* 230*/            for(int k1 = 0; k1 < i; k1++)
                    {
                        int j2;
/* 231*/                if((j2 = ai[k1]) > j)
                        {
/* 233*/                    i1 |= 1 << i - 1 - k1;
/* 234*/                    k++;
/* 235*/                    l += j2;
                        }
                    }

/* 238*/            if(k == 3)
                    {
/* 242*/                for(int l1 = 0; l1 < i && k > 0; l1++)
                        {
                            int k2;
/* 243*/                    if((k2 = ai[l1]) <= j)
/* 245*/                        continue;
/* 245*/                    k--;
/* 247*/                    if(k2 << 1 >= l)
/* 248*/                        return -1;
                        }

/* 252*/                return i1;
                    }
                } while(k > 3);
/* 255*/        return -1;
            }

            private static char patternToChar(int i)
                throws NotFoundException
            {
/* 259*/        for(int j = 0; j < CHARACTER_ENCODINGS.length; j++)
/* 260*/            if(CHARACTER_ENCODINGS[j] == i)
/* 261*/                return ALPHABET[j];

/* 264*/        throw NotFoundException.getNotFoundInstance();
            }

            private static String decodeExtended(CharSequence charsequence)
                throws FormatException
            {
/* 268*/        int i = charsequence.length();
/* 269*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 270*/        for(int j = 0; j < i; j++)
                {
                    char c;
/* 271*/            if((c = charsequence.charAt(j)) == '+' || c == '$' || c == '%' || c == '/')
                    {
/* 273*/                char c1 = charsequence.charAt(j + 1);
/* 274*/                char c2 = '\0';
/* 275*/                switch(c)
                        {
/* 268*/                default:
                            break;

/* 278*/                case 43: // '+'
/* 278*/                    if(c1 >= 'A' && c1 <= 'Z')
/* 279*/                        c2 = (char)(c1 + 32);
/* 281*/                    else
/* 281*/                        throw FormatException.getFormatInstance();
/* 286*/                    break;

/* 286*/                case 36: // '$'
/* 286*/                    if(c1 >= 'A' && c1 <= 'Z')
/* 287*/                        c2 = (char)(c1 - 64);
/* 289*/                    else
/* 289*/                        throw FormatException.getFormatInstance();
/* 294*/                    break;

/* 294*/                case 37: // '%'
/* 294*/                    if(c1 >= 'A' && c1 <= 'E')
                            {
/* 295*/                        c2 = (char)(c1 - 38);
/* 295*/                        break;
                            }
/* 296*/                    if(c1 >= 'F' && c1 <= 'W')
/* 297*/                        c2 = (char)(c1 - 11);
/* 299*/                    else
/* 299*/                        throw FormatException.getFormatInstance();
/* 304*/                    break;

/* 304*/                case 47: // '/'
/* 304*/                    if(c1 >= 'A' && c1 <= 'O')
                            {
/* 305*/                        c2 = (char)(c1 - 32);
/* 305*/                        break;
                            }
/* 306*/                    if(c1 == 'Z')
/* 307*/                        c2 = ':';
/* 309*/                    else
/* 309*/                        throw FormatException.getFormatInstance();
                            break;
                        }
/* 313*/                stringbuilder.append(c2);
/* 315*/                j++;
                    } else
                    {
/* 317*/                stringbuilder.append(c);
                    }
                }

/* 320*/        return stringbuilder.toString();
            }

            static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
            private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
            static final int CHARACTER_ENCODINGS[];
            private static final int ASTERISK_ENCODING;
            private final boolean usingCheckDigit;
            private final boolean extendedMode;
            private final StringBuilder decodeRowResult;
            private final int counters[];

            static 
            {
/*  47*/        ASTERISK_ENCODING = (CHARACTER_ENCODINGS = (new int[] {
/*  47*/            52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 
/*  47*/            265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 
/*  47*/            259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 
/*  47*/            385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 
/*  47*/            168, 162, 138, 42
                }))[39];
            }
}
