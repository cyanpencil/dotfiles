// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EAN8Reader.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN8Reader extends UPCEANReader
{

            public EAN8Reader()
            {
            }

            protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
                int ai1[];
/*  40*/        (ai1 = decodeMiddleCounters)[0] = 0;
/*  42*/        ai1[1] = 0;
/*  43*/        ai1[2] = 0;
/*  44*/        ai1[3] = 0;
/*  45*/        int i = bitarray.getSize();
/*  46*/        ai = ai[1];
/*  48*/        for(int j = 0; j < 4 && ai < i; j++)
                {
/*  49*/            int l = decodeDigit(bitarray, ai1, ai, L_PATTERNS);
/*  50*/            stringbuilder.append((char)(l + 48));
                    int ai3[];
/*  51*/            int k1 = (ai3 = ai1).length;
/*  51*/            for(int l1 = 0; l1 < k1; l1++)
                    {
/*  51*/                int j2 = ai3[l1];
/*  52*/                ai += j2;
                    }

                }

                int ai2[];
/*  56*/        ai = (ai2 = findGuardPattern(bitarray, ai, true, MIDDLE_PATTERN))[1];
/*  59*/        for(int i1 = 0; i1 < 4 && ai < i; i1++)
                {
/*  60*/            int j1 = decodeDigit(bitarray, ai1, ai, L_PATTERNS);
/*  61*/            stringbuilder.append((char)(j1 + 48));
                    int ai4[];
/*  62*/            int i2 = (ai4 = ai1).length;
/*  62*/            for(int k2 = 0; k2 < i2; k2++)
                    {
/*  62*/                int k = ai4[k2];
/*  63*/                ai += k;
                    }

                }

/*  67*/        return ai;
            }

            final BarcodeFormat getBarcodeFormat()
            {
/*  72*/        return BarcodeFormat.EAN_8;
            }

            private final int decodeMiddleCounters[] = new int[4];
}
