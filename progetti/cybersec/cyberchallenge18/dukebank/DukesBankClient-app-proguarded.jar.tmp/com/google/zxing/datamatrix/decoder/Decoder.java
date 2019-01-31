// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            BitMatrixParser, DataBlock, DecodedBitStreamParser

public final class Decoder
{

            public Decoder()
            {
/*  38*/        rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);
            }

            public final DecoderResult decode(boolean aflag[][])
                throws FormatException, ChecksumException
            {
/*  51*/        int i = aflag.length;
/*  52*/        BitMatrix bitmatrix = new BitMatrix(i);
/*  53*/        for(int j = 0; j < i; j++)
                {
/*  54*/            for(int k = 0; k < i; k++)
/*  55*/                if(aflag[j][k])
/*  56*/                    bitmatrix.set(k, j);

                }

/*  60*/        return decode(bitmatrix);
            }

            public final DecoderResult decode(BitMatrix bitmatrix)
                throws FormatException, ChecksumException
            {
/*  75*/        Version version = (bitmatrix = new BitMatrixParser(bitmatrix)).getVersion();
/*  79*/        int i = (bitmatrix = DataBlock.getDataBlocks(bitmatrix = bitmatrix.readCodewords(), version)).length;
/*  86*/        int j = 0;
                BitMatrix bitmatrix1;
/*  87*/        int l = (bitmatrix1 = bitmatrix).length;
/*  87*/        for(int j1 = 0; j1 < l; j1++)
                {
/*  87*/            DataBlock datablock1 = bitmatrix1[j1];
/*  88*/            j += datablock1.getNumDataCodewords();
                }

/*  90*/        byte abyte0[] = new byte[j];
/*  93*/        for(int i1 = 0; i1 < i; i1++)
                {
                    DataBlock datablock;
/*  94*/            byte abyte1[] = (datablock = bitmatrix[i1]).getCodewords();
/*  96*/            int k = datablock.getNumDataCodewords();
/*  97*/            correctErrors(abyte1, k);
/*  98*/            for(int k1 = 0; k1 < k; k1++)
/* 100*/                abyte0[k1 * i + i1] = abyte1[k1];

                }

/* 105*/        return DecodedBitStreamParser.decode(abyte0);
            }

            private void correctErrors(byte abyte0[], int i)
                throws ChecksumException
            {
                int j;
/* 117*/        int ai[] = new int[j = abyte0.length];
/* 120*/        for(int l = 0; l < j; l++)
/* 121*/            ai[l] = abyte0[l] & 0xff;

/* 123*/        int i1 = abyte0.length - i;
/* 125*/        try
                {
/* 125*/            rsDecoder.decode(ai, i1);
                }
/* 126*/        catch(ReedSolomonException _ex)
                {
/* 127*/            throw ChecksumException.getChecksumInstance();
                }
/* 131*/        for(int k = 0; k < i; k++)
/* 132*/            abyte0[k] = (byte)ai[k];

            }

            private final ReedSolomonDecoder rsDecoder;
}
