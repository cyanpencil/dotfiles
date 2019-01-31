// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class UPCEReader extends UPCEANReader
{

            public UPCEReader()
            {
            }

            protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
                int ai1[];
/*  60*/        (ai1 = decodeMiddleCounters)[0] = 0;
/*  62*/        ai1[1] = 0;
/*  63*/        ai1[2] = 0;
/*  64*/        ai1[3] = 0;
/*  65*/        int i = bitarray.getSize();
/*  66*/        ai = ai[1];
/*  68*/        int j = 0;
/*  70*/        for(int k = 0; k < 6 && ai < i; k++)
                {
/*  71*/            int l = decodeDigit(bitarray, ai1, ai, L_AND_G_PATTERNS);
/*  72*/            stringbuilder.append((char)(48 + l % 10));
                    int ai2[];
/*  73*/            int i1 = (ai2 = ai1).length;
/*  73*/            for(int j1 = 0; j1 < i1; j1++)
                    {
/*  73*/                int k1 = ai2[j1];
/*  74*/                ai += k1;
                    }

/*  76*/            if(l >= 10)
/*  77*/                j |= 1 << 5 - k;
                }

/*  81*/        determineNumSysAndCheckDigit(stringbuilder, j);
/*  83*/        return ai;
            }

            protected final int[] decodeEnd(BitArray bitarray, int i)
                throws NotFoundException
            {
/*  88*/        return findGuardPattern(bitarray, i, true, MIDDLE_END_PATTERN);
            }

            protected final boolean checkChecksum(String s)
                throws FormatException, ChecksumException
            {
/*  93*/        return super.checkChecksum(convertUPCEtoUPCA(s));
            }

            private static void determineNumSysAndCheckDigit(StringBuilder stringbuilder, int i)
                throws NotFoundException
            {
/*  99*/        for(int j = 0; j <= 1; j++)
                {
/* 100*/            for(int k = 0; k < 10; k++)
/* 101*/                if(i == NUMSYS_AND_CHECK_DIGIT_PATTERNS[j][k])
                        {
/* 102*/                    stringbuilder.insert(0, (char)(j + 48));
/* 103*/                    stringbuilder.append((char)(k + 48));
/* 104*/                    return;
                        }

                }

/* 108*/        throw NotFoundException.getNotFoundInstance();
            }

            final BarcodeFormat getBarcodeFormat()
            {
/* 113*/        return BarcodeFormat.UPC_E;
            }

            public static String convertUPCEtoUPCA(String s)
            {
/* 123*/        char ac[] = new char[6];
/* 124*/        s.getChars(1, 7, ac, 0);
                StringBuilder stringbuilder;
/* 125*/        (stringbuilder = new StringBuilder(12)).append(s.charAt(0));
                char c;
/* 127*/        switch(c = ac[5])
                {
/* 132*/        case 48: // '0'
/* 132*/        case 49: // '1'
/* 132*/        case 50: // '2'
/* 132*/            stringbuilder.append(ac, 0, 2);
/* 133*/            stringbuilder.append(c);
/* 134*/            stringbuilder.append("0000");
/* 135*/            stringbuilder.append(ac, 2, 3);
                    break;

/* 138*/        case 51: // '3'
/* 138*/            stringbuilder.append(ac, 0, 3);
/* 139*/            stringbuilder.append("00000");
/* 140*/            stringbuilder.append(ac, 3, 2);
                    break;

/* 143*/        case 52: // '4'
/* 143*/            stringbuilder.append(ac, 0, 4);
/* 144*/            stringbuilder.append("00000");
/* 145*/            stringbuilder.append(ac[4]);
                    break;

/* 148*/        default:
/* 148*/            stringbuilder.append(ac, 0, 5);
/* 149*/            stringbuilder.append("0000");
/* 150*/            stringbuilder.append(c);
                    break;
                }
/* 153*/        stringbuilder.append(s.charAt(7));
/* 154*/        return stringbuilder.toString();
            }

            private static final int MIDDLE_END_PATTERN[] = {
/*  39*/        1, 1, 1, 1, 1, 1
            };
            private static final int NUMSYS_AND_CHECK_DIGIT_PATTERNS[][] = {
/*  46*/        {
/*  46*/            56, 52, 50, 49, 44, 38, 35, 42, 41, 37
                }, {
/*  46*/            7, 11, 13, 14, 19, 25, 28, 21, 22, 26
                }
            };
            private final int decodeMiddleCounters[] = new int[4];

}
