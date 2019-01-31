// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataBlock.java

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

final class DataBlock
{

            private DataBlock(int i, byte abyte0[])
            {
/*  32*/        numDataCodewords = i;
/*  33*/        codewords = abyte0;
            }

            static DataBlock[] getDataBlocks(byte abyte0[], Version version)
            {
/*  49*/        Version.ECBlocks ecblocks = version.getECBlocks();
/*  52*/        int j = 0;
                Version.ECB aecb[];
                Object aobj[];
/*  53*/        int i1 = (aobj = aecb = ecblocks.getECBlocks()).length;
/*  54*/        for(int j1 = 0; j1 < i1; j1++)
                {
/*  54*/            Version.ECB ecb1 = aobj[j1];
/*  55*/            j += ecb1.getCount();
                }

/*  59*/        aobj = new DataBlock[j];
/*  60*/        i1 = 0;
                Version.ECB aecb1[];
/*  61*/        int l1 = (aecb1 = aecb).length;
/*  61*/        for(j = 0; j < l1; j++)
                {
/*  61*/            Version.ECB ecb = aecb1[j];
/*  62*/            for(int i2 = 0; i2 < ecb.getCount(); i2++)
                    {
/*  63*/                int k2 = ecb.getDataCodewords();
/*  64*/                int j3 = ecblocks.getECCodewords() + k2;
/*  65*/                aobj[i1++] = new DataBlock(k2, new byte[j3]);
                    }

                }

                int k1;
/*  72*/        j = (l1 = (k1 = aobj[0].codewords.length) - ecblocks.getECCodewords()) - 1;
/*  79*/        int l = 0;
/*  80*/        for(int j2 = 0; j2 < j; j2++)
                {
/*  81*/            for(int l2 = 0; l2 < i1; l2++)
/*  82*/                aobj[l2].codewords[j2] = abyte0[l++];

                }

                boolean flag;
/*  87*/        int i3 = (flag = version.getVersionNumber() == 24) ? 8 : i1;
/*  89*/        for(int k3 = 0; k3 < i3; k3++)
/*  90*/            aobj[k3].codewords[l1 - 1] = abyte0[l++];

/*  94*/        int l3 = aobj[0].codewords.length;
/*  95*/        for(version = l1; version < l3; version++)
                {
/*  96*/            for(int i = 0; i < i1; i++)
                    {
/*  97*/                int k = ((int) (!flag || i <= 7 ? ((int) (version)) : version - 1));
/*  98*/                aobj[i].codewords[k] = abyte0[l++];
                    }

                }

/* 102*/        if(l != abyte0.length)
/* 103*/            throw new IllegalArgumentException();
/* 106*/        else
/* 106*/            return ((DataBlock []) (aobj));
            }

            final int getNumDataCodewords()
            {
/* 110*/        return numDataCodewords;
            }

            final byte[] getCodewords()
            {
/* 114*/        return codewords;
            }

            private final int numDataCodewords;
            private final byte codewords[];
}
