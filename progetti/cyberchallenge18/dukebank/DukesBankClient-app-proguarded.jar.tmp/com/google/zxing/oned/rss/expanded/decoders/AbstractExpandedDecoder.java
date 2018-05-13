// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractExpandedDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI013103decoder, AI01320xDecoder, AI01392xDecoder, AI01393xDecoder, 
//            AI013x0x1xDecoder, AI01AndOtherAIs, AnyAIDecoder, GeneralAppIdDecoder

public abstract class AbstractExpandedDecoder
{

            AbstractExpandedDecoder(BitArray bitarray)
            {
/*  43*/        information = bitarray;
/*  44*/        generalDecoder = new GeneralAppIdDecoder(bitarray);
            }

            protected final BitArray getInformation()
            {
/*  48*/        return information;
            }

            protected final GeneralAppIdDecoder getGeneralDecoder()
            {
/*  52*/        return generalDecoder;
            }

            public abstract String parseInformation()
                throws NotFoundException, FormatException;

            public static AbstractExpandedDecoder createDecoder(BitArray bitarray)
            {
/*  58*/        if(bitarray.get(1))
/*  59*/            return new AI01AndOtherAIs(bitarray);
/*  61*/        if(!bitarray.get(2))
/*  62*/            return new AnyAIDecoder(bitarray);
                int i;
/*  65*/        switch(i = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 4))
                {
/*  68*/        case 4: // '\004'
/*  68*/            return new AI013103decoder(bitarray);

/*  69*/        case 5: // '\005'
/*  69*/            return new AI01320xDecoder(bitarray);
                }
/*  72*/        switch(i = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 5))
                {
/*  74*/        case 12: // '\f'
/*  74*/            return new AI01392xDecoder(bitarray);

/*  75*/        case 13: // '\r'
/*  75*/            return new AI01393xDecoder(bitarray);
                }
/*  78*/        switch(i = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 7))
                {
/*  80*/        case 56: // '8'
/*  80*/            return new AI013x0x1xDecoder(bitarray, "310", "11");

/*  81*/        case 57: // '9'
/*  81*/            return new AI013x0x1xDecoder(bitarray, "320", "11");

/*  82*/        case 58: // ':'
/*  82*/            return new AI013x0x1xDecoder(bitarray, "310", "13");

/*  83*/        case 59: // ';'
/*  83*/            return new AI013x0x1xDecoder(bitarray, "320", "13");

/*  84*/        case 60: // '<'
/*  84*/            return new AI013x0x1xDecoder(bitarray, "310", "15");

/*  85*/        case 61: // '='
/*  85*/            return new AI013x0x1xDecoder(bitarray, "320", "15");

/*  86*/        case 62: // '>'
/*  86*/            return new AI013x0x1xDecoder(bitarray, "310", "17");

/*  87*/        case 63: // '?'
/*  87*/            return new AI013x0x1xDecoder(bitarray, "320", "17");
                }
/*  90*/        throw new IllegalStateException((new StringBuilder("unknown decoder: ")).append(bitarray).toString());
            }

            private final BitArray information;
            private final GeneralAppIdDecoder generalDecoder;
}
