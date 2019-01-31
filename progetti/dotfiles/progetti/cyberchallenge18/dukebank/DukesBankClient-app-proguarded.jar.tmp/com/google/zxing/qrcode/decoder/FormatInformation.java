// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FormatInformation.java

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            ErrorCorrectionLevel

final class FormatInformation
{

            private FormatInformation(int i)
            {
/*  80*/        errorCorrectionLevel = ErrorCorrectionLevel.forBits(i >> 3 & 3);
/*  82*/        dataMask = (byte)(i & 7);
            }

            static int numBitsDiffering(int i, int j)
            {
/*  86*/        i ^= j;
/*  88*/        return BITS_SET_IN_HALF_BYTE[i & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 4 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 8 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 12 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 16 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 20 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 24 & 0xf] + BITS_SET_IN_HALF_BYTE[i >>> 28 & 0xf];
            }

            static FormatInformation decodeFormatInformation(int i, int j)
            {
                FormatInformation formatinformation;
/* 106*/        if((formatinformation = doDecodeFormatInformation(i, j)) != null)
/* 108*/            return formatinformation;
/* 113*/        else
/* 113*/            return doDecodeFormatInformation(i ^ 0x5412, j ^ 0x5412);
            }

            private static FormatInformation doDecodeFormatInformation(int i, int j)
            {
/* 119*/        int k = 0x7fffffff;
/* 120*/        int l = 0;
                int ai[][];
/* 121*/        int i1 = (ai = FORMAT_INFO_DECODE_LOOKUP).length;
/* 121*/        for(int j1 = 0; j1 < i1; j1++)
                {
                    int ai1[];
                    int k1;
/* 121*/            if((k1 = (ai1 = ai[j1])[0]) == i || k1 == j)
/* 125*/                return new FormatInformation(ai1[1]);
                    int l1;
/* 127*/            if((l1 = numBitsDiffering(i, k1)) < k)
                    {
/* 129*/                l = ai1[1];
/* 130*/                k = l1;
                    }
/* 132*/            if(i != j && (l1 = numBitsDiffering(j, k1)) < k)
                    {
/* 136*/                l = ai1[1];
/* 137*/                k = l1;
                    }
                }

/* 143*/        if(k <= 3)
/* 144*/            return new FormatInformation(l);
/* 146*/        else
/* 146*/            return null;
            }

            final ErrorCorrectionLevel getErrorCorrectionLevel()
            {
/* 150*/        return errorCorrectionLevel;
            }

            final byte getDataMask()
            {
/* 154*/        return dataMask;
            }

            public final int hashCode()
            {
/* 159*/        return errorCorrectionLevel.ordinal() << 3 | dataMask;
            }

            public final boolean equals(Object obj)
            {
/* 164*/        if(!(obj instanceof FormatInformation))
/* 165*/            return false;
/* 167*/        obj = (FormatInformation)obj;
/* 168*/        return errorCorrectionLevel == ((FormatInformation) (obj)).errorCorrectionLevel && dataMask == ((FormatInformation) (obj)).dataMask;
            }

            private static final int FORMAT_INFO_MASK_QR = 21522;
            private static final int FORMAT_INFO_DECODE_LOOKUP[][] = {
/*  34*/        {
/*  34*/            21522, 0
                }, {
/*  34*/            20773, 1
                }, {
/*  34*/            24188, 2
                }, {
/*  34*/            23371, 3
                }, {
/*  34*/            17913, 4
                }, {
/*  34*/            16590, 5
                }, {
/*  34*/            20375, 6
                }, {
/*  34*/            19104, 7
                }, {
/*  34*/            30660, 8
                }, {
/*  34*/            29427, 9
                }, {
/*  34*/            32170, 10
                }, {
/*  34*/            30877, 11
                }, {
/*  34*/            26159, 12
                }, {
/*  34*/            25368, 13
                }, {
/*  34*/            27713, 14
                }, {
/*  34*/            26998, 15
                }, {
/*  34*/            5769, 16
                }, {
/*  34*/            5054, 17
                }, {
/*  34*/            7399, 18
                }, {
/*  34*/            6608, 19
                }, {
/*  34*/            1890, 20
                }, {
/*  34*/            597, 21
                }, {
/*  34*/            3340, 22
                }, {
/*  34*/            2107, 23
                }, {
/*  34*/            13663, 24
                }, {
/*  34*/            12392, 25
                }, {
/*  34*/            16177, 26
                }, {
/*  34*/            14854, 27
                }, {
/*  34*/            9396, 28
                }, {
/*  34*/            8579, 29
                }, {
/*  34*/            11994, 30
                }, {
/*  34*/            11245, 31
                }
            };
            private static final int BITS_SET_IN_HALF_BYTE[] = {
/*  72*/        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
/*  72*/        2, 3, 2, 3, 3, 4
            };
            private final ErrorCorrectionLevel errorCorrectionLevel;
            private final byte dataMask;

}
