// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EAN8Writer.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, UPCEANReader

public final class EAN8Writer extends UPCEANWriter
{

            public EAN8Writer()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  45*/        if(barcodeformat != BarcodeFormat.EAN_8)
/*  46*/            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_8, but got ")).append(barcodeformat).toString());
/*  50*/        else
/*  50*/            return super.encode(s, barcodeformat, i, j, map);
            }

            public final boolean[] encode(String s)
            {
/*  58*/        if(s.length() != 8)
/*  59*/            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 8 digits long, but got ")).append(s.length()).toString());
/*  63*/        boolean aflag[] = new boolean[67];
/*  66*/        int i = 0 + appendPattern(aflag, 0, UPCEANReader.START_END_PATTERN, true);
/*  68*/        for(int j = 0; j <= 3; j++)
                {
/*  69*/            int l = Integer.parseInt(s.substring(j, j + 1));
/*  70*/            i += appendPattern(aflag, i, UPCEANReader.L_PATTERNS[l], false);
                }

/*  73*/        i += appendPattern(aflag, i, UPCEANReader.MIDDLE_PATTERN, false);
/*  75*/        for(int k = 4; k <= 7; k++)
                {
/*  76*/            int i1 = Integer.parseInt(s.substring(k, k + 1));
/*  77*/            i += appendPattern(aflag, i, UPCEANReader.L_PATTERNS[i1], true);
                }

/*  79*/        appendPattern(aflag, i, UPCEANReader.START_END_PATTERN, true);
/*  81*/        return aflag;
            }

            private static final int CODE_WIDTH = 67;
}
