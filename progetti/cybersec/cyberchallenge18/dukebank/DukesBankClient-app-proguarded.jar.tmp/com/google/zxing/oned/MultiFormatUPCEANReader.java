// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFormatUPCEANReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, EAN13Reader, EAN8Reader, UPCAReader, 
//            UPCEANReader, UPCEReader

public final class MultiFormatUPCEANReader extends OneDReader
{

            public MultiFormatUPCEANReader(Map map)
            {
/*  44*/        map = map != null ? ((Map) ((Collection)map.get(DecodeHintType.POSSIBLE_FORMATS))) : null;
/*  46*/        ArrayList arraylist = new ArrayList();
/*  47*/        if(map != null)
                {
/*  48*/            if(map.contains(BarcodeFormat.EAN_13))
/*  49*/                arraylist.add(new EAN13Reader());
/*  50*/            else
/*  50*/            if(map.contains(BarcodeFormat.UPC_A))
/*  51*/                arraylist.add(new UPCAReader());
/*  53*/            if(map.contains(BarcodeFormat.EAN_8))
/*  54*/                arraylist.add(new EAN8Reader());
/*  56*/            if(map.contains(BarcodeFormat.UPC_E))
/*  57*/                arraylist.add(new UPCEReader());
                }
/*  60*/        if(arraylist.isEmpty())
                {
/*  61*/            arraylist.add(new EAN13Reader());
/*  63*/            arraylist.add(new EAN8Reader());
/*  64*/            arraylist.add(new UPCEReader());
                }
/*  66*/        readers = (UPCEANReader[])arraylist.toArray(new UPCEANReader[arraylist.size()]);
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException
            {
/*  74*/        int ai[] = UPCEANReader.findStartGuardPattern(bitarray);
                UPCEANReader aupceanreader[];
/*  75*/        int j = (aupceanreader = readers).length;
/*  75*/        int k = 0;
/*  75*/label0:
/*  75*/        do
                {
/*  75*/label1:
                    {
/*  75*/                if(k >= j)
/*  75*/                    break label0;
/*  75*/                Object obj = aupceanreader[k];
/*  78*/                try
                        {
/*  78*/                    obj = ((UPCEANReader) (obj)).decodeRow(i, bitarray, ai, map);
                        }
/*  79*/                catch(ReaderException _ex)
                        {
/*  80*/                    break label1;
                        }
/*  94*/                i = ((Result) (obj)).getBarcodeFormat() != BarcodeFormat.EAN_13 || ((Result) (obj)).getText().charAt(0) != '0' ? 0 : 1;
/*  98*/                bitarray = (bitarray = map != null ? ((BitArray) ((Collection)map.get(DecodeHintType.POSSIBLE_FORMATS))) : null) != null && !bitarray.contains(BarcodeFormat.UPC_A) ? 0 : 1;
/* 102*/                if(i != 0 && bitarray != 0)
                        {
/* 104*/                    (i = new Result(((Result) (obj)).getText().substring(1), ((Result) (obj)).getRawBytes(), ((Result) (obj)).getResultPoints(), BarcodeFormat.UPC_A)).putAllMetadata(((Result) (obj)).getResultMetadata());
/* 109*/                    return i;
                        } else
                        {
/* 111*/                    return ((Result) (obj));
                        }
                    }
/*  75*/            k++;
                } while(true);
/* 114*/        throw NotFoundException.getNotFoundInstance();
            }

            public final void reset()
            {
                UPCEANReader aupceanreader[];
/* 119*/        int i = (aupceanreader = readers).length;
/* 119*/        for(int j = 0; j < i; j++)
                {
                    UPCEANReader upceanreader;
/* 119*/            (upceanreader = aupceanreader[j]).reset();
                }

            }

            private final UPCEANReader readers[];
}
