// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            BitMatrixParser, DataBlock, DecodedBitStreamParser, ErrorCorrectionLevel, 
//            FormatInformation, QRCodeDecoderMetaData, Version

public final class Decoder
{

            public Decoder()
            {
/*  41*/        rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
            }

            public final DecoderResult decode(boolean aflag[][])
                throws ChecksumException, FormatException
            {
/*  45*/        return decode(aflag, null);
            }

            public final DecoderResult decode(boolean aflag[][], Map map)
                throws ChecksumException, FormatException
            {
/*  59*/        int i = aflag.length;
/*  60*/        BitMatrix bitmatrix = new BitMatrix(i);
/*  61*/        for(int j = 0; j < i; j++)
                {
/*  62*/            for(int k = 0; k < i; k++)
/*  63*/                if(aflag[j][k])
/*  64*/                    bitmatrix.set(k, j);

                }

/*  68*/        return decode(bitmatrix, map);
            }

            public final DecoderResult decode(BitMatrix bitmatrix)
                throws ChecksumException, FormatException
            {
/*  72*/        return decode(bitmatrix, null);
            }

            public final DecoderResult decode(BitMatrix bitmatrix, Map map)
                throws FormatException, ChecksumException
            {
                FormatException formatexception;
                ChecksumException checksumexception;
/*  87*/        bitmatrix = new BitMatrixParser(bitmatrix);
/*  88*/        formatexception = null;
/*  89*/        checksumexception = null;
/*  91*/        return decode(((BitMatrixParser) (bitmatrix)), map);
/*  92*/        JVM INSTR dup ;
                Object obj;
/*  93*/        obj;
/*  93*/        formatexception;
                  goto _L1
/*  94*/        JVM INSTR dup ;
/*  95*/        obj;
/*  95*/        checksumexception;
_L1:
/* 101*/        bitmatrix.remask();
/* 104*/        bitmatrix.setMirror(true);
/* 107*/        bitmatrix.readVersion();
/* 110*/        bitmatrix.readFormatInformation();
/* 119*/        bitmatrix.mirror();
/* 121*/        ((DecoderResult) (obj = decode(((BitMatrixParser) (bitmatrix)), map))).setOther(new QRCodeDecoderMetaData(true));
/* 126*/        return ((DecoderResult) (obj));
                Object obj1;
/* 128*/        obj1;
/* 130*/        if(formatexception != null)
/* 131*/            throw formatexception;
/* 133*/        if(checksumexception != null)
/* 134*/            throw checksumexception;
/* 136*/        else
/* 136*/            throw obj1;
            }

            private DecoderResult decode(BitMatrixParser bitmatrixparser, Map map)
                throws FormatException, ChecksumException
            {
/* 143*/        Version version = bitmatrixparser.readVersion();
/* 144*/        ErrorCorrectionLevel errorcorrectionlevel = bitmatrixparser.readFormatInformation().getErrorCorrectionLevel();
/* 147*/        bitmatrixparser = DataBlock.getDataBlocks(bitmatrixparser = bitmatrixparser.readCodewords(), version, errorcorrectionlevel);
/* 152*/        int i = 0;
                BitMatrixParser bitmatrixparser1;
/* 153*/        int k = (bitmatrixparser1 = bitmatrixparser).length;
/* 153*/        for(int l = 0; l < k; l++)
                {
/* 153*/            DataBlock datablock1 = bitmatrixparser1[l];
/* 154*/            i += datablock1.getNumDataCodewords();
                }

/* 156*/        byte abyte0[] = new byte[i];
/* 157*/        k = 0;
                BitMatrixParser bitmatrixparser2;
/* 160*/        int i1 = (bitmatrixparser2 = bitmatrixparser).length;
/* 160*/        for(bitmatrixparser = 0; bitmatrixparser < i1; bitmatrixparser++)
                {
                    DataBlock datablock;
/* 160*/            byte abyte1[] = (datablock = bitmatrixparser2[bitmatrixparser]).getCodewords();
/* 162*/            int j = datablock.getNumDataCodewords();
/* 163*/            correctErrors(abyte1, j);
/* 164*/            for(int j1 = 0; j1 < j; j1++)
/* 165*/                abyte0[k++] = abyte1[j1];

                }

/* 170*/        return DecodedBitStreamParser.decode(abyte0, version, errorcorrectionlevel, map);
            }

            private void correctErrors(byte abyte0[], int i)
                throws ChecksumException
            {
                int j;
/* 182*/        int ai[] = new int[j = abyte0.length];
/* 185*/        for(int l = 0; l < j; l++)
/* 186*/            ai[l] = abyte0[l] & 0xff;

/* 188*/        int i1 = abyte0.length - i;
/* 190*/        try
                {
/* 190*/            rsDecoder.decode(ai, i1);
                }
/* 191*/        catch(ReedSolomonException _ex)
                {
/* 192*/            throw ChecksumException.getChecksumInstance();
                }
/* 196*/        for(int k = 0; k < i; k++)
/* 197*/            abyte0[k] = (byte)ai[k];

            }

            private final ReedSolomonDecoder rsDecoder;
}
