// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AI013103decoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI013x0xDecoder

final class AI013103decoder extends AI013x0xDecoder
{

            AI013103decoder(BitArray bitarray)
            {
/*  37*/        super(bitarray);
            }

            protected final void addWeightCode(StringBuilder stringbuilder, int i)
            {
/*  42*/        stringbuilder.append("(3103)");
            }

            protected final int checkWeight(int i)
            {
/*  47*/        return i;
            }
}
