// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ITFWriter.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, ITFReader

public final class ITFWriter extends OneDimensionalCodeWriter
{

            public ITFWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  42*/        if(barcodeformat != BarcodeFormat.ITF)
/*  43*/            throw new IllegalArgumentException((new StringBuilder("Can only encode ITF, but got ")).append(barcodeformat).toString());
/*  46*/        else
/*  46*/            return super.encode(s, barcodeformat, i, j, map);
            }

            public final boolean[] encode(String s)
            {
                int i;
/*  51*/        if((i = s.length()) % 2 != 0)
/*  53*/            throw new IllegalArgumentException("The lenght of the input should be even");
/*  55*/        if(i > 80)
/*  56*/            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
                boolean aflag[];
/*  59*/        int j = appendPattern(aflag = new boolean[9 + i * 9], 0, START_PATTERN, true);
/*  61*/        for(int k = 0; k < i; k += 2)
                {
/*  62*/            int l = Character.digit(s.charAt(k), 10);
/*  63*/            int i1 = Character.digit(s.charAt(k + 1), 10);
/*  64*/            int ai[] = new int[18];
/*  65*/            for(int j1 = 0; j1 < 5; j1++)
                    {
/*  66*/                ai[j1 << 1] = ITFReader.PATTERNS[l][j1];
/*  67*/                ai[(j1 << 1) + 1] = ITFReader.PATTERNS[i1][j1];
                    }

/*  69*/            j += appendPattern(aflag, j, ai, true);
                }

/*  71*/        appendPattern(aflag, j, END_PATTERN, true);
/*  73*/        return aflag;
            }

            private static final int START_PATTERN[] = {
/*  33*/        1, 1, 1, 1
            };
            private static final int END_PATTERN[] = {
/*  34*/        3, 1, 1
            };

}
