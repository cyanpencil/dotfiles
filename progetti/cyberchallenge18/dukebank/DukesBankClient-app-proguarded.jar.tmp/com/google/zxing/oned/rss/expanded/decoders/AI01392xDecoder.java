// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01392xDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, DecodedInformation, GeneralAppIdDecoder

final class AI01392xDecoder extends AI01decoder
{

            AI01392xDecoder(BitArray bitarray)
            {
/*  42*/        super(bitarray);
            }

            public final String parseInformation()
                throws NotFoundException, FormatException
            {
/*  47*/        if(getInformation().getSize() < 48)
                {
/*  48*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/*  51*/            StringBuilder stringbuilder = new StringBuilder();
/*  53*/            encodeCompressedGtin(stringbuilder, 8);
/*  55*/            int i = getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
/*  57*/            stringbuilder.append("(392");
/*  58*/            stringbuilder.append(i);
/*  59*/            stringbuilder.append(')');
/*  61*/            DecodedInformation decodedinformation = getGeneralDecoder().decodeGeneralPurposeField(50, null);
/*  63*/            stringbuilder.append(decodedinformation.getNewString());
/*  65*/            return stringbuilder.toString();
                }
            }

            private static final int HEADER_SIZE = 8;
            private static final int LAST_DIGIT_SIZE = 2;
}
