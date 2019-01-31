// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01weightDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, GeneralAppIdDecoder

abstract class AI01weightDecoder extends AI01decoder
{

            AI01weightDecoder(BitArray bitarray)
            {
/*  37*/        super(bitarray);
            }

            protected final void encodeCompressedWeight(StringBuilder stringbuilder, int i, int j)
            {
/*  41*/        i = getGeneralDecoder().extractNumericValueFromBitArray(i, j);
/*  42*/        addWeightCode(stringbuilder, i);
/*  44*/        i = checkWeight(i);
/*  46*/        j = 0x186a0;
/*  47*/        for(int k = 0; k < 5; k++)
                {
/*  48*/            if(i / j == 0)
/*  49*/                stringbuilder.append('0');
/*  51*/            j /= 10;
                }

/*  53*/        stringbuilder.append(i);
            }

            protected abstract void addWeightCode(StringBuilder stringbuilder, int i);

            protected abstract int checkWeight(int i);
}
