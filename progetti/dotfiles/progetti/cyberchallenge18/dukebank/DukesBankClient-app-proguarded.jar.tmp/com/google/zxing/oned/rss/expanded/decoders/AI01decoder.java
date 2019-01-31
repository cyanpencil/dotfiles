// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01decoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, GeneralAppIdDecoder

abstract class AI01decoder extends AbstractExpandedDecoder
{

            AI01decoder(BitArray bitarray)
            {
/*  40*/        super(bitarray);
            }

            protected final void encodeCompressedGtin(StringBuilder stringbuilder, int i)
            {
/*  44*/        stringbuilder.append("(01)");
/*  45*/        int j = stringbuilder.length();
/*  46*/        stringbuilder.append('9');
/*  48*/        encodeCompressedGtinWithoutAI(stringbuilder, i, j);
            }

            protected final void encodeCompressedGtinWithoutAI(StringBuilder stringbuilder, int i, int j)
            {
/*  52*/        for(int k = 0; k < 4; k++)
                {
                    int l;
/*  53*/            if((l = getGeneralDecoder().extractNumericValueFromBitArray(i + k * 10, 10)) / 100 == 0)
/*  55*/                stringbuilder.append('0');
/*  57*/            if(l / 10 == 0)
/*  58*/                stringbuilder.append('0');
/*  60*/            stringbuilder.append(l);
                }

/*  63*/        appendCheckDigit(stringbuilder, j);
            }

            private static void appendCheckDigit(StringBuilder stringbuilder, int i)
            {
/*  67*/        int j = 0;
/*  68*/        for(int k = 0; k < 13; k++)
                {
/*  69*/            int l = stringbuilder.charAt(k + i) - 48;
/*  70*/            j += (k & 1) != 0 ? l : 3 * l;
                }

/*  73*/        if((j = 10 - j % 10) == 10)
/*  75*/            j = 0;
/*  78*/        stringbuilder.append(j);
            }

            protected static final int GTIN_SIZE = 40;
}
