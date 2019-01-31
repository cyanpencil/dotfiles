// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitArrayBuilder.java

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.List;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            ExpandedPair

final class BitArrayBuilder
{

            private BitArrayBuilder()
            {
            }

            static BitArray buildBitArray(List list)
            {
/*  43*/        int i = (list.size() << 1) - 1;
/*  44*/        if(((ExpandedPair)list.get(list.size() - 1)).getRightChar() == null)
/*  45*/            i--;
/*  48*/        i *= 12;
/*  50*/        BitArray bitarray = new BitArray(i);
/*  51*/        int j = 0;
                ExpandedPair expandedpair;
/*  53*/        int k = (expandedpair = (ExpandedPair)list.get(0)).getRightChar().getValue();
/*  55*/        for(int i1 = 11; i1 >= 0; i1--)
                {
/*  56*/            if((k & 1 << i1) != 0)
/*  57*/                bitarray.set(j);
/*  59*/            j++;
                }

/*  62*/label0:
/*  62*/        for(int j1 = 1; j1 < list.size(); j1++)
                {
                    ExpandedPair expandedpair1;
/*  63*/            int k1 = (expandedpair1 = (ExpandedPair)list.get(j1)).getLeftChar().getValue();
/*  66*/            for(int l1 = 11; l1 >= 0; l1--)
                    {
/*  67*/                if((k1 & 1 << l1) != 0)
/*  68*/                    bitarray.set(j);
/*  70*/                j++;
                    }

/*  73*/            if(expandedpair1.getRightChar() == null)
/*  74*/                continue;
/*  74*/            int i2 = expandedpair1.getRightChar().getValue();
/*  75*/            int l = 11;
/*  75*/            do
                    {
/*  75*/                if(l < 0)
/*  76*/                    continue label0;
/*  76*/                if((i2 & 1 << l) != 0)
/*  77*/                    bitarray.set(j);
/*  79*/                j++;
/*  75*/                l--;
                    } while(true);
                }

/*  83*/        return bitarray;
            }
}
