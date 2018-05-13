// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCAReader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader, EAN13Reader

public final class UPCAReader extends UPCEANReader
{

            public UPCAReader()
            {
            }

            public final Result decodeRow(int i, BitArray bitarray, int ai[], Map map)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  46*/        return maybeReturnResult(ean13Reader.decodeRow(i, bitarray, ai, map));
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  52*/        return maybeReturnResult(ean13Reader.decodeRow(i, bitarray, map));
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, FormatException
            {
/*  57*/        return maybeReturnResult(ean13Reader.decode(binarybitmap));
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, FormatException
            {
/*  63*/        return maybeReturnResult(ean13Reader.decode(binarybitmap, map));
            }

            final BarcodeFormat getBarcodeFormat()
            {
/*  68*/        return BarcodeFormat.UPC_A;
            }

            protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
                throws NotFoundException
            {
/*  74*/        return ean13Reader.decodeMiddle(bitarray, ai, stringbuilder);
            }

            private static Result maybeReturnResult(Result result)
                throws FormatException
            {
                String s;
/*  78*/        if((s = result.getText()).charAt(0) == '0')
/*  80*/            return new Result(s.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
/*  82*/        else
/*  82*/            throw FormatException.getFormatInstance();
            }

            private final UPCEANReader ean13Reader = new EAN13Reader();
}
