// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AztecWriter.java

package com.google.zxing.aztec;

import com.google.zxing.*;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter
    implements Writer
{

            public AztecWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
            {
/*  35*/        return encode(s, barcodeformat, i, j, null);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
            {
/*  40*/        String s1 = map != null ? (String)map.get(EncodeHintType.CHARACTER_SET) : null;
/*  41*/        Number number = map != null ? (Number)map.get(EncodeHintType.ERROR_CORRECTION) : null;
/*  42*/        map = map != null ? ((Map) ((Number)map.get(EncodeHintType.AZTEC_LAYERS))) : null;
/*  43*/        return encode(s, barcodeformat, i, j, s1 != null ? Charset.forName(s1) : DEFAULT_CHARSET, number != null ? number.intValue() : 33, map != null ? map.intValue() : 0);
            }

            private static BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Charset charset, int k, int l)
            {
/*  55*/        if(barcodeformat != BarcodeFormat.AZTEC)
/*  56*/            throw new IllegalArgumentException((new StringBuilder("Can only encode AZTEC, but got ")).append(barcodeformat).toString());
/*  58*/        else
/*  58*/            return renderResult(s = Encoder.encode(s.getBytes(charset), k, l), i, j);
            }

            private static BitMatrix renderResult(AztecCode azteccode, int i, int j)
            {
/*  63*/        if((azteccode = azteccode.getMatrix()) == null)
/*  65*/            throw new IllegalStateException();
/*  67*/        int k = azteccode.getWidth();
/*  68*/        int l = azteccode.getHeight();
/*  69*/        i = Math.max(i, k);
/*  70*/        j = Math.max(j, l);
/*  72*/        int i1 = Math.min(i / k, j / l);
/*  73*/        int j1 = (i - k * i1) / 2;
/*  74*/        int k1 = (j - l * i1) / 2;
/*  76*/        i = new BitMatrix(i, j);
/*  78*/        j = 0;
/*  78*/        for(k1 = k1; j < l; k1 += i1)
                {
/*  80*/            int l1 = 0;
/*  80*/            for(int i2 = j1; l1 < k; i2 += i1)
                    {
/*  81*/                if(azteccode.get(l1, j))
/*  82*/                    i.setRegion(i2, k1, i1, i1);
/*  80*/                l1++;
                    }

/*  78*/            j++;
                }

/*  86*/        return i;
            }

            private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

}
