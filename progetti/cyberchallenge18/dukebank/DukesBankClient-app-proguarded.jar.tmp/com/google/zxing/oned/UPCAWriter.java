// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCAWriter.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            EAN13Writer

public final class UPCAWriter
    implements Writer
{

            public UPCAWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
                throws WriterException
            {
/*  39*/        return encode(s, barcodeformat, i, j, null);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  48*/        if(barcodeformat != BarcodeFormat.UPC_A)
/*  49*/            throw new IllegalArgumentException((new StringBuilder("Can only encode UPC-A, but got ")).append(barcodeformat).toString());
/*  51*/        else
/*  51*/            return subWriter.encode(preencode(s), BarcodeFormat.EAN_13, i, j, map);
            }

            private static String preencode(String s)
            {
                int i;
/*  59*/        if((i = s.length()) == 11)
                {
/*  62*/            i = 0;
/*  63*/            for(int j = 0; j < 11; j++)
/*  64*/                i += (s.charAt(j) - 48) * (j % 2 != 0 ? 1 : 3);

/*  66*/            s = (new StringBuilder()).append(s).append((1000 - i) % 10).toString();
                } else
/*  67*/        if(i != 12)
/*  68*/            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 11 or 12 digits long, but got ")).append(s.length()).toString());
/*  71*/        return (new StringBuilder("0")).append(s).toString();
            }

            private final EAN13Writer subWriter = new EAN13Writer();
}
