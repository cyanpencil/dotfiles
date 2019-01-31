// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFormatOneDReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;
import java.util.*;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, CodaBarReader, Code128Reader, Code39Reader, 
//            Code93Reader, ITFReader, MultiFormatUPCEANReader

public final class MultiFormatOneDReader extends OneDReader
{

            public MultiFormatOneDReader(Map map)
            {
/*  43*/        Collection collection = map != null ? (Collection)map.get(DecodeHintType.POSSIBLE_FORMATS) : null;
/*  45*/        boolean flag = map != null && map.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) != null;
/*  47*/        ArrayList arraylist = new ArrayList();
/*  48*/        if(collection != null)
                {
/*  49*/            if(collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E))
/*  53*/                arraylist.add(new MultiFormatUPCEANReader(map));
/*  55*/            if(collection.contains(BarcodeFormat.CODE_39))
/*  56*/                arraylist.add(new Code39Reader(flag));
/*  58*/            if(collection.contains(BarcodeFormat.CODE_93))
/*  59*/                arraylist.add(new Code93Reader());
/*  61*/            if(collection.contains(BarcodeFormat.CODE_128))
/*  62*/                arraylist.add(new Code128Reader());
/*  64*/            if(collection.contains(BarcodeFormat.ITF))
/*  65*/                arraylist.add(new ITFReader());
/*  67*/            if(collection.contains(BarcodeFormat.CODABAR))
/*  68*/                arraylist.add(new CodaBarReader());
/*  70*/            if(collection.contains(BarcodeFormat.RSS_14))
/*  71*/                arraylist.add(new RSS14Reader());
/*  73*/            if(collection.contains(BarcodeFormat.RSS_EXPANDED))
/*  74*/                arraylist.add(new RSSExpandedReader());
                }
/*  77*/        if(arraylist.isEmpty())
                {
/*  78*/            arraylist.add(new MultiFormatUPCEANReader(map));
/*  79*/            arraylist.add(new Code39Reader());
/*  80*/            arraylist.add(new CodaBarReader());
/*  81*/            arraylist.add(new Code93Reader());
/*  82*/            arraylist.add(new Code128Reader());
/*  83*/            arraylist.add(new ITFReader());
/*  84*/            arraylist.add(new RSS14Reader());
/*  85*/            arraylist.add(new RSSExpandedReader());
                }
/*  87*/        readers = (OneDReader[])arraylist.toArray(new OneDReader[arraylist.size()]);
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException
            {
                OneDReader aonedreader[];
                int j;
                int k;
/*  94*/        j = (aonedreader = readers).length;
/*  94*/        k = 0;
_L2:
                OneDReader onedreader;
/*  94*/        if(k >= j)
/*  94*/            break; /* Loop/switch isn't completed */
/*  94*/        onedreader = aonedreader[k];
/*  96*/        return onedreader.decodeRow(i, bitarray, map);
/*  97*/        JVM INSTR pop ;
/*  94*/        k++;
/*  94*/        if(true) goto _L2; else goto _L1
_L1:
/* 102*/        throw NotFoundException.getNotFoundInstance();
            }

            public final void reset()
            {
                OneDReader aonedreader[];
/* 107*/        int i = (aonedreader = readers).length;
/* 107*/        for(int j = 0; j < i; j++)
                {
                    OneDReader onedreader;
/* 107*/            (onedreader = aonedreader[j]).reset();
                }

            }

            private final OneDReader readers[];
}
