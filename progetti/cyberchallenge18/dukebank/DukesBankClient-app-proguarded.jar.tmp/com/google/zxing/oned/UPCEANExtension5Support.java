// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEANExtension5Support.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class UPCEANExtension5Support
{

            UPCEANExtension5Support()
            {
            }

            final Result decodeRow(int i, BitArray bitarray, int ai[])
                throws NotFoundException
            {
                Object obj;
/*  43*/        ((StringBuilder) (obj = decodeRowStringBuffer)).setLength(0);
/*  45*/        bitarray = decodeMiddle(bitarray, ai, ((StringBuilder) (obj)));
/*  47*/        Map map = parseExtensionString(((String) (obj = ((StringBuilder) (obj)).toString())));
/*  50*/        i = new Result(((String) (obj)), null, new ResultPoint[] {
/*  50*/            new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i), new ResultPoint((float)bitarray, i)
                }, BarcodeFormat.UPC_EAN_EXTENSION);
/*  58*/        if(map != null)
/*  59*/            i.putAllMetadata(map);
/*  61*/        return i;
            }

            final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
                int ai1[];
/*  65*/        (ai1 = decodeMiddleCounters)[0] = 0;
/*  67*/        ai1[1] = 0;
/*  68*/        ai1[2] = 0;
/*  69*/        ai1[3] = 0;
/*  70*/        int i = bitarray.getSize();
/*  71*/        ai = ai[1];
/*  73*/        int j = 0;
/*  75*/        for(int k = 0; k < 5 && ai < i; k++)
                {
/*  76*/            int i1 = UPCEANReader.decodeDigit(bitarray, ai1, ai, UPCEANReader.L_AND_G_PATTERNS);
/*  77*/            stringbuilder.append((char)(48 + i1 % 10));
                    int ai2[];
/*  78*/            int j1 = (ai2 = ai1).length;
/*  78*/            for(int k1 = 0; k1 < j1; k1++)
                    {
/*  78*/                int l1 = ai2[k1];
/*  79*/                ai += l1;
                    }

/*  81*/            if(i1 >= 10)
/*  82*/                j |= 1 << 4 - k;
/*  84*/            if(k != 4)
                    {
/*  86*/                ai = bitarray.getNextSet(ai);
/*  87*/                ai = bitarray.getNextUnset(ai);
                    }
                }

/*  91*/        if(stringbuilder.length() != 5)
/*  92*/            throw NotFoundException.getNotFoundInstance();
/*  95*/        int l = determineCheckDigit(j);
/*  96*/        if(extensionChecksum(stringbuilder.toString()) != l)
/*  97*/            throw NotFoundException.getNotFoundInstance();
/* 100*/        else
/* 100*/            return ai;
            }

            private static int extensionChecksum(CharSequence charsequence)
            {
/* 104*/        int i = charsequence.length();
/* 105*/        int j = 0;
/* 106*/        for(int k = i - 2; k >= 0; k -= 2)
/* 107*/            j += charsequence.charAt(k) - 48;

/* 109*/        j *= 3;
/* 110*/        for(int l = i - 1; l >= 0; l -= 2)
/* 111*/            j += charsequence.charAt(l) - 48;

/* 113*/        return (j *= 3) % 10;
            }

            private static int determineCheckDigit(int i)
                throws NotFoundException
            {
/* 119*/        for(int j = 0; j < 10; j++)
/* 120*/            if(i == CHECK_DIGIT_ENCODINGS[j])
/* 121*/                return j;

/* 124*/        throw NotFoundException.getNotFoundInstance();
            }

            private static Map parseExtensionString(String s)
            {
/* 133*/        if(s.length() != 5)
/* 134*/            return null;
/* 136*/        if((s = parseExtension5String(s)) == null)
                {
/* 138*/            return null;
                } else
                {
                    EnumMap enummap;
/* 140*/            (enummap = new EnumMap(com/google/zxing/ResultMetadataType)).put(ResultMetadataType.SUGGESTED_PRICE, s);
/* 142*/            return enummap;
                }
            }

            private static String parseExtension5String(String s)
            {
                String s1;
/* 147*/        switch(s.charAt(0))
                {
/* 149*/        case 48: // '0'
/* 149*/            s1 = "\243";
/* 150*/            break;

/* 152*/        case 53: // '5'
/* 152*/            s1 = "$";
/* 153*/            break;

/* 156*/        case 57: // '9'
/* 156*/            if("90000".equals(s))
/* 158*/                return null;
/* 160*/            if("99991".equals(s))
/* 162*/                return "0.00";
/* 164*/            if("99990".equals(s))
/* 165*/                return "Used";
/* 168*/            s1 = "";
                    break;

/* 171*/        default:
/* 171*/            s1 = "";
                    break;
                }
/* 174*/        String s2 = String.valueOf((s = Integer.parseInt(s.substring(1))) / 100);
/* 176*/        s = (s %= 100) >= 10 ? String.valueOf(s) : (new StringBuilder("0")).append(s).toString();
/* 178*/        return (new StringBuilder()).append(s1).append(s2).append('.').append(s).toString();
            }

            private static final int CHECK_DIGIT_ENCODINGS[] = {
/*  34*/        24, 20, 18, 17, 12, 6, 3, 10, 9, 5
            };
            private final int decodeMiddleCounters[] = new int[4];
            private final StringBuilder decodeRowStringBuffer = new StringBuilder();

}
