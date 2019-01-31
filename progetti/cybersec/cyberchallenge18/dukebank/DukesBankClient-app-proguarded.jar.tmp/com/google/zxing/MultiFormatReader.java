// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFormatReader.java

package com.google.zxing;

import com.google.zxing.aztec.AztecReader;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.*;

// Referenced classes of package com.google.zxing:
//            BarcodeFormat, BinaryBitmap, DecodeHintType, NotFoundException, 
//            Reader, ReaderException, Result

public final class MultiFormatReader
    implements Reader
{

            public MultiFormatReader()
            {
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/*  54*/        setHints(null);
/*  55*/        return decodeInternal(binarybitmap);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException
            {
/*  68*/        setHints(map);
/*  69*/        return decodeInternal(binarybitmap);
            }

            public final Result decodeWithState(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/*  82*/        if(readers == null)
/*  83*/            setHints(null);
/*  85*/        return decodeInternal(binarybitmap);
            }

            public final void setHints(Map map)
            {
/*  96*/        hints = map;
/*  98*/        boolean flag = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
/* 100*/        Collection collection = map != null ? (Collection)map.get(DecodeHintType.POSSIBLE_FORMATS) : null;
/* 102*/        ArrayList arraylist = new ArrayList();
/* 103*/        if(collection != null)
                {
                    boolean flag1;
/* 104*/            if((flag1 = collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.UPC_E) || collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.CODABAR) || collection.contains(BarcodeFormat.CODE_39) || collection.contains(BarcodeFormat.CODE_93) || collection.contains(BarcodeFormat.CODE_128) || collection.contains(BarcodeFormat.ITF) || collection.contains(BarcodeFormat.RSS_14) || collection.contains(BarcodeFormat.RSS_EXPANDED)) && !flag)
/* 118*/                arraylist.add(new MultiFormatOneDReader(map));
/* 120*/            if(collection.contains(BarcodeFormat.QR_CODE))
/* 121*/                arraylist.add(new QRCodeReader());
/* 123*/            if(collection.contains(BarcodeFormat.DATA_MATRIX))
/* 124*/                arraylist.add(new DataMatrixReader());
/* 126*/            if(collection.contains(BarcodeFormat.AZTEC))
/* 127*/                arraylist.add(new AztecReader());
/* 129*/            if(collection.contains(BarcodeFormat.PDF_417))
/* 130*/                arraylist.add(new PDF417Reader());
/* 132*/            if(collection.contains(BarcodeFormat.MAXICODE))
/* 133*/                arraylist.add(new MaxiCodeReader());
/* 136*/            if(flag1 && flag)
/* 137*/                arraylist.add(new MultiFormatOneDReader(map));
                }
/* 140*/        if(arraylist.isEmpty())
                {
/* 141*/            if(!flag)
/* 142*/                arraylist.add(new MultiFormatOneDReader(map));
/* 145*/            arraylist.add(new QRCodeReader());
/* 146*/            arraylist.add(new DataMatrixReader());
/* 147*/            arraylist.add(new AztecReader());
/* 148*/            arraylist.add(new PDF417Reader());
/* 149*/            arraylist.add(new MaxiCodeReader());
/* 151*/            if(flag)
/* 152*/                arraylist.add(new MultiFormatOneDReader(map));
                }
/* 155*/        readers = (Reader[])arraylist.toArray(new Reader[arraylist.size()]);
            }

            public final void reset()
            {
/* 160*/        if(readers != null)
                {
                    Reader areader[];
/* 161*/            int i = (areader = readers).length;
/* 161*/            for(int j = 0; j < i; j++)
                    {
                        Reader reader;
/* 161*/                (reader = areader[j]).reset();
                    }

                }
            }

            private Result decodeInternal(BinaryBitmap binarybitmap)
                throws NotFoundException
            {
/* 168*/        if(readers == null) goto _L2; else goto _L1
_L1:
                Reader areader[];
                int i;
                int j;
/* 169*/        i = (areader = readers).length;
/* 169*/        j = 0;
_L3:
                Reader reader;
/* 169*/        if(j >= i)
/* 169*/            break; /* Loop/switch isn't completed */
/* 169*/        reader = areader[j];
/* 171*/        return reader.decode(binarybitmap, hints);
/* 172*/        JVM INSTR pop ;
/* 169*/        j++;
/* 169*/        if(true) goto _L3; else goto _L2
_L2:
/* 177*/        throw NotFoundException.getNotFoundInstance();
            }

            private Map hints;
            private Reader readers[];
}
