// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.maxicode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;
import java.util.Map;

// Referenced classes of package com.google.zxing.maxicode.decoder:
//            BitMatrixParser, DecodedBitStreamParser

public final class Decoder
{

            public Decoder()
            {
/*  45*/        rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);
            }

            public final DecoderResult decode(BitMatrix bitmatrix)
                throws ChecksumException, FormatException
            {
/*  49*/        return decode(bitmatrix, null);
            }

            public final DecoderResult decode(BitMatrix bitmatrix, Map map)
                throws FormatException, ChecksumException
            {
/*  54*/        bitmatrix = (bitmatrix = new BitMatrixParser(bitmatrix)).readCodewords();
/*  57*/        correctErrors(bitmatrix, 0, 10, 10, 0);
                byte abyte0[];
/*  58*/        switch(map = bitmatrix[0] & 0xf)
                {
/*  64*/        case 2: // '\002'
/*  64*/        case 3: // '\003'
/*  64*/        case 4: // '\004'
/*  64*/            correctErrors(bitmatrix, 20, 84, 40, 1);
/*  65*/            correctErrors(bitmatrix, 20, 84, 40, 2);
/*  66*/            abyte0 = new byte[94];
                    break;

/*  69*/        case 5: // '\005'
/*  69*/            correctErrors(bitmatrix, 20, 68, 56, 1);
/*  70*/            correctErrors(bitmatrix, 20, 68, 56, 2);
/*  71*/            abyte0 = new byte[78];
                    break;

/*  74*/        default:
/*  74*/            throw FormatException.getFormatInstance();
                }
/*  77*/        System.arraycopy(bitmatrix, 0, abyte0, 0, 10);
/*  78*/        System.arraycopy(bitmatrix, 20, abyte0, 10, abyte0.length - 10);
/*  80*/        return DecodedBitStreamParser.decode(abyte0, map);
            }

            private void correctErrors(byte abyte0[], int i, int j, int k, int l)
                throws ChecksumException
            {
/*  88*/        int i1 = j + k;
/*  91*/        byte byte0 = ((byte)(l != 0 ? 2 : 1));
/*  94*/        int ai[] = new int[i1 / byte0];
/*  95*/        for(int j1 = 0; j1 < i1; j1++)
/*  96*/            if(l == 0 || j1 % 2 == l - 1)
/*  97*/                ai[j1 / byte0] = abyte0[j1 + i] & 0xff;

/* 101*/        try
                {
/* 101*/            rsDecoder.decode(ai, k / byte0);
                }
/* 102*/        catch(ReedSolomonException _ex)
                {
/* 103*/            throw ChecksumException.getChecksumInstance();
                }
/* 107*/        for(int k1 = 0; k1 < j; k1++)
/* 108*/            if(l == 0 || k1 % 2 == l - 1)
/* 109*/                abyte0[k1 + i] = (byte)ai[k1 / byte0];

            }

            private static final int ALL = 0;
            private static final int EVEN = 1;
            private static final int ODD = 2;
            private final ReedSolomonDecoder rsDecoder;
}
