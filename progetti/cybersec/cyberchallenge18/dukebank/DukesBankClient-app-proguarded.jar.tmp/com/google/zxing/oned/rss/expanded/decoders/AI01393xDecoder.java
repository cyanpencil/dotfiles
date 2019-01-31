// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01393xDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, DecodedInformation, GeneralAppIdDecoder

final class AI01393xDecoder extends AI01decoder
{

            AI01393xDecoder(BitArray bitarray)
            {
/*  42*/        super(bitarray);
            }

            public final String parseInformation()
                throws NotFoundException, FormatException
            {
/*  47*/        if(getInformation().getSize() < 48)
/*  48*/            throw NotFoundException.getNotFoundInstance();
/*  51*/        StringBuilder stringbuilder = new StringBuilder();
/*  53*/        encodeCompressedGtin(stringbuilder, 8);
/*  55*/        int i = getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
/*  58*/        stringbuilder.append("(393");
/*  59*/        stringbuilder.append(i);
/*  60*/        stringbuilder.append(')');
/*  62*/        if((i = getGeneralDecoder().extractNumericValueFromBitArray(50, 10)) / 100 == 0)
/*  65*/            stringbuilder.append('0');
/*  67*/        if(i / 10 == 0)
/*  68*/            stringbuilder.append('0');
/*  70*/        stringbuilder.append(i);
/*  72*/        DecodedInformation decodedinformation = getGeneralDecoder().decodeGeneralPurposeField(60, null);
/*  74*/        stringbuilder.append(decodedinformation.getNewString());
/*  76*/        return stringbuilder.toString();
            }

            private static final int HEADER_SIZE = 8;
            private static final int LAST_DIGIT_SIZE = 2;
            private static final int FIRST_THREE_DIGITS_SIZE = 10;
}
