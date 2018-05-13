// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorCorrection.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolInfo

public final class ErrorCorrection
{

            private ErrorCorrection()
            {
            }

            public static String encodeECC200(String s, SymbolInfo symbolinfo)
            {
/* 102*/        if(s.length() != symbolinfo.getDataCapacity())
/* 103*/            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
                StringBuilder stringbuilder;
/* 106*/        (stringbuilder = new StringBuilder(symbolinfo.getDataCapacity() + symbolinfo.getErrorCodewords())).append(s);
                int i;
/* 108*/        if((i = symbolinfo.getInterleavedBlockCount()) == 1)
                {
/* 110*/            String s1 = createECCBlock(s, symbolinfo.getErrorCodewords());
/* 111*/            stringbuilder.append(s1);
                } else
                {
/* 113*/            stringbuilder.setLength(stringbuilder.capacity());
/* 114*/            int ai[] = new int[i];
/* 115*/            int ai1[] = new int[i];
/* 116*/            int ai2[] = new int[i];
/* 117*/            for(int k = 0; k < i; k++)
                    {
/* 118*/                ai[k] = symbolinfo.getDataLengthForInterleavedBlock(k + 1);
/* 119*/                ai1[k] = symbolinfo.getErrorLengthForInterleavedBlock(k + 1);
/* 120*/                ai2[k] = 0;
/* 121*/                if(k > 0)
/* 122*/                    ai2[k] = ai2[k - 1] + ai[k];
                    }

/* 125*/            for(int l = 0; l < i; l++)
                    {
/* 126*/                StringBuilder stringbuilder1 = new StringBuilder(ai[l]);
/* 127*/                for(int i1 = l; i1 < symbolinfo.getDataCapacity(); i1 += i)
/* 128*/                    stringbuilder1.append(s.charAt(i1));

/* 130*/                String s2 = createECCBlock(stringbuilder1.toString(), ai1[l]);
/* 131*/                int j = 0;
/* 132*/                for(int j1 = l; j1 < ai1[l] * i; j1 += i)
/* 133*/                    stringbuilder.setCharAt(symbolinfo.getDataCapacity() + j1, s2.charAt(j++));

                    }

                }
/* 137*/        return stringbuilder.toString();
            }

            private static String createECCBlock(CharSequence charsequence, int i)
            {
/* 142*/        return createECCBlock(charsequence, 0, charsequence.length(), i);
            }

            private static String createECCBlock(CharSequence charsequence, int i, int j, int k)
            {
/* 146*/        int l = -1;
/* 147*/        int i1 = 0;
/* 147*/        do
                {
/* 147*/            if(i1 >= FACTOR_SETS.length)
/* 148*/                break;
/* 148*/            if(FACTOR_SETS[i1] == k)
                    {
/* 149*/                l = i1;
/* 150*/                break;
                    }
/* 147*/            i1++;
                } while(true);
/* 153*/        if(l < 0)
/* 154*/            throw new IllegalArgumentException((new StringBuilder("Illegal number of error correction codewords specified: ")).append(k).toString());
/* 157*/        int ai[] = FACTORS[l];
/* 158*/        char ac[] = new char[k];
/* 159*/        for(int j1 = 0; j1 < k; j1++)
/* 160*/            ac[j1] = '\0';

/* 162*/        for(int k1 = i; k1 < i + j; k1++)
                {
/* 163*/            int l1 = ac[k - 1] ^ charsequence.charAt(k1);
/* 164*/            for(int j2 = k - 1; j2 > 0; j2--)
/* 165*/                if(l1 != 0 && ai[j2] != 0)
/* 166*/                    ac[j2] = (char)(ac[j2 - 1] ^ ALOG[(LOG[l1] + LOG[ai[j2]]) % 255]);
/* 168*/                else
/* 168*/                    ac[j2] = ac[j2 - 1];

/* 171*/            if(l1 != 0 && ai[0] != 0)
/* 172*/                ac[0] = (char)ALOG[(LOG[l1] + LOG[ai[0]]) % 255];
/* 174*/            else
/* 174*/                ac[0] = '\0';
                }

/* 177*/        char ac1[] = new char[k];
/* 178*/        for(int i2 = 0; i2 < k; i2++)
/* 179*/            ac1[i2] = ac[k - i2 - 1];

/* 181*/        return String.valueOf(ac1);
            }

            private static final int FACTOR_SETS[] = {
/*  28*/        5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 
/*  28*/        36, 42, 48, 56, 62, 68
            };
            private static final int FACTORS[][] = {
/*  34*/        {
/*  34*/            228, 48, 15, 111, 62
                }, {
/*  34*/            23, 68, 144, 134, 240, 92, 254
                }, {
/*  34*/            28, 24, 185, 166, 223, 248, 116, 255, 110, 61
                }, {
/*  34*/            175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 
/*  34*/            120
                }, {
/*  34*/            41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 
/*  34*/            100, 242
                }, {
/*  34*/            156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 
/*  34*/            18, 186, 83, 185
                }, {
/*  34*/            83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 
/*  34*/            109, 129, 94, 254, 225, 48, 90, 188
                }, {
/*  34*/            15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 
/*  34*/            153, 145, 253, 79, 108, 82, 27, 174, 186, 172
                }, {
/*  34*/            52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 
/*  34*/            251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 
/*  34*/            184, 96, 50, 193
                }, {
/*  34*/            211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 
/*  34*/            170, 53, 75, 34, 249, 121, 17, 138, 110, 213, 
/*  34*/            141, 136, 120, 151, 233, 168, 93, 255
                }, {
/*  34*/            245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 
/*  34*/            84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 
/*  34*/            68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 
/*  34*/            59, 25, 225, 98, 81, 112
                }, {
/*  34*/            77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 
/*  34*/            122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 
/*  34*/            167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 
/*  34*/            54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 
/*  34*/            9, 5
                }, {
/*  34*/            245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 
/*  34*/            238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 
/*  34*/            118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 
/*  34*/            66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 
/*  34*/            160, 175, 69, 213, 92, 253, 225, 19
                }, {
/*  34*/            175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 
/*  34*/            175, 170, 230, 192, 215, 235, 150, 159, 36, 223, 
/*  34*/            38, 200, 132, 54, 228, 146, 218, 234, 117, 203, 
/*  34*/            29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 
/*  34*/            164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 
/*  34*/            203, 107, 233, 53, 143, 46
                }, {
/*  34*/            242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 
/*  34*/            201, 189, 143, 108, 196, 37, 185, 112, 134, 230, 
/*  34*/            245, 63, 197, 190, 250, 106, 185, 221, 175, 64, 
/*  34*/            114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 
/*  34*/            87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 
/*  34*/            4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 
/*  34*/            11, 204
                }, {
/*  34*/            220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 
/*  34*/            147, 244, 154, 36, 73, 127, 213, 136, 248, 180, 
/*  34*/            234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 
/*  34*/            227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 
/*  34*/            220, 232, 96, 210, 231, 136, 223, 239, 181, 241, 
/*  34*/            59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 
/*  34*/            108, 153, 132, 63, 96, 103, 82, 186
                }
            };
            private static final int MODULO_VALUE = 301;
            private static final int LOG[];
            private static final int ALOG[];

            static 
            {
/*  77*/        LOG = new int[256];
/*  78*/        ALOG = new int[255];
/*  80*/        int i = 1;
/*  81*/        for(int j = 0; j < 255; j++)
                {
/*  82*/            ALOG[j] = i;
/*  83*/            LOG[i] = j;
/*  84*/            if((i <<= 1) >= 256)
/*  86*/                i ^= 0x12d;
                }

            }
}
