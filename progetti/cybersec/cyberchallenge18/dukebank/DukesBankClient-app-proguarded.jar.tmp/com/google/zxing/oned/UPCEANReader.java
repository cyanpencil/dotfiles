// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEANReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, EANManufacturerOrgSupport, UPCEANExtensionSupport

public abstract class UPCEANReader extends OneDReader
{

            protected UPCEANReader()
            {
            }

            static int[] findStartGuardPattern(BitArray bitarray)
                throws NotFoundException
            {
/* 105*/        boolean flag = false;
/* 106*/        int ai[] = null;
/* 107*/        int i = 0;
/* 108*/        int ai1[] = new int[START_END_PATTERN.length];
/* 109*/        do
                {
/* 109*/            if(flag)
/* 110*/                break;
/* 110*/            Arrays.fill(ai1, 0, START_END_PATTERN.length, 0);
/* 111*/            int j = (ai = findGuardPattern(bitarray, i, false, START_END_PATTERN, ai1))[0];
/* 113*/            i = ai[1];
                    int k;
/* 117*/            if((k = j - (i - j)) >= 0)
/* 119*/                flag = bitarray.isRange(k, j, false);
                } while(true);
/* 122*/        return ai;
            }

            public Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
/* 128*/        return decodeRow(i, bitarray, findStartGuardPattern(bitarray), map);
            }

            public Result decodeRow(int i, BitArray bitarray, int ai[], Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
                Object obj;
/* 142*/        if((obj = map != null ? ((Object) ((ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))) : null) != null)
/* 146*/            ((ResultPointCallback) (obj)).foundPossibleResultPoint(new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i));
                StringBuilder stringbuilder;
/* 151*/        (stringbuilder = decodeRowStringBuffer).setLength(0);
/* 153*/        int k = decodeMiddle(bitarray, ai, stringbuilder);
/* 155*/        if(obj != null)
/* 156*/            ((ResultPointCallback) (obj)).foundPossibleResultPoint(new ResultPoint(k, i));
/* 161*/        int ai1[] = decodeEnd(bitarray, k);
/* 163*/        if(obj != null)
/* 164*/            ((ResultPointCallback) (obj)).foundPossibleResultPoint(new ResultPoint((float)(ai1[0] + ai1[1]) / 2.0F, i));
                int j;
                int i1;
/* 172*/        if((i1 = (j = ai1[1]) + (j - ai1[0])) >= bitarray.getSize() || !bitarray.isRange(j, i1, false))
/* 175*/            throw NotFoundException.getNotFoundInstance();
/* 178*/        if((j = stringbuilder.toString()).length() < 8)
/* 181*/            throw FormatException.getFormatInstance();
/* 183*/        if(!checkChecksum(j))
/* 184*/            throw ChecksumException.getChecksumInstance();
/* 187*/        ai = (float)(ai[1] + ai[0]) / 2.0F;
/* 188*/        float f = (float)(ai1[1] + ai1[0]) / 2.0F;
/* 189*/        BarcodeFormat barcodeformat = getBarcodeFormat();
/* 190*/        ai = new Result(j, null, new ResultPoint[] {
/* 190*/            new ResultPoint(ai, i), new ResultPoint(f, i)
                }, barcodeformat);
/* 197*/        f = 0;
/* 200*/        try
                {
/* 200*/            i = extensionReader.decodeRow(i, bitarray, ai1[1]);
/* 201*/            ai.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, i.getText());
/* 202*/            ai.putAllMetadata(i.getResultMetadata());
/* 203*/            ai.addResultPoints(i.getResultPoints());
/* 204*/            f = i.getText().length();
                }
/* 205*/        catch(ReaderException _ex) { }
/* 209*/        if((i = map != null ? ((int) ((int[])map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS))) : null) != null)
                {
/* 212*/            bitarray = 0;
/* 213*/            map = (i = i).length;
/* 213*/            int l = 0;
/* 213*/            do
                    {
/* 213*/                if(l >= map)
/* 213*/                    break;
/* 213*/                int j1 = i[l];
/* 214*/                if(f == j1)
                        {
/* 215*/                    bitarray = 1;
/* 216*/                    break;
                        }
/* 213*/                l++;
                    } while(true);
/* 219*/            if(bitarray == 0)
/* 220*/                throw NotFoundException.getNotFoundInstance();
                }
