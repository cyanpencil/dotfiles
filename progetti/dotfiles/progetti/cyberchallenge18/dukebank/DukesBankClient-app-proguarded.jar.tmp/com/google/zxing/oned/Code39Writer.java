// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code39Writer.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, Code39Reader

public final class Code39Writer extends OneDimensionalCodeWriter
{

            public Code39Writer()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  39*/        if(barcodeformat != BarcodeFormat.CODE_39)
/*  40*/            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_39, but got ")).append(barcodeformat).toString());
/*  42*/        else
/*  42*/            return super.encode(s, barcodeformat, i, j, map);
            }

            public final boolean[] encode(String s)
            {
                int i;
/*  47*/        if((i = s.length()) > 80)
/*  49*/            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
/*  53*/        int ai[] = new int[9];
/*  54*/        int j = i + 25;
/*  55*/        for(int l = 0; l < i; l++)
                {
                    int i1;
/*  56*/            if((i1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(l))) < 0)
/*  58*/                throw new IllegalArgumentException((new StringBuilder("Bad contents: ")).append(s).toString());
/*  60*/            toIntArray(Code39Reader.CHARACTER_ENCODINGS[i1], ai);
/*  61*/            int ai1[] = ai;
/*  61*/            for(int l1 = 0; l1 < 9; l1++)
                    {
/*  61*/                int j1 = ai1[l1];
/*  62*/                j += j1;
                    }

                }

/*  65*/        boolean aflag[] = new boolean[j];
/*  66*/        toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], ai);
/*  67*/        int k1 = appendPattern(aflag, 0, ai, true);
/*  68*/        int ai2[] = {
/*  68*/            1
                };
/*  69*/        k1 += appendPattern(aflag, k1, ai2, false);
/*  71*/        for(int k = 0; k < i; k++)
                {
/*  72*/            int i2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(k));
/*  73*/            toIntArray(Code39Reader.CHARACTER_ENCODINGS[i2], ai);
/*  74*/            k1 = (k1 += appendPattern(aflag, k1, ai, true)) + appendPattern(aflag, k1, ai2, false);
                }

/*  77*/        toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], ai);
/*  78*/        appendPattern(aflag, k1, ai, true);
/*  79*/        return aflag;
            }

            private static void toIntArray(int i, int ai[])
            {
/*  83*/        for(int j = 0; j < 9; j++)
                {
/*  84*/            int k = i & 1 << 8 - j;
/*  85*/            ai[j] = k != 0 ? 2 : 1;
                }

            }
}
