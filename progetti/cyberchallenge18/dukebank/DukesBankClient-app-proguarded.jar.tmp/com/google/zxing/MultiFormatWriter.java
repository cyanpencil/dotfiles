// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFormatWriter.java

package com.google.zxing;

import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;

// Referenced classes of package com.google.zxing:
//            BarcodeFormat, Writer, WriterException

public final class MultiFormatWriter
    implements Writer
{

            public MultiFormatWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
                throws WriterException
            {
/*  47*/        return encode(s, barcodeformat, i, j, null);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
        static class _cls1
        {

                    static final int $SwitchMap$com$google$zxing$BarcodeFormat[];

                    static 
                    {
/*  57*/                $SwitchMap$com$google$zxing$BarcodeFormat = new int[BarcodeFormat.values().length];
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.EAN_8.ordinal()] = 1;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.EAN_13.ordinal()] = 2;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.UPC_A.ordinal()] = 3;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.QR_CODE.ordinal()] = 4;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODE_39.ordinal()] = 5;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODE_128.ordinal()] = 6;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.ITF.ordinal()] = 7;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.PDF_417.ordinal()] = 8;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODABAR.ordinal()] = 9;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.DATA_MATRIX.ordinal()] = 10;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
/*  57*/                try
                        {
/*  57*/                    $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.AZTEC.ordinal()] = 11;
                        }
/*  57*/                catch(NoSuchFieldError _ex) { }
                    }
        }

                Object obj;
/*  57*/        switch(_cls1..SwitchMap.com.google.zxing.BarcodeFormat[barcodeformat.ordinal()])
                {
/*  59*/        case 1: // '\001'
/*  59*/            obj = new EAN8Writer();
                    break;

/*  62*/        case 2: // '\002'
/*  62*/            obj = new EAN13Writer();
                    break;

/*  65*/        case 3: // '\003'
/*  65*/            obj = new UPCAWriter();
                    break;

/*  68*/        case 4: // '\004'
/*  68*/            obj = new QRCodeWriter();
                    break;

/*  71*/        case 5: // '\005'
/*  71*/            obj = new Code39Writer();
                    break;

/*  74*/        case 6: // '\006'
/*  74*/            obj = new Code128Writer();
                    break;

/*  77*/        case 7: // '\007'
/*  77*/            obj = new ITFWriter();
                    break;

/*  80*/        case 8: // '\b'
/*  80*/            obj = new PDF417Writer();
                    break;

/*  83*/        case 9: // '\t'
/*  83*/            obj = new CodaBarWriter();
                    break;

/*  86*/        case 10: // '\n'
/*  86*/            obj = new DataMatrixWriter();
                    break;

/*  89*/        case 11: // '\013'
/*  89*/            obj = new AztecWriter();
                    break;

/*  92*/        default:
/*  92*/            throw new IllegalArgumentException((new StringBuilder("No encoder available for format ")).append(barcodeformat).toString());
                }
/*  94*/        return ((Writer) (obj)).encode(s, barcodeformat, i, j, map);
            }
}