/* 224*/        if((barcodeformat == BarcodeFormat.EAN_13 || barcodeformat == BarcodeFormat.UPC_A) && (bitarray = eanManSupport.lookupCountryIdentifier(j)) != null)
/* 227*/            ai.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, bitarray);
/* 231*/        return ai;
            }

            boolean checkChecksum(String s)
                throws ChecksumException, FormatException
            {
/* 238*/        return checkStandardUPCEANChecksum(s);
            }

            static boolean checkStandardUPCEANChecksum(CharSequence charsequence)
                throws FormatException
            {
                int i;
/* 250*/        if((i = charsequence.length()) == 0)
/* 252*/            return false;
/* 255*/        int j = 0;
/* 256*/        for(int k = i - 2; k >= 0; k -= 2)
                {
                    int i1;
/* 257*/            if((i1 = charsequence.charAt(k) - 48) < 0 || i1 > 9)
/* 259*/                throw FormatException.getFormatInstance();
/* 261*/            j += i1;
                }

/* 263*/        j *= 3;
/* 264*/        for(int l = i - 1; l >= 0; l -= 2)
                {
                    int j1;
/* 265*/            if((j1 = charsequence.charAt(l) - 48) < 0 || j1 > 9)
/* 267*/                throw FormatException.getFormatInstance();
/* 269*/            j += j1;
                }

/* 271*/        return j % 10 == 0;
            }

            int[] decodeEnd(BitArray bitarray, int i)
                throws NotFoundException
            {
/* 275*/        return findGuardPattern(bitarray, i, false, START_END_PATTERN);
            }

            static int[] findGuardPattern(BitArray bitarray, int i, boolean flag, int ai[])
                throws NotFoundException
            {
/* 282*/        return findGuardPattern(bitarray, i, flag, ai, new int[ai.length]);
            }

            private static int[] findGuardPattern(BitArray bitarray, int i, boolean flag, int ai[], int ai1[])
                throws NotFoundException
            {
/* 301*/        int j = ai.length;
/* 302*/        int k = bitarray.getSize();
/* 303*/        boolean flag1 = flag;
/* 304*/        i = flag ? bitarray.getNextUnset(i) : bitarray.getNextSet(i);
/* 305*/        flag = false;
/* 306*/        int l = i;
/* 307*/        for(i = i; i < k; i++)
                {
/* 308*/            if(bitarray.get(i) ^ flag1)
                    {
/* 309*/                ai1[flag]++;
/* 309*/                continue;
                    }
/* 311*/            if(flag == j - 1)
                    {
/* 312*/                if(patternMatchVariance(ai1, ai, 179) < 122)
/* 313*/                    return (new int[] {
/* 313*/                        l, i
                            });
/* 315*/                l += ai1[0] + ai1[1];
/* 316*/                System.arraycopy(ai1, 2, ai1, 0, j - 2);
/* 317*/                ai1[j - 2] = 0;
/* 318*/                ai1[j - 1] = 0;
/* 319*/                flag--;
                    } else
                    {
/* 321*/                flag++;
                    }
/* 323*/            ai1[flag] = 1;
/* 324*/            flag1 = !flag1;
                }

/* 327*/        throw NotFoundException.getNotFoundInstance();
            }

            static int decodeDigit(BitArray bitarray, int ai[], int i, int ai1[][])
                throws NotFoundException
            {
/* 344*/        recordPattern(bitarray, i, ai);
/* 345*/        bitarray = 122;
/* 346*/        i = -1;
/* 347*/        int j = ai1.length;
/* 348*/        for(int k = 0; k < j; k++)
                {
/* 349*/            int ai2[] = ai1[k];
                    int l;
/* 350*/            if((l = patternMatchVariance(ai, ai2, 179)) < bitarray)
                    {
/* 352*/                bitarray = l;
/* 353*/                i = k;
                    }
                }

/* 356*/        if(i >= 0)
/* 357*/            return i;
/* 359*/        else
/* 359*/            throw NotFoundException.getNotFoundInstance();
            }

            abstract BarcodeFormat getBarcodeFormat();

            protected abstract int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException;

            private static final int MAX_AVG_VARIANCE = 122;
            private static final int MAX_INDIVIDUAL_VARIANCE = 179;
            static final int START_END_PATTERN[] = {
/*  53*/        1, 1, 1
            };
            static final int MIDDLE_PATTERN[] = {
/*  58*/        1, 1, 1, 1, 1
            };
            static final int L_PATTERNS[][] = {
/*  63*/        {
/*  63*/            3, 2, 1, 1
                }, {
/*  63*/            2, 2, 2, 1
                }, {
/*  63*/            2, 1, 2, 2
                }, {
/*  63*/            1, 4, 1, 1
                }, {
/*  63*/            1, 1, 3, 2
                }, {
/*  63*/            1, 2, 3, 1
                }, {
/*  63*/            1, 1, 1, 4
                }, {
/*  63*/            1, 3, 1, 2
                }, {
/*  63*/            1, 2, 1, 3
                }, {
/*  63*/            3, 1, 1, 2
                }
            };
            static final int L_AND_G_PATTERNS[][];
            private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
            private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();
            private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();

            static 
            {
/*  82*/        L_AND_G_PATTERNS = new int[20][];
/*  83*/        System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
/*  84*/        for(int i = 10; i < 20; i++)
                {
                    int ai[];
/*  85*/            int ai1[] = new int[(ai = L_PATTERNS[i - 10]).length];
/*  87*/            for(int j = 0; j < ai.length; j++)
/*  88*/                ai1[j] = ai[ai.length - j - 1];

/*  90*/            L_AND_G_PATTERNS[i] = ai1;
                }

            }
}
