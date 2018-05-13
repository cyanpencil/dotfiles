// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEANExtension2Support.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class UPCEANExtension2Support
{

            UPCEANExtension2Support()
            {
            }

            final Result decodeRow(int i, BitArray bitarray, int ai[])
                throws NotFoundException
            {
                Object obj;
/*  39*/        ((StringBuilder) (obj = decodeRowStringBuffer)).setLength(0);
/*  41*/        bitarray = decodeMiddle(bitarray, ai, ((StringBuilder) (obj)));
/*  43*/        Map map = parseExtensionString(((String) (obj = ((StringBuilder) (obj)).toString())));
/*  46*/        i = new Result(((String) (obj)), null, new ResultPoint[] {
/*  46*/            new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i), new ResultPoint((float)bitarray, i)
                }, BarcodeFormat.UPC_EAN_EXTENSION);
/*  54*/        if(map != null)
/*  55*/            i.putAllMetadata(map);
/*  57*/        return i;
            }

            final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
                int ai1[];
/*  61*/        (ai1 = decodeMiddleCounters)[0] = 0;
/*  63*/        ai1[1] = 0;
/*  64*/        ai1[2] = 0;
/*  65*/        ai1[3] = 0;
/*  66*/        int i = bitarray.getSize();
/*  67*/        ai = ai[1];
/*  69*/        int j = 0;
/*  71*/        for(int k = 0; k < 2 && ai < i; k++)
                {
/*  72*/            int l = UPCEANReader.decodeDigit(bitarray, ai1, ai, UPCEANReader.L_AND_G_PATTERNS);
/*  73*/            stringbuilder.append((char)(48 + l % 10));
                    int ai2[];
/*  74*/            int i1 = (ai2 = ai1).length;
/*  74*/            for(int j1 = 0; j1 < i1; j1++)
                    {
/*  74*/                int k1 = ai2[j1];
/*  75*/                ai += k1;
                    }

/*  77*/            if(l >= 10)
/*  78*/                j |= 1 << 1 - k;
/*  80*/            if(k != 1)
                    {
/*  82*/                ai = bitarray.getNextSet(ai);
/*  83*/                ai = bitarray.getNextUnset(ai);
                    }
                }

/*  87*/        if(stringbuilder.length() != 2)
/*  88*/            throw NotFoundException.getNotFoundInstance();
/*  91*/        if(Integer.parseInt(stringbuilder.toString()) % 4 != j)
/*  92*/            throw NotFoundException.getNotFoundInstance();
/*  95*/        else
/*  95*/            return ai;
            }

            private static Map parseExtensionString(String s)
            {
/* 104*/        if(s.length() != 2)
                {
/* 105*/            return null;
                } else
                {
                    EnumMap enummap;
/* 107*/            (enummap = new EnumMap(com/google/zxing/ResultMetadataType)).put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(s));
/* 109*/            return enummap;
                }
            }

            private final int decodeMiddleCounters[] = new int[4];
            private final StringBuilder decodeRowStringBuffer = new StringBuilder();
}
