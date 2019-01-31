// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI01320xDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI013x0xDecoder

final class AI01320xDecoder extends AI013x0xDecoder
{

            AI01320xDecoder(BitArray bitarray)
            {
/*  37*/        super(bitarray);
            }

            protected final void addWeightCode(StringBuilder stringbuilder, int i)
            {
/*  42*/        if(i < 10000)
                {
/*  43*/            stringbuilder.append("(3202)");
/*  43*/            return;
                } else
                {
/*  45*/            stringbuilder.append("(3203)");
/*  47*/            return;
                }
            }

            protected final int checkWeight(int i)
            {
/*  51*/        if(i < 10000)
/*  52*/            return i;
/*  54*/        else
/*  54*/            return i - 10000;
            }
}
