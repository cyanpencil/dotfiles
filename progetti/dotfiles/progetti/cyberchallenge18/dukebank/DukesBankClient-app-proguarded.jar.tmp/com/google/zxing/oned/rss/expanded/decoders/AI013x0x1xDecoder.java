// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI013x0x1xDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01weightDecoder, GeneralAppIdDecoder

final class AI013x0x1xDecoder extends AI01weightDecoder
{

            AI013x0x1xDecoder(BitArray bitarray, String s, String s1)
            {
/*  46*/        super(bitarray);
/*  47*/        dateCode = s1;
/*  48*/        firstAIdigits = s;
            }

            public final String parseInformation()
                throws NotFoundException
            {
/*  53*/        if(getInformation().getSize() != 84)
                {
/*  54*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/*  57*/            StringBuilder stringbuilder = new StringBuilder();
/*  59*/            encodeCompressedGtin(stringbuilder, 8);
/*  60*/            encodeCompressedWeight(stringbuilder, 48, 20);
/*  61*/            encodeCompressedDate(stringbuilder, 68);
/*  63*/            return stringbuilder.toString();
                }
            }

            private void encodeCompressedDate(StringBuilder stringbuilder, int i)
            {
/*  67*/        if((i = getGeneralDecoder().extractNumericValueFromBitArray(i, 16)) == 38400)
/*  69*/            return;
/*  72*/        stringbuilder.append('(');
/*  73*/        stringbuilder.append(dateCode);
/*  74*/        stringbuilder.append(')');
/*  76*/        int j = i % 32;
/*  77*/        int k = (i /= 32) % 12 + 1;
/*  79*/        if((i /= 12) / 10 == 0)
/*  83*/            stringbuilder.append('0');
/*  85*/        stringbuilder.append(i);
/*  86*/        if(k / 10 == 0)
/*  87*/            stringbuilder.append('0');
/*  89*/        stringbuilder.append(k);
/*  90*/        if(j / 10 == 0)
/*  91*/            stringbuilder.append('0');
/*  93*/        stringbuilder.append(j);
            }

            protected final void addWeightCode(StringBuilder stringbuilder, int i)
            {
/*  98*/        i /= 0x186a0;
/*  99*/        stringbuilder.append('(');
/* 100*/        stringbuilder.append(firstAIdigits);
/* 101*/        stringbuilder.append(i);
/* 102*/        stringbuilder.append(')');
            }

            protected final int checkWeight(int i)
            {
/* 107*/        return i % 0x186a0;
            }

            private static final int HEADER_SIZE = 8;
            private static final int WEIGHT_SIZE = 20;
            private static final int DATE_SIZE = 16;
            private final String dateCode;
            private final String firstAIdigits;
}
