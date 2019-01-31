// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI013x0xDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01weightDecoder

abstract class AI013x0xDecoder extends AI01weightDecoder
{

            AI013x0xDecoder(BitArray bitarray)
            {
/*  41*/        super(bitarray);
            }

            public String parseInformation()
                throws NotFoundException
            {
/*  46*/        if(getInformation().getSize() != 60)
                {
/*  47*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/*  50*/            StringBuilder stringbuilder = new StringBuilder();
/*  52*/            encodeCompressedGtin(stringbuilder, 5);
/*  53*/            encodeCompressedWeight(stringbuilder, 45, 15);
/*  55*/            return stringbuilder.toString();
                }
            }

            private static final int HEADER_SIZE = 5;
            private static final int WEIGHT_SIZE = 15;
}
