// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01AndOtherAIs.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, GeneralAppIdDecoder

final class AI01AndOtherAIs extends AI01decoder
{

            AI01AndOtherAIs(BitArray bitarray)
            {
/*  42*/        super(bitarray);
            }

            public final String parseInformation()
                throws NotFoundException, FormatException
            {
                StringBuilder stringbuilder;
/*  47*/        (stringbuilder = new StringBuilder()).append("(01)");
/*  50*/        int i = stringbuilder.length();
/*  51*/        int j = getGeneralDecoder().extractNumericValueFromBitArray(4, 4);
/*  52*/        stringbuilder.append(j);
/*  54*/        encodeCompressedGtinWithoutAI(stringbuilder, 8, i);
/*  56*/        return getGeneralDecoder().decodeAllCodes(stringbuilder, 48);
            }

            private static final int HEADER_SIZE = 4;
}
