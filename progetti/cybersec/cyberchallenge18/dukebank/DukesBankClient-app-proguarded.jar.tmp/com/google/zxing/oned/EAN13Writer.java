// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EAN13Writer.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, EAN13Reader, UPCEANReader

public final class EAN13Writer extends UPCEANWriter
{

            public EAN13Writer()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  46*/        if(barcodeformat != BarcodeFormat.EAN_13)
/*  47*/            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_13, but got ")).append(barcodeformat).toString());
/*  50*/        else
/*  50*/            return super.encode(s, barcodeformat, i, j, map);
            }

            public final boolean[] encode(String s)
            {
/*  55*/        if(s.length() != 13)
/*  56*/            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 13 digits long, but got ")).append(s.length()).toString());
/*  60*/        try
                {
/*  60*/            if(!UPCEANReader.checkStandardUPCEANChecksum(s))
/*  61*/                throw new IllegalArgumentException("Contents do not pass checksum");
                }
/*  63*/        catch(FormatException _ex)
                {
/*  64*/            throw new IllegalArgumentException("Illegal contents");
                }
/*  67*/        int i = Integer.parseInt(s.substring(0, 1));
/*  68*/        i = EAN13Reader.FIRST_DIGIT_ENCODINGS[i];
/*  69*/        boolean aflag[] = new boolean[95];
/*  72*/        int j = 0 + appendPattern(aflag, 0, UPCEANReader.START_END_PATTERN, true);
/*  75*/        for(int k = 1; k <= 6; k++)
                {
/*  76*/            int i1 = Integer.parseInt(s.substring(k, k + 1));
/*  77*/            if((i >> 6 - k & 1) == 1)
/*  78*/                i1 += 10;
/*  80*/            j += appendPattern(aflag, j, UPCEANReader.L_AND_G_PATTERNS[i1], false);
                }

/*  83*/        j += appendPattern(aflag, j, UPCEANReader.MIDDLE_PATTERN, false);
/*  85*/        for(int l = 7; l <= 12; l++)
                {
/*  86*/            int j1 = Integer.parseInt(s.substring(l, l + 1));
/*  87*/            j += appendPattern(aflag, j, UPCEANReader.L_PATTERNS[j1], true);
                }

/*  89*/        appendPattern(aflag, j, UPCEANReader.START_END_PATTERN, true);
/*  91*/        return aflag;
            }

            private static final int CODE_WIDTH = 95;
}
