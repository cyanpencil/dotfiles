// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OneDimensionalCodeWriter.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public abstract class OneDimensionalCodeWriter
    implements Writer
{

            public OneDimensionalCodeWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
                throws WriterException
            {
/*  37*/        return encode(s, barcodeformat, i, j, null);
            }

            public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  53*/        if(s.isEmpty())
/*  54*/            throw new IllegalArgumentException("Found empty contents");
/*  57*/        if(i < 0 || j < 0)
/*  58*/            throw new IllegalArgumentException((new StringBuilder("Negative size is not allowed. Input: ")).append(i).append('x').append(j).toString());
/*  62*/        barcodeformat = getDefaultMargin();
/*  63*/        if(map != null && (map = (Integer)map.get(EncodeHintType.MARGIN)) != null)
/*  66*/            barcodeformat = map.intValue();
/*  70*/        return renderResult(map = encode(s), i, j, barcodeformat);
            }

            private static BitMatrix renderResult(boolean aflag[], int i, int j, int k)
            {
                int l;
/*  78*/        k = (l = aflag.length) + k;
/*  81*/        i = Math.max(i, k);
/*  82*/        j = Math.max(1, j);
/*  84*/        k = i / k;
/*  85*/        int i1 = (i - l * k) / 2;
/*  87*/        i = new BitMatrix(i, j);
/*  88*/        int j1 = 0;
/*  88*/        for(i1 = i1; j1 < l; i1 += k)
                {
/*  89*/            if(aflag[j1])
/*  90*/                i.setRegion(i1, 0, k, j);
/*  88*/            j1++;
                }

/*  93*/        return i;
            }

            protected static int appendPattern(boolean aflag[], int i, int ai[], boolean flag)
            {
/* 104*/        flag = flag;
/* 105*/        int j = 0;
/* 106*/        int k = (ai = ai).length;
/* 106*/        for(int l = 0; l < k; l++)
                {
/* 106*/            int i1 = ai[l];
/* 107*/            for(int j1 = 0; j1 < i1; j1++)
/* 108*/                aflag[i++] = flag;

/* 110*/            j += i1;
/* 111*/            flag = !flag;
                }

/* 113*/        return j;
            }

            public int getDefaultMargin()
            {
/* 119*/        return 10;
            }

            public abstract boolean[] encode(String s);
}
