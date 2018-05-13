// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataBlock.java

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            ErrorCorrectionLevel, Version

final class DataBlock
{

            private DataBlock(int i, byte abyte0[])
            {
/*  32*/        numDataCodewords = i;
/*  33*/        codewords = abyte0;
            }

            static DataBlock[] getDataBlocks(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel)
            {
/*  51*/        if(abyte0.length != version.getTotalCodewords())
/*  52*/            throw new IllegalArgumentException();
/*  57*/        version = version.getECBlocksForLevel(errorcorrectionlevel);
/*  60*/        errorcorrectionlevel = 0;
                Version.ECB aecb[];
                Object aobj[];
/*  61*/        int j = (aobj = aecb = version.getECBlocks()).length;
/*  62*/        for(int k = 0; k < j; k++)
                {
/*  62*/            Version.ECB ecb1 = aobj[k];
/*  63*/            errorcorrectionlevel += ecb1.getCount();
                }

/*  67*/        aobj = new DataBlock[errorcorrectionlevel];
/*  68*/        j = 0;
                Version.ECB aecb1[];
/*  69*/        int i1 = (aecb1 = aecb).length;
/*  69*/        for(errorcorrectionlevel = 0; errorcorrectionlevel < i1; errorcorrectionlevel++)
                {
/*  69*/            Version.ECB ecb = aecb1[errorcorrectionlevel];
/*  70*/            for(int j1 = 0; j1 < ecb.getCount(); j1++)
                    {
/*  71*/                int j2 = ecb.getDataCodewords();
/*  72*/                int i3 = version.getECCodewordsPerBlock() + j2;
/*  73*/                aobj[j++] = new DataBlock(j2, new byte[i3]);
                    }

                }

/*  79*/        int l = aobj[0].codewords.length;
/*  80*/        for(i1 = aobj.length - 1; i1 >= 0 && (errorcorrectionlevel = aobj[i1].codewords.length) != l; i1--);
/*  88*/        i1++;
/*  90*/        errorcorrectionlevel = l - version.getECCodewordsPerBlock();
/*  93*/        int i = 0;
/*  94*/        for(int k1 = 0; k1 < errorcorrectionlevel; k1++)
                {
/*  95*/            for(int k2 = 0; k2 < j; k2++)
/*  96*/                aobj[k2].codewords[k1] = abyte0[i++];

                }

/* 100*/        for(int l1 = i1; l1 < j; l1++)
/* 101*/            aobj[l1].codewords[errorcorrectionlevel] = abyte0[i++];

/* 104*/        int i2 = aobj[0].codewords.length;
/* 105*/        for(int l2 = errorcorrectionlevel; l2 < i2; l2++)
                {
/* 106*/            for(int j3 = 0; j3 < j; j3++)
                    {
/* 107*/                version = j3 >= i1 ? ((Version) (l2 + 1)) : ((Version) (l2));
/* 108*/                aobj[j3].codewords[version] = abyte0[i++];
                    }

                }

/* 111*/        return ((DataBlock []) (aobj));
            }

            final int getNumDataCodewords()
            {
/* 115*/        return numDataCodewords;
            }

            final byte[] getCodewords()
            {
/* 119*/        return codewords;
            }

            private final int numDataCodewords;
            private final byte codewords[];
}
