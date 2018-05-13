// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFormatWriter.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            BarcodeFormat, MultiFormatWriter

static class 
{

            static final int $SwitchMap$com$google$zxing$BarcodeFormat[];

            static 
            {
/*  57*/        $SwitchMap$com$google$zxing$BarcodeFormat = new int[BarcodeFormat.values().length];
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.EAN_8.ordinal()] = 1;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.EAN_13.ordinal()] = 2;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.UPC_A.ordinal()] = 3;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.QR_CODE.ordinal()] = 4;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODE_39.ordinal()] = 5;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODE_128.ordinal()] = 6;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.ITF.ordinal()] = 7;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.PDF_417.ordinal()] = 8;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.CODABAR.ordinal()] = 9;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.DATA_MATRIX.ordinal()] = 10;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
/*  57*/        try
                {
/*  57*/            $SwitchMap$com$google$zxing$BarcodeFormat[BarcodeFormat.AZTEC.ordinal()] = 11;
                }
/*  57*/        catch(NoSuchFieldError _ex) { }
            }
}
