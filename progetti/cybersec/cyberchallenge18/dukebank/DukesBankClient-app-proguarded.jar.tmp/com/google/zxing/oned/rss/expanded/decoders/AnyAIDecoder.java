// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnyAIDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, GeneralAppIdDecoder

final class AnyAIDecoder extends AbstractExpandedDecoder
{

            AnyAIDecoder(BitArray bitarray)
            {
/*  42*/        super(bitarray);
            }

            public final String parseInformation()
                throws NotFoundException, FormatException
            {
/*  47*/        StringBuilder stringbuilder = new StringBuilder();
/*  48*/        return getGeneralDecoder().decodeAllCodes(stringbuilder, 5);
            }

            private static final int HEADER_SIZE = 5;
}
