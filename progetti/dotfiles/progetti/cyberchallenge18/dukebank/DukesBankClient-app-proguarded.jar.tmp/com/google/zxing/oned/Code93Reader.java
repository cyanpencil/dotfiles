// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code93Reader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code93Reader extends OneDReader
{

            public Code93Reader()
            {
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  69*/        map = findAsteriskPattern(bitarray);
/*  71*/        int j = bitarray.getNextSet(map[1]);
/*  72*/        int k = bitarray.getSize();
                int ai[];
/*  74*/        Arrays.fill(ai = counters, 0);
                StringBuilder stringbuilder;
/*  76*/        (stringbuilder = decodeRowResult).setLength(0);
                int l;
                int i1;
/*  82*/        do
                {
/*  82*/            recordPattern(bitarray, j, ai);
/*  83*/            if((l = toPattern(ai)) < 0)
/*  85*/                throw NotFoundException.getNotFoundInstance();
/*  87*/            l = patternToChar(l);
/*  88*/            stringbuilder.append(l);
/*  89*/            i1 = j;
                    int ai1[];
/*  90*/            int j1 = (ai1 = ai).length;
/*  90*/            for(int l1 = 0; l1 < j1; l1++)
                    {
/*  90*/                int j2 = ai1[l1];
/*  91*/                j += j2;
                    }

/*  94*/            j = bitarray.getNextSet(j);
                } while(l != '*');
/*  96*/        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
/*  98*/        l = 0;
                int ai2[];
/*  99*/        int k1 = (ai2 = ai).length;
/*  99*/        for(int i2 = 0; i2 < k1; i2++)
                {
/*  99*/            int k2 = ai2[i2];
/* 100*/            l += k2;
                }

/* 104*/        if(j == k || !bitarray.get(j))
/* 105*/            throw NotFoundException.getNotFoundInstance();
/* 108*/        if(stringbuilder.length() < 2)
                {
/* 110*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 113*/            checkChecksums(stringbuilder);
/* 115*/            stringbuilder.setLength(stringbuilder.length() - 2);
/* 117*/            String s = decodeExtended(stringbuilder);
/* 119*/            float f = (float)(map[1] + map[0]) / 2.0F;
/* 120*/            float f1 = (float)i1 + (float)l / 2.0F;
/* 121*/            return new Result(s, null, new ResultPoint[] {
/* 121*/                new ResultPoint(f, i), new ResultPoint(f1, i)
                    }, BarcodeFormat.CODE_93);
                }
            }

            private int[] findAsteriskPattern(BitArray bitarray)
                throws NotFoundException
            {
/* 132*/        int i = bitarray.getSize();
/* 133*/        int j = bitarray.getNextSet(0);
/* 135*/        Arrays.fill(counters, 0);
/* 136*/        int ai[] = counters;
/* 137*/        int k = j;
/* 138*/        boolean flag = false;
/* 139*/        int l = ai.length;
/* 141*/        int i1 = 0;
/* 142*/        for(j = j; j < i; j++)
                {
/* 143*/            if(bitarray.get(j) ^ flag)
                    {
/* 144*/                ai[i1]++;
/* 144*/                continue;
                    }
/* 146*/            if(i1 == l - 1)
                    {
/* 147*/                if(toPattern(ai) == ASTERISK_ENCODING)
/* 148*/                    return (new int[] {
/* 148*/                        k, j
                            });
/* 150*/                k += ai[0] + ai[1];
/* 151*/                System.arraycopy(ai, 2, ai, 0, l - 2);
/* 152*/                ai[l - 2] = 0;
/* 153*/                ai[l - 1] = 0;
/* 154*/                i1--;
                    } else
                    {
/* 156*/                i1++;
                    }
/* 158*/            ai[i1] = 1;
/* 159*/            flag = !flag;
                }

/* 162*/        throw NotFoundException.getNotFoundInstance();
            }

            private static int toPattern(int ai[])
            {
/* 166*/        int i = ai.length;
/* 167*/        int j = 0;
                int ai1[];
/* 168*/        int l = (ai1 = ai).length;
/* 168*/        for(int j1 = 0; j1 < l; j1++)
                {
/* 168*/            int i2 = ai1[j1];
/* 169*/            j += i2;
                }

/* 171*/        int k = 0;
/* 172*/        for(int i1 = 0; i1 < i; i1++)
                {
                    int k1;
/* 173*/            int j2 = (k1 = ((ai[i1] << 8) * 9) / j) >> 8;
/* 175*/            if((k1 & 0xff) > 127)
/* 176*/                j2++;
/* 178*/            if(j2 <= 0 || j2 > 4)
/* 179*/                return -1;
/* 181*/            if((i1 & 1) == 0)
                    {
/* 182*/                for(int l1 = 0; l1 < j2; l1++)
/* 183*/                    k = k << 1 | 1;

                    } else
                    {
/* 186*/                k <<= j2;
                    }
                }

/* 189*/        return k;
            }

            private static char patternToChar(int i)
                throws NotFoundException
            {
/* 193*/        for(int j = 0; j < CHARACTER_ENCODINGS.length; j++)
/* 194*/            if(CHARACTER_ENCODINGS[j] == i)
/* 195*/                return ALPHABET[j];

/* 198*/        throw NotFoundException.getNotFoundInstance();
            }

            private static String decodeExtended(CharSequence charsequence)
                throws FormatException
            {
/* 202*/        int i = charsequence.length();
/* 203*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 204*/        for(int j = 0; j < i; j++)
                {
                    char c;
/* 205*/            if((c = charsequence.charAt(j)) >= 'a' && c <= 'd')
                    {
/* 207*/                if(j >= i - 1)
/* 208*/                    throw FormatException.getFormatInstance();
/* 210*/                char c1 = charsequence.charAt(j + 1);
/* 211*/                char c2 = '\0';
/* 212*/                switch(c)
                        {
/* 202*/                default:
                            break;

/* 215*/                case 100: // 'd'
/* 215*/                    if(c1 >= 'A' && c1 <= 'Z')
/* 216*/                        c2 = (char)(c1 + 32);
/* 218*/                    else
/* 218*/                        throw FormatException.getFormatInstance();
/* 223*/                    break;

/* 223*/                case 97: // 'a'
/* 223*/                    if(c1 >= 'A' && c1 <= 'Z')
/* 224*/                        c2 = (char)(c1 - 64);
/* 226*/                    else
/* 226*/                        throw FormatException.getFormatInstance();
/* 231*/                    break;

/* 231*/                case 98: // 'b'
/* 231*/                    if(c1 >= 'A' && c1 <= 'E')
                            {
/* 232*/                        c2 = (char)(c1 - 38);
/* 232*/                        break;
                            }
/* 233*/                    if(c1 >= 'F' && c1 <= 'W')
/* 234*/                        c2 = (char)(c1 - 11);
/* 236*/                    else
/* 236*/                        throw FormatException.getFormatInstance();
/* 241*/                    break;

/* 241*/                case 99: // 'c'
/* 241*/                    if(c1 >= 'A' && c1 <= 'O')
                            {
/* 242*/                        c2 = (char)(c1 - 32);
/* 242*/                        break;
                            }
/* 243*/                    if(c1 == 'Z')
/* 244*/                        c2 = ':';
/* 246*/                    else
/* 246*/                        throw FormatException.getFormatInstance();
                            break;
                        }
/* 250*/                stringbuilder.append(c2);
/* 252*/                j++;
                    } else
                    {
/* 254*/                stringbuilder.append(c);
                    }
                }

/* 257*/        return stringbuilder.toString();
            }

            private static void checkChecksums(CharSequence charsequence)
                throws ChecksumException
            {
/* 261*/        int i = charsequence.length();
/* 262*/        checkOneChecksum(charsequence, i - 2, 20);
/* 263*/        checkOneChecksum(charsequence, i - 1, 15);
            }

            private static void checkOneChecksum(CharSequence charsequence, int i, int j)
                throws ChecksumException
            {
/* 268*/        int k = 1;
/* 269*/        int l = 0;
/* 270*/        for(int i1 = i - 1; i1 >= 0; i1--)
                {
/* 271*/            l += k * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charsequence.charAt(i1));
/* 272*/            if(++k > j)
/* 273*/                k = 1;
                }

/* 276*/        if(charsequence.charAt(i) != ALPHABET[l % 47])
/* 277*/            throw ChecksumException.getChecksumInstance();
/* 279*/        else
/* 279*/            return;
            }

            private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
            private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
            private static final int CHARACTER_ENCODINGS[];
            private static final int ASTERISK_ENCODING;
            private final StringBuilder decodeRowResult = new StringBuilder(20);
            private final int counters[] = new int[6];

            static 
            {
/*  47*/        ASTERISK_ENCODING = (CHARACTER_ENCODINGS = (new int[] {
/*  47*/            276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 
/*  47*/            424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 
/*  47*/            282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 
/*  47*/            406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 
/*  47*/            366, 374, 430, 294, 474, 470, 306, 350
                }))[47];
            }
}
