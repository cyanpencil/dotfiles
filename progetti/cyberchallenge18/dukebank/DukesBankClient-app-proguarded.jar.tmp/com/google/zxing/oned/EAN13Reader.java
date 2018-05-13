// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EAN13Reader.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN13Reader extends UPCEANReader
{

            public EAN13Reader()
            {
            }

            protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
                int ai1[];
/*  75*/        (ai1 = decodeMiddleCounters)[0] = 0;
/*  77*/        ai1[1] = 0;
/*  78*/        ai1[2] = 0;
/*  79*/        ai1[3] = 0;
/*  80*/        int i = bitarray.getSize();
/*  81*/        ai = ai[1];
/*  83*/        int j = 0;
/*  85*/        for(int l = 0; l < 6 && ai < i; l++)
                {
/*  86*/            int i1 = decodeDigit(bitarray, ai1, ai, L_AND_G_PATTERNS);
/*  87*/            stringbuilder.append((char)(48 + i1 % 10));
                    int ai3[];
/*  88*/            int l1 = (ai3 = ai1).length;
/*  88*/            for(int i2 = 0; i2 < l1; i2++)
                    {
/*  88*/                int k2 = ai3[i2];
/*  89*/                ai += k2;
                    }

/*  91*/            if(i1 >= 10)
/*  92*/                j |= 1 << 5 - l;
                }

/*  96*/        determineFirstDigit(stringbuilder, j);
                int ai2[];
/*  98*/        ai = (ai2 = findGuardPattern(bitarray, ai, true, MIDDLE_PATTERN))[1];
/* 101*/        for(int j1 = 0; j1 < 6 && ai < i; j1++)
                {
/* 102*/            int k1 = decodeDigit(bitarray, ai1, ai, L_PATTERNS);
/* 103*/            stringbuilder.append((char)(k1 + 48));
                    int ai4[];
/* 104*/            int j2 = (ai4 = ai1).length;
/* 104*/            for(int l2 = 0; l2 < j2; l2++)
                    {
/* 104*/                int k = ai4[l2];
/* 105*/                ai += k;
                    }

                }

/* 109*/        return ai;
            }

            final BarcodeFormat getBarcodeFormat()
            {
/* 114*/        return BarcodeFormat.EAN_13;
            }

            private static void determineFirstDigit(StringBuilder stringbuilder, int i)
                throws NotFoundException
            {
/* 129*/        for(int j = 0; j < 10; j++)
/* 130*/            if(i == FIRST_DIGIT_ENCODINGS[j])
                    {
/* 131*/                stringbuilder.insert(0, (char)(j + 48));
/* 132*/                return;
                    }

/* 135*/        throw NotFoundException.getNotFoundInstance();
            }

            static final int FIRST_DIGIT_ENCODINGS[] = {
/*  61*/        0, 11, 13, 14, 19, 25, 28, 21, 22, 26
            };
            private final int decodeMiddleCounters[] = new int[4];

}
